package com.app.dto;

public class MinutesDto {

	private int seqno = 0;
	private String name = "";
	private String dateTime = "";
	private String place = "";
	private String content = "";
	private String saisyuuKousinDate = "";

	public int getSeqno() {
		return seqno;
	}
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSaisyuuKousinDate() {
		return saisyuuKousinDate;
	}
	public void setSaisyuuKousinDate(String saisyuuKousinDate) {
		this.saisyuuKousinDate = saisyuuKousinDate;
	}
}
