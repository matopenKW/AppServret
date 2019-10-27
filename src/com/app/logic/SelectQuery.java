package com.app.logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.app.exception.MinutesException;
import com.app.util.ConnectionUtils;

public abstract class SelectQuery<E> extends AbstractQuery<E>{

	public abstract E setObject(ResultSet result) throws SQLException;

	public SelectQuery(Connection connection, String sql) {
		super(connection, sql);
	}

	/** 作ってみよう！ */
	public int getCount() {
		return 0;
	}

	public E getResult() throws MinutesException {
		Statement stmt = null;
		ResultSet result = null;

		E dto = null;
		try {
			stmt = super.connection.createStatement();
			result = stmt.executeQuery(super.sql);
			while (result.next()) {
				dto = setObject(result);
			}
		} catch (Exception e) {
			throw new MinutesException(e.getMessage());
		} finally {
			ConnectionUtils.close(result);
			ConnectionUtils.close(stmt);
		}
		return dto;
	}


	public ArrayList<E> getResultList() throws MinutesException {
		Statement stmt = null;
		ResultSet result = null;

		ArrayList<E> list = new ArrayList<>();
		try {
			stmt = super.connection.createStatement();
			result = stmt.executeQuery(super.sql);
			while (result.next()) {
				list.add(setObject(result));
			}
		} catch (Exception e) {
			throw new MinutesException(e.getMessage());
		} finally {
			ConnectionUtils.close(result);
			ConnectionUtils.close(stmt);
		}
		return list;
	}

	public int execute() {
		return 0;
	}

}