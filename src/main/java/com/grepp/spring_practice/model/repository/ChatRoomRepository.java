package com.grepp.spring_practice.model.repository;

import com.grepp.spring_practice.model.dto.ChatRoomDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface ChatRoomRepository {
    int insert(ChatRoomDTO chatRoomDTO) throws SQLException;
    int update(ChatRoomDTO chatRoomDTO) throws SQLException;
    int delete(int id) throws SQLException;
    List<ChatRoomDTO> selectByUserId(@Param("sr")int startRow, @Param("cnt")int count, @Param("id")int id) throws SQLException;
    int selectCountByUserId(int id) throws SQLException;
    ChatRoomDTO selectByChatRoomId(int id) throws SQLException;
}
