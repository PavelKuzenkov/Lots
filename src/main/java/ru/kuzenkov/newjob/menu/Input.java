package ru.kuzenkov.newjob.menu;

public interface Input {

    String ask(String question);

    int ask(String question, int[] range);

    double ask(String question1, String question2);
}
