package com.app.util;

public class ConnectionUtils {

	public static void close(AutoCloseable close) {
		if(close != null) {
			try {
				close.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}