package dataAccess.dbmodel;

public class TicketDto {
	private int idTicket;
	private int seatRow;
	private int seatNr;
	private int idShow;
	private String status;

	public TicketDto() {
		this.status = "free";
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

}
