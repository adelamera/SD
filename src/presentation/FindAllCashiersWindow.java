package presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import business.model.CashierModel;
import business.service.CashierService;

public class FindAllCashiersWindow {

	public void findAllFrame() {

		JFrame principalFrame = new JFrame("Find cashiers");
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principalFrame.setSize(1000, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 600);
		panel.setBackground(new Color(226, 175, 124));
		principalFrame.add(panel);

		CashierService cashierService = new CashierService();
		List<CashierModel> cashiers = cashierService.findAll();
		JTextArea cashiersText = new JTextArea();
		cashiersText.setBounds(250, 70, 200, 200);
		for (int i = 0; i < cashiers.size(); i++) {
			cashiersText.append(cashiers.get(i).toString());
			cashiersText.append("\n");
		}
		panel.add(cashiersText);

		JButton back = new JButton("BACK");
		back.setBounds(650, 430, 200, 50);
		panel.add(back);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				principalFrame.setVisible(false);

			}

		});

		principalFrame.setVisible(true);
	}

}
