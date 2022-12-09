package ua.nure.dao;

import ua.nure.dao.entetity.Display;
import ua.nure.dao.entetity.Phone;
import ua.nure.dao.entetity.Processor;

import java.util.List;

public class Lab4 {
    public static void main(String[] args) throws Exception {
        DomainDao dao = DaoFactory.getDAOInstance(DAOVariant.MongoDBDAO);
        dao.clearDB();
        int amount = 10000;

        for (int i = 0; i < amount; i++) {
            dao.addPhone(
                    new Phone.Builder()
                            .setModel("testModel")
                            .setDisplay(new Display.Builder()
                                    .setScreenRefreshRate(100)
                                    .setMatrixType("matrixType")
                                    .build())
                            .setProcessor(new Processor.Builder()
                                    .setFrequency("90")
                                    .setCores("4")
                                    .setModel("processor model")
                                    .build())
                            .build()
            );
        }

        List<Phone> phones = dao.getPhonesByModel("testModel");
        System.out.println("Read " + phones.size() + " documents");
    }
}
