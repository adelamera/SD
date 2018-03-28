package presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminWindow {

	public void adminFrame() {
		JFrame principalFrame = new JFrame("Admin");
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principalFrame.setSize(1000, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 600);
		panel.setBackground(new Color(226, 175, 124));
		principalFrame.add(panel);

		JButton cCashier = new JButton("CREATE CASHIER");
		cCashier.setBounds(50, 50, 250, 50);
		panel.add(cCashier);

		JButton rCashierId = new JButton("FIND CASHIER BY ID");
		rCashierId.setBounds(50, 150, 250, 50);
		panel.add(rCashierId);

		JButton rCashier = new JButton("FIND ALL CASHIERS");
		rCashier.setBounds(50, 250, 250, 50);
		panel.add(rCashier);

		JButton uCashier = new JButton("UPDATE CASHIER");
		uCashier.setBounds(50, 350, 250, 50);
		panel.add(uCashier);

		JButton dCashier = new JButton("DELETE CASHIER");
		dCashier.setBounds(50, 450, 250, 50);
		panel.add(dCashier);

		JButton cShow = new JButton("CREATE SHOW");
		cShow.setBounds(350, 50, 250, 50);
		panel.add(cShow);

		JButton rShowId = new JButton("FIND SHOW BY ID");
		rShowId.setBounds(350, 150, 250, 50);
		panel.add(rShowId);

		JButton rShow = new JButton("FIND ALL SHOWS");
		rShow.setBounds(350, 250, 250, 50);
		panel.add(rShow);

		JButton uShow = new JButton("UPDATE SHOW");
		uShow.setBounds(350, 350, 250, 50);
		panel.add(uShow);

		JButton dShow = new JButton("DELETE SHOW");
		dShow.setBounds(350, 450, 250, 50);
		panel.add(dShow);

		JButton export = new JButton("EXPORT");
		export.setBounds(650, 50, 250, 50);
		panel.add(export);

		JButton back = new JButton("BACK");
		back.setBounds(770, 450, 200, 50);
		panel.add(back);

		cCashier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreateCashierWindow ccw = new CreateCashierWindow();
				ccw.createCFrame();
			}
		});

		uCashier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateCashierWindow ucw = new UpdateCashierWindow();
				ucw.updateCFrame();
			}
		});

		rCashierId.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FindByIdCashierWindow ficw = new FindByIdCashierWindow();
				ficw.findFrame();
			}
		});

		rCashier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FindAllCashiersWindow facw = new FindAllCashiersWindow();
				facw.findAllFrame();
			}
		});

		dCashier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteCashierWindow dcw = new DeleteCashierWindow();
				dcw.deleteFrame();
			}
		});

		cShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreateShowWindow csw = new CreateShowWindow();
				csw.createSWindow();
			}
		});

		uShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateShowWindow usw = new UpdateShowWindow();
				usw.updateSFrame();
			}
		});

		rShowId.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FindByIdShowWindow fisw = new FindByIdShowWindow();
				fisw.findSFrame();
			}
		});

		rShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FindAllShowsWindow fsw = new FindAllShowsWindow();
				fsw.findAllSFrame();
			}
		});

		dShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteShowWindow dsw = new DeleteShowWindow();
				dsw.deleteSFrame();
			}
		});

		export.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ExportWindow ew = new ExportWindow();
				ew.exportFrame();
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
