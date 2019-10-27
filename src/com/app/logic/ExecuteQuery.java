package com.app.logic;

import java.sql.Connection;
import java.sql.Statement;

import com.app.exception.MinutesException;
import com.app.util.ConnectionUtils;

public abstract class ExecuteQuery<E> extends AbstractQuery<E>{

	public ExecuteQuery(Connection connection, String sql) {
		super(connection, sql);
	}

	public int execute() throws MinutesException {
		Statement stmt = null;
		int count = 0;

		try {
			stmt = super.connection.createStatement();
			count = stmt.executeUpdate(super.sql);

		} catch (Exception e) {
			throw new MinutesException(e.getMessage());
		} finally {
			ConnectionUtils.close(stmt);
		}
		return count;
	}

}