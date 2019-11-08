package com.spacetech.moovme.Points;

import com.spacetech.moovme.Users.Data;

public class RankingInPointTable {

    private final Data data;
    private final Points points;

    RankingInPointTable(Data data, Points points){
        this.data=data;
        this.points=points;
    }

    public Data getData() {
        return data;
    }

    public Points getPoints() {
        return points;
    }
}
