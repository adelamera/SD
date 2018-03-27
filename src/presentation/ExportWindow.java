package presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import business.model.TicketModel;
import business.service.ConcreteCreator;
import business.service.IExport;
import business.service.TicketService;

public class ExportWindow {

	public void exportFrame() {

		JFrame principalFrame = new JFrame("Export tickets");
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principalFrame.setSize(1000, 600);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 600);
		panel.setBackground(new Color(226, 175, 124));
		principalFrame.add(panel);

		JTextField idShowText = new JTextField("Enter show's id");
		idShowText.setBounds(250, 170, 200, 50);
		panel.add(idShowText);

		JCheckBox csv = new JCheckBox("CSV");
		csv.setBounds(250, 70, 50, 50);
		panel.add(csv);

		JCheckBox xml = new JCheckBox("XML");
		xml.setBounds(350, 70, 50, 50);
		panel.add(xml);

		JButton export = new JButton("EXPORT");
		export.setBounds(250, 270, 200, 50);
		panel.add(export);

		JButton back = new JButton("BACK");
		back.setBounds(650, 430, 200, 50);
		panel.add(back);

		idShowText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				idShowText.setText("");
			}
		});

		export.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int idShow = Integer.parseInt(idShowText.getText());
				TicketService ticketService = new TicketService();
				List<TicketModel> tickets = ticketService.getSoldTickets(idShow);
				String type;
				if (csv.isSelected()) {
					type = "csv";
				} else {
					type = "xml";
				}
				IExport exporter = ConcreteCreator.getExporter(type);
				exporter.export("soldTickets." + type, tickets);
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
