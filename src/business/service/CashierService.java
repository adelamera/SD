package business.service;

import java.util.ArrayList;
import java.util.List;
import business.model.CashierModel;
import dataAccess.JDBConnectionWrapper;
import dataAccess.dbmodel.CashierDto;

import dataAccess.repository.CashierRepositoryMySql;
import dataAccess.repository.ICashierRepository;

public class CashierService implements ICashierService {
	private ICashierRepository repository;

	public CashierService() {
		this.repository = new CashierRepositoryMySql(new JDBConnectionWrapper("theater"));

	}

	@Override
	public List<CashierModel> findAll() {
		List<CashierDto> cashiersDto = repository.getAll();
		List<CashierModel> cashiers = new ArrayList<CashierModel>();
		for (int i = 0; i < cashiersDto.size(); i++) {
			cashiers.add(mapDto(cashiersDto.get(i)));
		}
		return cashiers;
	}

	@Override
	public CashierModel findById(int id) {
		CashierDto cashierDto = repository.getById(id);
		return mapDto(cashierDto);
	}

	@Override
	public boolean create(CashierModel cashier) {
		CashierDto cashierDto = null;
		cashierDto = mapModel(cashier);
		boolean created = repository.create(cashierDto);
		if (created) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void update(CashierModel cashier) {
		CashierDto cashierDto = null;
		cashierDto = mapModel(cashier);
		repository.update(cashierDto);

	}

	@Override
	public void delete(int id) {
		repository.delete(id);

	}

	@Override
	public String login(String username, String password) {
		boolean validUsername = false;
		boolean validPassword = false;
		boolean admin = false;
		CashierModel userToCheck = new CashierModel();
		userToCheck.setUsername(username);
		userToCheck.setPasswordEncrypt(password);
		List<CashierModel> users = this.findAll();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(userToCheck.getUsername())) {
				validUsername = true;
				if (users.get(i).getPassword().equals(userToCheck.getPassword())) {
					validPassword = true;
					if (users.get(i).getRole().equals("admin")) {
						admin = true;
					}
					break;
				}
			}
		}
		if (validUsername == false) {
			return "The username is not valid";
		} else if (validPassword == false) {
			return "The password is not valid";
		} else if (admin == true) {
			return "admin";
		} else {
			return "cashier";
		}

	}

	private CashierModel mapDto(CashierDto cashierDto) {
		CashierModel cashier = new CashierModel();
		cashier.setUsername(cashierDto.getUsername());
		cashier.setPassword(cashierDto.getPassword());
		cashier.setIdUser(cashierDto.getIdUser());
		cashier.setRole(cashierDto.getRole());
		return cashier;
	}

	private CashierDto mapModel(CashierModel cashierM) {
		CashierDto cashier = new CashierDto();
		cashier.setUsername(cashierM.getUsername());
		cashier.setPassword(cashierM.getPassword());
		cashier.setIdUser(cashierM.getIdUser());
		cashier.setRole(cashierM.getRole());
		return cashier;
	}

}
