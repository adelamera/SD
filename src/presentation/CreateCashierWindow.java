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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import business.model.CashierModel;
import business.service.CashierService;

public class CreateCashierWindow {

	public void createCFrame() {

		JFrame principalFrame = new JFrame("Create cashier");
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

		JLabel success = new JLabel("The cashier was successfully created");
		success.setBounds(10, 100, 300, 50);
		panel2.add(success);

		JLabel error = new JLabel("The cashier was not created");
		error.setBounds(10, 100, 300, 50);
		panel3.add(error);

		JTextField usernameText = new JTextField("Username");
		usernameText.setBounds(250, 70, 200, 50);
		panel.add(usernameText);

		JPasswordField passwordText = new JPasswordField();
		passwordText.setBounds(250, 170, 200, 50);
		panel.add(passwordText);

		JButton create = new JButton("CREATE");
		create.setBounds(250, 270, 200, 50);
		panel.add(create);

		JButton back = new JButton("BACK");
		back.setBounds(650, 430, 200, 50);
		panel.add(back);

		usernameText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				usernameText.setText("");
			}
		});

		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameText.getText();
				String password = String.valueOf(passwordText.getPassword());
				CashierModel cashierToCreate = new CashierModel();
				cashierToCreate.setUsername(username);
				cashierToCreate.setPasswordEncrypt(password);
				CashierService cashierService = new CashierService();
				boolean created = cashierService.create(cashierToCreate);
				if (created) {
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
