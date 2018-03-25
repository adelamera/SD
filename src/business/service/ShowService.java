package business.service;

import java.util.ArrayList;
import java.util.List;
import business.model.ShowModel;
import dataAccess.JDBConnectionWrapper;
import dataAccess.dbmodel.ShowDto;
import dataAccess.repository.IShowRepository;
import dataAccess.repository.ShowRepositoryMySql;

public class ShowService implements IShowService {

	private IShowRepository repository;

	public ShowService() {
		this.repository = new ShowRepositoryMySql(new JDBConnectionWrapper("theater"));
	}

	@Override
	public List<ShowModel> findAll() {
		List<ShowDto> showsDto = repository.getAll();
		List<ShowModel> shows = new ArrayList<ShowModel>();
		for (int i = 0; i < showsDto.size(); i++) {
			shows.add(mapDto(showsDto.get(i)));
		}
		return shows;
	}

	@Override
	public ShowModel findById(int id) {
		ShowDto showDto = repository.getById(id);
		return mapDto(showDto);
	}

	@Override
	public boolean create(ShowModel show) {
		ShowDto showDto = mapModel(show);
		boolean created = repository.create(showDto);
		if (created) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void update(ShowModel show) {
		ShowDto showDto = mapModel(show);
		repository.update(showDto);
	}

	@Override
	public void delete(int id) {
		repository.delete(id);

	}

	private ShowModel mapDto(ShowDto showDto) {
		ShowModel show = new ShowModel();
		show.setTitle(showDto.getTitle());
		show.setGenre(showDto.getGenre());
		show.setIdShow(showDto.getIdShow());
		show.setDistributionList(showDto.getDistributionList());
		show.setDate(showDto.getDate());
		show.setNrTickets(showDto.getNrTickets());
		return show;
	}

	private ShowDto mapModel(ShowModel showM) {
		ShowDto show = new ShowDto();
		show.setTitle(showM.getTitle());
		show.setGenre(showM.getGenre());
		show.setIdShow(showM.getIdShow());
		show.setDistributionList(showM.getDistributionList());
		show.setDate(showM.getDate());
		show.setNrTickets(showM.getNrTickets());
		return show;
	}

}
