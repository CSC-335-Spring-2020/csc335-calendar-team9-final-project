package view;

import javafx.application.Application;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;

public class CalendarView extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		MonthView months = new MonthView();
		months.show();
	}
	
	public static class MonthView extends Stage {
		private static final int WIDTH = 7;
		private static final int HEIGHT = 5;
		private GridPane grid;
		private HBox buttonRow;
		public MonthView() {
			BorderPane control = new BorderPane();
			control.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
			grid = new GridPane();
			buttonRow = new HBox();
			BorderPane.setMargin(grid, new Insets(8));
			BorderPane.setMargin(buttonRow, new Insets(5, 0, 3, 8));
			this.buildGrid();
			this.buildButtons();
			control.setCenter(grid);
			control.setTop(buttonRow);
			this.setTitle("Calendar");
			this.setScene(new Scene(control));
		}
		
		private void buildGrid() {
			grid.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
			for(int i=0;i<HEIGHT;i++) {
				for(int j=0;j<WIDTH;j++) {
					StackPane tempStack = new StackPane();
					tempStack.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
					Rectangle tempRect = new Rectangle(80,70,Color.LIGHTBLUE);
					tempStack.getChildren().add(tempRect);
					StackPane.setMargin(tempRect, new Insets(5));
					grid.add(tempStack, j, i);
				}
			}
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
			months.setValue("January");
			buttonRow.getChildren().addAll(zoomIn, zoomOut, months);
			buttonRow.setSpacing(8);
			
		}
	}
}
