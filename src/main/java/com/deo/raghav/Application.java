package com.deo.raghav;

import java.util.NoSuchElementException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
    	
    	String temp1 = "";
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("Enter ip address of mysql server (default localhost): ");
    	try {
    		temp1 = scanner.nextLine();
    		if (temp1.length() > 0) {
    			Database_Details.ip_address = temp1;
    		}
    	} catch (NoSuchElementException e) {	
    	}
    	
    	System.out.println("Enter port number (default 3306) : ");
    	try {
    		temp1 = scanner.nextLine();
    		if (temp1.length() > 0) {
    			Database_Details.port = temp1;
    		}
    	} catch (NoSuchElementException e) {
    	}
    	
    	System.out.println("Enter database name (default test) : ");
    	try {
    		temp1 = scanner.nextLine();
    		if (temp1.length() > 0) {
    			Database_Details.database_name = temp1;
    		}
    	} catch (NoSuchElementException e) {
    	}
    	
    	System.out.println("Enter username for mysql server (default root) : ");
    	try {
    		temp1 = scanner.nextLine();
    		if (temp1.length() > 0) {
    			Database_Details.username = temp1;
    		}
    	} catch (NoSuchElementException e) {
    		
    	}
    	
    	System.out.println("Enter password for mysql server (default 1234) : ");
    	try {
    		temp1 = scanner.nextLine();
    		if (temp1.length() > 0) {
    			Database_Details.password = temp1;
    		}
    	} catch (NoSuchElementException e) {
    		
    	}
    	
    	scanner.close();
    	
    	System.setProperty("spring.devtools.restart.enabled", "false");
    	
    	HikariConfig hikari_config = new HikariConfig();
    	hikari_config.setJdbcUrl("jdbc:mysql://" + Database_Details.ip_address + ":" + Database_Details.port + "/" + Database_Details.database_name);
    	hikari_config.setUsername(Database_Details.username);
    	hikari_config.setPassword(Database_Details.password);
    	hikari_config.setAutoCommit(false);
    	hikari_config.addDataSourceProperty("cachePrepStmts", "true");
    	hikari_config.addDataSourceProperty("prepStmtCacheSize", "250");
    	hikari_config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    	Database_Details.hikari_data_source = new HikariDataSource(hikari_config);

        SpringApplication.run(Application.class, args);
    }

}
