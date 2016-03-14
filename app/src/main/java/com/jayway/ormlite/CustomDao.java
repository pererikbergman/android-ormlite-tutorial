package com.jayway.ormlite;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

public class CustomDao<T, ID> extends BaseDaoImpl<T, ID> {
    protected CustomDao(final Class<T> dataClass) throws SQLException {
        super(dataClass);
    }

    protected CustomDao(final ConnectionSource connectionSource, final Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected CustomDao(final ConnectionSource connectionSource, final DatabaseTableConfig<T> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    @Override
    public int create(final T data) throws SQLException {
        int result = super.create(data);
        // Send an event with EventBus or Otto
        return result;
    }
}
