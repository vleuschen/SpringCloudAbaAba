package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/save")
    public CommonResult save(@RequestBody Payment payment){

        int result = paymentService.save(payment);
        log.info("****插入结果{}",result);

        if(result > 0){
            return new CommonResult(200,"插入数据成功,serverPort:"+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据失败",null);
        }
    }

    @GetMapping("/getPaymentId/{id}")
    public CommonResult<Payment> getPaymentId(@PathVariable("id")Long id){

        Payment payment = paymentService.getPaymentById(id);

        if(payment != null){
            log.info("****查询结果{}",payment.toString());
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }else {
            return new CommonResult(444,"查询失败",null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        //获得注册进Eureka所有的服务
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("****element:"+service);
        }
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        for (ServiceInstance serviceInstance : serviceInstances) {
            log.info(serviceInstance.getInstanceId()+"\t"+serviceInstance.getHost()+"\t"+serviceInstance.getPort()+"\t"+serviceInstance.getUri());
        }

        return this.discoveryClient;
    }



}
