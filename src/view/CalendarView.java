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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Day;
import model.Year;

public class CalendarView extends Application implements Observer {
	private CalendarController controller;
	private int currYear;
	
	@Override
	public void start(Stage stage) throws Exception {
		controller = new CalendarController(2020,this);
		currYear = 2020;
		MonthView months = new MonthView("May");
		months.show();
	}
	
	public class MonthView extends Stage {
		private static final int WIDTH = 7;
		private static final int HEIGHT = 6;
		private GridPane grid;
		private HBox buttonRow;
		private String month;
		public MonthView(String month) {
			this.month = month;
			BorderPane control = new BorderPane();
			control.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
			grid = new GridPane();
			buttonRow = new HBox();
			BorderPane.setMargin(grid, new Insets(8));
			BorderPane.setMargin(buttonRow, new Insets(5, 0, 3, 8));
			buildGrid();
			buildButtons();
			control.setCenter(grid);
			control.setTop(buttonRow);
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
				System.out.println((int)(event.getY()/82) * WIDTH + (int)(event.getX()/92));
			});
		}
		
		private void buildButtons() {
			ImageView plusView = new ImageView(new Image("https://i.ibb.co/Fg6jnYd/plus.png"));
			ImageView minusView = new ImageView(new Image("https://i.ibb.co/XXBNvcq/plus.png"));
			Button zoomIn = new Button();
			zoomIn.setGraphic(plusView);
			zoomIn.setTooltip(new Tooltip("Week View"));
			zoomIn.setShape(new Circle(7));
			Button zoomOut = new Button();
			zoomOut.setGraphic(minusView);
			zoomOut.setTooltip(new Tooltip("Year View"));
			zoomOut.setShape(new Circle(7));
			ComboBox<String> weeks = new ComboBox<String>();
			weeks.getItems().addAll("Month View", "Week 1", "Week 2","Week 3","Week 4","Week 5","Week 6");
			weeks.setValue("Month View");
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
			months.setValue("May");
			ComboBox<String> years = new ComboBox<String>();
			years.getItems().addAll("2019", "2020", "2021");
			years.setValue("2020");
			buttonRow.getChildren().addAll(weeks, months, years);
			buttonRow.setSpacing(8);
			
		}
	}
	
	public class WeekView extends Stage {
		private static final int WIDTH = 7;
		private GridPane grid;
		public WeekView() {
			BorderPane control = new BorderPane();
			control.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
			grid = new GridPane();
			BorderPane.setMargin(grid, new Insets(8));
			buildGrid();
			control.setCenter(grid);
			this.setTitle("Calendar");
			this.setScene(new Scene(control));
			this.show();
		}
		
		private void buildGrid() {
			grid.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
			for(int i=0;i<WIDTH;i++) {
					StackPane tempStack = new StackPane();
					tempStack.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
					Rectangle tempRect = new Rectangle(80,512,Color.LIGHTBLUE);
					tempStack.getChildren().add(tempRect);
					StackPane.setMargin(tempRect, new Insets(5));
					grid.add(tempStack, i, 0);
			}
		}
	}
	
	public class DayView extends Stage {
		
		public DayView() {
			//TODO
		}
	}
	
	public class EventBox extends Stage {
		
		public EventBox() {
			//TODO
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
