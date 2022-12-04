package ua.nure.dao;

public class DaoFactory {
    private static DomainDao dao = null;

    public static DomainDao getDAOInstance(DAOVariant type) throws Exception {
        switch (type) {
            case MySqlDao:
                if (dao == null) {
                    String URL = "jdbc:mysql://localhost:3306/phones";
                    String USER = "root";
                    String PASSWORD = "11017811";
                    dao = new MySqlDao(URL, USER, PASSWORD);
                }
                break;
            default:
                throw new Exception("DAOVariant is not supported");
        }
        return dao;
    }
}
