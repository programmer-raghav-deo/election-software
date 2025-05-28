/**
 * 
 */
package com.deo.raghav;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @author raghav
 *
 */

public class Database_Details {
	
	public static String ip_address = "localhost";
	public static String port = "3306";
	public static String database_name = "test";
	public static String username = "root";
	public static String password = "1234";
	public static HikariDataSource hikari_data_source;

}
