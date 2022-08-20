package com.example.jacksonmed.dao;

import com.example.jacksonmed.model.Test;
import org.springframework.stereotype.Component;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

@Component("TestDaoImpl")
public class TestDaoImpl implements TestDao {
    private static final String INSERT_TEST_SQL = "INSERT INTO test" + "  (id, name ) VALUES "
            + " (?, ?);";
    private static final String SELECT_USER_BY_ID = "select id,name from test where id =?";
    private static final String USER = "jacksonmed";
    private static final String PASS = "jacksonmedjms";


    @Override
    public Test retrieveTest(String uniqueId) throws SQLException {
        Test test = null;
        // Step 1: Establishing a Connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://jacksonmeddb.c3hoyglkaipl.us-west-1.rds.amazonaws.com:3306/jackson_med", USER, PASS);
             // Step 2:Create a statement using connection object
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
        preparedStatement.setString(1, uniqueId);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            test = new Test(id, name);
        }

        return test;
    }

    @Override
    public void insertTest(Test test) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://jacksonmeddb.c3hoyglkaipl.us-west-1.rds.amazonaws.com:3306/jackson_med", USER, PASS);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEST_SQL);
        preparedStatement.setString(1, test.getId());
        preparedStatement.setString(2, test.getName());
        preparedStatement.executeUpdate();




    }





}
