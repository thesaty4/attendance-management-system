package attendanceSystem;
import javax.swing.*;
/*
*@author Satya Narayan Mishra
*/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
public class new_student  extends JFrame implements ActionListener{
	private Choice Troll_no;
	private JTextField Tname;
	private JTextField Tfather;
	private JTextField Tmobile;
	private JTextField Taddress;
	private JButton register;
	private Integer dbRollno=0,flag=0;
	private Choice courseChoice,chooseState;
	private ButtonGroup bg;
	private JRadioButton male,female,other;
	new_student(){
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		setBounds(380,150,600,350);
		setTitle("New Student Registration");
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout); 
		setResizable(false);
		
		JLabel reg_form = new JLabel("Registration Form");
		springLayout.putConstraint(SpringLayout.NORTH, reg_form, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, reg_form, -212, SpringLayout.EAST, getContentPane());
		reg_form.setForeground(new Color(0, 128, 128));
		reg_form.setFont(new Font("Cambria Math", Font.BOLD, 20));
		getContentPane().add(reg_form);
		
		//Roll no-------------------
		JLabel roll_no = new JLabel("Roll No");
		roll_no.setFont(new Font("Tahoma", Font.PLAIN, 13));
		roll_no.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(roll_no);
		
		Troll_no = new Choice();
		springLayout.putConstraint(SpringLayout.WEST, Troll_no, 213, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, Troll_no, -231, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, Troll_no, -63, SpringLayout.EAST, getContentPane());
		getContentPane().add(Troll_no);
		//Add roll number in choice button from database
		connection conObj=new connection();
		try {
			String sql="select `roll_no` from `student_info`";
			ResultSet data2=conObj.stm.executeQuery(sql);
			while(data2.next()) {
				dbRollno=data2.getInt(1);
				flag=1;
			}
			if(flag==0) {
				dbRollno=1;
			}else {
				dbRollno+=1;
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		Troll_no.add(dbRollno.toString());
		
		
		//Student name--------------		
		JLabel stu_name = new JLabel("Student Name ");
		springLayout.putConstraint(SpringLayout.NORTH, stu_name, 87, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, roll_no, -10, SpringLayout.NORTH, stu_name);
		springLayout.putConstraint(SpringLayout.EAST, stu_name, -406, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, roll_no, 0, SpringLayout.WEST, stu_name);
		stu_name.setFont(new Font("Tahoma", Font.PLAIN, 13));
		stu_name.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(stu_name);
		
		Tname = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, Tname, 6, SpringLayout.SOUTH, Troll_no);
		springLayout.putConstraint(SpringLayout.WEST, Tname, 35, SpringLayout.EAST, stu_name);
		springLayout.putConstraint(SpringLayout.EAST, Tname, 0, SpringLayout.EAST, Troll_no);
		Tname.setColumns(10);
		getContentPane().add(Tname);
		
		//father name------------
		JLabel father = new JLabel("Father's Name ");
		springLayout.putConstraint(SpringLayout.WEST, father, 0, SpringLayout.WEST, roll_no);
		father.setFont(new Font("Tahoma", Font.PLAIN, 13));
		father.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(father);
		
		Tfather = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, Tfather, 33, SpringLayout.EAST, father);
		springLayout.putConstraint(SpringLayout.EAST, Tfather, 0, SpringLayout.EAST, Troll_no);
		Tfather.setColumns(10);
		getContentPane().add(Tfather);
		
		//Course----------------
		JLabel course = new JLabel("Course's Name ");
		springLayout.putConstraint(SpringLayout.NORTH, course, 10, SpringLayout.SOUTH, father);
		springLayout.putConstraint(SpringLayout.WEST, course, 0, SpringLayout.WEST, roll_no);
		course.setFont(new Font("Tahoma", Font.PLAIN, 13));
		course.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(course);
		
		courseChoice = new Choice();
		springLayout.putConstraint(SpringLayout.NORTH, courseChoice, 6, SpringLayout.SOUTH, Tfather);
		springLayout.putConstraint(SpringLayout.WEST, courseChoice, 0, SpringLayout.WEST, reg_form);
		springLayout.putConstraint(SpringLayout.EAST, courseChoice, 0, SpringLayout.EAST, Troll_no);
		getContentPane().add(courseChoice);
		courseChoice.add("Select Course");		
		connection conn=new connection();
		String sql="select `course_name` from `course_info`";
		try {
			ResultSet data=conn.stm.executeQuery(sql);
			while(data.next()) {
				courseChoice.add(data.getString("course_name"));
			}
		}catch(SQLException exp) {
			exp.printStackTrace();
		}
		
		
		
		//mobile-------------------	
		JLabel mobile = new JLabel("Mobile Number  ");
		springLayout.putConstraint(SpringLayout.NORTH, mobile, 10, SpringLayout.SOUTH, course);
		springLayout.putConstraint(SpringLayout.WEST, mobile, 0, SpringLayout.WEST, roll_no);
		mobile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mobile.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(mobile);
		
