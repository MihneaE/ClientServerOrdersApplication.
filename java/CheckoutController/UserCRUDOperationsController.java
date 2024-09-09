package CheckoutController;

import Model.Drink;
import Model.User;
import Networking.RpcProtocol.ServicesRpcProxy;
import Service.ServiceException;
import Service.UserService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Statement;
import java.sql.Wrapper;
import java.util.List;
import java.util.concurrent.Callable;

public class UserCRUDOperationsController {
    private Button addUserButton;
    private Button removeUserButton;
    private Button updateUserButton;
    private Button findUserButton;
    private HBox CRUDOperationsUserBox;
    private boolean userExists;
    private ServicesRpcProxy servicesRpcProxy;

    public UserCRUDOperationsController(ServicesRpcProxy servicesRpcProxy)
    {
        this.servicesRpcProxy = servicesRpcProxy;
        this.addUserButton = new Button("ADD USER");
        this.removeUserButton = new Button("REMOVE USER");
        this.updateUserButton = new Button("UPDATE USER");
        this.findUserButton = new Button("FIND USER");

        this.createCRUDButtonsBox();

        this.userExists = false;
    }

    public void createCRUDButtonsBox()
    {
        addUserButton.setPrefSize(159, 50);
        removeUserButton.setPrefSize(159, 50);
        updateUserButton.setPrefSize(159, 50);
        findUserButton.setPrefSize(159, 50);

        CRUDOperationsUserBox = new HBox(5, addUserButton, removeUserButton, updateUserButton, findUserButton);

        {
            addUserButton.getStyleClass().add("interface-button");
            removeUserButton.getStyleClass().add("interface-button");
            updateUserButton.getStyleClass().add("interface-button");
            findUserButton.getStyleClass().add("interface-button");
        }
    }

