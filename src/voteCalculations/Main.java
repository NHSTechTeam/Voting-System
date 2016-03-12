package voteCalculations;

import javafx.stage.*;
import java.util.ArrayList;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class Main extends Application {
	private static ArrayList<Candidate> list = new ArrayList<Candidate>();
	public static Manager manage = new Manager();
	private static ObservableList<Candidate> candidates = FXCollections.observableArrayList();
	private Stage window;
	private static int i = 0;
	public static int voters;
	private static int pick = 0;
	private static ArrayList<Integer> values = new ArrayList<>();

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setMaximized(true);
		window.setTitle("Fair Voting System");
		TableView<Candidate> table;
		Label winner = new Label("");

		Button start = new Button("Start Election");
		TextField candidate = new TextField();
		Button addCandidate = new Button("Add Candidate");
		TextField numVoters = new TextField();
		Button addVoters = new Button("Set Number of Voters");

		TableColumn<Candidate, String> candidates = new TableColumn<>("Candidates");
		candidates.setMinWidth(150);
		candidates.setCellValueFactory(new PropertyValueFactory<>("name"));

		table = new TableView<>();
		table.setMaxWidth(155);
		table.setMaxHeight(155);
		table.getColumns().addAll(candidates);

		addCandidate.setOnAction(e -> {
			table.setItems(getCandidates(candidate.getText()));
			candidate.clear();
		});
		
		addVoters.setOnAction(e -> {
			voters = Integer.parseInt(numVoters.getText());
		});

		start.setOnAction(e -> {
			winner.setText("");
			manage.setCandidates(list);
			for(int i = 0; i < voters; i++)
			{
			vote();
			}
			winner.setText("The winner is " + manage.calculateWinner().getName());
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(winner, table, candidate, addCandidate, numVoters, addVoters, start);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 300, 250);
		scene.getStylesheets().add("style.css");
		window.setScene(scene);
		window.show();
	}
	
	public static void vote() {
		Stage window = new Stage();
		VBox boothLayout = new VBox(10);
		Scene booth = new Scene(boothLayout, 250, 250);
		int[] votes = new int[list.size()];
		Label votesRemaining = new Label("" + ("Votes Remaining:" + "\n" + (list.size() - pick)));

		UIManager.windowBasic(window, "Voting Booth", 700, booth);

		HBox options = new HBox();

		i = 0;
		pick = 0;
		values.clear();
		
		for (int i = 0; i < list.size(); i++) {
			options.getChildren().add(new Button(list.get(i).getName()));
			values.add(options.getChildren().get(i).hashCode());
		}

		for (i = 0; i < list.size(); i++) {
			((Button) options.getChildren().get(i)).setOnAction(e -> {
				if (pick < list.size()) {
					votes[pick] = hashValue(e.getSource().hashCode());
					pick++;
					votesRemaining.setText("Votes Remaining:" + "\n" + (list.size() - pick));
					if (pick == list.size()) {
						System.out.println("Adding votes");
						i = 0;
						pick = 0;
						values.clear();
						manage.addVoter(votes);
						System.out.println("Added votes");
						window.close();
					}
				}
			});
		}

		options.setPadding(new Insets(10, 10, 10, 10));
		options.setSpacing(10);
		options.setAlignment(Pos.CENTER);

		boothLayout.getChildren().addAll(votesRemaining, options);
		boothLayout.setAlignment(Pos.CENTER);

		// Display window and wait for it to be closed before returning
		window.setScene(booth);
		window.showAndWait();
	}

	public static int hashValue(int hash) {
		int output = 0;
		for (int i = 0; i < values.size(); i++) {
			if (hash == values.get(i)) {
				output = i;
			}
		}
		return output;
	}
	
	public static ObservableList<Candidate> getCandidates(String name) {
		if (!(name.equals(""))) {
			list.add(new Candidate(name));
			candidates.add(new Candidate(name));
		}
		return candidates;
	}
}
