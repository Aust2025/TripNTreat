package controller.service;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import po.Service;
import po.service.impl.ServiceServiceImpl;

public class AddService extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField serviceNo;
	private JTextField name;
	private JTextField price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddService frame = new AddService();
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
	public AddService() {
		ServiceServiceImpl ssi = new ServiceServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 608, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(20, 6, 568, 39);
		panel.setLayout(null);
		panel.setBackground(new Color(64, 224, 208));
		contentPane.add(panel);

		JLabel lblNewLabel = new JLabel("新增加購服務項目");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel.setBounds(155, 0, 295, 35);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 55, 568, 249);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(64, 224, 208));
		contentPane.add(panel_1);

		JLabel lblNewLabel_2 = new JLabel("請填寫下表");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_2.setBounds(137, 26, 301, 25);
		panel_1.add(lblNewLabel_2);

		JLabel lblServiceNo = new JLabel("服務編號");
		lblServiceNo.setForeground(new Color(0, 0, 0));
		lblServiceNo.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblServiceNo.setBounds(161, 60, 60, 25);
		panel_1.add(lblServiceNo);

		serviceNo = new JTextField();
		serviceNo.setColumns(10);
		serviceNo.setBounds(227, 60, 185, 25);
		panel_1.add(serviceNo);

		JLabel lblNewLabel_1 = new JLabel("加購名稱");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1.setBounds(161, 104, 60, 25);
		panel_1.add(lblNewLabel_1);

		name = new JTextField();
		name.setColumns(10);
		name.setBounds(227, 104, 185, 25);
		panel_1.add(name);

		JLabel lblNewLabel_1_1 = new JLabel("價格");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(161, 146, 35, 25);
		panel_1.add(lblNewLabel_1_1);

		price = new JTextField();
		price.setColumns(10);
		price.setBounds(227, 146, 185, 25);
		panel_1.add(price);

		JButton btnNewButton_1 = new JButton("回上一頁");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateService up = new UpdateService();
				up.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBackground(new Color(15, 84, 145));
		btnNewButton_1.setBounds(227, 202, 85, 25);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton = new JButton("確認新增");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String serviceNoStr = serviceNo.getText().trim();
				String nameStr = name.getText().trim();
				String priceStr = price.getText().trim();

				if (serviceNoStr.isEmpty() || nameStr.isEmpty() || priceStr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "所有欄位均需填寫！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int priceInt;
				try {
					priceInt = Integer.parseInt(priceStr);
					if (priceInt <= 0) {
						JOptionPane.showMessageDialog(null, "價格必須大於0！", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "價格必須為數字！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Service service = new Service();
					service.setServiceNo(serviceNoStr);
					service.setServiceName(nameStr);
					service.setPrice(priceInt);

					ssi.addService(service);
					JOptionPane.showMessageDialog(null, "新增成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					UpdateService up = new UpdateService();
					up.setVisible(true);
					dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "新增失敗：" + ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(15, 84, 145));
		btnNewButton.setBounds(329, 202, 85, 25);
		panel_1.add(btnNewButton);
	}
}