package dataAccess.dbmodel;

import java.util.Date;

public class ShowDto {

	private int idShow;
	private String title;
	private String genre;
	private String distributionList;
	private Date date;
	private int nrTickets;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDistributionList() {
		return this.distributionList;
	}

	public void setDistributionList(String distributionList) {
		this.distributionList = distributionList;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNrTickets() {
		return this.nrTickets;
	}

	public void setNrTickets(int nrTickets) {
		this.nrTickets = nrTickets;
	}

	public int getIdShow() {
		return this.idShow;
	}

	public void setIdShow(int idShow) {
		this.idShow = idShow;
	}
}
