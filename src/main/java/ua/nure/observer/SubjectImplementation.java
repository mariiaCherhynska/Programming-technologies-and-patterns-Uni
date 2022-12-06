package ua.nure.observer;
import tokar.patterns.dao.MySqlDAO;
import java.util.ArrayList;
import java.util.List;

public class SubjectImplementation implements Subject{
    private List<Observer> observers;
    private String newsProcessors;
    public SubjectImplementation(MySqlDAO m) throws Exception {
        observers = new ArrayList<>();
        observers = m.getObservers();
    }
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyObserver() {
        for (Observer observer : observers)
            observer.update(newsProcessors);
    }
    public void setNewsProcessors(String news) {
        this.newsProcessors = news;
        notifyObserver();
    }
}
