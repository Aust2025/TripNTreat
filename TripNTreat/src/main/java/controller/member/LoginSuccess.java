package controller.member;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
		EventQueue.invokeLater(() -> {
			try {
				LoginSuccess frame = new LoginSuccess();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public LoginSuccess() {
		Member loggedInMember = Tool.loadMember();

		setTitle("登入成功頁");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 402);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(19, 50, 655, 300);
		mainPanel.setBackground(new Color(250, 119, 146));
		mainPanel.setLayout(null);
		contentPane.add(mainPanel);

		JButton orderTripButton = new JButton("TripNTreat旅遊行程訂購網");
		orderTripButton.setFont(new Font("Serif", Font.PLAIN, 13));
		orderTripButton.setBounds(202, 25, 280, 40);
		orderTripButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AddTrorder().setVisible(true);
				dispose();
			}
		});
		mainPanel.add(orderTripButton);

		JButton checkOrderButton = new JButton("查詢訂單");
		checkOrderButton.setFont(new Font("Serif", Font.PLAIN, 13));
		checkOrderButton.setBounds(204, 88, 280, 40);
		checkOrderButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new CheckTrorder().setVisible(true);
				dispose();
			}
		});
		mainPanel.add(checkOrderButton);

		JButton deleteOrderButton = new JButton("刪除訂單");
		deleteOrderButton.setFont(new Font("Serif", Font.PLAIN, 13));
		deleteOrderButton.setBounds(204, 143, 280, 40);
		deleteOrderButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new DeleteTrorder().setVisible(true);
				dispose();
			}
		});
		mainPanel.add(deleteOrderButton);

		JButton updateMemberButton = new JButton("查詢/修改/刪除資料");
		updateMemberButton.setFont(new Font("Serif", Font.PLAIN, 13));
		updateMemberButton.setBounds(202, 200, 280, 40);
		updateMemberButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new UpdateMember().setVisible(true);
				dispose();
			}
		});
		mainPanel.add(updateMemberButton);

		JLabel serviceSelectionLabel = new JLabel("訂購行程服務總覽");
		serviceSelectionLabel.setBounds(212, 13, 300, 25);
		contentPane.add(serviceSelectionLabel);
		serviceSelectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		serviceSelectionLabel.setBackground(new Color(250, 240, 230));
		serviceSelectionLabel.setForeground(new Color(0, 0, 0));
		serviceSelectionLabel.setFont(new Font("Serif", Font.BOLD, 16));

		JButton logoutButton = new JButton("登出");
		logoutButton.setFont(new Font("Serif", Font.PLAIN, 13));
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		logoutButton.setBounds(560, 13, 80, 26);
		contentPane.add(logoutButton);
		logoutButton.setForeground(new Color(0, 0, 0));
		logoutButton.setBackground(new Color(250, 240, 230));
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});

		if (loggedInMember != null && loggedInMember.isAdmin()) {
			JButton adminButton = new JButton("進入後台管理頁面");
			adminButton.setForeground(new Color(255, 245, 228));
			adminButton.setBackground(new Color(15, 84, 145));
			adminButton.setFont(new Font("Serif", Font.PLAIN, 14));
			adminButton.setBounds(10, 250, 280, 40);
			adminButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					new Backstage().setVisible(true);
					dispose();
				}
			});
			mainPanel.add(adminButton);
		}

		setLocationRelativeTo(null);
	}
}
