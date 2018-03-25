package business.model;

import dataAccess.dbmodel.TicketDto;

public class TicketModel {

	private int idTicket;
	private int seatRow;
	private int seatNr;
	private int idShow;
	private String status;

	public TicketModel() {
		this.status = "free";
	}

	public TicketModel(TicketDto ticketDto) {
		this.setIdShow(ticketDto.getIdShow());
		this.setSeatRow(ticketDto.getSeatRow());
		this.setSeatNr(ticketDto.getSeatNr());
		this.setStatus(ticketDto.getStatus());
		this.setIdTicket(ticketDto.getIdTicket());
	}

	public int getSeatRow() {
		return this.seatRow;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}

	public int getSeatNr() {
		return this.seatNr;
	}

	public void setSeatNr(int seatNr) {
		this.seatNr = seatNr;
	}

	public int getIdTicket() {
		return this.idTicket;
	}

	public void setIdTicket(int id) {
		this.idTicket = id;
	}

	public int getIdShow() {
		return this.idShow;
	}

	public void setIdShow(int id) {
		this.idShow = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		if ((status.equals("sold")) || (status.equals("free")) || (status.equals("reserved"))) {
			this.status = status;
		}
	}

	public String toString() {
		return "Row: " + this.seatRow + " Seat: " + this.seatNr;
	}

}
