package sa.app.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Vector;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import sa.app.dao.BacSiDAO;
import sa.app.dao.BacSiDAOImpl;
import sa.app.entity.BacSi;
import sa.app.entity.BenhNhan;
import sa.app.entity.KhamBenh;
import sa.app.jms.JMSManage;

public class DoctorGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private BacSiDAO bacSiDAO;

	private JTextField txtCMND;
	private JTextField txtHoTen;
	private JTextArea txaDiaChi;
	private JTextField txtMaBN;
	private JTextArea txaNDKham;
	private JButton btnGoiKham;
	private JList<BenhNhan> list;
	private JButton btnCapNhatTT;

	public DoctorGUI(String title) throws Exception {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel pnDSBenhNhan = new JPanel();
		pnDSBenhNhan.setBorder(
				new TitledBorder(new LineBorder(null, 2), "Danh s\u00E1ch b\u00EAnh nh\u00E2n ch\u1EDD kh\u00E1m",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(pnDSBenhNhan, BorderLayout.WEST);
		pnDSBenhNhan.setLayout(new BorderLayout(0, 0));

		btnGoiKham = new JButton("Gọi khám...");
		btnGoiKham.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnDSBenhNhan.add(btnGoiKham, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		pnDSBenhNhan.add(scrollPane, BorderLayout.CENTER);

		list = new JList<BenhNhan>();
		list.setFont(new Font("Arial", Font.PLAIN, 18));
		scrollPane.setViewportView(list);

		JPanel pnTTBenhNhan = new JPanel();
		pnTTBenhNhan.setBorder(new TitledBorder(new LineBorder(null, 2),
				"Th\u00F4ng tin b\u1EC7nh nh\u00E2n \u0111\u01B0\u1EE3c ch\u1ECDn", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		getContentPane().add(pnTTBenhNhan, BorderLayout.CENTER);
		pnTTBenhNhan.setLayout(new BoxLayout(pnTTBenhNhan, BoxLayout.Y_AXIS));

		JPanel pnTTCT = new JPanel();
		pnTTBenhNhan.add(pnTTCT);
		pnTTCT.setLayout(new BoxLayout(pnTTCT, BoxLayout.Y_AXIS));

		JPanel pnMaBN = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnMaBN.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnTTCT.add(pnMaBN);

		JLabel lblMaBN = new JLabel("Mã số bệnh nhân:");
		lblMaBN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnMaBN.add(lblMaBN);

		txtMaBN = new JTextField();
		txtMaBN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnMaBN.add(txtMaBN);
		txtMaBN.setColumns(20);

		JPanel pnCMND = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnCMND.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnTTCT.add(pnCMND);

		JLabel lblCMND = new JLabel("Số CMND:");
		lblCMND.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCMND.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnCMND.add(lblCMND);

		txtCMND = new JTextField();
		txtCMND.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnCMND.add(txtCMND);
		txtCMND.setColumns(20);

		JPanel pnHoTen = new JPanel();
		FlowLayout fl_pnHoTen = (FlowLayout) pnHoTen.getLayout();
		fl_pnHoTen.setAlignment(FlowLayout.LEFT);
		pnTTCT.add(pnHoTen);

		JLabel lblHoTen = new JLabel("Họ và tên:");
		lblHoTen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnHoTen.add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnHoTen.add(txtHoTen);
		txtHoTen.setColumns(20);

		JPanel pnDiaChi = new JPanel();
		pnTTCT.add(pnDiaChi);
		pnDiaChi.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiaChi.setVerticalAlignment(SwingConstants.TOP);
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnDiaChi.add(lblDiaChi);

		JPanel pnTxa = new JPanel();
		pnDiaChi.add(pnTxa);
		pnTxa.setLayout(new BorderLayout(0, 0));

		txaDiaChi = new JTextArea();
		txaDiaChi.setLineWrap(true);
		txaDiaChi.setFont(new Font("Arial", Font.PLAIN, 18));
		txaDiaChi.setRows(5);
		txaDiaChi.setColumns(20);
		pnTxa.add(txaDiaChi, BorderLayout.CENTER);

		lblHoTen.setPreferredSize(lblMaBN.getPreferredSize());
		lblCMND.setPreferredSize(lblMaBN.getPreferredSize());
		lblDiaChi.setPreferredSize(lblMaBN.getPreferredSize());
		lblHoTen.setPreferredSize(lblMaBN.getPreferredSize());

		JPanel pnNDKham = new JPanel();
		pnNDKham.setBorder(new TitledBorder(new LineBorder(null, 2), "N\u1ED9i dung kh\u00E1m", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnTTBenhNhan.add(pnNDKham);
		pnNDKham.setLayout(new BorderLayout(0, 0));

		JScrollPane scrNDKham = new JScrollPane();
		pnNDKham.add(scrNDKham, BorderLayout.CENTER);

		txaNDKham = new JTextArea();
		txaNDKham.setFont(new Font("Arial", Font.PLAIN, 18));
		scrNDKham.setViewportView(txaNDKham);

		JPanel pnCapNhatTT = new JPanel();
		pnTTBenhNhan.add(pnCapNhatTT);

		btnCapNhatTT = new JButton("Cập nhật thông tin khám bệnh");
		btnCapNhatTT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnCapNhatTT.add(btnCapNhatTT);

		Session session = JMSManage.getInstance().getSesstion();
		Destination destination = session.createQueue("HospitalQueue");
		MessageConsumer consumer = session.createConsumer(destination);
		Gson gson = new Gson();

		Vector<BenhNhan> benhNhans = new Vector<BenhNhan>();

		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				if (message instanceof TextMessage) {
					try {
						BenhNhan benhNhan = gson.fromJson(((TextMessage) message).getText(), BenhNhan.class);
						benhNhans.add(benhNhan);
						list.setListData(benhNhans);
					} catch (JsonSyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		bacSiDAO = new BacSiDAOImpl();
		btnGoiKham.addActionListener(this);
		btnCapNhatTT.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		BenhNhan bn = list.getSelectedValue();
		if (o.equals(btnGoiKham)) {
			showInfoPatient(bn);
		} else if (o.equals(btnCapNhatTT)) {

			saveInfoPatient(bn);
		}

	}

	/**
	 * Lưu thông tin khám bệnh
	 * 
	 * @param bn
	 */
	private void saveInfoPatient(BenhNhan bn) {
		BacSi bacSi = new BacSi("222", "Tuấn Anh");
		bacSi.luuTTKhamBenh(new KhamBenh(bn, LocalDateTime.now(), txaNDKham.getText()));
		bacSiDAO.save(bacSi);
	}

	/**
	 * Hiện thông tin chi tiết bệnh nhân
	 * 
	 * @param bn
	 */
	private void showInfoPatient(BenhNhan bn) {
		txtMaBN.setText(bn.getMsbn());
		txtCMND.setText(bn.getSocmnd());
		txtHoTen.setText(bn.getHoten());
		txaDiaChi.setText(bn.getDiachi());
		EditableTextComponent(false, txtCMND, txtHoTen, txtMaBN, txaDiaChi);

	}

	/**
	 * Vô hiệu hoá hoặc kích hoạt tính năng chỉnh sửa
	 */
	private void EditableTextComponent(boolean isEditable, JTextComponent... components) {
		for (JTextComponent component : components) {
			component.setEditable(isEditable);
		}

	}

}
