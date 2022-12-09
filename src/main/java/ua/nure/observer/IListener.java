package ua.nure.observer;

public interface IListener<T> {
    void update(T data);
}
