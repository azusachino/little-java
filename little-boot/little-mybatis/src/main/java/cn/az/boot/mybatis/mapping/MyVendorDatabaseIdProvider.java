package cn.az.boot.mybatis.mapping;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.DatabaseIdProvider;

/**
 * @author az
 */
public class MyVendorDatabaseIdProvider implements DatabaseIdProvider {

    @Override
    public String getDatabaseId(DataSource dataSource) {
        return "mysql";
    }
}
