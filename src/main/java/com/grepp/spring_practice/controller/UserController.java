package com.grepp.spring_practice.controller;

import com.grepp.spring_practice.model.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView loginForm(HttpSession session, HttpServletRequest request) {

        Map<String, String> map = userService.checkLogin(session);
        ModelAndView mav = new ModelAndView(map.get("view"));
        mav.addObject("msg", map.get("msg"));
        mav.addObject("alert", map.get("alert"));
        return mav;
    }

    @PostMapping("/login")
    public ModelAndView loginForm(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password, HttpSession session, HttpServletResponse resp, HttpServletRequest req, @RequestParam(value = "remLogin", required = false) String remLogin, @RequestParam(value = "remId", required = false) String remId) throws SQLException {

        Map<String, Object> map = userService.login(username, password, session, remLogin, remId);
        ModelAndView mav = new ModelAndView("main");
        mav.addObject("msg", map.get("msg"));
        mav.addObject("alert", map.get("alert"));
        List<Cookie> cookies = (List<Cookie>) map.get("cookies");
        for(Cookie cookie : cookies) {
            resp.addCookie(cookie);
        }

        return mav;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("main");

        session.invalidate();
        Cookie cookie = new Cookie("remLogin", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        mav.addObject("msg", "logout");
        mav.addObject("alert", "true");
        return mav;
    }
}
