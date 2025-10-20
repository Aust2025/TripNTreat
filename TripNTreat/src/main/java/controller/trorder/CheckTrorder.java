package controller.trorder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.member.Login;
import controller.member.LoginSuccess;
import po.Member;
import po.Trorder;
import po.TrorderDetail;
import po.service.impl.TrorderDetailServiceImpl;
import po.service.impl.TrorderServiceImpl;
import util.Tool;

public class CheckTrorder extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable orderTable;
	private DefaultTableModel tableModel;

	private Member loggedInMember = Tool.loadMember();
	private TrorderServiceImpl trorderService = new TrorderServiceImpl();
	private TrorderDetailServiceImpl trorderDetailService = new TrorderDetailServiceImpl();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				CheckTrorder frame = new CheckTrorder();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public CheckTrorder() {
		setTitle("會員訂單查詢");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(248, 245, 240));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 標題
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(250, 119, 146));
		headerPanel.setBounds(20, 8, 704, 35);
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);

		JLabel titleLabel = new JLabel("訂單查詢");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
		titleLabel.setBounds(277, 0, 160, 35);
		headerPanel.add(titleLabel);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(250, 119, 146));
		mainPanel.setBounds(20, 50, 704, 400);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		JLabel welcomeLabel = new JLabel("歡迎，" + (loggedInMember != null ? loggedInMember.getName() : "使用者"));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(Color.BLACK);
		welcomeLabel.setFont(new Font("Serif", Font.BOLD, 16));
		welcomeLabel.setBounds(151, 8, 367, 25);
		mainPanel.add(welcomeLabel);

		// 訂單表格
		String[] columnNames = { "訂單編號", "訂單日期", "總金額", "狀態" };
		tableModel = new DefaultTableModel(columnNames, 0);
		orderTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(orderTable);
		scrollPane.setBounds(50, 45, 600, 250);
		mainPanel.add(scrollPane);

		loadOrderData();

		// 點擊查看細項
		orderTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = orderTable.getSelectedRow();
				if (row >= 0) {
					String trorderNo = tableModel.getValueAt(row, 0).toString();
					StringBuilder details = new StringBuilder("訂單細節：訂單編號 " + trorderNo + "\n");

					List<TrorderDetail> detailList = trorderDetailService.findAllTrorderDetail();
					for (TrorderDetail detail : detailList) {
						if (detail.getTrorderNo().equals(trorderNo)) {
							details.append("項目：").append(detail.getItemName()).append("，數量：")
									.append(detail.getQuantity()).append("，單價：").append(detail.getUnitPrice())
									.append("，小計：").append(detail.getAmount()).append("\n");
						}
					}
					if (details.toString().equals("訂單細節：訂單編號 " + trorderNo + "\n")) {
						details.append("(此訂單目前沒有細項資料)");
					}
					JOptionPane.showMessageDialog(CheckTrorder.this, details.toString());
				}
			}
		});

		// 返回主頁
		JButton backButton = new JButton("返回主頁");
		backButton.setForeground(Color.BLACK);
		backButton.setBackground(new Color(15, 84, 145));
		backButton.setFont(new Font("Serif", Font.BOLD, 14));
		backButton.setBounds(529, 320, 120, 35);
		mainPanel.add(backButton);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new LoginSuccess().setVisible(true);
				dispose();
			}
		});

		// 登出
		JButton logoutButton = new JButton("登出");
		logoutButton.setForeground(Color.BLACK);
		logoutButton.setBackground(new Color(15, 84, 145));
		logoutButton.setFont(new Font("Serif", Font.BOLD, 14));
		logoutButton.setBounds(540, 5, 100, 35);
		mainPanel.add(logoutButton);
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Tool.clearFile("member.txt");
				new Login().setVisible(true);
				dispose();
			}
		});

		// 新增訂單
		JButton addButton = new JButton("新增訂單");
		addButton.setForeground(Color.BLACK);
		addButton.setBackground(new Color(15, 84, 145));
		addButton.setFont(new Font("Serif", Font.BOLD, 14));
		addButton.setBounds(300, 320, 120, 35);
		mainPanel.add(addButton);
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String totalStr = JOptionPane.showInputDialog("請輸入訂單總金額:");
				if (totalStr != null && !totalStr.isEmpty()) {
					try {
						int total = Integer.parseInt(totalStr);
						Trorder trorder = new Trorder();
						trorder.setTrorderNo("T" + System.currentTimeMillis());
						trorder.setMemberNo(loggedInMember.getMemberno());
						trorder.setEmployeeNo("E001");
						trorder.setOrderDate(LocalDate.now());
						trorder.setTotalAmount(total);
						trorder.setPaidAmount(total);
						trorder.setChangeAmount(0);
						trorderService.addTrorder(trorder);
						loadOrderData();
						JOptionPane.showMessageDialog(null, "新增訂單成功！");
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "輸入金額不正確！");
					}
				}
			}
		});

		setLocationRelativeTo(null);
	}

	private void loadOrderData() {
		if (loggedInMember != null) {
			try {
				List<Trorder> orders = trorderService.findAllTrorderByMemberId(loggedInMember.getMemberno());
				tableModel.setRowCount(0);
				for (Trorder order : orders) {
					tableModel.addRow(
							new Object[] { order.getTrorderNo(), order.getOrderDate(), order.getTotalAmount(), "已完成" });
				}
				if (orders.isEmpty()) {
					JOptionPane.showMessageDialog(this, "目前沒有訂單資料。", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "載入訂單失敗：" + e.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "請先登入會員！", "錯誤", JOptionPane.ERROR_MESSAGE);
			new Login().setVisible(true);
			dispose();
		}
	}
}
