package voteCalculations;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
	public static Manager manage = new Manager();
	public static int i = 0, voters, pick = 0, n = 1;
	static boolean done = false;
	private static ArrayList<Candidate> list = new ArrayList<Candidate>();
	private static ObservableList<Candidate> candidates = FXCollections.observableArrayList();
	private static ArrayList<Integer> values = new ArrayList<>();
	private Stage window;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * The main voting Screen. this contains a dynamic number of candidate
	 * buttons, which then using lambda's I create actions for each button which
	 * registers the correct voting order for each voter. Upon clicking all the
	 * candidates it moves onto the next voter and closes the existing window.
	 * Once all votes have been collected the "finish election" button will be
	 * clicked, which will collect the results.
	 */
	public static void vote() {
		Stage window = new Stage();
		window.setMaximized(true);
		VBox boothLayout = new VBox(10), confirmLayout = new VBox(10);
		Scene booth = new Scene(boothLayout, 250, 250), confirm = new Scene(confirmLayout, 100, 100);
		int[] votes = new int[list.size()];
		Label votesRemaining = new Label("Voter Number:" + "\n" + n);

		Button finish = new Button("Finish Election"), confirmFinish = new Button("Confirm End"),
				back = new Button("Back to voting"), undo = new Button("Undo");
		finish.setMinSize(200, 50);
		undo.setVisible(false);
		finish.setOnAction(e -> {
			window.setScene(confirm);
			window.setMaximized(true);
		});
		confirmFinish.setOnAction(e -> {
			window.setMaximized(true);
			done = true;
			window.close();
		});
		back.setOnAction(e -> {
			window.setMaximized(true);
			window.setScene(booth);
			window.setMaximized(true);
		});

		InterfaceAid.windowBasic(window, "Voting Booth", 700, booth);
		InterfaceAid.windowBasic(window, "Voting Booth", 700, confirm);

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
					options.getChildren().get(hashValue(e.getSource().hashCode())).setVisible(false);
					pick++;
					undo.setVisible(true);
					votesRemaining.setText("Voter Number:" + "\n" + n);
					if (pick == list.size()) {
						System.out.println("Adding votes");
						i = 0;
						pick = 0;
						n++;
						values.clear();
						manage.addVoter(votes);
						System.out.println("Added votes");
						window.close();
					}
				}
				undo.setOnAction(r -> {
					options.getChildren().get(hashValue(e.getSource().hashCode())).setVisible(true);
					boolean clicked = false;
					if (pick > 0 && !clicked) {
						pick--;
						clicked = true;
						undo.setVisible(false);
					}
				});
			});
		}

		options.setPadding(new Insets(10, 10, 10, 10));
		options.setSpacing(10);
		options.setAlignment(Pos.CENTER);

		boothLayout.getChildren().addAll(votesRemaining, options, undo, finish);
		boothLayout.setAlignment(Pos.CENTER);
		boothLayout.setSpacing(50.0);

		confirmLayout.getChildren().addAll(back, confirmFinish);
		confirmLayout.setAlignment(Pos.CENTER);
		confirmLayout.setSpacing(50.0);

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

	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setMaximized(true);
		window.setTitle("Fair Voting System");
		TableView<Candidate> table;
		Label winner = new Label("");
		winner.setFont(new Font("Cambria", 40));

		Button start = new Button("Start Election");
		start.setMinSize(250, 75);
		TextField candidate = new TextField();
		Button addCandidate = new Button("Add Candidate");

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

		start.setOnAction(e -> {
			winner.setText("");
			manage.setCandidates(list);
			while (done != true) {
				vote();
			}
			winner.setText("The winner is " + manage.calculateWinner().getName());
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(winner, table, candidate, addCandidate, start);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 300, 250);
		scene.getStylesheets().add("style.css");
		window.setScene(scene);
		window.show();
	}
}
