package com.grepp.spring_practice.controller;

import com.grepp.spring_practice.model.dto.ChatDTO;
import com.grepp.spring_practice.model.service.ChatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@RequestMapping("/chatting")
public class ChatController {
    ChatService chatService;
    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/{chatRoomNo}")
    public ModelAndView chatRoom(@PathVariable("chatRoomNo") int chatRoomNo) throws SQLException {
        ModelAndView mav = new ModelAndView("view");
        mav.addObject("chatRoom", chatService.viewChatRoom(chatRoomNo));
        mav.addObject("chatList", chatService.chatList(chatRoomNo));

        return mav;
    }

    @PostMapping("/{chatRoomNo}")
    public String chatRoomPost(@PathVariable("chatRoomNo") int chatRoomNo, @RequestParam("message") String message, HttpSession session) throws SQLException {
        Integer userNo = (Integer) session.getAttribute("loginNo");
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setChatRoomNo(chatRoomNo);
        chatDTO.setUserNo(userNo);
        chatDTO.setChatText(message);

        chatService.chatting(chatDTO);
        return "redirect:/chatting/"+chatRoomNo;
    }
}
