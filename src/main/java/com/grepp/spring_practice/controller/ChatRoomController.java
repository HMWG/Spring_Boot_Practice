package com.grepp.spring_practice.controller;

import com.grepp.spring_practice.model.dto.ChatRoomDTO;
import com.grepp.spring_practice.model.service.ChatRoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@RequestMapping("/chat")
public class ChatRoomController {
    ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(name = "page", defaultValue = "1")int page, HttpSession session) throws SQLException {
        ModelAndView mav = new ModelAndView("list");

        mav.addObject("pageData", chatRoomService.getList(page, (Integer) session.getAttribute("loginNo")));
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView mav = new ModelAndView("create_form");
        mav.addObject("action", "create");
        return mav;
    }

    @PostMapping("/create")
    public ModelAndView create(ChatRoomDTO chatRoomDTO, HttpSession session) throws SQLException {
        Integer loginNo = (Integer) session.getAttribute("loginNo");

        if (chatRoomService.createChatRoom(chatRoomDTO, loginNo) == 0){
            ModelAndView mav = new ModelAndView("alert"); // /WEB-INF/views/list.jsp
            mav.addObject("msg", "write fail -> 이름을 작성해주세요");
            mav.addObject("path", "list");
            return mav;
        }
        ModelAndView mav = new ModelAndView("alert"); // /WEB-INF/views/list.jsp
        mav.addObject("msg", "write success");
        mav.addObject("path", "list");
        return mav;
    }
}