    public void setupAddUserButton(ViewerController viewerController)
    {
        addUserButton.setOnAction(e -> {
            GridPane gridPane = new GridPane();
            gridPane.setVgap(5);
            gridPane.setHgap(5);

            Label idLabel = new Label("Id: ");
            TextField idField = new TextField();
            Label nameLabel = new Label("Name: ");
            TextField nameField = new TextField();
            Label passwordLabel = new Label("Password: ");
            TextField passwordField =  new TextField();
            Label firstNameLabel = new Label("First name: ");
            TextField firstNameField = new TextField();
            Label lastNameLabel = new Label("Last name: ");
            TextField lastNameField = new TextField();
            Label ageLabel = new Label("Age: ");
            TextField ageField =  new TextField();
            Label townLabel = new Label("Town: ");
            TextField townField = new TextField();
            Label countryLabel = new Label("Country: ");
            TextField countryField = new TextField();
            Label addressLabel = new Label("Address: ");
            TextField addressField =  new TextField();

            Pane idWrapper = new Pane(idLabel);
            Pane nameWrapper = new Pane(nameLabel);
            Pane passwordWrapper = new Pane(passwordLabel);
            Pane firstNameWrapper = new Pane(firstNameLabel);
            Pane lastNameWrapper = new Pane(lastNameLabel);
            Pane ageWrapper = new Pane(ageLabel);
            Pane townWrapper = new Pane(townLabel);
            Pane countryWrapper = new Pane(countryLabel);
            Pane addressWrapper = new Pane(addressLabel);

            {
                idLabel.getStyleClass().add("drinks-label-color-v4");
                nameLabel.getStyleClass().add("drinks-label-color-v4");
                passwordLabel.getStyleClass().add("drinks-label-color-v4");
                firstNameLabel.getStyleClass().add("drinks-label-color-v4");
                lastNameLabel.getStyleClass().add("drinks-label-color-v4");
                ageLabel.getStyleClass().add("drinks-label-color-v4");
                townLabel.getStyleClass().add("drinks-label-color-v4");
                countryLabel.getStyleClass().add("drinks-label-color-v4");
                addressLabel.getStyleClass().add("drinks-label-color-v4");

                idWrapper.setStyle("-fx-background-color: #df00fe;");
                nameWrapper.setStyle("-fx-background-color: #df00fe;");
                passwordWrapper.setStyle("-fx-background-color: #df00fe;");
                firstNameWrapper.setStyle("-fx-background-color: #df00fe;");
                lastNameWrapper.setStyle("-fx-background-color: #df00fe;");
                ageWrapper.setStyle("-fx-background-color: #df00fe;");
                townWrapper.setStyle("-fx-background-color: #df00fe;");
                countryWrapper.setStyle("-fx-background-color: #df00fe;");
                addressWrapper.setStyle("-fx-background-color: #df00fe;");
            }

            gridPane.add(idWrapper, 0, 0);
            gridPane.add(idField, 1,0);
            gridPane.add(nameWrapper, 0, 1);
            gridPane.add(nameField, 1, 1);
            gridPane.add(passwordWrapper, 0, 2);
            gridPane.add(passwordField,1, 2);
            gridPane.add(firstNameWrapper, 0, 3);
            gridPane.add(firstNameField, 1,3);
            gridPane.add(lastNameWrapper, 0, 4);
            gridPane.add(lastNameField, 1, 4);
            gridPane.add(ageWrapper, 0, 5);
            gridPane.add(ageField,1, 5);
            gridPane.add(townWrapper, 0, 6);
            gridPane.add(townField, 1,6);
            gridPane.add(countryWrapper, 0, 7);
            gridPane.add(countryField, 1, 7);
            gridPane.add(addressWrapper, 0, 8);
            gridPane.add(addressField,1, 8);

            Button addButton = new Button("ADD");
            Button cancelButton = new Button("CANCEL");

            addButton.setPrefSize(207, 50);
            cancelButton.setPrefSize(207, 50);

            idField.setPrefSize(250, 50);
            nameField.setPrefSize(250, 50);
            passwordField.setPrefSize(250, 50);
            firstNameField.setPrefSize(250, 50);
            lastNameField.setPrefSize(250, 50);
            ageField.setPrefSize(250, 50);
            townField.setPrefSize(250, 50);
            countryField.setPrefSize(250, 50);
            addressField.setPrefSize(250, 50);

            {
                addButton.getStyleClass().add("interface-button");
                cancelButton.getStyleClass().add("interface-button");

                idField.getStyleClass().add("drinks-label");
                nameField.getStyleClass().add("drinks-label");
                passwordField.getStyleClass().add("drinks-label");
                firstNameField.getStyleClass().add("drinks-label");
                lastNameField.getStyleClass().add("drinks-label");
                ageField.getStyleClass().add("drinks-label");
                townField.getStyleClass().add("drinks-label");
                countryField.getStyleClass().add("drinks-label");
                addressField.getStyleClass().add("drinks-label");
            }

            HBox buttonsBox = new HBox(5, cancelButton, addButton);
            VBox finalBox = new VBox(5, gridPane, buttonsBox);

            Scene scene = new Scene(finalBox);
            Stage newWindow = new Stage();
            newWindow.setTitle("Add new user");
            newWindow.setScene(scene);

            scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

            addButton.setOnAction(e2 -> {

                if (idField.getText().isEmpty())
                {
                    idField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                }
                else if (nameField.getText().isEmpty())
                {
                    nameField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    idField.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
                }
                else if (passwordField.getText().isEmpty())
                {
                    passwordField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    nameField.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
                }
                else
                {
                    try {
                        int id = Integer.parseInt(idField.getText());
                        String name = nameField.getText();
                        String password = passwordField.getText();
                        String firstName = firstNameField.getText();
                        String lastName = lastNameField.getText();
                        int age = Integer.parseInt(ageField.getText());
                        String town = townField.getText();
                        String country = countryField.getText();
                        String address = addressField.getText();

                        servicesRpcProxy.addUser(id, name, password, firstName, lastName, age, town, country, address);

                        User user = new User(id, name, password, firstName, lastName, age, town, country, address);

                        ViewerController.UserViewer userViewer = viewerController.getUserViewer();
                        userViewer.getUsersTableView().getItems().add(user);

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("ADD");
                        alert.setHeaderText(null);
                        alert.setContentText("User added successfully!");

                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                        Label contentLabel = (Label) dialogPane.lookup(".content");
                        if (contentLabel != null) {
                            contentLabel.getStyleClass().add("alert-content-text-v2");
                        }

                        alert.showAndWait();

                    } catch (ServiceException ex)
                    {
                        throw new RuntimeException(ex);
                    }
                    catch (NumberFormatException ex)
                    {
                        System.out.println("Id and age must be numbers!");
                    }


                    newWindow.close();
                }
            });

            cancelButton.setOnAction(e2 -> {
                newWindow.close();
            });

            newWindow.show();
        });
    }

