package controller.employee;

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

import po.Employee;
import po.service.impl.EmployeeServiceImpl;
import util.Tool;

public class AddEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmployeeNo;
	private JTextField txtPassword;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployee frame = new AddEmployee();
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
	public AddEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 713, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelHeader = new JPanel();
		panelHeader.setBounds(28, 6, 665, 35);
		panelHeader.setLayout(null);
		panelHeader.setBackground(new Color(64, 224, 208));
		contentPane.add(panelHeader);

		JLabel lblTitle = new JLabel("新增管理員");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(new Color(0, 0, 0));
		lblTitle.setFont(new Font("Serif", Font.BOLD, 16));
		lblTitle.setBounds(196, 0, 295, 35);
		panelHeader.add(lblTitle);

		JPanel panelForm = new JPanel();
		panelForm.setBounds(28, 53, 665, 237);
		panelForm.setBackground(new Color(64, 224, 208));
		contentPane.add(panelForm);
		panelForm.setLayout(null);

		JLabel lblEmployeeNo = new JLabel("員編");
		lblEmployeeNo.setBounds(210, 84, 35, 25);
		lblEmployeeNo.setForeground(new Color(0, 0, 0));
		lblEmployeeNo.setFont(new Font("Serif", Font.BOLD, 14));
		panelForm.add(lblEmployeeNo);

		txtEmployeeNo = new JTextField();
		txtEmployeeNo.setBounds(256, 84, 205, 25);
		txtEmployeeNo.setColumns(10);
		panelForm.add(txtEmployeeNo);

		JLabel lblPassword = new JLabel("密碼");
		lblPassword.setBounds(210, 124, 35, 25);
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setFont(new Font("Serif", Font.BOLD, 14));
		panelForm.add(lblPassword);

		txtPassword = new JTextField();
		txtPassword.setBounds(256, 124, 205, 25);
		txtPassword.setColumns(10);
		panelForm.add(txtPassword);

		JLabel lblInstruction = new JLabel("請填寫以下資料");
		lblInstruction.setBounds(192, 10, 301, 25);
		lblInstruction.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstruction.setForeground(new Color(0, 0, 0));
		lblInstruction.setFont(new Font("Serif", Font.BOLD, 14));
		panelForm.add(lblInstruction);

		JLabel lblName = new JLabel("姓名");
		lblName.setBounds(210, 47, 35, 25);
		lblName.setForeground(new Color(0, 0, 0));
		lblName.setFont(new Font("Serif", Font.BOLD, 14));
		panelForm.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(256, 47, 205, 25);
		txtName.setColumns(10);
		panelForm.add(txtName);

		JButton btnConfirm = new JButton("確認新增");
		btnConfirm.setFont(new Font("Serif", Font.PLAIN, 13));
		btnConfirm.setBounds(421, 172, 85, 25);
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = txtName.getText().trim();
				String employeeNo = txtEmployeeNo.getText().trim();
				String password = txtPassword.getText().trim();

				if (name.isEmpty() || employeeNo.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "姓名/員編/密碼不能是空白的", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				Employee employee = new Employee(employeeNo, name, password);
				try {
					new EmployeeServiceImpl().addEmployee(employee);
					JOptionPane.showMessageDialog(null, "新增成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					Tool.saveFile(employee, "employee.txt");
					UpdateEmployee ue = new UpdateEmployee();
					ue.setVisible(true);
					dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "帳號已存在請嘗試其他字串", "錯誤", JOptionPane.ERROR_MESSAGE);
					txtEmployeeNo.setText("");
				}
			}
		});
		btnConfirm.setForeground(new Color(0, 0, 0));
		btnConfirm.setBackground(new Color(15, 84, 145));
		panelForm.add(btnConfirm);

		JButton btnBack = new JButton("回上一頁");
		btnBack.setFont(new Font("Serif", Font.PLAIN, 13));
		btnBack.setBounds(187, 172, 85, 25);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateEmployee ue = new UpdateEmployee();
				ue.setVisible(true);
				dispose();
			}
		});
		btnBack.setForeground(new Color(0, 0, 0));
		btnBack.setBackground(new Color(15, 84, 145));
		panelForm.add(btnBack);
	}
}