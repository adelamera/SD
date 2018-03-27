package business.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import business.model.TicketModel;

public class CSVExport implements IExport {

	@Override
	public void export(String fileName, List<TicketModel> tickets) {
		FileWriter fileWriter = null;
		String fileHeader = "idTicket,seatRow,seatNr";
		try {

			fileWriter = new FileWriter(fileName);
			fileWriter.append(fileHeader);
			fileWriter.append(String.format("%n"));
			for (int i = 0; i < tickets.size(); i++) {
				TicketModel ticket = tickets.get(i);
				fileWriter.append(String.valueOf(ticket.getIdTicket()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(ticket.getSeatRow()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(ticket.getSeatNr()));
				fileWriter.append(String.format("%n"));

			}
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();

		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}

	}

}
