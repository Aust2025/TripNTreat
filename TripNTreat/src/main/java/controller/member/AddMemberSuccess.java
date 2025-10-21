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

public class AddMemberSuccess extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMemberSuccess frame = new AddMemberSuccess();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddMemberSuccess() {
		setTitle("註冊成功頁");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setBackground(new Color(248, 245, 240));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		int paddingX = 40;
		int width = 420 - 2 * paddingX;

		JLabel mainMessage = new JLabel("註冊成功");
		mainMessage.setFont(new Font("Serif", Font.BOLD, 28));
		mainMessage.setForeground(new Color(50, 50, 50));
		mainMessage.setHorizontalAlignment(SwingConstants.CENTER);
		mainMessage.setBounds(0, 147, 420, 35);
		contentPane.add(mainMessage);

		JLabel subMessage = new JLabel("你已成功建立帳戶！");
		subMessage.setFont(new Font("Serif", Font.BOLD, 28));
		subMessage.setForeground(new Color(50, 50, 50));
		subMessage.setHorizontalAlignment(SwingConstants.CENTER);
		subMessage.setBounds(10, 194, 420, 35);
		contentPane.add(subMessage);

		JLabel subText = new JLabel("立即前往登入頁面，開始你的旅程！");
		subText.setFont(new Font("Serif", Font.PLAIN, 14));
		subText.setForeground(new Color(100, 100, 100));
		subText.setHorizontalAlignment(SwingConstants.CENTER);
		subText.setBounds(40, 371, width, 20);
		contentPane.add(subText);

		JButton returnButton = new JButton("回登入頁");
		returnButton.setFont(new Font("Serif", Font.BOLD, 18));
		returnButton.setForeground(Color.WHITE);
		returnButton.setBackground(new Color(30, 30, 30));
		returnButton.setOpaque(true);
		returnButton.setBorderPainted(false);
		returnButton.setBounds(40, 403, width, 50);
		contentPane.add(returnButton);

		returnButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});

		this.setLocationRelativeTo(null);
	}
}