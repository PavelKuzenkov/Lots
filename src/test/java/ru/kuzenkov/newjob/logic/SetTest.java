package ru.kuzenkov.newjob.logic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SetTest {

    @Test
    public void deleteAllIntervals() {
        Set set = new Set();
        Interval interval1 = new Interval(-10.0, 0.0);
        Interval interval3 = new Interval(10.0, 30.0);
        set.addNotCrossInterval(interval1);
        set.addNotCrossInterval(interval3);
        boolean result1 = set.getSet().isEmpty();
        boolean expected1 = false;
        assertThat(result1, is(expected1));
        set.deleteAllIntervals();
        boolean result2 = set.getSet().isEmpty();
        boolean expected2 = true;
        assertThat(result2, is(expected2));
    }

//    @Test
//    public void addInterval() {
//        Set set = new Set();
//        Interval interval = new Interval(-10.0, 30.0);
//        set.addInterval(interval);
//        boolean result = set.getSet().contains(interval);
//        boolean expected = true;
//        assertThat(result, is(expected));
//    }

    @Test
    public void addNotCrossInterval() {
        Set set = new Set();
        Interval interval1 = new Interval(-10.0, 0.0);
        Interval interval3 = new Interval(10.0, 30.0);
        set.addNotCrossInterval(interval1);
        set.addNotCrossInterval(interval3);
        boolean result4 = set.getSet().contains(interval1);
        boolean result6 = set.getSet().contains(interval3);
        boolean expected4 = true;
        boolean expected6 = true;
        assertThat(result4, is(expected4));
        assertThat(result6, is(expected6));
    }

    @Test(expected = LogicException.class)
    public void addNotCrossIntervalException() {
        Set set = new Set();
        Interval interval1 = new Interval(-10.0, 0.0);
        Interval interval2 = new Interval(-1.0, 20.0);
        Interval interval3 = new Interval(10.0, 30.0);
        set.addNotCrossInterval(interval1);
        set.addNotCrossInterval(interval2);
        set.addNotCrossInterval(interval3);
    }

    @Test
    public void isIncludePoint() {
        Set set = new Set();
        Interval interval = new Interval(-10.0, 30.0);
        set.addInterval(interval);
        boolean result1 = set.isIncludePoint(0.0);
        boolean result2 = set.isIncludePoint(31);
        boolean expected1 = true;
        boolean expected2 = false;
        assertThat(result1, is(expected1));
        assertThat(result2, is(expected2));
    }

//    @Test
//    public void isCross() {
//        Set set1 = new Set();
//        Set set2 = new Set();
//        set1.addInterval(new Interval(1.0, 10.0));
//        set2.addInterval(new Interval(11.0, 15.0));
//        boolean result1 = set1.isCross(set2);
//        boolean expected1 = false;
//        set1.addInterval(new Interval(10.5, 12.0));
//        boolean result2 = set1.isCross(set2);
//        boolean expected2 = true;
//        assertThat(result1, is(expected1));
//        assertThat(result2, is(expected2));
//    }

    @Test
    public void cross() {
        Set set1 = new Set();
        Set set2 = new Set();
        set1.addInterval(new Interval(1.0, 10.6));
        set1.addInterval(new Interval(11.0, 15.0));
        set1.addInterval(new Interval(-10.0, 0.5));
        set2.addInterval(new Interval(10.5, 12.0));
        set2.addInterval(new Interval(-5.5, -12.0));
        Set result = set1.cross(set2);
        Set expected = new Set();
        expected.addInterval(new Interval(10.5, 10.6));
        expected.addInterval(new Interval(11.0, 12.0));
        expected.addInterval(new Interval(-10.0, -5.5));
        assertThat(result, is(expected));
    }

}