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

    /*
    public ArrayList<NameAndScore> getTopLeaders(){
        //TODO use comparators to get arraylist from top points at hashmap
    }

     */
}
