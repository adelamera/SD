package business.service;

import java.util.List;

import business.model.TicketModel;

public interface IExport {

	public abstract void export(String fileName, List<TicketModel> tickets);

}
