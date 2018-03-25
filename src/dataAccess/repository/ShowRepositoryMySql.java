package dataAccess.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccess.JDBConnectionWrapper;
import dataAccess.dbmodel.ShowDto;

public class ShowRepositoryMySql implements IShowRepository {
	private final JDBConnectionWrapper connectionWrapper;

	public ShowRepositoryMySql(JDBConnectionWrapper connectionWrapper) {
		this.connectionWrapper = connectionWrapper;
	}

	private ShowDto getShowFromResultSet(ResultSet rs) throws SQLException {
		ShowDto show = new ShowDto();
		show.setIdShow(rs.getInt("idShow"));
		show.setTitle(rs.getString("title"));
		show.setGenre(rs.getString("genre"));
		show.setDistributionList(rs.getString("distributionList"));
		show.setDate(rs.getDate("date"));
		show.setNrTickets(rs.getInt("nrTickets"));
		return show;
	}

	private PreparedStatement setStatement(PreparedStatement statement, ShowDto show) throws SQLException {
		statement.setString(1, show.getTitle());
		statement.setString(2, show.getGenre());
		statement.setString(3, show.getDistributionList());
		statement.setDate(4, new java.sql.Date(show.getDate().getTime()));
		statement.setInt(5, show.getNrTickets());
		return statement;
	}

	@Override
	public boolean create(ShowDto show) {

		Connection connection = connectionWrapper.getConnection();
		PreparedStatement insertStatement = null;
		try {
			String sql = "INSERT INTO showt (title,genre,distributionList,date,nrTickets)" + " VALUES (?,?,?,?,?)";
			insertStatement = connection.prepareStatement(sql);
			insertStatement = setStatement(insertStatement, show);
			insertStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ShowDto getById(int id) {
		Connection connection = connectionWrapper.getConnection();
		ShowDto show = new ShowDto();
		PreparedStatement findStatement = null;
		try {
			String sql = "SELECT * FROM showt WHERE idShow = ?";
			findStatement = connection.prepareStatement(sql);
			findStatement.setInt(1, id);
			ResultSet rs = findStatement.executeQuery();

			if (rs.next()) {
				show = getShowFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return show;
	}

	@Override
	public List<ShowDto> getAll() {
		Connection connection = connectionWrapper.getConnection();
		List<ShowDto> shows = new ArrayList<ShowDto>();
		PreparedStatement findAllStatement = null;
		try {
			String sql = "SELECT * FROM showt";
			findAllStatement = connection.prepareStatement(sql);
			ResultSet rs = findAllStatement.executeQuery();

			while (rs.next()) {
				shows.add(getShowFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return shows;
	}

	@Override
	public void update(ShowDto show) {
		Connection connection = connectionWrapper.getConnection();
		PreparedStatement updateStatement = null;
		try {
			String sql = "UPDATE showt SET title = ?, genre = ?, distributionList = ?, date = ?, nrTickets = ? WHERE idShow = ?";
			updateStatement = connection.prepareStatement(sql);
			updateStatement = setStatement(updateStatement, show);
			updateStatement.setInt(6, show.getIdShow());
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean delete(int idShow) {
		Connection connection = connectionWrapper.getConnection();
		PreparedStatement deleteStatement = null;
		try {
			String sql = "DELETE FROM showt WHERE idShow = ?";
			deleteStatement = connection.prepareStatement(sql);
			deleteStatement.setInt(1, idShow);
			deleteStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
