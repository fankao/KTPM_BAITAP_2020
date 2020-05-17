package sa.app.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.google.gson.Gson;

import sa.app.entity.BenhNhan;
import sa.app.jms.JMSManage;

public class PatientGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaBN;
	private JTextField txtCMND;
	private JTextField txtHoTen;
	private JButton btnTimMSSV;
	private JButton btnTimCMND;
	private JButton btnSubmit;
	private JTextArea textArea;
	


	public PatientGUI(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel pnTitle = new JPanel();
		getContentPane().add(pnTitle, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("NHẬN BỆNH");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 35));
		pnTitle.add(lblTitle);

		JPanel pnMain = new JPanel();
		pnMain.setBorder(new TitledBorder(new LineBorder(null), "Th\u00F4ng tin b\u1EC7nh nh\u00E2n",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		((TitledBorder) pnMain.getBorder()).setTitleFont(new Font("Arial", Font.PLAIN, 20));
		getContentPane().add(pnMain, BorderLayout.CENTER);
		pnMain.setLayout(new BorderLayout(0, 0));

		JPanel pnTextField = new JPanel();
		pnMain.add(pnTextField, BorderLayout.CENTER);
		pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));

		JPanel pnMaBN = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnMaBN.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnTextField.add(pnMaBN);

		JLabel lblMaBN = new JLabel("Mã số bệnh nhân:");
		lblMaBN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnMaBN.add(lblMaBN);

		txtMaBN = new JTextField();
		txtMaBN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnMaBN.add(txtMaBN);
		txtMaBN.setColumns(20);

		btnTimMSSV = new JButton(" ");
		pnMaBN.add(btnTimMSSV);

		JPanel pnCMND = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnCMND.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnTextField.add(pnCMND);

		JLabel lblCMND = new JLabel("Số CMND:");
		lblCMND.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCMND.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnCMND.add(lblCMND);

		txtCMND = new JTextField();
		txtCMND.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnCMND.add(txtCMND);
		txtCMND.setColumns(20);

		btnTimCMND = new JButton(" ");
		pnCMND.add(btnTimCMND);

		JPanel pnHoTen = new JPanel();
		FlowLayout fl_pnHoTen = (FlowLayout) pnHoTen.getLayout();
		fl_pnHoTen.setAlignment(FlowLayout.LEFT);
		pnTextField.add(pnHoTen);

		JLabel lblHoTen = new JLabel("Họ và tên:");
		lblHoTen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnHoTen.add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnHoTen.add(txtHoTen);
		txtHoTen.setColumns(20);

		JPanel pnDiaChi = new JPanel();
		pnTextField.add(pnDiaChi);
		pnDiaChi.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiaChi.setVerticalAlignment(SwingConstants.TOP);
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnDiaChi.add(lblDiaChi);

		JPanel pnTxa = new JPanel();
		pnDiaChi.add(pnTxa);
		pnTxa.setLayout(new BorderLayout(0, 0));

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Arial", Font.PLAIN, 18));
		textArea.setRows(5);
		textArea.setColumns(20);
		pnTxa.add(textArea, BorderLayout.CENTER);

		JPanel pnSubmit = new JPanel();
		pnMain.add(pnSubmit, BorderLayout.SOUTH);

		btnSubmit = new JButton("Lưu thông tin");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 24));
		pnSubmit.add(btnSubmit);

		lblHoTen.setPreferredSize(lblMaBN.getPreferredSize());
		lblCMND.setPreferredSize(lblMaBN.getPreferredSize());
		lblDiaChi.setPreferredSize(lblMaBN.getPreferredSize());
		lblHoTen.setPreferredSize(lblMaBN.getPreferredSize());

		btnSubmit.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnSubmit)) {
			Gson gson = new Gson();
			try {
				/*
				 * Gửi thông tin bệnh nhân đến bác sĩ đồng thời lưu vào csdl
				 */
				Session session = JMSManage.getInstance().getSesstion();
				Destination destination = session.createQueue("HospitalQueue");
				MessageProducer producer = session.createProducer(destination);
				
				BenhNhan bn = new BenhNhan(txtMaBN.getText(), txtCMND.getText(), txtHoTen.getText(), textArea.getText());
				// encoding obj thành json
				String json = gson.toJson(bn);
				
				TextMessage message = session.createTextMessage(json);
				
				producer.send(message);
				
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
		
	}

}
