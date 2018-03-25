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

public class SellTicketsWindow {

	public void sellFrame() {
		JFrame principalFrame = new JFrame("Sell Tickets");
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principalFrame.setSize(1000, 600);

		JFrame ticketFrame = new JFrame("Ticket");
		ticketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ticketFrame.setSize(500, 500);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 600);
		panel.setBackground(new Color(226, 175, 124));
		principalFrame.add(panel);

		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(0, 0, 500, 500);
		panel2.setBackground(new Color(226, 175, 124));
		ticketFrame.add(panel2);

		JTextArea ticketInfo = new JTextArea();
		ticketInfo.setBounds(10, 10, 300, 300);
		panel2.add(ticketInfo);
		
		JButton back = new JButton("BACK");
		back.setBounds(650, 430, 200, 50);
		panel.add(back);


		ShowService showService = new ShowService();
		List<ShowModel> allShows = showService.findAll();
		JComboBox<ShowModel> shows = new JComboBox<ShowModel>();
		shows.setBounds(150, 50, 500, 50);
		for (int i = 0; i < allShows.size(); i++) {
			shows.addItem(allShows.get(i));
		}
		panel.add(shows);

		shows.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ShowModel chosenShow = (ShowModel) shows.getSelectedItem();
				TicketService ticketservice = new TicketService();
				TicketModel ticket = ticketservice.sellTicket(chosenShow);
				ticketInfo.setText("Ticket for show " + chosenShow.getTitle() + " ");
				ticketInfo.append(ticket.toString());
				ticketFrame.setVisible(true);

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
