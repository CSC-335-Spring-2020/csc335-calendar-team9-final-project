package view;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import controller.CalendarController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Day;
import model.Event;

public class CalendarView extends Application implements Observer {
	private CalendarController controller;
	private int currYear;
	private Stage currView;
	private String currMonth;
	private int currDate;
	
	/**
	 * start initializes the controller to use and the necessary variables.
	 * @param stage Terrible stage that we don't want or need.
	 * Creates a controller, sets the current year to 2020, month to May.
	 * Produces a default view of the current month, May 2020.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		controller = new CalendarController(2020,this);
		currYear = 2020;
		currMonth = "May";
		currView = new MonthView("May");
		currView.show();
	}
	
	/**
	 * A method to change the month to a new month.
	 * @param month The month to display now.
	 * Updates the new month, closes any currently open calendar views, and creates a new view.
	 * The new month is shown as a MonthView.
	 */
	private void newMonth(String month) {
		if (currView != null) {
			currView.close();
		}
		currMonth = month;
		currView = new MonthView(month);
		currView.show();
	}
	
	/**
	 * Produces a new WeekView for the given week.
	 * @param week The string that contains the number of the week to display.
	 * Closes any currently open views and opens a WeekView of the given month and numbered week.
	 * Since the week is received as "Week #", a split and index is needed to get the number.
	 */
	private void newWeek(String week) {
		if (currView != null) {
			currView.close();
		}
		String[] instruction = week.split(" ");
		int num = Integer.valueOf(instruction[1]);
		currView = new WeekView(num, currMonth);
		currView.show();
	}
	
	private class MonthView extends Stage {
		private static final int WIDTH = 7;
		private static final int HEIGHT = 6;
		private GridPane grid;
		private HBox buttonRow;
		private HBox dayLabel;
		private String month;
		
		/**
		 * Creates a new view of a month, with all days shown.
		 * @param month The month to show of the current year.
		 * Produces a button selection system to change the view, and a grid of day objects.
		 * Each day can be clicked to bring up the DayView for that day
		 */
		public MonthView(String month) {
			this.month = month;
			BorderPane control = new BorderPane();
			control.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
			grid = new GridPane();
			buttonRow = new HBox();
			BorderPane.setMargin(grid, new Insets(8));
			BorderPane.setMargin(buttonRow, new Insets(5, 0, 3, 8));
			dayLabel = new HBox();
			buildGrid();
			buildButtons();
			buildLabels();
			VBox buttonsAndLabels = new VBox();
			VBox.setMargin(dayLabel, new Insets(6,0,0,8));
			buttonsAndLabels.getChildren().addAll(buttonRow, dayLabel);
			control.setCenter(grid);
			control.setTop(buttonsAndLabels);
			this.setTitle("Calendar");
			this.setScene(new Scene(control));
		}
		
		/**
		 * Produces a 7x6 grid of rectangles to represent the days of the month.
		 * Each month is labeled, with no label if the day does not fit in the month.
		 * Each day, when clicked, brings up the DayView for that day of the month.
		 */
		private void buildGrid() {
			grid.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
			Day[] days = controller.getDays(month);
			int currDay = 1;
			for(int i=0;i<HEIGHT;i++) {
				for(int j=0;j<WIDTH;j++) {
					StackPane tempStack = new StackPane();
					tempStack.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
					Rectangle tempRect = new Rectangle(80,70,Color.LIGHTBLUE);
					tempStack.getChildren().add(tempRect);
					if(days[i*WIDTH + j] != null) {
						tempRect = new Rectangle(80,70,Color.rgb(130,180,250));
						tempStack.getChildren().add(tempRect);
						int eventCount = days[i*WIDTH + j].getEvents().size();
						String eventCountString = "";
						if(eventCount == 1) {
							eventCountString = "1 Event";
						} else if(eventCount > 1) {
							eventCountString = String.format("%d Events", eventCount);
						}
						VBox dayInfo = new VBox(new Text(String.valueOf(currDay)), new Text(eventCountString));
						tempStack.getChildren().add(dayInfo);
						currDay++;
					} else {
						tempRect = new Rectangle(80,70,Color.LIGHTBLUE);
						tempStack.getChildren().add(tempRect);
					}
					grid.add(tempStack, j, i);
				}
			}
			grid.setOnMouseClicked((event) -> {
				currDate = (int)(event.getY()/72) * WIDTH + (int)event.getX()/82;
				Day day = controller.getDays(month)[currDate];
				if(day != null) {
					DayView dayView = new DayView(day);
					dayView.showAndWait();
				}	
			});
		}
		
