package Networking.DTO;

import Model.User;

import java.io.Serializable;

public class UpdateUserRequest implements Serializable {
    private String oldName;
    private User user;


    public UpdateUserRequest(String oldName, User user) {
        this.oldName = oldName;
        this.user = user;
    }


    public String getOldName() {
        return oldName;
    }

    public User getUser() {
        return user;
    }
}
