package presentation;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import business.service.CashierService;

public class LoginWindow {

	public void loginFrame() {
		JFrame principalFrame = new JFrame("Log in");
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principalFrame.setSize(1000, 600);

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
		errorFrame.add(panel2);

		JLabel logIn = new JLabel("LOG IN");
		logIn.setBounds(180, 60, 100, 50);
		panel.add(logIn);

		JTextField usernameText = new JTextField();
		usernameText.setBounds(250, 70, 100, 30);
		panel.add(usernameText);

		JPasswordField password = new JPasswordField();
		password.setBounds(250, 120, 100, 30);
		panel.add(password);

		JButton login = new JButton("LOG IN");
		login.setBounds(250, 200, 250, 50);
		panel.add(login);

		JLabel messageLabel = new JLabel();
		messageLabel.setBounds(10, 100, 200, 50);
		panel2.add(messageLabel);

		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameText.getText();
				String pass = String.valueOf(password.getPassword());
				CashierService userService = new CashierService();
				String message = userService.login(username, pass);
				if (message.equals("admin")) {
					AdminWindow aw = new AdminWindow();
					aw.adminFrame();
				} else if (message.equals("cashier")) {
					CashierWindow cw = new CashierWindow();
					cw.cashierFrame();
				} else {
					messageLabel.setText(message);
					errorFrame.setVisible(true);

				}

			}

		});

		principalFrame.setVisible(true);
	}

	public static void main(String[] args) {
		LoginWindow lw = new LoginWindow();
		lw.loginFrame();
	}

}
