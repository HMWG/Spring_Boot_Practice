package com.grepp.spring_practice.model.service;

import com.grepp.spring_practice.model.dto.ChatDTO;
import com.grepp.spring_practice.model.dto.ChatRoomDTO;
import com.grepp.spring_practice.model.repository.ChatRepository;
import com.grepp.spring_practice.model.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ChatService {
    ChatRoomRepository chatRoomRepository;
    ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRoomRepository chatRoomRepository, ChatRepository chatRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatRepository = chatRepository;
    }

    public ChatRoomDTO viewChatRoom(int chatRoomNo) throws SQLException {
        return chatRoomRepository.selectByChatRoomId(chatRoomNo);
    }

    public int chatting(ChatDTO chatDTO) throws SQLException {
        return chatRepository.insertChat(chatDTO);
    }

    public List<ChatDTO> chatList(int chatRoomNo) throws SQLException {
        return chatRepository.selectChatbyChatRoomId(chatRoomNo);
    }
}
