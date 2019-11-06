package com.spacetech.moovme;

import com.spacetech.moovme.Assets.Asset;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.Exeptions.UserDoesntExistException;
import com.spacetech.moovme.Repository.Repository;
import com.spacetech.moovme.Users.Administrator;
import com.spacetech.moovme.Users.Data;
import com.spacetech.moovme.Users.Operators;
import com.spacetech.moovme.Users.User;

import java.util.ArrayList;

public class Mooveme {


    private Repository adminRepository;
    private Repository userRepository;
    private Repository zoneRepository;
    private Repository assetRepository;
    private Repository assetTypeRepository;

    private Operators activeuser;

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
    public void addAssetRepository(Repository asset){
        this.assetRepository = asset;
    }
    public void addAssetTypeRepository(Repository assetType){
        this.assetTypeRepository = assetType;
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

    public User loginUser(User user) throws UserDoesntExistException{
        for(User users:(ArrayList<User>)userRepository.getRepository()){
            if(user.getPhoneNumber().equals(users.getPhoneNumber()) && user.getName().equals(users.getName())){
                return users;
            }
        }
        throw new UserDoesntExistException();
    }
    public User findUser(Data data) throws UserDoesntExistException {
        for(User user:(ArrayList<User>) userRepository.getRepository()){
            if(user.getPhoneNumber().equals(data.getPhoneNumber())){
                return user;
            }
        }
        throw new UserDoesntExistException();
    }

    public Repository<Administrator> getAdministratorRepository(){
        return adminRepository;
    }
    public Repository<User> getUserRepository(){
        return userRepository;
    }
    public Repository<Zone> getZoneRepository(){
        return zoneRepository;
    }
    public Repository<Asset> getAssetRepository(){
        return assetRepository;
    }
    public Repository<AssetType> getAssetTypeRepository(){
        return assetRepository;
    }
}
