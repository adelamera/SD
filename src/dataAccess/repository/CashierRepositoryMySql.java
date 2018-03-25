package dataAccess.repository;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccess.JDBConnectionWrapper;
import dataAccess.dbmodel.CashierDto;

public class CashierRepositoryMySql implements ICashierRepository {

	private final JDBConnectionWrapper connectionWrapper;

	public CashierRepositoryMySql(JDBConnectionWrapper connectionWrapper) {
		this.connectionWrapper = connectionWrapper;
	}

	private CashierDto getCashierFromResultSet(ResultSet rs) throws SQLException, NoSuchAlgorithmException {
		CashierDto cashier = new CashierDto();
		cashier.setIdUser(rs.getInt("userId"));
		cashier.setUsername(rs.getString("username"));
		cashier.setPassword(rs.getString("password"));
		cashier.setRole(rs.getString("role"));
		return cashier;
	}

	private PreparedStatement setStatement(PreparedStatement statement, CashierDto cashier) throws SQLException {

		statement.setString(1, cashier.getUsername());
		statement.setString(2, cashier.getPassword());
		statement.setString(3, cashier.getRole());
		return statement;
	}

	@Override
	public boolean create(CashierDto cashier) {
		Connection connection = connectionWrapper.getConnection();
		PreparedStatement insertStatement = null;
		try {
			String sql = "INSERT INTO user (username,password,role)" + " VALUES (?,?,?)";
			insertStatement = connection.prepareStatement(sql);
			insertStatement = setStatement(insertStatement, cashier);
			insertStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public CashierDto getById(int idCashier) {
		Connection connection = connectionWrapper.getConnection();
		CashierDto cashier = new CashierDto();
		PreparedStatement findStatement = null;
		try {
			String sql = "SELECT * FROM user WHERE userId = ?";
			findStatement = connection.prepareStatement(sql);
			findStatement.setInt(1, idCashier);
			ResultSet rs = findStatement.executeQuery();

			if (rs.next()) {
				cashier = getCashierFromResultSet(rs);
			}
		} catch (SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return cashier;
	}

	@Override
	public List<CashierDto> getAll() {
		Connection connection = connectionWrapper.getConnection();
		List<CashierDto> cashiers = new ArrayList<CashierDto>();
		PreparedStatement findAllStatement = null;
		try {
			String sql = "SELECT * FROM user";
			findAllStatement = connection.prepareStatement(sql);
			ResultSet rs = findAllStatement.executeQuery();

			while (rs.next()) {
				cashiers.add(getCashierFromResultSet(rs));
			}
		} catch (SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return cashiers;
	}

	@Override
	public void update(CashierDto cashier) {
		Connection connection = connectionWrapper.getConnection();
		PreparedStatement updateStatement = null;
		try {
			String sql = "UPDATE user SET username = ?, password = ?, role = ? WHERE userId = ?";
			updateStatement = connection.prepareStatement(sql);
			updateStatement = setStatement(updateStatement, cashier);
			updateStatement.setInt(4, cashier.getIdUser());
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean delete(int idCashier) {
		Connection connection = connectionWrapper.getConnection();
		PreparedStatement deleteStatement = null;
		try {
			String sql = "DELETE FROM user WHERE userId = ?";
			deleteStatement = connection.prepareStatement(sql);
			deleteStatement.setInt(1, idCashier);
			deleteStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		JDBConnectionWrapper connection = new JDBConnectionWrapper("theater");
		CashierRepositoryMySql c = new CashierRepositoryMySql(connection);
		CashierDto user = new CashierDto();
		user.setUsername("mary");
		user.setPassword("adelaa");
		user.setRole("cashier");
		c.create(user);
	}

}
