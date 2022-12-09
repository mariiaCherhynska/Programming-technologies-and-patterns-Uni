package ua.nure.observer;

import ua.nure.dao.entetity.Phone;

public class OnPhoneAddedListener  implements IListener<Phone>{
    @Override
    public void update(Phone data) {
        System.out.println("Phone added: " + data);
    }
}

