package com.deo.raghav;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
    	
    	
    	System.out.print("Enter ip address of mysql server (default localhost): ");
    	Scanner scanner1 = new Scanner(System.in);
    	String temp1 = "";
    	if (scanner1.hasNextLine()) {
    		temp1 = scanner1.nextLine();
    	} else {
    		temp1 = "";
    	}
    	if (temp1.length() > 0) {
    		Database_Details.ip_address = temp1;
    	}
    	
    	System.out.print("Enter port number (default 3306) : ");
    	Scanner scanner2 = new Scanner(System.in);
    	String temp2 = "";
    	if (scanner2.hasNextLine()) {
    		temp2 = scanner2.nextLine();
    	} else {
    		temp2 = "";
    	}
    	if (temp2.length() > 0) {
    		Database_Details.port = temp2;
    	}
    	
    	System.out.print("Enter database name (default test) : ");
    	Scanner scanner3 = new Scanner(System.in);
    	String temp3 = "";
    	if (scanner3.hasNextLine()) {
    		temp3 = scanner3.nextLine();
    	} else {
    		temp3 = "";
    	}
    	if (temp3.length() > 0) {
    		Database_Details.database_name = temp3;
    	}
    	
    	System.out.print("Enter username for mysql server (default root) : ");
    	Scanner scanner4 = new Scanner(System.in);
    	String temp4 = "";
    	if (scanner4.hasNextLine()) {
    		temp4 = scanner4.nextLine();
    	} else {
    		temp4 = "";
    	}
    	if (temp4.length() > 0) {
    		Database_Details.username = temp4;
    	}
    	
    	System.out.print("Enter password for mysql server (default 1234) : ");
    	Scanner scanner5 = new Scanner(System.in);
    	String temp5 = "";
    	if (scanner5.hasNextLine()) {
    		temp5 = scanner5.nextLine();
    	} else {
    		temp5 = "";
    	}
    	if (temp5.length() > 0) {
    		Database_Details.password = temp5;
    	}
    	

        SpringApplication.run(Application.class, args);
    }

}
