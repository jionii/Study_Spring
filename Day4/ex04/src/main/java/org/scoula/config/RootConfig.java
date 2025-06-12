package org.scoula.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration // 설정 파일임을 의미
@PropertySource({"classpath:/application.properties"}) // 이 경로의 properties 파일을 읽어 spring에서 쓸 수 있게 함
// 인터페이스에 해당하는 객체를 자동으로 만들어서 싱글톤으로 객체를 생성함
// xml파일이 있는 위치를 찾아라
@MapperScan(basePackages = {"org.scoula.mapper"})
public class RootConfig {

    // application.properties에서 데이터베이스 연결 정보 주입
    @Value("${jdbc.driver}") String driver;
    @Value("${jdbc.url}") String url;
    @Value("${jdbc.username}") String username;
    @Value("${jdbc.password}") String password;

    /**
     * HikariCP 커넥션 풀을 사용한 DataSource 빈 생성
     * @return 설정된 DataSource 객체
     */
    // HikariDBCP 싱글톤 만드는 함수를 하나 정의하자
    @Bean
    public DataSource dataSource() {
        // db연결 설정 --> db연결을 많이 해야함(DBCP)
        // 10개의 연결 기본을 만든다 ==> 100, 1000 연결 설정 수 가능
        // HikariCP 설정 객체 생성
        HikariConfig config = new HikariConfig();

        // 데이터베이스 연결 정보 설정
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        // 커넥션 풀 추가 설정 (선택사항)
//        config.setMaximumPoolSize(10);             // 최대 커넥션 수
//        config.setMinimumIdle(5);                  // 최소 유지 커넥션 수
//        config.setConnectionTimeout(30000);       // 연결 타임아웃 (30초)
//        config.setIdleTimeout(600000);            // 유휴 타임아웃 (10분)

        // HikariDataSource 생성 및 반환
        HikariDataSource dataSource = new HikariDataSource(config);
        // db연결 10개를 만들어서 대기줄에 넣어둠
        return dataSource;
    }

    //db사용 --> google mybatis사용 설정
    //트랜잭션 설정

    @Autowired
    ApplicationContext applicationContext;

    /**
     * SqlSessionFactory 빈 등록
     * - MyBatis의 핵심 팩토리 객체를 스프링 컨테이너에 등록
     *
     * // @param dataSource 위 dataSource() 메서드에서 등록된 bean이 주입됨
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        // MyBatis 설정 파일 위치 지정
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
        // 데이터베이스 연결 설정
        sqlSessionFactory.setDataSource(dataSource());

        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }

    /**
     * 트랜잭션 매니저 설정
     * - 데이터베이스 트랜잭션을 스프링이 관리하도록 설정
     */
    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource());

        return manager;
    }
}

