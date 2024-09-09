package Service;

import Model.User;
import Repository.UserRepository;
import SQLDataBase.UserDataBase;
import ServiceInteface.UserServiceI;

import java.util.List;

public class UserService implements UserServiceI {
    private UserRepository userRepository;
    private UserDataBase userDataBase;

    public UserService(UserRepository userRepository, UserDataBase userDataBase)
    {
        this.userRepository = userRepository;
        this.userDataBase = userDataBase;
    }

    public void addUser(int id, String name, String password, String firstName, String lastName, int age, String town, String country, String address)
    {
        User user = new User(id, name, password, firstName, lastName, age, town, country, address);

        this.userRepository.addUser(user);
        this.userDataBase.addUserToDataBase(user);
    }

    public void removeUser(String name)
    {
        userRepository.removeUser(name);
        userDataBase.deleteUserFromDataBase(name);
    }

    public void updateUser(String old_name, int id, String name, String password, String firstName, String lastName, int age, String town, String country, String address)
    {
        User user = new User(id, name, password, firstName, lastName, age, town, country, address);

        userRepository.updateUser(old_name, user);
        userDataBase.updateUserFromDataBase(old_name, user);
    }

    public User findUser(String name)
    {
        return userRepository.findUser(name);
    }

    public List<User> getUsers()
    {
        return userRepository.getUsers();
    }
}
