package dataAccess.repository;

import java.util.List;

import dataAccess.dbmodel.ShowDto;

public interface IShowRepository {

	// create
	public abstract boolean create(ShowDto show);

	// read
	public abstract ShowDto getById(int idShow);

	public abstract List<ShowDto> getAll();

	// update
	public abstract void update(ShowDto show);

	// delete
	public abstract boolean delete(int idShow);

}
