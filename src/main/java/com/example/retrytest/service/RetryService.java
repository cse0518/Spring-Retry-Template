package com.example.retrytest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetryService {

    private int global;

    public void setting(int value) {
        global = value;
    }

    public int getGlobal() {
        return global;
    }

    @Retryable(
            value = RuntimeException.class,
            maxAttempts = 2,
            backoff = @Backoff(delay = 2000)
    )
    public void test() {
        global++;
        log.info("1 더하기");

        if (global % 4 == 0) {
            return;
        }
        throw new RuntimeException("runtime exception");
    }

    @Recover
    public void recover(RuntimeException e, String name) {
        log.info(e.getMessage() + ", " + name);
    }
}
