package dataAccess.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dataAccess.JDBConnectionWrapper;
import dataAccess.dbmodel.TicketDto;

public class TicketRepositoryMySql implements ITicketRepository {

	private final JDBConnectionWrapper connectionWrapper;

	public TicketRepositoryMySql(JDBConnectionWrapper connectionWrapper) {
		this.connectionWrapper = connectionWrapper;
	}

	@Override
	public List<TicketDto> findByShow(int idShow) {
		Connection connection = connectionWrapper.getConnection();
		List<TicketDto> tickets = new ArrayList<TicketDto>();
		PreparedStatement findStatement = null;
		try {
			String sql = "SELECT * FROM ticket WHERE idShow = ?";
			findStatement = connection.prepareStatement(sql);
			findStatement.setInt(1, idShow);
			ResultSet rs = findStatement.executeQuery();

			while (rs.next()) {
				tickets.add(getTicketFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	private TicketDto getTicketFromResultSet(ResultSet rs) throws SQLException {
		TicketDto ticket = new TicketDto();
		ticket.setIdTicket(rs.getInt("idTicket"));
		ticket.setSeatRow(rs.getInt("seatRow"));
		ticket.setSeatNr(rs.getInt("seatNr"));
		ticket.setIdShow(rs.getInt("idShow"));
		ticket.setStatus(rs.getString("status"));
		return ticket;
	}

	@Override
	public void update(TicketDto ticket) {
		Connection connection = connectionWrapper.getConnection();
		PreparedStatement updateStatement = null;
		try {
			String sql = "UPDATE ticket SET seatRow = ?, seatNr = ?, idShow = ?, status = ? WHERE idTicket = ?";
			updateStatement = connection.prepareStatement(sql);
			updateStatement = setStatement(updateStatement, ticket);
			updateStatement.setInt(5, ticket.getIdTicket());
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<TicketDto> findSoldTicketsShow(int idShow) {
		Connection connection = connectionWrapper.getConnection();
		List<TicketDto> tickets = new ArrayList<TicketDto>();
		PreparedStatement findStatement = null;
		try {
			String sql = "SELECT * FROM ticket WHERE idShow = ? AND status = ?";
			findStatement = connection.prepareStatement(sql);
			findStatement.setInt(1, idShow);
			findStatement.setString(2, "sold");
			ResultSet rs = findStatement.executeQuery();
			while (rs.next()) {
				tickets.add(getTicketFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	private PreparedStatement setStatement(PreparedStatement statement, TicketDto ticket) throws SQLException {
		statement.setInt(1, ticket.getSeatRow());
		statement.setInt(2, ticket.getSeatNr());
		statement.setInt(3, ticket.getIdShow());
		statement.setString(4, ticket.getStatus());
		return statement;
	}

	@Override
	public List<TicketDto> findFreeTicketsShow(int idShow) {
		Connection connection = connectionWrapper.getConnection();
		List<TicketDto> tickets = new ArrayList<TicketDto>();
		PreparedStatement findStatement = null;
		try {
			String sql = "SELECT * FROM ticket WHERE idShow = ? AND status = ?";
			findStatement = connection.prepareStatement(sql);
			findStatement.setInt(1, idShow);
			findStatement.setString(2, "free");
			ResultSet rs = findStatement.executeQuery();
			while (rs.next()) {
				tickets.add(getTicketFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	@Override
	public boolean create(TicketDto ticket) {
		Connection connection = connectionWrapper.getConnection();
		PreparedStatement insertStatement = null;
		try {
			String sql = "INSERT INTO ticket (seatRow,seatNr,idShow,status)" + " VALUES (?,?,?,?)";
			insertStatement = connection.prepareStatement(sql);
			insertStatement = setStatement(insertStatement, ticket);
			insertStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public TicketDto findById(int idTicket) {
		Connection connection = connectionWrapper.getConnection();
		TicketDto ticket = null;
		PreparedStatement findStatement = null;
		try {
			String sql = "SELECT * FROM ticket WHERE idTicket = ?";
			findStatement = connection.prepareStatement(sql);
			findStatement.setInt(1, idTicket);
			ResultSet rs = findStatement.executeQuery();

			if (rs.next()) {
				ticket = getTicketFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticket;
	}

}
