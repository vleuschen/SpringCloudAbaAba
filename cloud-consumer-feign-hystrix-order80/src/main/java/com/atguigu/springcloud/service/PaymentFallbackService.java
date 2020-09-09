package com.atguigu.springcloud.service;


import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{


    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService paymentInfo_OK fall back,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallbackService paymentInfo_TimeOut fall back,o(╥﹏╥)o";
    }
}
