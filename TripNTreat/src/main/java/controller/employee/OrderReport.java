package controller.employee;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import po.Trorder;
import po.service.impl.TrorderDetailServiceImpl;
import po.service.impl.TrorderServiceImpl;
import util.Tool;

public class OrderReport extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea reportArea;
	private TrorderServiceImpl trorderService = new TrorderServiceImpl();
	private TrorderDetailServiceImpl trorderDetailService = new TrorderDetailServiceImpl();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new OrderReport().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public OrderReport() {
		setTitle("訂單報表");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(248, 245, 240));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(64, 224, 208));
		headerPanel.setBounds(20, 8, 704, 35);
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);

		JLabel titleLabel = new JLabel("訂單報表");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(new Color(0, 0, 0));
		titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
		titleLabel.setBounds(277, 0, 160, 35);
		headerPanel.add(titleLabel);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(64, 224, 208));
		mainPanel.setBounds(20, 55, 704, 380);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		reportArea = new JTextArea();
		reportArea.setEditable(false);
		reportArea.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(reportArea);
		scrollPane.setBounds(74, 45, 565, 250);
		mainPanel.add(scrollPane);

		JButton generateButton = new JButton("生成報表");
		generateButton.setBounds(289, 307, 150, 35);
		generateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<Trorder> trorderList = trorderService.selectAll();
				new Tool().submitOrder(trorderList, reportArea);
			}
		});
		mainPanel.add(generateButton);

		JButton backButton = new JButton("返回主頁");
		backButton.setBounds(489, 307, 150, 35);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Backstage().setVisible(true);
				dispose();
			}
		});
		mainPanel.add(backButton);

		setLocationRelativeTo(null);
	}
}