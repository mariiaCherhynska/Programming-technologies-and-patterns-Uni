package ua.nure.dao;

import ua.nure.dao.entetity.*;
import ua.nure.observer.IListener;

import java.sql.SQLException;
import java.util.List;

public interface DomainDao {

     void subscribeOnPhoneAdded(IListener<Phone> listener);
     void unsubscribeOnPhoneAdded(IListener<Phone> listener);
     void subscribeOnPhoneDeleted(IListener<Integer> listener);
     void unsubscribeOnPhoneDeleted(IListener<Integer> listener);
     void subscribeOnPhoneUpdated(IListener<Phone> listener);
     void unsubscribeOnPhoneUpdated(IListener<Phone> listener);

    public User authorize(String userName) throws SQLException;
    public void registerUser(User user) throws SQLException;
    public List<Role> getUserRoles() throws SQLException;



    List<Phone> getPhones() throws Exception;
    void deletePhone(int id) throws Exception;

    void addPhone(String phoneModel, int processorCategoryId, int
            displayId) throws Exception;
    void updatePhone(Phone phone) throws Exception;
    List<Phone> getPhonesByModel(String phoneModel) throws Exception;
    void addDisplay(int screenRefreshRate, String matrix_type) throws Exception;
    void addProcessor(String model, String cores, String frequency) throws Exception;
    public List<Display> getDisplays() throws Exception;
    public List<Processor> getProcessors() throws Exception;
    public List<Phone> getPhonesById(int id) throws Exception;
}
