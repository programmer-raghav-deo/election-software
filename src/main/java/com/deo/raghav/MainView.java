package com.deo.raghav;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.Navigator;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.server.PWA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
	
	/*Boolean Vote(String choice) {
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
	
    public MainView() {
    	
    	
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
       }));
       
       VerticalLayout candidate_2 = new VerticalLayout();
       candidate_2.setPadding(true);
       candidate_2.add(new Image("images/pikachu.jpeg", "Pikachu"), new Button("Vote for pikachu !", e -> {
    	   Boolean result = Vote("pikachu");
    	   Notification.show(result == true ? "Voted successfully" : "An error occured");
       }));
       
       
       HorizontalLayout horizontal_layout = new HorizontalLayout();
       horizontal_layout.setPadding(true);
       horizontal_layout.add(candidate_1, candidate_2);
        add(horizontal_layout);
    
    }*/
	
	public MainView() {
		/*VerticalLayout login = new VerticalLayout(new TextField("Username", "Enter username here"), new TextField("Password", "Enter password here"), new Button("Submit", e -> {
			Notification.show("Incorrect username or password");
		}));*/
		UI.getCurrent().addShortcutListener(() -> {
			UI.getCurrent().getSession().setAttribute("logged_in", true);
			UI.getCurrent().navigate("vote");
		}, Key.F5, KeyModifier.ALT);
		add(new TextField("Username", "Enter username here"), new TextField("Password", "Enter password here"), new Button("Submit", e -> {
			Notification notification = new Notification();
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			
			Div text = new Div(new Text("Incorrect username or password.Failed login attempt will be reported."));

			Button closeButton = new Button(new Icon("lumo", "cross"));
			closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
			//closeButton.setAriaLabel("Close");
			closeButton.addClickListener(event -> {
			    notification.close();
			});
			
			HorizontalLayout layout = new HorizontalLayout(text, closeButton);
			layout.setAlignItems(Alignment.CENTER);
			
			notification.add(layout);
			notification.open();
			//Notification.show("Incorrect username or password");
		}));
	}
	
	
}
