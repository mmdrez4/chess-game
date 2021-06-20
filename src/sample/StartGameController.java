package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import sample.Model.Game;
import sample.Model.Pieces.*;
import sample.Model.User;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.Arrays;

public class StartGameController {
    private int turnLimit;
    private int undoNo;
    private Game game;
    private HBox hBox;
    private Button forfeitButton;
    private Button undoButton;
    private Button changeTurnButton;
    private Button deselect;
    private Button remainingUndoNo;
    private Label turnLabel;
    private Label errorLabel;
    private GridPane gridPane;
    private Button[][] buttons;
    private VBox gameBox;
    private boolean gameEnded;
    private Label one, two, three, four, five, six, seven, eight;
    private int[] selected = new int[2];
    private int[] moveTo = new int[2];


    public StartGameController(int turnLimit, int undoNo) {
        this.turnLimit = turnLimit;
        this.undoNo = undoNo;
        hBox = new HBox();
        forfeitButton = new Button("forfeit");
        undoButton = new Button("undo");
        changeTurnButton = new Button("next turn");
        deselect = new Button("deselect");
        remainingUndoNo = new Button("undoNo");
        turnLabel = new Label();
        gridPane = new GridPane();
        buttons = new Button[8][8];
    }

    public void initializeGame() throws IOException {

        game = new Game(MainMenuController.player1, MainMenuController.player2, turnLimit, undoNo);

        turnLabel.setTextFill(Color.GREEN);
        turnLabel.setAlignment(Pos.CENTER);

        turnLabel.setText("\n\t\t\t\t\t\t\t\t\t\tit's player " + MainMenuController.player1.getUsername() + " turn with color white");

        forfeitButton.setStyle("-fx-background-color: maroon");
        undoButton.setStyle("-fx-background-color: mediumTurquoise");
        remainingUndoNo.setStyle("-fx-background-color: GoldenRod");
        changeTurnButton.setStyle("-fx-background-color: MediumSpringGreen");
        deselect.setStyle("-fx-background-color: yellowGreen");

        forfeitButton.setPrefWidth(75);
        forfeitButton.setPrefHeight(40);
        undoButton.setPrefWidth(75);
        undoButton.setPrefHeight(40);
        deselect.setPrefWidth(75);
        deselect.setPrefHeight(40);
        changeTurnButton.setPrefWidth(75);
        changeTurnButton.setPrefHeight(40);
        remainingUndoNo.setPrefWidth(75);
        remainingUndoNo.setPrefHeight(40);

        hBox.getChildren().addAll(forfeitButton, undoButton, remainingUndoNo,changeTurnButton, deselect, turnLabel);

        HBox middleBox = new HBox();
        eight = new Label("8\t");
        seven = new Label("7\t");
        six = new Label("6\t");
        five = new Label("5\t");
        four = new Label("4\t");
        three = new Label("3\t");
        two = new Label("2\t");
        one = new Label("1\t");

        VBox numbers = new VBox();
        numbers.getChildren().addAll(eight, seven, six, five, four, three, two, one);
        initializeNumbers();

        gridPane.setAlignment(Pos.CENTER);
        middleBox.getChildren().addAll(numbers, gridPane);
        middleBox.setAlignment(Pos.CENTER);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j] = new Button();
                if ((i + j) % 2 == 0) {
                    buttons[i][j].setStyle("-fx-background-color: aqua");
                } else {
                    buttons[i][j].setStyle("-fx-background-color: white");
                }
                if (game.getBoard().getSpot(8 - i, 8 - j).getPiece() != null) {
                    buttons[i][j].setGraphic(game.getBoard().getSpot(8 - i, 8 - j).getPiece().getImageView());
                }
                buttons[i][j].setMaxSize(60, 60);
                buttons[i][j].setMinSize(60, 60);
                gridPane.add(buttons[i][j], j + 1, i + 1);
            }
        }

        Label emptyLabel1 = new Label();
        Label lettersLabel = new Label();
        lettersLabel.setTextFill(Color.WHITE);
        lettersLabel.setText("\n \tA\t\t B\t\t C\t\t  D\t\t  E\t\t   F\t\t   G\t\t   H\n");

        HBox letters = new HBox();
        letters.setAlignment(Pos.CENTER);
        letters.getChildren().add(lettersLabel);

        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setAlignment(Pos.CENTER);
        errorLabel.setTranslateX(15);

        gameBox = new VBox();

        ImageView backGround = new ImageView();
        Image image = new Image(getClass().getResourceAsStream("wood.jpg"));
        backGround.setImage(image);
        Background background = new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));
        gameBox.setBackground(background);

        gameBox.getChildren().addAll(hBox, emptyLabel1, middleBox, letters, errorLabel);

        Scene gameScene = new Scene(gameBox, 950, 600);
        Main.window.setTitle("CHESS GAME");
        Main.window.setScene(gameScene);
        Main.window.show();
        startGame();
    }

    private void initializeNumbers() {
        eight.setTranslateY(22);
        seven.setTranslateY(64);
        six.setTranslateY(107);
        five.setTranslateY(150);
        four.setTranslateY(194);
        three.setTranslateY(236);
        two.setTranslateY(281);
        one.setTranslateY(322);

        eight.setTextFill(Color.WHITE);
        seven.setTextFill(Color.WHITE);
        six.setTextFill(Color.WHITE);
        five.setTextFill(Color.WHITE);
        four.setTextFill(Color.WHITE);
        three.setTextFill(Color.WHITE);
        two.setTextFill(Color.WHITE);
        one.setTextFill(Color.WHITE);
    }

    private void startGame() throws IOException {

        if (gameEnded) {
            endGame();
        } else if (game.getLimit() == 0) {
            draw();
            endGame();
        }

        forfeitButton.setOnAction(e -> forfeitButton());
        undoButton.setOnAction(e -> {
            try {
                undoButton();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        changeTurnButton.setOnAction(e -> {
            try {
                changeTurnButton(selected);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        deselect.setOnAction(e -> {
            try {
                deselect();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        remainingUndoNo.setOnAction(e -> {
            try {
                undoNumber();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int row = 8 - i;
                int column = 8 - j;
                buttons[i][j].setOnAction(e -> buttonAction(row, column));
            }
        }

    }

    private void updateBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (game.getBoard().getSpot(8 - i, 8 - j).getPiece() != null) {
                    buttons[i][j].setGraphic(game.getBoard().getSpot(8 - i, 8 - j).getPiece().getImageView());
                } else {
                    buttons[i][j].setGraphic(null);
                }
            }
        }
    }

    private void buttonAction(int row, int column) {
        if (selected[0] == 0) {
            try {
                select(row, column);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                move(row, column);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void select(int row, int column) throws IOException {
        if (!(Piece.isInsideArea(row, column))) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("wrong coordination");
            return;
        }
        if (game.getBoard().getSpot(row, column).getPiece() != null && game.getCurrentTurn().isWhite() != game.getBoard().getSpot(row, column).getPiece().isWhite()) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("you can only select one of your pieces");
            return;
        }
        if (!(game.getBoard().isTherePiece(row, column))) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("no piece on this spot");
        } else {
            errorLabel.setTextFill(Color.GREEN);
            errorLabel.setText("selected");
            selected[0] = row;
            selected[1] = column;
            buttons[8 - row][8 - column].setStyle("-fx-background-color: SpringGreen");
            audioForSelect();
        }
        startGame();
    }

    private void audioForSelect(){
        AudioClip audio = new AudioClip(this.getClass().getResource("button.wav").toString());
        audio.play();
    }

    private void deselect() throws IOException {
        if (selected[0] == 0 && selected[1] == 0) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("no piece is selected");
        } else {
            if ((selected[0] + selected[1]) % 2 == 0) {
                buttons[8 - selected[0]][8 - selected[1]].setStyle("-fx-background-color: aqua");
            } else {
                buttons[8 - selected[0]][8 - selected[1]].setStyle("-fx-background-color: white");
            }
            Arrays.fill(selected, 0);
            errorLabel.setTextFill(Color.GREEN);
            errorLabel.setText("deselected");
        }
        startGame();
    }

    private void move(int row, int column) throws IOException {
        if (moveTo[0] != 0 && moveTo[1] != 0) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("already moved");
            return;
        }
        if (!(Piece.isInsideArea(row, column))) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("wrong coordination");
            return;
        }
        if (selected[0] == 0) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("do not have any selected piece");
            return;
        }
        if ((game.getBoard().getSpot(row, column).getPiece() != null && game.getCurrentTurn().isWhite() == game.getBoard().getSpot(row, column).getPiece().isWhite())) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("cannot move to the spot");
        } else if (!(game.getBoard().getSpot(selected[0], selected[1]).getPiece().isMovementValid(selected[0], selected[1], row, column, game.getBoard()))) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("cannot move to the spot");
        } else {
            Piece movedPiece = game.getBoard().getSpot(selected[0], selected[1]).getPiece();
            if (game.getBoard().isTherePiece(row, column)) {
                game.getBoard().killEnemy(row, column, game);
                game.setMovesPlayed(game.getCurrentTurn(), movedPiece, selected[0], selected[1], row, column, game.getBoard().getSpot(row, column).getPiece());
                updateBoard();
                errorLabel.setTextFill(Color.GREEN);
                errorLabel.setText("rival piece destroyed!");
                audioForMove();
            } else {
                game.setMovesPlayed(game.getCurrentTurn(), movedPiece, selected[0], selected[1], row, column, null);
                updateBoard();
                errorLabel.setTextFill(Color.GREEN);
                errorLabel.setText("moved");
                audioForMove();
                updateBoard();
            }
            if ((selected[0] + selected[1]) % 2 == 0) {
                buttons[8 - selected[0]][8 - selected[1]].setStyle("-fx-background-color: aqua");
            } else {
                buttons[8 - selected[0]][8 - selected[1]].setStyle("-fx-background-color: white");
            }
            moveTo[0] = row;
            moveTo[1] = column;
        }
        startGame();
    }

    private void audioForMove(){
        AudioClip audio = new AudioClip(this.getClass().getResource("move.wav").toString());
        audio.play();
    }

    public void undoNumber() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("UNDO NUMBER");
        alert.setContentText("you have " + game.getCurrentTurn().getUndoNumber() + " undo moves");
        alert.showAndWait();
        startGame();
    }

    private void draw() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Draw!");
        game.getPlayer1().addPoints(1);
        game.getPlayer2().addPoints(1);
        game.getPlayer1().addDraws();
        game.getPlayer2().addDraws();
        alert.showAndWait();
    }

    private void forfeitButton() {
        User user = game.forfeit();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(ConfirmBox.display("forfeit confirmation", "are you sure you want to forfeit?")) {
            alert.setHeaderText(User.getColor(user) + " won!");
            alert.setContentText("player " + user.getUsername() + " with color " + User.getColor(user) + " won");
            alert.showAndWait();
            try {
                endGame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void undoButton() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (game.getCurrentTurn().canUndo()) {
            if (!game.isHasMoved()) {
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("you must move before undo");
            } else {
                if (game.isHasUndoBefore()) {
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("you have used your undo for this turn");
                } else {
                    game.setHasUndoBefore(true);
                    game.undo();
                    game.setIsGameOver(false);
                    select(selected[0], selected[1]);
                    errorLabel.setText("");
                    Arrays.fill(moveTo, 0);
                    alert.setHeaderText("undo has been completed!");
                    alert.showAndWait();
                    updateBoard();
                }
            }
        } else {
            errorLabel.setText("you cannot undo anymore");
        }
        startGame();
    }

    private void changeTurnButton(int[] selected) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (!game.isHasMoved()) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("you must move then proceed to next turn");
        } else {
            game.decreaseLimit();
            game.changeTurn();
            game.setHasMoved(false);
            Arrays.fill(selected, 0);
            Arrays.fill(moveTo, 0);
            game.setHasMoved(false);
            if (game.isGameOver()) {
                gameEnded = true;
                String color = User.getColor(game.getWinner());
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText(color + " won!ُ");
                alert.setContentText("player " + game.getWinner().getUsername()
                        + " with color " + color + " won!");
                if (User.getColor(game.getPlayer1()).equals(color)) {
                    game.getPlayer1().addPoints(3);
                    game.getPlayer1().addWins();
                    game.getPlayer2().addLosses();
                } else {
                    game.getPlayer2().addPoints(3);
                    game.getPlayer2().addWins();
                    game.getPlayer1().addLosses();
                }
                alert.showAndWait();
                turnLabel.setText("");
            } else {
                showTurn();
                alert.setHeaderText("Turn changed!");
                if (game.getLimit() != 0) {
                    alert.showAndWait();
                }
            }
            errorLabel.setText("");
        }

        startGame();
    }

    public void showTurn() throws IOException {
        String username = game.getCurrentTurn().getUsername();
        String color = game.getCurrentTurn().isWhite() ? "white" : "black";
        turnLabel.setText("\n\t\t\t\t\t\t\t\t\t\tit is player " + username + " turn with color " + color);
        startGame();
    }

    private void endGame() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("game has finished!");
        alert.showAndWait();
        LoginMenuController.goToMainMenu(MainMenuController.player1);
    }

}
