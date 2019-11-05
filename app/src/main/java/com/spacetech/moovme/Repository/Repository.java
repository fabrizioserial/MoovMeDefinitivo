package Repository;

import Exeptions.ElementExistExeption;
import Exeptions.ItemDoesNotExistExeption;

import java.util.ArrayList;

public class Repository<T> {

    private ArrayList<T> arrayListGeneric;
    private final T typeOfRepo ;

    Repository(T typeOfRepo){
       this.typeOfRepo = typeOfRepo;
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
    public T GetTypeOfRepository(){
        return typeOfRepo;
    }
}
