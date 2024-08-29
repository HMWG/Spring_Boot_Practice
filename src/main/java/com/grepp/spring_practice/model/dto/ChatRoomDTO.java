package com.grepp.spring_practice.model.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDTO {
    private int chatRoomNo;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
