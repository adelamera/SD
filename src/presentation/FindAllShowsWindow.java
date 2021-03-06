package presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import business.model.ShowModel;
import business.service.ShowService;

public class FindAllShowsWindow {

	public void findAllSFrame() {
		JFrame principalFrame = new JFrame("Find shows");
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principalFrame.setSize(1000, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 600);
		panel.setBackground(new Color(226, 175, 124));
		principalFrame.add(panel);

		ShowService showService = new ShowService();
		List<ShowModel> shows = showService.findAll();
		JTextArea showsText = new JTextArea();
		showsText.setBounds(250, 70, 200, 200);
		for (int i = 0; i < shows.size(); i++) {
			showsText.append(shows.get(i).toString());
			showsText.append("\n");
		}
		panel.add(showsText);

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
