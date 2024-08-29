package com.grepp.spring_practice.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private int fileNo;
    private int chatNo;
    private String originalName;
    private String savedPath;
}
