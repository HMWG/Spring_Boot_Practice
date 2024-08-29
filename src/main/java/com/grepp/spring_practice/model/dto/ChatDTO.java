package com.grepp.spring_practice.model.dto;

import lombok.*;

import java.time.LocalDateTime;

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
}
