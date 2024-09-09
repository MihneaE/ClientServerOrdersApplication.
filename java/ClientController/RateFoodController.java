package ClientController;

import Model.Food;
import Model.Order;
import Networking.RpcProtocol.ServicesRpcProxy;
import Service.PlaceOrderManagerService;
import Service.ServiceException;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class RateFoodController {
    private ServicesRpcProxy servicesRpcProxy;
    private VBox boxOfFoodBoxes;
    private VBox finalBox;
    private Button backButton;
    private Label noFoodLabel;
    private YourOrderViewController yourOrderViewController;
    private boolean isRatedFoodButtonPressed;
    private Label rateFoodLabel;
    private Pane rateFoodWrapper;
    private GridPane gridPane;
    List<Boolean> ratingList;

    public RateFoodController(YourOrderViewController yourOrderViewController, ServicesRpcProxy servicesRpcProxy, List<Boolean> ratingList)
    {
        this.servicesRpcProxy = servicesRpcProxy;
        this.boxOfFoodBoxes = new VBox(10);
        this.finalBox = new VBox(5);
        this.backButton = new Button("BACK");
        this.noFoodLabel = new Label("NO FOOD TO DISPLAY!");
        this.yourOrderViewController = yourOrderViewController;
        this.isRatedFoodButtonPressed = false;

        this.ratingList = ratingList;

        gridPane = new GridPane();

        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);

        rateFoodLabel = new Label("RATE FOOD");
        rateFoodLabel.getStyleClass().add("drinks-label-color-v5");
        rateFoodWrapper = new Pane(rateFoodLabel);

        rateFoodWrapper.setStyle("-fx-background-color: #0e0e0e;");

        this.rateFoodLabel.layoutXProperty().bind(
                this.rateFoodWrapper.widthProperty().subtract(this.rateFoodLabel.widthProperty()).divide(2)
        );

        this.rateFoodLabel.layoutYProperty().bind(
                this.rateFoodWrapper.heightProperty().subtract(this.rateFoodLabel.heightProperty()).divide(2)
        );

        this.setupBackBtnAndFinalBox();
    }

    public void setupBackBtnAndFinalBox()
    {
        {
            this.boxOfFoodBoxes.getStyleClass().add("header-cell");
            this.noFoodLabel.getStyleClass().add("welcome-label");
            this.backButton.getStyleClass().add("menu-button");
        }

        this.backButton.setPrefSize(300, 50);

        //this.finalBox.getChildren().addAll(boxOfFoodBoxes, backButton);
        this.finalBox.setAlignment(Pos.CENTER);
    }

    public void ratingRestrictions()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("RATING VALUE");
        alert.setHeaderText(null);
        alert.setContentText("Rating must be between 1 and 5 stars!");

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/startMenu.css").toExternalForm());

        Label contentLabel = (Label) dialogPane.lookup(".content");
        if (contentLabel != null) {
            contentLabel.getStyleClass().add("alert-content-text");
        }

        alert.showAndWait();
    }

    public void setupFoodBox() throws ServiceException {

            boxOfFoodBoxes.getChildren().clear();
            finalBox.getChildren().clear();

            List<Order> orders = servicesRpcProxy.getOrderHistoryDataBase().loadOrders();

            for (Order order : orders)
            {
                if (order.getName().equals(yourOrderViewController.getPlacedOrder().getName()))
                {
                    if (!order.getOrderedFood().isEmpty())
                    {
                        int row = 0;

                        for (Food food : order.getOrderedFood())
                        {
                            Label nameLabel = new Label(food.getName() + ": ");
                            Pane labelWrapper = new Pane(nameLabel);
                            TextField ratingField = new TextField();
                            Button submitButton = new Button("SUBMIT");

                            gridPane.add(labelWrapper, 0, row);

                            if (ratingList.get(row).equals(false))
                                gridPane.add(ratingField, 1, row);
                            else
                            {
                                ImageView tickImageView = new ImageView(new Image("green-check-mark-icon-in-a-circle-tick-symbol-in-green-color-2ABNAN7.jpg"));
                                tickImageView.setFitHeight(50);
                                tickImageView.setFitWidth(70);

                                gridPane.add(tickImageView, 1, row);
                            }

                            gridPane.add(submitButton, 2, row);

                            submitButton.setPrefSize(100, 50);
                            ratingField.setPrefSize(70, 50);
                            labelWrapper.setPrefHeight(50);

                            {
                                labelWrapper.setStyle("-fx-background-color: #3355ff;");

                                submitButton.getStyleClass().add("menu-button");
                                nameLabel.getStyleClass().add("welcome-label");

                                ratingField.setStyle("-fx-font-size: 25px;");
                                ratingField.getStyleClass().add("drinks-label");
                            }

                            int finalRow = row;
                            submitButton.setOnAction(e -> {
                                float rating;

                                try {
                                    rating = Float.parseFloat(ratingField.getText());

                                    if (rating < 1 || rating > 5)
                                        this.ratingRestrictions();
                                    else
                                    {
                                        ratingList.set(finalRow, true);

                                        food.updateRating(rating);

                                        ImageView tickImageView = new ImageView(new Image("green-check-mark-icon-in-a-circle-tick-symbol-in-green-color-2ABNAN7.jpg"));
                                        tickImageView.setFitHeight(50);
                                        tickImageView.setFitWidth(70);

                                        gridPane.getChildren().remove(ratingField);
                                        gridPane.add(tickImageView, 1, finalRow);
                                    }

                                } catch (NumberFormatException ex) {

                                    System.out.println("INPUT MUST BE A NUMBER!");
                                }
                            });

                            row++;
                        }

                        boxOfFoodBoxes.getChildren().add(gridPane);
                    }
                    else
                        boxOfFoodBoxes.getChildren().add(noFoodLabel);

                    boxOfFoodBoxes.setAlignment(Pos.CENTER);

                    finalBox.getChildren().addAll(rateFoodWrapper, boxOfFoodBoxes, backButton);

                    break;
                }
            }

    }

    public void setupFoodBoxAfterModify()
    {
        this.boxOfFoodBoxes.getChildren().clear();
        this.isRatedFoodButtonPressed = false;
    }

    public void resetOrderedFoodList() throws ServiceException {
        servicesRpcProxy.resetRateFood();
        this.boxOfFoodBoxes.getChildren().clear();
    }

    public void setupRateFoodButton(BorderPane borderPane, YourOrderViewController yourOrderViewController)
    {
        yourOrderViewController.getRateFoodButton().setOnAction(e -> {
            try {
                this.setupFoodBox();
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }
            borderPane.setCenter(this.getFinalBox());
            this.isRatedFoodButtonPressed = true;
        });
    }

    public void setupBackButton(BorderPane borderPane)
    {
        this.backButton.setOnAction(e -> {
            borderPane.setCenter(null);
            borderPane.setCenter(yourOrderViewController.getFinalDetailsBox());
        });
    }

    public void setupBackButton2(BorderPane borderPane)
    {
        this.backButton.setOnAction(e -> {
            borderPane.setCenter(null);
            borderPane.setCenter(yourOrderViewController.getFinalDetailsBox());
        });
    }

    public VBox getFinalBox() {
        return finalBox;
    }

    public void setRatedFoodButtonPressed(boolean ratedFoodButtonPressed) {
        isRatedFoodButtonPressed = ratedFoodButtonPressed;
    }
}
