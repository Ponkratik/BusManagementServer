package com.ponkratov.busmanagementserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:context.xml")
public class BusManagementServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusManagementServerApplication.class, args);
    }

}
