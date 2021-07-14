package attendanceSystem;
/*
*@author Satya Narayan Mishra
*/

import attendanceSystem.student_details;
import attendanceSystem.generate_list;
import attendanceSystem.home;
import attendanceSystem.new_student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.mysql.cj.protocol.Resultset;

import net.miginfocom.swing.MigLayout;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

//Table creation class================================================
class tableCreation{
	private JScrollPane sp;
	private JTable t1;
	private JCheckBox cb;
	String tableData;
	int counter=0,flag=0,i=0,j=0;
	void create(String course,JLabel welcomeMsg1,JLabel welcomeMsg2,JFrame f) {
		connection conObj=new connection();
		String query="select * FROM `student_info` where `course_name`='"+course+"'";
		try {
			ResultSet data=conObj.stm.executeQuery(query);
			while(data.next()) {
				flag=1;
				counter+=1;
			}
			//Dynamic Table creation
			String x[]= {"S.N","Roll.NO","Student's Name","Father's Name","Attendance Date","Attendance"};
			String y[][]=new String[counter][6];
			// students attendance, dynamic data creation
			t1=new JTable(y,x);
			sp=new JScrollPane(t1);
			sp.setBounds(230, 150, 900, 400);
			f.add(sp);
			sp.setVisible(false);
			
			if(flag==1) {	
//				new home().setVisible(true);
//				f.hide();
				welcomeMsg1.setVisible(false);
				welcomeMsg2.setVisible(false);
				ResultSet dbData=conObj.stm.executeQuery(query);
				while(dbData.next()) {
					Integer sq=i+1;
					y[i][j++]=sq.toString();
					y[i][j++]=dbData.getString("roll_no");
					y[i][j++]=dbData.getString("stu_name");
					y[i][j++]=dbData.getString("father");
					y[i][j++]=dbData.getString("regi_date");
					i++;
					j=0;
				}
				System.out.print(y[0][4]);
				counter=0;
				i=0;j=0;
				sp.setVisible(true);
			}else {
				//showing text (No data found)
				new home().setVisible(true);
				JOptionPane.showMessageDialog(null, "Opps ! no any student available about this course", "Warning Message", JOptionPane.WARNING_MESSAGE);
				f.hide();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}



// Main Class===============================================
public class home extends JFrame implements ActionListener {
	private Choice choice;
	private JDateChooser dateChooser;
	private JButton takeAttendance,refresh,update;
	private JLabel welcomeMsg1,welcomeMsg2,noData;
home()
{
	//------------------------------------ Title --------------------------------------------------------------
	setTitle("Attendance Management System");
	setBounds(0,5,1365,730);
	setResizable(false);
	JMenuBar mb=new JMenuBar();
	JMenu master=new JMenu("Master");
	JMenuItem mi1=new JMenuItem("New Student");
	JMenuItem mi2=new JMenuItem("Student Management & Details");
	
	
	master.setForeground(Color.BLUE);
	Font f=new Font("monospaced",Font.PLAIN,16);
	mi1.setFont(f);
            ImageIcon img_mi1=new ImageIcon(ClassLoader.getSystemResource("attendanceSystem/image/logo1.png"));
            Image image_mi1=img_mi1.getImage().getScaledInstance(16,20,Image.SCALE_DEFAULT);
            mi1.setIcon(new ImageIcon(image_mi1));
	mi1.setBackground(Color.WHITE);
	
	mi2.setFont(f);
            ImageIcon img_mi2=new ImageIcon(ClassLoader.getSystemResource("attendanceSystem/image/logo7.png"));
            Image image_mi2=img_mi2.getImage().getScaledInstance(16,20,Image.SCALE_DEFAULT);
            mi2.setIcon(new ImageIcon(image_mi2));
            mi2.setBackground(Color.WHITE);
            mi1.addActionListener(this);
	mi2.addActionListener(this);
	
	JMenu report=new JMenu("Management");
	JMenuItem r1=new JMenuItem("Generate Record");
	r1.setFont(f);
	r1.addActionListener(this);

	JMenuItem r2=new JMenuItem("Course Management");
	r2.setFont(f);
	r2.addActionListener(this);
	
	JMenuItem r3=new JMenuItem("New Course");
	r3.setFont(f);
	r3.addActionListener(this);
	
	JMenu exit=new JMenu("Exit");
	JMenuItem ex=new JMenuItem("Exit");
	ex.setFont(f);
	ex.setBackground(Color.WHITE);
	ex.addActionListener(this);
	
	master.add(mi1);
	master.add(mi2);
	report.add(r1);
	report.add(r3);
	report.add(r2);
	
	exit.add(ex);
	mb.add(master);
	mb.add(report);
	mb.add(exit);
	
	setJMenuBar(mb);
	
	setFont(new Font("Senserif",Font.BOLD,16));
	getContentPane().setLayout(null);
	
	JLabel Title = new JLabel("Attendance Management System");
	Title.setHorizontalAlignment(SwingConstants.CENTER);
	Title.setForeground(new Color(0, 139, 139));
	Title.setBackground(new Color(72, 209, 204));
	Title.setBounds(10, 0, 1349, 52);
	Title.setFont(new Font("Cambria Math", Font.BOLD, 20));
	getContentPane().add(Title);
	
	JLabel titleUnderline = new JLabel("________________________________________________________________________________________________________________________________________________________________________________________");
	titleUnderline.setHorizontalAlignment(SwingConstants.CENTER);
	titleUnderline.setBounds(0, 34, 1349, 14);
	getContentPane().add(titleUnderline);
	
	//-------------------------------------------- Date and courses ---------------------------------------------------
	JLabel date = new JLabel("Date:");
	date.setFont(new Font("Tahoma", Font.BOLD, 15));
	date.setBounds(807, 83, 46, 14);
	getContentPane().add(date);
	
	JLabel course = new JLabel("Course :");
	course.setFont(new Font("Tahoma", Font.BOLD, 15));
	course.setBounds(997, 83, 63, 14);
	getContentPane().add(course);
	
	choice = new Choice();
	connection conn=new connection();
	String sql="select `course_name` from `course_info`";
	try {
		ResultSet data=conn.stm.executeQuery(sql);
		while(data.next()) {
			choice.add(data.getString("course_name"));
		}
	}catch(SQLException exp) {
		exp.printStackTrace();
	}
	choice.setBounds(1066, 83, 105, 20);
	getContentPane().add(choice);
	
	dateChooser = new JDateChooser();
	dateChooser.setBounds(848, 83, 139, 20);
	getContentPane().add(dateChooser);	
	dateChooser.setDateFormatString("yyyy-MM-dd");
	//By default set today date 
	Date d= new Date();
	dateChooser.setDate(d);
	
	takeAttendance = new JButton("Take Attendance");
	takeAttendance.setFont(new Font("Tahoma", Font.BOLD, 11));
	takeAttendance.setBounds(1177, 83, 144, 23);
	getContentPane().add(takeAttendance);
	
	
	
	welcomeMsg1 = new JLabel("Welcome to Attendance Management System");
	welcomeMsg1.setFont(new Font("Cambria Math", Font.BOLD, 35));
	welcomeMsg1.setForeground(new Color(128, 0, 0));
	welcomeMsg1.setHorizontalAlignment(SwingConstants.CENTER);
	welcomeMsg1.setBounds(134, 266, 1173, 52);
	getContentPane().add(welcomeMsg1);
	
	welcomeMsg2 = new JLabel("Please select  today's date and course then you can take a attendance.");
	welcomeMsg2.setHorizontalAlignment(SwingConstants.CENTER);
	welcomeMsg2.setFont(new Font("Sitka Small", Font.PLAIN, 15));
	welcomeMsg2.setBounds(120, 329, 1187, 23);
	getContentPane().add(welcomeMsg2);
	
	takeAttendance.addActionListener(this);
	setVisible(false);
	setDefaultCloseOperation(this.EXIT_ON_CLOSE);
}

public static void main(String args[])
{
	new home().setVisible(true);
}
@Override
public void actionPerformed(ActionEvent ev) {
	// TODO Auto-generated method stub
		String msg=ev.getActionCommand();
		if(msg.equals("New Student")){
			new new_student().setVisible(true);
		}else if(msg.equals("Student Management & Details")){
	        new student_details().setVisible(true);
	    }else if(msg.equals("Generate Record")){
			new generate_list().setVisible(true);
		}else if(msg.equals("New Course")){
			new newCourse().setVisible(true);
		}else if(msg.equals("Course Management")){
			new courseManagement().setVisible(true);
		}else if(msg.equals("Exit")){
			System.exit(0);
		}
		
//	--------------------------------------- Students Attendance  ----------------------------------------------
		if(ev.getSource() == takeAttendance) {
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			String Fdate=df.format(dateChooser.getDate());
			if(Fdate.isEmpty()) {
//				when date is not selected then do nothing.
			}else {				
				String arr_date[]=Fdate.split("-");
				int date=Integer.parseInt(arr_date[2]);
				int month=Integer.parseInt(arr_date[1]);
				int year=Integer.parseInt(arr_date[0]);
				int flag=0,counter=0;
				String course=choice.getSelectedItem().toLowerCase();
				connection conObj=new connection();
				String query="select * FROM `student_info` where `course_name`='"+course+"'";
				try {
					ResultSet data=conObj.stm.executeQuery(query);
					while(data.next()) {
						flag=1;
						counter+=1;
					}
										
					if(flag==1) {	
						// Open another window for taking attendance......
						new Attendance(date,month,year,course,counter).setVisible(true);
					}else {
						//showing text (No data found)
						new home().setVisible(true);
						JOptionPane.showMessageDialog(null, "Opps ! no any student available about this course", "Warning Message", JOptionPane.WARNING_MESSAGE);
						this.hide();
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}	
	}  
}
