package CheckoutController;

import Enums.*;
import Model.Drink;
import Model.Food;
import Networking.RpcProtocol.ServicesRpcProxy;
import Service.DrinkService;
import Service.FoodService;
import Service.ServiceException;
import javafx.beans.Observable;
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

import java.util.List;

public class CRUDOperationsController {
    private Button addButton;
    private Button addFoodButton;
    private Button addDrinkButton;
    private Button cancelAddOperationButton;
    private Button removeButton;
    private Button removeDrinkButton;
    private Button removeFoodButton;
    private Button cancelRemoveOperationButton;
    private Button updateButton;
    private Button updateDrinkButton;
    private Button updateFoodButton;
    private Button cancelUpdateOperationButton;
    private Button findButton;
    private Button findDrinkButton;
    private Button findFoodButton;
    private Button cancelFindOperationButton;
    private Button beerButton = new Button("BEER");
    private Button cocktailButton = new Button("COCKTAIL");
    private Button wineButton = new Button("WINE");
    private Button strongDrinkButton = new Button("STRONG DRINK");
    private Button coffeeButton = new Button("COFFEE");
    private Button juiceButton = new Button("JUICE");
    private Button teaButton = new Button("TEA");
    private Button softDrinkButton = new Button("SOFT DRINK");
    private Button saladButton = new Button("SALAD");
    private Button soupButton = new Button("SOUP");
    private Button smallBiteButton = new Button("SMALL BITE");
    private Button meatButton = new Button("MEAT");
    private Button pastaButton = new Button("PASTA");
    private Button seaFoodButton = new Button("SEA FOOD");
    private Button pizzaButton = new Button("PIZZA");
    private Button cakeButton = new Button("CAKE");
    private Button iceScreamButton = new Button("ICE SCREAM");
    private Button otherDessertButton = new Button("OTHER DESSERT");
    private Button backButton = new Button("BACK");
    private VBox finalAddButtonsBox;
    private VBox finalRemoveButtonsBox;
    private VBox finalUpdateButtonsBox;
    private VBox finalFindButtonsBox;
    private VBox drinkAndFoodAndUserCRUDButtonsBox;
    private VBox biggestBox;
    private HBox addButtonsBox;
    private HBox removeButtonsBox;
    private HBox updateButtonsBox;
    private HBox findButtonsBox;
    private HBox CRUDButtonsBox;
    private HBox allDrinksBox;
    private HBox allFoodSBox;
    private HBox boxesWithLiveOrderBox;
    private String currentStage;
    private String currentType;
    private String nameOfItem;
    private boolean drinkExists;
    private boolean foodExists;
    private ServicesRpcProxy servicesRpcProxy;

    public CRUDOperationsController(ServicesRpcProxy servicesRpcProxy)
    {
        this.servicesRpcProxy = servicesRpcProxy;
        this.currentStage = "";
        this.currentType = "";
        this.nameOfItem = "";

        this.createButtons();
        this.createCRUDButtonsBox();

        this.drinkExists = false;
        this.foodExists = false;
    }

    public Button createCancelButton()
    {
        return new Button("CANCEL");
    }

    public void createCRUDButtonsBox()
    {
        this.CRUDButtonsBox = new HBox(5, addButton, removeButton, updateButton, findButton);

        addButton.setPrefSize(159, 50);
        removeButton.setPrefSize(159, 50);
        updateButton.setPrefSize(159, 50);
        findButton.setPrefSize(159, 50);

        {
            addButton.getStyleClass().add("interface-button");
            removeButton.getStyleClass().add("interface-button");
            updateButton.getStyleClass().add("interface-button");
            findButton.getStyleClass().add("interface-button");
        }
    }

    public void createDrinkAndFoodAndUserCRUDOperationBox(UserCRUDOperationsController userCRUDOperationsController, ViewerController viewerController, RestaurantController restaurantController)
    {
        Label titleLabel = new Label("MENU AND USERS MANAGER");
        Pane wrapper = new Pane(titleLabel);
        wrapper.setPrefSize(635, 50);

        titleLabel.layoutXProperty().bind(wrapper.widthProperty().subtract(titleLabel.widthProperty()).divide(2));

        {
            wrapper.setStyle("-fx-background-color: #df00fe;");
            titleLabel.getStyleClass().add("welcome-label");
        }

        this.drinkAndFoodAndUserCRUDButtonsBox = new VBox(5, wrapper, CRUDButtonsBox, userCRUDOperationsController.getCRUDOperationsUserBox(), viewerController.getTitleAndButtonsBox(), restaurantController.getTitleAndButtonsBox());
    }

    public void createBoxWithLiveOrderPane(UserCRUDOperationsController userCRUDOperationsController,LiveOrdersController liveOrdersController, ViewerController viewerController, RestaurantController restaurantController)
    {
        this.createDrinkAndFoodAndUserCRUDOperationBox(userCRUDOperationsController, viewerController, restaurantController);

        this.boxesWithLiveOrderBox = new HBox(5, drinkAndFoodAndUserCRUDButtonsBox, liveOrdersController.getAllComponentsBox());
    }

    public void createBoxWithViewerBox(UserCRUDOperationsController userCRUDOperationsController, LiveOrdersController liveOrdersController, ViewerController viewerController, RestaurantController restaurantController)
    {
        this.createBoxWithLiveOrderPane(userCRUDOperationsController, liveOrdersController, viewerController, restaurantController);

        this.biggestBox = new VBox(5, boxesWithLiveOrderBox, restaurantController.getViewerBox(), restaurantController.getToolBar());
    }

    public void createButtons()
    {
        this.addButton = new Button("ADD");
        this.addDrinkButton = new Button("ADD DRINK");
        this.addFoodButton = new Button("ADD FOOD");
        this.cancelAddOperationButton = createCancelButton();
        this.removeButton = new Button("REMOVE");
        this.removeDrinkButton = new Button("REMOVE DRINK");
        this.removeFoodButton = new Button("REMOVE FOOD");
        this.cancelRemoveOperationButton = createCancelButton();
        this.updateButton = new Button("UPDATE");
        this.updateDrinkButton = new Button("UPDATE DRINK");
        this.updateFoodButton = new Button("UPDATE FOOD");
        this.cancelUpdateOperationButton = createCancelButton();
        this.findButton = new Button("FIND");
        this.findDrinkButton = new Button("FIND DRINK");
        this.findFoodButton = new Button("FIND FOOD");
        this.cancelFindOperationButton = createCancelButton();
    }

    public VBox createAddButtonsBox()
    {
        addButtonsBox = new HBox(5, addDrinkButton, addFoodButton);
        this.finalAddButtonsBox = new VBox(5, addButtonsBox, cancelAddOperationButton);

        addDrinkButton.setPrefSize(250, 250);
        addFoodButton.setPrefSize(250, 250);
        cancelAddOperationButton.setPrefSize(505, 50);

        {
            addDrinkButton.getStyleClass().add("interface-button");
            addFoodButton.getStyleClass().add("interface-button");
            cancelAddOperationButton.getStyleClass().add("interface-button");
        }

        return finalAddButtonsBox;
    }

    public VBox createRemoveButtonsBox()
    {
        removeButtonsBox = new HBox(5, removeDrinkButton, removeFoodButton);
        this.finalRemoveButtonsBox = new VBox(5, removeButtonsBox, cancelRemoveOperationButton);

        removeDrinkButton.setPrefSize(250, 250);
        removeFoodButton.setPrefSize(250, 250);
        cancelRemoveOperationButton.setPrefSize(505, 50);

        {
            removeDrinkButton.getStyleClass().add("interface-button");
            removeFoodButton.getStyleClass().add("interface-button");
            cancelRemoveOperationButton.getStyleClass().add("interface-button");
        }

        return  finalRemoveButtonsBox;
    }

    public VBox createUpdateButtonsBox()
    {
        updateButtonsBox = new HBox(5, updateDrinkButton, updateFoodButton);
        this.finalUpdateButtonsBox = new VBox(5, updateButtonsBox, cancelUpdateOperationButton);

        updateDrinkButton.setPrefSize(250, 250);
        updateFoodButton.setPrefSize(250, 250);
        cancelUpdateOperationButton.setPrefSize(505, 50);

        {
            updateDrinkButton.getStyleClass().add("interface-button");
            updateFoodButton.getStyleClass().add("interface-button");
            cancelUpdateOperationButton.getStyleClass().add("interface-button");
        }

        return finalUpdateButtonsBox;
    }

    public VBox createFindButtonsBox()
    {
        findButtonsBox = new HBox(5, findDrinkButton, findFoodButton);
        this.finalFindButtonsBox = new VBox(5, findButtonsBox, cancelFindOperationButton);

        findDrinkButton.setPrefSize(250, 250);
        findFoodButton.setPrefSize(250, 250);
        cancelFindOperationButton.setPrefSize(505, 50);

        {
            findDrinkButton.getStyleClass().add("interface-button");
            findFoodButton.getStyleClass().add("interface-button");
            cancelFindOperationButton.getStyleClass().add("interface-button");
        }

        return finalFindButtonsBox;
    }

    public void createDrinkButtons()
    {
        beerButton.setPrefSize(250, 45);
        cocktailButton.setPrefSize(250,45);
        wineButton.setPrefSize(250, 45);
        strongDrinkButton.setPrefSize(250,45);
        coffeeButton.setPrefSize(250, 45);
        juiceButton.setPrefSize(250, 45);
        teaButton.setPrefSize(250, 45);
        softDrinkButton.setPrefSize(250, 45);

        {
            beerButton.getStyleClass().add("interface-button");
            cocktailButton.getStyleClass().add("interface-button");
            wineButton.getStyleClass().add("interface-button");
            strongDrinkButton.getStyleClass().add("interface-button");
            coffeeButton.getStyleClass().add("interface-button");
            juiceButton.getStyleClass().add("interface-button");
            teaButton.getStyleClass().add("interface-button");
            softDrinkButton.getStyleClass().add("interface-button");
        }

        this.beerButton.setOnAction(e -> {
            this.nameOfItem = "Beer";

            if (currentStage.equals("Add"))
            {
                this.addDrinkToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeDrinkFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateDrinkFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findDrinkFromMenu();
            }
        });

        this.cocktailButton.setOnAction(e -> {
            this.nameOfItem = "Cocktail";

            if (currentStage.equals("Add"))
            {
                this.addDrinkToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeDrinkFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateDrinkFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findDrinkFromMenu();
            }
        });

        this.wineButton.setOnAction(e -> {
            this.nameOfItem = "Wine";

            if (currentStage.equals("Add"))
            {
                this.addDrinkToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeDrinkFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateDrinkFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findDrinkFromMenu();
            }
        });

        this.strongDrinkButton.setOnAction(e -> {
            this.nameOfItem = "StrongDrink";

            if (currentStage.equals("Add"))
            {
                this.addDrinkToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeDrinkFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateDrinkFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findDrinkFromMenu();
            }
        });

        this.coffeeButton.setOnAction(e -> {
            this.nameOfItem = "Coffee";

            if (currentStage.equals("Add"))
            {
                this.addDrinkToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeDrinkFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateDrinkFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findDrinkFromMenu();
            }
        });

        this.juiceButton.setOnAction(e -> {
            this.nameOfItem = "Juice";

            if (currentStage.equals("Add"))
            {
                this.addDrinkToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeDrinkFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateDrinkFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findDrinkFromMenu();
            }
        });

        this.teaButton.setOnAction(e -> {
            this.nameOfItem = "Tea";

            if (currentStage.equals("Add"))
            {
                this.addDrinkToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeDrinkFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateDrinkFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findDrinkFromMenu();
            }
        });

        this.softDrinkButton.setOnAction(e -> {
            this.nameOfItem = "SoftDrink";

            if (currentStage.equals("Add"))
            {
                this.addDrinkToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeDrinkFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateDrinkFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findDrinkFromMenu();
            }
        });
    }

