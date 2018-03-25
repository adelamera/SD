package business.service;

import java.io.FileWriter;
import java.io.IOException;
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

	@Override
	public List<TicketModel> getAllTickets(int idShow) {
		List<TicketDto> ticketsDto = repository.findByShow(idShow);
		List<TicketModel> tickets = new ArrayList<TicketModel>();
		tickets = ticketsDto.stream().map(t -> new TicketModel(t)).collect(Collectors.toList());
		return tickets;
	}

	@Override
	public void update(TicketModel ticket) {
		TicketDto ticketDto = mapModel(ticket);
		repository.update(ticketDto);

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
	public TicketModel sellTicket(ShowModel show) {
		TicketModel ticket = null;
		List<TicketModel> availableTickets = this.getFreeTickets(show.getIdShow());
		if (show.getNrTickets() > 0) {
			ticket = availableTickets.get(0);
			ticket.setStatus("sold");
			this.update(ticket);
			show.setNrTickets(show.getNrTickets() - 1);
			this.showService.update(show);
		} else
			System.out.println("There are no tickets available");
		return ticket;

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
	public void exportTickets(String fileName, int idShow) {
		FileWriter fileWriter = null;
		String fileHeader = "idTicket,seatRow,seatNr";
		List<TicketModel> tickets = this.getSoldTickets(idShow);
		try {

			fileWriter = new FileWriter(fileName);
			fileWriter.append(fileHeader);
			fileWriter.append(String.format("%n"));
			for (int i = 0; i < tickets.size(); i++) {
				TicketModel ticket = tickets.get(i);
				fileWriter.append(String.valueOf(ticket.getIdTicket()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(ticket.getSeatRow()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(ticket.getSeatNr()));
				fileWriter.append(String.format("%n"));

			}
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();

		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
	}

	@Override
	public TicketModel getTicketById(int idTicket) {
		TicketDto ticketDto = repository.findById(idTicket);
		TicketModel ticket = new TicketModel(ticketDto);
		return ticket;
	}

}
