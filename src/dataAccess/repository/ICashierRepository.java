package dataAccess.repository;

import java.util.List;

import dataAccess.dbmodel.CashierDto;

public interface ICashierRepository {

	// create
	public abstract boolean create(CashierDto cashier);

	// read
	public abstract CashierDto getById(int idCashier);

	public abstract List<CashierDto> getAll();

	// update
	public abstract void update(CashierDto cashier);

	// delete
	public abstract boolean delete(int idCashier);

}
