package com.demo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Collections;

@SpringBootApplication
public class DemoApiApplication {

	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoApiApplication.class);

        // Handle running from project root where compose.yaml is inside the module
        File composeFile = new File("compose.yaml");
        File moduleComposeFile = new File("demo-api/compose.yaml");

        if (!composeFile.exists() && moduleComposeFile.exists()) {
            app.setDefaultProperties(Collections.singletonMap("spring.docker.compose.file", "demo-api/compose.yaml"));
        }

        app.run(args);
    }

}
