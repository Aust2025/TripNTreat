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
import javax.swing.border.LineBorder;

public class LoginError extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				LoginError frame = new LoginError();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	// 頁面標題
	public LoginError() {
		setTitle("登入錯誤");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 550);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 登入失敗標題
		JLabel errorTitle = new JLabel("登入失敗");
		errorTitle.setBounds(134, 100, 157, 64);
		contentPane.add(errorTitle);
		errorTitle.setFont(new Font("Serif", Font.BOLD, 35));

		this.setLocationRelativeTo(null);

		// 錯誤訊息框
		JPanel errorPanel = new JPanel();
		errorPanel.setBackground(new Color(245, 245, 245));
		errorPanel.setBounds(53, 176, 310, 80);
		errorPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		contentPane.add(errorPanel);
		errorPanel.setLayout(null);

		// 警告圖標
		JLabel warningIcon = new JLabel("!");
		warningIcon.setFont(new Font("Serif", Font.BOLD, 24));
		warningIcon.setForeground(new Color(255, 69, 0)); // 橘紅色
		warningIcon.setBounds(89, 25, 20, 30);
		errorPanel.add(warningIcon);

		// 錯誤訊息
		JLabel errorMessage = new JLabel("無效的帳號/密碼");
		errorMessage.setFont(new Font("Serif", Font.PLAIN, 12));
		errorMessage.setBounds(121, 34, 115, 20);
		errorPanel.add(errorMessage);

		// 註冊按鈕
		JButton registerButton = new JButton("現在註冊");
		registerButton.setFont(new Font("Serif", Font.BOLD, 18));
		registerButton.setForeground(Color.WHITE);
		registerButton.setBackground(new Color(230, 50, 80));
		registerButton.setOpaque(true);
		registerButton.setBorderPainted(false);
		registerButton.setBounds(30, 315, 360, 50);
		contentPane.add(registerButton);

		// 點擊註冊 -> 導航到 AddMember
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddMember addmember = new AddMember();
				addmember.setVisible(true);
				dispose();
			}
		});

		// --- 使用其他帳號登入 (返回 Login) ---
		JLabel returnToLoginLink = new JLabel("使用其他帳號登入", SwingConstants.CENTER);
		returnToLoginLink.setFont(new Font("Serif", Font.BOLD, 14));
		returnToLoginLink.setBounds(30, 400, 360, 20);
		returnToLoginLink.setForeground(new Color(50, 50, 50));
		returnToLoginLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		contentPane.add(returnToLoginLink);

		this.setLocationRelativeTo(null);
	}
}