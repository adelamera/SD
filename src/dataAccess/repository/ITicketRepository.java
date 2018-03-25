package dataAccess.repository;

import java.util.List;

import dataAccess.dbmodel.TicketDto;

public interface ITicketRepository {

	// create
	public abstract boolean create(TicketDto ticket);

	// read
	public abstract List<TicketDto> findByShow(int idShow);

	public abstract TicketDto findById(int idTicket);

	public abstract List<TicketDto> findSoldTicketsShow(int idShow);

	public abstract List<TicketDto> findFreeTicketsShow(int idShow);

	// update
	public abstract void update(TicketDto ticket);

}
