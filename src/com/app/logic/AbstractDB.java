package com.app.logic;

import java.sql.Connection;

public abstract class AbstractDB {

	private Connection connection = null;

	public AbstractDB (Connection connection) {
		this.connection = connection;
	}

	protected Connection getConn() {
		return this.connection;
	}
}