package com.spacetech.moovme.Points;

import com.spacetech.moovme.Exeptions.UserDoesntHavaScoreExeption;
import com.spacetech.moovme.Users.Data;

import java.util.ArrayList;

public class PointTable {

    ArrayList<RankingInPointTable> rankings;
    TopPointUserComparator topPointUserComparator;

    public PointTable() {
        this.rankings = new ArrayList<>();
        this.topPointUserComparator = new TopPointUserComparator();
    }

    public void updateScore(Points aquiredPoints, Data data) {
        rankings.add(new RankingInPointTable(data,aquiredPoints));
        rankings.sort(topPointUserComparator);
    }

    public Points getPoints(Data data) throws UserDoesntHavaScoreExeption {
        for (RankingInPointTable rankingInPointTable:rankings) {
            if(rankingInPointTable.getData().equals(data)){
                return rankingInPointTable.getPoints();
            }
        }
        throw new UserDoesntHavaScoreExeption();
    }

    public ArrayList<RankingInPointTable> getTopLeaders() {
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
