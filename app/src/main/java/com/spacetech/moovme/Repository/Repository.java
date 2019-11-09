package com.spacetech.moovme.Repository;

import com.spacetech.moovme.Exceptions.ElementExistException;
import com.spacetech.moovme.Exceptions.ItemDoesNotExistException;

import java.util.ArrayList;


public class Repository<T> { //repositorio generico bastante claro

    private ArrayList<T> arrayListGeneric;

    public Repository(){
        arrayListGeneric = new ArrayList<>();
    }


    public void add(T t) throws ElementExistException {
        for(T element: arrayListGeneric){
            if(t.equals(element)){
                throw new ElementExistException();
            }
        }
        arrayListGeneric.add(t);
    }

    public ArrayList<T> getRepository(){
        return arrayListGeneric;
    }

    public void remove(T t)throws ItemDoesNotExistException {
        T elementToDelete = null;
        for(T element: arrayListGeneric){
            if(t.equals(element)){
                elementToDelete = t;
            }
        }
        arrayListGeneric.remove(t);
        if(elementToDelete == null){
            throw new ItemDoesNotExistException();
        }else{
            arrayListGeneric.remove(elementToDelete);
        }

    }
}
