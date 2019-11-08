package com.spacetech.moovme.Points;

import java.util.Comparator;

public class TopPointUserComparator implements Comparator<RankingInPointTable> {


    @Override
    public int compare(RankingInPointTable rankingInPointTable, RankingInPointTable t1) {
        return rankingInPointTable.getPoints().getPointsinIntValue()-t1.getPoints().getPointsinIntValue();
    }
}
