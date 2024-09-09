package ServiceInteface;

import Service.ServiceException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public interface IndexManagerI {
    void saveIndex() throws IOException, ServiceException;
    void increment() throws ServiceException;
    int getCurrentIndex() throws ServiceException;
}
