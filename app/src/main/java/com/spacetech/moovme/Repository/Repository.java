package com.spacetech.moovme.Repository;

import java.util.ArrayList;

import Exeptions.ElementExistExeption;
import Exeptions.ItemDoesNotExistExeption;

public class Repository<T> {

    private ArrayList<T> arrayListGeneric;

    public Repository(){
    }


    public void add(T t) throws ElementExistExeption {
        if(arrayListGeneric.contains(t)){
            throw new ElementExistExeption();
        }
        arrayListGeneric.add(t);
    }

    public ArrayList<T> getRepository(){
        return arrayListGeneric;
    }

    public void remove(T t)throws ItemDoesNotExistExeption {
        if(arrayListGeneric.contains(t)) arrayListGeneric.remove(t);
        else throw new ItemDoesNotExistExeption();
    }
}