    public HBox createDrinkBoxes()
    {
        VBox leftDrinksBox = new VBox(5, beerButton, cocktailButton, wineButton, strongDrinkButton);
        VBox rightDrinksBox = new VBox(5, coffeeButton, juiceButton, teaButton, softDrinkButton);

        this.allDrinksBox = new HBox(5, leftDrinksBox, rightDrinksBox);

        return allDrinksBox;
    }

    public void createFoodButtons()
    {
        saladButton.setPrefSize(250, 45);
        soupButton.setPrefSize(250, 45);
        smallBiteButton.setPrefSize(250, 45);
        meatButton.setPrefSize(250, 45);
        pastaButton.setPrefSize(250, 45);
        seaFoodButton.setPrefSize(250, 45);
        pizzaButton.setPrefSize(250, 45);
        cakeButton.setPrefSize(250, 45);
        iceScreamButton.setPrefSize(250, 45);
        otherDessertButton.setPrefSize(250, 45);

        {
            saladButton.getStyleClass().add("interface-button");
            soupButton.getStyleClass().add("interface-button");
            smallBiteButton.getStyleClass().add("interface-button");
            meatButton.getStyleClass().add("interface-button");
            pastaButton.getStyleClass().add("interface-button");
            seaFoodButton.getStyleClass().add("interface-button");
            pizzaButton.getStyleClass().add("interface-button");
            cakeButton.getStyleClass().add("interface-button");
            iceScreamButton.getStyleClass().add("interface-button");
            otherDessertButton.getStyleClass().add("interface-button");
        }

        saladButton.setOnAction(e -> {
            this.nameOfItem = "Salad";

            if (currentStage.equals("Add"))
            {
                this.addFoodToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeFoodFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateFoodFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findFoodFromMenu();
            }
        });

        soupButton.setOnAction(e -> {
            this.nameOfItem = "Soup";

            if (currentStage.equals("Add"))
            {
                this.addFoodToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeFoodFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateFoodFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findFoodFromMenu();
            }
        });

        smallBiteButton.setOnAction(e -> {
            this.nameOfItem = "SmallBite";

            if (currentStage.equals("Add"))
            {
                this.addFoodToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeFoodFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateFoodFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findFoodFromMenu();
            }
        });

        meatButton.setOnAction(e -> {
            this.nameOfItem = "Meat";

            if (currentStage.equals("Add"))
            {
                this.addFoodToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeFoodFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateFoodFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findFoodFromMenu();
            }
        });

        pastaButton.setOnAction(e -> {
            this.nameOfItem = "Pasta";

            if (currentStage.equals("Add"))
            {
                this.addFoodToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeFoodFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateFoodFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findFoodFromMenu();
            }
        });

        seaFoodButton.setOnAction(e -> {
            this.nameOfItem = "SeaFood";

            if (currentStage.equals("Add"))
            {
                this.addFoodToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeFoodFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateFoodFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findFoodFromMenu();
            }
        });

        pizzaButton.setOnAction(e -> {
            this.nameOfItem = "Pizza";

            if (currentStage.equals("Add"))
            {
                this.addFoodToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeFoodFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateFoodFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findFoodFromMenu();
            }
        });

        cakeButton.setOnAction(e -> {
            this.nameOfItem = "Cake";

            if (currentStage.equals("Add"))
            {
                this.addFoodToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeFoodFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateFoodFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findFoodFromMenu();
            }
        });

        iceScreamButton.setOnAction(e -> {
            this.nameOfItem = "IceScream";

            if (currentStage.equals("Add"))
            {
                this.addFoodToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeFoodFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateFoodFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findFoodFromMenu();
            }
        });

        otherDessertButton.setOnAction(e -> {
            this.nameOfItem = "OtherDessert";

            if (currentStage.equals("Add"))
            {
                this.addFoodToMenu();
            }
            else if (currentStage.equals("Remove"))
            {
                this.removeFoodFromMenu();
            }
            else if (currentStage.equals("Update"))
            {
                this.updateFoodFromMenu();
            }
            else if (currentStage.equals("Find"))
            {
                this.findFoodFromMenu();
            }
        });
    }

    public HBox createFoodBoxes()
    {
        VBox leftFoodBox = new VBox(5, saladButton, soupButton, smallBiteButton, meatButton, pastaButton);
        VBox rightFoodBox = new VBox(5, seaFoodButton, pizzaButton, cakeButton, iceScreamButton, otherDessertButton);

        this.allFoodSBox = new HBox(5, leftFoodBox, rightFoodBox);

        return allFoodSBox;
    }

    public Button createBackButton()
    {
        this.backButton.setPrefSize(505, 50);

        {
            this.backButton.getStyleClass().add("interface-button");
        }

        this.backButton.setOnAction(e -> {
            if (currentStage.equals("Add"))
            {
                if (currentType.equals("Drink"))
                {
                    int index = finalAddButtonsBox.getChildren().indexOf(allDrinksBox);
                    int closeIndex = finalAddButtonsBox.getChildren().indexOf(backButton);

                    finalAddButtonsBox.getChildren().set(index, addButtonsBox);
                    finalAddButtonsBox.getChildren().set(closeIndex, cancelAddOperationButton);

                    finalAddButtonsBox.setSpacing(5);
                }
                else if (currentType.equals("Food"))
                {
                    int index = finalAddButtonsBox.getChildren().indexOf(allFoodSBox);
                    int closeIndex = finalAddButtonsBox.getChildren().indexOf(backButton);

                    finalAddButtonsBox.getChildren().set(index, addButtonsBox);
                    finalAddButtonsBox.getChildren().set(closeIndex, cancelAddOperationButton);
                }
            }
            else if (currentStage.equals("Remove"))
            {
                if (currentType.equals("Drink"))
                {
                    int index = finalRemoveButtonsBox.getChildren().indexOf(allDrinksBox);
                    int closeIndex = finalRemoveButtonsBox.getChildren().indexOf(backButton);

                    finalRemoveButtonsBox.getChildren().set(index, removeButtonsBox);
                    finalRemoveButtonsBox.getChildren().set(closeIndex, cancelRemoveOperationButton);

                    finalRemoveButtonsBox.setSpacing(5);
                }
                else if (currentType.equals("Food"))
                {
                    int index = finalRemoveButtonsBox.getChildren().indexOf(allFoodSBox);
                    int closeIndex = finalRemoveButtonsBox.getChildren().indexOf(backButton);

                    finalRemoveButtonsBox.getChildren().set(index, removeButtonsBox);
                    finalRemoveButtonsBox.getChildren().set(closeIndex, cancelRemoveOperationButton);
                }
            }
            else if (currentStage.equals("Update"))
            {
                if (currentType.equals("Drink"))
                {
                    int index = finalUpdateButtonsBox.getChildren().indexOf(allDrinksBox);
                    int closeIndex = finalUpdateButtonsBox.getChildren().indexOf(backButton);

                    finalUpdateButtonsBox.getChildren().set(index, updateButtonsBox);
                    finalUpdateButtonsBox.getChildren().set(closeIndex, cancelUpdateOperationButton);

                    finalUpdateButtonsBox.setSpacing(5);
                }
                else if (currentType.equals("Food"))
                {
                    int index = finalUpdateButtonsBox.getChildren().indexOf(allFoodSBox);
                    int closeIndex = finalUpdateButtonsBox.getChildren().indexOf(backButton);

                    finalUpdateButtonsBox.getChildren().set(index, updateButtonsBox);
                    finalUpdateButtonsBox.getChildren().set(closeIndex, cancelUpdateOperationButton);
                }
            }
            else if (currentStage.equals("Find"))
            {
                if (currentType.equals("Drink"))
                {
                    int index = finalFindButtonsBox.getChildren().indexOf(allDrinksBox);
                    int closeIndex = finalFindButtonsBox.getChildren().indexOf(backButton);

                    finalFindButtonsBox.getChildren().set(index, findButtonsBox);
                    finalFindButtonsBox.getChildren().set(closeIndex, cancelFindOperationButton);

                    finalFindButtonsBox.setSpacing(5);
                }
                else if (currentType.equals("Food"))
                {
                    int index = finalFindButtonsBox.getChildren().indexOf(allFoodSBox);
                    int closeIndex = finalFindButtonsBox.getChildren().indexOf(backButton);

                    finalFindButtonsBox.getChildren().set(index, findButtonsBox);
                    finalFindButtonsBox.getChildren().set(closeIndex, cancelFindOperationButton);
                }
            }
        });

        return backButton;
    }

