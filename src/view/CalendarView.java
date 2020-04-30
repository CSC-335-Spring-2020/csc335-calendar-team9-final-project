package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalendarView extends Application {

	GridPane gridPane;
	@Override
	public void start(Stage view) throws Exception {
		BorderPane pane = new BorderPane();
		
		//top menu bar setup
		HBox topBar = new HBox();
		topBar.setFillHeight(false);
		topBar.setPrefHeight(25);
		Button plus = new Button();
		topBar.getChildren().addAll(plus);
		
		//GridPane setup
		VBox[][] dayGrid = new VBox[7][6];
		gridPane = new GridPane();
		gridPane.setGridLinesVisible(true);
		for (Integer col = 0; col < 7; col++) {
			for (Integer row = 0; row < 6; row++) {
				VBox temp = new VBox();
				GridPane.setMargin(temp, new Insets(20,20,20,20));
				temp.getChildren().add(new Label(col.toString() + row.toString()));
				gridPane.add(temp, col, row);
				dayGrid[col][row] = temp;
				
			}
		}
		
		
		
		
		//Pane setup
		pane.setTop(topBar);
		pane.setCenter(gridPane);
		Scene start = new Scene(pane);
		view.setScene(start);
		view.setTitle("Calendar");
		view.show();
		
		
		
	}
	private class MonthView extends javafx.stage.Stage {
		
		
	}
}



