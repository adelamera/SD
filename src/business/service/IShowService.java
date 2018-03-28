package business.service;

import java.util.List;

import business.model.ShowModel;

public interface IShowService {

	public abstract List<ShowModel> findAll();

	public abstract ShowModel findById(int id);

	public abstract int create(ShowModel show);

	public boolean update(ShowModel show);

	public void delete(int id);

}
