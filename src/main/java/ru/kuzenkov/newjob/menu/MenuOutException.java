package ru.kuzenkov.newjob.menu;

/**
 * Class MenuOutException.
 *
 * @author Кузенков Павел
 * @since 26.07.2018
 */
public class MenuOutException extends RuntimeException {

    /**
     * Принимаем сообщение об ошибке в конструктор и передаем
     * его в конструктор родителя.
     * @param msg Сообщение об ошибке.
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
