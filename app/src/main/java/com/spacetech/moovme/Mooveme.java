package com.spacetech.moovme;

import com.spacetech.moovme.Assets.Asset;
import com.spacetech.moovme.Assets.AssetParking;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.Exceptions.AdministratorDoesntFoundException;
import com.spacetech.moovme.Exceptions.ElementExistException;
import com.spacetech.moovme.Exceptions.UserAlreadyExistException;
import com.spacetech.moovme.Exceptions.UserDoesntExistException;
import com.spacetech.moovme.Repository.ListAssetBachCodes;
import com.spacetech.moovme.Repository.Repository;
import com.spacetech.moovme.Users.Administrator;
import com.spacetech.moovme.Users.Data;
import com.spacetech.moovme.Users.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Mooveme {


    private Repository<Administrator> adminRepository;
    private Repository<User> userRepository;
    private Repository<Zone> zoneRepository;
    private Repository<Asset> assetRepository;
    private Repository<AssetType> assetTypeRepository;
    private ListAssetBachCodes listAssetBachCodes;
    private HashMap<AssetParking,Zone> assetParkingZoneHashMap;
    private Repository<AssetParking> assetParkingRepository;

    public Mooveme(){
        listAssetBachCodes = new ListAssetBachCodes();
        assetParkingZoneHashMap = new HashMap<>();
    }

    public void addAdminRepository(Repository<Administrator> admin){
        this.adminRepository = admin;
    }
    public void addUserRepository(Repository<User> user){
        this.userRepository = user;
    }
    public void addZoneRepository(Repository<Zone> zone){
        this.zoneRepository = zone;
    }
    public void addAssetRepository(Repository asset){
        this.assetRepository = asset;
    }
    public void addAssetTypeRepository(Repository assetType){
        this.assetTypeRepository = assetType;
    }
    public void addAssetParkingRepository(Repository<AssetParking> assetParkingRepository) {
        this.assetParkingRepository = assetParkingRepository;
    }



    public Administrator loginAdministrator(Administrator administrator) throws AdministratorDoesntFoundException {
        for(Administrator adminis:(ArrayList<Administrator>)adminRepository.getRepository()){
            if(administrator.getName().equals(adminis.getName())){
                return adminis;
            }
        }
        throw new AdministratorDoesntFoundException();
    }

    public User loginUser(User user) throws UserDoesntExistException{
        for(User users: userRepository.getRepository()){
            if(user.getPhoneNumber().equals(users.getPhoneNumber())){
                return users;
            }
        }
        throw new UserDoesntExistException();
    }

    public void registerUser(User user) throws UserAlreadyExistException {
        try {
            userRepository.add(user);
        } catch (ElementExistException elementExistException) {
            throw new UserAlreadyExistException();
        }
    }
    public User findUser(Data data) throws UserDoesntExistException {
        for(User user: userRepository.getRepository()){
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
        return assetTypeRepository;
    }
    public Repository<AssetParking> getAssetParkingRepository(){return assetParkingRepository;}

    public Administrator findAdmin(String name) throws AdministratorDoesntFoundException {
        for(Administrator administrator: adminRepository.getRepository()){
            if(administrator.getName().equals(name)){
                return administrator;
            }
        }
        throw new AdministratorDoesntFoundException();
    }

    public int getListAssetBachCodes(){
        return listAssetBachCodes.createNewCode();
    }

    public User getUser(Data data) throws UserDoesntExistException{
        for(User user:userRepository.getRepository()){
            if(user.getPhoneNumber().equals(data.getPhoneNumber())){
                return user;
            }
        }
        throw new UserDoesntExistException();
    }

    public void addParking(AssetParking assetParking,Zone zone) throws ElementExistException {
        assetParkingRepository.add(assetParking);

        //assetParkingZoneHashMap.put(assetParking,zone);
    }


}
