package org.scoula.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

    // 필요한 db처리 기능을 함수로 불완전하게 써놓자
    // 추상메서드라고 한다

    /**
     * 어노테이션 방식으로 SQL 정의
     * MySQL의 현재 시간을 조회하는 쿼리
     */

    // 어노테이션으로 sql문 사용하는 방법
    @Select("SELECT sysdate()")
    public String getTime();

    /**
     * XML 매퍼에서 정의할 메서드
     * 구현체는 MyBatis가 자동으로 생성
     */
    public String getTime2();
    // TimeMapper.xml에 sql문 있는 것 실행시키는 방법 사용

}
