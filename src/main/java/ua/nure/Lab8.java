package ua.nure;

import ua.nure.dao.DAOVariant;
import ua.nure.dao.DaoFactory;
import ua.nure.dao.DaoProtected;
import ua.nure.dao.DomainDao;
import ua.nure.dao.entetity.Phone;
import ua.nure.dao.entetity.Role;
import ua.nure.dao.entetity.User;

import java.util.List;

public class Lab8 {
    public static void main(String[] args) throws Exception {
        DomainDao dao = DaoFactory.getDAOInstance(DAOVariant.MySqlDao);

        User user = new User.Builder().setUserName("masha_user").setRole(Role.userUserRole).build();
        //dao.registerUser(user);
        DaoProtected userDAO = new DaoProtected(dao, dao.authorize("masha_user"));

        User admin = new User.Builder().setUserName("dasha_admin").setRole(Role.adminUserRole).build();
        //dao.registerUser(admin);
        DaoProtected adminDAO = new DaoProtected(dao, dao.authorize("dasha_admin"));

        try {
            System.out.println("User tries do operations (" + user.getUserName() + "):");

            List<Phone> phones = userDAO.getPhones();
            System.out.println(phones);

            System.out.println("Updating model name of phone");
            phones.get(0).setModel("new model name");

            userDAO.updatePhone(phones.get(0));
            System.out.println("successfully updated");

        } catch (Exception e) {
            System.out.println("Error with access: " + e.getMessage());
        }

        try {
            System.out.println("User tries do operations (" + admin.getUserName() + "):");

            List<Phone> phones = adminDAO.getPhones();
            System.out.println(phones);

            System.out.println("Updating model name of phone");
            phones.get(0).setModel("new model name");

            adminDAO.updatePhone(phones.get(0));
            System.out.println("successfully updated");

        } catch (Exception e) {
            System.out.println("Error with access: " + e.getMessage());
        }
    }
}
