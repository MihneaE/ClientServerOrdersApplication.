package ClientController;

import Model.Drink;
import Networking.RpcProtocol.ServicesRpcProxy;
import Service.DrinkService;
import Service.ServiceException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class DrinkMenuController {
    private Label drinksLabel;
    private Label alcoholicDrinksLabel;
    private Label nonAlcoholicDrinksLabel1;
    private Label nonAlcoholicDrinksLabel2;
    private Button beersButton;
    private Button winesButton;
    private Button cocktailsButton;
    private Button strongDrinksButton;
    private Button coffeesButton;
    private Button teasButton;
    private Button juicesButton;
    private Button softDrinksButton;
    private Pane drinksWrapper;
    private Pane nonAlcoholicDrinksWrapper1;
    private Pane nonAlcoholicDrinksWrapper2;
    private Pane alcoholicDrinksWrapper;
    private VBox nonAlcoholicDrinksBox;
    private VBox drinksMenuBox;
    private String currentCategory;
    private ServicesRpcProxy servicesRpcProxy;

    public DrinkMenuController(ServicesRpcProxy servicesRpcProxy)
    {
        this.servicesRpcProxy = servicesRpcProxy;
        this.setupNames();
        this.setupLabelsDesign();
        this.setupButtonDesign();
        this.setupStyles();

        this.drinksMenuBox = new VBox(5, drinksWrapper, nonAlcoholicDrinksBox, coffeesButton, teasButton, juicesButton, softDrinksButton,
                                        alcoholicDrinksWrapper, beersButton, winesButton, cocktailsButton, strongDrinksButton);
    }

    public void setupNames()
    {
        this.drinksLabel = new Label("DRINKS");
        this.alcoholicDrinksLabel = new Label("ALCOHOLIC DRINKS");
        this.nonAlcoholicDrinksLabel1 = new Label("NON-ALCOHOLIC");
        this.nonAlcoholicDrinksLabel2 = new Label("DRINKS");
        this.beersButton = new Button("BEERS");
        this.winesButton = new Button("WINES");
        this.cocktailsButton = new Button("COCKTAILS");
        this.strongDrinksButton = new Button("STRONG DRINKS");
        this.coffeesButton = new Button("COFFEE");
        this.teasButton = new Button("TEA");
        this.juicesButton = new Button("JUICES");
        this.softDrinksButton = new Button("SOFT DRINKS");
        this.currentCategory = "";
    }

    public void setupLabelsDesign()
    {
        drinksWrapper = new Pane(drinksLabel);
        nonAlcoholicDrinksWrapper1 = new Pane(nonAlcoholicDrinksLabel1);
        nonAlcoholicDrinksWrapper2 = new Pane(nonAlcoholicDrinksLabel2);
        alcoholicDrinksWrapper = new Pane(alcoholicDrinksLabel);

        drinksWrapper.setPrefHeight(50);
        nonAlcoholicDrinksWrapper1.setPrefHeight(25);
        nonAlcoholicDrinksWrapper2.setPrefHeight(25);
        alcoholicDrinksWrapper.setPrefHeight(30);

        {
            drinksWrapper.setStyle("-fx-background-color: #b833ff;");
            nonAlcoholicDrinksWrapper1.setStyle("-fx-background-color: #b833ff;");
            nonAlcoholicDrinksWrapper2.setStyle("-fx-background-color: #b833ff;");
            alcoholicDrinksWrapper.setStyle("-fx-background-color: #b833ff;");
        }

        drinksLabel.layoutXProperty().bind(drinksWrapper.widthProperty().subtract(drinksLabel.widthProperty()).divide(2));
        drinksLabel.layoutYProperty().bind(drinksWrapper.heightProperty().subtract(drinksLabel.heightProperty()).divide(2));

        nonAlcoholicDrinksLabel1.layoutXProperty().bind(nonAlcoholicDrinksWrapper1.widthProperty().subtract(nonAlcoholicDrinksLabel1.widthProperty()).divide(2));
        nonAlcoholicDrinksLabel1.layoutYProperty().bind(nonAlcoholicDrinksWrapper1.heightProperty().subtract(nonAlcoholicDrinksLabel1.heightProperty()).divide(2));

        nonAlcoholicDrinksLabel2.layoutXProperty().bind(nonAlcoholicDrinksWrapper2.widthProperty().subtract(nonAlcoholicDrinksLabel2.widthProperty()).divide(2));
        nonAlcoholicDrinksLabel2.layoutYProperty().bind(nonAlcoholicDrinksWrapper2.heightProperty().subtract(nonAlcoholicDrinksLabel2.heightProperty()).divide(2));

        alcoholicDrinksLabel.layoutXProperty().bind(alcoholicDrinksWrapper.widthProperty().subtract(alcoholicDrinksLabel.widthProperty()).divide(2));
        alcoholicDrinksLabel.layoutYProperty().bind(alcoholicDrinksWrapper.heightProperty().subtract(alcoholicDrinksLabel.heightProperty()).divide(2));

        nonAlcoholicDrinksBox = new VBox(nonAlcoholicDrinksWrapper1, nonAlcoholicDrinksWrapper2);
    }

    public void setupButtonDesign()
    {
        coffeesButton.setMaxWidth(Double.MAX_VALUE);
        coffeesButton.setPrefHeight(40);
        teasButton.setMaxWidth(Double.MAX_VALUE);
        coffeesButton.setPrefHeight(40);
        juicesButton.setMaxWidth(Double.MAX_VALUE);
        juicesButton.setPrefHeight(40);
        softDrinksButton.setMaxWidth(Double.MAX_VALUE);
        softDrinksButton.setPrefHeight(40);

        beersButton.setMaxWidth(Double.MAX_VALUE);
        beersButton.setPrefHeight(40);
        winesButton.setMaxWidth(Double.MAX_VALUE);
        winesButton.setPrefHeight(40);
        cocktailsButton.setMaxWidth(Integer.MAX_VALUE);
        cocktailsButton.setPrefHeight(40);
        strongDrinksButton.setMaxWidth(Double.MAX_VALUE);
        strongDrinksButton.setPrefWidth(40);
    }

    public void setupStyles()
    {
        this.drinksLabel.getStyleClass().add("welcome-label");
        this.alcoholicDrinksLabel.getStyleClass().add("drinks-label");
        this.nonAlcoholicDrinksLabel1.getStyleClass().add("drinks-label");
        this.nonAlcoholicDrinksLabel2.getStyleClass().add("drinks-label");
        this.beersButton.getStyleClass().add("menu-button");
        this.winesButton.getStyleClass().add("menu-button");
        this.cocktailsButton.getStyleClass().add("menu-button");
        this.strongDrinksButton.getStyleClass().add("menu-button");
        this.coffeesButton.getStyleClass().add("menu-button");
        this.teasButton.getStyleClass().add("menu-button");
        this.juicesButton.getStyleClass().add("menu-button");
        this.softDrinksButton.getStyleClass().add("menu-button");
    }

    public void setupBeersButton(DrinkFoodViewerController drinkFoodViewerController)
    {
        this.beersButton.setOnAction(e -> {
            try {
                drinkFoodViewerController.displayDrink(servicesRpcProxy.getDrinkDataBase().loadDrinks("Beers"));
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }
            this.currentCategory = "beers";
        });
    }

    public void setupWinesButton(DrinkFoodViewerController drinkFoodViewerController)
    {
        this.winesButton.setOnAction(e -> {
            try {
                drinkFoodViewerController.displayDrink(servicesRpcProxy.getDrinkDataBase().loadDrinks("Wines"));
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }
            this.currentCategory = "wines";
        });
    }

    public void setupCocktailsButton(DrinkFoodViewerController drinkFoodViewerController)
    {
        this.cocktailsButton.setOnAction(e -> {
            try {
                drinkFoodViewerController.displayDrink(servicesRpcProxy.getDrinkDataBase().loadDrinks("Cocktails"));
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }
            this.currentCategory = "cocktails";
        });
    }

    public void setupStrongDrinksButton(DrinkFoodViewerController drinkFoodViewerController)
    {
        this.strongDrinksButton.setOnAction(e -> {
            try {
                drinkFoodViewerController.displayDrink(servicesRpcProxy.getDrinkDataBase().loadDrinks("StrongDrinks"));
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }
            this.currentCategory = "strongDrinks";
        });
    }

    public void setupCoffeesButton(DrinkFoodViewerController drinkFoodViewerController)
    {
        this.coffeesButton.setOnAction(e -> {
            try {
                drinkFoodViewerController.displayDrink(servicesRpcProxy.getDrinkDataBase().loadDrinks("Coffees"));
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }
            this.currentCategory = "coffees";
        });
    }

    public void setupTeasButton(DrinkFoodViewerController drinkFoodViewerController)
    {
        this.teasButton.setOnAction(e -> {
            try {
                drinkFoodViewerController.displayDrink(servicesRpcProxy.getDrinkDataBase().loadDrinks("Teas"));
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }
            this.currentCategory = "teas";
        });
    }

    public void setupJuicesButton(DrinkFoodViewerController drinkFoodViewerController)
    {
        this.juicesButton.setOnAction(e -> {
            try {
                drinkFoodViewerController.displayDrink(servicesRpcProxy.getDrinkDataBase().loadDrinks("Juices"));
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }
            this.currentCategory = "juices";
        });
    }

    public void setupSoftDrinksButton(DrinkFoodViewerController drinkFoodViewerController)
    {
        this.softDrinksButton.setOnAction(e -> {
            try {
                drinkFoodViewerController.displayDrink(servicesRpcProxy.getDrinkDataBase().loadDrinks("SoftDrinks"));
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }
            this.currentCategory = "softDrinks";
        });
    }

    public String getCurrentCategory() {
        return currentCategory;
    }

    public VBox getDrinksMenuBox() {
        return drinksMenuBox;
    }
}
