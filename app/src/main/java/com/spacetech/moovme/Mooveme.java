package com.spacetech.moovme;

import com.spacetech.moovme.Repository.RepositoryAdmin;
import com.spacetech.moovme.Repository.RepositoryAsset;
import com.spacetech.moovme.Repository.RepositoryUser;
import com.spacetech.moovme.Users.Administrator;
import com.spacetech.moovme.Users.PhoneNumber;
import com.spacetech.moovme.Repository.RepositoryZone;
import com.spacetech.moovme.Users.User;

public class Mooveme {

    private static RepositoryUser repositoryUser;
    private static RepositoryAdmin repositoryAdmin;
    private static RepositoryZone repositoryZone;
    private  static RepositoryAsset repositoryAsset;
    private static User activeuser;
    private static String name;

    public Mooveme(RepositoryUser repositoryUser, RepositoryAdmin repositoryAdmin, RepositoryZone repositoryZone, RepositoryAsset repositoryAsset){
        Mooveme.repositoryUser = repositoryUser;
        Mooveme.repositoryAdmin = repositoryAdmin;
        Mooveme.repositoryZone = repositoryZone;
        Mooveme.repositoryAsset = repositoryAsset;
    }

    public static void register(String name, PhoneNumber phoneNumber){
        repositoryUser.addUser(name,phoneNumber);
    }

    public static  RepositoryAsset getRepositoryAsset(){
        return repositoryAsset;
    }

    public static RepositoryUser getRepositoryUser() {
        return repositoryUser;
    }

    public static RepositoryZone getRepositoryZone() {
        return repositoryZone;
    }

    public void setName(String string){
        name = string;
    }
    public static String getName(){
        return name;
    }

    public static boolean login(PhoneNumber phoneNumber) {

        return repositoryUser.testing(phoneNumber);
       // activeuser =  repositoryUser.findUser(phoneNumber);
    }

    public static void registeradmin(Administrator newadmin) {
        repositoryAdmin.addAdmin(newadmin);
    }

    public static boolean loginadmin(String name){
        if(repositoryAdmin.findAdmin(name) == null){
            return false;
        }
        else {
            return true;
        }
    }

    public static RepositoryAdmin getRepositoryAdmin(){
        return repositoryAdmin;
    }
}
