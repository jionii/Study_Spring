package org.scoula.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.scoula.board.domain.BoardVO;

//@Repository // 저장(db)
//@Component + db 예외처리
public interface BoardMapper {
//    @Select("select * from tbl_board order by no desc")
    public List<BoardVO> getList();

    public BoardVO get(Long no);

    public void create(BoardVO board);
}