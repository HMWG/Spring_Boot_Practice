package com.grepp.spring_practice.model.repository;

import com.grepp.spring_practice.model.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileRepository {
    int insertFiles(@Param("fileDTOList") List<FileDTO> fileDTOList); // SQL에서 여러 개를 한번에 insert 하는 기능 ( 성능 생각하면 이러헥 해야함. 커넷견 연결이 비교적 비싼 리소스여서..
//    int insertFile(FileDTO fileDTO); // 때로는 차라리 디비 연결을 여러번 해버리는게 유리한 경우도 있을 수는 있음
    List<FileDTO> selectFiles(int chatNo); // 특정 게시글에 첨부된 어러 개의 파일목록 읽어오기.
    FileDTO selectFile(int fileNo);
}
