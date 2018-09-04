package com.hiProject.service.user;

public interface UserServiceInterface {
    void create(String name, String password);

    void delete(String name);

    Integer getAllUsers();

    void deleteAllUsers();
}
