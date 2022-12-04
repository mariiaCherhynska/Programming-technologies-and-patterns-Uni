package ua.nure.dao;

import ua.nure.dao.entetity.Display;
import ua.nure.dao.entetity.Phone;
import ua.nure.dao.entetity.Processor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDao implements DomainDao{

    private static MySqlDao instance;
    private Connection connection;

    public MySqlDao(String URL, String username, String password) throws
            Exception {
        if (instance == null) {
            connection = DriverManager.getConnection(URL, username,
                    password);
            connection.setAutoCommit(false);
            instance = this;
        }
    }

    @Override
    public List<Phone> getPhones() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(DaoConstants.getPhones);
        List<Phone> result = new ArrayList<>();
        while (queryResult.next()) {
            result.add(recordToPhone(queryResult));
        }
        return result;
    }


    @Override
    public void addPhone(String phoneModel, int processorCategoryId, int displayId)
            throws Exception {
        try {
            int k = 1;
            PreparedStatement preparedStatement =
                    connection.prepareStatement(DaoConstants.addPhone);
            preparedStatement.setString(k++,  phoneModel);
            preparedStatement.setInt(k++, processorCategoryId);
            preparedStatement.setInt(k++, displayId);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    @Override
    public void updatePhone(Phone phone)
            throws Exception {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(DaoConstants.updatePhone);
            int k = 1;
            preparedStatement.setString(k++, phone.getModel());
            preparedStatement.setInt(k++, phone.getProcessor().getId());
            preparedStatement.setInt(k++, phone.getDisplay().getId());
            preparedStatement.setInt(k++, phone.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }

    }

    @Override
    public void deletePhone(int id) throws Exception {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(DaoConstants.deletePhone);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Looks like nothing was updated");
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }


    @Override
    public List<Phone> getPhonesByModel(String phoneModel)
            throws Exception {
        PreparedStatement preparedStatement =
                connection.prepareStatement(DaoConstants.getPhonesByModel);
        preparedStatement.setString(1, phoneModel);
        ResultSet queryResult = preparedStatement.executeQuery();
        List<Phone> result = new ArrayList<>();
        while (queryResult.next()) {
            result.add(recordToPhone(queryResult));
        }
        return result;
    }

    @Override
    public void addDisplay(int screenRefreshRate, String brandRate)
            throws Exception {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(DaoConstants.addDisplay);
            preparedStatement.setInt(1, screenRefreshRate);
            preparedStatement.setString(2, brandRate);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }

    }

    @Override
    public void addProcessor(String model, String cores, String frequency)
            throws Exception {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(DaoConstants.addProcessor);
            preparedStatement.setString(1, model);
            preparedStatement.setString(2, cores);
            preparedStatement.setString(3, frequency);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }

    }


    @Override
    public List<Display> getDisplays() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(DaoConstants.getDisplay);
        List<Display> result = new ArrayList<>();
        while (queryResult.next()) {
            result.add(recordToDisplay(queryResult));
        }
        return result;

    }

    @Override
    public List<Processor> getProcessors() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(DaoConstants.getProcessor);
        List<Processor> result = new ArrayList<>();
        while (queryResult.next()) {
            result.add(recordToProcessor(queryResult));
        }
        return result;
    }

    @Override
    public List<Phone> getPhonesById(int id) throws Exception {
        PreparedStatement preparedStatement =
                connection.prepareStatement(DaoConstants.getPhonesById);
        preparedStatement.setInt(1, id);
        ResultSet queryResult = preparedStatement.executeQuery();
        List<Phone> result = new ArrayList<>();
        while (queryResult.next()) {
            result.add(recordToPhone(queryResult));
        }
        return result;
    }

    private Processor recordToProcessor(ResultSet queryResult) throws Exception {
        return new Processor.Builder()
                .setId(queryResult.getInt("id_processor"))
                .setModel(queryResult.getString("model"))
                .setCores(queryResult.getString("cores"))
                .setFrequency(queryResult.getString("frequency"))
                .build();
    }
    private Display recordToDisplay(ResultSet queryResult) throws Exception {
        return new Display.Builder()
                .setId(queryResult.getInt("id_display"))
                .setScreenRefreshRate(queryResult.getInt("screen_refresh_rate"))
                .setMatrixType(queryResult.getString("matrix_type"))
                .build();
    }


    private Phone recordToPhone(ResultSet queryResult) throws Exception {
        Processor currentProcessor = recordToProcessor(queryResult);
        Display currentDisplay = recordToDisplay(queryResult);

        return new Phone.Builder()
                .setId(queryResult.getInt("id_phone"))
                .setModel(queryResult.getString("model"))
                .setProcessor(currentProcessor)
                .setDisplay(currentDisplay)
                .build();
    }
}
