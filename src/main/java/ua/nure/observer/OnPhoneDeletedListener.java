package ua.nure.observer;

public class OnPhoneDeletedListener implements IListener<Integer>{
    @Override
    public void update(Integer data) {
        System.out.println("Phone with id " + data + " deleted");
    }
}
