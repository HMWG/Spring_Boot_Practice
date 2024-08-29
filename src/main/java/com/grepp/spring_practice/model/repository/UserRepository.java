package com.grepp.spring_practice.model.repository;

import com.grepp.spring_practice.model.dto.UserDTO;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRepository {
    int addUser(UserDTO user);
    UserDTO findByUsernameAndPassword(String username, String password);
    List<UserDTO> findAllUsers();
}
