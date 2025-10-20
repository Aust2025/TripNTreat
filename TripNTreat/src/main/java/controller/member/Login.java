package controller.member;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.employee.EmployeeLogin;
import po.Member;
import po.service.impl.MemberServiceImpl;
import util.Tool;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private MemberServiceImpl msi = new MemberServiceImpl();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new Login().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Login() {
		setTitle("會員登入頁");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
		contentPane.setBackground(new Color(248, 245, 240));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titleLabel = new JLabel("會員登入");
		titleLabel.setBounds(20, 28, 380, 25);
		titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(titleLabel);

		usernameField = new JTextField("帳號");
		usernameField.setBounds(53, 107, 340, 45);
		usernameField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		usernameField.setForeground(Color.GRAY);
		usernameField.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		contentPane.add(usernameField);
		usernameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (usernameField.getText().equals("帳號")) {
					usernameField.setText("");
					usernameField.setForeground(Color.BLACK);
				}
			}
		});

		passwordField = new JPasswordField("密碼");
		passwordField.setBounds(53, 164, 340, 45);
		passwordField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		passwordField.setForeground(Color.GRAY);
		passwordField.setEchoChar((char) 0);
		passwordField.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		contentPane.add(passwordField);
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (String.valueOf(passwordField.getPassword()).equals("密碼")) {
					passwordField.setText("");
					passwordField.setForeground(Color.BLACK);
					passwordField.setEchoChar('•');
				}
			}
		});

		JLabel infoLabel = new JLabel("請輸入使用者帳號及密碼");
		infoLabel.setBounds(53, 209, 340, 15);
		infoLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		infoLabel.setForeground(Color.GRAY);
		contentPane.add(infoLabel);

		JButton btnLogin = new JButton("登入");
		btnLogin.setBounds(53, 240, 340, 50);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				attemptLogin();
			}
		});
		contentPane.add(btnLogin);

		JLabel orLabel = new JLabel("or");
		orLabel.setBounds(207, 302, 20, 15);
		orLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		orLabel.setForeground(Color.GRAY);
		contentPane.add(orLabel);

		JPanel lineLeft = new JPanel();
		lineLeft.setBounds(53, 302, 142, 1);
		lineLeft.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lineLeft);

		JPanel lineRight = new JPanel();
		lineRight.setBounds(239, 302, 155, 1);
		lineRight.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lineRight);

		JLabel signUpLink = new JLabel("立即註冊");
		signUpLink.setBounds(187, 329, 106, 59);
		signUpLink.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		signUpLink.setForeground(new Color(255, 124, 8));
		signUpLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AddMember().setVisible(true);
				dispose();
			}
		});
		contentPane.add(signUpLink);

		setLocationRelativeTo(null);
	}

	private void attemptLogin() {
		String username = usernameField.getText().trim();
		String password = new String(passwordField.getPassword()).trim();
		Member member = msi.Login(username, password);
		if (member != null) {
			Tool.saveMember(member); // 保存登入會員資訊供 AddTrorder 使用
			if (Tool.isMember(member)) {
				new LoginSuccess().setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "管理員請使用管理員登入入口", "錯誤", JOptionPane.ERROR_MESSAGE);
				new EmployeeLogin().setVisible(true);
				dispose();
			}
		} else {
			new LoginError().setVisible(true);
			dispose();
		}
	}
}