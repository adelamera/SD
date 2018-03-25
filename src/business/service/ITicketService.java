package business.service;

import java.util.List;

import business.model.ShowModel;
import business.model.TicketModel;

public interface ITicketService {

	public abstract List<TicketModel> getAllTickets(int idShow);

	public abstract TicketModel getTicketById(int idShow);

	public abstract void update(TicketModel ticket);

	public abstract List<TicketModel> getSoldTickets(int idShow);

	public abstract List<TicketModel> getFreeTickets(int idShow);

	public abstract TicketModel sellTicket(ShowModel show);

	public abstract boolean editSeat(TicketModel ticket, int seatRow, int seatNr);

	public abstract boolean cancelReservation(TicketModel ticket);

	public abstract void exportTickets(String fileName, int idShow);

}
