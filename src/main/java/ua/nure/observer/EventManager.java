package ua.nure.observer;

import java.util.ArrayList;
import java.util.List;

public class EventManager <T>{
    List<IListener<T>> listenerList = new ArrayList<>();

    public EventManager() {}

    public void subscribe(IListener<T> listener) {
        listenerList.add(listener);
    }

    public void unsubscribe(IListener<T> listener) {
        listenerList.remove(listener);
    }

    public void update(T data) {
        for (IListener<T> listener: listenerList) {
            listener.update(data);
        }
    }
}

