package ClientController;

import Model.DeliveryAccount;
import Model.Order;
import Networking.RpcProtocol.ServicesRpcProxy;
import Service.ServiceException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.util.Pair;

import java.util.List;

public class FirstMenuController {
    private Label welcomeLabel;
    private Pane welcomeWrapper;
    private Button drinksButton;
    private Button foodButton;
    private VBox leftMenu;
    private String currentContext;

    //###########################################################################################33

    private Label tableNumberLabel;
    private Label placeLabel;
    private TextField tableNumberField;
    private TextField placeField;
    private Pane tableNumberWrapper;
    private Pane placeWrapper;
    private Button setButton;
    private Button terraceButton;
    private Button insideButton;
    private Button placeUndoButton;
    private HBox tableNumberBox;
    private HBox placeBox;
    private VBox setBox;
    private StackPane stackPane;
    private boolean placeChoice;
    private boolean isInsideButtonPressed;
    private boolean orderIsEmpty;
    private Label insideLabel;
    private Label terraceLabel;

    //###################################################################################################3

    private Label createAccountLabel;
    private Label nameLabel;
    private Label gmailLabel;
    private Label passwordLabel;
    private Label retypePasswordLabel;
    private Label loginGmailLabel;
    private Label loginPasswordLabel;
    private Label loginLabel;
    private Label createLabel;
    private TextField loginGmailField;
    private TextField loginPasswordField;
    private Pane createAccountWrapper;
    private Pane nameWrapper;
    private Pane gmailWrapper;
    private Pane passwordWrapper;
    private Pane retypePasswordWrapper;
    private Pane loginGmailWrapper;
    private Pane loginPasswordWrapper;
    private Pane loginWrapper;
    private Pane createWrapper;
    private TextField nameField;
    private TextField gmailField;
    private TextField passwordField;
    private TextField retypePasswordField;
    private Button signUpButton;
    private Button signInButton;
    private GridPane gridPane;
    private GridPane loginGridPane;
    private HBox buttonsBox;
    private VBox signUpBox;
    private VBox createAccountBox;
    private VBox loginBox;
    private Button createNewAccountButton;
    private Button loginButton;
    private Button backLoginButton;
    private Button backCreateButton;
    private ServicesRpcProxy servicesRpcProxy;

    private StringBuilder passwordBuilder = new StringBuilder();
    private StringBuilder retypePasswordBuilder = new StringBuilder();
    private StringBuilder loginPasswordBuilder = new StringBuilder();


