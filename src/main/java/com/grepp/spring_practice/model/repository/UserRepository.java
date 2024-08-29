package com.grepp.spring_practice.model.repository;

import com.grepp.spring_practice.model.dto.UserDTO;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    int addUser(User user);
    UserDTO findByUsernameAndPassword(String username, String password);
}
