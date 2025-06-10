package org.scoula;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 싱글톤 객체 생성
@Data
public class Restaurant {

    @Autowired // 자동으로 두리번 거리며 싱글톤을 찾아서 아래에 선언된 변수에 주소를 넣어줌
    Chef chef; // 주입 성공시 주소가 들어가게됨, 아니면 null 반환
}
