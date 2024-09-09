package ServiceInteface;

import Model.User;
import Service.ServiceException;

import java.util.List;

public interface UserServiceI {
    void addUser(int id, String name, String password, String firstName, String lastName, int age, String town, String country, String address) throws ServiceException;
    void removeUser(String name) throws ServiceException;
    void updateUser(String old_name, int id, String name, String password, String firstName, String lastName, int age, String town, String country, String address) throws ServiceException;
    User findUser(String name) throws ServiceException;
    List<User> getUsers() throws ServiceException;
}