		/**
		 * Adds the labels for each day of the week at the top of the calendar grid.
		 * Starts the week on a Sunday, ends on Saturday.
		 */
		private void buildLabels() {
			Label mon = new Label("Monday");
			Label tue = new Label("Tuesday");
			Label wed = new Label("Wednesday");
			Label thu = new Label("Thursday");
			Label fri = new Label("Friday");
			Label sun = new Label("Sunday");
			Label sat = new Label("Saturday");
			dayLabel.getChildren().addAll(sun, mon, tue, wed, thu, fri, sat);
			HBox.setMargin(mon, new Insets(0,0,0,34));
			HBox.setMargin(tue, new Insets(0,0,0,29));
			HBox.setMargin(wed, new Insets(0,0,0,26));
			HBox.setMargin(thu, new Insets(0,22,0,14));
			HBox.setMargin(sat, new Insets(0,0,0,35));
			dayLabel.setSpacing(10);
			
		}
		
		/**
		 * Method for producing the year, month, and week selectors to change the calendar view.
		 * If the week selector is changed to a numbered week, a WeekView is produced instead of a MonthView.
		 */
		private void buildButtons() {
			ComboBox<String> weeks = new ComboBox<String>();
			weeks.getItems().addAll("Month View", "Week 1", "Week 2","Week 3","Week 4","Week 5","Week 6");
			weeks.setValue("Month View");
			weeks.setOnAction(e -> newWeek(weeks.getValue()));
			ComboBox<String> months = new ComboBox<String>();
			months.getItems().addAll(
					"January", 
					"February", 
					"March",
					"April",
					"May",
					"June",
					"July",
					"August", 
					"September",
					"October", 
					"November", 
					"December"
					);
			months.setValue(currMonth);
			months.setOnAction(e -> newMonth(months.getValue()));
			
			ComboBox<String> years = new ComboBox<String>();
			years.getItems().addAll("2019", "2020", "2021");
			years.setValue(String.valueOf(currYear));
			years.setOnAction(e -> {
				currYear = Integer.valueOf(years.getValue());
				controller.changeYear(Integer.valueOf(years.getValue()));
				newMonth(months.getValue());
			});
			buttonRow.getChildren().addAll(weeks, months, years);
			buttonRow.setSpacing(8);
			
		}
	}
	
	private class WeekView extends Stage {
		private static final int WIDTH = 7;
		private int weekNum;
		private GridPane grid;
		private HBox buttonRow;
		private String month;
		private HBox dayLabel;
		
		/**
		 * Constructor for a 7 day view of the currently selected week.
		 * @param weekNum The number representing which week (1-6) of the month to display.
		 * @param currMonth The string of the current month, e.g. "May"
		 * A WeekView consists of selector buttons to change the current year, month, or week, a grid of the current days, and labels.
		 * Each day can be clicked to bring up the DayView for that specific day.
		 */
		public WeekView(int weekNum, String currMonth) {
			this.month = currMonth;
			this.weekNum = weekNum;
			BorderPane control = new BorderPane();
			control.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
			grid = new GridPane();
			buttonRow = new HBox();
			dayLabel = new HBox();
			BorderPane.setMargin(grid, new Insets(8));
			buildGrid();
			buildButtons();
			buildLabels();
			VBox buttonsAndLabels = new VBox();
			VBox.setMargin(dayLabel, new Insets(6,0,0,8));
			buttonsAndLabels.getChildren().addAll(buttonRow, dayLabel);
			control.setCenter(grid);
			control.setTop(buttonsAndLabels);
			this.setTitle("Calendar");
			this.setScene(new Scene(control));
			this.show();
		}
		
		/**
		 * Adds the labels for each day of the week at the top of the calendar grid.
		 * Starts the week on a Sunday, ends on Saturday.
		 */
		private void buildLabels() {
			Label mon = new Label("Monday");
			Label tue = new Label("Tuesday");
			Label wed = new Label("Wednesday");
			Label thu = new Label("Thursday");
			Label fri = new Label("Friday");
			Label sun = new Label("Sunday");
			Label sat = new Label("Saturday");
			dayLabel.getChildren().addAll(sun, mon, tue, wed, thu, fri, sat);
			HBox.setMargin(mon, new Insets(0,0,0,34));
			HBox.setMargin(tue, new Insets(0,0,0,29));
			HBox.setMargin(wed, new Insets(0,0,0,26));
			HBox.setMargin(thu, new Insets(0,22,0,14));
			HBox.setMargin(sat, new Insets(0,0,0,35));
			dayLabel.setSpacing(10);
		}

