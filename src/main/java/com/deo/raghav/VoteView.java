/**
 * 
 */
package com.deo.raghav;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;

/**
 * @author raghav
 *
 */

@Route("vote")
public class VoteView extends VerticalLayout {
	
	Map<String, String> choices = new HashMap<>();
	int number_of_roles = 2;

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
	
// Please make a note that name must be in small characters
	
	VerticalLayout get_view(String name_1, String image_1, String name_2, String image_2, String role) {
	       
	       Text role_view = new Text(role);
	       
	       HorizontalLayout horizontal_layout = new HorizontalLayout();
	       horizontal_layout.setPadding(true);
	       horizontal_layout.add(new Image(image_1, name_1), new Image(image_2, name_2), role_view);
		
	       RadioButtonGroup<String> radio_group = new RadioButtonGroup<String>();
	       radio_group.setItems(name_1, name_2);
	       radio_group.addValueChangeListener(event -> {
	    	   choices.put(role, event.getValue());
	       });
	       
	       VerticalLayout return_view = new VerticalLayout();
	       return_view.setPadding(true);
	       return_view.add(horizontal_layout, radio_group);
	       
		return return_view;
	}
	
	public VoteView() {
		
		if (UI.getCurrent().getSession().getAttribute("logged_in") != null && (boolean)UI.getCurrent().getSession().getAttribute("logged_in") == true) {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				System.out.println("Driver not found !");
			}
			
			Button submit = new Button("submit", e -> {
				if (choices.size() == number_of_roles) {
					Boolean checker = true;
					for (String name : choices.values()) {
						Boolean result = Vote(name);
						if (result == false) {
							System.out.println("Error occured while voting for : " + name);
							checker = false;
						}
					}
					Notification.show(checker == true ? "Voted successfully" : "An error occured");
					UI.getCurrent().getSession().setAttribute("logged_in", false);
		    		UI.getCurrent().navigate("/");
					
				} else {
					Notification.show("Voting for all roles is compulsory");
				}
			});
			
	        add(get_view("doreamon", "images/doreamon.jpeg", "pikachu", "images/pikachu.jpeg", "Head boy"), get_view("sho", "images/sho.webp", "arthur", "images/arthur.webp", "Some ministry"), submit);
			
		} else {
			
			
			
		}
		
	}
	
}
