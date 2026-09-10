package com.example.employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@SpringBootApplication
@EnableCaching
public class EmployeeManagementApplication {
 public static void main(String[] args){SpringApplication.run(EmployeeManagementApplication.class,args);}
}