    public void setupRemoveUserButton(ViewerController viewerController) throws ServiceException
    {
        this.removeUserButton.setOnAction(e -> {
            userExists = false;

            GridPane gridPane = new GridPane();
            gridPane.setVgap(5);
            gridPane.setHgap(5);

            Label nameLabel = new Label("Name: ");
            TextField nameField = new TextField();

            Pane nameWrapper = new Pane(nameLabel);

            {
                nameLabel.getStyleClass().add("drinks-label-color-v4");
                nameWrapper.setStyle("-fx-background-color: #df00fe;");
                nameField.getStyleClass().add("drinks-label");
            }

            gridPane.add(nameWrapper, 0, 0);
            gridPane.add(nameField, 1, 0);

            Button removeButton = new Button("REMOVE");
            Button cancelButton = new Button("CANCEL");

            removeButton.setPrefSize(175, 50);
            cancelButton.setPrefSize(175, 50);

            nameField.setPrefSize(250, 50);

            {
                removeButton.getStyleClass().add("interface-button");
                cancelButton.getStyleClass().add("interface-button");
            }

            HBox buttonsBox = new HBox(5, cancelButton, removeButton);
            VBox finalBox = new VBox(5, gridPane, buttonsBox);

            Scene scene = new Scene(finalBox);
            Stage newWindow = new Stage();
            newWindow.setTitle("Add new user");
            newWindow.setScene(scene);

            scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

            removeButton.setOnAction(e2 -> {

                if (nameField.getText().isEmpty())
                {
                    nameField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                }
                else
                {
                    String name = nameField.getText();

                    try
                    {
                        List<User> users = servicesRpcProxy.getUserDataBase().loadUsers();

                        for (int i = 0; i < users.size(); ++i)
                        {

                            if (users.get(i).getName().equals(name))
                            {
                                userExists = true;
                                break;
                            }

                        }

                    }catch (ServiceException err)
                    {
                        err.printStackTrace();
                    }

                    if (userExists)
                    {
                        try {
                            servicesRpcProxy.removeUser(name);
                        } catch (ServiceException ex) {
                            throw new RuntimeException(ex);
                        }

                        ViewerController.UserViewer userViewer = viewerController.getUserViewer();
                        try {
                            userViewer.setupObservableList();
                        } catch (ServiceException ex) {
                            throw new RuntimeException(ex);
                        }

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("REMOVE");
                        alert.setHeaderText(null);
                        alert.setContentText("User removed successfully!");

                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                        Label contentLabel = (Label) dialogPane.lookup(".content");
                        if (contentLabel != null) {
                            contentLabel.getStyleClass().add("alert-content-text-v2");
                        }

                        alert.showAndWait();
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("REMOVE");
                        alert.setHeaderText(null);
                        alert.setContentText("This user does not exists!");

                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                        Label contentLabel = (Label) dialogPane.lookup(".content");
                        if (contentLabel != null) {
                            contentLabel.getStyleClass().add("alert-content-text");
                        }

                        alert.showAndWait();
                    }

                    newWindow.close();
                }
            });

            cancelButton.setOnAction(e2 -> {
                newWindow.close();
            });

            newWindow.show();
        });
    }