    public void addDrinkToMenu()
    {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        Label idLabel = new Label("Id: ");
        TextField idField = new TextField();
        Label nameLabel = new Label("Name: ");
        TextField nameField = new TextField();
        Label volumeLabel = new Label("Volume: ");
        TextField volumeField = new TextField();
        Label priceLabel = new Label("Price: ");
        TextField priceField = new TextField();
        Label quantityLabel = new Label("Quantity: ");
        TextField quantityField = new TextField();

        Pane idWrapper = new Pane(idLabel);
        Pane nameWrapper = new Pane(nameLabel);
        Pane volumeWrapper = new Pane(volumeLabel);
        Pane priceWrapper = new Pane(priceLabel);
        Pane quantityWrapper = new Pane(quantityLabel);

        {
            idLabel.getStyleClass().add("drinks-label-color-v4");
            nameLabel.getStyleClass().add("drinks-label-color-v4");
            volumeLabel.getStyleClass().add("drinks-label-color-v4");
            priceLabel.getStyleClass().add("drinks-label-color-v4");
            quantityLabel.getStyleClass().add("drinks-label-color-v4");

            idWrapper.setStyle("-fx-background-color: #df00fe;");
            nameWrapper.setStyle("-fx-background-color: #df00fe;");
            volumeWrapper.setStyle("-fx-background-color: #df00fe;");
            priceWrapper.setStyle("-fx-background-color: #df00fe;");
            quantityWrapper.setStyle("-fx-background-color: #df00fe;");

            idField.getStyleClass().add("drinks-label");
            nameField.getStyleClass().add("drinks-label");
            volumeField.getStyleClass().add("drinks-label");
            priceField.getStyleClass().add("drinks-label");
            quantityField.getStyleClass().add("drinks-label");
        }

        idField.setPrefSize(250, 50);
        nameField.setPrefSize(250, 50);
        volumeField.setPrefSize(250, 50);
        priceField.setPrefSize(250, 50);
        quantityField.setPrefSize(250, 50);

        gridPane.add(idWrapper, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(nameWrapper, 0, 1);
        gridPane.add(nameField, 1, 1);
        gridPane.add(volumeWrapper, 0, 2);
        gridPane.add(volumeField, 1, 2);
        gridPane.add(priceWrapper, 0, 3);
        gridPane.add(priceField, 1, 3);
        gridPane.add(quantityWrapper, 0, 4);
        gridPane.add(quantityField, 1,4);

        Button addButton = new Button("ADD");
        Button cancelButton = new Button("CANCEL");

        addButton.setPrefSize(195, 50);
        cancelButton.setPrefSize(195, 50);

        {
            addButton.getStyleClass().add("interface-button");
            cancelButton.getStyleClass().add("interface-button");
        }

        HBox buttonsBox = new HBox(5, cancelButton, addButton);
        VBox finalBox = new VBox(5, gridPane, buttonsBox);

        Scene scene = new Scene(finalBox);
        Stage newWindow = new Stage();
        newWindow.setTitle("Add new drink");
        newWindow.setScene(scene);

        scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

        addButton.setOnAction(e -> {

            if (idField.getText().isEmpty())
            {
                idField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            }
            else if (nameField.getText().isEmpty())
            {
                nameField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                idField.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
            }
            else if (volumeField.getText().isEmpty())
            {
                volumeField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                nameField.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
            }
            else if (priceField.getText().isEmpty())
            {
                priceField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                volumeField.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
            }
            else if (quantityField.getText().isEmpty())
            {
                quantityField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                priceField.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
            }
            else
            {
                try
                {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    float volume = Float.parseFloat(volumeField.getText());
                    int price = Integer.parseInt(priceField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());

                    try
                    {
                        if (nameOfItem.equals("Beer"))
                            servicesRpcProxy.addAlcoholicDrink("Beers", AlcoholicDrinkType.BEER, id, name, volume, price, quantity);
                        else if (nameOfItem.equals("Cocktail"))
                            servicesRpcProxy.addAlcoholicDrink("Cocktails", AlcoholicDrinkType.COCKTAIL, id, name, volume, price, quantity);
                        else if (nameOfItem.equals("Wine"))
                            servicesRpcProxy.addAlcoholicDrink("Wines", AlcoholicDrinkType.WINE, id, name, volume, price, quantity);
                        else if (nameOfItem.equals("StrongDrink"))
                            servicesRpcProxy.addAlcoholicDrink("StrongDrinks", AlcoholicDrinkType.STRONG_DRINK, id, name, volume, price, quantity);
                        else if (nameOfItem.equals("Coffee"))
                            servicesRpcProxy.addNonAlcoholicDrink("Coffees", NonAlcoholicDrinkType.COFFEE, id, name, volume, price, quantity);
                        else if (nameOfItem.equals("Juice"))
                            servicesRpcProxy.addNonAlcoholicDrink("Juices", NonAlcoholicDrinkType.JUICE, id, name, volume, price, quantity);
                        else if (nameOfItem.equals("Tea"))
                            servicesRpcProxy.addNonAlcoholicDrink("Teas", NonAlcoholicDrinkType.TEA, id, name, volume, price, quantity);
                        else if (nameOfItem.equals("SoftDrink"))
                            servicesRpcProxy.addNonAlcoholicDrink("SoftDrinks", NonAlcoholicDrinkType.SOFT_DRINK, id, name, volume, price, quantity);

                    }
                    catch (ServiceException ex)
                    {
                        ex.printStackTrace();
                    }


                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ADD");
                    alert.setHeaderText(null);
                    alert.setContentText("Drink added successfully!");

                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                    Label contentLabel = (Label) dialogPane.lookup(".content");
                    if (contentLabel != null) {
                        contentLabel.getStyleClass().add("alert-content-text-v2");
                    }

                    alert.showAndWait();
                }
                catch (NumberFormatException ex)
                {
                    System.err.println("Id, volume, price, quantity must be numbers!");
                }

                newWindow.close();
            }
        });

        cancelButton.setOnAction(e -> {
            newWindow.close();
        });

        newWindow.show();
    }

    public void addFoodToMenu()
    {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Label idLabel = new Label("Id: ");
        TextField idField = new TextField();
        Label nameLabel = new Label("Name: ");
        TextField nameField = new TextField();
        Label descriptionLabel = new Label("Description: ");
        TextField descriptionField = new TextField();
        Label gramsLabel = new Label("Grams: ");
        TextField gramsField = new TextField();
        Label priceLabel = new Label("Price: ");
        TextField priceField = new TextField();
        Label ratingLabel = new Label("Rating: ");
        TextField ratingField = new TextField();
        Label quantityLabel = new Label("Quantity: ");
        TextField quantityField = new TextField();

        Pane idWrapper = new Pane(idLabel);
        Pane nameWrapper = new Pane(nameLabel);
        Pane descriptionWrapper = new Pane(descriptionLabel);
        Pane gramsWrapper = new Pane(gramsLabel);
        Pane priceWrapper = new Pane(priceLabel);
        Pane ratingWrapper = new Pane(ratingLabel);
        Pane quantityWrapper = new Pane(quantityLabel);

        idField.setPrefSize(250, 50);
        nameField.setPrefSize(250, 50);
        descriptionField.setPrefSize(250, 50);
        gramsField.setPrefSize(250, 50);
        priceField.setPrefSize(250, 50);
        ratingField.setPrefSize(250, 50);
        quantityField.setPrefSize(250, 50);

        {
            idLabel.getStyleClass().add("drinks-label-color-v4");
            nameLabel.getStyleClass().add("drinks-label-color-v4");
            descriptionLabel.getStyleClass().add("drinks-label-color-v4");
            gramsLabel.getStyleClass().add("drinks-label-color-v4");
            priceLabel.getStyleClass().add("drinks-label-color-v4");
            ratingLabel.getStyleClass().add("drinks-label-color-v4");
            quantityLabel.getStyleClass().add("drinks-label-color-v4");

            idWrapper.setStyle("-fx-background-color: #df00fe;");
            nameWrapper.setStyle("-fx-background-color: #df00fe;");
            descriptionWrapper.setStyle("-fx-background-color: #df00fe;");
            gramsWrapper.setStyle("-fx-background-color: #df00fe;");
            priceWrapper.setStyle("-fx-background-color: #df00fe;");
            ratingWrapper.setStyle("-fx-background-color: #df00fe;");
            quantityWrapper.setStyle("-fx-background-color: #df00fe;");

            idField.getStyleClass().add("drinks-label");
            nameField.getStyleClass().add("drinks-label");
            descriptionField.getStyleClass().add("drinks-label");
            gramsField.getStyleClass().add("drinks-label");
            priceField.getStyleClass().add("drinks-label");
            ratingField.getStyleClass().add("drinks-label");
            quantityField.getStyleClass().add("drinks-label");
        }

        gridPane.add(idWrapper, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(nameWrapper, 0, 1);
        gridPane.add(nameField, 1, 1);
        gridPane.add(descriptionWrapper, 0, 2);
        gridPane.add(descriptionField, 1, 2);
        gridPane.add(gramsWrapper, 0, 3);
        gridPane.add(gramsField, 1, 3);
        gridPane.add(priceWrapper, 0, 4);
        gridPane.add(priceField, 1, 4);
        gridPane.add(ratingWrapper, 0, 5);
        gridPane.add(ratingField, 1, 5);
        gridPane.add(quantityWrapper, 0, 6);
        gridPane.add(quantityField, 1, 6);

        Button addButton = new Button("ADD");
        Button cancelButton = new Button("CANCEL");

        HBox buttonsBox = new HBox(5, cancelButton, addButton);
        VBox finalBox = new VBox(5, gridPane, buttonsBox);

        addButton.setPrefSize(215, 50);
        cancelButton.setPrefSize(215, 50);

        {
            addButton.getStyleClass().add("interface-button");
            cancelButton.getStyleClass().add("interface-button");
        }

        Scene scene = new Scene(finalBox);
        Stage newWindow = new Stage();
        newWindow.setTitle("Add new food");
        newWindow.setScene(scene);

        scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

        addButton.setOnAction(e -> {

            if (idField.getText().isEmpty())
            {
                idField.setStyle("-fx-border-color: red;");
            }
            else if (nameField.getText().isEmpty())
            {
                nameField.setStyle("-fx-border-color: red;");
                idField.setStyle("-fx-border-color: black;");
            }
            else if (descriptionField.getText().isEmpty())
            {
                descriptionField.setStyle("-fx-border-color: red;");
                nameField.setStyle("-fx-border-color: black; ");
            }
            else if (gramsField.getText().isEmpty())
            {
                gramsField.setStyle("-fx-border-color: red;");
                descriptionField.setStyle("-fx-border-color: black;");
            }
            else if (priceField.getText().isEmpty())
            {
                priceField.setStyle("-fx-border-color: red;");
                descriptionField.setStyle("-fx-border-color: black;");
            }
            else if (ratingField.getText().isEmpty())
            {
                ratingField.setStyle("-fx-border-color: red;");
                priceField.setStyle("-fx-border-color: black;");
            }
            else if (quantityField.getText().isEmpty())
            {
                quantityField.setStyle("-fx-border-color: red;");
                ratingField.setStyle("-fx-border-color: black;");
            }
            else
            {

                try
                {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    int grams = Integer.parseInt(gramsField.getText());
                    int price = Integer.parseInt(priceField.getText());
                    float rating = Float.parseFloat(ratingField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());

                    try
                    {
                        if (nameOfItem.equals("Salad"))
                            servicesRpcProxy.addStarterFood("Salad", StarterFoodType.SALAD, id, name, description, grams, price, rating, quantity);
                        else if (nameOfItem.equals("Soup"))
                            servicesRpcProxy.addStarterFood("Soup", StarterFoodType.SOUP, id, name, description, grams, price, rating, quantity);
                        else if (nameOfItem.equals("SmallBite"))
                            servicesRpcProxy.addStarterFood("SmallBites", StarterFoodType.SMALL_BITES, id, name, description, grams, price, rating, quantity);
                        else if (nameOfItem.equals("Meat"))
                            servicesRpcProxy.addMainFood("Meat", MainFoodType.MEAT, id, name, description, grams, price, rating, quantity);
                        else if (nameOfItem.equals("Pasta"))
                            servicesRpcProxy.addMainFood("Pasta", MainFoodType.PASTA, id, name, description, grams, price, rating, quantity);
                        else if (nameOfItem.equals("SeaFood"))
                            servicesRpcProxy.addMainFood("SeaFood", MainFoodType.SEAFOOD, id, name, description, grams, price, rating, quantity);
                        else if (nameOfItem.equals("Pizza"))
                            servicesRpcProxy.addMainFood("Pizza", MainFoodType.PIZZA, id, name, description, grams, price, rating, quantity);
                        else if (nameOfItem.equals("Cake"))
                            servicesRpcProxy.addDessertFood("Cakes", DessertFoodType.CAKE, id, name, description, grams, price, rating, quantity);
                        else if (nameOfItem.equals("IceScream"))
                            servicesRpcProxy.addDessertFood("IceScream", DessertFoodType.ICE_SCREAM, id, name, description, grams, price, rating, quantity);
                        else if (nameOfItem.equals("OtherDessert"))
                            servicesRpcProxy.addDessertFood("OtherDesserts", DessertFoodType.OTHER_DESSERT, id, name, description, grams, price, rating, quantity);

                    }
                    catch (ServiceException ex)
                    {
                        ex.printStackTrace();
                    }


                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ADD");
                    alert.setHeaderText(null);
                    alert.setContentText("Food added successfully!");

                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                    Label contentLabel = (Label) dialogPane.lookup(".content");
                    if (contentLabel != null) {
                        contentLabel.getStyleClass().add("alert-content-text-v2");
                    }

                    alert.showAndWait();
                }
                catch (NumberFormatException ex)
                {
                    System.err.println("Id, grams, price, quantity must be numbers!");
                }

                newWindow.close();
            }
        });

        cancelButton.setOnAction(e -> {
            newWindow.close();
        });

        newWindow.show();
    }

    public void removeDrinkFromMenu()
    {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Label nameLabel = new Label("Name: ");
        TextField nameField = new TextField();

        Pane nameWrapper = new Pane(nameLabel);

        nameField.setPrefSize(250, 50);

        {
            nameLabel.getStyleClass().add("drinks-label-color-v4");
            nameWrapper.setStyle("-fx-background-color: #df00fe;");

            nameField.getStyleClass().add("drinks-label");
        }

        gridPane.add(nameWrapper, 0, 0);
        gridPane.add(nameField, 1, 0);

        Button removeButton = new Button("REMOVE");
        Button cancelButton = new Button("CANCEL");

        HBox buttonsBox = new HBox(5, cancelButton, removeButton);
        VBox finalBox = new VBox(5, gridPane, buttonsBox);

        removeButton.setPrefSize(175, 50);
        cancelButton.setPrefSize(175, 50);

        {
            removeButton.getStyleClass().add("interface-button");
            cancelButton.getStyleClass().add("interface-button");
        }

        Scene scene = new Scene(finalBox);
        Stage newWindow = new Stage();
        newWindow.setTitle("Remove drink");
        newWindow.setScene(scene);

        scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

        removeButton.setOnAction(e -> {

            if (nameField.getText().isEmpty())
            {
                nameField.setStyle("-fx-border-color: red;");
            }
            else
            {
                String name = nameField.getText();

                try
                {
                    if (nameOfItem.equals("Beer"))
                    {
                        drinkExists = false;

                        List<Drink> beers = servicesRpcProxy.getDrinkDataBase().loadDrinks("Beers");
                        for (int i = 0; i < beers.size(); i++) {
                            if (beers.get(i).getName().equals(name)) {
                                drinkExists = true;
                                break;
                            }
                        }

                        if (drinkExists)
                        {
                            servicesRpcProxy.removeAlcoholicDrink("Beers", AlcoholicDrinkType.BEER, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Drink removed successfully!");

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
                            alert.setContentText("This drink does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("Cocktail"))
                    {
                        drinkExists = false;

                        List<Drink> cocktails = servicesRpcProxy.getDrinkDataBase().loadDrinks("Cocktails");

                        for (int i = 0; i < cocktails.size(); ++i)
                        {
                            if (cocktails.get(i).getName().equals(name))
                            {
                                drinkExists = true;
                                break;
                            }
                        }

                        if (drinkExists)
                        {
                            servicesRpcProxy.removeAlcoholicDrink("Cocktails", AlcoholicDrinkType.COCKTAIL, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Drink removed successfully!");

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
                            alert.setContentText("This drink does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("Wine"))
                    {
                        drinkExists = false;

                        List<Drink> wines = servicesRpcProxy.getDrinkDataBase().loadDrinks("Wines");

                        for (int i = 0; i < wines.size(); ++i)
                        {
                            if (wines.get(i).getName().equals(name))
                            {
                                drinkExists = true;
                                break;
                            }
                        }

                        if (drinkExists)
                        {
                            servicesRpcProxy.removeAlcoholicDrink("Wines", AlcoholicDrinkType.WINE, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Drink removed successfully!");

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
                            alert.setContentText("This drink does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("StrongDrink"))
                    {
                        drinkExists = false;

                        List<Drink> strongDrinks = servicesRpcProxy.getDrinkDataBase().loadDrinks("StrongDrinks");

                        for (int i = 0; i < strongDrinks.size(); ++i)
                        {
                            if (strongDrinks.get(i).getName().equals(name))
                            {
                                drinkExists = true;
                                break;
                            }
                        }

                        if (drinkExists)
                        {
                            servicesRpcProxy.removeAlcoholicDrink("StrongDrinks", AlcoholicDrinkType.STRONG_DRINK, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Drink removed successfully!");

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
                            alert.setContentText("This drink does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("Coffee"))
                    {
                        drinkExists = false;

                        List<Drink> coffees = servicesRpcProxy.getDrinkDataBase().loadDrinks("Coffees");

                        for (int i = 0; i < coffees.size(); ++i)
                        {
                            if (coffees.get(i).getName().equals(name))
                            {
                                drinkExists = true;
                                break;
                            }
                        }

                        if (drinkExists)
                        {
                            servicesRpcProxy.removeNonAlcoholicDrink("Coffees", NonAlcoholicDrinkType.COFFEE, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Drink removed successfully!");

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
                            alert.setContentText("This drink does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("Juice"))
                    {
                        drinkExists = false;

                        List<Drink> juices = servicesRpcProxy.getDrinkDataBase().loadDrinks("Juices");

                        for (int i = 0; i < juices.size(); ++i)
                        {
                            if (juices.get(i).getName().equals(name))
                            {
                                drinkExists = true;
                                break;
                            }
                        }

                        if (drinkExists)
                        {
                            servicesRpcProxy.removeNonAlcoholicDrink("Juices", NonAlcoholicDrinkType.JUICE, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Drink removed successfully!");

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
                            alert.setContentText("This drink does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("Tea"))
                    {
                        drinkExists = false;

                        List<Drink> teas = servicesRpcProxy.getDrinkDataBase().loadDrinks("Teas");

                        for (int i = 0; i < teas.size(); ++i)
                        {
                            if (teas.get(i).getName().equals(name))
                            {
                                drinkExists = true;
                                break;
                            }
                        }

                        if (drinkExists)
                        {
                            servicesRpcProxy.removeNonAlcoholicDrink("Teas", NonAlcoholicDrinkType.TEA, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Drink removed successfully!");

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
                            alert.setContentText("This drink does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("SoftDrink"))
                    {
                        drinkExists = false;

                        List<Drink> softDrinks = servicesRpcProxy.getDrinkDataBase().loadDrinks("SoftDrinks");

                        for (int i = 0; i < softDrinks.size(); ++i)
                        {
                            if (softDrinks.get(i).getName().equals(name))
                            {
                                drinkExists = true;
                                break;
                            }
                        }

                        if (drinkExists)
                        {
                            servicesRpcProxy.removeNonAlcoholicDrink("SoftDrinks", NonAlcoholicDrinkType.SOFT_DRINK, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Drink removed successfully!");

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
                            alert.setContentText("This drink does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                }
                catch (ServiceException ex)
                {
                    ex.printStackTrace();
                }



                newWindow.close();
            }
        });

        cancelButton.setOnAction(e -> {
            newWindow.close();
        });

        newWindow.show();
    }

    public void removeFoodFromMenu()
    {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Label nameLabel = new Label("Name: ");
        TextField nameField = new TextField();

        Pane nameWrapper = new Pane(nameLabel);

        nameField.setPrefSize(250, 50);

        {
            nameLabel.getStyleClass().add("drinks-label-color-v4");
            nameWrapper.setStyle("-fx-background-color: #df00fe;");

            nameField.getStyleClass().add("drinks-label");
        }

        gridPane.add(nameWrapper, 0, 0);
        gridPane.add(nameField, 1, 0);

        Button removeButton = new Button("REMOVE");
        Button cancelButton = new Button("CANCEL");

        HBox buttonsBox = new HBox(5, cancelButton, removeButton);
        VBox finalBox = new VBox(5, gridPane, buttonsBox);

        removeButton.setPrefSize(175, 50);
        cancelButton.setPrefSize(175, 50);

        {
            removeButton.getStyleClass().add("interface-button");
            cancelButton.getStyleClass().add("interface-button");
        }

        Scene scene = new Scene(finalBox);
        Stage newWindow = new Stage();
        newWindow.setTitle("Remove food");
        newWindow.setScene(scene);

        scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

        removeButton.setOnAction(e -> {

            if (nameField.getText().isEmpty())
            {
                nameField.setStyle("-fx-border-color: red;");
            }
            else
            {
                String name = nameField.getText();

                try
                {
                    if (nameOfItem.equals("Salad"))
                    {
                        foodExists = false;

                        List<Food> salads = servicesRpcProxy.getFoodDataBase().loadFoods("Salad");

                        for (int i = 0; i < salads.size(); ++i)
                        {
                            if (salads.get(i).getName().equals(name))
                            {
                                foodExists = true;
                                break;
                            }
                        }

                        if (foodExists)
                        {
                            servicesRpcProxy.removeStarterFood("Salad", StarterFoodType.SALAD, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Food removed successfully!");

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
                            alert.setContentText("This food does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("Soup"))
                    {
                        foodExists = false;

                        List<Food> soups = servicesRpcProxy.getFoodDataBase().loadFoods("Soup");

                        for (int i = 0; i < soups.size(); ++i)
                        {
                            if (soups.get(i).getName().equals(name))
                            {
                                foodExists = true;
                                break;
                            }
                        }

                        if (foodExists)
                        {
                            servicesRpcProxy.removeStarterFood("Soup", StarterFoodType.SOUP, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Food removed successfully!");

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
                            alert.setContentText("This food does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("SmallBite"))
                    {
                        foodExists = false;

                        List<Food> smallBites = servicesRpcProxy.getFoodDataBase().loadFoods("SmallBites");

                        for (int i = 0; i < smallBites.size(); ++i)
                        {
                            if (smallBites.get(i).getName().equals(name))
                            {
                                foodExists = true;
                                break;
                            }
                        }

                        if (foodExists)
                        {
                            servicesRpcProxy.removeStarterFood("SmallBites", StarterFoodType.SMALL_BITES, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Food removed successfully!");

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
                            alert.setContentText("This food does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("Meat"))
                    {
                        foodExists = false;

                        List<Food> meat = servicesRpcProxy.getFoodDataBase().loadFoods("Meat");

                        for (int i = 0; i < meat.size(); ++i)
                        {
                            if (meat.get(i).getName().equals(name))
                            {
                                foodExists = true;
                                break;
                            }
                        }

                        if (foodExists)
                        {
                            servicesRpcProxy.removeMainFood("Meat", MainFoodType.MEAT, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Food removed successfully!");

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
                            alert.setContentText("This food does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("Pasta"))
                    {
                        foodExists = false;

                        List<Food> pasta = servicesRpcProxy.getFoodDataBase().loadFoods("Pasta");

                        for (int i = 0; i < pasta.size(); ++i)
                        {
                            if (pasta.get(i).getName().equals(name))
                            {
                                foodExists = true;
                                break;
                            }
                        }

                        if (foodExists)
                        {
                            servicesRpcProxy.removeMainFood("Pasta", MainFoodType.PASTA, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Food removed successfully!");

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
                            alert.setContentText("This food does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("SeaFood"))
                    {
                        foodExists = false;

                        List<Food> seafood = servicesRpcProxy.getFoodDataBase().loadFoods("SeaFood");

                        for (int i = 0; i < seafood.size(); ++i)
                        {
                            if (seafood.get(i).getName().equals(name))
                            {
                                foodExists = true;
                                break;
                            }
                        }

                        if (foodExists)
                        {
                            servicesRpcProxy.removeMainFood("SeaFood", MainFoodType.SEAFOOD, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Food removed successfully!");

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
                            alert.setContentText("This food does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("Pizza"))
                    {
                        foodExists = false;

                        List<Food> pizza = servicesRpcProxy.getFoodDataBase().loadFoods("Pizza");

                        for (int i = 0; i < pizza.size(); ++i)
                        {
                            if (pizza.get(i).getName().equals(name))
                            {
                                foodExists = true;
                                break;
                            }
                        }

                        if (foodExists)
                        {
                            servicesRpcProxy.removeMainFood("Pizza", MainFoodType.PIZZA, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Food removed successfully!");

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
                            alert.setContentText("This food does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("Cake"))
                    {
                        foodExists = false;

                        List<Food> cakes = servicesRpcProxy.getFoodDataBase().loadFoods("Cakes");

                        for (int i = 0; i < cakes.size(); ++i)
                        {
                            if (cakes.get(i).getName().equals(name))
                            {
                                foodExists = true;
                                break;
                            }
                        }

                        if (foodExists)
                        {
                            servicesRpcProxy.removeDessertFood("Cakes", DessertFoodType.CAKE, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Food removed successfully!");

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
                            alert.setContentText("This food does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("IceScream"))
                    {
                        foodExists = false;

                        List<Food> iceScream = servicesRpcProxy.getFoodDataBase().loadFoods("IceScream");

                        for (int i = 0; i < iceScream.size(); ++i)
                        {
                            if (iceScream.get(i).getName().equals(name))
                            {
                                foodExists = true;
                                break;
                            }
                        }

                        if (foodExists)
                        {
                            servicesRpcProxy.removeDessertFood("IceScream", DessertFoodType.ICE_SCREAM, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Food removed successfully!");

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
                            alert.setContentText("This food does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                    else if (nameOfItem.equals("OtherDessert"))
                    {
                        foodExists = false;

                        List<Food> otherDesserts = servicesRpcProxy.getFoodDataBase().loadFoods("OtherDesserts");

                        for (int i = 0; i < otherDesserts.size(); ++i)
                        {
                            if (otherDesserts.get(i).getName().equals(name))
                            {
                                foodExists = true;
                                break;
                            }
                        }

                        if (foodExists)
                        {
                            servicesRpcProxy.removeDessertFood("OtherDesserts", DessertFoodType.OTHER_DESSERT, name);

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("REMOVE");
                            alert.setHeaderText(null);
                            alert.setContentText("Food removed successfully!");

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
                            alert.setContentText("This food does not exists!");

                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                            Label contentLabel = (Label) dialogPane.lookup(".content");
                            if (contentLabel != null) {
                                contentLabel.getStyleClass().add("alert-content-text");
                            }

                            alert.showAndWait();
                        }
                    }
                }
                catch (ServiceException ex)
                {
                    ex.printStackTrace();
                }


                newWindow.close();
            }

        });

        cancelButton.setOnAction(e -> {
            newWindow.close();
        });

        newWindow.show();
    }

    public void updateDrinkFromMenu()
    {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        Label oldNameLabel = new Label("Old name: ");
        TextField oldNameField = new TextField();
        Label idLabel = new Label("New id: ");
        TextField idField = new TextField();
        Label nameLabel = new Label("New name: ");
        TextField nameField = new TextField();
        Label volumeLabel = new Label("New volume: ");
        TextField volumeField = new TextField();
        Label priceLabel = new Label("New price: ");
        TextField priceField = new TextField();
        Label quantityLabel = new Label("New quantity: ");
        TextField quantityField = new TextField();

        Pane oldNameWrapper = new Pane(oldNameLabel);
        Pane idWrapper = new Pane(idLabel);
        Pane nameWrapper = new Pane(nameLabel);
        Pane volumeWrapper = new Pane(volumeLabel);
        Pane priceWrapper = new Pane(priceLabel);
        Pane quantityWrapper = new Pane(quantityLabel);

        oldNameField.setPrefSize(250, 50);
        idField.setPrefSize(250, 50);
        nameField.setPrefSize(250, 50);
        volumeField.setPrefSize(250, 50);
        priceField.setPrefSize(250, 50);
        quantityField.setPrefSize(250, 50);

        {
            oldNameLabel.getStyleClass().add("drinks-label-color-v4");
            idLabel.getStyleClass().add("drinks-label-color-v4");
            nameLabel.getStyleClass().add("drinks-label-color-v4");
            volumeLabel.getStyleClass().add("drinks-label-color-v4");
            priceLabel.getStyleClass().add("drinks-label-color-v4");
            quantityLabel.getStyleClass().add("drinks-label-color-v4");

            oldNameWrapper.setStyle("-fx-background-color: #df00fe;");
            idWrapper.setStyle("-fx-background-color: #df00fe;");
            nameWrapper.setStyle("-fx-background-color: #df00fe;");
            volumeWrapper.setStyle("-fx-background-color: #df00fe;");
            priceWrapper.setStyle("-fx-background-color: #df00fe;");
            quantityWrapper.setStyle("-fx-background-color: #df00fe;");

            oldNameField.getStyleClass().add("drinks-label");
            idField.getStyleClass().add("drinks-label");
            nameField.getStyleClass().add("drinks-label");
            volumeField.getStyleClass().add("drinks-label");
            priceField.getStyleClass().add("drinks-label");
            quantityField.getStyleClass().add("drinks-label");
        }

        gridPane.add(oldNameWrapper, 0, 0);
        gridPane.add(oldNameField, 1, 0);
        gridPane.add(idWrapper, 0, 1);
        gridPane.add(idField, 1, 1);
        gridPane.add(nameWrapper, 0, 2);
        gridPane.add(nameField, 1, 2);
        gridPane.add(volumeWrapper, 0, 3);
        gridPane.add(volumeField, 1, 3);
        gridPane.add(priceWrapper, 0, 4);
        gridPane.add(priceField, 1, 4);
        gridPane.add(quantityWrapper, 0, 5);
        gridPane.add(quantityField, 1,5);

        Button updateButton = new Button("UPDATE");
        Button cancelButton = new Button("CANCEL");

        HBox buttonsBox = new HBox(5, cancelButton, updateButton);
        VBox finalBox = new VBox(5, gridPane, buttonsBox);

        updateButton.setPrefSize(229, 50);
        cancelButton.setPrefSize(229, 50);

        {
            updateButton.getStyleClass().add("interface-button");
            cancelButton.getStyleClass().add("interface-button");
        }

        Scene scene = new Scene(finalBox);
        Stage newWindow = new Stage();
        newWindow.setTitle("Update drink");
        newWindow.setScene(scene);

        scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

        updateButton.setOnAction(e -> {

            if (oldNameField.getText().isEmpty())
            {
                oldNameField.setStyle("-fx-border-color: red;");
            }
            else if (idField.getText().isEmpty())
            {
                idField.setStyle("-fx-border-color: red;");
                oldNameField.setStyle("-fx-border-color: black;");
            }
            else if (nameField.getText().isEmpty())
            {
                nameField.setStyle("-fx-border-color: red;");
                idField.setStyle("-fx-border-color: black; ");
            }
            else if (volumeField.getText().isEmpty())
            {
                volumeField.setStyle("-fx-border-color: red;");
                nameField.setStyle("-fx-border-color: black;");
            }
            else if (priceField.getText().isEmpty())
            {
                priceField.setStyle("-fx-border-color: red;");
                volumeField.setStyle("-fx-border-color: black;");
            }
            else if (quantityField.getText().isEmpty())
            {
                quantityField.setStyle("-fx-border-color: red;");
                priceField.setStyle("-fx-border-color: black;");
            }
            else
            {
                try {

                    String oldName = oldNameField.getText();

                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    float volume = Float.parseFloat(volumeField.getText());
                    int price = Integer.parseInt(priceField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());

                    Drink new_drink = new Drink(id, name, volume, price, quantity);

                    try {
                        if (nameOfItem.equals("Beer")) {
                            drinkExists = false;

                            List<Drink> beers = servicesRpcProxy.getDrinkDataBase().loadDrinks("Beers");

                            for (int i = 0; i < beers.size(); ++i) {
                                if (beers.get(i).getName().equals(oldName)) {
                                    drinkExists = true;
                                    break;
                                }
                            }

                            if (drinkExists) {
                                servicesRpcProxy.getDrinkDataBase().updateDrinkFromDataBase("Beers", oldName, new_drink);

                                servicesRpcProxy.updateAlcoholicDrink("Beers", AlcoholicDrinkType.BEER, oldName, id, name, volume, price, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Drink updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This drink does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        } else if (nameOfItem.equals("Cocktail")) {
                            drinkExists = false;

                            List<Drink> cocktails = servicesRpcProxy.getDrinkDataBase().loadDrinks("Cocktails");

                            for (int i = 0; i < cocktails.size(); ++i) {
                                if (cocktails.get(i).getName().equals(oldName)) {
                                    drinkExists = true;
                                    break;
                                }
                            }

                            if (drinkExists) {
                                servicesRpcProxy.getDrinkDataBase().updateDrinkFromDataBase("Cocktails", oldName, new_drink);

                                servicesRpcProxy.updateAlcoholicDrink("Cocktails", AlcoholicDrinkType.COCKTAIL, oldName, id, name, volume, price, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Drink updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This drink does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        } else if (nameOfItem.equals("Wine")) {
                            drinkExists = false;

                            List<Drink> wines = servicesRpcProxy.getDrinkDataBase().loadDrinks("Wines");

                            for (int i = 0; i < wines.size(); ++i) {
                                if (wines.get(i).getName().equals(oldName)) {
                                    drinkExists = true;
                                    break;
                                }
                            }

                            if (drinkExists) {
                                servicesRpcProxy.getDrinkDataBase().updateDrinkFromDataBase("Wines", oldName, new_drink);

                                servicesRpcProxy.updateAlcoholicDrink("Wines", AlcoholicDrinkType.WINE, oldName, id, name, volume, price, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Drink updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This drink does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        } else if (nameOfItem.equals("StrongDrink")) {
                            drinkExists = false;

                            List<Drink> strongDrinks = servicesRpcProxy.getDrinkDataBase().loadDrinks("StrongDrinks");

                            for (int i = 0; i < strongDrinks.size(); ++i) {
                                if (strongDrinks.get(i).getName().equals(oldName)) {
                                    drinkExists = true;
                                    break;
                                }
                            }

                            if (drinkExists) {
                                servicesRpcProxy.getDrinkDataBase().updateDrinkFromDataBase("StrongDrinks", oldName, new_drink);

                                servicesRpcProxy.updateAlcoholicDrink("StrongDrinks", AlcoholicDrinkType.STRONG_DRINK, oldName, id, name, volume, price, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Drink updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This drink does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        } else if (nameOfItem.equals("Coffee")) {
                            drinkExists = false;

                            List<Drink> coffees = servicesRpcProxy.getDrinkDataBase().loadDrinks("Coffees");

                            for (int i = 0; i < coffees.size(); ++i) {
                                if (coffees.get(i).getName().equals(oldName)) {
                                    drinkExists = true;
                                    break;
                                }
                            }

                            if (drinkExists) {
                                servicesRpcProxy.getDrinkDataBase().updateDrinkFromDataBase("Coffees", oldName, new_drink);

                                servicesRpcProxy.updateNonAlcoholicDrink("Coffees", NonAlcoholicDrinkType.COFFEE, oldName, id, name, volume, price, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Drink updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This drink does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        } else if (nameOfItem.equals("Juice")) {
                            drinkExists = false;

                            List<Drink> juices = servicesRpcProxy.getDrinkDataBase().loadDrinks("Juices");

                            for (int i = 0; i < juices.size(); ++i) {
                                if (juices.get(i).getName().equals(oldName)) {
                                    drinkExists = true;
                                    break;
                                }
                            }

                            if (drinkExists) {
                                servicesRpcProxy.getDrinkDataBase().updateDrinkFromDataBase("Juices", oldName, new_drink);

                                servicesRpcProxy.updateNonAlcoholicDrink("Juices", NonAlcoholicDrinkType.JUICE, oldName, id, name, volume, price, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Drink updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This drink does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        } else if (nameOfItem.equals("Tea")) {
                            drinkExists = false;

                            List<Drink> teas = servicesRpcProxy.getDrinkDataBase().loadDrinks("Teas");

                            for (int i = 0; i < teas.size(); ++i) {
                                if (teas.get(i).getName().equals(oldName)) {
                                    drinkExists = true;
                                    break;
                                }
                            }

                            if (drinkExists) {
                                servicesRpcProxy.getDrinkDataBase().updateDrinkFromDataBase("Teas", oldName, new_drink);

                                servicesRpcProxy.updateNonAlcoholicDrink("Teas", NonAlcoholicDrinkType.TEA, oldName, id, name, volume, price, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Drink updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This drink does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }

                            for (Drink drink : servicesRpcProxy.getTeas())
                                if (oldName.equals(drink.getName()))
                                    servicesRpcProxy.updateNonAlcoholicDrink("Teas", NonAlcoholicDrinkType.TEA, oldName, id, name, volume, price, quantity);
                        } else if (nameOfItem.equals("SoftDrink")) {
                            drinkExists = false;

                            List<Drink> softDrinks = servicesRpcProxy.getDrinkDataBase().loadDrinks("SoftDrinks");

                            for (int i = 0; i < softDrinks.size(); ++i) {
                                if (softDrinks.get(i).getName().equals(oldName)) {
                                    drinkExists = true;
                                }
                            }

                            if (drinkExists) {
                                servicesRpcProxy.getDrinkDataBase().updateDrinkFromDataBase("SoftDrinks", oldName, new_drink);

                                servicesRpcProxy.updateNonAlcoholicDrink("SoftDrinks", NonAlcoholicDrinkType.SOFT_DRINK, oldName, id, name, volume, price, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Drink updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This drink does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        }
                    } catch (ServiceException ex) {
                        ex.printStackTrace();
                    }
                }
                catch (NumberFormatException ex)
                {
                    System.err.println("Id, volume, price, quantity must be numbers!");
                }

                newWindow.close();
            }
        });

        cancelButton.setOnAction(e -> {
            newWindow.close();
        });

        newWindow.show();
    }

    public void updateFoodFromMenu()
    {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Label oldNameLabel = new Label("Old name: ");
        TextField oldNameFiled = new TextField();
        Label idLabel = new Label("New id: ");
        TextField idField = new TextField();
        Label nameLabel = new Label("New name: ");
        TextField nameField = new TextField();
        Label descriptionLabel = new Label("New description: ");
        TextField descriptionField = new TextField();
        Label gramsLabel = new Label("New grams: ");
        TextField gramsField = new TextField();
        Label priceLabel = new Label("New price: ");
        TextField priceField = new TextField();
        Label ratingLabel = new Label("New rating: ");
        TextField ratingField = new TextField();
        Label quantityLabel = new Label("New quantity: ");
        TextField quantityField = new TextField();

        Pane oldNameWrapper = new Pane(oldNameLabel);
        Pane idWrapper = new Pane(idLabel);
        Pane nameWrapper = new Pane(nameLabel);
        Pane descriptionWrapper = new Pane(descriptionLabel);
        Pane gramsWrapper = new Pane(gramsLabel);
        Pane priceWrapper = new Pane(priceLabel);
        Pane ratingWrapper = new Pane(ratingLabel);
        Pane quantityWrapper = new Pane(quantityLabel);

        oldNameFiled.setPrefSize(250, 50);
        idField.setPrefSize(250, 50);
        nameField.setPrefSize(250, 50);
        descriptionField.setPrefSize(250, 50);
        gramsField.setPrefSize(250, 50);
        priceField.setPrefSize(250, 50);
        ratingField.setPrefSize(250, 50);
        quantityField.setPrefSize(250, 50);

        {
            oldNameLabel.getStyleClass().add("drinks-label-color-v4");
            idLabel.getStyleClass().add("drinks-label-color-v4");
            nameLabel.getStyleClass().add("drinks-label-color-v4");
            descriptionLabel.getStyleClass().add("drinks-label-color-v4");
            gramsLabel.getStyleClass().add("drinks-label-color-v4");
            priceLabel.getStyleClass().add("drinks-label-color-v4");
            ratingLabel.getStyleClass().add("drinks-label-color-v4");
            quantityLabel.getStyleClass().add("drinks-label-color-v4");

            oldNameWrapper.setStyle("-fx-background-color: #df00fe;");
            idWrapper.setStyle("-fx-background-color: #df00fe;");
            nameWrapper.setStyle("-fx-background-color: #df00fe;");
            descriptionWrapper.setStyle("-fx-background-color: #df00fe;");
            gramsWrapper.setStyle("-fx-background-color: #df00fe;");
            priceWrapper.setStyle("-fx-background-color: #df00fe;");
            ratingWrapper.setStyle("-fx-background-color: #df00fe;");
            quantityWrapper.setStyle("-fx-background-color: #df00fe;");

            oldNameFiled.getStyleClass().add("drinks-label");
            idField.getStyleClass().add("drinks-label");
            nameField.getStyleClass().add("drinks-label");
            descriptionField.getStyleClass().add("drinks-label");
            gramsField.getStyleClass().add("drinks-label");
            priceField.getStyleClass().add("drinks-label");
            ratingField.getStyleClass().add("drinks-label");
            quantityField.getStyleClass().add("drinks-label");

        }

        gridPane.add(oldNameWrapper, 0, 0);
        gridPane.add(oldNameFiled, 1, 0);
        gridPane.add(idWrapper, 0, 1);
        gridPane.add(idField, 1, 1);
        gridPane.add(nameWrapper, 0, 2);
        gridPane.add(nameField, 1, 2);
        gridPane.add(descriptionWrapper, 0, 3);
        gridPane.add(descriptionField, 1, 3);
        gridPane.add(gramsWrapper, 0, 4);
        gridPane.add(gramsField, 1, 4);
        gridPane.add(priceWrapper, 0, 5);
        gridPane.add(priceField, 1, 5);
        gridPane.add(ratingWrapper, 0, 6);
        gridPane.add(ratingField, 1, 6);
        gridPane.add(quantityWrapper, 0, 7);
        gridPane.add(quantityField, 1, 7);

        Button updateButton = new Button("UPDATE");
        Button cancelButton = new Button("CANCEL");

        updateButton.setPrefSize(249, 50);
        cancelButton.setPrefSize(249, 50);

        {
            updateButton.getStyleClass().add("interface-button");
            cancelButton.getStyleClass().add("interface-button");
        }

        HBox buttonsBox = new HBox(5, cancelButton, updateButton);
        VBox finalBox = new VBox(5, gridPane, buttonsBox);

        Scene scene = new Scene(finalBox);
        Stage newWindow = new Stage();
        newWindow.setTitle("Update food");
        newWindow.setScene(scene);

        scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

        updateButton.setOnAction(e -> {

            if (oldNameFiled.getText().isEmpty())
            {
                oldNameFiled.setStyle("-fx-border-color: red;");
            }
            else if (idField.getText().isEmpty())
            {
                idField.setStyle("-fx-border-color: red;");
                oldNameFiled.setStyle("-fx-border-color: black;");
            }
            else if (nameField.getText().isEmpty())
            {
                nameField.setStyle("-fx-border-color: red;");
                idField.setStyle("-fx-border-color: black; ");
            }
            else if (descriptionField.getText().isEmpty())
            {
                descriptionField.setStyle("-fx-border-color: red;");
                nameField.setStyle("-fx-border-color: black;");
            }
            else if (gramsField.getText().isEmpty())
            {
                gramsField.setStyle("-fx-border-color: red;");
                descriptionField.setStyle("-fx-border-color: black;");
            }
            else if (priceField.getText().isEmpty())
            {
                priceField.setStyle("-fx-border-color: red;");
                gramsField.setStyle("-fx-border-color: black;");
            }
            else if (ratingField.getText().isEmpty())
            {
                ratingField.setStyle("-fx-border-color: red;");
                priceField.setStyle("-fx-border-color: black;");
            }
            else if (quantityField.getText().isEmpty())
            {
                quantityField.setStyle("-fx-border-color: red;");
                ratingField.setStyle("-fx-border-color: black;");
            }
            else
            {
                try {


                    String oldName = oldNameFiled.getText();
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    int grams = Integer.parseInt(gramsField.getText());
                    int price = Integer.parseInt(priceField.getText());
                    float rating = Float.parseFloat(ratingField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());

                    try {
                        if (nameOfItem.equals("Salad")) {
                            foodExists = false;

                            List<Food> salads = servicesRpcProxy.getFoodDataBase().loadFoods("Salad");

                            for (int i = 0; i < salads.size(); ++i) {
                                if (salads.get(i).getName().equals(oldName)) {
                                    foodExists = true;
                                    break;
                                }
                            }

                            if (foodExists) {
                                for (Food food : salads)
                                    if (oldName.equals(food.getName()))
                                        servicesRpcProxy.updateStarterFood("Salad", StarterFoodType.SALAD, oldName, id, name, description, grams, price, rating, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Food updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This food does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        } else if (nameOfItem.equals("Soup")) {
                            foodExists = false;

                            List<Food> soups = servicesRpcProxy.getFoodDataBase().loadFoods("Soup");

                            for (int i = 0; i < soups.size(); ++i) {
                                if (soups.get(i).getName().equals(oldName)) {
                                    foodExists = true;
                                    break;
                                }
                            }

                            if (foodExists) {
                                for (Food food : soups)
                                    if (oldName.equals(food.getName()))
                                        servicesRpcProxy.updateStarterFood("Soup", StarterFoodType.SOUP, oldName, id, name, description, grams, price, rating, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Food updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This food does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        } else if (nameOfItem.equals("SmallBite")) {
                            foodExists = false;

                            List<Food> smallBites = servicesRpcProxy.getFoodDataBase().loadFoods("SmallBites");

                            for (int i = 0; i < smallBites.size(); ++i) {
                                if (smallBites.get(i).getName().equals(oldName)) {
                                    foodExists = true;
                                    break;
                                }
                            }

                            if (foodExists) {
                                for (Food food : smallBites)
                                    if (oldName.equals(food.getName()))
                                        servicesRpcProxy.updateStarterFood("SmallBites", StarterFoodType.SMALL_BITES, oldName, id, name, description, grams, price, rating, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Food updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This food does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        } else if (nameOfItem.equals("Meat")) {
                            foodExists = false;

                            List<Food> meat = servicesRpcProxy.getFoodDataBase().loadFoods("Meat");

                            for (int i = 0; i < meat.size(); ++i) {
                                if (meat.get(i).getName().equals(oldName)) {
                                    foodExists = true;
                                    break;
                                }
                            }

                            if (foodExists) {
                                for (Food food : meat)
                                    if (oldName.equals(food.getName()))
                                        servicesRpcProxy.updateMainFood("Meat", MainFoodType.MEAT, oldName, id, name, description, grams, price, rating, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Food updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This food does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        } else if (nameOfItem.equals("Pasta")) {
                            foodExists = false;

                            List<Food> pasta = servicesRpcProxy.getFoodDataBase().loadFoods("Pasta");

                            for (int i = 0; i < pasta.size(); ++i) {
                                if (pasta.get(i).getName().equals(oldName)) {
                                    foodExists = true;
                                    break;
                                }
                            }

                            if (foodExists) {
                                for (Food food : pasta)
                                    if (oldName.equals(food.getName()))
                                        servicesRpcProxy.updateMainFood("Pasta", MainFoodType.PASTA, oldName, id, name, description, grams, price, rating, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Food updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This food does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        } else if (nameOfItem.equals("SeaFood")) {
                            foodExists = false;

                            List<Food> seafood = servicesRpcProxy.getFoodDataBase().loadFoods("SeaFood");

                            for (int i = 0; i < seafood.size(); ++i) {
                                if (seafood.get(i).getName().equals(oldName)) {
                                    foodExists = true;
                                    break;
                                }
                            }

                            if (foodExists) {
                                for (Food food : seafood)
                                    if (oldName.equals(food.getName()))
                                        servicesRpcProxy.updateMainFood("SeaFood", MainFoodType.SEAFOOD, oldName, id, name, description, grams, price, rating, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Food updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This food does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        } else if (nameOfItem.equals("Pizza")) {
                            foodExists = false;

                            List<Food> pizza = servicesRpcProxy.getFoodDataBase().loadFoods("Pizza");

                            for (int i = 0; i < pizza.size(); ++i) {
                                if (pizza.get(i).getName().equals(oldName)) {
                                    foodExists = true;
                                    break;
                                }
                            }

                            if (foodExists) {
                                for (Food food : pizza)
                                    if (oldName.equals(food.getName()))
                                        servicesRpcProxy.updateMainFood("Pizza", MainFoodType.PIZZA, oldName, id, name, description, grams, price, rating, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Food updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This food does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        } else if (nameOfItem.equals("Cake")) {
                            foodExists = false;

                            List<Food> cakes = servicesRpcProxy.getFoodDataBase().loadFoods("Cakes");

                            for (int i = 0; i < cakes.size(); ++i) {
                                if (cakes.get(i).getName().equals(oldName)) {
                                    foodExists = true;
                                    break;
                                }
                            }

                            if (foodExists) {
                                for (Food food : cakes)
                                    if (oldName.equals(food.getName()))
                                        servicesRpcProxy.updateDessertFood("Cakes", DessertFoodType.CAKE, oldName, id, name, description, grams, price, rating, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Food updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This food does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        } else if (nameOfItem.equals("IceScream")) {
                            foodExists = false;

                            List<Food> iceScream = servicesRpcProxy.getFoodDataBase().loadFoods("IceScream");

                            for (int i = 0; i < iceScream.size(); ++i) {
                                if (iceScream.get(i).getName().equals(oldName)) {
                                    foodExists = true;
                                    break;
                                }
                            }

                            if (foodExists) {
                                for (Food food : iceScream)
                                    if (oldName.equals(food.getName()))
                                        servicesRpcProxy.updateDessertFood("IceScream", DessertFoodType.ICE_SCREAM, oldName, id, name, description, grams, price, rating, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Food updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This food does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        } else if (nameOfItem.equals("OtherDessert")) {
                            foodExists = false;

                            List<Food> otherDesserts = servicesRpcProxy.getFoodDataBase().loadFoods("OtherDesserts");

                            for (int i = 0; i < otherDesserts.size(); ++i) {
                                if (otherDesserts.get(i).getName().equals(oldName)) {
                                    foodExists = true;
                                    break;
                                }
                            }

                            if (foodExists) {
                                for (Food food : otherDesserts)
                                    if (oldName.equals(food.getName()))
                                        servicesRpcProxy.updateDessertFood("OtherDesserts", DessertFoodType.OTHER_DESSERT, oldName, id, name, description, grams, price, rating, quantity);

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("Food updated successfully!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text-v2");
                                }

                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("UPDATE");
                                alert.setHeaderText(null);
                                alert.setContentText("This food does not exists!");

                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

                                Label contentLabel = (Label) dialogPane.lookup(".content");
                                if (contentLabel != null) {
                                    contentLabel.getStyleClass().add("alert-content-text");
                                }

                                alert.showAndWait();
                            }
                        }
                    } catch (ServiceException ex) {
                        ex.printStackTrace();
                    }
                }
                catch (NumberFormatException ex)
                {
                    System.err.println("Id, grams, price, quantity must be numbers!");
                }

                newWindow.close();
            }
        });

        cancelButton.setOnAction(e -> {
            newWindow.close();
        });

        newWindow.show();
    }

    public void findDrinkFromMenu()
    {
        HBox searchBox = new HBox(5);

        Label nameLabel = new Label("Name: ");
        TextField nameField = new TextField();
        Button searchButton = new Button("SEARCH");

        Pane nameWrapper = new Pane(nameLabel);

        nameField.setPrefSize(250, 50);

        {
            nameLabel.getStyleClass().add("drinks-label-color-v4");
            nameWrapper.setStyle("-fx-background-color: #df00fe;");

            nameField.getStyleClass().add("drinks-label");
        }

        searchBox.getChildren().addAll(nameWrapper, nameField, searchButton);

        VBox resultBox = new VBox(5);

        HBox cancelBox = new HBox();
        cancelBox.setAlignment(Pos.BOTTOM_RIGHT);
        Button cancelButton = new Button("CANCEL");
        cancelBox.getChildren().add(cancelButton);

        cancelButton.setPrefSize(511, 50);
        searchButton.setPrefSize(150, 50);

        {
            searchButton.getStyleClass().add("interface-button");
            cancelButton.getStyleClass().add("interface-button");
        }

        TableView<Drink> drinkTableView = new TableView<>();

        drinkTableView.setPrefSize(511, 150);

        TableColumn<Drink, String> nameDrinkColumn = new TableColumn<>("Name");
        TableColumn<Drink, Double> volumeDrinkColumn = new TableColumn<>("Volume");
        TableColumn<Drink, Integer> priceDrinkColumn = new TableColumn<>("Price");

        nameDrinkColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        volumeDrinkColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getVolume()));
        priceDrinkColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrice()));

        nameDrinkColumn.setPrefWidth(170);
        nameDrinkColumn.setStyle("-fx-alignment: CENTER;");
        drinkTableView.getColumns().add(nameDrinkColumn);

        volumeDrinkColumn.setPrefWidth(170);
        volumeDrinkColumn.setStyle("-fx-alignment: CENTER;");
        drinkTableView.getColumns().add(volumeDrinkColumn);

        priceDrinkColumn.setPrefWidth(170);
        priceDrinkColumn.setStyle("-fx-alignment: CENTER;");
        drinkTableView.getColumns().add(priceDrinkColumn);

        nameDrinkColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    getStyleClass().add("orders-label");
                    setStyle("-fx-background-color: rgb(83,141,8); -fx-alignment: center;");
                }
            }
        });

        volumeDrinkColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.valueOf(item + " ML"));
                    getStyleClass().add("orders-label");
                    setStyle("-fx-background-color: rgb(83,141,8); -fx-alignment: center;");
                }
            }
        });

        priceDrinkColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.valueOf(item + " LEI"));
                    getStyleClass().add("orders-label");
                    setStyle("-fx-background-color: rgb(83,141,8); -fx-alignment: center;");
                }
            }
        });

        resultBox.getChildren().addAll(searchBox, drinkTableView, cancelBox);

        Scene scene = new Scene(resultBox);
        Stage newWindow = new Stage();
        newWindow.setTitle("Find drink");
        newWindow.setScene(scene);

        scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

        searchButton.setOnAction(e -> {
            String name = nameField.getText();

            try
            {
                if (nameOfItem.equals("Beer"))
                {
                    Drink drink = servicesRpcProxy.findAlcoholicDrink(AlcoholicDrinkType.BEER, name);

                    ObservableList<Drink> observableList = FXCollections.observableArrayList(drink);
                    drinkTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("Cocktail"))
                {
                    Drink drink = servicesRpcProxy.findAlcoholicDrink(AlcoholicDrinkType.COCKTAIL, name);

                    ObservableList<Drink> observableList = FXCollections.observableArrayList(drink);
                    drinkTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("Wine"))
                {
                    Drink drink = servicesRpcProxy.findAlcoholicDrink(AlcoholicDrinkType.WINE, name);

                    ObservableList<Drink> observableList = FXCollections.observableArrayList(drink);
                    drinkTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("StringDrink"))
                {
                    Drink drink = servicesRpcProxy.findAlcoholicDrink(AlcoholicDrinkType.STRONG_DRINK, name);

                    ObservableList<Drink> observableList = FXCollections.observableArrayList(drink);
                    drinkTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("Coffee"))
                {
                    Drink drink = servicesRpcProxy.findNonAlcoholicDrink(NonAlcoholicDrinkType.COFFEE, name);

                    ObservableList<Drink> observableList = FXCollections.observableArrayList(drink);
                    drinkTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("Juice"))
                {
                    Drink drink = servicesRpcProxy.findNonAlcoholicDrink(NonAlcoholicDrinkType.JUICE, name);

                    ObservableList<Drink> observableList = FXCollections.observableArrayList(drink);
                    drinkTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("Tea"))
                {
                    Drink drink = servicesRpcProxy.findNonAlcoholicDrink(NonAlcoholicDrinkType.TEA, name);

                    ObservableList<Drink> observableList = FXCollections.observableArrayList(drink);
                    drinkTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("SoftDrink"))
                {
                    Drink drink = servicesRpcProxy.findNonAlcoholicDrink(NonAlcoholicDrinkType.SOFT_DRINK, name);

                    ObservableList<Drink> observableList = FXCollections.observableArrayList(drink);
                    drinkTableView.setItems(observableList);
                }
            }
            catch (ServiceException ex)
            {
                ex.printStackTrace();
            }


        });

        cancelButton.setOnAction(e -> {
            newWindow.close();
        });

        newWindow.show();
    }

    public void findFoodFromMenu()
    {
        HBox searchBox = new HBox(5);

        Label nameLabel = new Label("Name: ");
        TextField nameField = new TextField();
        Button searchButton = new Button("SEARCH");

        Pane nameWrapper = new Pane(nameLabel);

        nameField.setPrefSize(250, 50);

        {
            nameLabel.getStyleClass().add("drinks-label-color-v4");
            nameWrapper.setStyle("-fx-background-color: #df00fe;");

            nameField.getStyleClass().add("drinks-label");
        }

        searchBox.getChildren().addAll(nameWrapper, nameField, searchButton);

        VBox resultBox = new VBox(5);

        HBox cancelBox = new HBox();
        cancelBox.setAlignment(Pos.BOTTOM_RIGHT);
        Button cancelButton = new Button("CANCEL");
        cancelBox.getChildren().add(cancelButton);

        cancelButton.setPrefSize(511, 50);
        searchButton.setPrefSize(150, 50);

        {
            searchButton.getStyleClass().add("interface-button");
            cancelButton.getStyleClass().add("interface-button");
        }

        TableView<Food> foodTableView = new TableView<>();

        foodTableView.setPrefSize(511, 150);

        TableColumn<Food, String> nameFoodColumn = new TableColumn<>("Name");
        TableColumn<Food, String> descriptionFoodColumn = new TableColumn<>("Description");
        TableColumn<Food, Integer> gramsColumn = new TableColumn<>("Grams");
        TableColumn<Food, Integer> priceFoodColumn = new TableColumn<>("Price");
        TableColumn<Food, Float> ratingFoodColumn = new TableColumn<>("Rating");

        nameFoodColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        descriptionFoodColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescription()));
        gramsColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGrams()));
        priceFoodColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrice()));
        ratingFoodColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRating()));

        nameFoodColumn.setPrefWidth(102);
        nameFoodColumn.setStyle("-fx-alignment: CENTER;");
        foodTableView.getColumns().add(nameFoodColumn);

        descriptionFoodColumn.setPrefWidth(102);
        descriptionFoodColumn.setStyle("-fx-alignment: CENTER;");
        foodTableView.getColumns().add(descriptionFoodColumn);

        gramsColumn.setPrefWidth(102);
        gramsColumn.setStyle("-fx-alignment: CENTER;");
        foodTableView.getColumns().add(gramsColumn);

        priceFoodColumn.setPrefWidth(102);
        priceFoodColumn.setStyle("-fx-alignment: CENTER;");
        foodTableView.getColumns().add(priceFoodColumn);

        ratingFoodColumn.setPrefWidth(102);
        ratingFoodColumn.setStyle("-fx-alignment: CENTER;");
        foodTableView.getColumns().add(ratingFoodColumn);

        nameFoodColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    getStyleClass().add("orders-label");
                    setStyle("-fx-background-color: rgb(83,141,8); -fx-alignment: center;");
                }
            }
        });

        descriptionFoodColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    getStyleClass().add("orders-label");
                    setStyle("-fx-background-color: rgb(83,141,8); -fx-alignment: center;");
                }
            }
        });

        gramsColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.valueOf(item + " GR"));
                    getStyleClass().add("orders-label");
                    setStyle("-fx-background-color: rgb(83,141,8); -fx-alignment: center;");
                }
            }
        });

        priceFoodColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.valueOf(item + " LEI"));
                    getStyleClass().add("orders-label");
                    setStyle("-fx-background-color: rgb(83,141,8); -fx-alignment: center;");
                }
            }
        });

        ratingFoodColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Float item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.valueOf(item + "☆"));
                    getStyleClass().add("orders-label");
                    setStyle("-fx-background-color: rgb(83,141,8); -fx-alignment: center;");
                }
            }
        });

        resultBox.getChildren().addAll(searchBox, foodTableView, cancelBox);

        Scene scene = new Scene(resultBox);
        Stage newWindow = new Stage();
        newWindow.setTitle("Find drink");
        newWindow.setScene(scene);

        scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

        searchButton.setOnAction(e -> {
            String name = nameField.getText();

            try
            {
                if (nameOfItem.equals("Salad"))
                {
                    Food food = servicesRpcProxy.findStarterFood(StarterFoodType.SALAD, name);

                    ObservableList<Food> observableList = FXCollections.observableArrayList(food);
                    foodTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("Soup"))
                {
                    Food food = servicesRpcProxy.findStarterFood(StarterFoodType.SOUP, name);

                    ObservableList<Food> observableList = FXCollections.observableArrayList(food);
                    foodTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("SmallBite"))
                {
                    Food food = servicesRpcProxy.findStarterFood(StarterFoodType.SMALL_BITES, name);

                    ObservableList<Food> observableList = FXCollections.observableArrayList(food);
                    foodTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("Meat"))
                {
                    Food food = servicesRpcProxy.findMainFood(MainFoodType.MEAT, name);

                    ObservableList<Food> observableList = FXCollections.observableArrayList(food);
                    foodTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("Pasta"))
                {
                    Food food = servicesRpcProxy.findMainFood(MainFoodType.PASTA, name);

                    ObservableList<Food> observableList = FXCollections.observableArrayList(food);
                    foodTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("SeaFood"))
                {
                    Food food = servicesRpcProxy.findMainFood(MainFoodType.SEAFOOD, name);

                    ObservableList<Food> observableList = FXCollections.observableArrayList(food);
                    foodTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("Pizza"))
                {
                    Food food = servicesRpcProxy.findMainFood(MainFoodType.PIZZA, name);

                    ObservableList<Food> observableList = FXCollections.observableArrayList(food);
                    foodTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("Cake"))
                {
                    Food food = servicesRpcProxy.findDessertFood(DessertFoodType.CAKE, name);

                    ObservableList<Food> observableList = FXCollections.observableArrayList(food);
                    foodTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("IceScream"))
                {
                    Food food = servicesRpcProxy.findDessertFood(DessertFoodType.ICE_SCREAM, name);

                    ObservableList<Food> observableList = FXCollections.observableArrayList(food);
                    foodTableView.setItems(observableList);
                }
                else if (nameOfItem.equals("OtherDessert"))
                {
                    Food food = servicesRpcProxy.findDessertFood(DessertFoodType.OTHER_DESSERT, name);

                    ObservableList<Food> observableList = FXCollections.observableArrayList(food);
                    foodTableView.setItems(observableList);
                }
            }
            catch (ServiceException ex)
            {
                ex.printStackTrace();
            }
        });

        cancelButton.setOnAction(e -> {
            newWindow.close();
        });

        newWindow.show();
    }

    public void setupAddButton()
    {
        this.addButton.setOnAction(e -> {
            this.currentStage = "Add";
            VBox newAddButtonsBox = this.createAddButtonsBox();

            Scene scene = new Scene(newAddButtonsBox);

            scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());
            newAddButtonsBox.setStyle("-fx-background-color: #00d0ff;");

            Stage newWindow = new Stage();

            newWindow.setScene(scene);
            newWindow.setTitle("Add Items");

            cancelAddOperationButton.setOnAction(ev -> {
                newWindow.close();
            });

            newWindow.show();
        });
    }

    public void setupRemoveButton()
    {
        this.removeButton.setOnAction(e -> {
            this.currentStage = "Remove";
            VBox newRemoveButtonsBox = this.createRemoveButtonsBox();

            Scene scene = new Scene(newRemoveButtonsBox);

            scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());
            newRemoveButtonsBox.setStyle("-fx-background-color: #00d0ff;");

            Stage newWindow = new Stage();

            newWindow.setScene(scene);
            newWindow.setTitle("Remove Items");

            cancelRemoveOperationButton.setOnAction(ev -> {
                newWindow.close();
            });

            newWindow.show();
        });
    }

    public void setupUpdateButton()
    {
        this.updateButton.setOnAction(e -> {
            this.currentStage = "Update";
            VBox newUpdateButtonsBox = this.createUpdateButtonsBox();

            Scene scene = new Scene(newUpdateButtonsBox);

            scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());
            newUpdateButtonsBox.setStyle("-fx-background-color: #00d0ff;");

            Stage newWindow = new Stage();

            newWindow.setScene(scene);
            newWindow.setTitle("Update Items");

            cancelUpdateOperationButton.setOnAction(ev -> {
                newWindow.close();
            });

            newWindow.show();
        });
    }

    public void setupFindButton()
    {
        this.findButton.setOnAction(e -> {
            this.currentStage = "Find";
            VBox newFindButtonsBox = this.createFindButtonsBox();

            Scene scene = new Scene(newFindButtonsBox);

            scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());
            newFindButtonsBox.setStyle("-fx-background-color: #00d0ff;");

            Stage newWindow = new Stage();

            newWindow.setScene(scene);
            newWindow.setTitle("Find Items");

            cancelFindOperationButton.setOnAction(ev -> {
                newWindow.close();
            });

            newWindow.show();
        });
    }

    public void setupAddDrinkButton()
    {
        this.addDrinkButton.setOnAction(e -> {
            Scene currentScene = addDrinkButton.getScene();

            this.createDrinkButtons();
            HBox allDBox = createDrinkBoxes();
            Button back = createBackButton();

            if (currentScene.getRoot() == finalAddButtonsBox) {
                int index = finalAddButtonsBox.getChildren().indexOf(addButtonsBox);
                int backIndex = finalAddButtonsBox.getChildren().indexOf(cancelAddOperationButton);

                if(index != -1)
                {
                    finalAddButtonsBox.getChildren().set(index, allDBox);
                    finalAddButtonsBox.getChildren().set(backIndex, back);
                }

                finalAddButtonsBox.setSpacing(60);
            }

            currentType = "Drink";
        });
    }

    public void setupAddFoodButton()
    {
        this.addFoodButton.setOnAction(e -> {
            Scene currentScene = addFoodButton.getScene();

            this.createFoodButtons();
            HBox allFBox = createFoodBoxes();
            Button back = createBackButton();

            if (currentScene.getRoot() == finalAddButtonsBox)
            {
                int index = finalAddButtonsBox.getChildren().indexOf(addButtonsBox);
                int backIndex = finalAddButtonsBox.getChildren().indexOf(cancelAddOperationButton);

                if (index != -1)
                {
                    finalAddButtonsBox.getChildren().set(index, allFBox);
                    finalAddButtonsBox.getChildren().set(backIndex, back);
                }
            }

            currentType = "Food";
        });
    }

    public void setupRemoveDrinkButton()
    {
        this.removeDrinkButton.setOnAction(e -> {
            Scene currentScene = removeDrinkButton.getScene();

            this.createDrinkButtons();
            HBox allDBox = createDrinkBoxes();
            Button back = createBackButton();

            if (currentScene.getRoot() == finalRemoveButtonsBox)
            {
                int index = finalRemoveButtonsBox.getChildren().indexOf(removeButtonsBox);
                int backIndex = finalRemoveButtonsBox.getChildren().indexOf(cancelRemoveOperationButton);

                if (index != -1)
                {
                    finalRemoveButtonsBox.getChildren().set(index, allDBox);
                    finalRemoveButtonsBox.getChildren().set(backIndex, back);
                }

                finalRemoveButtonsBox.setSpacing(60);
            }

            currentType = "Drink";
        });
    }

    public void setupRemoveFoodButton()
    {
        this.removeFoodButton.setOnAction(e -> {
            Scene currentScene = removeFoodButton.getScene();

            this.createFoodButtons();
            HBox allFBox = createFoodBoxes();
            Button back = createBackButton();

            if (currentScene.getRoot() == finalRemoveButtonsBox)
            {
                int index = finalRemoveButtonsBox.getChildren().indexOf(removeButtonsBox);
                int backIndex = finalRemoveButtonsBox.getChildren().indexOf(cancelRemoveOperationButton);

                if (index != -1)
                {
                    finalRemoveButtonsBox.getChildren().set(index, allFBox);
                    finalRemoveButtonsBox.getChildren().set(backIndex, back);
                }
            }

            currentType = "Food";
        });
    }

    public void setupUpdateDrinkButton()
    {
        this.updateDrinkButton.setOnAction(e -> {
            Scene currentScene = updateDrinkButton.getScene();

            this.createDrinkButtons();
            HBox allDBox = createDrinkBoxes();
            Button back = createBackButton();

            if (currentScene.getRoot() == finalUpdateButtonsBox)
            {
                int index = finalUpdateButtonsBox.getChildren().indexOf(updateButtonsBox);
                int backIndex = finalUpdateButtonsBox.getChildren().indexOf(cancelUpdateOperationButton);

                if (index != -1)
                {
                    finalUpdateButtonsBox.getChildren().set(index, allDBox);
                    finalUpdateButtonsBox.getChildren().set(backIndex, back);
                }

                finalUpdateButtonsBox.setSpacing(60);
            }

            currentType = "Drink";
        });
    }

    public void setupUpdateFoodButton()
    {
        this.updateFoodButton.setOnAction(e -> {
            Scene currentScene = updateFoodButton.getScene();

            this.createFoodButtons();
            HBox allFBox = createFoodBoxes();
            Button back = createBackButton();

            if (currentScene.getRoot() == finalUpdateButtonsBox)
            {
                int index = finalUpdateButtonsBox.getChildren().indexOf(updateButtonsBox);
                int backIndex = finalUpdateButtonsBox.getChildren().indexOf(cancelUpdateOperationButton);

                if (index != -1)
                {
                    finalUpdateButtonsBox.getChildren().set(index, allFBox);
                    finalUpdateButtonsBox.getChildren().set(backIndex, back);
                }
            }

            currentType = "Food";
        });
    }

    public void setupFindDrinkButton()
    {
        this.findDrinkButton.setOnAction(e -> {
            Scene currentScene = findDrinkButton.getScene();

            this.createDrinkButtons();
            HBox allDBox = createDrinkBoxes();
            Button back = createBackButton();

            if (currentScene.getRoot() == finalFindButtonsBox)
            {
                int index = finalFindButtonsBox.getChildren().indexOf(findButtonsBox);
                int backIndex = finalFindButtonsBox.getChildren().indexOf(cancelFindOperationButton);

                if (index != -1)
                {
                    finalFindButtonsBox.getChildren().set(index, allDBox);
                    finalFindButtonsBox.getChildren().set(backIndex, back);
                }

                finalFindButtonsBox.setSpacing(60);
            }

            currentType = "Drink";
        });
    }

    public void setupFindFoodButton()
    {
        this.findFoodButton.setOnAction(e -> {
            Scene currentScene = findFoodButton.getScene();

            this.createFoodButtons();
            HBox allFBox = createFoodBoxes();
            Button back = createBackButton();

            if (currentScene.getRoot() == finalFindButtonsBox)
            {
                int index = finalFindButtonsBox.getChildren().indexOf(findButtonsBox);
                int backIndex = finalFindButtonsBox.getChildren().indexOf(cancelFindOperationButton);

                if (index != -1)
                {
                    finalFindButtonsBox.getChildren().set(index, allFBox);
                    finalFindButtonsBox.getChildren().set(backIndex, back);
                }
            }

            currentType = "Food";
        });
    }

    public HBox getCRUDButtonsBox() {
        return CRUDButtonsBox;
    }

    public HBox getBoxesWithLiveOrderBox() {
        return boxesWithLiveOrderBox;
    }

    public VBox getBiggestBox() {
        return biggestBox;
    }

    public VBox getDrinkAndFoodAndUserCRUDButtonsBox() {
        return drinkAndFoodAndUserCRUDButtonsBox;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public Button getFindButton() {
        return findButton;
    }
}
