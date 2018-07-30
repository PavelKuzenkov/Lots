package ru.kuzenkov.newjob.logic;

/**
 * Class Aggregator.
 *
 * @author Kuzenkov Pavel.
 * @since 26.07.2018
 */
public class Aggregator {

    /**
     * Список множеств.
     */
    private SetList setList = new SetList();

    public SetList getSetList() {
        return this.setList;
    }

    /**
     * Добавление нового интервала текущее в множество.
     * @param a начало интервала.
     * @param b конец интервала.
     */
    public void newInterval(double a, double b) {
        this.setList.addIntervalToCurrentSet(a, b);
    }

    /**
     * Добавление нового множества.
     */
    public void newSet() {
        this.setList.addSet(new Set());
    }

    /**
     * Вывод списка всех пересечений множеств.
     */
    public void crossOfAllSets() {
        System.out.println(this.setList.crossOfAllSets());
    }

    /**
     * Выполнение задания с точкой.
     * @param x точка.
     */
    public void task(double x) {
        System.out.println(this.setList.task(x));
    }


}
