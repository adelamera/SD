package presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.model.TicketModel;
import business.service.TicketService;

public class AddTicketsWindow {

	public void addTicketsFrame() {

		JFrame principalFrame = new JFrame("Add tickets");
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principalFrame.setSize(1000, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 600);
		panel.setBackground(new Color(226, 175, 124));
		principalFrame.add(panel);

		JTextField idShowText = new JTextField("Enter show's id");
		idShowText.setBounds(250, 170, 200, 50);
		panel.add(idShowText);

		JTextField nrTicketsText = new JTextField("Enter the number of tickets");
		nrTicketsText.setBounds(250, 270, 200, 50);
		panel.add(nrTicketsText);

		JButton add = new JButton("ADD");
		add.setBounds(250, 370, 200, 50);
		panel.add(add);

		JButton back = new JButton("BACK");
		back.setBounds(650, 430, 200, 50);
		panel.add(back);

		idShowText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				idShowText.setText("");
			}
		});

		nrTicketsText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				nrTicketsText.setText("");
			}
		});

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TicketService ticketService = new TicketService();
				int nrTickets = Integer.parseInt(nrTicketsText.getText());
				int id = Integer.parseInt(idShowText.getText());
				for (int i = 1; i <= 10; i++) {
					if (nrTickets == 0) {
						break;
					} else {
						for (int j = 1; j <= 10; j++) {
							TicketModel ticket = new TicketModel();
							ticket.setSeatRow(i);
							ticket.setSeatNr(j);
							ticket.setIdShow(id);
							ticketService.create(ticket);
							nrTickets--;
							if (nrTickets == 0) {
								break;
							}
						}
					}
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