		Tmobile = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, Tmobile, 26, SpringLayout.EAST, mobile);
		springLayout.putConstraint(SpringLayout.EAST, Tmobile, 0, SpringLayout.EAST, Troll_no);
		Tmobile.setColumns(10);
		getContentPane().add(Tmobile);
		
		//Address---------------
		JLabel address = new JLabel("Address ");
		springLayout.putConstraint(SpringLayout.NORTH, address, 10, SpringLayout.SOUTH, mobile);
		springLayout.putConstraint(SpringLayout.WEST, address, 0, SpringLayout.WEST, roll_no);
		address.setFont(new Font("Tahoma", Font.PLAIN, 13));
		address.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(address);
		
		Taddress = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, Taddress, 228, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, Tmobile, -6, SpringLayout.NORTH, Taddress);
		springLayout.putConstraint(SpringLayout.WEST, Taddress, 70, SpringLayout.EAST, address);
		springLayout.putConstraint(SpringLayout.EAST, Taddress, 0, SpringLayout.EAST, Troll_no);
		Taddress.setColumns(10);
		getContentPane().add(Taddress);
		
		//State-----------------
		JLabel state = new JLabel("State ");
		springLayout.putConstraint(SpringLayout.NORTH, state, 10, SpringLayout.SOUTH, address);
		springLayout.putConstraint(SpringLayout.WEST, state, 0, SpringLayout.WEST, roll_no);
		state.setFont(new Font("Tahoma", Font.PLAIN, 13));
		state.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(state);
		
		//Gender---------------
		male = new JRadioButton("Male");
		springLayout.putConstraint(SpringLayout.NORTH, Tfather, 4, SpringLayout.SOUTH, male);
		springLayout.putConstraint(SpringLayout.SOUTH, male, -175, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(male);
		
		female = new JRadioButton("Female");
		springLayout.putConstraint(SpringLayout.NORTH, female, 112, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, female, 331, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, male, -61, SpringLayout.WEST, female);
		getContentPane().add(female);
		
		other = new JRadioButton("Other");
		springLayout.putConstraint(SpringLayout.SOUTH, other, -5, SpringLayout.NORTH, Tfather);
		springLayout.putConstraint(SpringLayout.WEST, other, 48, SpringLayout.EAST, female);
		getContentPane().add(other);
		
		bg=new ButtonGroup();
		bg.add(male);bg.add(female);bg.add(other);
		
		JLabel gender = new JLabel("Gender");
		springLayout.putConstraint(SpringLayout.NORTH, father, 12, SpringLayout.SOUTH, gender);
		springLayout.putConstraint(SpringLayout.NORTH, gender, 0, SpringLayout.NORTH, male);
		springLayout.putConstraint(SpringLayout.EAST, gender, 0, SpringLayout.EAST, roll_no);
		gender.setHorizontalAlignment(SwingConstants.CENTER);
		gender.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(gender);
		
		//state choice
		chooseState = new Choice();
		springLayout.putConstraint(SpringLayout.NORTH, chooseState, 3, SpringLayout.SOUTH, Taddress);
		springLayout.putConstraint(SpringLayout.WEST, chooseState, 0, SpringLayout.WEST, reg_form);
		springLayout.putConstraint(SpringLayout.EAST, chooseState, 0, SpringLayout.EAST, Troll_no);
		getContentPane().add(chooseState);
		chooseState.add("Select State");chooseState.add("Uttar Pradesh");chooseState.add("Andhra Pradesh");chooseState.add("Assam");chooseState.add("Goa");
		chooseState.add("Gujarat");chooseState.add("Hariyana");
		
		//Submit button
		register = new JButton("Register");
		springLayout.putConstraint(SpringLayout.SOUTH, register, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, register, -233, SpringLayout.EAST, getContentPane());
		getContentPane().add(register);
		
		//Action for register button
		register.addActionListener(this);
		
	}
	
	public static void main(String arg[]) {
		new new_student().setVisible(true);;
	}
//	Tname;
//	private JTextField Tfather;
//	private JTextField Tcourse;
//	private JTextField Tmobile;
//	private JTextField Taddress;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == register) {
			Integer rollNumber=dbRollno;
			String name=Tname.getText().toLowerCase();
			String gender=null;
			if(male.isSelected()) {
				gender="male";
			}else if(female.isSelected()) {
				gender="female";
			}else if(other.isSelected()) {
				gender="other";
			}
			String father=Tfather.getText().toLowerCase();
			String course=courseChoice.getSelectedItem();
			String mobile=Tmobile.getText();
			String address=Taddress.getText().toLowerCase();
			String state=chooseState.getSelectedItem();
			//Check null values-----
			if(name.isEmpty() || gender.isEmpty() || father.isEmpty() || course.equals("Select Course") || mobile.isEmpty() || address.isEmpty() || state.equals("Select State")) {
				JOptionPane.showMessageDialog(null, "Opps ! all field required....");
			}else {
				connection conObj=new connection();
				try {
					String sql="insert into `student_info` (`stu_name`,`gender`,`father`,`course_name`,`mobile`,`address`,`state`) values ('"+name+"', '"+gender+"', '"+father+"', '"+course.toLowerCase()+"', '"+mobile+"', '"+address+"', '"+state.toLowerCase()+"')";
					conObj.stm.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "User Registration successfull");
				}catch(Exception exc) {
					exc.printStackTrace();
				}
				
				//After inserting data, clearing form
				rollNumber+=1;
				String newRollNo=rollNumber.toString();
				Troll_no.removeAll();
				Troll_no.add(newRollNo);
				Tname.setText(null);Tfather.setText(null);courseChoice.select("Select Course");Tmobile.setText(null);Taddress.setText(null);chooseState.select("Select State");
			}
		}
		
	}
}
