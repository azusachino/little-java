package cn.az.boot.mybatis.mapping;

import org.apache.ibatis.mapping.DatabaseIdProvider;

import javax.sql.DataSource;

/**
 * @author az
 */
public class MyVendorDatabaseIdProvider implements DatabaseIdProvider {

    @Override
    public String getDatabaseId(DataSource dataSource) {
        return "mysql";
    }
}
