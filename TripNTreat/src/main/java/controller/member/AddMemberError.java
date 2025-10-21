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

public class AddMemberError extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMemberError frame = new AddMemberError();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddMemberError() {
		setTitle("註冊失敗頁");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setBackground(new Color(248, 245, 240));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		int paddingX = 40;
		int width = 420 - 2 * paddingX;

		JLabel mainMessage = new JLabel("註冊失敗");
		mainMessage.setFont(new Font("Serif", Font.BOLD, 28));
		mainMessage.setForeground(new Color(50, 50, 50));
		mainMessage.setHorizontalAlignment(SwingConstants.CENTER);
		mainMessage.setBounds(0, 111, 420, 35);
		contentPane.add(mainMessage);

		JLabel subMessage = new JLabel("該帳號已被註冊！");
		subMessage.setFont(new Font("Serif", Font.BOLD, 28));
		subMessage.setForeground(new Color(255, 69, 0));
		subMessage.setHorizontalAlignment(SwingConstants.CENTER);
		subMessage.setBounds(0, 158, 420, 35);
		contentPane.add(subMessage);

		JLabel subText = new JLabel("該帳號已有其他使用者，請重新註冊");
		subText.setFont(new Font("Serif", Font.PLAIN, 14));
		subText.setForeground(new Color(100, 100, 100));
		subText.setHorizontalAlignment(SwingConstants.CENTER);
		subText.setBounds(40, 358, width, 20);
		contentPane.add(subText);

		JButton returnButton = new JButton("重新註冊");
		returnButton.setFont(new Font("Serif", Font.BOLD, 18));
		returnButton.setForeground(Color.WHITE);
		returnButton.setBackground(new Color(30, 30, 30));
		returnButton.setOpaque(true);
		returnButton.setBorderPainted(false);
		returnButton.setBounds(40, 390, width, 50);
		contentPane.add(returnButton);

		returnButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddMember addmember = new AddMember();
				addmember.setVisible(true);
				dispose();
			}
		});

		this.setLocationRelativeTo(null);
	}
}