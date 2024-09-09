package Repository;

import Model.User;

import java.util.Iterator;
import java.util.List;

public class UserRepository {
    private List<User> users;

    public UserRepository(List<User> users)
    {
        this.users = users;
    }

    public void addUser(User user)
    {
        users.add(user);
    }

    public void removeUser(String name)
    {
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext())
        {
            User user = iterator.next();
            if (user.getName().equals(name))
                iterator.remove();
        }
    }

    public void updateUser(String name, User user)
    {
        for (int i = 0; i < users.size(); ++i)
            if (users.get(i).getName().equals(name))
                users.set(i, user);
    }

    public User findUser(String name)
    {
        User user = new User();

        for (int i = 0; i < users.size(); ++i)
            if (users.get(i).getName().equals(name))
                user = users.get(i);

        return user;
    }

    public List<User> getUsers() {
        return users;
    }
}
