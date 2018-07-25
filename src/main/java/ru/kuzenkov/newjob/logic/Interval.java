package ru.kuzenkov.newjob.logic;

/**
 * Class Interval. Абстракция подмножества.
 *
 * @author Kuzenkov Pavel.
 * @since 19.07.2018
 */
class Interval {

    /**
     * Начало интервала.
     */
    private double a;

    /**
     * Конец интервала.
     */
    private double b;

    /**
     * Конструктор. Если при вводе точки перепутаны местами - рассавит в правильном порядке.
     * @param a Начало интервала.
     * @param b Конец интервала.
     */
    Interval(double a, double b) {
        if (a < b) {
            this.a = a;
            this.b = b;
        } else {
            this.a = b;
            this.b = a;
        }
    }

    double getA() {
        return this.a;
    }

    double getB() {
        return this.b;
    }

    private void setA(double a) {
        this.a = a;
    }

    private void setB(double b) {
        this.b = b;
    }

    /**
     * Проверка: исходный интервал включает ли в себя входящий интервал.
     * @param interval Входящий интервал.
     * @return "true" если включает, или "false" если нет.
     */
    boolean isInclude(Interval interval) {
        return this.a <= interval.getA() && interval.getB() <= this.b;
    }

    /**
     * Проверка: входящий интервал включает ли в себя исходный интервал.
     * @param interval Входящий интервал.
     * @return "true" если включает, или "false" если нет.
     */
    boolean isPartOf(Interval interval) {
        return interval.getA() < this.a && this.b < interval.getB();
    }

    /**
     * Проверка интервалов на пересечение.
     * @param interval Входящий интервал.
     * @return "true" если интервалы пересекаются, или "false" если нет.
     */
    boolean isCross(Interval interval) {
        return this.isPartOf(interval)
                || this.a <= interval.getA() && interval.getA() <= this.b
                || this.a <= interval.getB() && interval.getB() <= this.b;
    }

    /**
     * Проверка того, как пересекаются интервалы.
     * @param interval Входящий интервал.
     * @return -1 если исходный интервал пересекается с началом входящего интервала,
     * 1 если с концом входящего интервала, 0 если входящий интервал полностью находится в исходном,
     * 2 если исходный интервал полностью находится во входящем -2 если интервалы не пересекаются.
     */
    int howCross(Interval interval) {
        int result;
        if (!this.isCross(interval)) {
            return -2;
        }
        if (this.isPartOf(interval)) {
            return 2;
        }
        if (this.isInclude(interval)) {
            result = 0;
        } else {
            result = this.a <= interval.getA() && interval.getA() <= this.b ? -1 : 1;
        }
        return result;
    }

    /**
     * Проверка: содержит ли интервал в себе указанную точку.
     * @param point Точка.
     * @return "true" если содержит, или "false" если нет.
     */
    boolean hasPoint(double point) {
        return this.a <= point && point <= this.b;
    }

    /**
     * Расчет пересечения двух интервалов.
     * @param interval Входящий интервал.
     * @return Пересечение интервалов. Если интервалы не пересекаются вернется интервал с точками 0 0
     * и на экран выведется сообщение.
     */
    Interval cross(Interval interval) {
        Interval result = new Interval(0, 0);
        if (this.isCross(interval)) {
            if (this.isPartOf(interval)) {
                result = this;
            } else if (this.isInclude(interval)) {
                result = interval;
            } else if (this.howCross(interval) == -1) {
                result.setA(interval.getA());
                result.setB(this.b);
            } else {
                result.setA(this.a);
                result.setB(interval.getB());
            }
        } else {
            System.out.println("Интервалы не пересекаются.");
        }
        return result;
    }

    /**
     * Переопределение метода equals.
     * @param o Сравниваемый интервал.
     * @return "true" если интервалы равны, или "false" если нет.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Interval interval = (Interval) o;

        if (Double.compare(interval.a, a) != 0) {
            return false;
        }
        return Double.compare(interval.b, b) == 0;
    }

    /**
     * Переопределение метода hashCode.
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(a);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Переопределение метода toString.
     * @return Текстовое представление интервала.
     */
    @Override
    public String toString() {
        return "[" + this.a + ", " + this.b + "]";
    }
}
