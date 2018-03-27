package dataAccess.repository;

import java.util.List;

import dataAccess.dbmodel.ShowDto;

public class ShowRepositoryCacheDecorator implements IShowRepository {

	private IShowRepository decoratedRepository;
	private Cache<ShowDto> cache;

	public ShowRepositoryCacheDecorator(IShowRepository showRepository) {
		this.decoratedRepository = showRepository;
		this.cache = new Cache<ShowDto>();
	}

	@Override
	public boolean create(ShowDto show) {
		cache.invalidateCache();
		return decoratedRepository.create(show);
	}

	@Override
	public ShowDto getById(int idShow) {
		if (cache.hasResult()) {
			return cache.load().stream().filter(x -> x.getIdShow() == idShow).findFirst().get();
		}
		return decoratedRepository.getById(idShow);
	}

	@Override
	public List<ShowDto> getAll() {
		if (cache.hasResult()) {
			return cache.load();
		}
		List<ShowDto> shows = decoratedRepository.getAll();
		cache.save(shows);
		return shows;
	}

	@Override
	public void update(ShowDto show) {
		cache.invalidateCache();
		decoratedRepository.update(show);

	}

	@Override
	public boolean delete(int idShow) {
		cache.invalidateCache();
		return decoratedRepository.delete(idShow);
	}

}
