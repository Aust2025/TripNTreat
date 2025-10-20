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
	private DefaultTableModel tableModel;
	private DefaultTableModel orderModel;
	private JTable orderTable;
	private JTable table;
	private JPanel contentPane;
	private JTextField txtQuantity;
	private JTextField txtCashTendered;
	private JLabel lblTotal;
	private JLabel lblChange;
	private TrorderServiceImpl trorderService;
	private TrorderDetailServiceImpl trorderDetailService;
	private TripServiceImpl tripService;
	private ServiceServiceImpl serviceService;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new AddTrorder().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
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
		lblTitle.setForeground(new Color(0, 0, 0));
		lblTitle.setFont(new Font("Serif", Font.BOLD, 14));
		lblTitle.setBounds(410, 0, 130, 35);
		panelHeader.add(lblTitle);

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		JLabel lblTime = new JLabel(now.format(formatter));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblTime.setForeground(new Color(255, 245, 228));
		lblTime.setBounds(10, 0, 157, 34);
		panelHeader.add(lblTime);
		Timer timer = new Timer(1000, e -> lblTime.setText(LocalDateTime.now().format(formatter)));
		timer.start();

		Member member = Tool.loadMember();
		Employee employee = (Employee) Tool.readFile("employee.txt");
		String welcomeText = member != null
				? "會員：" + member.getName() + " | 員工：" + (employee != null ? employee.getEmployeeNo() : "無")
				: "未登入";
		JLabel lblMember = new JLabel(welcomeText);
		lblMember.setHorizontalAlignment(SwingConstants.CENTER);
		lblMember.setForeground(new Color(0, 0, 0));
		lblMember.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblMember.setBounds(650, 0, 250, 35);
		panelHeader.add(lblMember);

		JPanel panelContent = new JPanel();
		panelContent.setBackground(new Color(250, 119, 146));
		panelContent.setBounds(20, 53, 939, 500);
		contentPane.add(panelContent);
		panelContent.setLayout(null);

		JComboBox<Trip> tripCombo = new JComboBox<>();
		tripCombo.setBounds(120, 28, 726, 25);
		List<Trip> trips = tripService.findAllTrip();
		for (Trip t : trips) {
			tripCombo.addItem(t);
		}
		panelContent.add(tripCombo);

		String[] columnNames = { "類別", "編號", "項目編號", "項目名稱", "價格" };
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		JScrollPane scrollPaneProducts = new JScrollPane(table);
		scrollPaneProducts.setBounds(120, 55, 726, 168);
		panelContent.add(scrollPaneProducts);

		tripCombo.addActionListener(e -> {
			tableModel.setRowCount(0);
			List<Trip> tripList = tripService.findAllTrip();
			for (Trip t : tripList) {
				tableModel.addRow(new Object[] { "行程", t.getTripId(), t.getTripNo(), t.getTripName(), t.getPrice() });
			}
			List<Service> serviceList = serviceService.findAllService();
			for (Service s : serviceList) {
				tableModel.addRow(
						new Object[] { "服務", s.getServiceId(), s.getServiceNo(), s.getServiceName(), s.getPrice() });
			}
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getColumnModel().getColumn(0).setPreferredWidth(60);
			table.getColumnModel().getColumn(1).setPreferredWidth(60);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(230);
			table.getColumnModel().getColumn(4).setPreferredWidth(80);
		});

		if (tripCombo.getItemCount() > 0) {
			tripCombo.setSelectedIndex(0);
		}

		JLabel lblQuantity = new JLabel("數量");
		lblQuantity.setBounds(248, 225, 35, 25);
		lblQuantity.setForeground(new Color(0, 0, 0));
		lblQuantity.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		panelContent.add(lblQuantity);

		txtQuantity = new JTextField("1");
		txtQuantity.setBounds(294, 225, 70, 25);
		txtQuantity.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		txtQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		panelContent.add(txtQuantity);

		String[] orderColumns = { "類別", "商品ID", "商品名稱", "單價", "數量", "小計" };
		orderModel = new DefaultTableModel(orderColumns, 0);
		orderTable = new JTable(orderModel);
		JScrollPane scrollPaneOrder = new JScrollPane(orderTable);
		scrollPaneOrder.setBounds(120, 269, 534, 183);
		panelContent.add(scrollPaneOrder);

		JPanel panelTotal = new JPanel();
		panelTotal.setBounds(660, 269, 180, 43);
		panelTotal.setBackground(new Color(33, 52, 72));
		panelContent.add(panelTotal);
		panelTotal.setLayout(null);

		JLabel lblTotalLabel = new JLabel("總價");
		lblTotalLabel.setForeground(new Color(255, 245, 228));
		lblTotalLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblTotalLabel.setBounds(6, 6, 35, 25);
		panelTotal.add(lblTotalLabel);

		lblTotal = new JLabel("0");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setForeground(new Color(158, 248, 219));
		lblTotal.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		lblTotal.setBounds(39, 5, 98, 25);
		panelTotal.add(lblTotal);

		JLabel lblCurrency = new JLabel("元");
		lblCurrency.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrency.setForeground(new Color(255, 245, 228));
		lblCurrency.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblCurrency.setBounds(149, 6, 25, 25);
		panelTotal.add(lblCurrency);

		JLabel lblCash = new JLabel("實收金額");
		lblCash.setBounds(660, 322, 70, 25);
		lblCash.setForeground(new Color(255, 245, 228));
		lblCash.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		panelContent.add(lblCash);

		txtCashTendered = new JTextField();
		txtCashTendered.setBounds(660, 347, 180, 44);
		txtCashTendered.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		txtCashTendered.setHorizontalAlignment(SwingConstants.CENTER);
		panelContent.add(txtCashTendered);

		JPanel panelChange = new JPanel();
		panelChange.setBounds(660, 403, 180, 43);
		panelChange.setBackground(new Color(33, 52, 72));
		panelContent.add(panelChange);
		panelChange.setLayout(null);

		JLabel lblChangeLabel = new JLabel("找零");
		lblChangeLabel.setForeground(new Color(255, 245, 228));
		lblChangeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblChangeLabel.setBounds(6, 7, 35, 25);
		panelChange.add(lblChangeLabel);

		lblChange = new JLabel("0");
		lblChange.setHorizontalAlignment(SwingConstants.CENTER);
		lblChange.setForeground(new Color(158, 248, 219));
		lblChange.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		lblChange.setBounds(46, 7, 98, 25);
		panelChange.add(lblChange);

		JLabel lblChangeCurrency = new JLabel("元");
		lblChangeCurrency.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeCurrency.setForeground(new Color(255, 245, 228));
		lblChangeCurrency.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblChangeCurrency.setBounds(149, 7, 25, 25);
		panelChange.add(lblChangeCurrency);

		JButton btnCalculateChange = new JButton("計算找零");
		btnCalculateChange.setBounds(660, 469, 180, 25);
		btnCalculateChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				calculateChange();
			}
		});
		panelContent.add(btnCalculateChange);

		JButton btnAddToOrder = new JButton("加入訂單");
		btnAddToOrder.setBounds(448, 225, 115, 25);
		btnAddToOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "請先選擇商品！", "提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String qtyText = txtQuantity.getText().trim();
				if (!InputUtil.isPositiveInteger(qtyText)) {
					JOptionPane.showMessageDialog(null, "請輸入正確的數量！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int qty = Integer.parseInt(qtyText);
				String type = table.getValueAt(selectedRow, 0).toString();
				int itemId = Integer.parseInt(table.getValueAt(selectedRow, 1).toString());
				String itemNo = table.getValueAt(selectedRow, 2).toString();
				String itemName = table.getValueAt(selectedRow, 3).toString();
				int price = Integer.parseInt(table.getValueAt(selectedRow, 4).toString());
				int subtotal = price * qty;

				if (type.equals("行程")) {
					Trip selectedTrip = tripService.findTripById(itemId);
					if (selectedTrip != null && qty > selectedTrip.getStock()) {
						JOptionPane.showMessageDialog(null, "庫存不足！剩餘庫存：" + selectedTrip.getStock(), "錯誤",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				boolean found = false;
				for (int i = 0; i < orderModel.getRowCount(); i++) {
					int existingId = Integer.parseInt(orderModel.getValueAt(i, 1).toString());
					String existingType = orderModel.getValueAt(i, 0).toString();
					if (existingId == itemId && existingType.equals(type)) {
						int oldQty = Integer.parseInt(orderModel.getValueAt(i, 4).toString());
						int newQty = oldQty + qty;
						orderModel.setValueAt(newQty, i, 4);
						orderModel.setValueAt(price * newQty, i, 5);
						found = true;
						break;
					}
				}
				if (!found) {
					orderModel.addRow(new Object[] { type, itemId, itemName, price, qty, subtotal });
				}

				int sum = 0;
				for (int i = 0; i < orderModel.getRowCount(); i++) {
					sum += Integer.parseInt(orderModel.getValueAt(i, 5).toString());
				}
				lblTotal.setText(String.valueOf(sum));
			}
		});
		panelContent.add(btnAddToOrder);

		JButton btnConfirmOrder = new JButton("確認下單");
		btnConfirmOrder.setBounds(610, 225, 115, 25);
		btnConfirmOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Member currentMember = Tool.loadMember(); // 確保重新從 session 讀取最新會員資訊
				if (currentMember == null) {
					JOptionPane.showMessageDialog(null, "請先登入會員！", "錯誤", JOptionPane.ERROR_MESSAGE);
					new Login().setVisible(true);
					dispose();
					return;
				}

				if (orderModel.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "請先加入訂單項目！", "提示", JOptionPane.WARNING_MESSAGE);
					return;
				}

				String cashStr = txtCashTendered.getText().trim();
				if (!InputUtil.isPositiveInteger(cashStr)) {
					JOptionPane.showMessageDialog(null, "請輸入有效的實付金額（正整數）", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int totalAmt = Integer.parseInt(lblTotal.getText());
				int cash = Integer.parseInt(cashStr);
				if (cash < totalAmt) {
					JOptionPane.showMessageDialog(null, "實收金額不足", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String trorderNo = generateTrorderNo();
				String employeeNo = (employee != null) ? employee.getEmployeeNo() : "E001";

				Trorder trorder = new Trorder();
				trorder.setTrorderNo(trorderNo);
				trorder.setMemberNo(currentMember.getMemberno()); // 設定 memberNo
				trorder.setEmployeeNo(employeeNo);
				trorder.setOrderDate(LocalDate.now());
				trorder.setTotalAmount(totalAmt);
				trorder.setPaidAmount(cash);
				trorder.setChangeAmount(cash - totalAmt);

				try {
					trorderService.addTrorder(trorder);

					for (int i = 0; i < orderModel.getRowCount(); i++) {
						String type = orderModel.getValueAt(i, 0).toString();
						int itemId = Integer.parseInt(orderModel.getValueAt(i, 1).toString());
						String itemName = orderModel.getValueAt(i, 2).toString();
						int unitPrice = Integer.parseInt(orderModel.getValueAt(i, 3).toString());
						int qty = Integer.parseInt(orderModel.getValueAt(i, 4).toString());
						int amount = Integer.parseInt(orderModel.getValueAt(i, 5).toString());

						String trorderDetailNo = generateTrorderDetailNo();
						String itemNo = generateItemNo();

						TrorderDetail detail = new TrorderDetail();
						detail.setTrorderdetailNo(trorderDetailNo);
						detail.setTrorderNo(trorderNo); // 綁定主訂單號
						detail.setItemNo(itemNo);
						detail.setItemName(itemName);
						detail.setQuantity(qty);
						detail.setUnitPrice(unitPrice);
						detail.setAmount(amount);

						trorderDetailService.addTrorderDetail(detail);

						if (type.equals("行程")) {
							Trip selectedTrip = tripService.findTripById(itemId);
							if (selectedTrip != null) {
								selectedTrip.setStock(selectedTrip.getStock() - qty);
								tripService.updateTrip(selectedTrip);
							}
						}
					}

					JOptionPane.showMessageDialog(null, "下單成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					orderModel.setRowCount(0);
					lblTotal.setText("0");
					txtCashTendered.setText("");
					lblChange.setText("0");

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "下單失敗：" + ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panelContent.add(btnConfirmOrder);

		JButton btnClearOrder = new JButton("清除訂單");
		btnClearOrder.setBounds(168, 469, 115, 25);
		btnClearOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				orderModel.setRowCount(0);
				lblTotal.setText("0");
				txtCashTendered.setText("");
				lblChange.setText("0");
			}
		});
		panelContent.add(btnClearOrder);

		JButton btnDeleteItem = new JButton("刪除選中項目");
		btnDeleteItem.setBounds(338, 469, 115, 25);
		btnDeleteItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = orderTable.getSelectedRow();
				if (selectedRow != -1) {
					orderModel.removeRow(selectedRow);
					int sum = 0;
					for (int i = 0; i < orderModel.getRowCount(); i++) {
						sum += Integer.parseInt(orderModel.getValueAt(i, 5).toString());
					}
					lblTotal.setText(String.valueOf(sum));
					lblChange.setText("0");
				} else {
					JOptionPane.showMessageDialog(null, "請選擇要刪除的項目！", "提示", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelContent.add(btnDeleteItem);

		JButton btnBack = new JButton("回上一頁");
		btnBack.setBounds(500, 469, 115, 25);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new LoginSuccess().setVisible(true);
				dispose();
			}
		});
		panelContent.add(btnBack);

		JLabel lblTripTitle = new JLabel("熱門行程");
		lblTripTitle.setBounds(110, 6, 70, 25);
		lblTripTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTripTitle.setForeground(new Color(0, 0, 0));
		lblTripTitle.setFont(new Font("Serif", Font.BOLD, 14));
		panelContent.add(lblTripTitle);

		JLabel lblOrderTitle = new JLabel("訂單細項列表");
		lblOrderTitle.setBounds(119, 243, 85, 25);
		lblOrderTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderTitle.setForeground(new Color(0, 0, 0));
		lblOrderTitle.setFont(new Font("Serif", Font.BOLD, 14));
		panelContent.add(lblOrderTitle);

		JButton btnLogout = new JButton("登出");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Tool.clearFile("session_member.ser");
				new Login().setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(869, 7, 70, 25);
		panelHeader.add(btnLogout);

		setLocationRelativeTo(null);
	}

	private void initializeServices() {
		List<Trip> trips = tripService.findAllTrip();
		if (trips.isEmpty()) {
			tripService.addTrip(new Trip("TKO1015A", "日本", "東京", "日本東京3日遊", LocalDate.of(2025, 10, 15), 24000, 17));
			tripService.addTrip(new Trip("HKD1101B", "日本", "北海道", "日本北海道5日遊", LocalDate.of(2025, 11, 1), 40000, 10));
			tripService.addTrip(new Trip("AK1230C", "美國", "阿拉斯加", "美國阿拉斯加10日遊", LocalDate.of(2025, 12, 30), 90000, 18));
			tripService
					.addTrip(new Trip("SF1101B", "美國", "西部大峽谷", "美國西部大峽谷7日遊", LocalDate.of(2025, 11, 1), 110000, 22));
			tripService.addTrip(new Trip("MEL1230C", "澳洲", "雪墨", "澳洲雪墨9日遊", LocalDate.of(2025, 12, 30), 60000, 22));
			tripService.addTrip(new Trip("BNE1101B", "澳洲", "布里斯本", "澳洲布里斯本7日遊", LocalDate.of(2025, 11, 1), 60000, 21));
			tripService.addTrip(new Trip("NL1230C", "歐洲", "荷比", "荷比10日遊", LocalDate.of(2025, 12, 30), 110000, 11));
			tripService.addTrip(new Trip("DE1230C", "歐洲", "德奧", "德奧10日遊", LocalDate.of(2025, 12, 30), 60000, 25));
		}

		List<Service> services = serviceService.findAllService();
		if (services.isEmpty()) {
			serviceService.addService(new Service("S001", "機場Taxi接駁", 2000));
			serviceService.addService(new Service("S002", "托運行李", 700));
			serviceService.addService(new Service("S003", "東京市區飯店升級", 4000));
			serviceService.addService(new Service("S004", "東京壽喜燒吃到飽", 1000));
			serviceService.addService(new Service("S005", "北海道溫泉旅館升級", 2200));
			serviceService.addService(new Service("S006", "北海道羊肉燒烤餐廳", 8000));
			serviceService.addService(new Service("S007", "荷蘭恩荷芬鄉村飯店餐廳", 4500));
			serviceService.addService(new Service("S008", "荷蘭恩荷芬現代酒店升級", 4000));
			serviceService.addService(new Service("S009", "荷蘭阿姆斯特丹文青咖啡館", 1800));
			serviceService.addService(new Service("S010", "荷蘭阿姆斯特丹渡假村升級", 10000));
			serviceService.addService(new Service("S011", "美國阿拉斯加精選酒店升級", 12000));
			serviceService.addService(new Service("S012", "美國阿拉斯加現撈海鮮餐廳", 1500));
			serviceService.addService(new Service("S013", "美國舊金山經典豪華酒店升級", 14000));
			serviceService.addService(new Service("S014", "美國大老鷹餐廳風味料理", 800));
			serviceService.addService(new Service("S015", "德國時尚設計酒店升級", 5400));
			serviceService.addService(new Service("S016", "德國柏林地中海餐館", 600));
			serviceService.addService(new Service("S017", "德國巴伐利亞啤酒餐廳", 3000));
			serviceService.addService(new Service("S018", "德國慕尼黑別墅飯店升級", 7000));
			serviceService.addService(new Service("S019", "澳洲墨爾本遊艇晚餐", 6000));
			serviceService.addService(new Service("S020", "澳洲墨爾本濱海渡假村升級", 9800));
			serviceService.addService(new Service("S021", "澳洲布里斯本酒莊餐廳", 2600));
			serviceService.addService(new Service("S022", "澳洲布里斯本養生會館升級", 5500));
		}
	}

	private String generateTrorderNo() {
		List<Trorder> trorders = trorderService.selectAll();
		int orderCount = trorders.size() + 1;
		return String.format("T%03d", orderCount);
	}

	private String generateTrorderDetailNo() {
		List<TrorderDetail> details = trorderDetailService.findAllTrorderDetail();
		int detailCount = details.size() + 1;
		return String.format("Td%03d", detailCount);
	}

	private String generateItemNo() {
		List<TrorderDetail> details = trorderDetailService.findAllTrorderDetail();
		int itemCount = details.size() + 1;
		return String.format("I%03d", itemCount);
	}

	private void calculateChange() {
		try {
			int totalAmt = Integer.parseInt(lblTotal.getText());
			String cashStr = txtCashTendered.getText().trim();
			if (!InputUtil.isPositiveInteger(cashStr)) {
				throw new NumberFormatException();
			}
			int cash = Integer.parseInt(cashStr);
			if (cash < totalAmt) {
				throw new IllegalArgumentException("實收金額不足。");
			}
			int change = cash - totalAmt;
			lblChange.setText(String.valueOf(change));
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "請輸入有效的金額。", "錯誤", JOptionPane.WARNING_MESSAGE);
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "錯誤", JOptionPane.WARNING_MESSAGE);
		}
	}
}