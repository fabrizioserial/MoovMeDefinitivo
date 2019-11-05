package com.spacetech.moovme;

import java.util.ArrayList;

import Repository.Repository;
import Users.PhoneNumber;

public class Mooveme {


    private Repository admin;
    private static Users.Operators activeuser;

    public Mooveme(ArrayList<Repository> repository){
        SplitRepositories(repository);
    }

    public static void register(String name, PhoneNumber phoneNumber){
        userMananger.add(new Users.Data(name,phoneNumber));
    }

    public void SplitRepositories(ArrayList<Repository> arrayList){
        for(Repository repository:arrayList){
            if(repository.GetTypeOfRepository()instanceof Users.Administrator){

            }
        }
    }
}
