package com.santosh.logging.package2;

import com.santosh.logging.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloComponent {

    public void test1() {
        log.info("Hell world");
    }
}
