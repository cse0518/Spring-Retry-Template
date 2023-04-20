package com.example.retrytest.controller;


import com.example.retrytest.service.RetryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RetryController {

    private final RetryService retryService;

    @GetMapping("/{value}")
    public int success(@PathVariable int value) {
        System.out.println("input 값 : " + value);
        retryService.setting(value);
        retryService.test();
        System.out.println("return : " + retryService.getGlobal());
        return retryService.getGlobal();
    }
}
