package controller.member;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.employee.Backstage;
import controller.trorder.AddTrorder;
import controller.trorder.CheckTrorder;
import controller.trorder.DeleteTrorder;
import po.Member;
import util.Tool;

public class LoginSuccess extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginSuccess frame = new LoginSuccess();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginSuccess() {
		Member loggedInMember = Tool.readMember(); // 讀取登入狀態
		setTitle("登入成功頁");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 0, 0);
		headerPanel.setLayout(null);
		headerPanel.setBackground(new Color(33, 52, 72));
		contentPane.add(headerPanel);

		JLabel serviceSelectionLabel = new JLabel("請選擇要使用的服務");
		serviceSelectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		serviceSelectionLabel.setForeground(new Color(255, 245, 228));
		serviceSelectionLabel.setFont(new Font("Serif", Font.BOLD, 14));
		serviceSelectionLabel.setBounds(0, 4, 300, 25);
		headerPanel.add(serviceSelectionLabel);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(19, 5, 655, 351);
		mainPanel.setBackground(new Color(250, 119, 146));
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		JPanel orderServiceHeaderPanel = new JPanel();
		orderServiceHeaderPanel.setBounds(273, 24, 133, 25);
		orderServiceHeaderPanel.setBackground(new Color(250, 240, 230));
		mainPanel.add(orderServiceHeaderPanel);
		orderServiceHeaderPanel.setLayout(null);

		JLabel orderServiceLabel = new JLabel("訂購行程服務");
		orderServiceLabel.setForeground(new Color(0, 0, 0));
		orderServiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderServiceLabel.setFont(new Font("Serif", Font.BOLD, 14));
		orderServiceLabel.setBounds(21, 0, 85, 25);
		orderServiceHeaderPanel.add(orderServiceLabel);

		JPanel memberManagementHeaderPanel = new JPanel();
		memberManagementHeaderPanel.setBounds(273, 217, 133, 31);
		memberManagementHeaderPanel.setLayout(null);
		memberManagementHeaderPanel.setBackground(new Color(250, 240, 230));
		mainPanel.add(memberManagementHeaderPanel);

		JLabel memberManagementLabel = new JLabel("管理會員資料");
		memberManagementLabel.setHorizontalAlignment(SwingConstants.CENTER);
		memberManagementLabel.setFont(new Font("Serif", Font.BOLD, 14));
		memberManagementLabel.setForeground(new Color(0, 0, 0));
		memberManagementLabel.setBounds(25, 6, 90, 20);
		memberManagementHeaderPanel.add(memberManagementLabel);

		JButton logoutButton = new JButton("登出");
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		logoutButton.setForeground(new Color(255, 245, 228));
		logoutButton.setBackground(new Color(15, 84, 145));
		logoutButton.setBounds(225, 6, 70, 25);
		headerPanel.add(logoutButton);

		JButton orderTripButton = new JButton("TripNTreat旅遊行程訂購網");
		orderTripButton.setBounds(202, 61, 280, 40);
		orderTripButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AddTrorder().setVisible(true);
				dispose();
			}
		});
		mainPanel.add(orderTripButton);

		JButton checkOrderButton = new JButton("查詢訂單");
		checkOrderButton.setBounds(202, 113, 280, 40);
		checkOrderButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new CheckTrorder().setVisible(true);
				dispose();
			}
		});
		mainPanel.add(checkOrderButton);

		JButton deleteOrderButton = new JButton("刪除訂單");
		deleteOrderButton.setBounds(202, 165, 280, 40);
		deleteOrderButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new DeleteTrorder().setVisible(true);
				dispose();
			}
		});
		mainPanel.add(deleteOrderButton);

		JButton updateMemberButton = new JButton("查詢/修改/刪除資料");
		updateMemberButton.setBounds(202, 260, 280, 40);
		updateMemberButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new UpdateMember().setVisible(true);
				dispose();
			}
		});
		mainPanel.add(updateMemberButton);

		if (loggedInMember != null && loggedInMember.isAdmin()) {
			JButton adminButton = new JButton("進入後台管理頁面");
			adminButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Backstage bs = new Backstage();
					bs.setVisible(true);
					dispose();
				}
			});
			adminButton.setForeground(new Color(255, 245, 228));
			adminButton.setBackground(new Color(15, 84, 145));
			adminButton.setFont(new Font("Serif", Font.PLAIN, 14));
			adminButton.setBounds(10, 189, 280, 40);
			mainPanel.add(adminButton);

		}
		setLocationRelativeTo(null);
	}
}