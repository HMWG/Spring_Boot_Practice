package com.grepp.spring_practice.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int userNo;
    private String username;
    private String password;
}
