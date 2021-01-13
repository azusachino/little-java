package cn.az.boot.es.repository;

import cn.az.boot.es.entity.UserEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Liz
 * @version 2019/11/29
 */
@Repository
public interface UserRepository extends ElasticsearchRepository<UserEs, Long> {

}
