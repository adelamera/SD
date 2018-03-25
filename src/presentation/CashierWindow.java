package presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CashierWindow {

	public void cashierFrame() {
		JFrame principalFrame = new JFrame("Cashier");
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principalFrame.setSize(1000, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 600);
		panel.setBackground(new Color(226, 175, 124));
		principalFrame.add(panel);

		JButton sell = new JButton("SELL TICKETS");
		sell.setBounds(250, 50, 250, 50);
		panel.add(sell);

		JButton see = new JButton("SEE SOLD TICKETS");
		see.setBounds(250, 150, 250, 50);
		panel.add(see);

		JButton cancel = new JButton("CANCEL RESERVATION");
		cancel.setBounds(250, 250, 250, 50);
		panel.add(cancel);

		JButton edit = new JButton("EDIT SEAT");
		edit.setBounds(250, 350, 250, 50);
		panel.add(edit);
		
		JButton back = new JButton("BACK");
		back.setBounds(650, 430, 200, 50);
		panel.add(back);

		sell.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SellTicketsWindow stw = new SellTicketsWindow();
				stw.sellFrame();

			}

		});

		see.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SoldTicketsWindow stw = new SoldTicketsWindow();
				stw.soldFrame();

			}

		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CancelReservationWindow crw = new CancelReservationWindow();
				crw.cancelFrame();

			}

		});

		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EditSeatWindow esw = new EditSeatWindow();
				esw.editFrame();

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
