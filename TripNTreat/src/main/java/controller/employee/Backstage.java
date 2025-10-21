package controller.employee;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import controller.service.AddTrip;
import controller.service.UpdateService;
import po.Employee;
import util.Tool;

public class Backstage extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new Backstage().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Backstage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 800, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelHeader = new JPanel();
		panelHeader.setBounds(20, 10, 761, 35);
		panelHeader.setLayout(null);
		panelHeader.setBackground(new Color(64, 224, 208));
		contentPane.add(panelHeader);

		JLabel lblWelcome = new JLabel("");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setForeground(new Color(255, 245, 228));
		lblWelcome.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblWelcome.setBounds(236, -1, 215, 35);

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		JLabel lblTime = new JLabel(now.format(formatter));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Serif", Font.BOLD, 14));
		lblTime.setForeground(new Color(0, 0, 0));
		lblTime.setBounds(4, 0, 157, 34);
		panelHeader.add(lblTime);
		Timer timer = new Timer(1000, e -> {
			lblTime.setText(LocalDateTime.now().format(formatter));
		});
		timer.start();

		try {
			Employee employee = (Employee) Tool.readFile("employee.txt");
			if (employee != null) {
				lblWelcome.setText(employee.getName() + " 管理員登入中");
			} else {
				lblWelcome.setText("管理員登入中");
			}
		} catch (Exception e) {
			lblWelcome.setText("管理員登入中");
		}
		panelHeader.add(lblWelcome);

		JPanel panelMain = new JPanel();
		panelMain.setBounds(20, 55, 761, 387);
		panelMain.setBackground(new Color(64, 224, 208));
		contentPane.add(panelMain);
		panelMain.setLayout(null);

		JLabel lblServiceTitle = new JLabel("請選擇要使用的服務");
		lblServiceTitle.setBounds(228, 20, 300, 25);
		lblServiceTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblServiceTitle.setForeground(new Color(0, 0, 0));
		lblServiceTitle.setFont(new Font("Serif", Font.BOLD, 14));
		panelMain.add(lblServiceTitle);

		JPanel panelTripService = new JPanel();
		panelTripService.setBounds(116, 93, 199, 25);
		panelTripService.setLayout(null);
		panelTripService.setBackground(new Color(250, 240, 230));
		panelMain.add(panelTripService);

		JLabel lblTripService = new JLabel("行程服務調整");
		lblTripService.setHorizontalAlignment(SwingConstants.CENTER);
		lblTripService.setForeground(new Color(0, 0, 0));
		lblTripService.setFont(new Font("Serif", Font.BOLD, 14));
		lblTripService.setBounds(38, 0, 121, 25);
		panelTripService.add(lblTripService);

		JPanel panelOtherService = new JPanel();
		panelOtherService.setBounds(433, 93, 198, 25);
		panelOtherService.setLayout(null);
		panelOtherService.setBackground(new Color(250, 240, 230));
		panelMain.add(panelOtherService);

		JLabel lblOtherService = new JLabel("其他");
		lblOtherService.setHorizontalAlignment(SwingConstants.CENTER);
		lblOtherService.setForeground(new Color(0, 0, 0));
		lblOtherService.setFont(new Font("Serif", Font.BOLD, 14));
		lblOtherService.setBounds(52, 0, 90, 20);
		panelOtherService.add(lblOtherService);

		JButton btnLogout = new JButton("登出");
		btnLogout.setFont(new Font("Serif", Font.PLAIN, 13));
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Tool.clearFile("employee.txt");
				} catch (Exception ex) {

				}
				EmployeeLogin el = new EmployeeLogin();
				el.setVisible(true);
				dispose();
			}
		});
		btnLogout.setForeground(new Color(0, 0, 0));
		btnLogout.setBackground(new Color(15, 84, 145));
		btnLogout.setBounds(554, 2, 121, 35);
		panelHeader.add(btnLogout);

		JButton btnTripItem = new JButton("調整行程項目");
		btnTripItem.setFont(new Font("Serif", Font.PLAIN, 13));
		btnTripItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTripItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddTrip at = new AddTrip();
				at.setVisible(true);
				dispose();
			}
		});
		btnTripItem.setForeground(new Color(0, 0, 0));
		btnTripItem.setBackground(new Color(175, 238, 238));
		btnTripItem.setBounds(71, 130, 280, 40);
		panelMain.add(btnTripItem);

		JButton btnServiceConfirm = new JButton("調整服務項目");
		btnServiceConfirm.setFont(new Font("Serif", Font.PLAIN, 13));
		btnServiceConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateService up = new UpdateService();
				up.setVisible(true);
				dispose();
			}
		});
		btnServiceConfirm.setForeground(new Color(0, 0, 0));
		btnServiceConfirm.setBackground(new Color(175, 238, 238));
		btnServiceConfirm.setBounds(71, 182, 280, 40);
		panelMain.add(btnServiceConfirm);

		JButton btnEmployeeManage = new JButton("調整管理員");
		btnEmployeeManage.setFont(new Font("Serif", Font.PLAIN, 13));
		btnEmployeeManage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateEmployee ue = new UpdateEmployee();
				ue.setVisible(true);
				dispose();
			}
		});
		btnEmployeeManage.setForeground(new Color(0, 0, 0));
		btnEmployeeManage.setBackground(new Color(175, 238, 238));
		btnEmployeeManage.setBounds(389, 130, 280, 40);
		panelMain.add(btnEmployeeManage);

		JButton btnOrderConfirm = new JButton("確認顧客訂單");
		btnOrderConfirm.setFont(new Font("Serif", Font.PLAIN, 13));
		btnOrderConfirm.setForeground(new Color(0, 0, 0));
		btnOrderConfirm.setBackground(new Color(175, 238, 238));
		btnOrderConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ConfirmOrder().setVisible(true);
				dispose();
			}
		});
		btnOrderConfirm.setBounds(389, 182, 280, 40);
		panelMain.add(btnOrderConfirm);

		JButton btnReportGenerate = new JButton("建立訂單報表");
		btnReportGenerate.setFont(new Font("Serif", Font.PLAIN, 13));
		btnReportGenerate.setForeground(new Color(0, 0, 0));
		btnReportGenerate.setBackground(new Color(175, 238, 238));
		btnReportGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new OrderReport().setVisible(true);
				dispose();
			}
		});
		btnReportGenerate.setBounds(389, 234, 280, 40);
		panelMain.add(btnReportGenerate);

		setLocationRelativeTo(null);
	}
}