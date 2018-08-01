package ru.kuzenkov.newjob.logic;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Class Set. Абстракция множества.
 *
 * @author Kuzenkov Pavel.
 * @since 21.07.2018
 */
class Set {

    /**
     * Множество интервалов.
     */
    private ConcurrentSkipListSet<Interval> set = new ConcurrentSkipListSet<Interval>(new Sort());

    /**
     * Порядковый номер множества.
     */
    private int index;

    void setIndex(int index) {
        this.index = index;
    }

    ConcurrentSkipListSet<Interval> getSet() {
        return this.set;
    }

    /**
     * Удаление всех интервалов в множестве.
     */
    void deleteAllIntervals() {
        this.set.clear();
    }

    /**
     * Добавление нового интервала.
     * @param newInterval Новый интервал.
     */
    void addInterval(Interval newInterval) {
        this.set.add(newInterval);
    }

    /**
     * Добавление нового интервала с проверкой на пересечение с уже существующими интервалами.
     * Если новый интервал пересекается с существующим в списке интервалом - добавления не произойдет.
     * @param newInterval Новый интервал.
     */
    void addNotCrossInterval(Interval newInterval) {
        if (this.set.isEmpty()) {
            this.addInterval(newInterval);
        } else {
            for (Interval interval : this.set) {
                if (!interval.isCross(newInterval)) {
                    this.addInterval(newInterval);
                } else {
                    throw new LogicException("Данный интервал пересекается с уже существующими в этом множестве.");
                }
            }
        }
    }

    /**
     * Проверка: включает ли данное множество в себя указанную точку.
     * @param point Точка.
     * @return "true" если включает, или "false" если нет.
     */
    boolean isIncludePoint(double point) {
        boolean result = false;
        for (Interval interval : this.set) {
            if (interval.hasPoint(point)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Проверка: Есть ли пересечение между исходным множеством и входящим.
     * @param incomingSet Входящее множество.
     * @return "true" если множества пересекаются, или "false" если нет.
     */
    private boolean isCross(Set incomingSet) {
        boolean result = false;
        for (Interval interval : this.set) {
            for (Interval incomingInterval : incomingSet.getSet()) {
                if (interval.isCross(incomingInterval)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Расчет пересечений двух множеств.
     * @param incomingSet Входящее множество.
     * @return Список всех пересечений двух множеств. Если интервалы не пересекаются вернется пустой список.
     */
    Set cross(Set incomingSet) {
        Set result = new Set();
        if (this.isCross(incomingSet)) {
            for (Interval interval : this.set) {
                for (Interval incomingInterval : incomingSet.getSet()) {
                    if (interval.isCross(incomingInterval)) {
                        result.addInterval(interval.cross(incomingInterval));
                    }
                }
            }
        }
        return result;
    }

    /**
     * Переопределение метода equals.
     * @param o Сравниваемое множество.
     * @return "true" если множества равны, или "false" если нет.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Set set1 = (Set) o;

        if (index != set1.index) {
            return false;
        }
        return set != null ? set.equals(set1.set) : set1.set == null;
    }

    /**
     * Переопределение метода hashCode.
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        int result = set != null ? set.hashCode() : 0;
        result = 31 * result + index;
        return result;
    }

    /**
     * Переопределение метода toString.
     * @return Текстовое представление множества.
     */
    @Override
    public String toString() {
        return "Множество №" + (index) + ": "
                .concat(System.lineSeparator()) + this.set;
    }
}
