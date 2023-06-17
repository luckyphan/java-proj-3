package com.javaunit3.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//run and return the beans in the ApplicationContext
//beans: instances created by the Spring container (IoC is the applicationContext)

@SpringBootApplication
public class SpringmvcApplication {
    public static void main(String[] args) {
        //launches the app, the bean defined in run is created
        //starts the Spring framework and integrates the main with springboot
        //needs the ComponentScan so do not define the bean in class for run
        //mvn spring-boot:run
        SpringApplication.run(SpringmvcApplication.class, args);
    }
}
