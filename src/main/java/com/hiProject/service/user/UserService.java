package com.hiProject.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserService implements UserServiceInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(String name, String password) {
        jdbcTemplate.update("insert into USER (NAME, PASSWORD) values (?, ?)", name, password);
    }

    @Override
    public void delete(String name) {

    }

    @Override
    public Integer getAllUsers() {
        return null;
    }

    @Override
    public void deleteAllUsers() {

    }
}
