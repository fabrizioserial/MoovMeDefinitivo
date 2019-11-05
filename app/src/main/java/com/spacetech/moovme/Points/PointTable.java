package com.spacetech.moovme.Points;

import com.spacetech.moovme.Users.Data;

import java.util.HashMap;

public class PointTable {

    HashMap<Data,Integer> storedPoints;

    public void updateScore(Integer aquiredPoints, Data data) {
        storedPoints.put(data,aquiredPoints);
    }

    public Integer getPoints(Data data){
        return storedPoints.get(data);
    }

    /*
    public ArrayList<NameAndScore> getTopLeaders(){
        //TODO use comparators to get arraylist from top points at hashmap
    }

     */
}
