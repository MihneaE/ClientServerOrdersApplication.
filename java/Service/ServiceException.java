package Service;

public class ServiceException extends Exception{

    public ServiceException(){
        super();
    }

    public ServiceException(String message) {
        super(message);
    }
}
