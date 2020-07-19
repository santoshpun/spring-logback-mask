package com.santosh.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestComponent {

    public void test1() {
        UserDetail userDetail = new UserDetail();
        userDetail.setName("Santosh Pun");
        userDetail.setSsn("3310104322");

        //logger.debug("{ \"name\":\"noor\", \"cardNo\":\"12345\" }");
        //log.info("user detail : " + userDetail);
        log.info("{ \"user_id\" : \"1234\", \"ssn\" : \"3310104322\", \"favourite_team\" : \"Juventus\", \"address\" : \"Wiejska 4, Warszawa\", \"additional_info_1\" : \"192.168.1.1\", \"additional_info_2\" : \"bianconeri36@gmail.com\" }");
    }
}
