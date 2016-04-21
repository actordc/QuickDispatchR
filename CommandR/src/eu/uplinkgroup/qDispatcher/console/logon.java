package eu.uplinkgroup.qDispatcher.console;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class logon extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logon frame = new logon();
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
	public logon() {
		setTitle("QuickDispatcher - Log On");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 346, 207);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(64, 11, 256, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 14, 57, 14);
		contentPane.add(lblUsername);
		
		textField_1 = new JTextField();
		textField_1.setBounds(64, 42, 256, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 45, 57, 14);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Log On");
		btnNewButton.setBounds(120, 134, 89, 23);
		contentPane.add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(2);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"UpLinkGroup.eu", "CaptainCode.net"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(64, 73, 256, 20);
		contentPane.add(comboBox);
		
		JLabel lblServer = new JLabel("Server:");
		lblServer.setBounds(10, 76, 46, 14);
		contentPane.add(lblServer);
	}
}
