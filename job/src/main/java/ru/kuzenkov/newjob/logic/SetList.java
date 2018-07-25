package ru.kuzenkov.newjob.logic;

/**
 * Class Set. Абстракция списка множеств.
 *
 * @author Kuzenkov Pavel.
 * @since 21.07.2018
 */
class SetList {

    /**
     * Список множеств.
     */
    private Set[] sets = new Set[10];

    /**
     * "Ползунок"
     */
    private int index = 0;

    /**
     * Добавление нового множества.
     * @param newSet Новое множество.
     */
    public void addSet(Set newSet) {
        newSet.setIndex(this.index + 1);
        this.sets[index++] = newSet;
    }

    public Set[] getSets() {
        return this.sets;
    }

    public int getIndex() {
        return this.index;
    }

    /**
     * Удаление всех множеств.
     */
    public void deleteAllSets() {
        for (int i = 0; i < index; i++) {
            this.sets[i] = null;
        }
        this.index = 0;
    }

    /**
     * Проверка: включает ли данный список множеств в себя указанную точку.
     * @param point Точка.
     * @return "true" если включает, или "false" если нет.
     */
    public boolean isIncludePoint(double point) {
        boolean result = false;
        for (int i = 0; i < index; i++) {
            if (this.sets[i].isIncludePoint(point)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Проверка: включают ли все множества в данном списке множеств в себя указанную точку.
     * @param point Точка.
     * @return "true" если включает, или "false" если нет.
     */
    public boolean isAllSetsIncludePoint(double point) {
        boolean result = true;
        for (int i = 0; i < index; i++) {
            if (!this.sets[i].isIncludePoint(point)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Расчет пересечения всех множеств в списке.
     * @return Список пересечений всех множеств.
     */
    public Set crossOfAllSets() {
        Set result = this.sets[0];
        for (int i = 1; i < index; i++) {
            result = result.cross(this.sets[i]);
            if (result.getSet().isEmpty()) {
                System.out.println("Нет общего пересечения всех множеств");
            }
        }
        return result;
    }

    /**
     * Вычисляем какой интервал ближе к указанной точке.
     * @param i1 Первый интервал.
     * @param i2 Второй интервал.
     * @param point Точка.
     * @return "-1" если первый ближе. "1" если второй ближе. "0" если расстояния одинаковы.
     */
    public int whoIsCloser(Interval i1, Interval i2, double point) {
        double distanceI1 = this.whichPointIsCloser(i1, point);
        double distanceI2 = this.whichPointIsCloser(i2, point);
        return Double.compare(distanceI1, distanceI2);
    }

    /**
     * Вычисляем какой интервал ближе к указанной точке.
     * @param i1 Первый интервал.
     * @param i2 Второй интервал.
     * @param point Точка.
     * @return Ближайший к точке интервал.
     */
    public Interval whichIntervalIsCloser(Interval i1, Interval i2, double point) {
        if (this.whoIsCloser(i1, i2, point) == -1) {
            return i1;
        } else {
            return i2;
        }
    }

    /**
     * Вычисляем какая точка интервала ближе к указанной точке.
     * @param interval Интервал.
     * @param point Точка.
     * @return Ближайшая точка.
     */
    public double whichPointIsCloser(Interval interval, double point) {
        double distanceA = Math.abs(interval.getA() - point);
        double distanceB = Math.abs(interval.getB() - point);
        return distanceA < distanceB ? interval.getA() : interval.getB();
    }

    /**
     * Нахождение подмножества принадлежащего пересечению
     * множеств, максимально близкое к указанной точке.
     * @param x Точка.
     * @return Искомый интервал. Если нет пересечений - метод вернет интервал равный указанной точке.
     */
    public Interval findNearestCrossToPoint(double x) {
        Set crossSet = this.crossOfAllSets();
        Interval result = new Interval(x, x);
        if (crossSet.getSet().isEmpty()) {
            System.out.println("Нет общего пересечения всех множеств");
        } else {
            Interval point = new Interval(x, x);
            crossSet.addInterval(point);
            Interval nearLeft = crossSet.getSet().lower(point);
            Interval nearRight = crossSet.getSet().higher(point);
            result = this.whichIntervalIsCloser(nearLeft, nearRight, x);
        }
        return result;
    }

    /**
     * Нахождение точки принадлежащей пересечению множеств,
     * максимально близкое к указанной точке.
     * @param x Точка.
     * @return Искомая точка. Если нет пересечений - метод вернет укказанную точку точке.
     */
    public double findNearestPointToPoint(double x) {
        return this.whichPointIsCloser(this.findNearestCrossToPoint(x), x);
    }

}
