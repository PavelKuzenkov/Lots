package ru.kuzenkov.newjob.menu;

import ru.kuzenkov.newjob.logic.Aggregator;

interface UserAction {

    int key();

    void execute(Input input, Aggregator aggregator);

    String info();

}
