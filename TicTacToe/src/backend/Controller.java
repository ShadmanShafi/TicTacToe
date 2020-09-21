package backend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Controller {

    boolean isGameWon = false;
    boolean isGameDrawn = false;
    int tilesFilled = 0;
    int yPosition;
    int xPosition;
    int[] emptyPostionSelectedbyRandomAI = new int[3];
    int[][] checkWin = new int[3][3];
    Button[][] buttons = new Button[3][3];
    String currentTheme = "classic";
    String currentAI ="random";

    @FXML
    private AnchorPane AnchorPaneRight;
    @FXML
    private GridPane GridPane01;
    @FXML
    private ToggleGroup themeSelection;
    @FXML
    private Button button_00, button_10, button_20,
            button_01, button_11, button_21,
            button_02, button_12, button_22,
            startWithDefensiveAI, startWithRandomAI;

    @FXML
    void startWithDefensiveAIClicked(ActionEvent event) {
        initializeButton();
        currentAI = "defensive";
        startNewGame();
        System.out.println("Game Started with PRO Level Difficulty . . .");
    }
    @FXML
    void startWithRandomAIClicked(ActionEvent event) {
        initializeButton();
        currentAI = "random";
        startNewGame();
        System.out.println("Game Started with NOOB Level Difficulty . . .");
    }

    @FXML
    void tileButtonPressed(ActionEvent event) {
        button_00.setOnAction(e -> { makeMove(0, 0); });
        button_10.setOnAction(e -> { makeMove(1, 0); });
        button_20.setOnAction(e -> { makeMove(2, 0); });
        button_01.setOnAction(e -> { makeMove(0, 1); });
        button_11.setOnAction(e -> { makeMove(1, 1); });
        button_21.setOnAction(e -> { makeMove(2, 1); });
        button_02.setOnAction(e -> { makeMove(0, 2); });
        button_12.setOnAction(e -> { makeMove(1, 2); });
        button_22.setOnAction(e -> { makeMove(2, 2); });
    }

    public void makeMove(int y, int x) {
        initializeButton();
        placeX(y, x);
    }

    public void initializeButton() {
        buttons[0][0] = button_00; buttons[1][0] = button_10;
        buttons[2][0] = button_20; buttons[0][1] = button_01;
        buttons[1][1] = button_11; buttons[2][1] = button_21;
        buttons[0][2] = button_02; buttons[1][2] = button_12;
        buttons[2][2] = button_22;
    }

    public void placeX(int y, int x) {
        if (currentTheme == "classic") { playerMoveForClassicTheme(y, x); }
        else if (currentTheme == "forrest") { playerMoveForForrestTheme(y, x); }
        else if (currentTheme == "highContrast") { playerMoveForHighContrastTheme(y, x); }
    }

    public void playerMoveForClassicTheme(int y, int x) {
        buttons[y][x].setText("X");
        System.out.println("Draw X on cell " + y + x);
        buttons[y][x].setMouseTransparent(true);
        prepareNextMove(y, x);
    }
    public void playerMoveForForrestTheme(int y, int x) {
        buttons[y][x].setText("\uD83C\uDF3C");
        System.out.println("Draw X on cell " + y + x);
        buttons[y][x].setMouseTransparent(true);
        prepareNextMove(y, x);
    }
    public void playerMoveForHighContrastTheme(int y, int x) {
        buttons[y][x].setStyle("-fx-opacity: 1.0;-fx-background-color: black;");
        buttons[y][x].setText("P");
        System.out.println("Draw X on cell " + y + x);
        buttons[y][x].setMouseTransparent(true);
        prepareNextMove(y, x);
    }

    public void prepareNextMove(int y, int x) {
        tilesFilled++;
        checkWin[y][x] = 1; // 1 for Player Occupied Cell.
        System.out.println("Count: " + tilesFilled);
        checkGameStatus();
        if (!isGameWon && !isGameDrawn) { callAI(); }
        else disableTileButtons();
    }

    public void callAI() {
        int y = (int) (Math.random() * ((3 - 1) + 1));
        int x = (int) (Math.random() * ((3 - 1) + 1));
        checkIfCellAlreadyOccupied(y, x);

        if (currentAI=="random")
            placeO(emptyPostionSelectedbyRandomAI);
        else {
            checkDefensiveAICondition();
            placeO(emptyPostionSelectedbyRandomAI);
        }
    }

    public void checkIfCellAlreadyOccupied(int y, int x) {
        if (checkWin[y][x] == 0) {
            System.out.println("Empty Cell Selected by AI.");
            emptyPostionSelectedbyRandomAI[0] = y;
            emptyPostionSelectedbyRandomAI[1] = x;
        }
        else {
            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    if (checkWin[j][i] == 0) {
                        emptyPostionSelectedbyRandomAI[0] = j;
                        emptyPostionSelectedbyRandomAI[1] = i;
                        break;
                    } } } } }

    public void checkDefensiveAICondition() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                // if (checkWin[j][0]==1)
            }
        }
    }

    public void placeO(int[] arr) {
        yPosition = arr[0];
        xPosition = arr[1];
        if (currentTheme == "classic") { AIMoveForClassicTheme(yPosition, xPosition); }
        else if (currentTheme == "forrest") { AIMoveForForrestTheme(yPosition, xPosition); }
        else if (currentTheme == "highContrast") { AIMoveForHighContrastTheme(yPosition, xPosition); }
    }
    public void AIMoveForClassicTheme(int y, int x) {
        buttons[y][x].setText("O");
        System.out.println("Draw O on cell " + y + x);
        buttons[y][x].setMouseTransparent(true);
        finishAImove(y, x);
    }
    public void AIMoveForForrestTheme(int y, int x) {
        buttons[y][x].setText("\uD83C\uDF44");
        System.out.println("Draw O on cell " + y + x);
        buttons[y][x].setMouseTransparent(true);
        finishAImove(y, x);
    }
    public void AIMoveForHighContrastTheme(int y, int x) {
        buttons[y][x].setStyle("-fx-opacity: 1.0;-fx-background-color: white;");
        buttons[y][x].setText("AI");
        System.out.println("Draw O on cell " + y + x);
        buttons[y][x].setMouseTransparent(true);
        finishAImove(y, x);
    }

    public void finishAImove(int y, int x) {
        tilesFilled++;
        checkWin[y][x] = 2; // 2 for AI Occupied Cell.
        System.out.println("Count: " + tilesFilled);
        checkGameStatus();
    }

    public void checkGameStatus() {
        for (int i=0; i<3; i++) {
            // Checking for Player Winning in Horizontal.
            if (checkWin[i][0] == 1 && checkWin[i][1] == 1 && checkWin[i][2] == 1) {
                System.out.println("PLAYER WON with " + currentAI + " AI.");
                isGameWon = true;
                playWinAnimation();
                disableTileButtons();
                break;
            }
            // Checking for Player Winning in Vertical.
            else if (checkWin[0][i] == 1 && checkWin[1][i] == 1 && checkWin[2][i] == 1) {
                System.out.println("PLAYER WON with " + currentAI + " AI.");
                isGameWon = true;
                playWinAnimation();
                disableTileButtons();
                break;
            }
            // Checking for Player Winning in Diagonal.
            else if (checkWin[0][0] == 1 && checkWin[1][1] == 1 && checkWin[2][2] == 1 || checkWin[2][0] == 1 && checkWin[1][1] == 1 && checkWin[0][2] == 1) {
                System.out.println("PLAYER WON with " + currentAI + " AI.");
                isGameWon = true;
                playWinAnimation();
                disableTileButtons();
                break;
            }
            // Checking for AI Winning in Horizontal.
            else if (checkWin[i][0] == 2 && checkWin[i][1] == 2 && checkWin[i][2] == 2) {
                System.out.println(currentAI + " AI WON!");
                isGameWon = true;
                playWinAnimation();
                disableTileButtons();
                break;
            }
            // Checking for AI Winning in Vertical.
            else if (checkWin[0][i] == 2 && checkWin[1][i] == 2 && checkWin[2][i] == 2) {
                System.out.println(currentAI + " AI WON!");
                isGameWon = true;
                playWinAnimation();
                disableTileButtons();
                break;
            }
            // Diagonal Checking for AI Winning in Diagonal.
            else if (checkWin[0][0] == 2 && checkWin[1][1] == 2 && checkWin[2][2] == 2 || checkWin[2][0] == 2 && checkWin[1][1] == 2 && checkWin[0][2] == 2) {
                System.out.println(currentAI + " AI WON!");
                isGameWon = true;
                playWinAnimation();
                disableTileButtons();
                break;
            }
        }
        System.out.println("Won: " + isGameWon);
        if (tilesFilled == 9 && !isGameWon) {
            System.out.println("DRAW with " + currentAI + " AI.");
            isGameDrawn = true;
            disableTileButtons();
        }
    }

    public void playWinAnimation() {
        //checkWin[][];
    }

    public void disableTileButtons() {
        for (Button[] innerArray : buttons) {
            for (Button val : innerArray) {
                val.setMouseTransparent(true);
            } } }

    public void enableTileButtons() {
        for (Button[] innerArray : buttons) {
            for (Button val : innerArray) {
                val.setMouseTransparent(false);
            } } }

    public void resetButtonText() {
        for (Button[] innerArray : buttons) {
            for (Button val : innerArray) {
                val.setText("");
            } } }

    // Only for HighContrast Theme
    public void resetButtonColor() {
        for (Button[] innerArray : buttons) {
            for (Button val : innerArray) {
                val.setStyle("-fx-opacity: 1.0;-fx-background-color: darkgray;");
            } } }

    public void startNewGame() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                checkWin[i][j] = 0;
            } }
        enableTileButtons();
        resetButtonText();
        if (currentTheme == "highContrast") {
            resetButtonColor();
        }
        tilesFilled = 0;
        isGameWon = false;
        isGameDrawn = false;
        System.out.println("AI: " + currentAI);
    }

    @FXML
    void changeThemeToClassic(ActionEvent event) {
        initializeButton();
        currentTheme = "classic";
        GridPane01.setStyle("-fx-opacity: 1.0;-fx-background-color: black;");
        for (Button[] innerArray : buttons) {
            for (Button val : innerArray) {
                val.setStyle("-fx-opacity: 1.0;-fx-background-color: white;");
            } }
        AnchorPaneRight.setStyle("-fx-opacity: 1.0;-fx-background-color: white;");
        startWithDefensiveAI.setStyle("-fx-opacity: 1.0;-fx-background-color: black;");
        startWithRandomAI.setStyle("-fx-opacity: 1.0;-fx-background-color: black;");
        startNewGame();
    }

    @FXML
    void changeThemeToForrest(ActionEvent event) {
        initializeButton();
        currentTheme = "forrest";
        GridPane01.setStyle("-fx-opacity: 1.0;-fx-background-color: forestgreen;");
        for (Button[] innerArray : buttons) {
            for (Button val : innerArray) {
                val.setStyle("-fx-opacity: 1.0;-fx-background-color: darkseagreen;");
            } }
        AnchorPaneRight.setStyle("-fx-opacity: 1.0;-fx-background-color: darkseagreen;");
        startWithDefensiveAI.setStyle("-fx-opacity: 1.0;-fx-background-color: darkgreen;");
        startWithRandomAI.setStyle("-fx-opacity: 1.0;-fx-background-color: darkgreen;");
        startNewGame();
    }

    @FXML
    void changeThemeToHighContrast() {
        initializeButton();
        currentTheme = "highContrast";
        GridPane01.setStyle("-fx-opacity: 1.0;-fx-background-color: lightgray;");
        for (Button[] innerArray : buttons) {
            for (Button val : innerArray) {
                val.setStyle("-fx-opacity: 1.0;-fx-background-color: darkgray;");
            } }
        AnchorPaneRight.setStyle("-fx-opacity: 1.0;-fx-background-color: darkgray;");
        startWithDefensiveAI.setStyle("-fx-opacity: 1.0;-fx-background-color: lightgray;-fx-text-fill: black");
        startWithRandomAI.setStyle("-fx-opacity: 1.0;-fx-background-color: lightgray;-fx-text-fill: black");
        startNewGame();
    }
}