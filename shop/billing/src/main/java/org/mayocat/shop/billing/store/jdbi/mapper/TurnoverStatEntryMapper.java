package org.mayocat.shop.billing.store.jdbi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mayocat.shop.billing.model.stats.TurnoverStatEntry;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @version $Id$
 */
public class TurnoverStatEntryMapper implements ResultSetMapper<TurnoverStatEntry>
{
    @Override
    public TurnoverStatEntry map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException
    {
        TurnoverStatEntry entry = new TurnoverStatEntry();
        entry.setNumberOfOrders(resultSet.getInt("orders"));
        entry.setTotal(resultSet.getBigDecimal("total"));
        return entry;
    }
}
