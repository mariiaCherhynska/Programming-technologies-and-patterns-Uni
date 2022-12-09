package ua.nure;

import ua.nure.dao.DAOVariant;
import ua.nure.dao.DaoFactory;
import ua.nure.dao.DomainDao;
import ua.nure.dao.entetity.Display;
import ua.nure.dao.entetity.Phone;
import ua.nure.dao.entetity.Processor;

import java.util.List;

public class Lab3 {
    public static void main(String[] args) throws Exception {
        DomainDao dao = DaoFactory.getDAOInstance(DAOVariant.MongoDBDAO);
        int amount = 100;
        // insert

        Long startTime = System.currentTimeMillis();

        for (int i = 0; i < amount; i++) {
            dao.addPhone(
                    new Phone.Builder()
                            .setModel("testModelName")
                            .setProcessor(new Processor.Builder()
                                    .setId(1)
                                    .setModel("test Processor Model")
                                    .setCores("5")
                                    .setFrequency("100")
                                    .build()
                            )
                            .setDisplay(new Display.Builder()
                                    .setId(1)
                                    .setMatrixType("test Matrix Type")
                                    .setScreenRefreshRate(100)
                                    .build()
                            )
                            .build()
            );
        }

        System.out.println("Insert for " + amount + " units of data took " + (System.currentTimeMillis() - startTime) + "millis");

        // Select

        startTime = System.currentTimeMillis();

        List<Phone> phones = dao.getPhonesByModel("testModelName");

        System.out.println("Select for " + amount + " units of data took " + (System.currentTimeMillis() - startTime) + "millis");
    }

}
