package com.app.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.exception.MinutesException;
import com.app.form.ActionForm;

public abstract class AbstractAction {

	protected HttpServletRequest request = null;

	protected HttpServletResponse response = null;

	public abstract ActionForm execute() throws MinutesException;

	/** DBコネクション */
	private Connection connection = null;
	protected Connection getConnection() {
		return this.connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public AbstractAction(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
}

