package controller.trorder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
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

/**
 * 會員查詢訂單畫面
 */
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
		setBounds(100, 100, 780, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(248, 245, 240));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(250, 119, 146));
		headerPanel.setBounds(20, 8, 724, 35);
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);

		JLabel titleLabel = new JLabel("會員訂單查詢");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
		titleLabel.setBounds(287, 0, 160, 35);
		headerPanel.add(titleLabel);

		JButton logoutButton = new JButton("登出");
		logoutButton.setBounds(572, 2, 120, 35);
		headerPanel.add(logoutButton);
		logoutButton.setForeground(Color.BLACK);
		logoutButton.setBackground(new Color(250, 240, 230));
		logoutButton.setFont(new Font("Serif", Font.BOLD, 14));
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Tool.clearFile("member.txt");
				new Login().setVisible(true);
				dispose();
			}
		});

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(250, 119, 146));
		mainPanel.setBounds(20, 50, 724, 420);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		JLabel welcomeLabel = new JLabel("歡迎，" + (loggedInMember != null ? loggedInMember.getName() : "使用者"));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(Color.BLACK);
		welcomeLabel.setFont(new Font("Serif", Font.BOLD, 16));
		welcomeLabel.setBounds(194, 9, 336, 25);
		mainPanel.add(welcomeLabel);

		// 表格欄位：Trorder + TrorderDetail
		String[] columnNames = { "訂單編號", "訂單日期", "總金額", "狀態" };
		tableModel = new DefaultTableModel(columnNames, 0);
		orderTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(orderTable);
		scrollPane.setBounds(50, 45, 620, 250);
		mainPanel.add(scrollPane);

		loadOrderData();

		// 點擊訂單顯示細項
		orderTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = orderTable.getSelectedRow();
				if (row >= 0) {
					String trorderNo = tableModel.getValueAt(row, 0).toString();
					List<TrorderDetail> details = trorderDetailService.findByTrorderNo(trorderNo);
					StringBuilder sb = new StringBuilder("【訂單細節】" + trorderNo + "\n");
					sb.append("------------------------------------------\n");
					for (TrorderDetail d : details) {
						sb.append(String.format("項目：%s | 數量：%d | 單價：%d | 小計：%d\n", d.getItemName(), d.getQuantity(),
								d.getUnitPrice(), d.getAmount()));
					}
					if (details.isEmpty())
						sb.append("(此訂單無細項資料)");
					JOptionPane.showMessageDialog(CheckTrorder.this, sb.toString(), "訂單明細",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		// 新增訂單（含細項）
		JButton addButton = new JButton("新增訂單");
		addButton.setForeground(Color.BLACK);
		addButton.setBackground(new Color(250, 240, 230));
		addButton.setFont(new Font("Serif", Font.BOLD, 14));
		addButton.setBounds(268, 320, 120, 35);
		mainPanel.add(addButton);

		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (loggedInMember == null) {
					JOptionPane.showMessageDialog(null, "請先登入會員！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					String totalStr = JOptionPane.showInputDialog("請輸入訂單總金額：");
					if (totalStr == null || totalStr.isEmpty())
						return;
					int total = Integer.parseInt(totalStr);

					// 建立 Trorder
					String trorderNo = String.format("T%03d", trorderService.selectAll().size() + 1);
					Trorder trorder = new Trorder();
					trorder.setTrorderNo(trorderNo);
					trorder.setMemberNo(loggedInMember.getMemberno());
					trorder.setEmployeeNo("E001");
					trorder.setOrderDate(LocalDate.now());
					trorder.setTotalAmount(total);
					trorder.setPaidAmount(total);
					trorder.setChangeAmount(0);
					trorderService.addTrorder(trorder);

					// 建立明細
					List<TrorderDetail> detailList = new ArrayList<>();
					while (true) {
						String itemName = JOptionPane.showInputDialog("請輸入項目名稱（留空結束）：");
						if (itemName == null || itemName.trim().isEmpty())
							break;

						String qtyStr = JOptionPane.showInputDialog("請輸入數量：");
						String priceStr = JOptionPane.showInputDialog("請輸入單價：");

						int qty = Integer.parseInt(qtyStr);
						int price = Integer.parseInt(priceStr);
						int amount = qty * price;

						String detailNo = String.format("Td%03d",
								trorderDetailService.findAllTrorderDetail().size() + detailList.size() + 1);

						TrorderDetail detail = new TrorderDetail();
						detail.setTrorderdetailNo(detailNo);
						detail.setTrorderNo(trorderNo);
						detail.setItemName(itemName);
						detail.setQuantity(qty);
						detail.setUnitPrice(price);
						detail.setAmount(amount);
						trorderDetailService.addTrorderDetail(detail);
						detailList.add(detail);
					}

					loadOrderData();
					JOptionPane.showMessageDialog(null, "✅ 訂單新增成功！共 " + detailList.size() + " 筆細項已儲存。", "成功",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "新增訂單失敗：" + ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton backButton = new JButton("返回主頁");
		backButton.setForeground(Color.BLACK);
		backButton.setBackground(new Color(250, 240, 230));
		backButton.setFont(new Font("Serif", Font.BOLD, 14));
		backButton.setBounds(510, 320, 120, 35);
		mainPanel.add(backButton);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new LoginSuccess().setVisible(true);
				dispose();
			}
		});

		setLocationRelativeTo(null);
	}

	// 載入會員訂單資料
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
