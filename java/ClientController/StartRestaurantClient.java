package ClientController;

import Networking.RpcProtocol.ServicesRpcProxy;
import Service.ServiceException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StartRestaurantClient extends Application {
    private static int defaultPort = 12345;
    private static String defaultServer = "localhost";

    @Override
    public void start(Stage stage) throws Exception {

        Properties clientProps = new Properties();

        try
        {
            clientProps.load(StartRestaurantClient.class.getResourceAsStream("/client.properties"));

            System.out.println("client properties set");
            clientProps.list(System.out);
        } catch (IOException e)
        {
            System.err.println("Cannot find client.properties " + e);
            return;
        }

        String serverIP = clientProps.getProperty("server.host", defaultServer);
        int serverPort = defaultPort;

        try
        {
            serverPort = Integer.parseInt(clientProps.getProperty("server.port"));
        }
        catch (NumberFormatException e)
        {
            System.out.println("Wrong port number" + e);
            System.out.println("Using default port: " + defaultPort);
        }

        ServicesRpcProxy servicesRpcProxy = new ServicesRpcProxy(serverIP, serverPort);

        servicesRpcProxy.initializeConnection();

        List<Boolean> ratingList = new ArrayList<>();

        for (int i = 0; i < 100; ++i)
            ratingList.add(false);

        BorderPane borderPane = new BorderPane();

        FirstMenuController startMenuController = new FirstMenuController(servicesRpcProxy);
        startMenuController.setupStartMenu();

        YourOrderViewController yourOrderViewController = new YourOrderViewController(servicesRpcProxy, ratingList);

        borderPane.setCenter(startMenuController.getSetBox());

        DrinkMenuController drinkMenuController = new DrinkMenuController(servicesRpcProxy);
        FoodMenuController foodMenuController = new FoodMenuController(servicesRpcProxy);
        RateFoodController rateFoodController = new RateFoodController(yourOrderViewController, servicesRpcProxy, ratingList);
        DrinkFoodViewerController drinkDrinkFoodViewerController = new DrinkFoodViewerController(yourOrderViewController, drinkMenuController, foodMenuController, startMenuController, rateFoodController, servicesRpcProxy);
        DrinkFoodViewerController foodDrinkFoodViewerController = new DrinkFoodViewerController(yourOrderViewController, drinkMenuController, foodMenuController, startMenuController, rateFoodController, servicesRpcProxy);

        HBox drinksActionBox = new HBox(10, drinkMenuController.getDrinksMenuBox(), drinkDrinkFoodViewerController.getMiddleBox());
        HBox foodActionBox = new HBox(10, foodMenuController.getFoodMenuBox(), foodDrinkFoodViewerController.getMiddleBox());

        startMenuController.setupDrinksButton(borderPane, drinksActionBox, yourOrderViewController, drinkDrinkFoodViewerController);
        startMenuController.setupFoodButton(borderPane, foodActionBox, yourOrderViewController, foodDrinkFoodViewerController);
        startMenuController.setupSetButton(borderPane, yourOrderViewController);
        startMenuController.setupInsideButton();
        startMenuController.setupTerraceButton();
        startMenuController.setupPlaceUndoButton();

        drinkMenuController.setupBeersButton(drinkDrinkFoodViewerController);
        drinkMenuController.setupWinesButton(drinkDrinkFoodViewerController);
        drinkMenuController.setupCocktailsButton(drinkDrinkFoodViewerController);
        drinkMenuController.setupStrongDrinksButton(drinkDrinkFoodViewerController);
        drinkMenuController.setupCoffeesButton(drinkDrinkFoodViewerController);
        drinkMenuController.setupTeasButton(drinkDrinkFoodViewerController);
        drinkMenuController.setupJuicesButton(drinkDrinkFoodViewerController);
        drinkMenuController.setupSoftDrinksButton(drinkDrinkFoodViewerController);

        foodMenuController.setupSaladButton(foodDrinkFoodViewerController);
        foodMenuController.setupSoupButton(foodDrinkFoodViewerController);
        foodMenuController.setupSmallBitesButton(foodDrinkFoodViewerController);
        foodMenuController.setupMeatButton(foodDrinkFoodViewerController);
        foodMenuController.setupPastaButton(foodDrinkFoodViewerController);
        foodMenuController.setupSeaFoodButton(foodDrinkFoodViewerController);
        foodMenuController.setupPizzaButton(foodDrinkFoodViewerController);
        foodMenuController.setupCakesButton(foodDrinkFoodViewerController);
        foodMenuController.setupIceScreamButton(foodDrinkFoodViewerController);
        foodMenuController.setupOtherDessertsButton(foodDrinkFoodViewerController);

        drinkDrinkFoodViewerController.setupAddButton(true);
        foodDrinkFoodViewerController.setupAddButton(false);

        drinkDrinkFoodViewerController.setupSearchButton();
        foodDrinkFoodViewerController.setupSearchButton();

        drinkDrinkFoodViewerController.setupUndoButton(borderPane, yourOrderViewController);
        foodDrinkFoodViewerController.setupUndoButton(borderPane, yourOrderViewController);

        yourOrderViewController.setupRemoveButton(rateFoodController);
        yourOrderViewController.setupPlaceOrderButton(borderPane, rateFoodController);
        yourOrderViewController.setupBackButton(borderPane, drinksActionBox, foodActionBox, startMenuController);
        yourOrderViewController.setupCashButton(borderPane);
        yourOrderViewController.setupCardButton(borderPane);
        yourOrderViewController.setupModifyPaymentMethod(borderPane);
        yourOrderViewController.setupViewOrderButton(borderPane);
        yourOrderViewController.setupOkButton(borderPane);
        yourOrderViewController.setupModifyButton(borderPane, startMenuController);
        yourOrderViewController.setupSubmitButton(borderPane, startMenuController/*viewerController, restaurantController, liveOrdersController*/);
        yourOrderViewController.setupBackToMenuButton(borderPane, startMenuController, rateFoodController);
        yourOrderViewController.setupEnterTableButton();
        yourOrderViewController.setupInsideButton();
        yourOrderViewController.setupTerraceButton();
        yourOrderViewController.setupPlaceUndoButton();
        yourOrderViewController.setupTableUndoButton();
        yourOrderViewController.setupDeliveryButton();
        yourOrderViewController.setupDoneDeliveryButton();
        yourOrderViewController.setupCloseDeliveryButton();
        yourOrderViewController.setupCheckButton();
        yourOrderViewController.setupRemoveFinalButton();
        yourOrderViewController.setupAddFinalButton(borderPane, startMenuController, yourOrderViewController);
        yourOrderViewController.setupRefreshOrderButton();

        rateFoodController.setupRateFoodButton(borderPane, yourOrderViewController);
        rateFoodController.setupBackButton(borderPane);

        borderPane.setStyle("-fx-background-color: #01fdcf;");

        Scene scene = new Scene(borderPane, 1200, 720);
        scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());
        stage.setTitle("CLIENT APPLICATION");
        stage.setScene(scene);

        stage.setOnCloseRequest(e -> {
            try {
                servicesRpcProxy.clearOrderItems();
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }
        });

        stage.show();

        double initialWidth = stage.getWidth();
        double initialHeight = stage.getHeight();

        stage.setMinWidth(initialWidth);
        stage.setMinHeight(initialHeight);
        stage.setMaxHeight(initialHeight);
        stage.setMaxWidth(initialWidth);
    }

    public static void main(String[] args) {
        launch();
    }
}
