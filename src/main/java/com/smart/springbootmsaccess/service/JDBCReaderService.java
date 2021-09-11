package com.smart.springbootmsaccess.service;

import com.smart.springbootmsaccess.config.GlobalConfigurations;
import com.smart.springbootmsaccess.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class JDBCReaderService {

    @Autowired
    private GlobalConfigurations globalConfigurations;

    public List<Book> getAllBooks() throws SQLException {
        String databaseURL = globalConfigurations.getJdbcUrl();
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            String sql = "SELECT * FROM BOOK";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                Long id = result.getLong("ID");
                String fullname = result.getString("BookName");
                String year = result.getString("BookYear");
                bookList.add(new Book(id, fullname, year));
            }
            return bookList;
        } catch (SQLException ex) {
            throw new SQLException(ex.getNextException());
        }
    }
}
