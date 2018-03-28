package presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import business.model.ShowModel;
import business.model.TicketModel;
import business.service.ShowService;
import business.service.TicketService;

public class CreateShowWindow {

	public void createSWindow() {
		JFrame principalFrame = new JFrame("Create show");
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principalFrame.setSize(1000, 600);

		JFrame successFrame = new JFrame("Success");
		successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		successFrame.setSize(400, 400);

		JFrame errorFrame = new JFrame("Error");
		errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		errorFrame.setSize(400, 400);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 600);
		panel.setBackground(new Color(226, 175, 124));
		principalFrame.add(panel);

		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(0, 0, 400, 400);
		panel2.setBackground(new Color(226, 175, 124));
		successFrame.add(panel2);

		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(0, 0, 400, 400);
		panel3.setBackground(new Color(226, 175, 124));
		errorFrame.add(panel3);

		JLabel success = new JLabel("The show was successfully created");
		success.setBounds(10, 100, 300, 50);
		panel2.add(success);

		JLabel error = new JLabel("The show was not created");
		error.setBounds(10, 100, 300, 50);
		panel3.add(error);

		JTextField titleText = new JTextField("Title");
		titleText.setBounds(250, 10, 200, 50);
		panel.add(titleText);

		JTextField genreText = new JTextField("Genre");
		genreText.setBounds(250, 80, 200, 50);
		panel.add(genreText);

		JTextField distributionText = new JTextField("Distribution");
		distributionText.setBounds(250, 150, 200, 50);
		panel.add(distributionText);

		JTextField dateText = new JTextField("Date");
		dateText.setBounds(250, 220, 200, 50);
		panel.add(dateText);

		JTextField nrTicketsText = new JTextField("Number tickets");
		nrTicketsText.setBounds(250, 290, 200, 50);
		panel.add(nrTicketsText);

		JButton create = new JButton("CREATE");
		create.setBounds(250, 390, 200, 50);
		panel.add(create);

		JButton back = new JButton("BACK");
		back.setBounds(650, 430, 200, 50);
		panel.add(back);

		titleText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				titleText.setText("");
			}
		});

		genreText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				genreText.setText("");
			}
		});

		distributionText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				distributionText.setText("");
			}
		});

		dateText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dateText.setText("");
			}
		});

		nrTicketsText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				nrTicketsText.setText("");
			}
		});

		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");
				String title = titleText.getText();
				String genre = genreText.getText();
				String distribution = distributionText.getText();
				Date date = null;
				try {
					date = formatter.parse(dateText.getText());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				int nrTickets = Integer.parseInt(nrTicketsText.getText());
				ShowModel showToCreate = new ShowModel();
				showToCreate.setTitle(title);
				showToCreate.setGenre(genre);
				if (distribution.equals("Distribution")) {
					distribution = null;
				}
				showToCreate.setDistributionList(distribution);
				showToCreate.setDate(date);
				showToCreate.setNrTickets(nrTickets);
				ShowService showService = new ShowService();
				int createdId = showService.create(showToCreate);
				if (createdId != -1) {
					successFrame.setVisible(true);
					CreateShowWindow.addTickets(createdId, nrTickets);
				} else {
					errorFrame.setVisible(true);
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

	public static void addTickets(int showId, int nrTickets) {
		TicketService ticketService = new TicketService();
		for (int i = 1; i <= 10; i++) {
			if (nrTickets == 0) {
				break;
			} else {
				for (int j = 1; j <= 10; j++) {
					TicketModel ticket = new TicketModel();
					ticket.setSeatRow(i);
					ticket.setSeatNr(j);
					ticket.setIdShow(showId);
					ticketService.create(ticket);
					nrTickets--;
					if (nrTickets == 0) {
						break;
					}
				}
			}
		}

	}
}
