package attendanceSystem;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class mainPage extends JFrame implements ActionListener{


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainPage window = new mainPage();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private JButton login,registration;
	private void initialize() {
		setBounds(90, 30, 1200, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Attendance Management System");
		lblNewLabel_1.setBounds(0, 0, 1184, 30);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Cambria Math", Font.PLAIN, 25));
		getContentPane().add(lblNewLabel_1);
		
		login = new JButton("Login");
		login.setFont(new Font("Tahoma", Font.BOLD, 11));
		login.setBounds(93, 552, 129, 30);
		getContentPane().add(login);
		login.addActionListener(this);
		
		registration = new JButton("Registration");
		registration.setFont(new Font("Tahoma", Font.BOLD, 11));
		registration.setBounds(232, 552, 129, 30);
		getContentPane().add(registration);
		registration.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 30, 1184, 631);
		lblNewLabel.setIcon(new ImageIcon(mainPage.class.getResource("/attendanceSystem/image/theme2.png")));
		getContentPane().add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(login)) {
			this.setVisible(false);
			new login().setVisible(true);
		}else if(e.getSource().equals(registration)) {
			this.setVisible(false);
			new Registration().setVisible(true);
		}
		
	}
}
