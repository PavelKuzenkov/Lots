package ru.kuzenkov.newjob.menu;

import ru.kuzenkov.newjob.logic.Aggregator;

public interface UserAction {

    int key();

    void execute(Input input, Aggregator aggregator);

    String info();

}
