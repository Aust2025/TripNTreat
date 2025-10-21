package controller.service;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.employee.Backstage;
import controller.employee.EmployeeLogin;
import po.Trip;
import po.service.impl.TripServiceImpl;

public class AddTrip extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tripNoField;
	private JTextField regionField;
	private JTextField destinationField;
	private JTextField tripNameField;
	private JTextField departureDateField;
	private JTextField priceField;
	private JTextField stockField;
	private JTextField createTripNoField;
	private JTextField createRegionField;
	private JTextField createDestinationField;
	private JTextField createTripNameField;
	private JTextField createDepartureDateField;
	private JTextField createPriceField;
	private JTextField createStockField;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel idLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				AddTrip frame = new AddTrip();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public AddTrip() {
		TripServiceImpl tsi = new TripServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 600, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 560, 35);
		panel.setLayout(null);
		panel.setBackground(new Color(64, 224, 208));
		contentPane.add(panel);

		JLabel lblNewLabel = new JLabel("行程管理");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel.setBounds(241, 0, 78, 35);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 55, 560, 550);
		panel_1.setBackground(new Color(64, 224, 208));
		panel_1.setLayout(null);
		contentPane.add(panel_1);

		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBounds(13, 10, 534, 25);
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setBackground(new Color(250, 240, 230));
		panel_1.add(panel_2_1_1);

		JLabel lblNewLabel_1_1_3 = new JLabel("所有行程資料");
		lblNewLabel_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1_3.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_1_1_3.setBounds(200, 0, 134, 25);
		panel_2_1_1.add(lblNewLabel_1_1_3);

		String[] columnNames = { "編號", "行程編號", "區域", "目的地", "名稱", "出發日期", "價格", "庫存" };
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(13, 45, 534, 120);
		panel_1.add(scrollPane);

		List<Trip> list = tsi.findAllTrip();
		if (list != null) {
			for (Trip t : list) {
				Object[] row = { t.getTripId(), t.getTripNo(), t.getRegion(), t.getDestination(), t.getTripName(),
						t.getDepartureDate(), t.getPrice(), t.getStock() };
				tableModel.addRow(row);
			}
		}

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row >= 0) {
					idLabel.setText(table.getValueAt(row, 0).toString());
					tripNoField.setText(table.getValueAt(row, 1).toString());
					regionField.setText(table.getValueAt(row, 2).toString());
					destinationField.setText(table.getValueAt(row, 3).toString());
					tripNameField.setText(table.getValueAt(row, 4).toString());
					departureDateField.setText(table.getValueAt(row, 5).toString());
					priceField.setText(table.getValueAt(row, 6).toString());
					stockField.setText(table.getValueAt(row, 7).toString());
				}
			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(13, 175, 534, 25);
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(250, 240, 230));
		panel_1.add(panel_2);

		JLabel lblNewLabel_1_1_2 = new JLabel("選取要修改的資料");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1_2.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(150, 0, 234, 25);
		panel_2.add(lblNewLabel_1_1_2);

		JLabel lblId = new JLabel("編號");
		lblId.setForeground(new Color(0, 0, 0));
		lblId.setFont(new Font("Serif", Font.BOLD, 14));
		lblId.setBounds(13, 210, 60, 25);
		panel_1.add(lblId);

		idLabel = new JLabel("");
		idLabel.setForeground(new Color(255, 245, 228));
		idLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		idLabel.setBounds(83, 210, 160, 25);
		panel_1.add(idLabel);

		JLabel lblTripNo = new JLabel("行程編號");
		lblTripNo.setForeground(new Color(0, 0, 0));
		lblTripNo.setFont(new Font("Serif", Font.BOLD, 14));
		lblTripNo.setBounds(13, 245, 60, 25);
		panel_1.add(lblTripNo);

		tripNoField = new JTextField();
		tripNoField.setBounds(83, 245, 160, 25);
		panel_1.add(tripNoField);

		JLabel lblRegion = new JLabel("區域");
		lblRegion.setForeground(new Color(0, 0, 0));
		lblRegion.setFont(new Font("Serif", Font.BOLD, 14));
		lblRegion.setBounds(13, 280, 60, 25);
		panel_1.add(lblRegion);

		regionField = new JTextField();
		regionField.setBounds(83, 280, 160, 25);
		panel_1.add(regionField);

		JLabel lblDestination = new JLabel("目的地");
		lblDestination.setForeground(new Color(0, 0, 0));
		lblDestination.setFont(new Font("Serif", Font.BOLD, 14));
		lblDestination.setBounds(13, 315, 60, 25);
		panel_1.add(lblDestination);

		destinationField = new JTextField();
		destinationField.setBounds(83, 315, 160, 25);
		panel_1.add(destinationField);

		JLabel lblTripName = new JLabel("名稱");
		lblTripName.setForeground(new Color(0, 0, 0));
		lblTripName.setFont(new Font("Serif", Font.BOLD, 14));
		lblTripName.setBounds(290, 210, 60, 25);
		panel_1.add(lblTripName);

		tripNameField = new JTextField();
		tripNameField.setBounds(360, 210, 160, 25);
		panel_1.add(tripNameField);

		JLabel lblDepartureDate = new JLabel("出發日期");
		lblDepartureDate.setForeground(new Color(0, 0, 0));
		lblDepartureDate.setFont(new Font("Serif", Font.BOLD, 14));
		lblDepartureDate.setBounds(290, 245, 60, 25);
		panel_1.add(lblDepartureDate);

		departureDateField = new JTextField();
		departureDateField.setBounds(360, 245, 160, 25);
		panel_1.add(departureDateField);

		JLabel lblPrice = new JLabel("價格");
		lblPrice.setForeground(new Color(0, 0, 0));
		lblPrice.setFont(new Font("Serif", Font.BOLD, 14));
		lblPrice.setBounds(290, 280, 60, 25);
		panel_1.add(lblPrice);

		priceField = new JTextField();
		priceField.setBounds(360, 280, 160, 25);
		panel_1.add(priceField);

		JLabel lblStock = new JLabel("庫存");
		lblStock.setForeground(new Color(0, 0, 0));
		lblStock.setFont(new Font("Serif", Font.BOLD, 14));
		lblStock.setBounds(290, 315, 60, 25);
		panel_1.add(lblStock);

		stockField = new JTextField();
		stockField.setBounds(360, 315, 160, 25);
		panel_1.add(stockField);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(13, 350, 534, 25);
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(250, 240, 230));
		panel_1.add(panel_2_1);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("新增行程的資料");
		lblNewLabel_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1_2_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_1_1_2_1.setBounds(170, 0, 194, 25);
		panel_2_1.add(lblNewLabel_1_1_2_1);

		JLabel lblCreateTripNo = new JLabel("行程編號");
		lblCreateTripNo.setForeground(new Color(0, 0, 0));
		lblCreateTripNo.setFont(new Font("Serif", Font.BOLD, 14));
		lblCreateTripNo.setBounds(13, 385, 60, 25);
		panel_1.add(lblCreateTripNo);

		createTripNoField = new JTextField();
		createTripNoField.setBounds(83, 385, 160, 25);
		panel_1.add(createTripNoField);

		JLabel lblCreateRegion = new JLabel("區域");
		lblCreateRegion.setForeground(new Color(0, 0, 0));
		lblCreateRegion.setFont(new Font("Serif", Font.BOLD, 14));
		lblCreateRegion.setBounds(13, 420, 60, 25);
		panel_1.add(lblCreateRegion);

		createRegionField = new JTextField();
		createRegionField.setBounds(83, 420, 160, 25);
		panel_1.add(createRegionField);

		JLabel lblCreateDestination = new JLabel("目的地");
		lblCreateDestination.setForeground(new Color(0, 0, 0));
		lblCreateDestination.setFont(new Font("Serif", Font.BOLD, 14));
		lblCreateDestination.setBounds(13, 455, 60, 25);
		panel_1.add(lblCreateDestination);

		createDestinationField = new JTextField();
		createDestinationField.setBounds(83, 455, 160, 25);
		panel_1.add(createDestinationField);

		JLabel lblCreateTripName = new JLabel("名稱");
		lblCreateTripName.setForeground(new Color(0, 0, 0));
		lblCreateTripName.setFont(new Font("Serif", Font.BOLD, 14));
		lblCreateTripName.setBounds(290, 385, 60, 25);
		panel_1.add(lblCreateTripName);

		createTripNameField = new JTextField();
		createTripNameField.setBounds(360, 385, 160, 25);
		panel_1.add(createTripNameField);

		JLabel lblCreateDepartureDate = new JLabel("出發日期");
		lblCreateDepartureDate.setForeground(new Color(0, 0, 0));
		lblCreateDepartureDate.setFont(new Font("Serif", Font.BOLD, 14));
		lblCreateDepartureDate.setBounds(290, 420, 60, 25);
		panel_1.add(lblCreateDepartureDate);

		createDepartureDateField = new JTextField();
		createDepartureDateField.setBounds(360, 420, 160, 25);
		panel_1.add(createDepartureDateField);

		JLabel lblCreatePrice = new JLabel("價格");
		lblCreatePrice.setForeground(new Color(0, 0, 0));
		lblCreatePrice.setFont(new Font("Serif", Font.BOLD, 14));
		lblCreatePrice.setBounds(290, 455, 60, 25);
		panel_1.add(lblCreatePrice);

		createPriceField = new JTextField();
		createPriceField.setBounds(360, 455, 160, 25);
		panel_1.add(createPriceField);

		JLabel lblCreateStock = new JLabel("庫存");
		lblCreateStock.setForeground(new Color(0, 0, 0));
		lblCreateStock.setFont(new Font("Serif", Font.BOLD, 14));
		lblCreateStock.setBounds(290, 490, 60, 25);
		panel_1.add(lblCreateStock);

		createStockField = new JTextField();
		createStockField.setBounds(360, 490, 160, 25);
		panel_1.add(createStockField);

		JButton btnNewButton_3 = new JButton("登出");
		btnNewButton_3.setFont(new Font("Serif", Font.PLAIN, 13));
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EmployeeLogin el = new EmployeeLogin();
				el.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setForeground(new Color(0, 0, 0));
		btnNewButton_3.setBackground(new Color(175, 238, 238));
		btnNewButton_3.setBounds(480, 5, 70, 25);
		panel.add(btnNewButton_3);

		JButton btnUpdate = new JButton("修改資料");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (idLabel.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "請先選擇要修改的項目！", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					}

					String tripNo = tripNoField.getText().trim();
					String region = regionField.getText().trim();
					String destination = destinationField.getText().trim();
					String tripName = tripNameField.getText().trim();
					String departureDateStr = departureDateField.getText().trim();
					String priceStr = priceField.getText().trim();
					String stockStr = stockField.getText().trim();

					if (tripNo.isEmpty() || region.isEmpty() || destination.isEmpty() || tripName.isEmpty()
							|| departureDateStr.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
						JOptionPane.showMessageDialog(null, "所有欄位均需填寫！", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					}

					LocalDate departureDate;
					try {
						departureDate = LocalDate.parse(departureDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
					} catch (DateTimeParseException ex) {
						JOptionPane.showMessageDialog(null, "出發日期格式錯誤，需為YYYY-MM-DD！", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					}

					int price;
					int stock;
					try {
						price = Integer.parseInt(priceStr);
						stock = Integer.parseInt(stockStr);
						if (price < 0 || stock < 0) {
							JOptionPane.showMessageDialog(null, "價格和庫存必須為非負數！", "錯誤", JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "價格和庫存必須為數字！", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					}

					Trip t = new Trip();
					t.setTripId(Integer.parseInt(idLabel.getText()));
					t.setTripNo(tripNo);
					t.setRegion(region);
					t.setDestination(destination);
					t.setTripName(tripName);
					t.setDepartureDate(departureDate);
					t.setPrice(price);
					t.setStock(stock);

					tsi.updateTrip(t);
					JOptionPane.showMessageDialog(null, "修改成功，點擊確認後將更新資料", "提示", JOptionPane.INFORMATION_MESSAGE);
					tableModel.setRowCount(0);
					List<Trip> newList = tsi.findAllTrip();
					if (newList != null) {
						for (Trip trip : newList) {
							tableModel.addRow(new Object[] { trip.getTripId(), trip.getTripNo(), trip.getRegion(),
									trip.getDestination(), trip.getTripName(), trip.getDepartureDate(), trip.getPrice(),
									trip.getStock() });
						}
					}
					idLabel.setText("");
					tripNoField.setText("");
					regionField.setText("");
					destinationField.setText("");
					tripNameField.setText("");
					departureDateField.setText("");
					priceField.setText("");
					stockField.setText("");

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "修改過程中發生錯誤：" + ex.getMessage(), "錯誤",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUpdate.setForeground(new Color(255, 245, 228));
		btnUpdate.setBackground(new Color(15, 84, 145));
		btnUpdate.setBounds(432, 315, 115, 25);
		panel_1.add(btnUpdate);

		JButton btnNewButton_1 = new JButton("回上一頁");
		btnNewButton_1.setFont(new Font("Serif", Font.PLAIN, 13));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Backstage bs = new Backstage();
				bs.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBackground(new Color(255, 139, 57));
		btnNewButton_1.setBounds(13, 520, 115, 25);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_3_1 = new JButton("刪除選中資料");
		btnNewButton_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (idLabel.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "請先選擇一筆資料！", "提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int tripId = Integer.parseInt(idLabel.getText());
				String message = "確認要刪除這個行程嗎？\n" + "ID：" + idLabel.getText() + "\n" + "行程編號：" + tripNoField.getText()
						+ "\n" + "名稱：" + tripNameField.getText();
				Object[] options = { "刪除", "取消" };
				int confirm = JOptionPane.showOptionDialog(null, message, "刪除確認", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				if (confirm == 0) {
					try {
						tsi.deleteTrip(tripId);
						JOptionPane.showMessageDialog(null, "刪除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						tableModel.setRowCount(0);
						List<Trip> newList = tsi.findAllTrip();
						if (newList != null) {
							for (Trip trip : newList) {
								tableModel.addRow(new Object[] { trip.getTripId(), trip.getTripNo(), trip.getRegion(),
										trip.getDestination(), trip.getTripName(), trip.getDepartureDate(),
										trip.getPrice(), trip.getStock() });
							}
						}
						idLabel.setText("");
						tripNoField.setText("");
						regionField.setText("");
						destinationField.setText("");
						tripNameField.setText("");
						departureDateField.setText("");
						priceField.setText("");
						stockField.setText("");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "刪除過程中發生錯誤：" + ex.getMessage(), "錯誤",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "已取消刪除", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton_3_1.setForeground(new Color(255, 245, 228));
		btnNewButton_3_1.setBackground(new Color(15, 84, 145));
		btnNewButton_3_1.setBounds(432, 175, 115, 25);
		panel_1.add(btnNewButton_3_1);

		JButton btnUpdate_1 = new JButton("確認新增");
		btnUpdate_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String tripNo = createTripNoField.getText().trim();
					String region = createRegionField.getText().trim();
					String destination = createDestinationField.getText().trim();
					String tripName = createTripNameField.getText().trim();
					String departureDateStr = createDepartureDateField.getText().trim();
					String priceStr = createPriceField.getText().trim();
					String stockStr = createStockField.getText().trim();

					if (tripNo.isEmpty() || region.isEmpty() || destination.isEmpty() || tripName.isEmpty()
							|| departureDateStr.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
						JOptionPane.showMessageDialog(null, "所有欄位均需填寫！", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					}

					LocalDate departureDate;
					try {
						departureDate = LocalDate.parse(departureDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
					} catch (DateTimeParseException ex) {
						JOptionPane.showMessageDialog(null, "出發日期格式錯誤，需為YYYY-MM-DD！", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					}

					int price;
					int stock;
					try {
						price = Integer.parseInt(priceStr);
						stock = Integer.parseInt(stockStr);
						if (price < 0 || stock < 0) {
							JOptionPane.showMessageDialog(null, "價格和庫存必須為非負數！", "錯誤", JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "價格和庫存必須為數字！", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					}

					Trip t = new Trip();
					t.setTripNo(tripNo);
					t.setRegion(region);
					t.setDestination(destination);
					t.setTripName(tripName);
					t.setDepartureDate(departureDate);
					t.setPrice(price);
					t.setStock(stock);

					tsi.addTrip(t);
					JOptionPane.showMessageDialog(null, "新增成功，確認後將更新資料", "提示", JOptionPane.INFORMATION_MESSAGE);
					tableModel.setRowCount(0);
					List<Trip> newList = tsi.findAllTrip();
					if (newList != null) {
						for (Trip trip : newList) {
							tableModel.addRow(new Object[] { trip.getTripId(), trip.getTripNo(), trip.getRegion(),
									trip.getDestination(), trip.getTripName(), trip.getDepartureDate(), trip.getPrice(),
									trip.getStock() });
						}
					}
					createTripNoField.setText("");
					createRegionField.setText("");
					createDestinationField.setText("");
					createTripNameField.setText("");
					createDepartureDateField.setText("");
					createPriceField.setText("");
					createStockField.setText("");

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "新增過程中發生錯誤：" + ex.getMessage(), "錯誤",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUpdate_1.setForeground(new Color(255, 245, 228));
		btnUpdate_1.setBackground(new Color(15, 84, 145));
		btnUpdate_1.setBounds(432, 490, 115, 25);
		panel_1.add(btnUpdate_1);
	}
}