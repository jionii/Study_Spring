package org.scoula.board.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.domain.BoardVO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j2
class BoardMapperTest {

    @Autowired
    // BoardMapper 인터페이스를 구현한 클래스의 객체를 mapper 변수에 넣어주세요
    // spring과 mybatis가 환결설정한 것을 기반으로 하여 자동으로 클래스를 만들고 싱글톤도 만들어줌
    private BoardMapper mapper;

    @Test
    @DisplayName("BoardMapper 의 목록 불러오기")
    void getList() {
        List<BoardVO> list = mapper.getList();
        for (int i = 0; i < list.size(); i++) {
            log.info(list.get(i));
        }
    }

    @Test
    @DisplayName("BoardMapper의 게시글 읽기")
    public void get() {

        // 존재하는 게시물 번호로 테스트
        BoardVO board = mapper.get(1L);
        log.info(board);

    }

    @Test
    @DisplayName("BoardMapper의 새글 작성")
    public void create() {

        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글");
        board.setContent("새로 작성하는 내용");
        board.setWriter("user0");

        mapper.create(board);

        log.info(board);
    }
}