package cn.az.boot.service;

import cn.az.boot.dao.StudentDao;
import cn.az.boot.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author azusachino
 * @version 12/10/2019
 */
@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;

    @Autowired
    RedisService redisService;

    private static final String KEY = "student";

    public Iterable<Student> findAll() {
        return studentDao.findAll();
    }


    public Student save(Student student) {
        return studentDao.save(student);
    }

    public Optional<Student> findById(String id) {
        return studentDao.findById(id);
    }

    public void update(Student student) {
        findById(student.getId()).ifPresent(studentDao::save);
    }

    public void delete(Student student) {
        findById(student.getId()).ifPresent(studentDao::delete);
    }
}
