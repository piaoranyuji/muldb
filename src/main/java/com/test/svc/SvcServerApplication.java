package com.test.svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement
public class SvcServerApplication {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(SvcServerApplication.class);
        springApplication.addListeners(new SvcListeners());
        springApplication.run(args);
    }
}
