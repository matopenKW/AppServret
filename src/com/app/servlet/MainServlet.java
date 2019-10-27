package com.app.servlet;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.app.action.AbstractAction;
import com.app.exception.MinutesException;
import com.app.form.ActionForm;
import com.app.util.ConnectionUtils;

/**
 * Servlet implementation class EmplRegistServlet
 */
@WebServlet("/test/*")
public class MainServlet extends HttpServlet {

	private final String SERVRET_PATH = "test";
	private final String INIT_PATH = "minutesList";

	private String url = "";
	private String user = "";
	private String password = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
	}

	public void init() {
		System.out.println("--------システム開始-----------");

		/** プロパティで設定する？？ */
		url = "jdbc:postgresql://localhost:5432/test";
		user = "postgres";
		password = "postgres";
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forward(request, response);
	}

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forward(request, response);

	}

	/**
	 * メイン処理
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = request.getRequestURL().toString();

		int lastSrash = url.lastIndexOf("/");
		String actionPacName = "com.app.action.";
		String actionName = url.substring(lastSrash+1, url.length());

		if (StringUtils.isBlank(actionName) || SERVRET_PATH.equals(actionName)) {
			actionName = INIT_PATH;
		}

		String jspName = execute(request, response, actionPacName, actionName);

		request.setAttribute("servretPath", SERVRET_PATH);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/" + jspName +".jsp");
		//  フォワードによるページ遷移
		dispatcher.forward(request, response);

	}

	private String execute(HttpServletRequest request, HttpServletResponse response, String actionPacName, String actionName) {

		Connection conn = null;

		try {
			Class<?> cl = Class.forName (actionPacName + cnvFirstCharacter(actionName) + "Action");
			Constructor<?> cunstructor = (Constructor<?>) cl.getConstructor(HttpServletRequest.class, HttpServletResponse.class);
			AbstractAction action = (AbstractAction) cunstructor.newInstance(new Object[]{request, response});

			// DB接続
			conn = getConnection();
			action.setConnection(conn);

			ActionForm form = action.execute();

			for (Field field : form.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				System.out.println(field.getName() + " = " + field.get(form) + "\n");
				request.setAttribute(field.getName(), field.get(form));
			}

			// コミット
			conn.commit();
			return cnvFirstCharacter(actionName);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "404";
		} catch (Exception e) {
			e.printStackTrace();
			return "505";
		} finally {
			ConnectionUtils.close(conn);
		}
	}

	private String cnvFirstCharacter(String str){
		if (str == null != str.length() < 1) {
			return "";
		} else {
			String first = str.substring(0, 1);
			String otherThan = str.substring(1, str.length());
			return first.toUpperCase() + otherThan;
		}
	}

	private Connection getConnection() throws MinutesException {

		try{
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			//オートコミットOFF
			conn.setAutoCommit(false);
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			throw new MinutesException(e.getMessage());
		}
	}

}
