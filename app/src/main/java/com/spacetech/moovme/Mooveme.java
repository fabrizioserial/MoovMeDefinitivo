package com.spacetech.moovme;

import Repository.*;
import Users.PhoneNumber;
import Users.*;
import UserManager;

public class Mooveme {

    private static Repository.Repository<Users.User> userRepo;
    private static Repository.UserManager userMananger=new Repository.UserManager(userRepo);

    private static Repository.RepositoryAdmins repositoryAdmins;
    private static Users.Operators activeuser;

    public Mooveme(Repository.UserManager userMananger, Repository.AdminManager adminMananger){
        Mooveme.userMananger = userMananger;
        Mooveme.repositoryAdmins=repositoryAdmins;
    }

    public static void register(String name, PhoneNumber phoneNumber){
        userMananger.add(new Users.Data(name,phoneNumber));
    }

/*
    public static void login(PhoneNumber phoneNumber) {

        if(repositoryUser.testing(phoneNumber)){
            activeuser=repositoryUser.findUser(phoneNumber);
        }
        //usuario no registrado
    }

    public static void loginAdmin(String name){
        if(repositoryAdmins.testing(name)){
            activeuser=repositoryAdmins.findAdmin(name);
        }
        //admin no registrado
    }

 */
}