		/**
		 * Method to produce the 7 slot grid for a week view. Each slot gets a label of its date.
		 * When any day in the week is clicked, the specific day view is opened for that date.
		 */
		private void buildGrid() {
			grid.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
			int count = 0;
			Day[] days = controller.getDays(month);
			int low = (weekNum - 1) * WIDTH;
			int high = (weekNum * WIDTH);
			for(int i=0;i<days.length;i++) {
				if (days[i] != null) {
					count++;
				}
				if (i >= low && i < high) {
					StackPane tempStack = new StackPane();
					tempStack.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
					Rectangle tempRect;
					if (count > 0 && days[i] != null) {
						tempRect = new Rectangle(80,512,Color.rgb(130,180,250));
						tempStack.getChildren().add(tempRect);
						int eventCount = days[i].getEvents().size();
						String eventCountString = "";
						if(eventCount == 1) {
							eventCountString = "1 Event";
						} else if(eventCount > 1) {
							eventCountString = String.format("%d Events", eventCount);
						}
						VBox dayInfo = new VBox(new Text(String.valueOf(i)), new Text(eventCountString));
						tempStack.getChildren().add(dayInfo);
					} else {
						tempRect = new Rectangle(80,512,Color.LIGHTBLUE);
						tempStack.getChildren().add(tempRect);
					}
					grid.add(tempStack, i, 0);
					}
			}
			grid.setOnMouseClicked((event) -> {
				currDate = low + (int)event.getX()/92;
				Day day = controller.getDays(month)[currDate];
				if(day != null) {
					DayView dayView = new DayView(day);
					dayView.show();
				}	
			});
			}
		
		
		
		/**
		 * Method for producing the year, month, and week selectors to change the calendar view.
		 * If the week selector is changed to month view, it changes back to a full month view instead of a 7 day view.
		 */
		private void buildButtons() {
			ComboBox<String> weeks = new ComboBox<String>();
			weeks.getItems().addAll("Month View", "Week 1", "Week 2","Week 3","Week 4","Week 5","Week 6");
			weeks.setValue("Week " + weekNum);
			
			ComboBox<String> months = new ComboBox<String>();
			months.getItems().addAll(
					"January", 
					"February", 
					"March",
					"April",
					"May",
					"June",
					"July",
					"August", 
					"September",
					"October", 
					"November", 
					"December"
					);
			months.setValue(currMonth);
			months.setOnAction(e -> newMonth(months.getValue()));
			
			ComboBox<String> years = new ComboBox<String>();
			years.getItems().addAll("2019", "2020", "2021");
			years.setValue(String.valueOf(currYear));
			years.setOnAction(e -> {
				currYear = Integer.valueOf(years.getValue());
				controller.changeYear(Integer.valueOf(years.getValue()));
				newMonth(months.getValue());
			});
			weeks.setOnAction(e -> {
				if (weeks.getValue().equals("Month View")) {
					newMonth(months.getValue());
				}
				else {
					newWeek(weeks.getValue());
				}
			});
			buttonRow.getChildren().addAll(weeks, months, years);
			buttonRow.setSpacing(8);
			
		}
		
		/**
		 * Simple getter method to retrieve the current week number.
		 * @return The number of the week, from 1-6 through the month.
		 * This is used to help refresh the view when an event is added or removed.
		 */
		public int getWeek() {
			return this.weekNum;
		}
	}
	
	
	private class DayView extends Stage {
		
