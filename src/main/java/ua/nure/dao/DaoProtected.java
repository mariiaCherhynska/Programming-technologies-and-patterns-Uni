package ua.nure.dao;

import ua.nure.dao.entetity.*;
import ua.nure.observer.IListener;

import java.sql.SQLException;
import java.util.List;

public class DaoProtected implements DomainDao{

    private final DomainDao dao;
    private final User user;

    public DaoProtected(DomainDao dao, User user) {
        this.dao = dao;
        this.user = user;
    }

    private void userAdminRoleRequired() throws IllegalAccessException {
        if (user.getRole().equals(Role.userUserRole)) {
            throw new IllegalAccessException("User " + user.getUserName() + " required admin access");
        }
    }

    @Override
    public void subscribeOnPhoneAdded(IListener<Phone> listener) {
        dao.subscribeOnPhoneAdded(listener);
    }

    @Override
    public void unsubscribeOnPhoneAdded(IListener<Phone> listener) {
        dao.unsubscribeOnPhoneAdded(listener);
    }

    @Override
    public void subscribeOnPhoneDeleted(IListener<Integer> listener) {
        dao.subscribeOnPhoneDeleted(listener);
    }

    @Override
    public void unsubscribeOnPhoneDeleted(IListener<Integer> listener) {
        dao.unsubscribeOnPhoneDeleted(listener);
    }

    @Override
    public void subscribeOnPhoneUpdated(IListener<Phone> listener) {
        dao.subscribeOnPhoneUpdated(listener);
    }

    @Override
    public void unsubscribeOnPhoneUpdated(IListener<Phone> listener) {
        dao.unsubscribeOnPhoneUpdated(listener);
    }

    @Override
    public User authorize(String userName) throws SQLException {
        return dao.authorize(userName);
    }

    @Override
    public void registerUser(User user) throws SQLException {
        dao.registerUser(user);
    }

    @Override
    public List<Role> getUserRoles() throws SQLException {
        return dao.getUserRoles();
    }

    @Override
    public List<Phone> getPhones() throws Exception {
        return dao.getPhones();
    }

    @Override
    public void deletePhone(int id) throws Exception {
        userAdminRoleRequired();
        dao.deletePhone(id);
    }

    @Override
    public void addPhone(String phoneModel, int processorCategoryId, int displayId) throws Exception {
        userAdminRoleRequired();
        dao.addPhone(phoneModel, processorCategoryId, displayId);
    }

    @Override
    public void updatePhone(Phone phone) throws Exception {
        userAdminRoleRequired();
        dao.updatePhone(phone);
    }

    @Override
    public List<Phone> getPhonesByModel(String phoneModel) throws Exception {
        return dao.getPhonesByModel(phoneModel);
    }

    @Override
    public void addDisplay(int screenRefreshRate, String matrix_type) throws Exception {
        userAdminRoleRequired();
        dao.addDisplay(screenRefreshRate, matrix_type);
    }

    @Override
    public void addProcessor(String model, String cores, String frequency) throws Exception {
        userAdminRoleRequired();
        dao.addProcessor(model, cores, frequency);
    }

    @Override
    public List<Display> getDisplays() throws Exception {
        return dao.getDisplays();
    }

    @Override
    public List<Processor> getProcessors() throws Exception {
        return dao.getProcessors();
    }

    @Override
    public List<Phone> getPhonesById(int id) throws Exception {
        return dao.getPhonesById(id);
    }
}

