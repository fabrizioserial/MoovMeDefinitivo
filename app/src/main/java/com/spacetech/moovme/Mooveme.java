package com.spacetech.moovme;

import com.spacetech.moovme.Repository.Repository;
import com.spacetech.moovme.Users.Administrator;

import java.util.ArrayList;

import Users.Operators;

public class Mooveme {


    private Repository adminRepository;
    private Repository userRepository;
    private Repository zoneRepository;
    private static Operators activeuser;

    public Mooveme(){
    }

    public void addAdminRepository(Repository admin){
        this.admin = admin;
    }
    public void addUserRepository(Repository user){
        this.user = user;
    }
    public void addZoneRepository(Repository zone){
        this.zone = zone;
    }

    public Administrator loginAdministrator(Administrator administrator){
        for(Administrator adminis:(ArrayList<Administrator>)adminRepository.getRepository()){

        }
        return administrator;
    }
}
