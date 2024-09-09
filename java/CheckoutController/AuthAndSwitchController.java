package CheckoutController;

import ClientController.FirstMenuController;
import ClientController.YourOrderViewController;
import Networking.RpcProtocol.ServicesRpcProxy;
import Service.ServiceException;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class AuthAndSwitchController {
    private Label usernameLabel;
    private Label passwordLabel;
    private TextField usernameField;
    private TextField passwordField;
    private Button swtichMenuButton;
    private Button swtichToClientInterfaceButton;
    private Button swtichToClientInterfaceButton2;
    private Button loginButton;
    private HBox usernameBox;
    private HBox passwordBox;
    private HBox switchButtonBox;
    private HBox switchBoxWithOrderBox;
    private VBox loginBox;
    private StackPane stackPane;
    private StringBuilder passwordBuilder = new StringBuilder();
    private ServicesRpcProxy servicesRpcProxy;

    private Label loginLabel;
    private Pane loginWrapper;
    private GridPane gridPane;

    public AuthAndSwitchController(ServicesRpcProxy servicesRpcProxy)
    {
        this.servicesRpcProxy = servicesRpcProxy;

        loginLabel = new Label("LOGIN");
        loginWrapper = new Pane(loginLabel);

        loginWrapper.setMaxWidth(510);

        {
            loginLabel.getStyleClass().add("welcome-label");
            loginWrapper.setStyle("-fx-background-color: #b833ff;");
        }

        this.usernameLabel = new Label("USERNAME:");
        this.passwordLabel = new Label("PASSWORD:");
        this.usernameField = new TextField();
        this.passwordField = new TextField();
        this.loginButton = new Button("LOGIN");
        this.swtichMenuButton = new Button("SWITCH");
        this.swtichToClientInterfaceButton = new Button("SWITCH");
        this.swtichToClientInterfaceButton2 = new Button("LOGOUT");
        this.stackPane = new StackPane();

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

        this.swtichMenuButton.setPrefSize(150, 50);
        this.swtichMenuButton.getStyleClass().add("interface-button");
        this.swtichToClientInterfaceButton2.getStyleClass().add("toolbar-button");

        this.setupAuthBox();
    }

    public void placeSwitchToClientInterfaceButton(BorderPane borderPane)
    {
        this.swtichToClientInterfaceButton.setPrefSize(150,50);
        this.swtichToClientInterfaceButton.getStyleClass().add("interface-button");

        HBox switchToClientBox = new HBox(swtichToClientInterfaceButton);
        switchToClientBox.setAlignment(Pos.TOP_LEFT);

        borderPane.setTop(switchToClientBox);
    }

    public void setupAuthBox()
    {
        Pane usernameWrapper = new Pane(usernameLabel);
        Pane passwordWrapper = new Pane(passwordLabel);

        gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.add(usernameWrapper, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(passwordWrapper, 0, 1);
        gridPane.add(passwordField, 1, 1);

        this.loginBox = new VBox(5, loginWrapper, gridPane, loginButton);

        usernameWrapper.setPrefHeight(50);
        passwordWrapper.setPrefHeight(50);

        stackPane.setMaxSize(470, 180);
        stackPane.setAlignment(Pos.CENTER);

        usernameField.setPrefSize(300, 50);
        passwordField.setPrefSize(300,50);

        loginButton.setPrefSize(510, 50);

        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");

        //usernameBox.setAlignment(Pos.CENTER);
        //passwordBox.setAlignment(Pos.CENTER);
        loginBox.setAlignment(Pos.CENTER);
        gridPane.setAlignment(Pos.CENTER);

        {
            usernameLabel.getStyleClass().add("welcome-label");
            passwordLabel.getStyleClass().add("welcome-label");

            usernameWrapper.setStyle("-fx-background-color: #3355ff;");
            passwordWrapper.setStyle("-fx-background-color: #3355ff;");

            usernameField.setStyle("-fx-font-size: 20px;");
            usernameField.getStyleClass().add("drinks-label");
            passwordField.setStyle("-fx-font-size: 20px;");
            passwordField.getStyleClass().add("drinks-label");

            loginButton.getStyleClass().add("interface-button");
        }
    }

    public void invalidLogin()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WRONG USER");
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

    public boolean isValidAuth() throws ServiceException {
        String username = usernameField.getText();
        //String password = passwordField.getText();
        String password = String.valueOf(passwordBuilder);

        for (int i = 0; i < servicesRpcProxy.getUsers().size(); ++i)
            if (username.equals(servicesRpcProxy.getUsers().get(i).getName()) && password.equals(servicesRpcProxy.getUsers().get(i).getPassword()))
                return true;

        return false;
    }

    public void setupSwitchButton(BorderPane borderPane)
    {
        this.swtichMenuButton.setOnAction(e -> {
            borderPane.setLeft(null);
            borderPane.setRight(null);
            borderPane.setCenter(this.getStackPane());

            this.placeSwitchToClientInterfaceButton(borderPane);
        });
    }

    public void setupSwitchToClientInterfaceButton(BorderPane borderPane, FirstMenuController startMenuController, YourOrderViewController yourOrderViewController)
    {
        this.swtichToClientInterfaceButton.setOnAction(e -> {
            borderPane.setCenter(null);
            borderPane.setLeft(startMenuController.getLeftMenu());
            borderPane.setRight(yourOrderViewController.getOrderBox());
            borderPane.setTop(null);
        });
    }

    public void setupSwitchToClientInterfaceButton2(BorderPane borderPane, FirstMenuController startMenuController, YourOrderViewController yourOrderViewController)
    {
        this.swtichToClientInterfaceButton2.setOnAction(e -> {
            borderPane.setCenter(null);
            borderPane.setLeft(startMenuController.getLeftMenu());
            borderPane.setRight(yourOrderViewController.getOrderBox());
            borderPane.setTop(null);
        });
    }

    public void setupSwitchToClientInterfaceButton2_v2(BorderPane borderPane)
    {
        this.swtichToClientInterfaceButton2.setOnAction(e -> {
            borderPane.setCenter(null);
            borderPane.setTop(null);
            borderPane.setCenter(this.getLoginBox());
        });
    }

    public void setupLoginButton(BorderPane borderPane, CRUDOperationsController crudOperations, UserCRUDOperationsController userCRUDOperationsController, LiveOrdersController liveOrdersController, ViewerController viewerController, RestaurantController restaurantController)
    {
        this.loginButton.setOnAction(e -> {
            try {
                if (isValidAuth())
                {
                    borderPane.setCenter(null);
                    crudOperations.createBoxWithViewerBox(userCRUDOperationsController, liveOrdersController, viewerController, restaurantController);
                    borderPane.setTop(crudOperations.getBiggestBox());
                }
                else
                    this.invalidLogin();
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public HBox getSwitchBoxWithOrderBox() {
        return switchBoxWithOrderBox;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public VBox getLoginBox() {
        return loginBox;
    }

    public Button getSwtichMenuButton() {
        return swtichMenuButton;
    }

    public Button getSwtichToClientInterfaceButton() {
        return swtichToClientInterfaceButton;
    }

    public Button getSwtichToClientInterfaceButton2() {
        return swtichToClientInterfaceButton2;
    }
}
