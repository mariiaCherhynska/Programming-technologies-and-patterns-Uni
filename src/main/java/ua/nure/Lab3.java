package ua.nure;

import ua.nure.dao.DAOVariant;
import ua.nure.dao.DaoFactory;
import ua.nure.dao.DomainDao;
import ua.nure.dao.entetity.Display;
import ua.nure.dao.entetity.Phone;
import ua.nure.momento.Memento;

public class Lab3 {
    public static void main(String[] args) throws Exception {
        DomainDao dao = DaoFactory.getDAOInstance(DAOVariant.MySqlDao);

        Phone instance = dao.getPhones().get(0);

        Memento memento = new Memento(dao, instance);

        try {
            System.out.println("Initial state");
            System.out.println(memento.get());
            Phone tmpPhone = memento.get();
            tmpPhone.setModel("Nokia");
            memento.update(tmpPhone);

            System.out.println("After model update: ");
            System.out.println(memento.get());
            tmpPhone = memento.get();
            tmpPhone.setDisplay(new Display.Builder().setMatrixType("lcd").setScreenRefreshRate(120).build());
            memento.update(tmpPhone);

            System.out.println("After display updated: ");
            System.out.println(memento.get());

            memento.rollback();
            System.out.println("After rollback: ");
            System.out.println(memento.get());

            memento.rollback();
            System.out.println("After rollback: ");
            System.out.println(memento.get());


            memento.delete();
            try {
                System.out.println("Trying to rollback");
                memento.rollback();
            } catch (Exception e) {
                System.out.println("Rollback canceled with result: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Program finished with exception: " + e.getMessage());
        }
    }

}
