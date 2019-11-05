package com.spacetech.moovme;

import java.util.ArrayList;

import Repository.Repository;
import Users.Operators;

public class Mooveme {


    private Repository admin;
    private static Operators activeuser;

    public Mooveme(ArrayList<Repository> repository){
        SplitRepositories(repository);
    }


    public void SplitRepositories(ArrayList<Repository> arrayList){
        for(Repository repository:arrayList){
            if(repository.GetTypeOfRepository()instanceof Users.Administrator){

            }
        }
    }
}
