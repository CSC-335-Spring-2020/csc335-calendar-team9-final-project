package view;

import javafx.application.Application;

/**
 * Does startup for the GUI based calendar app.
 * Launches in current month view by default.
 *
 */
public class Calendar {
	public static void main(String[] args) {
		Application.launch(CalendarView.class,args);
	}
}
