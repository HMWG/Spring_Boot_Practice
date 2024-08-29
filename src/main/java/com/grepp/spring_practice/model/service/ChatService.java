package com.grepp.spring_practice.model.service;

import com.grepp.spring_practice.model.dto.ChatDTO;
import com.grepp.spring_practice.model.dto.ChatRoomDTO;
import com.grepp.spring_practice.model.dto.FileDTO;
import com.grepp.spring_practice.model.repository.ChatRepository;
import com.grepp.spring_practice.model.repository.ChatRoomRepository;
import com.grepp.spring_practice.model.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ChatService {
    ChatRoomRepository chatRoomRepository;
    ChatRepository chatRepository;
    FileRepository fileRepository;

    @Autowired
    public ChatService(ChatRoomRepository chatRoomRepository, ChatRepository chatRepository, FileRepository fileRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatRepository = chatRepository;
        this.fileRepository = fileRepository;
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

    public int saveFileInfos(List<FileDTO> fileDTOList, int chatNo){ // 작성된 글 하나에 파일이 여러개 첨부될 수 있음
        if(fileDTOList == null || fileDTOList.isEmpty()) return 0;
        for(FileDTO fileDTO : fileDTOList){
            fileDTO.setChatNo(chatNo);
        }
        return fileRepository.insertFiles(fileDTOList);
    }

    public FileDTO getFileInfo(int fileNo){
        // file 다운로드 카운트를 update 한다던지 뭐 부가작업 필요하면 여기서 해야 함
        return fileRepository.selectFile(fileNo);
    }
}
