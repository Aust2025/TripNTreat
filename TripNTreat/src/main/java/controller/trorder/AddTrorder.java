package controller.trorder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.member.Login;
import controller.member.LoginSuccess;
import po.Employee;
import po.Member;
import po.Service;
import po.Trip;
import po.Trorder;
import po.TrorderDetail;
import po.service.impl.ServiceServiceImpl;
import po.service.impl.TripServiceImpl;
import po.service.impl.TrorderDetailServiceImpl;
import po.service.impl.TrorderServiceImpl;
import util.InputUtil;
import util.Tool;

public class AddTrorder extends JFrame {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel, orderModel;
	private JTable table, orderTable;
	private JPanel contentPane;
	private JTextField txtQuantity, txtCashTendered;
	private JLabel lblTotal, lblChange;
	private TrorderServiceImpl trorderService;
	private TrorderDetailServiceImpl trorderDetailService;
	private TripServiceImpl tripService;
	private ServiceServiceImpl serviceService;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new AddTrorder().setVisible(true));
	}

	public AddTrorder() {
		trorderService = new TrorderServiceImpl();
		trorderDetailService = new TrorderDetailServiceImpl();
		tripService = new TripServiceImpl();
		serviceService = new ServiceServiceImpl();
		initializeServices();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 982, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(248, 245, 240));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelHeader = new JPanel();
		panelHeader.setLayout(null);
		panelHeader.setBackground(new Color(250, 119, 146));
		panelHeader.setBounds(20, 10, 939, 35);
		contentPane.add(panelHeader);

		JLabel lblTitle = new JLabel("旅遊行程訂購系統");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Serif", Font.BOLD, 16));
		lblTitle.setBounds(410, 0, 130, 35);
		panelHeader.add(lblTitle);

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		JLabel lblTime = new JLabel(now.format(formatter));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Serif", Font.BOLD, 14));
		lblTime.setForeground(Color.WHITE);
		lblTime.setBounds(10, 0, 157, 34);
		panelHeader.add(lblTime);
		new Timer(1000, e -> lblTime.setText(LocalDateTime.now().format(formatter))).start();

		Member member = Tool.loadMember();
		Employee employee = (Employee) Tool.readFile("employee.txt");
		String welcomeText = member != null
				? "會員：" + member.getName() + " | 員工：" + (employee != null ? employee.getEmployeeNo() : "無")
				: "未登入";
		JLabel lblMember = new JLabel(welcomeText);
		lblMember.setHorizontalAlignment(SwingConstants.CENTER);
		lblMember.setFont(new Font("Serif", Font.BOLD, 14));
		lblMember.setBounds(598, -1, 250, 35);
		panelHeader.add(lblMember);

		JButton btnLogout = new JButton("登出");
		btnLogout.setBounds(869, 7, 70, 25);
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		panelHeader.add(btnLogout);

		JPanel panelContent = new JPanel();
		panelContent.setBackground(new Color(250, 119, 146));
		panelContent.setBounds(20, 53, 939, 500);
		panelContent.setLayout(null);
		contentPane.add(panelContent);

		JComboBox<Trip> tripCombo = new JComboBox<>();
		tripCombo.setBounds(120, 28, 726, 25);
		List<Trip> trips = tripService.findAllTrip();
		for (Trip t : trips)
			tripCombo.addItem(t);
		panelContent.add(tripCombo);

		// 商品表格 (行程 + 服務)
		String[] columnNames = { "類別", "編號", "項目編號", "項目名稱", "價格" };
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		JScrollPane scrollPaneProducts = new JScrollPane(table);
		scrollPaneProducts.setBounds(120, 55, 726, 168);
		panelContent.add(scrollPaneProducts);

		tripCombo.addActionListener(e -> loadProductData());

		JLabel lblQuantity = new JLabel("數量");
		lblQuantity.setBounds(251, 230, 35, 25);
		lblQuantity.setFont(new Font("Serif", Font.BOLD, 14));
		panelContent.add(lblQuantity);

		txtQuantity = new JTextField("1");
		txtQuantity.setBounds(297, 230, 70, 25);
		txtQuantity.setFont(new Font("serif", Font.PLAIN, 14));
		txtQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		panelContent.add(txtQuantity);

		// 訂單細項表格
		String[] orderColumns = { "類別", "商品ID", "商品名稱", "單價", "數量", "小計" };
		orderModel = new DefaultTableModel(orderColumns, 0);
		orderTable = new JTable(orderModel);
		JScrollPane scrollPaneOrder = new JScrollPane(orderTable);
		scrollPaneOrder.setBounds(120, 269, 534, 183);
		panelContent.add(scrollPaneOrder);

		// 總價顯示
		lblTotal = new JLabel("0");
		lblTotal.setFont(new Font("serif", Font.BOLD, 18));
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setForeground(Color.BLACK);
		lblTotal.setBounds(710, 270, 150, 40);
		panelContent.add(lblTotal);

		// 實收金額與找零
		txtCashTendered = new JTextField();
		txtCashTendered.setBounds(710, 320, 150, 30);
		panelContent.add(txtCashTendered);

		lblChange = new JLabel("0");
		lblChange.setFont(new Font("serif", Font.BOLD, 18));
		lblChange.setHorizontalAlignment(SwingConstants.CENTER);
		lblChange.setBounds(710, 360, 150, 40);
		panelContent.add(lblChange);

		JButton btnAddToOrder = new JButton("加入訂單");
		btnAddToOrder.setFont(new Font("Serif", Font.PLAIN, 13));
		btnAddToOrder.setBounds(388, 225, 115, 40);
		btnAddToOrder.addActionListener(e -> addToOrder());
		panelContent.add(btnAddToOrder);

		JButton btnConfirmOrder = new JButton("確認下單");
		btnConfirmOrder.setFont(new Font("Serif", Font.PLAIN, 13));
		btnConfirmOrder.setBounds(528, 226, 115, 38);
		btnConfirmOrder.addActionListener(e -> confirmOrder());
		panelContent.add(btnConfirmOrder);

		JButton btnClearOrder = new JButton("清除訂單");
		btnClearOrder.setFont(new Font("Serif", Font.PLAIN, 13));
		btnClearOrder.setBounds(386, 454, 115, 40);
		btnClearOrder.addActionListener(e -> clearOrder());
		panelContent.add(btnClearOrder);

		JButton btnDeleteItem = new JButton("刪除項目");
		btnDeleteItem.setFont(new Font("Serif", Font.PLAIN, 13));
		btnDeleteItem.setBounds(241, 453, 117, 42);
		btnDeleteItem.addActionListener(e -> deleteSelectedItem());
		panelContent.add(btnDeleteItem);

		JButton btnBack = new JButton("回上一頁");
		btnBack.setFont(new Font("Serif", Font.PLAIN, 13));
		btnBack.setBounds(530, 454, 115, 40);
		btnBack.addActionListener(e -> {
			new LoginSuccess().setVisible(true);
			dispose();
		});
		panelContent.add(btnBack);

		JButton btnChange = new JButton("計算找零");
		btnChange.setBounds(710, 410, 150, 44);
		btnChange.addActionListener(e -> calculateChange());
		panelContent.add(btnChange);

		setLocationRelativeTo(null);
		loadProductData();
	}

	private void initializeServices() {
		if (tripService.findAllTrip().isEmpty()) {
			tripService.addTrip(new Trip("TKO1015A", "日本", "東京", "日本東京3日遊", LocalDate.of(2025, 10, 15), 24000, 17));
		}
		if (serviceService.findAllService().isEmpty()) {
			serviceService.addService(new Service("S001", "機場Taxi接駁", 2000));
		}
	}

	private void loadProductData() {
		tableModel.setRowCount(0);
		for (Trip t : tripService.findAllTrip()) {
			tableModel.addRow(new Object[] { "行程", t.getTripId(), t.getTripNo(), t.getTripName(), t.getPrice() });
		}
		for (Service s : serviceService.findAllService()) {
			tableModel.addRow(
					new Object[] { "服務", s.getServiceId(), s.getServiceNo(), s.getServiceName(), s.getPrice() });
		}
	}

	private void addToOrder() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "請選擇商品！", "提示", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String qtyText = txtQuantity.getText().trim();
		if (!InputUtil.isPositiveInteger(qtyText)) {
			JOptionPane.showMessageDialog(this, "請輸入正確數量！", "錯誤", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int qty = Integer.parseInt(qtyText);
		String type = table.getValueAt(row, 0).toString();
		int id = Integer.parseInt(table.getValueAt(row, 1).toString());
		String name = table.getValueAt(row, 3).toString();
		int price = Integer.parseInt(table.getValueAt(row, 4).toString());
		int subtotal = price * qty;

		orderModel.addRow(new Object[] { type, id, name, price, qty, subtotal });

		int total = 0;
		for (int i = 0; i < orderModel.getRowCount(); i++) {
			total += Integer.parseInt(orderModel.getValueAt(i, 5).toString());
		}
		lblTotal.setText(String.valueOf(total));
	}

	private void confirmOrder() {
		Member member = Tool.loadMember();
		if (member == null) {
			JOptionPane.showMessageDialog(this, "請先登入會員！", "錯誤", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (orderModel.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "請先加入訂單項目！", "提示", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			int total = Integer.parseInt(lblTotal.getText());
			int paid = Integer.parseInt(txtCashTendered.getText());
			int change = paid - total;
			if (paid < total) {
				JOptionPane.showMessageDialog(this, "實收金額不足！", "錯誤", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String trorderNo = String.format("T%03d", trorderService.selectAll().size() + 1);
			Trorder trorder = new Trorder();
			trorder.setTrorderNo(trorderNo);
			trorder.setMemberNo(member.getMemberno());
			trorder.setOrderDate(LocalDate.now());
			trorder.setTotalAmount(total);
			trorder.setPaidAmount(paid);
			trorder.setChangeAmount(change);
			trorderService.addTrorder(trorder);

			int detailStart = trorderDetailService.findAllTrorderDetail().size();
			for (int i = 0; i < orderModel.getRowCount(); i++) {
				TrorderDetail detail = new TrorderDetail();
				detail.setTrorderdetailNo(String.format("Td%03d", detailStart + i + 1));
				detail.setTrorderNo(trorderNo);
				detail.setItemName(orderModel.getValueAt(i, 2).toString());
				detail.setQuantity(Integer.parseInt(orderModel.getValueAt(i, 4).toString()));
				detail.setUnitPrice(Integer.parseInt(orderModel.getValueAt(i, 3).toString()));
				detail.setAmount(Integer.parseInt(orderModel.getValueAt(i, 5).toString()));
				trorderDetailService.addTrorderDetail(detail);
			}

			JOptionPane.showMessageDialog(this, "下單成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
			clearOrder();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "下單失敗：" + ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearOrder() {
		orderModel.setRowCount(0);
		lblTotal.setText("0");
		lblChange.setText("0");
		txtCashTendered.setText("");
	}

	private void deleteSelectedItem() {
		int row = orderTable.getSelectedRow();
		if (row >= 0) {
			orderModel.removeRow(row);
			int total = 0;
			for (int i = 0; i < orderModel.getRowCount(); i++) {
				total += Integer.parseInt(orderModel.getValueAt(i, 5).toString());
			}
			lblTotal.setText(String.valueOf(total));
		}
	}

	private void calculateChange() {
		try {
			int total = Integer.parseInt(lblTotal.getText());
			int cash = Integer.parseInt(txtCashTendered.getText());
			if (cash < total) {
				JOptionPane.showMessageDialog(this, "實收金額不足！", "錯誤", JOptionPane.ERROR_MESSAGE);
				return;
			}
			lblChange.setText(String.valueOf(cash - total));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "請輸入有效金額！", "錯誤", JOptionPane.WARNING_MESSAGE);
		}
	}
}
