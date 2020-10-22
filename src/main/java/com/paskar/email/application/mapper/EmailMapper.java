package com.paskar.email.application.mapper;

import com.paskar.email.application.model.Email;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmailMapper implements RowMapper<Email> {
    @Override
    public Email mapRow(ResultSet resultSet, int i) throws SQLException {
        Email email = new Email();
        email.setId(resultSet.getInt("id"));
        email.setRecipient(resultSet.getString("recipient"));
        email.setSubject(resultSet.getString("subject"));
        email.setBody(resultSet.getString("body"));
        email.setDate(LocalDate.parse(resultSet.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return email;
    }
}
