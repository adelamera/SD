package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import business.model.ShowModel;
import business.service.ShowService;
import business.service.TicketService;
import dataAccess.dbmodel.TicketDto;
import dataAccess.repository.TicketRepositoryMySql;

class SellTicketTest {

	public static ShowModel show;

	public static TicketRepositoryMySql setup1() {

		TicketRepositoryMySql mockedTicketRepository = Mockito.mock(TicketRepositoryMySql.class);

		TicketDto ticket1 = new TicketDto();
		ticket1.setIdTicket(26);
		ticket1.setSeatRow(1);
		ticket1.setSeatNr(4);
		ticket1.setIdShow(8);

		TicketDto ticket2 = new TicketDto();
		ticket2.setIdTicket(27);
		ticket2.setSeatRow(1);
		ticket2.setSeatNr(5);
		ticket2.setIdShow(8);

		Mockito.when(mockedTicketRepository.findFreeTicketsShow(8)).thenReturn(Arrays.asList(ticket1, ticket2));
		Mockito.when(mockedTicketRepository.update(ticket1)).thenReturn(true);
		Mockito.when(mockedTicketRepository.update(ticket2)).thenReturn(true);
		return mockedTicketRepository;

	}

	public static ShowService setup2() {

		ShowService mockedShowService = Mockito.mock(ShowService.class);
		SimpleDateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");

		show = new ShowModel();
		show.setIdShow(8);
		show.setTitle("Don Quijote");
		show.setGenre("Balet");
		try {
			show.setDate(formatter.parse("20.01.2018"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		show.setNrTickets(2);

		Mockito.when(mockedShowService.update(show)).thenReturn(true);
		return mockedShowService;

	}

	@Test
	void SellTicket_3Tickets_ThereAreNoAvailableTickets() {

		// Arrange
		TicketRepositoryMySql mockedTicketRepository = SellTicketTest.setup1();
		ShowService mockedShowService = SellTicketTest.setup2();
		TicketService ticketService = new TicketService();
		ticketService.setTicketRepository(mockedTicketRepository);
		ticketService.setShowService(mockedShowService);

		// Act
		String expected = null;
		for (int i = 0; i < 3; i++) {
			expected = ticketService.sellTicket(show);
		}

		// Assert
		assertEquals(expected, "There are no tickets available");
	}

	@Test
	void SellTicket_1Ticket_ThereAreNoAvailableTickets() {

		// Arrange
		TicketRepositoryMySql mockedTicketRepository = SellTicketTest.setup1();
		ShowService mockedShowService = SellTicketTest.setup2();
		TicketService ticketService = new TicketService();
		ticketService.setTicketRepository(mockedTicketRepository);
		ticketService.setShowService(mockedShowService);

		// Act
		String expected = ticketService.sellTicket(show);

		// Assert
		assertEquals(expected, "Row: 1 Seat: 4");
	}

}
