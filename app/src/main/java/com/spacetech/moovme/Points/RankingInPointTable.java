package com.spacetech.moovme.Points;

import com.spacetech.moovme.Users.Data;

public class RankingInPointTable {

    private final Data data;
    private final Points points;

    //clase que vendria a ser el puesto de la tabla de puntos(ordena puntos por datos de usuarios)

    public RankingInPointTable(Data data, Points points){
        this.data=data;
        this.points=points;
    }

    public Data getData() {
        return data;
    }

    public Points getPoints() {
        return points;
    }

    public void addPoints(Points points){
        this.points.add(points);
    }
}
