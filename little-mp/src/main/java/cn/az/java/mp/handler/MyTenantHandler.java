package cn.az.java.mp.handler;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

/**
 * @author az
 * @since 2020-04-03
 */
public class MyTenantHandler implements TenantHandler {
    /**
     * 获取租户 ID 值表达式，支持多个 ID 条件查询
     * <p>
     * 支持自定义表达式，比如：tenant_id in (1,2) @since 2019-8-2
     *
     * @param where 参数 true 表示为 where 条件 false 表示为 insert 或者 select 条件
     * @return 租户 ID 值表达式
     */
    @Override
    public Expression getTenantId(boolean where) {
        return new LongValue(12321323L);
    }

    /**
     * 获取租户字段名
     *
     * @return 租户字段名
     */
    @Override
    public String getTenantIdColumn() {
        return "id";
    }

    /**
     * 根据表名判断是否进行过滤
     *
     * @param tableName 表名
     * @return 是否进行过滤, true:表示忽略，false:需要解析多租户字段
     */
    @Override
    public boolean doTableFilter(String tableName) {
        return false;
    }
}
