package controller.service;

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

import controller.employee.Backstage;
import po.Service;
import po.service.impl.ServiceServiceImpl;

public class UpdateService extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField serviceNoField;
	private JTextField nameField;
	private JTextField priceField;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel idLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UpdateService frame = new UpdateService();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public UpdateService() {
		ServiceServiceImpl ssi = new ServiceServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 500, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(64, 224, 208));
		panel.setBounds(20, 6, 460, 35);
		contentPane.add(panel);

		JLabel lblNewLabel = new JLabel("服務項目管理");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel.setBounds(6, 0, 448, 35);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(64, 240, 208));
		panel_1.setBounds(20, 57, 460, 443);
		contentPane.add(panel_1);

		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setBackground(new Color(250, 240, 230));
		panel_2_1_1.setBounds(13, 10, 431, 25);
		panel_1.add(panel_2_1_1);

		JLabel lblNewLabel_1_1_3 = new JLabel("所有服務項目資料");
		lblNewLabel_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1_3.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_1_1_3.setBounds(150, 0, 134, 25);
		panel_2_1_1.add(lblNewLabel_1_1_3);

		String[] columnNames = { "編號", "服務編號", "名稱", "價格" };
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(13, 45, 431, 120);
		panel_1.add(scrollPane);

		List<Service> list = ssi.findAllService();
		if (list != null) {
			for (Service s : list) {
				Object[] row = { s.getServiceId(), s.getServiceNo(), s.getServiceName(), s.getPrice() };
				tableModel.addRow(row);
			}
		}

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row >= 0) {
					idLabel.setText(table.getValueAt(row, 0).toString());
					serviceNoField.setText(table.getValueAt(row, 1).toString());
					nameField.setText(table.getValueAt(row, 2).toString());
					priceField.setText(table.getValueAt(row, 3).toString());
				}
			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(250, 240, 230));
		panel_2.setBounds(13, 175, 434, 25);
		panel_1.add(panel_2);

		JLabel lblNewLabel_1_1_2 = new JLabel("修改服務項目");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1_2.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(100, 0, 234, 25);
		panel_2.add(lblNewLabel_1_1_2);

		JLabel lblId = new JLabel("編號");
		lblId.setForeground(new Color(0, 0, 0));
		lblId.setFont(new Font("Serif", Font.BOLD, 14));
		lblId.setBounds(70, 217, 60, 25);
		panel_1.add(lblId);

		idLabel = new JLabel("");
		idLabel.setForeground(new Color(0, 0, 0));
		idLabel.setFont(new Font("Serif", Font.BOLD, 14));
		idLabel.setBounds(83, 210, 160, 25);
		panel_1.add(idLabel);

		JLabel lblServiceNo = new JLabel("服務編號");
		lblServiceNo.setForeground(new Color(0, 0, 0));
		lblServiceNo.setFont(new Font("Serif", Font.BOLD, 14));
		lblServiceNo.setBounds(70, 252, 60, 25);
		panel_1.add(lblServiceNo);

		serviceNoField = new JTextField();
		serviceNoField.setBounds(140, 252, 160, 25);
		panel_1.add(serviceNoField);

		JLabel lblName = new JLabel("名稱");
		lblName.setForeground(new Color(0, 0, 0));
		lblName.setFont(new Font("Serif", Font.BOLD, 14));
		lblName.setBounds(70, 287, 60, 25);
		panel_1.add(lblName);

		nameField = new JTextField();
		nameField.setBounds(140, 287, 160, 25);
		panel_1.add(nameField);

		JLabel lblPrice = new JLabel("價格");
		lblPrice.setForeground(new Color(0, 0, 0));
		lblPrice.setFont(new Font("Serif", Font.BOLD, 14));
		lblPrice.setBounds(70, 322, 60, 25);
		panel_1.add(lblPrice);

		priceField = new JTextField();
		priceField.setBounds(140, 322, 160, 25);
		panel_1.add(priceField);

		JButton btnUpdate = new JButton("修改資料");
		btnUpdate.setFont(new Font("Serif", Font.PLAIN, 13));
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (idLabel.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "請先選擇要修改的項目！", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					}

					String serviceNo = serviceNoField.getText().trim();
					String name = nameField.getText().trim();
					String priceText = priceField.getText().trim();

					if (serviceNo.isEmpty() || name.isEmpty() || priceText.isEmpty()) {
						JOptionPane.showMessageDialog(null, "所有欄位均需填寫！", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					}

					int price;
					try {
						price = Integer.parseInt(priceText);
						if (price <= 0) {
							JOptionPane.showMessageDialog(null, "價格必須大於0！", "錯誤", JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "價格必須為數字！", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					}

					Service service = new Service();
					service.setServiceId(Integer.parseInt(idLabel.getText()));
					service.setServiceNo(serviceNo);
					service.setServiceName(name);
					service.setPrice(price);

					ssi.updateService(service);
					JOptionPane.showMessageDialog(null, "修改成功，點擊確認後將更新資料", "提示", JOptionPane.INFORMATION_MESSAGE);
					tableModel.setRowCount(0);
					List<Service> newList = ssi.findAllService();
					if (newList != null) {
						for (Service s : newList) {
							tableModel.addRow(new Object[] { s.getServiceId(), s.getServiceNo(), s.getServiceName(),
									s.getPrice() });
						}
					}
					idLabel.setText("");
					serviceNoField.setText("");
					nameField.setText("");
					priceField.setText("");

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "修改過程中發生錯誤：" + ex.getMessage(), "錯誤",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUpdate.setForeground(new Color(0, 0, 0));
		btnUpdate.setBackground(new Color(175, 238, 238));
		btnUpdate.setBounds(322, 310, 115, 52);
		panel_1.add(btnUpdate);

		JButton btnNewButton_1 = new JButton("回上一頁");
		btnNewButton_1.setFont(new Font("Serif", Font.PLAIN, 13));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Backstage bs = new Backstage();
				bs.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBackground(new Color(175, 238, 238));
		btnNewButton_1.setBounds(162, 372, 115, 52);
		panel_1.add(btnNewButton_1);

		JButton btnDelete = new JButton("刪除選中資料");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (idLabel.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "請先選擇一筆資料！", "提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int serviceId = Integer.parseInt(idLabel.getText());
				String message = "確認要刪除這個服務項目嗎？\n" + "ID：" + idLabel.getText() + "\n" + "服務編號："
						+ serviceNoField.getText() + "\n" + "名稱：" + nameField.getText();
				Object[] options = { "刪除", "取消" };
				int confirm = JOptionPane.showOptionDialog(null, message, "刪除確認", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				if (confirm == 0) {
					try {
						ssi.deleteService(serviceId);
						JOptionPane.showMessageDialog(null, "刪除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						tableModel.setRowCount(0);
						List<Service> newList = ssi.findAllService();
						if (newList != null) {
							for (Service s : newList) {
								tableModel.addRow(new Object[] { s.getServiceId(), s.getServiceNo(), s.getServiceName(),
										s.getPrice() });
							}
						}
						idLabel.setText("");
						serviceNoField.setText("");
						nameField.setText("");
						priceField.setText("");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "刪除過程中發生錯誤：" + ex.getMessage(), "錯誤",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "已取消刪除", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnDelete.setForeground(new Color(255, 245, 228));
		btnDelete.setBackground(new Color(15, 84, 145));
		btnDelete.setBounds(332, 175, 115, 25);
		panel_1.add(btnDelete);

		JButton btnAddService = new JButton("新增服務");
		btnAddService.setFont(new Font("Serif", Font.PLAIN, 13));
		btnAddService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddService add = new AddService();
				add.setVisible(true);
				dispose();
			}
		});
		btnAddService.setForeground(new Color(0, 0, 0));
		btnAddService.setBackground(new Color(15, 84, 145));
		btnAddService.setBounds(322, 240, 115, 52);
		panel_1.add(btnAddService);
	}
}