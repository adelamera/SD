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
import business.service.ShowService;

public class DeleteShowWindow {

	public void deleteSFrame() {
		JFrame principalFrame = new JFrame("Delete show");
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principalFrame.setSize(1000, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 600);
		panel.setBackground(new Color(226, 175, 124));
		principalFrame.add(panel);

		JTextField idShowText = new JTextField("Enter show's id to delete");
		idShowText.setBounds(250, 70, 200, 50);
		panel.add(idShowText);

		JButton delete = new JButton("DELETE");
		delete.setBounds(250, 170, 200, 50);
		panel.add(delete);

		JButton back = new JButton("BACK");
		back.setBounds(650, 430, 200, 50);
		panel.add(back);

		idShowText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				idShowText.setText("");
			}
		});

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int idShow = Integer.parseInt(idShowText.getText());
				ShowService showService = new ShowService();
				showService.delete(idShow);

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
