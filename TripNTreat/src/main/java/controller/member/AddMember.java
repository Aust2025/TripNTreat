package controller.member;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import po.Member;
import po.service.impl.MemberServiceImpl;
import util.DbConnection;

public class AddMember extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField username;
	private JTextField password;
	private JTextField email;
	private JTextField phone;
	private JTextField address;
	private MemberServiceImpl msi = new MemberServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				AddMember frame = new AddMember();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public AddMember() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(248, 245, 240));
		panel.setBounds(0, -13, 533, 535);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("會員註冊");
		lblNewLabel.setBounds(202, 6, 148, 44);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 30));
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("姓名");
		lblNewLabel_1.setBounds(156, 76, 46, 27);
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 16));
		panel.add(lblNewLabel_1);
		name = new JTextField();
		name.setBounds(202, 76, 124, 21);
		panel.add(name);
		name.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("帳號");
		lblNewLabel_1_1.setBounds(156, 121, 46, 27);
		lblNewLabel_1_1.setFont(new Font("Serif", Font.BOLD, 16));
		panel.add(lblNewLabel_1_1);
		username = new JTextField();
		username.setBounds(202, 121, 124, 21);
		panel.add(username);
		username.setColumns(10);

		JLabel lblNewLabel_1_2 = new JLabel("密碼");
		lblNewLabel_1_2.setBounds(156, 166, 46, 27);
		lblNewLabel_1_2.setFont(new Font("Serif", Font.BOLD, 16));
		panel.add(lblNewLabel_1_2);
		password = new JTextField();
		password.setBounds(202, 166, 124, 21);
		panel.add(password);
		password.setColumns(10);

		JLabel lblNewLabel_1_5 = new JLabel("信箱");
		lblNewLabel_1_5.setBounds(156, 215, 46, 27);
		lblNewLabel_1_5.setFont(new Font("Serif", Font.BOLD, 16));
		panel.add(lblNewLabel_1_5);
		email = new JTextField();
		email.setBounds(202, 215, 124, 21);
		panel.add(email);
		email.setColumns(10);

		JLabel lblNewLabel_1_4 = new JLabel("電話");
		lblNewLabel_1_4.setBounds(156, 256, 46, 27);
		lblNewLabel_1_4.setFont(new Font("Serif", Font.BOLD, 16));
		panel.add(lblNewLabel_1_4);
		phone = new JTextField();
		phone.setBounds(202, 256, 124, 21);
		panel.add(phone);
		phone.setColumns(10);

		JLabel lblNewLabel_1_3 = new JLabel("地址");
		lblNewLabel_1_3.setBounds(156, 305, 46, 27);
		lblNewLabel_1_3.setFont(new Font("Serif", Font.BOLD, 16));
		panel.add(lblNewLabel_1_3);
		address = new JTextField();
		address.setBounds(202, 305, 124, 21);
		panel.add(address);
		address.setColumns(10);

		JButton btnNewButton = new JButton("立即註冊");
		btnNewButton.setForeground(new Color(242, 83, 40));
		btnNewButton.setFont(new Font("Serif", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				attemptRegister();
			}
		});
		btnNewButton.setBounds(179, 401, 164, 72);
		panel.add(btnNewButton);
	}

	private void attemptRegister() { // 取得所有欄位的值
		String Name = name.getText().trim();
		String Username = username.getText().trim();
		String Password = password.getText().trim();
		String Email = email.getText().trim();
		String Phone = phone.getText().trim();
		String Address = address.getText().trim();

		// 欄位驗證
		if (Name.isEmpty() || Username.isEmpty() || Password.isEmpty() || Email.isEmpty() || Phone.isEmpty()
				|| Address.isEmpty()) {
			JOptionPane.showMessageDialog(this, "所有欄位都必填！", "註冊失敗", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Generate memberNo
		String memberNo = generateMemberNo();

		Member member = new Member();
		member.setMemberno(memberNo);
		member.setName(Name);
		member.setUsername(Username);
		member.setPassword(Password);
		member.setEmail(Email);
		member.setPhone(Phone);
		member.setAddress(Address);

		int result = msi.addMember(member);
		// 帳號重複 -> 導航到 AddMemberError
		if (result == 1) {
			new AddMemberError().setVisible(true);
			JOptionPane.showMessageDialog(this, "帳號重複，請換其他帳號。", "註冊失敗", JOptionPane.WARNING_MESSAGE);
			dispose();

		}
		// 註冊成功 -> 導航到 AddMemberSuccess
		else if (result == 0) {
			new AddMemberSuccess().setVisible(true);
			JOptionPane.showMessageDialog(this, "恭喜你！會員註冊成功。", "註冊成功", JOptionPane.INFORMATION_MESSAGE);
			dispose();

		} else {
			JOptionPane.showMessageDialog(this, "系統錯誤，註冊失敗，請稍後重試。", "系統錯誤", JOptionPane.ERROR_MESSAGE);
		}
	}

	private String generateMemberNo() {
		String memberNo = "M001";
		try (Connection conn = DbConnection.getDb();
				PreparedStatement ps = conn
						.prepareStatement("SELECT MAX(CAST(SUBSTRING(memberno, 2) AS UNSIGNED)) AS max_no FROM member");
				ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				int maxNo = rs.getInt("max_no");
				memberNo = String.format("M%03d", maxNo + 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberNo;
	}
}