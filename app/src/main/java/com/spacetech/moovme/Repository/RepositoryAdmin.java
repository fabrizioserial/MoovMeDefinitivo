package com.spacetech.moovme.Repository;

import com.spacetech.moovme.Users.Administrator;

import java.util.HashMap;
import java.util.List;

public class RepositoryAdmin implements IRepository<HashMap>{

    private HashMap<String, Administrator>  admins ;

    public RepositoryAdmin(){
        admins = new HashMap<>();
    }

    public void addAdmin(Administrator newadministrator){
        admins.put(newadministrator.getName(),newadministrator);
    } //TODO PASAR EL ADMIN YA CREADO

    public HashMap getCollection(){
        return admins;
    }


    public Administrator findAdmin(String name){
        for (Administrator adminis: admins.values()) {
            if(adminis.getName().equals(name)){
                return adminis;
            }
        }return null;
    }
    public void setAdmin(HashMap hashMap) {
        this.admins = hashMap;
    }

    @Override
    public List<HashMap> getList() {
        return null;
    }

    @Override
    public void add(HashMap objeto) {

    }
}
