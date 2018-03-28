package business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import business.model.ShowModel;
import business.model.TicketModel;
import dataAccess.JDBConnectionWrapper;
import dataAccess.dbmodel.TicketDto;
import dataAccess.repository.ITicketRepository;
import dataAccess.repository.TicketRepositoryMySql;

public class TicketService implements ITicketService {

	private ITicketRepository repository;
	private ShowService showService;

	public TicketService() {
		this.repository = new TicketRepositoryMySql(new JDBConnectionWrapper("theater"));
		this.showService = new ShowService();
	}

	public void setTicketRepository(TicketRepositoryMySql ticketRepository) {
		this.repository = ticketRepository;
	}

	public void setShowService(ShowService showService) {
		this.showService = showService;
	}

	@Override
	public List<TicketModel> getAllTickets(int idShow) {
		List<TicketDto> ticketsDto = repository.findByShow(idShow);
		List<TicketModel> tickets = new ArrayList<TicketModel>();
		tickets = ticketsDto.stream().map(t -> new TicketModel(t)).collect(Collectors.toList());
		return tickets;
	}

	@Override
	public boolean create(TicketModel ticket) {
		TicketDto ticketDto = mapModel(ticket);
		boolean created = repository.create(ticketDto);
		if (created) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean update(TicketModel ticket) {
		TicketDto ticketDto = mapModel(ticket);
		boolean updated = repository.update(ticketDto);
		if (updated) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public List<TicketModel> getSoldTickets(int idShow) {
		List<TicketDto> ticketsDto = repository.findSoldTicketsShow(idShow);
		List<TicketModel> tickets = new ArrayList<TicketModel>();
		tickets = ticketsDto.stream().map(t -> new TicketModel(t)).collect(Collectors.toList());
		return tickets;
	}

	private TicketDto mapModel(TicketModel ticket) {
		TicketDto ticketDto = new TicketDto();
		ticketDto.setIdShow(ticket.getIdShow());
		ticketDto.setSeatRow(ticket.getSeatRow());
		ticketDto.setSeatNr(ticket.getSeatNr());
		ticketDto.setStatus(ticket.getStatus());
		ticketDto.setIdTicket(ticket.getIdTicket());
		return ticketDto;
	}

	@Override
	public List<TicketModel> getFreeTickets(int idShow) {
		List<TicketDto> ticketsDto = repository.findFreeTicketsShow(idShow);
		List<TicketModel> tickets = new ArrayList<TicketModel>();
		tickets = ticketsDto.stream().map(t -> new TicketModel(t)).collect(Collectors.toList());
		return tickets;
	}

	@Override
	public String sellTicket(ShowModel show) {
		TicketModel ticket = null;
		List<TicketModel> availableTickets = this.getFreeTickets(show.getIdShow());
		if (show.getNrTickets() > 0) {
			ticket = availableTickets.get(0);
			ticket.setStatus("sold");
			this.update(ticket);
			show.setNrTickets(show.getNrTickets() - 1);
			this.showService.update(show);
			return ticket.toString();
		} else
			return ("There are no tickets available");

	}

	@Override
	public boolean editSeat(TicketModel ticket, int seatRow, int seatNr) {
		boolean found = false;
		List<TicketModel> availableTickets = this.getFreeTickets(ticket.getIdShow());
		for (int i = 0; i < availableTickets.size(); i++) {
			TicketModel freeTicket = availableTickets.get(i);
			if ((freeTicket.getSeatNr() == seatNr) && (freeTicket.getSeatRow() == seatRow)) {
				if (freeTicket.getStatus().equals("free")) {
					freeTicket.setStatus("sold");
					this.update(freeTicket);
					ticket.setStatus("free");
					this.update(ticket);
					found = true;
				}
			}
		}
		if (found) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean cancelReservation(TicketModel ticket) {
		if (ticket.getStatus().equals("reserved")) {
			ticket.setStatus("free");
			this.update(ticket);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public TicketModel getTicketById(int idTicket) {
		TicketDto ticketDto = repository.findById(idTicket);
		TicketModel ticket = new TicketModel(ticketDto);
		return ticket;
	}

	@Override
	public boolean makeReservation(TicketModel ticket) {
		if (ticket.getStatus().equals("free")) {
			ticket.setStatus("reserved");
			this.update(ticket);
			return true;
		} else {
			return false;
		}
	}

}
