package ru.kuzenkov.newjob.logic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IntervalTest {

    @Test
    public void whenBLessAThen() {
        Interval interval = new Interval(10.0, 2.0);
        double resultA = interval.getA();
        double resultB = interval.getB();
        double expectedA = 2.0;
        double expectedB = 10.0;
        assertThat(resultA, is(expectedA));
        assertThat(resultB, is(expectedB));
    }

//    @Test
//    public void isInclude() {
//        Interval interval = new Interval(2.0, 10.0);
//        Interval interval1 = new Interval(3.0, 5.0);
//        Interval interval2 = new Interval(2.0, 10.0);
//        Interval interval3 = new Interval(5.0, 11.0);
//        Interval interval4 = new Interval(1.0, 9.0);
//        boolean result1 = interval.isInclude(interval1);
//        boolean result2 = interval.isInclude(interval2);
//        boolean result3 = interval.isInclude(interval3);
//        boolean result4 = interval.isInclude(interval4);
//        boolean expected1 = true;
//        boolean expected2 = true;
//        boolean expected3 = false;
//        boolean expected4 = false;
//        assertThat(result1, is(expected1));
//        assertThat(result2, is(expected2));
//        assertThat(result3, is(expected3));
//        assertThat(result4, is(expected4));
//    }

//    @Test
//    public void isPartOf() {
//        Interval interval1 = new Interval(3.0, 5.0);
//        Interval interval2 = new Interval(2.0, 10.0);
//        boolean result1 = interval1.isPartOf(interval2);
//        boolean result2 = interval2.isPartOf(interval1);
//        boolean expected1 = true;
//        boolean expected2 = false;
//        assertThat(result1, is(expected1));
//        assertThat(result2, is(expected2));
//    }

    @Test
    public void isCross() {
        Interval interval = new Interval(2.0, 10.0);
        Interval interval1 = new Interval(3.0, 5.0);
        Interval interval2 = new Interval(2.0, 10.0);
        Interval interval3 = new Interval(5.0, 11.0);
        Interval interval4 = new Interval(1.0, 9.0);
        Interval interval5 = new Interval(-1.0, -9.0);
        boolean result1 = interval.isCross(interval1);
        boolean result2 = interval.isCross(interval2);
        boolean result3 = interval.isCross(interval3);
        boolean result4 = interval.isCross(interval4);
        boolean result5 = interval.isCross(interval5);
        boolean expected1 = true;
        boolean expected2 = true;
        boolean expected3 = true;
        boolean expected4 = true;
        boolean expected5 = false;
        assertThat(result1, is(expected1));
        assertThat(result2, is(expected2));
        assertThat(result3, is(expected3));
        assertThat(result4, is(expected4));
        assertThat(result5, is(expected5));
    }

//    @Test
//    public void howCross() {
//        Interval interval = new Interval(2.0, 10.0);
//        Interval interval1 = new Interval(3.0, 5.0);
//        Interval interval2 = new Interval(2.0, 10.0);
//        Interval interval3 = new Interval(5.0, 11.0);
//        Interval interval4 = new Interval(1.0, 9.0);
//        Interval interval5 = new Interval(-1.0, -9.0);
//        int result1 = interval.howCross(interval1);
//        int result2 = interval.howCross(interval2);
//        int result3 = interval.howCross(interval3);
//        int result4 = interval.howCross(interval4);
//        int result5 = interval.howCross(interval5);
//        int expected1 = 0;
//        int expected2 = 0;
//        int expected3 = -1;
//        int expected4 = 1;
//        int expected5 = -2;
//        assertThat(result1, is(expected1));
//        assertThat(result2, is(expected2));
//        assertThat(result3, is(expected3));
//        assertThat(result4, is(expected4));
//        assertThat(result5, is(expected5));
//
//    }

    @Test
    public void hasPoint() {
        Interval interval = new Interval(2.0, 10.0);
        double point1 = 5.01;
        double point2 = 20.0;
        boolean result1 = interval.hasPoint(point1);
        boolean result2 = interval.hasPoint(point2);
        boolean expected1 = true;
        boolean expected2 = false;
        assertThat(result1, is(expected1));
        assertThat(result2, is(expected2));
    }

    @Test
    public void cross() {
        Interval interval = new Interval(2.0, 10.0);
        Interval interval1 = new Interval(3.0, 5.0);
        Interval interval2 = new Interval(2.0, 10.0);
        Interval interval3 = new Interval(5.0, 11.0);
        Interval interval4 = new Interval(1.0, 9.0);
        Interval interval5 = new Interval(-1.0, -9.0);
        Interval result1 = interval.cross(interval1);
        Interval result2 = interval.cross(interval2);
        Interval result3 = interval.cross(interval3);
        Interval result4 = interval.cross(interval4);
        Interval result5 = interval.cross(interval5);
        Interval expected1 = new Interval(3.0, 5.0);
        Interval expected2 = new Interval(2.0, 10.0);
        Interval expected3 = new Interval(5.0, 10.0);
        Interval expected4 = new Interval(2.0, 9.0);
        Interval expected5 = new Interval(0, 0);
        assertThat(result1, is(expected1));
        assertThat(result2, is(expected2));
        assertThat(result3, is(expected3));
        assertThat(result4, is(expected4));
        assertThat(result5, is(expected5));
    }

}