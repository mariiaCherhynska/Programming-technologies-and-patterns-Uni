package ua.nure.dao;

import ua.nure.dao.entetity.Display;
import ua.nure.dao.entetity.Phone;
import ua.nure.dao.entetity.Processor;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        DomainDao dao = DaoFactory.getDAOInstance(DAOVariant.MySqlDao);
        boolean loop = true;
        while (loop) {
            try {
                System.out.println("What do you want to do?\n" +
                        "1. View all catalog\n" +
                        "2. Delete phone\n" +
                        "3. Add phone\n" +
                        "4. Edit phone\n" +
                        "5. Find by model\n" +
                        "6. Add display\n" +
                        "7. Add processor\n" +
                        "8. Exit");
                switch(scanner.nextInt()) {
                    case 1:
                        List<Phone> phones = dao.getPhones();
                        for (Phone phone : phones) {
                            System.out.println(phone);
                        }
                        break;
                    case 2:
                        System.out.println("Enter id of phone that will be deleted: ");
                        int id = scanner.nextInt();
                        dao.deletePhone(id);
                        break;
                    case 3:
                        System.out.println("Enter the name of model of new phone: ");
                        String modelName = scanner.next();
                        System.out.println("Enter id of processor, there are: ");
                        for (Processor processor : dao.getProcessors()) {
                            System.out.println(processor);
                        }
                        int processorId = scanner.nextInt();
                        System.out.println("Enter id of display, there are: ");
                        for (Display display : dao.getDisplays()) {
                            System.out.println(display);
                        }
                        int displayId = scanner.nextInt();
                        dao.addPhone(modelName, processorId, displayId);
                        break;
                    case 4:
                        System.out.println("Enter id of phone that you want to edit: ");
                        id = scanner.nextInt();
                        Phone editable = dao.getPhonesById(id).get(0);
                        System.out.println(editable);
                        boolean isEditing = true;
                        while (isEditing) {
                            System.out.println("1. Update model\n" +
                                    "2. Save\n" +
                                    "3. Exit without saving");
                            switch (scanner.nextInt()) {
                                case 1:
                                    System.out.println("Enter model: ");
                                    String model = scanner.next();
                                    editable.setModel(model);
                                    break;
                                case 2:
                                    dao.updatePhone(editable);
                                    isEditing = false;
                                    break;
                                case 34:
                                    isEditing = false;
                                    break;
                                default:
                                    System.out.println("Wrong argument");
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Enter name of model: ");
                        String brand = scanner.next();
                        for (Phone monitor :
                                dao.getPhonesByModel(brand)) {
                            System.out.println(monitor);
                        }
                        break;
                    case 6:
                        System.out.println("Enter screen_refresh_rate: ");
                        int rate = scanner.nextInt();
                        System.out.println("Enter matrix_type: ");
                        String matrixType = scanner.next();
                        dao.addDisplay(rate, matrixType);
                        break;
                    case 7:
                        System.out.println("Enter name of model: ");
                        String model = scanner.next();
                        System.out.println("Enter cores: ");
                        String cores = scanner.next();
                        System.out.println("Enter frequency: ");
                        String frequency = scanner.next();
                        dao.addProcessor(model, cores, frequency);
                        break;
                    case 8:
                        loop = false;
                        break;
                    default:
                        System.out.println("Wrong argument");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage()
                        + "\n" + e.getLocalizedMessage());
            }
        }

    }
}
