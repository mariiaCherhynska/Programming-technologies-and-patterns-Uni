package ua.nure.dao;

import ua.nure.dao.entetity.Display;
import ua.nure.dao.entetity.Phone;
import ua.nure.dao.entetity.Processor;

import java.util.List;

public class Lab4 {
    public static void main(String[] args) throws Exception {
      //migrateMySQLToMongo(DaoFactory.getDAOInstance(DAOVariant.MongoDBDAO), DaoFactory.getDAOInstance(DAOVariant.MySqlDao));
    migrateMongoToMySQL(DaoFactory.getDAOInstance(DAOVariant.MongoDBDAO), DaoFactory.getDAOInstance(DAOVariant.MySqlDao));
}

        public static void migrateMongoToMySQL(DomainDao mongo, DomainDao mySQL) throws Exception {
            mySQL.clearDB();

            List<Display> displays = mongo.getDisplays();
            List<Processor> processors = mongo.getProcessors();

            for (Display display: displays) {
                mySQL.addDisplay(display.getScreenRefreshRate(), display.getMatrixType());
            }

            for (Processor processor: processors) {
                mySQL.addProcessor(processor.getModel(), processor.getCores(), processor.getFrequency());
            }

            displays = mySQL.getDisplays();
            processors = mySQL.getProcessors();

            List<Phone> phones = mongo.getPhones();

            for (Phone phone: phones) {
                phone.setProcessor(
                        processors.stream().filter(processor ->
                                processor.getModel().equals(phone.getProcessor().getModel())).findFirst().get()
                );

                phone.setDisplay(
                        displays.stream().filter(display
                                -> display.getMatrixType().equals(phone.getDisplay().getMatrixType())).findFirst().get()
                );

                mySQL.addPhone(phone);
            }
        }

        public static void migrateMySQLToMongo(DomainDao mongo, DomainDao mySQL) throws Exception {
            mongo.clearDB();

            List<Display> displays = mySQL.getDisplays();
            List<Processor> processors = mySQL.getProcessors();
            for (Display display: displays) {
                mongo.addDisplay(display.getScreenRefreshRate(), display.getMatrixType());
            }

            for (Processor processor: processors) {
                mongo.addProcessor(processor.getModel(), processor.getCores(), processor.getFrequency());
            }

            List<Phone> phones = mySQL.getPhones();

            System.out.println(phones);

            for (Phone phone: phones) {
                mongo.addPhone(phone);
            }
        }
}

