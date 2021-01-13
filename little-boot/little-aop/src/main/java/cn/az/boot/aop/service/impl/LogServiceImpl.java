package cn.az.boot.aop.service.impl;

import cn.az.boot.aop.dao.LogDao;
import cn.az.boot.aop.entity.SysLog;
import cn.az.boot.aop.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Liz
 * @version 2019/11/27
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogDao, SysLog> implements LogService {

}
