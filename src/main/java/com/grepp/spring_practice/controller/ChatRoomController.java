package com.grepp.spring_practice.controller;

import com.grepp.spring_practice.model.dto.ChatRoomDTO;
import com.grepp.spring_practice.model.service.ChatRoomService;
import com.grepp.spring_practice.model.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
@RequestMapping("/chat")
public class ChatRoomController {
    ChatRoomService chatRoomService;
    UserService userService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService, UserService userService) {
        this.chatRoomService = chatRoomService;
        this.userService = userService;
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
    public ModelAndView create(ChatRoomDTO chatRoomDTO, HttpSession session, RedirectAttributes redirectAttributes) throws SQLException {
        Integer loginNo = (Integer) session.getAttribute("loginNo");

        if (chatRoomService.createChatRoom(chatRoomDTO, loginNo) == 0){
            ModelAndView mav = new ModelAndView("redirect:list"); // /WEB-INF/views/list.jsp
            redirectAttributes.addFlashAttribute("msg", "write fail -> 이름을 작성해주세요");
            return mav;
        }
        ModelAndView mav = new ModelAndView("redirect:list"); // /WEB-INF/views/list.jsp
        redirectAttributes.addFlashAttribute("msg", "write success");
        return mav;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("no")int chatRoomNo, HttpSession session, RedirectAttributes redirectAttributes) throws SQLException {
        Integer loginNo = (Integer) session.getAttribute("loginNo");
        ModelAndView mav = new ModelAndView("redirect:list");
        redirectAttributes.addFlashAttribute("msg", "채팅방 삭제 완료");
        if(0==chatRoomService.deleteChatRoom(chatRoomNo, loginNo)){
            mav.setViewName("redirect:/chatting/"+chatRoomNo);
            redirectAttributes.addFlashAttribute("msg", "delete fail -> 권한이 없습니다.");
        }
        return mav;
    }

    @GetMapping("/update")
    public ModelAndView update(@RequestParam("no")int chatRoomNo, HttpSession session, RedirectAttributes redirectAttributes) throws SQLException {
        Integer loginNo = (Integer) session.getAttribute("loginNo");
        ModelAndView mav = new ModelAndView("create_form");
        if(!chatRoomService.checkCreatedBy(loginNo, chatRoomNo)){
            redirectAttributes.addFlashAttribute("msg", "권한이 없습니다.");
            mav.setViewName("redirect:/chatting/"+chatRoomNo);
            return mav;
        }
        mav.addObject("action", "update");
        mav.addObject("no",chatRoomNo);
        return mav;
    }

    @PostMapping("/update")
    public ModelAndView update(ChatRoomDTO chatRoomDTO, HttpSession session, @RequestParam("no")int chatRoomNo, RedirectAttributes redirectAttributes) throws SQLException {
        ModelAndView mav = new ModelAndView("redirect:list");
        Integer loginNo = (Integer) session.getAttribute("loginNo");
        chatRoomDTO.setChatRoomNo(chatRoomNo);
        if (chatRoomService.updateChatRoom(chatRoomDTO, loginNo) == 0){
            redirectAttributes.addFlashAttribute("msg", "update fail -> 권한이 없습니다.");
        }
        return mav;
    }

    @GetMapping("/inviteForm")
    public ModelAndView inviteForm(@RequestParam("no")int chatRoomNo) throws SQLException {
        ModelAndView mav = new ModelAndView("invite_form");
        mav.addObject("userList", userService.getUsers());
        mav.addObject("no",chatRoomNo);
        return mav;
    }

    @GetMapping("/invite")
    public ModelAndView invite(@RequestParam("chatRoomNo")int chatRoomNo, @RequestParam("userNo")int userNo, RedirectAttributes redirectAttributes) throws SQLException {
        ModelAndView mav = new ModelAndView("redirect:/chatting/"+chatRoomNo);
        redirectAttributes.addFlashAttribute("msg", "초대 완료");
        if(0 == chatRoomService.inviteChatRoom(chatRoomNo, userNo)){
            redirectAttributes.addFlashAttribute("msg", "초대 실패");
        }
        return mav;
    }
}
