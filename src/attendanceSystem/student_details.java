package attendanceSystem;
import javax.swing.*;
/*
*@author Satya Narayan Mishra
*/

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import com.mysql.cj.protocol.Resultset;

import java.awt.Font;
import java.awt.Color;
import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.ScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class student_details extends JFrame implements ActionListener{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					student_details window = new student_details();
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
	public student_details() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private int i=0;
	private JTable table;
	private JButton btnClose,btnUpdate,btnDelete;
	private JTextField Registration;
	private JTextField Mobile;
	private JTextField Course;
	private JTextField FatherName;
	private JTextField Gender;
	private JTextField StudentName;
	private JTextField RollNo;
	private JTextField Address;
	private void initialize() {
		setBounds(40, 80, 1300, 610);
		
		JLabel header = new JLabel("Student Management & Details");
		header.setForeground(new Color(139, 0, 0));
		header.setFont(new Font("Cambria Math", Font.BOLD, 20));
		header.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(header, BorderLayout.NORTH);		
		
		connection conn=new connection();
		String sql="select * from `student_info`";
		try {
			ResultSet rslt=conn.stm.executeQuery(sql);	
			if(rslt.next()) {
				// If data available
				
				Panel panel = new Panel();
				getContentPane().add(panel, BorderLayout.CENTER);
				panel.setLayout(null);
				
				Panel panel_1 = new Panel();
				panel_1.setBounds(10, 11, 496, 506);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				btnUpdate = new JButton("Update");
				btnUpdate.setBounds(84, 448, 89, 23);
				panel_1.add(btnUpdate);
				btnUpdate.addActionListener(this);
				
				btnDelete = new JButton("Delete");
				btnDelete.setBounds(210, 448, 89, 23);
				panel_1.add(btnDelete);
				btnDelete.addActionListener(this);
				
				btnClose = new JButton("Close");
				btnClose.setBounds(335, 448, 89, 23);
				panel_1.add(btnClose);
				
				Registration = new JTextField();
				Registration.setText("");
				Registration.setColumns(10);
				Registration.setBounds(201, 396, 223, 20);
				panel_1.add(Registration);
				
				JLabel lblRegistrationDate = new JLabel("Registration Date");
				lblRegistrationDate.setBounds(84, 399, 91, 14);
				panel_1.add(lblRegistrationDate);
				
				JLabel lblMobileNo = new JLabel("Mobile No.");
				lblMobileNo.setBounds(84, 312, 91, 14);
				panel_1.add(lblMobileNo);
				
				Mobile = new JTextField();
				Mobile.setText("");
				Mobile.setColumns(10);
				Mobile.setBounds(201, 309, 223, 20);
				panel_1.add(Mobile);
				
				Course = new JTextField();
				Course.setText("");
				Course.setColumns(10);
				Course.setBounds(201, 265, 223, 20);
				panel_1.add(Course);
				
				JLabel lblCourse = new JLabel("Course");
				lblCourse.setBounds(84, 268, 91, 14);
				panel_1.add(lblCourse);
				
				JLabel lblFathersName_1 = new JLabel("Father's Name");
				lblFathersName_1.setBounds(84, 223, 91, 14);
				panel_1.add(lblFathersName_1);
				
				FatherName = new JTextField();
				FatherName.setText("");
				FatherName.setColumns(10);
				FatherName.setBounds(201, 220, 223, 20);
				panel_1.add(FatherName);
				
				Gender = new JTextField();
				Gender.setText("");
				Gender.setColumns(10);
				Gender.setBounds(201, 177, 223, 20);
				panel_1.add(Gender);
				
				JLabel lblFathersName = new JLabel("Gender");
				lblFathersName.setBounds(84, 180, 91, 14);
				panel_1.add(lblFathersName);
				
				JLabel lblStudentName = new JLabel("Student Name");
				lblStudentName.setBounds(84, 136, 91, 14);
				panel_1.add(lblStudentName);
				
				StudentName = new JTextField();
				StudentName.setText("");
				StudentName.setColumns(10);
				StudentName.setBounds(201, 133, 223, 20);
				panel_1.add(StudentName);
				
				RollNo = new JTextField();
				RollNo.setText("");
				RollNo.setColumns(10);
				RollNo.setBounds(201, 86, 223, 20);
				panel_1.add(RollNo);
				RollNo.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						String rollNo=RollNo.getText().strip();
						Integer intRollNo=Integer.parseInt(rollNo);
						String Query="select * from `student_info` where `roll_no`="+intRollNo;
						try {
							ResultSet quickData=conn.stm.executeQuery(Query);
							if(quickData.next()) {
								StudentName.setText(quickData.getString("stu_name"));
								Gender.setText(quickData.getString("gender"));
								FatherName.setText(quickData.getString("father"));
								Course.setText(quickData.getString("course_name"));
								Mobile.setText(quickData.getString("mobile"));
								Address.setText(quickData.getString("address"));
								Registration.setText(quickData.getString("regi_date"));
							}else {
								StudentName.setText("");
								Gender.setText("");
								FatherName.setText("");
								Course.setText("");
								Mobile.setText("");
								Address.setText("");
								Registration.setText("");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				
				JLabel lblRollNo = new JLabel("Roll No.");
				lblRollNo.setBounds(84, 89, 91, 14);
				panel_1.add(lblRollNo);
				
				Address = new JTextField();
				Address.setText("");
				Address.setColumns(10);
				Address.setBounds(201, 353, 223, 20);
				panel_1.add(Address);
				
				JLabel lblAddress = new JLabel("Address");
				lblAddress.setBounds(84, 356, 91, 14);
				panel_1.add(lblAddress);
				btnClose.addActionListener(this);
				
				Panel panel_2 = new Panel();
				panel_2.setBounds(512, 22, 762, 495);
				panel.add(panel_2);
				panel_2.setLayout(null);
				
				try {
					String query="select * from `student_info`";
					ResultSet data1=conn.stm.executeQuery(query);
					int counter=0,i=0,j=0;
					while(data1.next()) {
						counter+=1;
					}
					String x[]= {"Roll No","Name","Gender","Father","Course","Mobile","Registration Date"};
					String y[][]=new String[counter][8];
						ResultSet data=conn.stm.executeQuery(query);
						while(data.next()) {
							y[i][j++]=data.getString("roll_no");
							y[i][j++]=data.getString("stu_name");
							y[i][j++]=data.getString("gender");
							y[i][j++]=data.getString("father");
							y[i][j++]=data.getString("course_name");
							y[i][j++]=data.getString("mobile");
							y[i][j++]=data.getString("regi_date");
							i++;
							j=0;
						}
					table = new JTable(y,x);
					table.setBounds(10, 11, 403, 18);
					JScrollPane titleSP=new JScrollPane(table);
					panel_2.add(titleSP);
					titleSP.setBounds(10, 24, 742, 432);				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
								
			}else {
				JLabel lblNewLabel = new JLabel("Opps ! There are no any record found...");
				lblNewLabel.setForeground(new Color(255, 0, 0));
				lblNewLabel.setBackground(new Color(255, 0, 0));
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				getContentPane().add(lblNewLabel, BorderLayout.CENTER);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnClose)) {
			this.setVisible(false);
		}else {
			String rollNo=new String(RollNo.getText().strip().toLowerCase());
			Integer intRoll=Integer.parseInt(rollNo);
			String name=StudentName.getText().strip().toLowerCase();
			String gender=Gender.getText().strip().toLowerCase();
			String father=FatherName.getText().strip().toLowerCase();
			String course_name=Course.getText().strip().toLowerCase();
			String mobile=Mobile.getText().strip().toLowerCase();
			String address=Address.getText().strip().toLowerCase();
			if(rollNo.isEmpty() || name.isEmpty() || gender.isEmpty() || father.isEmpty() || course_name.isEmpty() || mobile.isEmpty() || address.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Opps ! all field required..", "Warning", JOptionPane.WARNING_MESSAGE);
			}else {
				connection conn=new connection();
				if(e.getSource().equals(btnUpdate)) {
					String sql="update `student_info` set `stu_name`='"+name+"', `gender`='"+gender+"', `father`='"+father+"', `course_name`='"+course_name+"',`mobile`='"+mobile+"', `address`='"+address+"' where `roll_no`="+intRoll;
					try {
						conn.stm.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Record Updated successfull...", "Success", JOptionPane.WARNING_MESSAGE);
						this.hide();
						new student_details().setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if(e.getSource().equals(btnDelete)) {
					int val=JOptionPane.showConfirmDialog(null, "After clicking you lose all record from database \n Do you want to delete this record?", "Conformation", JOptionPane.YES_NO_OPTION);					
					if(val==0) {
						// clicked on yes button
						String sql1="delete from `attendance_info` where `roll_no`="+intRoll;
						String sql2="delete from `student_info` where `roll_no`="+intRoll;
						try {
							conn.stm.executeUpdate(sql1);
							conn.stm.executeUpdate(sql2);
							JOptionPane.showMessageDialog(null, "Record Deleted successfull");
							this.hide();
							new student_details().setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						// clicked on no button
					}
				}
			}
		}
	}
}
