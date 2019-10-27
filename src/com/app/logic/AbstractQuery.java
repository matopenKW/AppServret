package com.app.logic;

import java.sql.Connection;

public abstract class AbstractQuery<E>{

	protected Connection connection = null;
	protected String sql = "";

	public AbstractQuery(Connection connection, String sql) {
		this.connection = connection;
		this.sql = sql;
	}
}