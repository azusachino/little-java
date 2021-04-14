package cn.az.boot.dao;

import cn.az.boot.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author azusachino
 * @version 2019/12/09
 */
@Repository
public interface StudentDao extends CrudRepository<Student, String> {

}
