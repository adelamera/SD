package presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.model.TicketModel;
import business.service.TicketService;

public class EditSeatWindow {

	public void editFrame() {
		JFrame principalFrame = new JFrame("Edit Seat");
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

		JLabel success = new JLabel("The seat was edited");
		success.setBounds(10, 100, 300, 50);
		panel2.add(success);

		JLabel error = new JLabel("The edit is not possible");
		error.setBounds(10, 100, 300, 50);
		panel3.add(error);

		JTextField idTicketText = new JTextField("Enter your ticket's id");
		idTicketText.setBounds(250, 70, 200, 50);
		panel.add(idTicketText);

		JTextField rowText = new JTextField("Enter the new row");
		rowText.setBounds(250, 170, 200, 50);
		panel.add(rowText);

		JTextField seatText = new JTextField("Enter the new seat number");
		seatText.setBounds(250, 270, 200, 50);
		panel.add(seatText);

		JButton edit = new JButton("EDIT SEAT");
		edit.setBounds(250, 370, 200, 50);
		panel.add(edit);

		JButton back = new JButton("BACK");
		back.setBounds(650, 430, 200, 50);
		panel.add(back);

		idTicketText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				idTicketText.setText("");
			}
		});

		rowText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				rowText.setText("");
			}
		});

		seatText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				seatText.setText("");
			}
		});

		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int idTicket = Integer.parseInt(idTicketText.getText());
				int seatRow = Integer.parseInt(rowText.getText());
				int seatNr = Integer.parseInt(seatText.getText());
				TicketService ticketService = new TicketService();
				TicketModel ticketToEdit = ticketService.getTicketById(idTicket);
				boolean succ = ticketService.editSeat(ticketToEdit, seatRow, seatNr);
				if (succ) {
					successFrame.setVisible(true);

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

}
