package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/save")
    public CommonResult save(Payment payment){

        int result = paymentService.save(payment);
        log.info("****插入结果{}",result);

        if(result > 0){
            return new CommonResult(200,"插入数据成功",result);
        }else{
            return new CommonResult(444,"插入数据失败",null);
        }
    }

    @GetMapping("/getPaymentId/{id}")
    public CommonResult<Payment> getPaymentId(@PathVariable("id")Long id){

        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果{}",payment.toString());


        if(payment != null){
            return new CommonResult(200,"查询成功",payment);
        }else {
            return new CommonResult(444,"查询失败",null);
        }

    }

}
