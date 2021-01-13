package cn.az.boot.mybatis.mapping;

import org.apache.ibatis.mapping.DatabaseIdProvider;

import javax.sql.DataSource;

/**
 * @author az
 * @date 2020-03-09
 */
public class MyVendorDatabaseIdProvider implements DatabaseIdProvider {

    @Override
    public String getDatabaseId(DataSource dataSource) {
        return "mysql";
    }
}
