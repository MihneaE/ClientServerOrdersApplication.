package Service;

public class ServiceManager {
    private DrinkService drinkService;
    private FoodService foodService;
    private UserService userService;
    private PlaceOrderManagerService placeOrderManagerService;
    private IndexManager indexManager;

    public ServiceManager(DrinkService drinkService, FoodService foodService, UserService userService, PlaceOrderManagerService placeOrderManagerService, IndexManager indexManager)
    {
        this.drinkService = drinkService;
        this.foodService = foodService;
        this.userService = userService;
        this.placeOrderManagerService = placeOrderManagerService;
        this.indexManager = indexManager;
    }

    public DrinkService getDrinkService() throws ServiceException
    {
        return drinkService;
    }

    public FoodService getFoodService() throws ServiceException
    {
        return foodService;
    }

    public UserService getUserService() throws ServiceException{
        return userService;
    }

    public PlaceOrderManagerService getPlaceOrderManagerService() throws ServiceException {
        return placeOrderManagerService;
    }

    public IndexManager getIndexManager() throws ServiceException {
        return indexManager;
    }
}
