package view;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import controller.CalendarController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
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
	private MonthView months;
	private WeekView weeks;
	private String currMonth;
	
	@Override
	public void start(Stage stage) throws Exception {
		controller = new CalendarController(2020,this);
		currYear = 2020;
		currMonth = "May";
		months = new MonthView("May");
		months.show();
	}
	
	private void newMonth(String month) {
		if (months != null) {
			months.close();
		}
		if (weeks != null){
			weeks.close();
		}
		currMonth = month;
		months = new MonthView(month);
		months.show();
	}
	
	private void newWeek(String week) {
		if (months != null) {
			months.close();
		}
		if (weeks != null){
			weeks.close();
		}
		String[] instruction = week.split(" ");
		int num = Integer.valueOf(instruction[1]);
		weeks = new WeekView(num, currMonth);
		weeks.show();
	}
	
	
	private void showEventBox(int i) {
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
		for (int j = 1; j < 25; j++) {
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
		for (int j = 1; j < 25; j++) {
			eh.getItems().add(String.valueOf(j));
		}
		eh.setValue("1");
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
		

		
		//EventBox setup
		Stage box = new Stage();
		box.initModality(Modality.APPLICATION_MODAL);
		
		//HBox Line 4 setup
		Button ok = new Button("OK");
		Button cancel = new Button("Cancel");
		buttons.getChildren().addAll(ok, cancel);
		buttons.setPadding(new Insets(8, 8, 8, 8));
		buttons.setSpacing(8);
		ok.setOnAction(e -> box.close());
		cancel.setOnAction(e -> box.close());
		
		//Vbox setup
		vbox.getChildren().addAll(label, sTime, eTime, notes, loc, buttons);
		vbox.setPadding(new Insets(8,8,8,8));
		vbox.setSpacing(8);
		pane.setCenter(vbox);
		
		pane.setCenter(vbox);
		box.setScene(scene);
		box.setTitle("New Event");
		box.showAndWait();
		
		
		
	}
	
	
	
	private class MonthView extends Stage {
		private static final int WIDTH = 7;
		private static final int HEIGHT = 6;
		private GridPane grid;
		private HBox buttonRow;
		private HBox dayLabel;
		private String month;
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
			buttonsAndLabels.setMargin(dayLabel, new Insets(6,0,0,8));
			buttonsAndLabels.getChildren().addAll(buttonRow, dayLabel);
			control.setCenter(grid);
			control.setTop(buttonsAndLabels);
			this.setTitle("Calendar");
			this.setScene(new Scene(control));
		}
		
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
						Text dayLabel = new Text(String.valueOf(currDay));
						currDay++;
						tempStack.getChildren().add(dayLabel);
					}
					StackPane.setMargin(tempRect, new Insets(5));
					grid.add(tempStack, j, i);
				}
			}
			grid.setOnMouseClicked((event) -> {
				int date = (int)(event.getY()/82) * WIDTH + (int)event.getX()/92;
				Day day = controller.getDays(month)[date];
				if(day != null) {
					DayView dayView = new DayView(day);
					dayView.showAndWait();
				}	
			});
		}
		
		private void buildLabels() {
			Label mon = new Label("Monday");
			Label tue = new Label("Tuesday");
			Label wed = new Label("Wednesday");
			Label thu = new Label("Thursday");
			Label fri = new Label("Friday");
			Label sun = new Label("Sunday");
			Label sat = new Label("Saturday");
			dayLabel.getChildren().addAll(sun, mon, tue, wed, thu, fri, sat);
			dayLabel.setMargin(mon, new Insets(0,0,0,15));
			dayLabel.setMargin(tue, new Insets(0,0,0,12));
			dayLabel.setMargin(wed, new Insets(0,0,0,11));
			dayLabel.setMargin(sat, new Insets(0,0,0,12));
			dayLabel.setSpacing(39);
			
		}
		
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
			buttonsAndLabels.setMargin(dayLabel, new Insets(6,0,0,8));
			buttonsAndLabels.getChildren().addAll(buttonRow, dayLabel);
			control.setCenter(grid);
			control.setTop(buttonsAndLabels);
			this.setTitle("Calendar");
			this.setScene(new Scene(control));
			this.show();
		}
		
		private void buildLabels() {
			Label mon = new Label("Monday");
			Label tue = new Label("Tuesday");
			Label wed = new Label("Wednesday");
			Label thu = new Label("Thursday");
			Label fri = new Label("Friday");
			Label sun = new Label("Sunday");
			Label sat = new Label("Saturday");
			dayLabel.getChildren().addAll(sun, mon, tue, wed, thu, fri, sat);
			dayLabel.setMargin(mon, new Insets(0,0,0,15));
			dayLabel.setMargin(tue, new Insets(0,0,0,12));
			dayLabel.setMargin(wed, new Insets(0,0,0,11));
			dayLabel.setMargin(sat, new Insets(0,0,0,12));
			dayLabel.setSpacing(39);
			
		}

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
					tempStack.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
					Rectangle tempRect = new Rectangle(80,512,Color.LIGHTBLUE);
					tempStack.getChildren().add(tempRect);
					if (count > 0 && days[i] != null) {
						Text dayLabel = new Text(String.valueOf(count));
						tempStack.getChildren().add(dayLabel);
					}
					StackPane.setMargin(tempRect, new Insets(5));
					grid.add(tempStack, i, 0);
					}
			}
			grid.setOnMouseClicked((event) -> {
				int date = low + (int)event.getX()/92;
				Day day = controller.getDays(month)[date];
				if(day != null) {
					DayView dayView = new DayView(day);
					dayView.showAndWait();
				}	
			});
			}
		
		
		

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
	}
	
	
	private class DayView extends Stage {
		private Day day;
		public DayView(Day day) {
			this.day = day;
			this.initModality(Modality.APPLICATION_MODAL);
			BorderPane control = new BorderPane();
			VBox eventsVBox = new VBox();
			List<Event> eventsList = day.getEvents();
			for(Event e: eventsList) {
				Rectangle eventRect = new Rectangle(80,Math.max(20, 2 * e.getDuration()),Color.LIGHTBLUE);
				eventRect.setOnMouseClicked((event) -> {
					EventBox eventDetails = new EventBox(e);
					eventDetails.showAndWait();
				});
				eventsVBox.getChildren().add(eventRect);
			}
			control.setCenter(eventsVBox);
			this.setTitle(day.getMonth() + " " + String.valueOf(day.getDate() + 1));
			this.setScene(new Scene(control));
		}
	}
	
	private class EventBox extends Stage {

		private Event event;
		public EventBox(Event e) {
			event = e;
			this.initModality(Modality.APPLICATION_MODAL);
		}
	}
	
	/**
	 * Utility method for retrieving the node at a given location in a grid pane.
	 * @param grid The grid to locate a node within.
	 * @param row The row index of the grid to grab from.
	 * @param column The
	 * @return
	 */
	public Node getNodeByRowColumnIndex(GridPane grid,int row,int column) {
		  Node result = null;
		  List<Node> childrens = grid.getChildren();

		  for (Node node : childrens) {
		    if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
		      result = node;
		      break;
		    }
		  }

		  return result;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