    public FirstMenuController(ServicesRpcProxy servicesRpcProxy)
    {
        this.servicesRpcProxy = servicesRpcProxy;

        this.welcomeLabel = new Label("<-WELCOME->");
        this.welcomeWrapper = new Pane(welcomeLabel);
        this.welcomeWrapper.setStyle("-fx-background-color: #0e0e0e;");

        welcomeWrapper.setPrefWidth(715);
        welcomeWrapper.setPrefHeight(110);

        this.drinksButton = new Button("DRINKS");
        this.foodButton = new Button("FOOD");
        this.currentContext = "";

        this.placeChoice = false;
        this.isInsideButtonPressed = false;
        this.orderIsEmpty = false;
        this.insideLabel = new Label();
        this.terraceLabel = new Label();

        this.setupSetBox();
        this.setupAccountBox();

        this.welcomeLabel.layoutXProperty().bind(
                this.welcomeWrapper.widthProperty().subtract(this.welcomeLabel.widthProperty()).divide(2)
        );
        this.welcomeLabel.layoutYProperty().bind(
                this.welcomeWrapper.heightProperty().subtract(this.welcomeLabel.heightProperty()).divide(2)
        );

        passwordField.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            char inputChar = keyEvent.getCharacter().charAt(0);
            if (inputChar == '\b') {
                if (passwordBuilder.length() > 0) {
                    passwordBuilder.deleteCharAt(passwordBuilder.length() - 1);
                    passwordField.setText(passwordField.getText().substring(0, passwordField.getText().length() - 1));
                }
                keyEvent.consume();
            } else if (!keyEvent.getCharacter().isEmpty()) {
                passwordBuilder.append(inputChar);
                passwordField.setText(passwordField.getText() + "*");
                keyEvent.consume();
            }
        });

        retypePasswordField.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            char inputChar = keyEvent.getCharacter().charAt(0);
            if (inputChar == '\b') {
                if (retypePasswordBuilder.length() > 0) {
                    retypePasswordBuilder.deleteCharAt(retypePasswordBuilder.length() - 1);
                    retypePasswordField.setText(retypePasswordField.getText().substring(0, retypePasswordField.getText().length() - 1));
                }
                keyEvent.consume();
            } else if (!keyEvent.getCharacter().isEmpty()) {
                retypePasswordBuilder.append(inputChar);
                retypePasswordField.setText(retypePasswordField.getText() + "*");
                keyEvent.consume();
            }
        });

        loginPasswordField.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            char inputChar = keyEvent.getCharacter().charAt(0);
            if (inputChar == '\b') {
                if (loginPasswordBuilder.length() > 0) {
                    loginPasswordBuilder.deleteCharAt(loginPasswordBuilder.length() - 1);
                    loginPasswordField.setText(loginPasswordField.getText().substring(0, loginPasswordField.getText().length() - 1));
                }
                keyEvent.consume();
            } else if (!keyEvent.getCharacter().isEmpty()) {
                loginPasswordBuilder.append(inputChar);
                loginPasswordField.setText(loginPasswordField.getText() + "*");
                keyEvent.consume();
            }
        });
    }

    public void setupStartMenu()
    {
        {
            welcomeLabel.getStyleClass().add("welcome-label");
            welcomeLabel.setStyle("-fx-font-size: 100px;");

            drinksButton.getStyleClass().add("menu-button");
            foodButton.getStyleClass().add("menu-button");
        }

        drinksButton.setPrefSize(340, 340);
        foodButton.setPrefSize(340, 340);

        HBox menuButtons = new HBox(40, drinksButton, foodButton);
        HBox.setMargin(drinksButton, new Insets(0, 0, 0, 40));
        menuButtons.setAlignment(Pos.CENTER);

        Region spacer = new Region();
        spacer.setPrefHeight(30);

        Region spacerWelcome = new Region();
        spacerWelcome.setPrefWidth(40);

        HBox welcomeBox = new HBox(spacerWelcome, welcomeWrapper);

        leftMenu = new VBox(40, welcomeBox, menuButtons);
        leftMenu.setAlignment(Pos.CENTER);
    }

    public void setupSetBox()
    {
        tableNumberLabel = new Label("Table number:");
        placeLabel = new Label("Place:");

        tableNumberWrapper = new Pane(tableNumberLabel);
        placeWrapper = new Pane(placeLabel);

        tableNumberField = new TextField();
        placeField = new TextField();

        setButton = new Button("SET");
        terraceButton = new Button("Terrace");
        insideButton = new Button("Inside");
        placeUndoButton = new Button();

        terraceButton.setPrefSize(160, 50);
        insideButton.setPrefSize(160,50);

        Image undoImage = new Image("undo-6.PNG");
        ImageView imageView  = new ImageView(undoImage);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);

        placeUndoButton.setGraphic(imageView);
        placeUndoButton.setPrefSize(50, 50);

        tableNumberBox = new HBox(10, tableNumberWrapper, tableNumberField);
        placeBox = new HBox(10, placeWrapper, insideButton, terraceButton, placeUndoButton);
        setBox = new VBox(5, tableNumberBox, placeBox, setButton);

        tableNumberWrapper.setPrefHeight(50);
        placeWrapper.setPrefHeight(50);

        this.stackPane = new StackPane(setBox);

        stackPane.setMaxSize(500, 180);
        stackPane.setAlignment(Pos.CENTER);

        tableNumberField.setPrefSize(250, 50);

        setButton.setPrefSize(500, 50);

        tableNumberField.setPromptText("Table number");

        tableNumberBox.setAlignment(Pos.CENTER);
        placeBox.setAlignment(Pos.CENTER);
        setBox.setAlignment(Pos.CENTER);

        {
            tableNumberLabel.getStyleClass().add("welcome-label");
            placeLabel.getStyleClass().add("welcome-label");

            tableNumberWrapper.setStyle("-fx-background-color: #3355ff;");
            placeWrapper.setStyle("-fx-background-color: #3355ff;");

            tableNumberField.setStyle("-fx-font-size: 20px;");
            tableNumberField.getStyleClass().add("drinks-label");

            setButton.getStyleClass().add("interface-button");
            terraceButton.getStyleClass().add("choice-button");
            insideButton.getStyleClass().add("choice-button");
            placeUndoButton.getStyleClass().add("live-order-button");
        }
    }

    public void setupAccountBox()
    {
        createAccountLabel = new Label("CREATE ACCOUNT");
        createAccountWrapper = new Pane(createAccountLabel);

        createAccountWrapper.setMaxWidth(310);

        createAccountLabel.layoutXProperty().bind(
                createAccountWrapper.widthProperty().subtract(createAccountLabel.widthProperty()).divide(2)
        );

        createAccountLabel.layoutYProperty().bind(
                createAccountWrapper.heightProperty().subtract(createAccountLabel.heightProperty()).divide(2)
        );

        loginLabel = new Label("LOGIN");
        createLabel = new Label("NEW ACCOUNT");

        nameLabel = new Label("Name:");
        gmailLabel = new Label("Gmail:");
        passwordLabel = new Label("Password");
        retypePasswordLabel = new Label("R-password:");

        loginGmailLabel = new Label("Gmail:");
        loginPasswordLabel = new Label("Password:");

        nameWrapper = new Pane(nameLabel);
        gmailWrapper = new Pane(gmailLabel);
        passwordWrapper = new Pane(passwordLabel);
        retypePasswordWrapper = new Pane(retypePasswordLabel);

        loginGmailWrapper = new Pane(loginGmailLabel);
        loginPasswordWrapper = new Pane(loginPasswordLabel);

        loginWrapper = new Pane(loginLabel);
        createWrapper = new Pane(createLabel);

        loginWrapper.setMaxWidth(473);
        createWrapper.setMaxWidth(509);

        nameField = new TextField();
        gmailField = new TextField();
        passwordField = new TextField();
        retypePasswordField = new TextField();

        loginGmailField = new TextField();
        loginPasswordField = new TextField();

        gridPane = new GridPane();
        loginGridPane = new GridPane();

        gridPane.setHgap(5);
        gridPane.setVgap(5);
        loginGridPane.setHgap(5);
        loginGridPane.setVgap(5);

        gridPane.add(nameWrapper, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(gmailWrapper, 0, 1);
        gridPane.add(gmailField, 1, 1);
        gridPane.add(passwordWrapper, 0, 2);
        gridPane.add(passwordField, 1, 2);
        gridPane.add(retypePasswordWrapper, 0, 3);
        gridPane.add(retypePasswordField, 1, 3);

        loginGridPane.add(loginGmailWrapper, 0, 0);
        loginGridPane.add(loginGmailField, 1, 0);
        loginGridPane.add(loginPasswordWrapper, 0, 1);
        loginGridPane.add(loginPasswordField, 1, 1);

        signInButton = new Button("SIGN IN");
        signUpButton = new Button("SIGN UP");

        createNewAccountButton = new Button("CREATE");
        loginButton = new Button("LOGIN");

        backLoginButton = new Button("BACK");
        backCreateButton = new Button("BACK");

        HBox back1Box = new HBox(5, backLoginButton, loginButton);
        HBox back2Box = new HBox(5, backCreateButton, createNewAccountButton);

        back2Box.setAlignment(Pos.CENTER);
        back1Box.setAlignment(Pos.CENTER);

        buttonsBox = new HBox(10, signInButton, signUpButton);
        signUpBox = new VBox(10, createAccountWrapper, buttonsBox);

        createAccountBox = new VBox(5, createWrapper, gridPane, back2Box);
        loginBox = new VBox(5, loginWrapper, loginGridPane, back1Box);

        buttonsBox.setAlignment(Pos.CENTER);
        signUpBox.setAlignment(Pos.CENTER);

        createAccountBox.setAlignment(Pos.CENTER);
        loginBox.setAlignment(Pos.CENTER);

        gridPane.setAlignment(Pos.CENTER);
        loginGridPane.setAlignment(Pos.CENTER);

        nameField.setPrefSize(300, 50);
        gmailField.setPrefSize(300, 50);
        passwordField.setPrefSize(300, 50);
        retypePasswordField.setPrefSize(300, 50);
        loginPasswordField.setPrefSize(300, 50);
        loginGmailField.setPrefSize(300, 50);

        signInButton.setPrefSize(150, 50);
        signUpButton.setPrefSize(150, 50);

        createNewAccountButton.setPrefSize(254, 50);
        loginButton.setPrefSize(234, 50);

        backLoginButton.setPrefSize(234, 50);
        backCreateButton.setPrefSize(254, 50);

        nameField.setPromptText("Name");
        gmailField.setPromptText("Gmail");
        passwordField.setPromptText("Password");
        retypePasswordField.setPromptText("R-Password");
        loginGmailField.setPromptText("Gmail");
        loginPasswordField.setPromptText("Password");

        {
            nameLabel.getStyleClass().add("welcome-label");
            gmailLabel.getStyleClass().add("welcome-label");
            passwordLabel.getStyleClass().add("welcome-label");
            retypePasswordLabel.getStyleClass().add("welcome-label");
            loginGmailLabel.getStyleClass().add("welcome-label");
            loginPasswordLabel.getStyleClass().add("welcome-label");
            createAccountLabel.getStyleClass().add("welcome-label");
            loginLabel.getStyleClass().add("welcome-label");
            createLabel.getStyleClass().add("welcome-label");

            nameWrapper.setStyle("-fx-background-color: #3355ff;");
            gmailWrapper.setStyle("-fx-background-color: #3355ff;");
            passwordWrapper.setStyle("-fx-background-color: #3355ff;");
            retypePasswordWrapper.setStyle("-fx-background-color: #3355ff;");
            loginGmailWrapper.setStyle("-fx-background-color: #3355ff;");
            loginPasswordWrapper.setStyle("-fx-background-color: #3355ff;");
            createAccountWrapper.setStyle("-fx-background-color: #b833ff;");
            loginWrapper.setStyle("-fx-background-color: #b833ff;");
            createWrapper.setStyle("-fx-background-color: #b833ff;");

            nameField.setStyle("-fx-font-size: 20px;");
            nameField.getStyleClass().add("drinks-label");
            gmailField.setStyle("-fx-font-size: 20px;");
            gmailField.getStyleClass().add("drinks-label");
            passwordField.setStyle("-fx-font-size: 20px;");
            passwordField.getStyleClass().add("drinks-label");
            retypePasswordField.setStyle("-fx-font-size: 20px;");
            retypePasswordField.getStyleClass().add("drinks-label");
            loginGmailField.setStyle("-fx-font-size: 20px;");
            loginGmailField.getStyleClass().add("drinks-label");
            loginPasswordField.setStyle("-fx-font-size: 20px;");
            loginPasswordField.getStyleClass().add("drinks-label");

            signUpButton.getStyleClass().add("interface-button");
            signInButton.getStyleClass().add("interface-button");
            createNewAccountButton.getStyleClass().add("interface-button");
            loginButton.getStyleClass().add("interface-button");
            backLoginButton.getStyleClass().add("interface-button");
            backCreateButton.getStyleClass().add("interface-button");
        }
    }

    public void setupDrinksButton(BorderPane borderPane, HBox drinkActionBox, YourOrderViewController yourOrderViewController, DrinkFoodViewerController drinkFoodViewerController)
    {
        this.drinksButton.setOnAction(e -> {
            borderPane.setLeft(drinkActionBox);
            borderPane.setRight(yourOrderViewController.getOrderBox());
            this.currentContext = "drinks";

            drinkFoodViewerController.getMiddleBox().getChildren().clear();
            drinkFoodViewerController.getMiddleBox().getChildren().addAll(drinkFoodViewerController.getSearchBox(), drinkFoodViewerController.getDrinkContainer(), drinkFoodViewerController.getButtonsBox());
        });
    }

    public void setupFoodButton(BorderPane borderPane, HBox foodActionBox, YourOrderViewController yourOrderViewController, DrinkFoodViewerController drinkFoodViewerController)
    {
        this.foodButton.setOnAction(e -> {
            borderPane.setLeft(foodActionBox);
            borderPane.setRight(yourOrderViewController.getOrderBox());
            this.currentContext = "foods";

            drinkFoodViewerController.getMiddleBox().getChildren().clear();
            drinkFoodViewerController.getMiddleBox().getChildren().addAll(drinkFoodViewerController.getSearchBox(), drinkFoodViewerController.getFoodContainer(), drinkFoodViewerController.getButtonsBox());
        });
    }

    public void setupInsideButton()
    {
        insideButton.setOnAction(e -> {
            if (placeChoice)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("EMPTY CHOICE");
                alert.setHeaderText(null);
                alert.setContentText("The place is already entered!");

                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                Label contentLabel = (Label) dialogPane.lookup(".content");
                if (contentLabel != null) {
                    contentLabel.getStyleClass().add("alert-content-text");
                }

                alert.showAndWait();
            }
            else
            {
                ImageView tickImageView = new ImageView(new Image("green-check-mark-icon-in-a-circle-tick-symbol-in-green-color-2ABNAN7.jpg"));
                tickImageView.setFitHeight(50);
                tickImageView.setFitWidth(160);

                placeBox.getChildren().set(1, tickImageView);

                this.placeChoice = true;
                this.isInsideButtonPressed = true;
                insideLabel.setText("inside");
            }
        });
    }

    public void setupTerraceButton()
    {
        terraceButton.setOnAction(e -> {
            if (placeChoice)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("EMPTY CHOICE");
                alert.setHeaderText(null);
                alert.setContentText("The place is already entered!");

                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                Label contentLabel = (Label) dialogPane.lookup(".content");
                if (contentLabel != null) {
                    contentLabel.getStyleClass().add("alert-content-text");
                }

                alert.showAndWait();
            }
            else
            {
                ImageView tickImageView = new ImageView(new Image("green-check-mark-icon-in-a-circle-tick-symbol-in-green-color-2ABNAN7.jpg"));
                tickImageView.setFitHeight(50);
                tickImageView.setFitWidth(160);

                placeBox.getChildren().set(2, tickImageView);

                this.placeChoice = true;
                this.isInsideButtonPressed = false;
                terraceLabel.setText("terrace");
            }
        });
    }

    public void setupPlaceUndoButton()
    {
        this.placeUndoButton.setOnAction(e -> {
            this.placeChoice = false;
            this.isInsideButtonPressed = false;

            if (placeBox.getChildren().get(1) != insideButton)
                placeBox.getChildren().set(1, insideButton);
            else if (placeBox.getChildren().get(2) != terraceButton)
                placeBox.getChildren().set(2, terraceButton);
        });
    }

    public void setupSetButton(BorderPane borderPane, YourOrderViewController yourOrderViewController)
    {
        this.setButton.setOnAction(e -> {

            if (placeChoice && !tableNumberField.getText().isEmpty())
            {
                if (isInsideButtonPressed)
                {
                    Pair<String, Integer> key = new Pair<>(insideLabel.getText(), Integer.parseInt(tableNumberField.getText()));

                    try {
                        Order order = servicesRpcProxy.getOrderFromMap(key);

                        if (order != null)
                        {
                            String orderName = order.getName();
                            if (orderName == null || orderName.isEmpty()) {
                                orderIsEmpty = true;
                            }
                        }


                    } catch (ServiceException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else
                {
                    Pair<String, Integer> key = new Pair<>(terraceLabel.getText(), Integer.parseInt(tableNumberField.getText()));

                    try {
                        Order order = servicesRpcProxy.getOrderFromMap(key);

                        if (order != null)
                        {
                            String orderName = order.getName();
                            if (orderName == null || orderName.isEmpty()) {
                                orderIsEmpty = true;
                            }
                        }


                    } catch (ServiceException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if (orderIsEmpty)
                {
                    borderPane.setCenter(null);
                    borderPane.setLeft(this.getLeftMenu());
                    borderPane.setRight(yourOrderViewController.getOrderBox());
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("NOT EMPTY");
                    alert.setHeaderText(null);
                    alert.setContentText("This key has another order!");

                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                    Label contentLabel = (Label) dialogPane.lookup(".content");
                    if (contentLabel != null) {
                        contentLabel.getStyleClass().add("alert-content-text");
                    }

                    alert.showAndWait();
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("EMPTY");
                alert.setHeaderText(null);
                alert.setContentText("The place and table number must not be empty!");

                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                Label contentLabel = (Label) dialogPane.lookup(".content");
                if (contentLabel != null) {
                    contentLabel.getStyleClass().add("alert-content-text");
                }

                alert.showAndWait();
            }
        });
    }

    public void setupSignInButton(BorderPane borderPane)
    {
        this.signInButton.setOnAction(e -> {
            borderPane.setCenter(null);
            borderPane.setCenter(this.getLoginBox());
        });
    }

    public void setupSignUpButton(BorderPane borderPane)
    {
        this.signUpButton.setOnAction(e -> {
            borderPane.setCenter(null);
            borderPane.setCenter(this.getCreateAccountBox());
        });
    }

    public void setupBackLoginButton(BorderPane borderPane)
    {
        this.backLoginButton.setOnAction(e -> {
            borderPane.setCenter(null);
            borderPane.setCenter(this.getSignUpBox());
        });
    }

    public void setupBackCreateButton(BorderPane borderPane)
    {
        this.backCreateButton.setOnAction(e -> {
            borderPane.setCenter(null);
            borderPane.setCenter(this.getSignUpBox());
        });
    }

    public boolean isValidAuthDelivery() throws ServiceException {
        String gmail = loginGmailField.getText().trim();
        String password  = String.valueOf(loginPasswordBuilder);

        boolean validAuth = false;

        List<DeliveryAccount> accountList = servicesRpcProxy.getDeliveryAccountsDataBase().loadAccounts();

        for (DeliveryAccount deliveryAccount : accountList)
            if (deliveryAccount.getGmail().equals(gmail) && deliveryAccount.getPassword().equals(password))
            {
                validAuth = true;
                break;
            }

        if (validAuth)
            return true;
        return false;
    }

    public void setupLoginButton(BorderPane borderPane, YourOrderViewController yourOrderViewController)
    {
        this.loginButton.setOnAction(e -> {

            try {
                if (isValidAuthDelivery())
                {
                    borderPane.setCenter(null);
                    borderPane.setLeft(this.getLeftMenu());
                    borderPane.setRight(yourOrderViewController.getOrderBox());
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("WRONG");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect username or password!");

                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                    Label contentLabel = (Label) dialogPane.lookup(".content");
                    if (contentLabel != null) {
                        contentLabel.getStyleClass().add("alert-content-text");
                    }

                    alert.showAndWait();
                }

            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void setupCreateButton(BorderPane borderPane, YourOrderViewController yourOrderViewController)
    {
        this.createNewAccountButton.setOnAction(e -> {

            String name = nameField.getText().trim();
            String gmail = gmailField.getText().trim();
            String password = String.valueOf(passwordBuilder);
            String retypePassword = String.valueOf(retypePasswordBuilder);

            boolean accountExists = false;

            try
            {
                for (DeliveryAccount account : servicesRpcProxy.getDeliveryAccounts())
                {
                    if (account.getGmail().equals(gmail))
                    {
                        accountExists = true;
                        break;
                    }
                }
            }
            catch (ServiceException ex)
            {
                ex.printStackTrace();
            }


            if (!name.isEmpty() && !gmail.isEmpty() && !password.isEmpty() && !retypePassword.isEmpty())
            {
                if (!accountExists)
                {
                    if (password.equals(retypePassword))
                    {
                        DeliveryAccount deliveryAccount = new DeliveryAccount(name, gmail, password);

                        try
                        {
                            servicesRpcProxy.addAccountToAccounts(deliveryAccount);
                            servicesRpcProxy.getDeliveryAccountsDataBase().addAccountToDatabase(deliveryAccount);
                        }
                        catch (ServiceException ex)
                        {
                            ex.printStackTrace();
                        }

                        borderPane.setCenter(null);
                        borderPane.setLeft(this.getLeftMenu());
                        borderPane.setRight(yourOrderViewController.getOrderBox());
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("PASSWORDS");
                        alert.setHeaderText(null);
                        alert.setContentText("The passwords does not match!");

                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                        Label contentLabel = (Label) dialogPane.lookup(".content");
                        if (contentLabel != null) {
                            contentLabel.getStyleClass().add("alert-content-text");
                        }

                        alert.showAndWait();
                    }
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("SAME ACCOUNT");
                    alert.setHeaderText(null);
                    alert.setContentText("This account already exists! Please login");

                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                    Label contentLabel = (Label) dialogPane.lookup(".content");
                    if (contentLabel != null) {
                        contentLabel.getStyleClass().add("alert-content-text");
                    }

                    alert.showAndWait();
                }

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("EMPTY FIELDS");
                alert.setHeaderText(null);
                alert.setContentText("The fiels must not be empty!");

                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                Label contentLabel = (Label) dialogPane.lookup(".content");
                if (contentLabel != null) {
                    contentLabel.getStyleClass().add("alert-content-text");
                }

                alert.showAndWait();
            }
        });
    }



    public String getCurrentContext() {
        return currentContext;
    }

    public VBox getLeftMenu() {
        return leftMenu;
    }

    public VBox getSetBox() {
        return setBox;
    }

    public TextField getTableNumberField() {
        return tableNumberField;
    }

    public Label getInsideLabel() {
        return insideLabel;
    }

    public Label getTerraceLabel() {
        return terraceLabel;
    }

    public boolean isInsideButtonPressed() {
        return isInsideButtonPressed;
    }

    public VBox getSignUpBox() {
        return signUpBox;
    }

    public VBox getCreateAccountBox() {
        return createAccountBox;
    }

    public VBox getLoginBox() {
        return loginBox;
    }
}
