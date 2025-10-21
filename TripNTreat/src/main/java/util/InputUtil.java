package util;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class InputUtil {

	
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

	
	public static boolean isValidEmail(String email) {
		return email != null && email.matches("^[\\w-_.+]*[\\w-_.]@([\\w]+[.])+[\\w]+[\\w]$");
	}


	public static boolean isValidPhone(String phone) {
		return phone != null && phone.matches("\\d{10}");
	}
}