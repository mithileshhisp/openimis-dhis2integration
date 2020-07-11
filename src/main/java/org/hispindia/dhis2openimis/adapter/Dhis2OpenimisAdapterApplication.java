package org.hispindia.dhis2openimis.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Dhis2OpenimisAdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dhis2OpenimisAdapterApplication.class, args);
    }

}
