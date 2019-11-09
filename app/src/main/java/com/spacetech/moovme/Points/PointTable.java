package com.spacetech.moovme.Points;

import com.spacetech.moovme.Exceptions.UserDoesntHavaScoreException;
import com.spacetech.moovme.Users.Data;

import java.util.ArrayList;

public class PointTable {

    ArrayList<RankingInPointTable> rankings;
    TopPointUserComparator topPointUserComparator;

    public PointTable() {
        this.rankings = new ArrayList<>();
        this.topPointUserComparator = new TopPointUserComparator();
    }

    public void updateScore(Points aquiredPoints, Data data) {//actualiza los puntos
        boolean wasActualized=false;
        for (RankingInPointTable ranking:rankings) {
            if(ranking.getData().equals(data)){
                ranking.addPoints(aquiredPoints);
                wasActualized=true;
            }
        }
        if(!wasActualized){
            rankings.add(new RankingInPointTable(data,aquiredPoints));
            rankings.sort(topPointUserComparator);
        }
    }

    public Points getPoints(Data data) throws UserDoesntHavaScoreException {//obtiene los puntos de un ranking especifico
        for (RankingInPointTable rankingInPointTable:rankings) {
            if(rankingInPointTable.getData().equals(data)){
                return rankingInPointTable.getPoints();
            }
        }
        throw new UserDoesntHavaScoreException();
    }

    public ArrayList<RankingInPointTable> getTopLeaders() {//obtiene los lideres
        if(rankings.size()>3){
            ArrayList<RankingInPointTable> rankingInPointTables=new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                rankingInPointTables.add(rankings.get(i));
            }
            return rankingInPointTables;
        }
        else{
            return new ArrayList<>(rankings);
        }
    }
}
