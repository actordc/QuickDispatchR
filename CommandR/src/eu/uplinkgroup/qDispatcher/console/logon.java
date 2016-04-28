package eu.uplinkgroup.qDispatcher.console;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class logon extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JComboBox sBox;
	private JPasswordField password;

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
		setBounds(100, 100, 404, 207);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		username = new JTextField();
		username.setBounds(122, 11, 256, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 14, 102, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 45, 102, 14);
		contentPane.add(lblPassword);
		
		JButton logOnBtn = new JButton("Log On");
		logOnBtn.setBounds(145, 134, 89, 23);
		contentPane.add(logOnBtn);
		Handler btnHandler = new Handler();
		logOnBtn.addActionListener(btnHandler);
		
		
		sBox = new JComboBox<String>();
		sBox.setMaximumRowCount(2);
		sBox.setModel(new DefaultComboBoxModel<String>(new String[] {"UplinkGroup.eu", "CaptainCode.net"}));
		sBox.setSelectedIndex(0);
		contentPane.add(sBox);
		sBox.setBounds(122, 73, 256, 20);
		
		
		
		JLabel lblServer = new JLabel("Server:");
		lblServer.setBounds(10, 76, 102, 14);
		contentPane.add(lblServer);
		
		password = new JPasswordField();
		password.setBounds(122, 42, 256, 20);
		contentPane.add(password);
		
	}
	
	private class Handler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String user = username.getText();
			char[] passwd = password.getPassword();
			String cBoxSelection = (String)sBox.getSelectedItem();
			PasswordHelper phelp = new PasswordHelper(passwd);
			String pass = phelp.parse();
			CBoxHelper chelp = new CBoxHelper(cBoxSelection);
			String host = chelp.parse();
			URLHelper uhelp = new URLHelper(host, user, pass);
			int returnValue = 0;
			try {
				returnValue = uhelp.chk();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (returnValue == 0){
				System.out.println("Alright. Logged In!");
			} else if (returnValue == -1){
				System.out.println("Uh Oh! Check your Password!");
			} else if (returnValue == -2){
				System.out.println("Oops! There was an error while connecting.");
			}
			
		}
	}
}
