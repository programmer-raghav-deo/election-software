package com.deo.raghav;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;


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

    private static final long serialVersionUID = 1L;

	/**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
	
	public MainView() {
		UI.getCurrent().addShortcutListener(() -> {
			UI.getCurrent().getSession().setAttribute("logged_in", true);
			UI.getCurrent().navigate("vote");
		}, Key.F5, KeyModifier.ALT);
		Span election_span = new Span("D.A.V. Public School Aundh Pune\nElection Software 2024\n© Raghav Deo\nProject by : Raghav Deo,Ashutosh Saxena,Aditi Baghel");
		election_span.getStyle().set("white-space", "pre-line");
		election_span.getStyle().set("font-size", "32px");
		add(election_span, new TextField("Username", "Enter username here"), new TextField("Password", "Enter password here"), new Button("Submit", e -> {
			Notification notification = new Notification();
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			
			Div text = new Div(new Text("Incorrect username or password.Failed login attempt will be reported."));

			@SuppressWarnings("deprecation")
			Button closeButton = new Button(new Icon("lumo", "cross"));
			closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
			closeButton.addClickListener(event -> {
			    notification.close();
			});
			
			HorizontalLayout layout = new HorizontalLayout(text, closeButton);
			layout.setAlignItems(Alignment.CENTER);
			
			notification.add(layout);
			notification.open();
		}));
	}
	
	
}
