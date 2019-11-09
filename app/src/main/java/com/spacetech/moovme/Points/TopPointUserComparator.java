package com.spacetech.moovme.Points;

import java.util.Comparator;

public class TopPointUserComparator implements Comparator<RankingInPointTable> {

    //comparador para ordenar tabla de lideres
    @Override
    public int compare(RankingInPointTable rankingInPointTable, RankingInPointTable t1) {
        return rankingInPointTable.getPoints().getPointsinIntValue()-t1.getPoints().getPointsinIntValue();
    }
}
