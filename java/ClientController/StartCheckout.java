package ClientController;

import CheckoutController.*;
import Networking.RpcProtocol.ServicesRpcProxy;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

public class StartCheckout extends Application {
    private static int defaultPort = 12345;
    private static String defaultServer = "localhost";

    @Override
    public void start(Stage stage) throws Exception {


        Properties clientProps = new Properties();


        try
        {
            clientProps.load(StartCheckout.class.getResourceAsStream("/checkout.properties"));

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

        AuthAndSwitchController authAndSwitchController = new AuthAndSwitchController(servicesRpcProxy);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(authAndSwitchController.getLoginBox());

        CRUDOperationsController crudOperations = new CRUDOperationsController(servicesRpcProxy);
        UserCRUDOperationsController userCRUDOperationsController = new UserCRUDOperationsController(servicesRpcProxy);
        LiveOrdersController liveOrdersController = new LiveOrdersController(servicesRpcProxy);
        ViewerController viewerController = new ViewerController(servicesRpcProxy);
        RestaurantController restaurantController = new RestaurantController(servicesRpcProxy, authAndSwitchController, viewerController, liveOrdersController);
        //##############################################################################

        authAndSwitchController.setupSwitchButton(borderPane);
        //authAndSwitchController.setupSwitchToClientInterfaceButton(borderPane, startMenuController, yourOrderViewController);
        authAndSwitchController.setupLoginButton(borderPane, crudOperations, userCRUDOperationsController, liveOrdersController, viewerController, restaurantController);
        //authAndSwitchController.setupSwitchToClientInterfaceButton2(borderPane /*startMenuController, yourOrderViewController*/);
        authAndSwitchController.setupSwitchToClientInterfaceButton2_v2(borderPane);

        crudOperations.setupAddButton();
        crudOperations.setupRemoveButton();
        crudOperations.setupUpdateButton();
        crudOperations.setupFindButton();
        crudOperations.setupAddDrinkButton();
        crudOperations.setupAddFoodButton();
        crudOperations.setupRemoveDrinkButton();
        crudOperations.setupRemoveFoodButton();
        crudOperations.setupUpdateDrinkButton();
        crudOperations.setupUpdateFoodButton();
        crudOperations.setupFindDrinkButton();
        crudOperations.setupFindFoodButton();

        userCRUDOperationsController.setupAddUserButton(viewerController);
        userCRUDOperationsController.setupRemoveUserButton(viewerController);
        userCRUDOperationsController.setupUpdateUserButton(viewerController);
        userCRUDOperationsController.setupFindUserButton();


        liveOrdersController.setupOpenButton();
        liveOrdersController.setupDeleteButton();
        liveOrdersController.setupRestoreButton();
        liveOrdersController.setupFinishedButton();
        liveOrdersController.setupNotFinishedButton();
        liveOrdersController.setupRefreshButton();
        //liveOrdersController.setupPaidButton(viewerController, restaurantController);
        //liveOrdersController.setupNotPaidButton(viewerController, restaurantController);
        liveOrdersController.setupSetTimeButton();
        liveOrdersController.setupAddRemoveTimeButton();

        viewerController.setupAllOrdersButton();
        viewerController.setupAllUsersButton();
        viewerController.setupAllDrinksButton();
        viewerController.setupAllFoodButton();
        viewerController.setupAllPaymentsButton();

        restaurantController.setupTotalAmountButton();
        restaurantController.setupInsideButton();
        restaurantController.setupTerraceButton();
        restaurantController.setupDeliveryButton();
        restaurantController.setupButtonsStyleButton(viewerController, crudOperations, userCRUDOperationsController);
        restaurantController.setupTablesStyleButton();
        restaurantController.setupToolbarButtonsStyleButton();
        restaurantController.setupLiveOrdersStyleButton(liveOrdersController);
        restaurantController.setupBackgroundStyleButton(borderPane);
        restaurantController.setupSetDefaultStyleButton(viewerController, crudOperations, userCRUDOperationsController, liveOrdersController,borderPane);
        restaurantController.setupSaveThemeButton();
        restaurantController.setupSaveButton();
        restaurantController.setupCancelSaveButton();
        restaurantController.setupSavedThemesButton();
        restaurantController.setupApplyButton(crudOperations, userCRUDOperationsController, borderPane);
        restaurantController.setupDeleteThemeButton();

        borderPane.setStyle("-fx-background-color: #01fdcf;");

        Scene scene = new Scene(borderPane, 1200, 720);
        scene.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());
        stage.setTitle("CHECKOUT APPLICATION");
        stage.setScene(scene);
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
