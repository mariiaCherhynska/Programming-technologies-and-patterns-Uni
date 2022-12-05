package ua.nure.dao;


public class DaoFactory {
    private static DomainDao dao = null;

    public static DomainDao getDAOInstance(DAOVariant type) throws Exception {
        switch (type) {
            case MySqlDao:
                //if (dao == null) {
                    String URL = "jdbc:mysql://localhost:3306/phones";
                    String USER = "root";
                    String PASSWORD = "11017811";
                    dao = new MySqlDao(URL, USER, PASSWORD);
                //}
                break;
            case MongoDBDAO:
                String MONGO_DB_URL = "mongodb://127.0.0.1:27017";
                String MONGO_DB_NAME = "phones";
                dao = new MongoDBDAO(MONGO_DB_URL, MONGO_DB_NAME);
                break;

            default:
                throw new Exception("DAOVariant is not supported");
        }
        return dao;
    }
}
