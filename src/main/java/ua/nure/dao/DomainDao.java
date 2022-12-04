package ua.nure.dao;

import ua.nure.dao.entetity.Display;
import ua.nure.dao.entetity.Phone;
import ua.nure.dao.entetity.Processor;

import java.util.List;

public interface DomainDao {

    List<Phone> getPhones() throws Exception;
    public void deletePhone(String id) throws Exception;

    public void addPhone(Phone phone) throws Exception;
    void updatePhone(Phone phone) throws Exception;
    List<Phone> getPhonesByModel(String phoneModel) throws Exception;
    void addDisplay(int screenRefreshRate, String matrix_type) throws Exception;
    void addProcessor(String model, String cores, String frequency) throws Exception;
    public List<Display> getDisplays() throws Exception;
    public List<Processor> getProcessors() throws Exception;
    public List<Phone> getPhonesById(int id) throws Exception;
}
