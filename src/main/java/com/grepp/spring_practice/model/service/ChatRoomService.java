package com.grepp.spring_practice.model.service;

import com.grepp.spring_practice.model.dto.ChatRoomDTO;
import com.grepp.spring_practice.model.repository.ChatRoomRepository;
import com.grepp.spring_practice.model.repository.UserChatRoomRepository;
import com.grepp.spring_practice.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatRoomService {
    private static final int COUNT_PER_PAGE = 10;

    ChatRoomRepository chatRoomRepository;
    UserChatRoomRepository userChatRoomRepository;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository, UserChatRoomRepository userChatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.userChatRoomRepository = userChatRoomRepository;
    }

    public Map<String, Object> getList(int page, int id) throws SQLException {
        int totalCount = chatRoomRepository.selectCountByUserId(id);
        int totalPageCount = totalCount / COUNT_PER_PAGE; // (ex : 27/10 = 2.7 = 2)
        if(totalCount % COUNT_PER_PAGE > 0){ // 10개씩 2페이지하고 7개의 글이 남은 상태라 페이지 하나 늘려주기
            totalPageCount++; // 총 페이지는 3개
        }

        int startPage = (page - 1)/10 * 10 + 1; // 현재 페이지가 11, 12, 13,..., 20 이었을 때, -1 해서 10~19로 바꾸고 /10*10 하면 11, 12, .., 19 다 동일하게 10으로 통일됨
        int endPage = startPage+9;
        if(totalPageCount < endPage){ // 총 페이지 수보다 범위가 넓을 때
            endPage = totalPageCount; //마지막 페이지 링크를 총 페이지 수로 줄여줍시다
        }

        int startRow = (page - 1) * COUNT_PER_PAGE; // 한 페이지당 보여질 줄의 갯수 반영해서 db에 모든 글들 다 읽어오지 않고 여기부터 끊어서 읽으라고 알려줄 수 있음
        List<ChatRoomDTO> chatRoomList = chatRoomRepository.selectByUserId(startRow, COUNT_PER_PAGE, id);
        /////////////////////// service는 이렇게 여러가지 비즈니스 로직을 수행해서 데이터를 계산해냄
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("page",page);
        resultData.put("startPage",startPage);
        resultData.put("endPage",endPage);
        resultData.put("totalPageCount",totalPageCount);
        resultData.put("cList",chatRoomList);

        return resultData;
    }

    @Transactional
    public int createChatRoom(ChatRoomDTO chatRoomDTO, int userNo) throws SQLException {
        int a = chatRoomRepository.insert(chatRoomDTO);
        int b = userChatRoomRepository.insert(userNo, chatRoomDTO.getChatRoomNo());

        if(a==1 && b==1){
            return 1;
        }
        return 0;
    }

    public int inviteChatRoom(int userNo, int chatRoomNo){
        return userChatRoomRepository.insert(userNo, chatRoomNo);
    }

    public int deleteChatRoom(int chatRoomNo) throws SQLException {
        return chatRoomRepository.delete(chatRoomNo);
    }
}
