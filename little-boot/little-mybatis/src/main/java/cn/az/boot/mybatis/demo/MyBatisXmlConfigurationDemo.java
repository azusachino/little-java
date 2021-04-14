package cn.az.boot.mybatis.demo;

import cn.az.boot.mybatis.domain.User;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

import java.io.Reader;
import java.util.Properties;

/**
 * @author az
 */
public class MyBatisXmlConfigurationDemo {

    @SneakyThrows
    public static void main(String[] args) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();

        Resource resource = resourceLoader.getResource("classpath:mybatis/mybatis-config.xml");

        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");

        Reader reader = encodedResource.getReader();

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        SqlSessionFactory sqlSessionFactory = builder.build(reader, new Properties());

        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = sqlSession.selectOne("cn.az.boot.mybatis.dao.UserDao.selectOneUser", 1);
        System.out.println("user = " + user);
        sqlSession.close();
    }
}
