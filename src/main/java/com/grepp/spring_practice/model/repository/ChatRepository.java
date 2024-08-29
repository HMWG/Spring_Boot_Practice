package com.grepp.spring_practice.model.repository;

import com.grepp.spring_practice.model.dto.ChatDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRepository {
    int insertChat(ChatDTO chatDTO);
    List<ChatDTO> selectChatbyChatRoomId(int chatRoomNo);
}
