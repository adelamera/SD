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
import business.service.CashierService;

public class DeleteCashierWindow {

	public void deleteFrame() {
		JFrame principalFrame = new JFrame("Delete cashier");
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principalFrame.setSize(1000, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 600);
		panel.setBackground(new Color(226, 175, 124));
		principalFrame.add(panel);

		JTextField idUserText = new JTextField("Enter cashier's id to delete");
		idUserText.setBounds(250, 70, 200, 50);
		panel.add(idUserText);

		JButton delete = new JButton("DELETE");
		delete.setBounds(250, 170, 200, 50);
		panel.add(delete);

		JButton back = new JButton("BACK");
		back.setBounds(650, 430, 200, 50);
		panel.add(back);

		idUserText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				idUserText.setText("");
			}
		});

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int idUser = Integer.parseInt(idUserText.getText());
				CashierService cashierService = new CashierService();
				cashierService.delete(idUser);

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
