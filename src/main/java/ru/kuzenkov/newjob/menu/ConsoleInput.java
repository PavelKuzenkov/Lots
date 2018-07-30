package ru.kuzenkov.newjob.menu;

import java.util.Scanner;

/**
 * Class ConsoleInput используется для ввода пользовательских данных из консоли.
 *
 * @author Кузенков Павел
 * @since 25.07.2018
 */

public class ConsoleInput implements Input {

    private Scanner input = new Scanner(System.in);

    /**
     * Работа с консолью.
     * @param question вопрос от программы.
     * @return ввод пользователя в консоль.
     */
    public String ask(String question) {
        System.out.println(question);
        System.out.println("Введите число. Если необходимо ввести +- бесконечность - введите +-infinity");
//        if (this.input.nextLine() == "infinity" || this.input.nextLine() == "+ infinity" || this.input.nextLine() == "+infinity") {
//            return Double.POSITIVE_INFINITY;
//        }
//        if (this.input.nextLine() == "- infinity" || this.input.nextLine() == "-infinity") {
//            return Double.NEGATIVE_INFINITY;
//        }
//        double result = 0;
//        try {
//            return Double.valueOf(this.input.nextLine());
//        } catch (NumberFormatException nfe) {
//            System.out.println("Необходимо ввести корректное значение.");
//        }
        return this.input.nextLine();
    }

    public int ask(String question, int[] range) {
        System.out.print(question);
        int key = Integer.valueOf(this.input.nextLine());
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Out of range.");
        }
        return key;
    }
}
