package ru.kuzenkov.newjob.menu;

import ru.kuzenkov.newjob.logic.Aggregator;

import java.util.ArrayList;

/**
 * Class Menu.
 *
 * @author Kuzenkov Pavel.
 * @since 26.07.2018
 */
class Menu {

    /**
     * Получение данных от пользователя.
     */
    private Input input;

    /**
     * Список множеств.
     */
    private Aggregator aggregator;

    /**
     * Меню.
     */
    private ArrayList<UserAction> actions = new ArrayList<>();

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param aggregator Список множеств.
     */
    Menu(Input input, Aggregator aggregator) {
        this.input = input;
        this.aggregator = aggregator;
    }

    /**
     * Заполнение массива дейтвиями.
     * @param ui
     */
    void fillActions(Start ui) {
        this.actions.add(new AddNewSet(this.actions.size(), "Добавить новое множество."));
        this.actions.add(this.actions.size(), new Exit(ui));
        int[] range = new int[this.actions.size()];
        for (int index = 0; index != range.length; index++) {
            range[index] = index;
        }
        ui.setRange(range);
    }

    /**
     * Дозаполнение массива дейтвиями.
     */
    private void fillAfter1Action() {
        Exit exit = (Exit) Menu.this.actions.get(1);
        Menu.this.actions.remove(1);
        Menu.this.actions.add(new AddNewIntervalToCurrentSet(Menu.this.actions.size(), "Добавить новый интервал в текущее множество."));
        Menu.this.actions.add(Menu.this.actions.size(), new Exit(exit.getUi()));
        int[] range = new int[Menu.this.actions.size()];
        for (int index = 0; index != range.length; index++) {
            range[index] = index;
        }
        exit.getUi().setRange(range);
    }

    /**
     * Дозаполнение массива дейтвиями.
     */
    private void fillAfter2Actions() {
        Exit exit = (Exit) Menu.this.actions.get(2);
        Menu.this.actions.remove(2);
        Menu.this.actions.add(new CrossOfAllSets(Menu.this.actions.size(), "Расчет пересечения всех множеств в списке."));
        Menu.this.actions.add(new PointTask(Menu.this.actions.size(), "Ввести точку и определить ее отношение к пересечению множеств."));
        Menu.this.actions.add(Menu.this.actions.size(), new Exit(exit.getUi()));
        int[] range = new int[Menu.this.actions.size()];
        for (int index = 0; index != range.length; index++) {
            range[index] = index;
        }
        exit.getUi().setRange(range);
    }

    /**
     * Дозаполнение массива дейтвиями.
     */
    private void fillAfter10Actions() {
        Exit exit = (Exit) Menu.this.actions.get(4);
        Menu.this.actions.clear();
        Menu.this.actions.add(new AddNewIntervalToCurrentSet(Menu.this.actions.size(), "Добавить новый интервал в текущее множество."));
        Menu.this.actions.add(new CrossOfAllSets(Menu.this.actions.size(), "Расчет пересечения всех множеств в списке."));
        Menu.this.actions.add(new PointTask(Menu.this.actions.size(), "Ввести точку и определить ее отношение к пересечению множеств."));
        Menu.this.actions.add(Menu.this.actions.size(), new Exit(exit.getUi()));
        int[] range = new int[Menu.this.actions.size()];
        for (int index = 0; index != range.length; index++) {
            range[index] = index;
        }
        exit.getUi().setRange(range);
    }

    /**
     * Действие введенное пользователем.
     * @param key номер действия.
     */
    void select(int key) {
        this.actions.get(key).execute(this.input, this.aggregator);
    }

    /**
     * Вывод меню в консоль.
     */
    void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Внутренний класс AddNewIntervalToCurrentSet. Отвечает за добавление нового интервала в текущее множество.
     */
    class AddNewIntervalToCurrentSet extends BaseAction {

        /**
         * Конструктор класса.
         * @param key номер действия.
         * @param name описание действия.
         */
        AddNewIntervalToCurrentSet(int key, String name) {
            super(key, name);
        }

