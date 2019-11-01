package com.spacetech.moovme.Repository;


import android.content.Context;
import android.widget.Toast;

import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.exceptions.ZoneAlreadyExistsException;
import com.spacetech.moovme.exceptions.ZoneDoesNotExistException;

import java.util.HashMap;
import java.util.List;

public class RepositoryZone implements IRepository<HashMap>{

    private HashMap<String, Zone> zones;

    public RepositoryZone(){
        zones=new HashMap<>();
    }


    public void add(int zonePoints, String name, Context ctx) throws ZoneAlreadyExistsException {
        if(zones.get(name) != null){
            throw new ZoneAlreadyExistsException();
        }else {
            zones.put(name, new Zone(name,null));
            Toast.makeText(ctx,name + "has been created", Toast.LENGTH_SHORT).show();
        }
    }

    public void setZones(HashMap zones){
        this.zones = zones;

    }
    public HashMap getCollection(){
        return zones;
    }

    public void delete(String name) throws ZoneDoesNotExistException {
        if(zones.get(name) != null){
            zones.remove(name);
            return;
        } else {
            throw new ZoneDoesNotExistException();
        }
    }

    @Override
    public List<HashMap> getList() {
        return null;
    }

    @Override
    public void add(HashMap objeto) {

    }
}
