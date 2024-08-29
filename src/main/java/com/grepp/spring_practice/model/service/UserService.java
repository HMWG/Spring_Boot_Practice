package com.grepp.spring_practice.model.service;

import com.grepp.spring_practice.model.dto.UserDTO;
import com.grepp.spring_practice.model.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String,Object> login(String username, String password, HttpSession session, String remLogin, String remId) throws SQLException {
        UserDTO user = userRepository.findByUsernameAndPassword(username,password);

        Map<String,Object> map = new HashMap<>();

        if(user.getUsername()!=null && !user.getUsername().isEmpty()){
            session.setAttribute("loginNo", user.getUserNo());
            session.setAttribute("loginUsername", user.getUsername());
            List<Cookie> cookies = new ArrayList<>();

            if ("on".equals(remLogin)) {
                cookies.add(new Cookie("remLogin", user.getUsername()));
            }
            if ("on".equals(remId)) {
                cookies.add(new Cookie("remId", user.getUsername()));
            } else {
                Cookie cookie = new Cookie("remId", "");
                cookie.setMaxAge(0);
                cookies.add(cookie);
            }
            map.put("cookies", cookies);

            map.put("msg", "로그인 완료되었습니다. 반갑습니다. " + username + "님");
        } else {
            map.put("msg", "로그인 실패입니다. 아이디나 패스워드를 확인 해 주세요.");
        }
        return map;
    }

    public Map<String,String> checkLogin(HttpSession session){
        Integer loginId = (Integer) session.getAttribute("loginNo");
        Map<String,String> map = new HashMap<>();
        if(loginId == null) {
            map.put("view", "login_form");
        } else{ // 너 이미 로그인 되어있어!
            map.put("msg", "이미 로그인 내역이 있습니다.");
            map.put("view", "main");
        }
        return map;
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAllUsers();
    }

    public int joinUser(UserDTO user) {
        return userRepository.addUser(user);
    }
}
