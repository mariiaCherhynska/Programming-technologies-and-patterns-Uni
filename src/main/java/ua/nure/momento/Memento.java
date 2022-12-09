package ua.nure.momento;

import ua.nure.dao.DomainDao;
import ua.nure.dao.entetity.Phone;

import java.util.Stack;

public class Memento {
    private Stack<Phone> states;
    private DomainDao dao;

    public Memento(DomainDao dao, Phone entryState) {
        states = new Stack<>();
        this.dao = dao;
        states.push(entryState.clone());
    }

    public void update(Phone monitor) throws Exception {
        if (states.empty()) {
            throw new Exception("You can't update entity before it's assignment");
        }
        states.push(monitor.clone());
    }

    public void delete() throws Exception {
        if (states.empty()) {
            throw new Exception("You can't delete an uncreated instance");
        }
        dao.deletePhone(states.pop().getId());
        states.clear();
    }

    public Phone get() throws Exception {
        if (states.empty()) {
            throw new Exception("You can't get entity before it's assignment");
        }
        return states.lastElement().clone();
    }

    public Phone rollback() throws Exception {
        if (states.size() <= 1) {
            throw new Exception("Can't rollback any earlier");
        }
        return states.pop();
    }

    public void commit() throws Exception {
        if (states.empty()) {
            throw new Exception("You can't commit entity before it's assignment");
        }
        dao.updatePhone(get());
        states.setElementAt(states.lastElement(), 0);
        states.setSize(1);
    }
}
