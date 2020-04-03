package cn.az.java.mp.filter;

import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;

/**
 * @author az
 * @since 2020-04-03
 */
public class MySqlParserFilter implements ISqlParserFilter {

    @Override
    public boolean doFilter(MetaObject metaObject) {
        MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
        if ("".equals(ms.getId())) {
            return true;
        }
        return false;
    }
}
