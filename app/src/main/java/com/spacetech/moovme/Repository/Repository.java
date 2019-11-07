package com.spacetech.moovme.Repository;

import com.spacetech.moovme.Exeptions.ElementExistExeption;
import com.spacetech.moovme.Exeptions.ItemDoesNotExistExeption;

import java.util.ArrayList;


public class Repository<T> {

    private ArrayList<T> arrayListGeneric;

    public Repository(){
        arrayListGeneric = new ArrayList<>();
    }


    public void add(T t) throws ElementExistExeption {
        for(T element: arrayListGeneric){
            if(t.equals(element)){
                throw new ElementExistExeption();
            }
        }
        arrayListGeneric.add(t);
    }

    public ArrayList<T> getRepository(){
        return arrayListGeneric;
    }

    public void remove(T t)throws ItemDoesNotExistExeption {
        for(T element: arrayListGeneric){
            if(t.equals(element)){
                arrayListGeneric.remove(t);
            }
        }
        throw new ItemDoesNotExistExeption();
    }
}