    public void setupUpdateUserButton(ViewerController viewerController)
    {
        this.updateUserButton.setOnAction(e -> {
            userExists = false;

            GridPane gridPane = new GridPane();
            gridPane.setVgap(5);
            gridPane.setHgap(5);

            Label oldNameLabel = new Label("Old name: ");
            TextField oldNameField = new TextField();
            Label idLabel = new Label("New id: ");
            TextField idField = new TextField();
            Label nameLabel = new Label("New name: ");
            TextField nameField = new TextField();
            Label passwordLabel = new Label("New password: ");
            TextField passwordField =  new TextField();
            Label firstNameLabel = new Label("New First name: ");
            TextField firstNameField = new TextField();
            Label lastNameLabel = new Label("New Last name: ");
            TextField lastNameField = new TextField();
            Label ageLabel = new Label("New Age: ");
            TextField ageField =  new TextField();
            Label townLabel = new Label("New Town: ");
            TextField townField = new TextField();
            Label countryLabel = new Label("New Country: ");
            TextField countryField = new TextField();
            Label addressLabel = new Label("New Address: ");
            TextField addressField =  new TextField();

            Pane oldNameWrapper = new Pane(oldNameLabel);
            Pane idWrapper = new Pane(idLabel);
            Pane nameWrapper = new Pane(nameLabel);
            Pane passwordWrapper = new Pane(passwordLabel);
            Pane firstNameWrapper = new Pane(firstNameLabel);
            Pane lastNameWrapper = new Pane(lastNameLabel);
            Pane ageWrapper = new Pane(ageLabel);
            Pane townWrapper = new Pane(townLabel);
            Pane countryWrapper = new Pane(countryLabel);
            Pane addressWrapper = new Pane(addressLabel);


            {
                oldNameLabel.getStyleClass().add("drinks-label-color-v4");
                idLabel.getStyleClass().add("drinks-label-color-v4");
                nameLabel.getStyleClass().add("drinks-label-color-v4");
                passwordLabel.getStyleClass().add("drinks-label-color-v4");
                firstNameLabel.getStyleClass().add("drinks-label-color-v4");
                lastNameLabel.getStyleClass().add("drinks-label-color-v4");
                ageLabel.getStyleClass().add("drinks-label-color-v4");
                townLabel.getStyleClass().add("drinks-label-color-v4");
                countryLabel.getStyleClass().add("drinks-label-color-v4");
                addressLabel.getStyleClass().add("drinks-label-color-v4");

                oldNameWrapper.setStyle("-fx-background-color: #df00fe;");
                idWrapper.setStyle("-fx-background-color: #df00fe;");
                nameWrapper.setStyle("-fx-background-color: #df00fe;");
                passwordWrapper.setStyle("-fx-background-color: #df00fe;");
                firstNameWrapper.setStyle("-fx-background-color: #df00fe;");
                lastNameWrapper.setStyle("-fx-background-color: #df00fe;");
                ageWrapper.setStyle("-fx-background-color: #df00fe;");
                townWrapper.setStyle("-fx-background-color: #df00fe;");
                countryWrapper.setStyle("-fx-background-color: #df00fe;");
                addressWrapper.setStyle("-fx-background-color: #df00fe;");

                oldNameField.getStyleClass().add("drinks-label");
                idField.getStyleClass().add("drinks-label");
                nameField.getStyleClass().add("drinks-label");
                passwordField.getStyleClass().add("drinks-label");
                firstNameField.getStyleClass().add("drinks-label");
                lastNameField.getStyleClass().add("drinks-label");
                ageField.getStyleClass().add("drinks-label");
                townField.getStyleClass().add("drinks-label");
                countryField.getStyleClass().add("drinks-label");
                addressField.getStyleClass().add("drinks-label");
            }

            gridPane.add(oldNameWrapper, 0, 0);
            gridPane.add(oldNameField, 1, 0);
            gridPane.add(idWrapper, 0, 1);
            gridPane.add(idField, 1,1);
            gridPane.add(nameWrapper, 0, 2);
            gridPane.add(nameField, 1, 2);
            gridPane.add(passwordWrapper, 0, 3);
            gridPane.add(passwordField,1, 3);
            gridPane.add(firstNameWrapper, 0, 4);
            gridPane.add(firstNameField, 1,4);
            gridPane.add(lastNameWrapper, 0, 5);
            gridPane.add(lastNameField, 1, 5);
            gridPane.add(ageWrapper, 0, 6);
            gridPane.add(ageField,1, 6);
            gridPane.add(townWrapper, 0, 7);
            gridPane.add(townField, 1,7);
            gridPane.add(countryWrapper, 0, 8);
            gridPane.add(countryField, 1, 8);
            gridPane.add(addressWrapper, 0, 9);
            gridPane.add(addressField,1, 9);


            Button updateButton = new Button("UPDATE");
            Button cancelButton = new Button("CANCEL");

            updateButton.setPrefSize(243, 50);
            cancelButton.setPrefSize(243, 50);

            oldNameField.setPrefSize(250, 50);
            idField.setPrefSize(250, 50);
            nameField.setPrefSize(250, 50);
            passwordField.setPrefSize(250,50);
            firstNameField.setPrefSize(250, 50);
            lastNameField.setPrefSize(250, 50);
            ageField.setPrefSize(250, 50);
            townField.setPrefSize(250, 50);
            countryField.setPrefSize(250, 50);
            addressField.setPrefSize(250, 50);

            {
                updateButton.getStyleClass().add("interface-button");
                cancelButton.getStyleClass().add("interface-button");
            }

            HBox buttonsBox = new HBox(5, cancelButton, updateButton);
            VBox finalBox = new VBox(5, gridPane, buttonsBox);

            Scene scene = new Scene(finalBox);
            Stage newWindow = new Stage();
            newWindow.setTitle("Add new user");
            newWindow.setScene(scene);

            scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

            updateButton.setOnAction(e2 -> {

                if (oldNameField.getText().isEmpty())
                {
                    oldNameField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                }
                else if (idField.getText().isEmpty())
                {
                    idField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    oldNameField.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
                }
                else if (nameField.getText().isEmpty())
                {
                    nameField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    idField.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
                }
                else if (passwordField.getText().isEmpty())
                {
                    passwordField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    nameField.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
                }
                else
                {
                    try
                    {
                        String oldName = oldNameField.getText();
                        int id = Integer.parseInt(idField.getText());
                        String name = nameField.getText();
                        String password = passwordField.getText();
                        String firstName = firstNameField.getText();
                        String lastName = lastNameField.getText();
                        int age = Integer.parseInt(ageField.getText());
                        String town = townField.getText();
                        String country = countryField.getText();
                        String address = addressField.getText();

                        try {
                            List<User> users = servicesRpcProxy.getUserDataBase().loadUsers();

                            for (int i = 0; i < users.size(); ++i)
                            {
                                if (users.get(i).getName().equals(oldName))
                                {
                                    userExists = true;
                                    break;
                                }
                            }
                        }
                        catch (ServiceException exx){
                            exx.printStackTrace();
                        }


                        if (userExists)
                        {
                            try {
                                servicesRpcProxy.updateUser(oldName, id, name, password, firstName, lastName, age, town, country, address);
                            } catch (ServiceException ex) {
                                throw new RuntimeException(ex);
                            }

                            ViewerController.UserViewer userViewer = viewerController.getUserViewer();
                            try {
                                userViewer.setupObservableList();
                            } catch (ServiceException ex) {
                                throw new RuntimeException(ex);
                            }

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("UPDATE");
                            alert.setHeaderText(null);
                            alert.setContentText("User updated successfully!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text-v2");
                            }

                            alert.showAndWait();
                        }
                        else
                        {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("This user does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    catch (NumberFormatException ex)
                    {
                        System.out.println("Id and age must be numbers!");
                    }


                    newWindow.close();
                }

            });

            cancelButton.setOnAction(e2 -> {
                newWindow.close();
            });

            newWindow.show();
        });
    }

    public void setupFindUserButton()
    {
        this.findUserButton.setOnAction(e -> {
            HBox searchBox = new HBox(5);

            Label nameLabel = new Label("Name: ");
            TextField nameField = new TextField();
            Button searchButton = new Button("SEARCH");

            Pane nameWrapper = new Pane(nameLabel);

            {
                nameLabel.getStyleClass().add("drinks-label-color-v4");
                nameWrapper.setStyle("-fx-background-color: #df00fe;");
            }

            searchBox.getChildren().addAll(nameWrapper, nameField, searchButton);

            TableView<User> tableView = new TableView<>();

            tableView.setPrefSize(511, 150);

            TableColumn<User, Integer> idColumn = new TableColumn<>("Id");
            idColumn.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getId()));

            TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
            usernameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUsername()));

            TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
            passwordColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPassword()));

            idColumn.setPrefWidth(170);
            idColumn.setStyle("-fx-alignment: CENTER;");
            tableView.getColumns().add(idColumn);

            usernameColumn.setPrefWidth(170);
            usernameColumn.setStyle("-fx-alignment: CENTER;");
            tableView.getColumns().add(usernameColumn);

            passwordColumn.setPrefWidth(170);
            passwordColumn.setStyle("-fx-alignment: CENTER;");
            tableView.getColumns().add(passwordColumn);

            idColumn.setCellFactory(column -> new TableCell<>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(String.valueOf(item));
                        getStyleClass().add("orders-label");
                        setStyle("-fx-background-color: rgb(83,141,8); -fx-alignment: center;");
                    }
                }
            });

            usernameColumn.setCellFactory(column -> new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(String.valueOf(item ));
                        getStyleClass().add("orders-label");
                        setStyle("-fx-background-color: rgb(83,141,8); -fx-alignment: center;");
                    }
                }
            });

            passwordColumn.setCellFactory(column -> new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(String.valueOf(item));
                        getStyleClass().add("orders-label");
                        setStyle("-fx-background-color: rgb(83,141,8); -fx-alignment: center;");
                    }
                }
            });

            VBox resultBox = new VBox(5);

            HBox cancelBox = new HBox();
            cancelBox.setAlignment(Pos.BOTTOM_RIGHT);
            Button cancelButton = new Button("CANCEL");
            cancelBox.getChildren().add(cancelButton);

            nameField.setPrefSize(250, 50);
            cancelButton.setPrefSize(511, 50);
            searchButton.setPrefSize(150, 50);

            {
                searchButton.getStyleClass().add("interface-button");
                cancelButton.getStyleClass().add("interface-button");
                nameField.getStyleClass().add("drinks-label");
            }

            resultBox.getChildren().addAll(searchBox, tableView, cancelBox);

            Scene scene = new Scene(resultBox);
            Stage newWindow = new Stage();
            newWindow.setTitle("Find drink");
            newWindow.setScene(scene);

            scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

            searchButton.setOnAction(e2 -> {

                if (nameField.getText().isEmpty())
                {
                    nameField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                }
                else
                {
                    nameField.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

                    String name = nameField.getText();

                    User user = null;
                    try {
                        user = servicesRpcProxy.findUser(name);
                    } catch (ServiceException ex) {
                        throw new RuntimeException(ex);
                    }

                    ObservableList<User> observableList = FXCollections.observableArrayList(user);

                    tableView.setItems(observableList);
                }
            });

            cancelButton.setOnAction(e2 -> {
                newWindow.close();
            });

            newWindow.show();
        });
    }

    public HBox getCRUDOperationsUserBox() {
        return CRUDOperationsUserBox;
    }

    public Button getAddUserButton() {
        return addUserButton;
    }

    public Button getRemoveUserButton() {
        return removeUserButton;
    }

    public Button getUpdateUserButton() {
        return updateUserButton;
    }

    public Button getFindUserButton() {
        return findUserButton;
    }
}