		/**
		 * Produces a GUI to look at a specific day.
		 * @param day The Day object to produce an interactive view for.
		 * This builds a list of the events present in the given day. Each slot scales to the length of the event.
		 * There is also an option to add a new event to the day.
		 */
		public DayView(Day day) {
			this.initModality(Modality.APPLICATION_MODAL);
			BorderPane control = new BorderPane();
			Button addEvent = new Button("Add Event");
			addEvent.setOnAction((event) -> {
				addEventBox add = new addEventBox(day);
				add.showAndWait();
				if(add.changed) {
					controller.save();
					this.close();
				}
			});
			VBox eventsVBox = new VBox(addEvent);
			VBox.setMargin(addEvent, new Insets(10));
			List<Event> eventsList = day.getEvents();
			for(Event e: eventsList) {
				StackPane tempStack = new StackPane();
				Rectangle eventRect = new Rectangle(300,Math.max(20,e.getDuration()),decode(e.getColor()));
				tempStack.getChildren().add(eventRect);
				tempStack.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
				tempStack.getChildren().add(new Text(e.getLabel()));
				tempStack.setOnMouseClicked((event) -> {
					EventBox eventDetails = new EventBox(e);
					eventDetails.showAndWait();
					if(eventDetails.removed) {
						controller.removeEvent(day, e);
						controller.save();
						this.close();
					}
				});
				Label time = new Label(String.format("%02d:%02d", e.getSH(),e.getSM()));
				HBox tempBox = new HBox(time,tempStack);
				tempBox.setSpacing(10);
				eventsVBox.getChildren().add(tempBox);
			}
			if(eventsList.isEmpty()) {
				Label noEvents = new Label("There are currently no events to display.");
				noEvents.setPadding(new Insets(10));
				eventsVBox.getChildren().add(noEvents);
			}
			eventsVBox.setPrefWidth(340);
			eventsVBox.setMinHeight(500);
			control.setCenter(eventsVBox);
			this.setTitle(day.getMonth() + " " + String.valueOf(day.getDate() + 1));
			this.setScene(new Scene(control));
		}
		
		/**
		 * A method for converting a color string to an actual JavaFX Color.
		 * @param color The string of color to convert.
		 * @return The JavaFX Color of the corresponding string.
		 * A wrapper for a switch statement, converts the string "Red" to Color.RED for example.
		 * If somehow the color string given is not one of the valid colors, it defaults to our favorite Color.LIGHTBLUE.
		 */
		private Color decode(String color) {
			switch(color) {
			case "Red":
				return Color.RED;
			case "Orange":
				return Color.ORANGE;
			case "Yellow":
				return Color.YELLOW;
			case "Green":
				return Color.GREEN;
			case "Blue":
				return Color.BLUE;
			case "Purple":
				return Color.PURPLE;
			default:
				return Color.LIGHTBLUE;
			}
		}
	}
	
	private class EventBox extends Stage {
		public boolean removed;
		/**
		 * This window provides a basic overview of the details of an event, and the ability to remove it.
		 * @param e The event object to provide information for.
		 * Mostly a way to display text, this window displays the information associated with an event.
		 * There is also a button that removes the event from the calendar. 
		 */
		public EventBox(Event e) {
			this.removed = false;
			BorderPane control = new BorderPane();
			control.setPrefHeight(200);
			control.setPrefWidth(300);
			Label titleInfo = new Label("Event Title:");
			Label title = new Label(e.getLabel());
			Label startInfo = new Label("Starts at:");
			Label start = new Label(String.format("%02d:%02d", e.getSH(),e.getSM()));
			Label endInfo = new Label("Ends at:");
			Label end = new Label(String.format("%02d:%02d", e.getEH(),e.getEM()));
			Label locInfo = new Label("Location:");
			Label loc = new Label(e.getLoc());
			Label notesInfo = new Label("Notes:");
			Label notes = new Label(e.getNotes());
			Button removeButton = new Button("Remove Event");
			titleInfo.setPadding(new Insets(5));
			title.setPadding(new Insets(5));
			startInfo.setPadding(new Insets(5));
			start.setPadding(new Insets(5));
			endInfo.setPadding(new Insets(5));
			end.setPadding(new Insets(5));
			locInfo.setPadding(new Insets(5));
			loc.setPadding(new Insets(5));
			notesInfo.setPadding(new Insets(5));
			notes.setPadding(new Insets(5));
			removeButton.setOnAction((event) -> {
				this.removed = true;
				this.close();
			});
			VBox details = new VBox(titleInfo,title,startInfo,start,endInfo,end,locInfo,loc,notesInfo,notes);
			control.setCenter(details);
			control.setBottom(removeButton);
			BorderPane.setMargin(removeButton, new Insets(10));
			this.setScene(new Scene(control));
		}
	}
	
	private class addEventBox extends Stage {
		public boolean changed;
		
