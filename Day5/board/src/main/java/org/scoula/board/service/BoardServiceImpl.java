package org.scoula.board.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.springframework.stereotype.Service;
import org.scoula.board.mapper.BoardMapper;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service // Component + 서비스 역할의 클래스라는 것이 스프링에 등록
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    // 전처리해서 dao의 메서드를 불러서 db 처리해 달라고 해야함
    private final BoardMapper mapper;
    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> getList() {
//        List<BoardVO> list = boardMapper.getList();
        return mapper.getList().stream() // BoardVO의 스트림
                .map(BoardDTO::of) // BoardDTO의 스트림
                .toList(); // List<BoardDTO> 변환
    }

    @Override
    public BoardDTO get(Long no) {
        BoardVO vo = boardMapper.get(no);
        // 다른 곳으로 넘길 때는 dto로 만들어서 넘기자
        BoardDTO dto = BoardDTO.of(vo);
        return dto;
    }

    @Override
    public void create(BoardDTO board) { }

    @Override
    public boolean update(BoardDTO board) {
        return false;
    }

    @Override
    public boolean delete(Long no) {
        return false;
    }
}