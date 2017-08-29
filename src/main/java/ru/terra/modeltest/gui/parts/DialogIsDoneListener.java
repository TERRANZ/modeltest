package ru.terra.modeltest.gui.parts;

public interface DialogIsDoneListener<T> {
    public void dialogIsDone(T ret, String... strings);

    public void cancel();
}