package com.santosh.logging;

import com.santosh.logging.package1.TestComponent;
import com.santosh.logging.package2.HelloComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private TestComponent testComponent;
    @Autowired
    private HelloComponent helloComponent;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testComponent.test1();
        helloComponent.test1();
    }
}
