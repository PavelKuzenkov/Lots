package ru.kuzenkov.newjob.menu;

import ru.kuzenkov.newjob.logic.Aggregator;

/**
 * Class StartUI точка входа в программу.
 *
 * @author Кузенков Павел
 * @since 25.07.2018
 */
public class Start {

    /**
     * Индикатор работы программы.
     * Программа работает до тех пор, пока заначение ложно.
     */
    private boolean exit;

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Список множеств.
     */
    private final Aggregator aggregator;

    /**
     * Диапозон пунктов меню.
     */
    private int[] range;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param aggregator список множеств.
     */
    private Start(Input input, Aggregator aggregator) {
        this.input = input;
        this.aggregator = aggregator;
        this.exit = false;
    }

    /**
     * Основой цикл программы.
     */
    private void init() {
        Menu menu = new Menu(this.input, this.aggregator);
        menu.fillActions(this);
        while (!this.exit) {
            menu.show();
            menu.select(input.ask("Select: ", this.range));
        }
    }

    /**
     * Выход из программы.
     */
    void exit() {
        this.exit = true;
    }

    void setRange(int[] range) {
        this.range = range;
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new Start(new ValidateInput(new ConsoleInput()), new Aggregator()).init();
    }
}