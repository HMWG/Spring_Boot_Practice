package com.grepp.spring_practice.model.repository;

import com.grepp.spring_practice.model.dto.UserChatRoomDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserChatRoomRepository {
    int insert(int userNo, int chatRoomNo);
    UserChatRoomDTO findByUserNoAndChatRoomNo(int userNo, int chatRoomNo);
}