		/**
		 * This window allows the user to add a new event to the given day.
		 * @param day The Day object to add a new event to.
		 * A new event consists of a title, start and end times, location, and notes.
		 * These parameters are collected from the user using various input features like text fields
		 * and sent to the controller to produce a new event and store it in the model.
		 */
		public addEventBox(Day day) {
			changed = false;
			this.initModality(Modality.APPLICATION_MODAL);
			BorderPane pane = new BorderPane();
			Scene scene = new Scene(pane);
			
			VBox vbox = new VBox();
			HBox label = new HBox();
			HBox sTime = new HBox();
			HBox eTime = new HBox();
			HBox notes = new HBox();
			HBox loc = new HBox();
			HBox buttons = new HBox();
			
			//Label line setup
			Label title = new Label("Event Title: ");
			TextField tField = new TextField();
			tField.setPrefWidth(150);
			label.getChildren().addAll(title, tField);
			label.setSpacing(8);
			
			//Start Time setup
			ComboBox<String> sh = new ComboBox<String>();
			for (int j = 0; j < 24; j++) {
				sh.getItems().add(String.valueOf(j));
			}
			sh.setValue("12");
			ComboBox<String> sm = new ComboBox<String>();
			for (int f = 0; f < 60; f++) {
				sm.getItems().add(String.format("%02d" , f));
			}
			sm.setValue("00");
			Label shl = new Label("Start Time: ");
			sTime.getChildren().addAll(shl, sh, sm );
			sTime.setSpacing(8);
			
			
			//End Time setup
			ComboBox<String> eh = new ComboBox<String>();
			for (int j = 0; j < 24; j++) {
				eh.getItems().add(String.valueOf(j));
			}
			eh.setValue("13");
			ComboBox<String> em = new ComboBox<String>();
			for (int f = 0; f < 60; f++) {
				em.getItems().add(String.format("%02d" , f));
			}
			em.setValue("00");
			Label ehl = new Label("End Time:  ");
			eTime.getChildren().addAll(ehl, eh, em);
			eTime.setSpacing(8);
			
			
			//Notes setup
			Label noteLabel = new Label("Notes: ");
			TextArea noteField = new TextArea();
			noteField.setPrefHeight(200);
			noteField.setPrefWidth(200);
			notes.getChildren().addAll(noteLabel, noteField);
			
			//Location setup
			Label locTitle = new Label("Location: ");
			TextField locField = new TextField();
			locField.setPrefWidth(150);
			loc.setSpacing(8);
			loc.getChildren().addAll(locTitle, locField);
			
			//Color setup
			
			ComboBox<String> colors = new ComboBox<String>();
			colors.getItems().addAll("Red","Orange","Yellow","Green","Blue","Purple");
			colors.setValue("Blue");
			VBox.setMargin(colors, new Insets(8));
			
			//HBox Line 4 setup
			Button ok = new Button("OK");
			Button cancel = new Button("Cancel");
			buttons.getChildren().addAll(ok, cancel);
			buttons.setPadding(new Insets(8, 8, 8, 8));
			buttons.setSpacing(8);
			ok.setOnAction((e) -> {
				if(!controller.addEvent(day, tField.getText(), Integer.valueOf(sh.getValue()), Integer.valueOf(sm.getValue()),
															Integer.valueOf(eh.getValue()), Integer.valueOf(em.getValue()), noteField.getText(), locField.getText(), colors.getValue())) {
					Alert invalid = new Alert(AlertType.ERROR);
					invalid.setTitle("Invalid event");
					invalid.setContentText("That is not a valid event. Please make sure you have a title and a positive duration.");
					invalid.showAndWait();
				}
				changed = true;
				this.close();
			});
			cancel.setOnAction((e) -> {
				this.close();
			});
			
			//Vbox setup
			vbox.getChildren().addAll(label, sTime, eTime, notes, loc, colors, buttons);
			vbox.setPadding(new Insets(8,8,8,8));
			vbox.setSpacing(8);
			pane.setCenter(vbox);
			
			pane.setCenter(vbox);
			this.setScene(scene);
			this.setTitle("New Event");
		}
	}

	/**
	 * This method redraws the given view when an update is detected in the model.
	 * When an event is added or removed from the calendar, the currently viewed day is refreshed
	 * to reflect the new event list. This is done by producing a new DayView of the most recently viewed day.
	 * It also rebuilds the open calendar to reflect the change in event counts for the given day.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		Day day = controller.getDays(currMonth)[currDate];
		if(currView instanceof MonthView) {
			currView.close();
			currView = new MonthView(currMonth);
			currView.show();
		} else if(currView instanceof WeekView) {
			currView.close();
			currView = new WeekView(((WeekView) currView).getWeek(),currMonth);
		}
		if(day != null) {
			DayView dayView = new DayView(day);
			dayView.show();
		}
	}
}
