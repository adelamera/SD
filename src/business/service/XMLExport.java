package business.service;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import business.model.TicketModel;

public class XMLExport implements IExport {

	@Override
	public void export(String fileName, List<TicketModel> tickets) {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element rootElement = doc.createElement("Tickets");
			doc.appendChild(rootElement);
			for (int i = 0; i < tickets.size(); i++) {
				TicketModel ticket = tickets.get(i);
				rootElement.appendChild(getTicket(doc, ticket.getIdTicket(), ticket.getSeatRow(), ticket.getSeatNr()));
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult file = new StreamResult(new File(fileName));
			transformer.transform(source, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Node getTicket(Document doc, int id, int seatRow, int seatNr) {
		Element ticket = doc.createElement("Ticket");
		ticket.setAttribute("id", String.valueOf(id));
		ticket.appendChild(getEmployeeElements(doc, ticket, "seatRow", seatRow));
		ticket.appendChild(getEmployeeElements(doc, ticket, "seatNr", seatNr));
		return ticket;
	}

	private static Node getEmployeeElements(Document doc, Element element, String name, int value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(String.valueOf(value)));
		return node;
	}

}