        /**
         * Реализация добавления нового интервала в текущее множество.
         * @param input ввод данных.
         * @param aggregator список множеств.
         */
        public void execute(Input input, Aggregator aggregator) {
            System.out.println("---------- Добавление нового интервала в текущее множество -------------");
            double a = input.ask("Начало интервала: ", "Если необходимо ввести +- бесконечность - введите +-infinity. ");
            double b = input.ask("Конец интервала: ", "Если необходимо ввести +- бесконечность - введите +-infinity. ");
            aggregator.newInterval(a, b);
            System.out.println();
        }
    }

    /**
     * Внутренний класс AddNewSet. Отвечает за добавление нового множества.
     */
    class AddNewSet extends BaseAction {

        /**
         * Количестов добавленных множеств.
         */
        private int numberOfSet;

        /**
         * Конструктор класса.
         * @param key номер действия.
         * @param name описание действия.
         */
        AddNewSet(int key, String name) {
            super(key, name);
        }

        /**
         * Реализация добавления нового множества.
         * @param input ввод данных.
         * @param aggregator список множеств.
         */
        public void execute(Input input, Aggregator aggregator) {
            System.out.println("------------ Добавлено новое множество --------------");
            aggregator.newSet();
            System.out.println();
            this.numberOfSet++;
            if (this.numberOfSet == 1) {
                Menu.this.fillAfter1Action();
            }
            if (this.numberOfSet == 2) {
                Menu.this.fillAfter2Actions();
            }
            if (this.numberOfSet == 10) {
                Menu.this.fillAfter10Actions();
            }
        }
    }

    /**
     * Внутренний класс CrossOfAllSets. Отвечает за расчет пересечения всех множеств.
     */
    class CrossOfAllSets extends BaseAction {

        /**
         * Конструктор класса.
         * @param key номер действия.
         * @param name описание действия.
         */
        CrossOfAllSets(int key, String name) {
            super(key, name);
        }

        /**
         * Реализация расчета пересечения всех множеств.
         * @param input ввод данных.
         * @param aggregator список множеств.
         */
        public void execute(Input input, Aggregator aggregator) {
            System.out.println("------------ Список пересечений всех множеств. --------------");
            aggregator.crossOfAllSets();
        }
    }

    /**
     * Внутренний класс PointTask. Отвечает за выполнение задания с точкой.
     */
    class PointTask extends BaseAction {

        /**
         * Конструктор класса.
         * @param key номер действия.
         * @param name описание действия.
         */
        PointTask(int key, String name) {
            super(key, name);
        }

        /**
         * Реализация удаления заявки.
         * @param input ввод данных.
         * @param aggregator список множеств.
         */
        public void execute(Input input, Aggregator aggregator) {
            System.out.println("Для любого заданного числа x возвратится число, принадлежащее пересечению"
                    .concat(System.lineSeparator())
                    + "подмножеств, максимально близкое к x (или само число x, если оно принадлежит"
                    .concat(System.lineSeparator())
                    + "пересечению подмножеств).");
            double x = Double.valueOf(input.ask("Введите число Х. Если необходимо ввести +- бесконечность - введите +-infinity: "));
            aggregator.task(x);
            System.out.println();
        }
    }


    /**
     * Внутренний класс Exit. Отвечает за выход из программы.
     */
    private class Exit implements UserAction {

        private Start ui;

        Exit(Start ui) {
            this.ui = ui;
        }

        Start getUi() {
            return ui;
        }

        /**
         * Действие пользователя.
         * @return номер действия в меню.
         */
        public int key() {
            return Menu.this.actions.size() - 1;
        }

        /**
         * Реализация выхода из программы.
         * @param input ввод данных.
         * @param aggregator хранилище заявок.
         */
        public void execute(Input input, Aggregator aggregator) {
            boolean stop = false;
            while (!stop) {
                String key = input.ask("Exit? y/n: ");
                switch (key) {
                    case "y" :
                        this.ui.exit();
                        stop = true;
                        break;
                    case "n" :
                        stop = true;
                        break;
                    default:
                        break;
                }
            }
        }

        /**
         * Вывод в консоль описания действия.
         * @return описание действия.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Exit program.");
        }

    }
}
