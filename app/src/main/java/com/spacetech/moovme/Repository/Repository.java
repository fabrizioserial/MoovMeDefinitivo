package com.spacetech.moovme.Repository;

import com.spacetech.moovme.Exeptions.ElementExistExeption;
import com.spacetech.moovme.Exeptions.ItemDoesNotExistExeption;

import java.util.ArrayList;


public class Repository<T> {

    private ArrayList<T> arrayListGeneric;

    public Repository(){
    }


    public void add(T t) throws ElementExistExeption {
        for
    }

    public ArrayList<T> getRepository(){
        return arrayListGeneric;
    }

    public void remove(T t)throws ItemDoesNotExistExeption {
        if(arrayListGeneric.contains(t)) arrayListGeneric.remove(t);
        else throw new ItemDoesNotExistExeption();
    }
}
