package ru.kuzenkov.newjob.logic;

public class Aggregator {

    private SetList setList = new SetList();

    public SetList getSetList() {
        return this.setList;
    }

    public void newInterval(double a, double b) {
        this.setList.addIntervalToCurrentSet(a, b);
    }

    public void newSet() {
        this.setList.addSet(new Set());
    }

    public void crossOfAllSets() {
        System.out.println(this.setList.crossOfAllSets());
    }

    public void task(double x) {
        System.out.println(this.setList.task(x));
    }


}
