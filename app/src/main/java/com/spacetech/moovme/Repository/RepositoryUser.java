package com.spacetech.moovme.Repository;

import com.spacetech.moovme.Users.PhoneNumber;
import com.spacetech.moovme.Users.User;

import java.util.HashMap;
import java.util.List;

public class RepositoryUser implements IRepository<User>{

    private HashMap<PhoneNumber, User> users ;
    public RepositoryUser(){
        users = new HashMap<>();
    }

    public void addUser(String name, PhoneNumber phoneNumber){ //TODO PASAR EL USUARIO YA CREADO
        users.put(phoneNumber,new User(phoneNumber,name));
    }


    public User findUser(PhoneNumber phoneNumber){
        for (User users: users.values()){
            if(users.getPhoneNumber().getPhoneNumber().equals(phoneNumber.getPhoneNumber())){
                return users;
            }
        }
        return null;
    }

    public boolean testing(PhoneNumber phoneNumber){
        return users.containsKey(phoneNumber);
    }


    @Override
    public List<User> getList() {
        return null;
    }

    @Override
    public void add(User objeto) {

    }
}
