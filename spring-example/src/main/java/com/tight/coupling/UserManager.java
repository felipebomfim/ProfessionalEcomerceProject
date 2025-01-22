package com.tight.coupling;

public class UserManager {
    private UserDataBase userDataBase = new UserDataBase();
    public String getUserInfo(){
        return userDataBase.getUserDetails();
    }
}
