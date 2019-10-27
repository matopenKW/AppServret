package com.app.logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.app.dto.MinutesDto;
import com.app.dto.SyainDto;
import com.app.exception.MinutesException;

public class EditLogic extends AbstractDB{

	public EditLogic(Connection connection) {
		super(connection);
	}

	public int selectMaxSeqno() throws MinutesException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append("        COALESCE(MAX(seqno), 0) +1 AS seqno");
		sql.append("    FROM");
		sql.append("        minutes");

		SelectQuery<Integer> select = new SelectQuery<Integer>(getConn(), sql.toString()){
			@Override
			public Integer setObject(ResultSet rs) throws SQLException {
				int seqno = rs.getInt("seqno");
				return seqno;
			}
		};

		int seqno = select.getResult();

		return seqno;
	}

	public MinutesDto select(int seqno) throws MinutesException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append("        seqno");
		sql.append("        ,name");
		sql.append("        ,dateTime");
		sql.append("        ,place");
		sql.append("        ,content");
		sql.append("    FROM");
		sql.append("        minutes");
		sql.append("  WHERE");
		sql.append("      seqno = ").append(seqno);

		SelectQuery<MinutesDto> select = new SelectQuery<MinutesDto>(getConn(), sql.toString()){
			@Override
			public MinutesDto setObject(ResultSet rs) throws SQLException {
				MinutesDto dto = new MinutesDto();
				dto.setSeqno(rs.getInt("seqno"));
				dto.setName(rs.getString("name"));
				dto.setDateTime(rs.getString("dateTime"));
				dto.setPlace(rs.getString("place"));
				dto.setContent(rs.getString("content"));
				return dto;
			}
		};

		MinutesDto dto = select.getResult();
		return dto;
	}

	public List<SyainDto> selectSyainList(int seqno) throws MinutesException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append("        sanka.syain_code");
		sql.append("        ,m.name");
		sql.append("    FROM");
		sql.append("        sanka_syain sanka");
		sql.append("            LEFT JOIN m_syain m");
		sql.append("                ON sanka.syain_code = m.code");
		sql.append("    WHERE");
		sql.append("        sanka.minutes_seqno = ").append(seqno);

		SelectQuery<SyainDto> select = new SelectQuery<SyainDto>(getConn(), sql.toString()){
			@Override
			public SyainDto setObject(ResultSet rs) throws SQLException {
				SyainDto dto = new SyainDto();
				dto.setCode(rs.getString("syain_code"));
				dto.setName(rs.getString("name"));
				return dto;
			}
		};

		List<SyainDto> list = select.getResultList();
		return list;
	}

	public List<SyainDto> selectSyainMasterList() throws MinutesException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append("        code");
		sql.append("        ,name");
		sql.append("    FROM");
		sql.append("        m_syain");

		SelectQuery<SyainDto> select = new SelectQuery<SyainDto>(getConn(), sql.toString()){
			@Override
			public SyainDto setObject(ResultSet rs) throws SQLException {
				SyainDto dto = new SyainDto();
				dto.setCode(rs.getString("code"));
				dto.setName(rs.getString("name"));
				return dto;
			}
		};

		List<SyainDto> list = select.getResultList();
		return list;
	}

	public void insert(MinutesDto dto) throws MinutesException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT");
		sql.append("    INTO");
		sql.append("        minutes(");
		sql.append("            seqno");
		sql.append("            ,name");
		sql.append("            ,dateTime");
		sql.append("            ,place");
		sql.append("            ,content");
		sql.append("            ,saisyuu_kousin_date");
		sql.append("        )");
		sql.append("    VALUES");
		sql.append("        (");
		sql.append("            ").append(dto.getSeqno());
		sql.append("            ,'").append(dto.getName()).append("'");
		sql.append("            ,'").append(dto.getDateTime()).append("'");
		sql.append("            ,'").append(dto.getPlace()).append("'");
		sql.append("            ,'").append(dto.getContent()).append("'");
		sql.append("            ,current_timestamp");
		sql.append("        )");

		ExecuteQuery<String> insert = new ExecuteQuery<String>(getConn(), sql.toString()) {};
		insert.execute();
	}

	public void insertSyain(int minutesSeqno, SyainDto dto) throws MinutesException {

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT");
		sql.append("    INTO");
		sql.append("        public.sanka_syain(");
		sql.append("            minutes_seqno");
		sql.append("            ,seqno");
		sql.append("            ,syain_code");
		sql.append("        )");
		sql.append("    VALUES");
		sql.append("        (");
		sql.append("            ").append(minutesSeqno);
		sql.append("            ,(");
		sql.append("                    SELECT");
		sql.append("                            COALESCE(MAX(seqno), 0) +1 AS seqno");
		sql.append("                        FROM");
		sql.append("                            sanka_syain");
		sql.append("                        WHERE");
		sql.append("                            minutes_seqno = ").append(minutesSeqno);
		sql.append("            )");
		sql.append("            ,'").append(dto.getCode()).append("'");
		sql.append("        );");

		ExecuteQuery<String> insert = new ExecuteQuery<String>(getConn(), sql.toString()) {};
		insert.execute();
	}

	public void update(MinutesDto dto) throws MinutesException {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE");
		sql.append("        minutes");
		sql.append("    SET");
		sql.append("        name = '").append(dto.getName()).append("'");
		sql.append("        ,dateTime = '").append(dto.getDateTime()).append("'");
		sql.append("        ,place = '").append(dto.getPlace()).append("'");
		sql.append("        ,content = '").append(dto.getContent()).append("'");
		sql.append("        ,saisyuu_kousin_date = current_timestamp");
		sql.append("    WHERE");
		sql.append("        seqno = ").append(dto.getSeqno());

		ExecuteQuery<String> update = new ExecuteQuery<String>(getConn(), sql.toString()) {};
		update.execute();
	}

	public void delete(int seqno) throws MinutesException {

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE");
		sql.append("    FROM");
		sql.append("        minutes");
		sql.append("    WHERE");
		sql.append("        seqno = ").append(seqno);

		ExecuteQuery<String> delete = new ExecuteQuery<String>(getConn(), sql.toString()) {};
		delete.execute();
	}

	public void deleteSyain(int seqno) throws MinutesException {

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE");
		sql.append("    FROM");
		sql.append("        sanka_syain");
		sql.append("    WHERE");
		sql.append("        minutes_seqno = ").append(seqno);

		ExecuteQuery<String> delete = new ExecuteQuery<String>(getConn(), sql.toString()) {};
		delete.execute();
	}
}