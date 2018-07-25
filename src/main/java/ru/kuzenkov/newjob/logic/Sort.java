package ru.kuzenkov.newjob.logic;

import java.util.Comparator;

class Sort implements Comparator<Interval> {

    @Override
    public int compare(Interval o1, Interval o2) {
        return Double.compare(o1.getA(), o2.getA());
    }
}
