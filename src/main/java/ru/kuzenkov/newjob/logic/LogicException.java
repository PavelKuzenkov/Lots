package ru.kuzenkov.newjob.logic;

/**
 * Class LogicException.
 *
 * @author Кузенков Павел
 * @since 26.07.2018
 */
public class LogicException extends RuntimeException {

    /**
     * Принимаем сообщение об ошибке в конструктор и передаем
     * его в конструктор родителя.
     * @param msg Сообщение об ошибке.
     */
    public LogicException(String msg) {
        super(msg);
    }
}
