package util;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class InputUtil {

	/** 監聽欄位輸入，若有任一欄位有輸入內容才啟用按鈕 **/
	public static void enableButtonWhenAnyFieldNotEmpty(JTextField[] fields, JButton button) {
		DocumentListener listener = new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				check();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				check();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				check();
			}

			private void check() {
				boolean anyFilled = false;
				for (JTextField field : fields) {
					if (!field.getText().trim().isEmpty()) {
						anyFilled = true;
						break;
					}
				}
				button.setEnabled(anyFilled);
			}
		};

		for (JTextField field : fields) {
			field.getDocument().addDocumentListener(listener);
		}
	}

	/** 監聽JTextField輸入數量，大於零才啟動按鈕 **/
	public static void enableButtonWhenAnyQuantityGreaterThanZero(JTextField[] fields, JButton button) {
		DocumentListener listener = new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				check();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				check();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				check();
			}

			private void check() {
				boolean validQuantity = false;
				for (JTextField field : fields) {
					String text = field.getText().trim();
					if (!text.isEmpty()) {
						try {
							int value = Integer.parseInt(text);
							if (value > 0) {
								validQuantity = true;
								break;
							}
						} catch (NumberFormatException ex) {
							// 忽略非數字輸入
						}
					}
				}
				button.setEnabled(validQuantity);
			}
		};

		for (JTextField field : fields) {
			field.getDocument().addDocumentListener(listener);
		}
	}

	/** 監聽JTextArea是否有內容，有內容才啟動按鈕 **/
	public static void enableButtonWhenTextAreaNotEmpty(JTextArea textArea, JButton button) {
		DocumentListener listener = new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				check();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				check();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				check();
			}

			private void check() {
				button.setEnabled(!textArea.getText().trim().isEmpty());
			}
		};

		textArea.getDocument().addDocumentListener(listener);
	}

	// 驗證輸入是否為正整數
	public static boolean isPositiveInteger(String input) {
		if (input == null || input.trim().isEmpty()) {
			return false;
		}
		try {
			int value = Integer.parseInt(input);
			return value > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// 驗證電子郵件格式（簡單驗證）
	public static boolean isValidEmail(String email) {
		return email != null && email.matches("^[\\w-_.+]*[\\w-_.]@([\\w]+[.])+[\\w]+[\\w]$");
	}

	// 驗證電話格式（簡單10位數字）
	public static boolean isValidPhone(String phone) {
		return phone != null && phone.matches("\\d{10}");
	}
}