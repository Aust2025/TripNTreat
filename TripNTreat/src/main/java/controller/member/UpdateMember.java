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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import po.Member;
import po.service.impl.MemberServiceImpl;

public class UpdateMember extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField passwordField;
	private JTextField emailField;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField deleteUsernameField;
	private JTextField deletePasswordField;
	private JLabel memberNameLabel;
	private JTextArea memberDataTextArea;
	private MemberServiceImpl msi;
	private Member currentMember;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UpdateMember frame = new UpdateMember();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateMember() {
		msi = new MemberServiceImpl();
		// Placeholder: Current member should be passed or retrieved
		// For now, assume a logged-in member is passed or retrieved via another
		// mechanism
		// currentMember = (Member) Tool.readFile("member.txt");
		// Instead, we need a way to get the logged-in member; here we assume it's set
		// externally
		currentMember = new Member(); // Replace with actual member retrieval logic

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 550); // Adjusted height to accommodate email field
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(19, 10, 553, 35);
		headerPanel.setBackground(new Color(250, 240, 230));
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);

		JLabel titleLabel = new JLabel("會員資料更新");
		titleLabel.setForeground(new Color(0, 0, 0));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		titleLabel.setBounds(157, -2, 226, 35);
		headerPanel.add(titleLabel);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(19, 55, 553, 448);
		mainPanel.setBackground(new Color(250, 240, 230));
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		memberNameLabel = new JLabel("會員資料");
		memberNameLabel.setBounds(38, 6, 213, 25);
		memberNameLabel.setForeground(new Color(0, 0, 0));
		memberNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		memberNameLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		mainPanel.add(memberNameLabel);

		memberDataTextArea = new JTextArea();
		memberDataTextArea.setBounds(48, 43, 203, 179);
		memberDataTextArea.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		memberDataTextArea.setEditable(false);
		mainPanel.add(memberDataTextArea);

		// Update member data display (initially empty until member is loaded)
		updateMemberDataDisplay();

		JPanel updatePanel = new JPanel();
		updatePanel.setBounds(294, 11, 237, 25);
		updatePanel.setLayout(null);
		updatePanel.setBackground(new Color(250, 119, 146));
		mainPanel.add(updatePanel);

		JLabel updateInstructionLabel = new JLabel("輸入要修改的資料");
		updateInstructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		updateInstructionLabel.setForeground(new Color(0, 0, 0));
		updateInstructionLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		updateInstructionLabel.setBounds(25, 0, 202, 25);
		updatePanel.add(updateInstructionLabel);

		JLabel nameLabel = new JLabel("姓名");
		nameLabel.setBounds(247, 43, 35, 25);
		nameLabel.setForeground(new Color(255, 245, 228));
		nameLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		mainPanel.add(nameLabel);

		nameField = new JTextField();
		nameField.setBounds(294, 43, 244, 25);
		nameField.setColumns(10);
		mainPanel.add(nameField);

		JLabel passwordLabel = new JLabel("密碼");
		passwordLabel.setBounds(247, 82, 35, 25);
		passwordLabel.setForeground(new Color(255, 245, 228));
		passwordLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		mainPanel.add(passwordLabel);

		passwordField = new JTextField();
		passwordField.setBounds(294, 82, 244, 25);
		passwordField.setColumns(10);
		mainPanel.add(passwordField);

		JLabel emailLabel = new JLabel("信箱");
		emailLabel.setBounds(247, 121, 35, 25);
		emailLabel.setForeground(new Color(255, 245, 228));
		emailLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		mainPanel.add(emailLabel);

		emailField = new JTextField();
		emailField.setBounds(294, 121, 244, 25);
		emailField.setColumns(10);
		mainPanel.add(emailField);

		JLabel addressLabel = new JLabel("地址");
		addressLabel.setBounds(247, 160, 35, 25);
		addressLabel.setForeground(new Color(255, 245, 228));
		addressLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		mainPanel.add(addressLabel);

		addressField = new JTextField();
		addressField.setBounds(294, 160, 244, 25);
		addressField.setColumns(10);
		mainPanel.add(addressField);

		JLabel phoneLabel = new JLabel("電話");
		phoneLabel.setBounds(247, 199, 35, 25);
		phoneLabel.setForeground(new Color(255, 245, 228));
		phoneLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		mainPanel.add(phoneLabel);

		phoneField = new JTextField();
		phoneField.setBounds(294, 199, 244, 25);
		phoneField.setColumns(10);
		mainPanel.add(phoneField);

		JPanel deletePanel = new JPanel();
		deletePanel.setBounds(294, 271, 237, 25);
		deletePanel.setLayout(null);
		deletePanel.setBackground(new Color(250, 119, 146));
		mainPanel.add(deletePanel);

		JLabel deleteInstructionLabel = new JLabel("刪除資料，請輸入帳密");
		deleteInstructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		deleteInstructionLabel.setForeground(new Color(0, 0, 0));
		deleteInstructionLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		deleteInstructionLabel.setBounds(6, 0, 211, 25);
		deletePanel.add(deleteInstructionLabel);

		JLabel deleteUsernameLabel = new JLabel("帳號");
		deleteUsernameLabel.setBounds(247, 306, 35, 25);
		deleteUsernameLabel.setForeground(new Color(255, 245, 228));
		deleteUsernameLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		mainPanel.add(deleteUsernameLabel);

		deleteUsernameField = new JTextField();
		deleteUsernameField.setBounds(294, 306, 244, 25);
		deleteUsernameField.setColumns(10);
		mainPanel.add(deleteUsernameField);

		JLabel deletePasswordLabel = new JLabel("密碼");
		deletePasswordLabel.setBounds(247, 345, 35, 25);
		deletePasswordLabel.setForeground(new Color(255, 245, 228));
		deletePasswordLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		mainPanel.add(deletePasswordLabel);

		deletePasswordField = new JTextField();
		deletePasswordField.setBounds(294, 345, 244, 25);
		deletePasswordField.setColumns(10);
		mainPanel.add(deletePasswordField);

		JButton backButton = new JButton("回上一頁");
		backButton.setBounds(99, 250, 104, 44);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginSuccess ls = new LoginSuccess();
				ls.setVisible(true);
				dispose();
			}
		});
		backButton.setForeground(new Color(0, 0, 0));
		backButton.setBackground(new Color(15, 84, 145));
		mainPanel.add(backButton);

		JButton confirmUpdateButton = new JButton("確認修改");
		confirmUpdateButton.setBounds(370, 236, 85, 25);
		confirmUpdateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = nameField.getText().trim();
				String password = passwordField.getText().trim();
				String email = emailField.getText().trim();
				String address = addressField.getText().trim();
				String phone = phoneField.getText().trim();

				if (name.isEmpty() || password.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty()) {
					JOptionPane.showMessageDialog(null, "所有欄位都必填！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Member member = new Member();
					member.setId(currentMember.getId());
					member.setUsername(currentMember.getUsername());
					member.setName(name);
					member.setPassword(password);
					member.setEmail(email);
					member.setAddress(address);
					member.setPhone(phone);
					member.setAdmin(currentMember.isAdmin());

					msi.updateMember(member);
					currentMember = member; // Update current member
					JOptionPane.showMessageDialog(null, "修改成功，左側資料已更新", "提示", JOptionPane.INFORMATION_MESSAGE);
					updateMemberDataDisplay();
					nameField.setText("");
					passwordField.setText("");
					emailField.setText("");
					addressField.setText("");
					phoneField.setText("");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "修改失敗：" + ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		confirmUpdateButton.setForeground(new Color(0, 0, 0));
		confirmUpdateButton.setBackground(new Color(15, 84, 145));
		mainPanel.add(confirmUpdateButton);

		JButton confirmDeleteButton = new JButton("確認刪除");
		confirmDeleteButton.setBounds(370, 374, 85, 25);
		confirmDeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String inputUsername = deleteUsernameField.getText().trim();
				String inputPassword = deletePasswordField.getText().trim();

				if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
					JOptionPane.showMessageDialog(null, "請輸入帳號和密碼！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!inputUsername.equals(currentMember.getUsername())
						|| !inputPassword.equals(currentMember.getPassword())) {
					JOptionPane.showMessageDialog(null, "帳號或密碼錯誤！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				Object[] options = { "刪除", "取消" };
				int confirm = JOptionPane.showOptionDialog(null, "確認要刪除這個會員嗎？", "刪除確認", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				if (confirm == 0) {
					try {
						msi.deleteMember(currentMember.getId());
						JOptionPane.showMessageDialog(null, "刪除成功，確認後將自動關閉視窗", "提示", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "刪除失敗：" + ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "已取消刪除", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		confirmDeleteButton.setForeground(new Color(0, 0, 0));
		confirmDeleteButton.setBackground(new Color(15, 84, 145));
		mainPanel.add(confirmDeleteButton);

		JButton logoutButton = new JButton("登出");
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login lo = new Login();
				lo.setVisible(true);
				dispose();
			}
		});
		logoutButton.setForeground(new Color(0, 0, 0));
		logoutButton.setBackground(new Color(15, 84, 145));
		logoutButton.setBounds(471, 5, 70, 25);
		headerPanel.add(logoutButton);
	}

	private void updateMemberDataDisplay() {
		if (currentMember != null) {
			String show = currentMember.getName() + "，以下是您的資料";
			memberNameLabel.setText(show);
			String show2 = "\n 姓名：" + currentMember.getName() + "\n 密碼：" + currentMember.getPassword() + "\n 信箱："
					+ currentMember.getEmail() + "\n 地址：" + currentMember.getAddress() + "\n 電話："
					+ currentMember.getPhone();
			memberDataTextArea.setText(show2);
		} else {
			memberNameLabel.setText("無會員資料");
			memberDataTextArea.setText("");
		}
	}
}