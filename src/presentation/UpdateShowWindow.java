package presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.model.ShowModel;
import business.service.ShowService;

public class UpdateShowWindow {

	public void updateSFrame() {
		JFrame principalFrame = new JFrame("Update show");
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

		JTextField idShowText = new JTextField("Enter show's id to update");
		idShowText.setBounds(250, 10, 200, 50);
		panel.add(idShowText);

		JTextField titleText = new JTextField("Title");
		titleText.setBounds(250, 80, 200, 50);
		panel.add(titleText);

		JTextField genreText = new JTextField("Genre");
		genreText.setBounds(250, 150, 200, 50);
		panel.add(genreText);

		JTextField distributionText = new JTextField("Distribution");
		distributionText.setBounds(250, 220, 200, 50);
		panel.add(distributionText);

		JTextField dateText = new JTextField("Date");
		dateText.setBounds(250, 290, 200, 50);
		panel.add(dateText);

		JTextField nrTicketsText = new JTextField("Number tickets");
		nrTicketsText.setBounds(250, 360, 200, 50);
		panel.add(nrTicketsText);

		JButton update = new JButton("UPDATE");
		update.setBounds(250, 460, 200, 50);
		panel.add(update);

		JButton back = new JButton("BACK");
		back.setBounds(650, 430, 200, 50);
		panel.add(back);

		idShowText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				idShowText.setText("");
			}
		});

		titleText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				titleText.setText("");
			}
		});

		genreText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				genreText.setText("");
			}
		});

		distributionText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				distributionText.setText("");
			}
		});

		dateText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dateText.setText("");
			}
		});

		nrTicketsText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				nrTicketsText.setText("");
			}
		});

		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");
				int idShow = Integer.parseInt(idShowText.getText());
				ShowService showService = new ShowService();
				ShowModel showToUpdate = showService.findById(idShow);
				String title = titleText.getText();
				String genre = genreText.getText();
				String distribution = distributionText.getText();
				Date date = null;
				if ((dateText.getText() != null) && !(dateText.getText().equals("Date"))) {
					try {
						date = formatter.parse(dateText.getText());
						showToUpdate.setDate(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if ((nrTicketsText.getText() != null) && !(nrTicketsText.getText().equals("Number tickets"))) {
					int nrTickets = Integer.parseInt(nrTicketsText.getText());
					showToUpdate.setNrTickets(nrTickets);
				}
				if ((title != null) && !(title.equals("Title"))) {
					showToUpdate.setTitle(title);
				}
				if ((genre != null) && !(genre.equals("Genre"))) {
					showToUpdate.setGenre(genre);
				}
				if ((distribution != null) && !(distribution.equals("Distribution"))) {
					showToUpdate.setDistributionList(distribution);
				}

				showService.update(showToUpdate);

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
