package com.app.logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dto.MinutesDto;
import com.app.exception.MinutesException;

public class MinutesListLogic extends AbstractDB{

	public MinutesListLogic(Connection connection) {
		super(connection);
	}

	public List<MinutesDto> select() throws MinutesException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append("        seqno");
		sql.append("        ,name");
		sql.append("    FROM");
		sql.append("        minutes");

		SelectQuery<MinutesDto> select = new SelectQuery<MinutesDto>(getConn(), sql.toString()){
			@Override
			public MinutesDto setObject(ResultSet rs) throws SQLException {
				MinutesDto dto = new MinutesDto();
				dto.setSeqno(rs.getInt("seqno"));
				dto.setName(rs.getString("name"));
				return dto;
			}
		};

		ArrayList<MinutesDto> list = select.getResultList();
		return list;
	}

	private void update() {
		ExecuteQuery<String> select = new ExecuteQuery<String>(getConn(), "") {};
	}








}