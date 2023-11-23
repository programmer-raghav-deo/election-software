/**
 * 
 */
package com.deo.raghav;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * @author raghav
 *
 */
@Route("vote")
public class VoteView extends VerticalLayout {

	Boolean Vote(String choice) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + Database_Details.ip_address + ":" + Database_Details.port + "/" + Database_Details.database_name, Database_Details.username, Database_Details.password);
			java.sql.Statement statement = connection.createStatement();
			ResultSet result_set = statement.executeQuery("SELECT votes FROM cartoon WHERE name = '" + choice + "';");
			Integer temp = 0;
			while (result_set.next()) {
				temp = result_set.getInt("votes");
			}
			statement.executeUpdate("UPDATE cartoon SET votes = " + Integer.toString(temp + 1) + " WHERE name = '" + choice + "'");
			return true;
		} catch (SQLException e1) {
			System.out.println("Connection failed !");
			e1.printStackTrace();
			return false;
		}
	}
	
	public VoteView() {
		
		if (UI.getCurrent().getSession().getAttribute("logged_in") != null && (boolean)UI.getCurrent().getSession().getAttribute("logged_in") == true) {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				System.out.println("Driver not found !");
			}

	       VerticalLayout candidate_1 = new VerticalLayout();
	       candidate_1.setPadding(true);
	       candidate_1.add(new Image("images/doreamon.jpeg", "Doreamon"), new Button("Vote for doreamon !", e -> {
	    	   Boolean result = Vote("doreamon");
	    	   Notification.show(result == true ? "Voted successfully" : "An error occured");
	    	   if(result == true) {
	    		  UI.getCurrent().getSession().setAttribute("logged_in", false);
	    		  UI.getCurrent().navigate("/");
	    	   }
	       }));
	       
	       VerticalLayout candidate_2 = new VerticalLayout();
	       candidate_2.setPadding(true);
	       candidate_2.add(new Image("images/pikachu.jpeg", "Pikachu"), new Button("Vote for pikachu !", e -> {
	    	   Boolean result = Vote("pikachu");
	    	   Notification.show(result == true ? "Voted successfully" : "An error occured");
	    	   if(result == true) {
	    		UI.getCurrent().getSession().setAttribute("logged_in", false);
	    		UI.getCurrent().navigate("/");
	    	   }
	       }));
	       
	       HorizontalLayout horizontal_layout = new HorizontalLayout();
	       horizontal_layout.setPadding(true);
	       horizontal_layout.add(candidate_1, candidate_2);
	        add(horizontal_layout);
			
		} else {
			
			
			
		}
		
	}
	
}
