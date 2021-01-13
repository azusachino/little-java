package cn.az.boot.aop.dao;

import cn.az.boot.aop.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Liz
 * @version 2019/11/27
 */
@Mapper
public interface LogDao extends BaseMapper<SysLog> {

}
