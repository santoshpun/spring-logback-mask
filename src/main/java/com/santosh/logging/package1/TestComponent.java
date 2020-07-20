package com.santosh.logging.package1;

import com.santosh.logging.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestComponent {

    public void test1() {
        UserDetail userDetail = new UserDetail();
        userDetail.setName("Santosh Pun");
        userDetail.setCardNo("5500000000000004");
        userDetail.setPassword("abc1234");

        log.info("user detail : " + userDetail);

        log.info("{ \"name\" : \"Santosh Pun\", \"cardNo\" : \"5500000000000004\", \"password\" : \"abc1234\" }");
    }
}
