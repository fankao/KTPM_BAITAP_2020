package sa.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.TopicSubscriber;
import javax.naming.NamingException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.transaction.Transactional.TxType;

import sa.topic.PSManager;

public class ChatGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private String name;
	private JButton btnGui;
	private JList<String> lstNoiDung;
	private JTextArea txaMsg;

	public ChatGUI(String name, Color color) {
		super(name);
		this.name = name;

		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel pnNoiDung = new JPanel();
		pnNoiDung.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(pnNoiDung, BorderLayout.CENTER);
		pnNoiDung.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		pnNoiDung.add(scrollPane);

		lstNoiDung = new JList<String>();
		lstNoiDung.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(lstNoiDung);

		JPanel pnChat = new JPanel();
		pnChat.setPreferredSize(new Dimension(10, 100));
		getContentPane().add(pnChat, BorderLayout.SOUTH);
		pnChat.setLayout(new BorderLayout(0, 0));

		btnGui = new JButton("Gửi");
		btnGui.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnChat.add(btnGui, BorderLayout.EAST);

		JScrollPane scrChat = new JScrollPane();
		scrChat.setViewportBorder(new EmptyBorder(5, 5, 5, 5));
		pnChat.add(scrChat, BorderLayout.CENTER);

		txaMsg = new JTextArea();
		txaMsg.setFont(new Font("Arial", Font.PLAIN, 14));
		scrChat.setViewportView(txaMsg);

		JLabel lblChat = new JLabel("Enter text");
		lblChat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnChat.add(lblChat, BorderLayout.WEST);

		txaMsg.setForeground(color);

		btnGui.addActionListener(this);

		// Show list message chat
		try {
			TopicSubscriber subscriber = PSManager.getInstance().getSubscriber();
			Vector<String> lstMsg = new Vector<String>();
			subscriber.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message message) {
					try {

						if (message instanceof TextMessage) {
							lstMsg.add(((TextMessage) message).getText());
							showListMessage(lstMsg);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});

		} catch (JMSException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Show messages
	 * 
	 * @param lstMsg
	 */
	private void showListMessage(Vector<String> lstMsg) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.removeAllElements();
		for (String msg : lstMsg) {
			listModel.addElement(msg);
		}
		lstNoiDung.setModel(listModel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnGui)) {
			try {
				PSManager.getInstance().sendMessage(name + ": " + txaMsg.getText());
				txaMsg.setText("");
			} catch (JMSException | NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
