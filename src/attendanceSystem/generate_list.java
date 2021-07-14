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

import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JMonthChooser;
public class generate_list  extends JFrame implements ActionListener{
	private JTextField textField;
	private JButton printBtn,showBtn;
	private JTextArea dataContainer;
	private JMonthChooser monthChooser;
	private JYearChooser yearChooser;
	private JLabel lblcourse;
	private JTextField courseName;
	private Choice choice;
	public String monthName[]= {"January","February","March","April","May","June","July","August","September","October","November","December"};
	 generate_list()
	 {
		 //title ---------------------------------------------
	     setTitle("Generate Student Records");
	     setBounds(450,70,522,600);
	     setResizable(false);
	     getContentPane().setLayout(new BorderLayout(0, 0));
	     
	     Panel panel = new Panel();
	     getContentPane().add(panel, BorderLayout.NORTH);
	     
	     JLabel rollLabel = new JLabel("Roll No");
	     panel.add(rollLabel);
	     
	     textField = new JTextField();
	     panel.add(textField);
	     textField.setColumns(10);
	     
	     JLabel monthLabel = new JLabel("Month");
	     panel.add(monthLabel);
	     
	     monthChooser = new JMonthChooser();
	     panel.add(monthChooser);
	     
	     JLabel yearLabel = new JLabel("Year");
	     panel.add(yearLabel);
	     
	     yearChooser = new JYearChooser();
	     panel.add(yearChooser);
	     
	     
	     dataContainer = new JTextArea("\n\t               Attendance Management System \n___________________________________________________________________________________\n\n");
	     getContentPane().add(dataContainer, BorderLayout.CENTER);
	     JScrollPane sp=new JScrollPane(dataContainer);
         getContentPane().add(sp);
         Font f=new Font("Cambria",Font.BOLD,15);
         dataContainer.setFont(f);
         
	     //Footer------
	     Panel panel_1 = new Panel();
	     getContentPane().add(panel_1, BorderLayout.SOUTH);
	     
	     printBtn = new JButton("Print");
	     panel_1.add(printBtn);
	     
	     showBtn = new JButton("Show");
	     panel_1.add(showBtn);
	     showBtn.addActionListener(this);
	     printBtn.addActionListener(this);
	     
	 }

	
	public static void main(String arg[]) {
		new generate_list().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == showBtn) {
			String a = textField.getText();
			if(a.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Opps ! please fill roll number....");
			}else {
				try {
					connection conn=new connection();
					int myRoll=Integer.parseInt(a);  
					int month=monthChooser.getMonth()+1;
					int year=yearChooser.getYear();
					String sql="select * from `attendance_info` where `roll_no`="+myRoll+" and `month`="+month+" and `year`="+year;
					ResultSet data=conn.stm.executeQuery(sql);
					if(data.next()) {
						String query="select * from `student_info` where `roll_no`="+myRoll;
						ResultSet stuData=conn.stm.executeQuery(query);
						while(stuData.next()) {
							dataContainer.append("  Roll NO \t	:	"+stuData.getString("roll_no")+"\n");
							dataContainer.append("  Student's Name\t:	"+stuData.getString("stu_name")+"\n");
							dataContainer.append("  Gender\t	:	"+stuData.getString("gender")+"\n");
							dataContainer.append("  Father's Name\t:	"+stuData.getString("father")+"\n");
							dataContainer.append("  Course Name\t	:	"+stuData.getString("course_name")+"\n");
							dataContainer.append("  Mobile Number\t:	"+stuData.getString("mobile")+"\n");
							dataContainer.append("  Address\t	:	"+stuData.getString("address")+"\n");
							dataContainer.append("  State\t	:	"+stuData.getString("state")+"\n");
							dataContainer.append("  Registration Time\t:	"+stuData.getString("regi_time")+"\n");
							dataContainer.append("  Registration Date\t:	"+stuData.getString("regi_date")+"\n");
						}
						
						// presenting and upsenting calculation
						int present=0,absent=0,i=0,count=0;
						String issPresentQuery="select `iss_present`, sum(`iss_present`) as `sum` from `attendance_info` where `roll_no`="+myRoll+" and `month`="+month+" and `year`="+year+" and `iss_present`="+1;
						ResultSet attendanceData=conn.stm.executeQuery(issPresentQuery);
						while(attendanceData.next()) {
						present=attendanceData.getInt("sum");
						}
						
						String notPresentQuery="select `iss_present` from `attendance_info` where `roll_no`="+myRoll+" and `month`="+month+" and `year`="+year+" and `iss_present`="+0;
						ResultSet notPresent=conn.stm.executeQuery(notPresentQuery);
						while(notPresent.next()) {
							absent+=1;
						}

						dataContainer.append("  Attendance Month\t:	"+monthName[month-1]+"\n");
						dataContainer.append("  Attendance year\t:	"+year+"\n");
						dataContainer.append("  Total Present\t	:	"+present+"\n");	
						dataContainer.append("  Total Absent\t	:	"+absent+"\n\n");						
						dataContainer.append("________________________________________________________________________________\n");
						dataContainer.append("\t                                Prested Days of "+monthName[month-1]);
						dataContainer.append("\n________________________________________________________________________________\n    ");
						
						String p[] =new String[present];
						String presentedQuery="select `date` from `attendance_info` where `roll_no`="+myRoll+" and `month`="+month+" and `year`="+year+" and `iss_present`="+1;
						ResultSet presentedData=conn.stm.executeQuery(presentedQuery);
						while(presentedData.next()) {
							Integer var=presentedData.getInt("date");;
							p[i]=var.toString();
							i++;
							count+=1;
						}
						// appending data into text field
						for(int j=0;j<count;j++) {
							if(j!=count-1) {
								dataContainer.append(p[j]+", ");
							}else {
								dataContainer.append(p[j]);
							}
						}
						
						
						dataContainer.append("\n\n______________________________________________________________________________\n");
						dataContainer.append("\t                                Absent Days of "+monthName[month-1]);
						dataContainer.append("\n______________________________________________________________________________\n    ");
						
						count=0;
						i=0;
						String ab[] =new String[absent];
						String absentQuery="select `date` from `attendance_info` where `roll_no`="+myRoll+" and `month`="+month+" and `year`="+year+" and `iss_present`="+0;
						ResultSet absentData=conn.stm.executeQuery(absentQuery);
						while(absentData.next()) {
							Integer var1=absentData.getInt("date");;
							ab[i]=var1.toString();
							i++;
							count+=1;
						}
//						 appending data into text field
						for(int k=0;k<count;k++) {
							if(k!=count-1) {
								dataContainer.append(ab[k]+", ");
							}else {
								dataContainer.append(ab[k]);
							}
						}
						dataContainer.append("\n\n\n\n");
						dataContainer.append("\n---------------------------------------------------------------------------------------------\n");
						dataContainer.append("\n\n\n\n");
						
					}else {
						JOptionPane.showMessageDialog(null,"There are no any data found about roll no"+myRoll);
					}
				}catch(Exception excep) {
					excep.printStackTrace();
				}
			}
			
		}
		if(e.getSource() == printBtn) {
			try {
				dataContainer.print();
			}catch(Exception exc) {
				exc.printStackTrace();
			}
		}
	}
}
