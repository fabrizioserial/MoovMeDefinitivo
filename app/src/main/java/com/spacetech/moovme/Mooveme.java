package com.spacetech.moovme;

import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.Repository.Repository;
import com.spacetech.moovme.Users.Administrator;
import com.spacetech.moovme.Users.User;

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
        this.adminRepository = admin;
    }
    public void addUserRepository(Repository user){
        this.userRepository = user;
    }
    public void addZoneRepository(Repository zone){
        this.zoneRepository = zone;
    }

    public Administrator loginAdministrator(Administrator administrator) throws AdministratorDoesntFoundExeption{
        boolean Found= false;
        for(Administrator adminis:(ArrayList<Administrator>)adminRepository.getRepository()){
            if(administrator.getName().equals(adminis.getName())){
                Found = true;
                return adminis;
            }
        }
        throw new AdministratorDoesntFoundExeption();
    }

    public User loginUser(User user){
        for(User users:(ArrayList<User>)userRepository.getRepository()){
            if(user.get)
        }
    }
    public

    public Repository<Administrator> getAdministratorRepository(){
        return adminRepository;
    }
    public Repository<User> getUserRepository(){
        return userRepository;
    }
    public Repository<Zone> getZoneRepository(){
        return zoneRepository;
    }
}
