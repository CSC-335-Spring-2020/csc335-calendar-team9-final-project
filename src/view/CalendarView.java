package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
		private static final int HEIGHT = 6;
		private GridPane grid;
		public MonthView() {
			BorderPane control = new BorderPane();
			control.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
			grid = new GridPane();
			BorderPane.setMargin(grid, new Insets(8));
			this.buildGrid();
			control.setCenter(grid);
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
			grid.setOnMouseClicked((event) -> {
				System.out.println((int)(event.getY()/82) * WIDTH + (int)(event.getX()/92));
			});
		}
	}
}
