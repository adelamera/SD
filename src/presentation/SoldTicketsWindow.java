package presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import business.model.ShowModel;
import business.model.TicketModel;
import business.service.ShowService;
import business.service.TicketService;

public class SoldTicketsWindow {

	public void soldFrame() {
		JFrame principalFrame = new JFrame("Sold Tickets");
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principalFrame.setSize(1000, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 600);
		panel.setBackground(new Color(226, 175, 124));
		principalFrame.add(panel);

		ShowService showService = new ShowService();
		List<ShowModel> allShows = showService.findAll();
		JComboBox<ShowModel> shows = new JComboBox<ShowModel>();
		shows.setBounds(100, 50, 500, 50);
		for (int i = 0; i < allShows.size(); i++) {
			shows.addItem(allShows.get(i));
		}
		panel.add(shows);

		JTextArea soldTickets = new JTextArea();
		soldTickets.setBounds(100, 200, 500, 300);
		panel.add(soldTickets);
		
		JButton back = new JButton("BACK");
		back.setBounds(650, 430, 200, 50);
		panel.add(back);


		shows.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				soldTickets.setText("");
				ShowModel chosenShow = (ShowModel) shows.getSelectedItem();
				TicketService ticketService = new TicketService();
				List<TicketModel> soldTicketsList = ticketService.getSoldTickets(chosenShow.getIdShow());
				for (int i = 0; i < soldTicketsList.size(); i++) {
					soldTickets.append(soldTicketsList.get(i).toString());
					soldTickets.append("\n");
				}
			}

		});
		
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				principalFrame.setVisible(false);

			}

		});

		principalFrame.setVisible(true);
	}

}
