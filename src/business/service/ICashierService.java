package business.service;

import java.util.List;

import business.model.CashierModel;

public interface ICashierService {

	public abstract List<CashierModel> findAll();

	public abstract CashierModel findById(int id);

	public abstract boolean create(CashierModel cashier);

	public abstract void update(CashierModel cashier);

	public abstract void delete(int id);

	public abstract String login(String username, String password);

}
