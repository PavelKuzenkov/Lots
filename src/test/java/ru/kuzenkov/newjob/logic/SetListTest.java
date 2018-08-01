package ru.kuzenkov.newjob.logic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SetListTest {

    @Test
    public void addSet() {
        SetList setList = new SetList();
        Set set = new Set();
        Interval interval = new Interval(-10.0, 30.0);
        set.addInterval(interval);
        setList.addSet(set);
        boolean result = setList.getSets()[0] == set;
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test
    public void isIncludePoint() {
        SetList setList = new SetList();
        Set set = new Set();
        set.addInterval(new Interval(-10.0, 30.0));
        setList.addSet(set);
        boolean result1 = setList.isIncludePoint(0.0);
        boolean result2 = setList.isIncludePoint(31);
        boolean expected1 = true;
        boolean expected2 = false;
        assertThat(result1, is(expected1));
        assertThat(result2, is(expected2));
    }

    @Test
    public void isAllSetsIncludePoint() {
        Set set1 = new Set();
        Set set2 = new Set();
        Set set3 = new Set();
        set1.addInterval(new Interval(-1.0, 3.0));
        set2.addInterval(new Interval(-10.0, 5.0));
        set3.addInterval(new Interval(-5.0, 80.0));
        SetList setList = new SetList();
        setList.addSet(set1);
        setList.addSet(set2);
        setList.addSet(set3);
        boolean result1 = setList.isAllSetsIncludePoint(0.0);
        boolean result2 = setList.isAllSetsIncludePoint(10);
        boolean expected1 = true;
        boolean expected2 = false;
        assertThat(result1, is(expected1));
        assertThat(result2, is(expected2));
    }

    @Test
    public void crossOfAllSets() {
        Set set1 = new Set();
        Set set2 = new Set();
        Set set3 = new Set();
        Set set4 = new Set();
        set1.addNotCrossInterval(new Interval(-1.0, 3.0));
        set1.addNotCrossInterval(new Interval(10.0, 12.0));
        set2.addNotCrossInterval(new Interval(-10.0, 5.0));
        set2.addNotCrossInterval(new Interval(8.0, 14.0));
        set3.addNotCrossInterval(new Interval(-5.0, 80.0));
        set4.addNotCrossInterval(new Interval(0.0, 11.0));
        SetList setList = new SetList();
        setList.addSet(set1);
        setList.addSet(set2);
        setList.addSet(set3);
        setList.addSet(set4);
        Set result1 = setList.crossOfAllSets();
        Set expected1 = new Set();
        expected1.addNotCrossInterval(new Interval(0.0, 3.0));
        expected1.addNotCrossInterval(new Interval(10.0, 11.0));
        assertThat(result1, is(expected1));
        setList.deleteAllSets();
        set1.deleteAllIntervals();
        set2.deleteAllIntervals();
        set3.deleteAllIntervals();
        set1.addNotCrossInterval(new Interval(-1.0, 3.0));
        set1.addNotCrossInterval(new Interval(10.0, 12.0));
        set2.addNotCrossInterval(new Interval(-100.0, -50.0));
        set2.addNotCrossInterval(new Interval(80.0, 90.0));
        set3.addNotCrossInterval(new Interval(100.0, Double.POSITIVE_INFINITY));
        setList.addSet(set1);
        setList.addSet(set2);
        setList.addSet(set3);
        Set result2 = setList.crossOfAllSets();
        Set expected2 = new Set();
        assertThat(result2, is(expected2));
    }

    @Test
    public void whoIsCloser() {
        Interval interval1 = new Interval(-10.0, -1.0);
        Interval interval2 = new Interval(2.0, 10.0);
        Interval interval3 = new Interval(-11.0, -1.0);
        double point = 0.0;
        SetList setList = new SetList();
        int result1 = setList.whoIsCloser(interval1, interval2, point);
        int expected1 = -1;
        int result2 = setList.whoIsCloser(interval2, interval1, point);
        int expected2 = 1;
        int result3 = setList.whoIsCloser(interval1, interval3, point);
        int expected3 = 0;
        assertThat(result1, is(expected1));
        assertThat(result2, is(expected2));
        assertThat(result3, is(expected3));
    }

    @Test
    public void whichPointIsCloser() {
        Interval interval1 = new Interval(-10.0, -1.0);
        Interval interval2 = new Interval(2.0, 10.0);
        double point = 0.0;
        SetList setList = new SetList();
        double result1 = setList.whichPointIsCloser(interval1, point);
        double expected1 = -1.0;
        double result2 = setList.whichPointIsCloser(interval2, point);
        double expected2 = 2;
        assertThat(result1, is(expected1));
        assertThat(result2, is(expected2));
    }

    @Test
    public void findNearestCrossToPoint() {
        Set set1 = new Set();
        Set set2 = new Set();
        Set set3 = new Set();
        Set set4 = new Set();
        set1.addNotCrossInterval(new Interval(-1.0, 3.0));
        set1.addNotCrossInterval(new Interval(10.0, 12.0));
        set2.addNotCrossInterval(new Interval(-10.0, 5.0));
        set2.addNotCrossInterval(new Interval(8.0, 14.0));
        set3.addNotCrossInterval(new Interval(-5.0, 80.0));
        set4.addNotCrossInterval(new Interval(0.0, 11.0));
        SetList setList = new SetList();
        setList.addSet(set1);
        setList.addSet(set2);
        setList.addSet(set3);
        setList.addSet(set4);
        Set setCross = setList.crossOfAllSets();
        Interval result = setList.findNearestCrossToPoint(5, setCross);
        Interval expected = new Interval(0.0, 3.0);
        assertThat(result, is(expected));
    }

    @Test
    public void findNearestPointToPoint() {
        Set set1 = new Set();
        Set set2 = new Set();
        Set set3 = new Set();
        Set set4 = new Set();
        set1.addNotCrossInterval(new Interval(-1.0, 3.0));
        set1.addNotCrossInterval(new Interval(10.0, 12.0));
        set2.addNotCrossInterval(new Interval(-10.0, 5.0));
        set2.addNotCrossInterval(new Interval(8.0, 14.0));
        set3.addNotCrossInterval(new Interval(-5.0, 80.0));
        set4.addNotCrossInterval(new Interval(0.0, 11.0));
        SetList setList = new SetList();
        setList.addSet(set1);
        setList.addSet(set2);
        setList.addSet(set3);
        setList.addSet(set4);
        Set setCross = setList.crossOfAllSets();
        double result = setList.findNearestPointToPoint(5, setCross);
        double expected = 3;
        assertThat(result, is(expected));
    }

    @Test
    public void findNearestPointFromCrossToPoint() {
        Set set1 = new Set();
        Set set2 = new Set();
        set1.addNotCrossInterval(new Interval(1, 4));
        set1.addNotCrossInterval(new Interval(7, 10));
        set2.addNotCrossInterval(new Interval(0, 10));
        SetList setList = new SetList();
        setList.addSet(set1);
        setList.addSet(set2);
//        Set setCross = setList.crossOfAllSets();
        double result = setList.task(6);
        double expected = 7;
        assertThat(result, is(expected));
    }

    @Test
    public void task() {
        Set set1 = new Set();
        Set set2 = new Set();
        set1.addNotCrossInterval(new Interval(3, 5));
        set2.addNotCrossInterval(new Interval(4, 7));
        SetList setList = new SetList();
        setList.addSet(set1);
        setList.addSet(set2);
        double result = setList.task(1);
        double expected = 4;
        assertThat(result, is(expected));
    }

}