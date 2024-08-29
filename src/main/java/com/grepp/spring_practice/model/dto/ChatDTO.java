package com.grepp.spring_practice.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    private int chatNo;
    private int userNo;
    private int chatRoomNo;
    private String chatText;
    private LocalDateTime createdAt;
    private List<FileDTO> fileDTOList;
}
