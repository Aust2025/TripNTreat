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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.WelcomePage;
import po.Employee;
import po.service.impl.EmployeeServiceImpl;
import util.Tool;

public class EmployeeLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmployeeNo;
	private JPasswordField txtEmployeePass;
	private EmployeeServiceImpl esi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLogin frame = new EmployeeLogin();
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
	public EmployeeLogin() {
		esi = new EmployeeServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 828, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelHeader = new JPanel();
		panelHeader.setBounds(25, 3, 776, 48);
		panelHeader.setLayout(null);
		panelHeader.setBackground(new Color(64, 224, 208));
		contentPane.add(panelHeader);

		JLabel lblTitle = new JLabel("後台管理系統");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("Serif", Font.PLAIN, 16));
		lblTitle.setBounds(226, 5, 300, 35);
		panelHeader.add(lblTitle);

		JLabel lblEmployeeInfo = new JLabel("員工資訊：未登入");
		lblEmployeeInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeeInfo.setForeground(Color.BLACK);
		lblEmployeeInfo.setFont(new Font("Serif", Font.PLAIN, 14));
		lblEmployeeInfo.setBounds(520, 6, 250, 35);
		panelHeader.add(lblEmployeeInfo);

		JPanel panelMain = new JPanel();
		panelMain.setBounds(25, 67, 776, 361);
		panelMain.setBackground(new Color(64, 224, 208));
		contentPane.add(panelMain);
		panelMain.setLayout(null);

		JLabel lblEmployeeNo = new JLabel("員工編號");
		lblEmployeeNo.setBounds(269, 81, 65, 25);
		lblEmployeeNo.setForeground(Color.BLACK);
		lblEmployeeNo.setFont(new Font("Serif", Font.PLAIN, 15));
		panelMain.add(lblEmployeeNo);

		txtEmployeeNo = new JTextField();
		txtEmployeeNo.setBounds(331, 81, 177, 25);
		panelMain.add(txtEmployeeNo);
		txtEmployeeNo.setColumns(10);

		JLabel lblEmployeePass = new JLabel("員工密碼");
		lblEmployeePass.setBounds(269, 137, 65, 25);
		lblEmployeePass.setForeground(Color.BLACK);
		lblEmployeePass.setFont(new Font("Serif", Font.PLAIN, 15));
		panelMain.add(lblEmployeePass);

		txtEmployeePass = new JPasswordField();
		txtEmployeePass.setBounds(331, 135, 177, 25);
		panelMain.add(txtEmployeePass);

		JButton btnLogin = new JButton("登入");
		btnLogin.setFont(new Font("Serif", Font.PLAIN, 15));
		btnLogin.setBounds(286, 217, 98, 64);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(new Color(175, 238, 238));
		panelMain.add(btnLogin);

		JButton btnBack = new JButton("返回");
		btnBack.setFont(new Font("Serif", Font.PLAIN, 15));
		btnBack.setBounds(410, 217, 98, 64);
		btnBack.setForeground(Color.BLACK);
		btnBack.setBackground(new Color(175, 238, 238));
		panelMain.add(btnBack);

		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String empNo = txtEmployeeNo.getText().trim();
				String empPass = new String(txtEmployeePass.getPassword()).trim();

				if (empNo.isEmpty() || empPass.isEmpty()) {
					JOptionPane.showMessageDialog(null, "請輸入員工編號與密碼", "提示", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Employee employee = esi.employeeLogin(empNo, empPass);

				if (employee != null) {

					lblEmployeeInfo.setText("員工編號：" + employee.getEmployeeNo() + "  員工姓名：" + employee.getName());
					Tool.saveFile(employee, "employee.txt");

					JOptionPane.showMessageDialog(null, "登入成功！", "成功", JOptionPane.INFORMATION_MESSAGE);

					new Backstage().setVisible(true);
					dispose();
				} else {

					JOptionPane.showMessageDialog(null, "員工帳號或密碼錯誤，請重新輸入", "錯誤", JOptionPane.ERROR_MESSAGE);
					txtEmployeeNo.setText("");
					txtEmployeePass.setText("");
				}
			}
		});

		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new WelcomePage().setVisible(true);
				dispose();
			}
		});
	}

	private Employee authenticateEmployee(String employeeNo, String employeePass) {
		try {
			List<Employee> employees = esi.findAllEmployee();
			for (Employee emp : employees) {
				if (emp.getEmployeeNo().equals(employeeNo) && emp.getEmployeePass().equals(employeePass)) {
					return emp;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
