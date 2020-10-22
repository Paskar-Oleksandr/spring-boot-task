package com.paskar.email.application.repositiory;

import com.paskar.email.application.mapper.EmailMapper;
import com.paskar.email.application.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmailDaoImpl implements EmailDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmailDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Email email) {
        String sql = "INSERT INTO first_homework_with_jdbc (recipient,subject, body, date) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, email.getRecipient(), email.getSubject(), email.getBody(), email.getDate());
    }

    @Override
    public Email getById(int id) {
        String sql ="SELECT * FROM first_homework_with_jdbc WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new EmailMapper(), id);
    }

    @Override
    public List<Email> findAll() {
        String sql = "SELECT * FROM first_homework_with_jdbc";
        return jdbcTemplate.query(sql, new EmailMapper());
    }

    @Override
    public void update(Email email) {
        String sql ="UPDATE first_homework_with_jdbc SET recipient=?, subject=?, body=?, date=? WHERE id=?";
        jdbcTemplate.update(sql,email.getRecipient(), email.getSubject(), email.getBody(), email.getDate(), email.getId());
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM first_homework_with_jdbc WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Email> findEmailsNearDeliveryDate() {
        LocalDate time = LocalDate.now();
        List<Email> emailList = findAll();
        List<Email> result = new ArrayList<>();

        for (Email email : emailList) {
            if (time.equals(email.getDate())) {
                result.add(email);
            }
        }
        return result;
    }
}
