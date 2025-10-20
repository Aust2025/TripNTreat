package controller.trorder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import controller.member.LoginSuccess;
import po.Member;
import po.Trorder;
import po.service.impl.TrorderServiceImpl;
import util.Tool;

public class DeleteTrorder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable orderTable;
	private DefaultTableModel tableModel;
	// private Member loggedInMember = Tool.readMember();
	private Member loggedInMember = Tool.loadMember();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				DeleteTrorder frame = new DeleteTrorder();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DeleteTrorder() {
		TrorderServiceImpl trorderService = new TrorderServiceImpl();
		setTitle("刪除訂單");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(248, 245, 240));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 標題面板
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(250, 119, 146));
		headerPanel.setBounds(20, 10, 563, 35);
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);

		JLabel titleLabel = new JLabel("訂單刪除");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(new Color(0, 0, 0));
		titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
		titleLabel.setBounds(200, 0, 160, 35);
		headerPanel.add(titleLabel);

		// 主面板
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(250, 119, 146));
		mainPanel.setBounds(20, 55, 563, 380);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		// 歡迎訊息
		JLabel welcomeLabel = new JLabel("選要刪除的訂單，" + (loggedInMember != null ? loggedInMember.getName() : "使用者"));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(new Color(0, 0, 0));
		welcomeLabel.setFont(new Font("Serif", Font.BOLD, 16));
		welcomeLabel.setBounds(17, 6, 540, 25);
		mainPanel.add(welcomeLabel);

		// 訂單表格
		String[] columnNames = { "訂單編號", "訂單日期", "總金額", "狀態" };
		tableModel = new DefaultTableModel(columnNames, 0);
		orderTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(orderTable);
		scrollPane.setBounds(35, 45, 488, 250);
		mainPanel.add(scrollPane);

		// 載入訂單資料
		loadOrderData(trorderService);

		// 刪除按鈕
		JButton deleteButton = new JButton("刪除選中訂單");
		deleteButton.setForeground(new Color(0, 0, 0));
		deleteButton.setBackground(new Color(15, 84, 145));
		deleteButton.setFont(new Font("Serif", Font.BOLD, 14));
		deleteButton.setBounds(202, 307, 150, 35);
		mainPanel.add(deleteButton);
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = orderTable.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(DeleteTrorder.this, "請選擇一筆訂單！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int orderId = Integer.parseInt(tableModel.getValueAt(row, 0).toString()); // 假設第一欄為 ID
				int confirm = JOptionPane.showConfirmDialog(DeleteTrorder.this, "確認刪除訂單？此操作不可復原。", "刪除確認",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					trorderService.deleteTrorder(orderId); // 整合 TrorderDaoImpl.delete 和 TrorderDetailDaoImpl.delete
					JOptionPane.showMessageDialog(DeleteTrorder.this, "刪除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					loadOrderData(trorderService); // 重新載入
				}
			}
		});

		// 返回按鈕
		JButton backButton = new JButton("返回主頁");
		backButton.setForeground(new Color(0, 0, 0));
		backButton.setBackground(new Color(15, 84, 145));
		backButton.setFont(new Font("Serif", Font.BOLD, 14));
		backButton.setBounds(415, 307, 120, 35);
		mainPanel.add(backButton);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginSuccess ls = new LoginSuccess();
				ls.setVisible(true);
				dispose();
			}
		});

		setLocationRelativeTo(null);
	}

	private void loadOrderData(TrorderServiceImpl trorderService) {
		if (loggedInMember != null) {
			List<Trorder> orders = trorderService.findAllTrorderByMemberId(loggedInMember.toString());
			tableModel.setRowCount(0);
			for (Trorder order : orders) {
				tableModel.addRow(new Object[] { order.getTrorderId(), // 使用 ID 作為第一欄
						order.getOrderDate(), order.getTotalAmount(), "已完成" });
			}
		}
	}
}