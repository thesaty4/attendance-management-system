package attendanceSystem;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class newCourse extends JFrame implements ActionListener{
	private JTextField courseName;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new newCourse().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public newCourse() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	JTextArea textArea ;
	JButton add,close;
	private void initialize() {
		setBounds(200, 100, 859, 482);
		setResizable(false);
		getContentPane().setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(65, 47, 330, 367);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(-118, -65, 458, 486);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(newCourse.class.getResource("/attendanceSystem/image/logo2.png")));
		
		Panel right = new Panel();
		right.setBounds(462, 47, 358, 361);
		getContentPane().add(right);
		right.setLayout(null);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setBounds(55, 75, 87, 14);
		right.add(lblCourseName);
		
		courseName = new JTextField();
		courseName.setText("");
		courseName.setBounds(152, 72, 154, 20);
		right.add(courseName);
		courseName.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(55, 114, 87, 14);
		right.add(lblDescription);
		
		textArea = new JTextArea();
		textArea.setBounds(154, 114, 152, 111);
		right.add(textArea);
		
		add = new JButton("Add ");
		add.setBounds(53, 270, 89, 23);
		right.add(add);
		add.addActionListener(this);
		
		close = new JButton("Close");
		close.setBounds(212, 270, 89, 23);
		right.add(close);
		close.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==close) {
			this.hide();
		}else if(e.getSource()==add){
			String name=courseName.getText().strip();
			String disc=textArea.getText().strip().toLowerCase();
			if(name.isEmpty() || disc.isEmpty()) {
				JOptionPane.showMessageDialog(null, "All field required !");
			}else {
				String sql="insert into `course_info`(`course_name`,`description`) values ('"+name+"','"+disc+"')";
				try {
					connection conn=new connection();
					conn.stm.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "New Course added successfully !");
					courseName.setText("");
					textArea.setText("");
				}catch(Exception exx) {
					JOptionPane.showMessageDialog(null, "Opps ! This course already exist..");
				}
			}
		}
	}
}
