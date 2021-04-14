package cn.az.boot.mybatis.demo;

import cn.az.boot.mybatis.dao.UserMapper;
import cn.az.boot.mybatis.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

/**
 * @author az
 */
public class MybatisAnnotationConfigurationDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream("mybatis/mybatis-config.xml");
        Objects.requireNonNull(inputStream);
        Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        SqlSessionFactory sqlSessionFactory = builder.build(reader, new Properties());

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectUser(2);

        System.out.println(user);

        sqlSession.close();
    }
}
