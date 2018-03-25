package presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import business.model.CashierModel;
import business.service.CashierService;

public class UpdateCashierWindow {

	public void updateCFrame() {

		JFrame principalFrame = new JFrame("Update cashier");
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principalFrame.setSize(1000, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 600);
		panel.setBackground(new Color(226, 175, 124));
		principalFrame.add(panel);

		JTextField idUserText = new JTextField("Enter cashier's id to update");
		idUserText.setBounds(250, 70, 200, 50);
		panel.add(idUserText);

		JTextField usernameText = new JTextField("Username");
		usernameText.setBounds(250, 170, 200, 50);
		panel.add(usernameText);

		JPasswordField passwordText = new JPasswordField();
		passwordText.setBounds(250, 270, 200, 50);
		panel.add(passwordText);

		JButton create = new JButton("UPDATE");
		create.setBounds(250, 370, 200, 50);
		panel.add(create);

		JButton back = new JButton("BACK");
		back.setBounds(650, 430, 200, 50);
		panel.add(back);

		idUserText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				idUserText.setText("");
			}
		});

		usernameText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				usernameText.setText("");
			}
		});

		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int idUser = Integer.parseInt(idUserText.getText());
				String username = usernameText.getText();
				String password = String.valueOf(passwordText.getPassword());
				CashierService cashierService = new CashierService();
				CashierModel cashierToUpdate = cashierService.findById(idUser);
				if ((username != null) && !(username.equals("Username"))) {
					cashierToUpdate.setUsername(username);
				}
				if (password != null) {
					cashierToUpdate.setPasswordEncrypt(password);
				}
				cashierService.update(cashierToUpdate);

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
