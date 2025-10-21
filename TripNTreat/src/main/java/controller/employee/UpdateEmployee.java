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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import po.Employee;
import po.service.impl.EmployeeServiceImpl;

public class UpdateEmployee extends JFrame {
	private DefaultTableModel tableModel;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtEmployeeNo;
	private JTextField txtPassword;
	private JTextField txtName;
	private JLabel lblId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateEmployee frame = new UpdateEmployee();
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
	public UpdateEmployee() {
		EmployeeServiceImpl esi = new EmployeeServiceImpl();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelHeader = new JPanel();
		panelHeader.setLayout(null);
		panelHeader.setBackground(new Color(64, 224, 208));
		panelHeader.setBounds(10, 10, 555, 35);
		contentPane.add(panelHeader);

		JLabel lblTitle = new JLabel("管理員資料系統");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(new Color(0, 0, 0));
		lblTitle.setFont(new Font("Serif", Font.BOLD, 16));
		lblTitle.setBounds(219, -2, 137, 35);
		panelHeader.add(lblTitle);

		JPanel panelForm = new JPanel();
		panelForm.setLayout(null);
		panelForm.setBackground(new Color(64, 224, 208));
		panelForm.setBounds(10, 55, 555, 357);
		contentPane.add(panelForm);

		JLabel lblName = new JLabel("姓名");
		lblName.setForeground(new Color(0, 0, 0));
		lblName.setFont(new Font("serif", Font.BOLD, 14));
		lblName.setBounds(10, 285, 35, 25);
		panelForm.add(lblName);

		JLabel lblPassword = new JLabel("密碼");
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setFont(new Font("serif", Font.BOLD, 14));
		lblPassword.setBounds(297, 285, 35, 25);
		panelForm.add(lblPassword);

		JLabel lblEmployeeNo = new JLabel("員編");
		lblEmployeeNo.setForeground(new Color(0, 0, 0));
		lblEmployeeNo.setFont(new Font("serif", Font.BOLD, 14));
		lblEmployeeNo.setBounds(298, 250, 35, 25);
		panelForm.add(lblEmployeeNo);

		txtEmployeeNo = new JTextField();
		txtEmployeeNo.setBounds(344, 250, 200, 25);
		panelForm.add(txtEmployeeNo);

		txtPassword = new JTextField();
		txtPassword.setBounds(344, 285, 200, 25);
		panelForm.add(txtPassword);

		txtName = new JTextField();
		txtName.setBounds(53, 285, 200, 25);
		panelForm.add(txtName);

		JPanel panelInstruction = new JPanel();
		panelInstruction.setLayout(null);
		panelInstruction.setBackground(new Color(250, 240, 230));
		panelInstruction.setBounds(136, 213, 295, 25);
		panelForm.add(panelInstruction);

		JLabel lblSelectInstruction = new JLabel("選取要改的資料");
		lblSelectInstruction.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectInstruction.setForeground(new Color(0, 0, 0));
		lblSelectInstruction.setFont(new Font("Serif", Font.BOLD, 14));
		lblSelectInstruction.setBounds(26, 0, 260, 25);
		panelInstruction.add(lblSelectInstruction);

		JLabel lblIdTitle = new JLabel("編號");
		lblIdTitle.setForeground(new Color(0, 0, 0));
		lblIdTitle.setFont(new Font("serif", Font.BOLD, 14));
		lblIdTitle.setBounds(10, 249, 35, 25);
		panelForm.add(lblIdTitle);

		lblId = new JLabel("");
		lblId.setForeground(new Color(0, 0, 0));
		lblId.setFont(new Font("serif", Font.BOLD, 14));
		lblId.setBounds(53, 250, 200, 25);
		panelForm.add(lblId);

		JPanel panelTableHeader = new JPanel();
		panelTableHeader.setLayout(null);
		panelTableHeader.setBackground(new Color(250, 240, 230));
		panelTableHeader.setBounds(136, 7, 286, 25);
		panelForm.add(panelTableHeader);

		JLabel lblTableTitle = new JLabel("所有員工資料");
		lblTableTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableTitle.setForeground(new Color(0, 0, 0));
		lblTableTitle.setFont(new Font("Serif", Font.BOLD, 14));
		lblTableTitle.setBounds(72, 0, 149, 25);
		panelTableHeader.add(lblTableTitle);

		String[] columnNames = { "編號", "姓名", "員編", "密碼" };
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(11, 44, 534, 120);
		panelForm.add(scrollPane);

		List<Employee> list = esi.findAllEmployee();
		for (Employee emp : list) {
			Object[] row = { emp.getId(), emp.getName(), emp.getEmployeeNo(), emp.getEmployeePass() };
			tableModel.addRow(row);
		}

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row >= 0) {
					lblId.setText(table.getValueAt(row, 0).toString());
					txtName.setText(table.getValueAt(row, 1).toString());
					txtEmployeeNo.setText(table.getValueAt(row, 2).toString());
					txtPassword.setText(table.getValueAt(row, 3).toString());
				}
			}
		});

		JButton btnUpdate = new JButton("修改資料");
		btnUpdate.setFont(new Font("Serif", Font.PLAIN, 13));
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lblId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "請先選擇一筆資料！", "提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String name = txtName.getText().trim();
				String employeeNo = txtEmployeeNo.getText().trim();
				String password = txtPassword.getText().trim();
				if (name.isEmpty() || employeeNo.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "姓名/員編/密碼不能是空白的", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Employee employee = new Employee();
				employee.setId(Integer.parseInt(lblId.getText()));
				employee.setName(name);
				employee.setEmployeeNo(employeeNo);
				employee.setEmployeePass(password);
				try {
					esi.updateEmployee(employee);
					JOptionPane.showMessageDialog(null, "修改成功，點擊確認後將更新資料", "提示", JOptionPane.INFORMATION_MESSAGE);
					tableModel.setRowCount(0);
					List<Employee> newList = esi.findAllEmployee();
					for (Employee emp : newList) {
						tableModel.addRow(new Object[] { emp.getId(), emp.getName(), emp.getEmployeeNo(),
								emp.getEmployeePass() });
					}
					lblId.setText("");
					txtName.setText("");
					txtEmployeeNo.setText("");
					txtPassword.setText("");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "修改失敗，請檢查資料", "錯誤", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUpdate.setForeground(new Color(0, 0, 0));
		btnUpdate.setBackground(new Color(175, 238, 238));
		btnUpdate.setBounds(429, 320, 115, 25);
		panelForm.add(btnUpdate);

		JButton btnBack = new JButton("回上一頁");
		btnBack.setFont(new Font("Serif", Font.PLAIN, 13));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Backstage bs = new Backstage();
				bs.setVisible(true);
				dispose();
			}
		});
		btnBack.setForeground(new Color(0, 0, 0));
		btnBack.setBackground(new Color(175, 238, 238));
		btnBack.setBounds(296, 173, 115, 25);
		panelForm.add(btnBack);

		JButton btnDelete = new JButton("刪除資料");
		btnDelete.setFont(new Font("Serif", Font.PLAIN, 13));
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lblId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "請先選擇一筆資料！", "提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int deleteId = Integer.parseInt(lblId.getText());
				String message = "確認要刪除這個管理員嗎？\n\n" + "ID： " + deleteId + "\n" + "姓名： " + txtName.getText() + "\n"
						+ "帳號：" + txtEmployeeNo.getText();
				Object[] options = { "刪除", "取消" };
				int confirm = JOptionPane.showOptionDialog(null, message, "刪除確認", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				if (confirm == 0) {
					try {
						esi.deleteEmployee(deleteId);
						JOptionPane.showMessageDialog(null, "刪除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						tableModel.setRowCount(0);
						List<Employee> newList = esi.findAllEmployee();
						for (Employee emp : newList) {
							tableModel.addRow(new Object[] { emp.getId(), emp.getName(), emp.getEmployeeNo(),
									emp.getEmployeePass() });
						}
						lblId.setText("");
						txtName.setText("");
						txtEmployeeNo.setText("");
						txtPassword.setText("");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "刪除失敗，請檢查資料", "錯誤", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "已取消刪除", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnDelete.setForeground(new Color(0, 0, 0));
		btnDelete.setBackground(new Color(175, 238, 238));
		btnDelete.setBounds(429, 173, 115, 25);
		panelForm.add(btnDelete);

		JButton btnAddEmployee = new JButton("新增管理員");
		btnAddEmployee.setFont(new Font("Serif", Font.PLAIN, 13));
		btnAddEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddEmployee ae = new AddEmployee();
				ae.setVisible(true);
				dispose();
			}
		});
		btnAddEmployee.setForeground(new Color(0, 0, 0));
		btnAddEmployee.setBackground(new Color(175, 238, 238));
		btnAddEmployee.setBounds(10, 174, 115, 25);
		panelForm.add(btnAddEmployee);

		JButton btnLogout = new JButton("登出");
		btnLogout.setFont(new Font("Serif", Font.PLAIN, 13));
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EmployeeLogin el = new EmployeeLogin();
				el.setVisible(true);
				dispose();
			}
		});
		btnLogout.setForeground(new Color(0, 0, 0));
		btnLogout.setBackground(new Color(175, 238, 238));
		btnLogout.setBounds(471, 0, 70, 35);
		panelHeader.add(btnLogout);
	}
}