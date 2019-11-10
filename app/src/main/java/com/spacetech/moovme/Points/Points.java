package com.spacetech.moovme.Points;

public class Points {
    private int points;

    public Points(int i) {
        this.points = i;
    }

    public void add(Points calculateAquiredPoints) {
        this.points=this.points+calculateAquiredPoints.getPointsinIntValue();
    }

    public void remove(Points pointsToRemove){
        this.points=this.points-pointsToRemove.getPointsinIntValue();
    }

    public int getPointsinIntValue() {
        return points;
    }
}
