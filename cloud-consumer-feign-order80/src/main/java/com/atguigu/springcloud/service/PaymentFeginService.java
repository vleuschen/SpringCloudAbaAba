package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") //使用Fegin
public interface PaymentFeginService {

    @GetMapping("/payment/getPaymentId/{id}")
    public CommonResult<Payment> getPaymentId(@PathVariable("id")Long id);

    @GetMapping("/payment/feign/timeout")
    public String paymentFeiginTimeout();
}
