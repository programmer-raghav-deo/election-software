/**
 * 
 */
package com.deo.raghav;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Span;

/**
 * @author raghav
 *
 */

@Route("vote")
public class VoteView extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;
	Map<String, String> choices = new HashMap<>();
	Map<String, String> ip_machine_code_map = new HashMap<String, String>(){private static final long serialVersionUID = 1L;
	{
		put("169.254.99.225","Machine 26");
		put("192.168.1.53","Machine 25");
		put("192.168.1.102","Machine 28");
		put("192.168.1.104","Machine 23");
		put("192.168.1.107","Machine 19");
		put("192.168.1.51","Machine 27");
		put("192.168.1.105","Machine 21");
		put("192.168.1.106","Machine 24");
		put("192.168.1.103","Machine 22");
		put("192.168.1.108","Machine 18");
		put("192.168.1.109","Machine 35");
		put("192.168.1.23","Machine 33");
		put("192.168.1.26","Machine 29");
	}};
	int number_of_roles = 4;

	Boolean Vote(String choice) {
		try {
			Connection connection = Database_Details.hikari_data_source.getConnection();
			
			PreparedStatement select_prepared_statement = connection.prepareStatement("SELECT votes FROM cartoon WHERE name = ?");
			select_prepared_statement.setString(1, choice);
			ResultSet result_set = select_prepared_statement.executeQuery();
			
			Integer temp = 0;
			while (result_set.next()) {
				temp = result_set.getInt("votes");
			}
			result_set.close();
			select_prepared_statement.close();
			
			PreparedStatement update_prepared_statement = connection.prepareStatement("UPDATE cartoon SET votes = ? WHERE name = ?");
			update_prepared_statement.setString(1, Integer.toString(temp + 1));
			update_prepared_statement.setString(2, choice);
			update_prepared_statement.executeUpdate();
			update_prepared_statement.close();
			
			connection.commit();
			connection.close();
			return true;
		} catch (SQLException e1) {
			System.out.println("Voting failed for : " + choice);
			e1.printStackTrace();
			return false;
		}
	}
	
// Please make a note that name must be in small characters
	
	
	RadioButtonGroup<Candidate> get_view(String name_1, String image_1, String name_2, String image_2, String name_3, String image_3, String name_4, String image_4, String name_5, String image_5, String role) {
		RadioButtonGroup<Candidate> radio_group = new RadioButtonGroup<Candidate>();
		radio_group.setItems(new Candidate(image_1, name_1), new Candidate(image_2, name_2), new Candidate(image_3, name_3), new Candidate(image_4, name_4), new Candidate(image_5, name_5));
		radio_group.setRenderer(new ComponentRenderer<>(candidate -> {
			Span name_span = new Span(candidate.get_name());
			name_span.getStyle().set("font-size", "20px");
			return new VerticalLayout(name_span, new Image(candidate.get_image_path(), candidate.get_name()));
		}));
		radio_group.addValueChangeListener(event -> {
			choices.put(role, event.getValue().get_name());
		});
		return radio_group;
	}
	
	Span get_role_view(String role) {
		
		Span role_view= new Span(role);
		role_view.getStyle().set("font-size", "26px");
		
		return role_view;
		
	}
	
	public VoteView() {
		
		if (UI.getCurrent().getSession().getAttribute("logged_in") != null && (boolean)UI.getCurrent().getSession().getAttribute("logged_in") == true) {
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				System.out.println("Driver not found !");
			}
			
			StringBuilder string_builder = new StringBuilder();
			if (ip_machine_code_map.get(UI.getCurrent().getSession().getBrowser().getAddress()) != null) {
				string_builder.append(ip_machine_code_map.get(UI.getCurrent().getSession().getBrowser().getAddress()));
			} else {
				string_builder.append(UI.getCurrent().getSession().getBrowser().getAddress());
			}
			
			
			Button submit = new Button("submit", e -> {
				if (choices.size() == number_of_roles) {
					Boolean checker = true;
					for (String name : choices.values()) {
						Boolean result = Vote(name);
						if (result == false) {
							checker = false;
						} else {
							string_builder.append(" " + name);
						}
					}
					Notification.show(checker == true ? "Voted successfully" : "An error occured");
					final Boolean temp_bool = checker;
						if (temp_bool == true) {
							try {
								Socket_Connection_Handler.send_message(string_builder.toString());
							} catch (NullPointerException e_2) {
								// TODO: handle exception
								System.out.println("WebSocket client not connected");
							}
						}
					UI.getCurrent().getSession().setAttribute("logged_in", false);
		    		UI.getCurrent().navigate("/");
					
				} else {
					Notification.show("Voting for all roles is compulsory");
				}
			});
			
			Span election_span = new Span("D.A.V. Public School Aundh Pune\nElection Software 2024\n© Raghav Deo\nProject by : Raghav Deo,Ashutosh Saxena,Aditi Baghel");
			election_span.getStyle().set("white-space", "pre-line");
			election_span.getStyle().set("font-size", "32px");
			
			add(election_span, get_role_view("School Captain Boy"), get_view("doreamon", "images/doreamon.jpeg", "pikachu", "images/pikachu.jpeg", "bill", "images/bill.png", "sho", "images/sho.png", "arthur", "images/arthur.png", "School Captain Boy"), get_role_view("School Captain Girl"), get_view("gwen","images/gwen.png", "blossom", "images/blossom.png", "shizuka", "images/shizuka.png", "misty", "images/misty.png", "buttercup", "images/buttercup.png", "School Captain Girl"), get_role_view("School Vice Captain Boy"), get_view("tyson", "images/tyson.png", "steve", "images/steve.png", "goku", "images/goku.png", "vegeta", "images/vegeta.png", "thor", "images/thor.png", "School Vice Captain Boy"), get_role_view("School Vice Captain Girl"), get_view("starfire", "images/starfire.png", "margo", "images/margo.png", "ladybug", "images/ladybug.png", "bubbles", "images/bubbles.png", "dora", "images/dora.png", "School Vice Captain Girl"), submit);
		} else {
			
			
		}
		
	}
	
}