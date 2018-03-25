package business.service;

import java.util.List;

import business.model.ShowModel;

public interface IShowService {

	public abstract List<ShowModel> findAll();

	public abstract ShowModel findById(int id);

	public abstract boolean create(ShowModel show);

	public void update(ShowModel show);

	public void delete(int id);

}
