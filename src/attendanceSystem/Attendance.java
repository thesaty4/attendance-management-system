package attendanceSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import com.toedter.components.JSpinField;
import java.awt.GridBagConstraints;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.CardLayout;
/*
*@author Satya Narayan Mishra
*/
public class Attendance extends JFrame implements ActionListener {
	private JTable table;
	private JButton done;
	private JCheckBox cb1[];
	private JLabel numOfdata,selectedDate;
	private JLabel LabelRollNo[];
	private JLabel crs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		int month=0,date=0,year = 0,counter=0;
		String course = null;
		new Attendance( date,month,year,course,counter).setVisible(true);
	}
/**
 * Application start
 * @param date
 * @param month
 * @param year
 * @param course
 * @param counter
 */
	Attendance(Integer date,Integer month,Integer year,String course,Integer counter) {
		setBounds(400, 130, 700, 530);
		getContentPane().setLayout(null);
		crs = new JLabel(course);
		//Data container---------------------------
		JScrollPane sp=new JScrollPane();
		JPanel panel = new JPanel();
		sp.setViewportView(panel);
				
		//Title---------------------------
		JLabel title = new JLabel("Attendance Date : "+date.toString()+"-"+month+"-"+year+"   Course : "+course.toUpperCase());
		title.setForeground(new Color(128, 0, 0));
		title.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(10, 11, 664, 33);
		getContentPane().add(title);
		
		
		// fetching data from database and setting the data into class 
		myDb dbObj=new myDb();
		dbObj.setData(date, month, year, course, counter);
		
		// this is dummy JLabel for accessing on Action Listening time
		numOfdata=new JLabel(counter.toString());
		selectedDate=new JLabel(date+"-"+month+"-"+year);
		
		String n[][]=new String[1][4];
		String x[]= {"A/P","S.N","Roll.NO","Student's Name"};
		table = new JTable(n,x);
		JScrollPane titleSP=new JScrollPane(table);
		getContentPane().add(titleSP);
		titleSP.setBounds(102, 53, 508, 25);
		
		
		//inserting dynamically checkbox
		cb1=new JCheckBox[counter];
		LabelRollNo=new JLabel[counter];
		int boundsY=0;
		for(int i=0,k=0;i<counter;i++) {
			    //LabelRollNo is also dummy label for accessing on Action Listening time
				connection conn=new connection();
				String sql="select * from `attendance_info` where `roll_no`="+dbObj.DbData[i][k+1]+" and `date`="+date+" and `month`="+month+" and `year`="+year+" and `iss_present`="+1;
				try {
					ResultSet attendaceData=conn.stm.executeQuery(sql);
					if(attendaceData.next()) {
						cb1[i] = new JCheckBox("                                    "+dbObj.DbData[i][k]+"                                    "+dbObj.DbData[i][k+1]+"                         "+dbObj.DbData[i][k+2].toUpperCase(),true);	
					}else {
						cb1[i] = new JCheckBox("                                    "+dbObj.DbData[i][k]+"                                    "+dbObj.DbData[i][k+1]+"                         "+dbObj.DbData[i][k+2].toUpperCase());
						String f1="insert into `attendance_info` (`roll_no`,`date`,`month`,`year`,`iss_present`) values ("+dbObj.DbData[i][k+1]+","+date+","+month+","+year+","+0+")";
						try {
							conn.stm.executeUpdate(f1);
						}catch(SQLException sqlex) {
							sqlex.printStackTrace();
						}
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				//setting checkbox bounds dynamically======================
			    LabelRollNo[i]=new JLabel(dbObj.DbData[i][k+1]);
				cb1[i].setBounds(50, boundsY, 508, 23);
				panel.add(cb1[i]);
				boundsY+=25;
		}
		//Setting Layout Dynamically------------
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 490, Short.MAX_VALUE)
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, boundsY, Short.MAX_VALUE)
				);
		panel.setLayout(gl_panel);
		sp.setBounds(102, 75, 508, 355);
		getContentPane().add(sp);
		
		//Done button-----------------------------------------
		done = new JButton("Attendance Done");
		done.setFont(new Font("Cambria Math", Font.PLAIN, 15));
		done.setForeground(new Color(0, 0, 0));
		done.setBounds(102, 429, 508, 33);
		getContentPane().add(done);
		done.addActionListener(this);
		for(int i=0;i<counter;i++) {
				cb1[i].addActionListener(this);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==done) {
			this.hide();
		}else {
			String strNumOfData=numOfdata.getText();
			int intNumOfData=Integer.parseInt(strNumOfData);
			String strDate=selectedDate.getText();
			String arrDate[]=strDate.split("-");
			int intDate=Integer.parseInt(arrDate[0]);
			int intMonth=Integer.parseInt(arrDate[1]);
			int intYear=Integer.parseInt(arrDate[2]);
			
			connection conn=new connection();
			for(int i=0;i<intNumOfData;i++) {
				String strRollNo=LabelRollNo[i].getText();
				int intRollNo=Integer.parseInt(strRollNo);
				if(e.getSource()==cb1[i]) {
					
					try {
						String sql="select * from `attendance_info` where `roll_no`="+intRollNo+" and `date`="+intDate+" and `month`="+intMonth+" and `year`="+intYear+" and `iss_present`="+1;
						ResultSet dbDataset=conn.stm.executeQuery(sql);
						if(dbDataset.next()) {
							//when something found in the database then---------------------
							if(cb1[i].isSelected()) {
								//when check box selected
								String updateSql="update `attendance_info` set `iss_present`="+1+" where `roll_no`="+intRollNo+" and `date`="+intDate+" and `month`="+intMonth+" and `year`="+intYear;
								conn.stm.executeUpdate(updateSql);
							}else {
								//when check box unselected
								int att=JOptionPane.showConfirmDialog(null, "Already Attended, Do you want to update?", "Modification", JOptionPane.YES_NO_OPTION);
								if(att==0) {
									String updateSql="update `attendance_info` set `iss_present`="+0+" where `roll_no`="+intRollNo+" and `date`="+intDate+" and `month`="+intMonth+" and `year`="+intYear;
									conn.stm.executeUpdate(updateSql);
								}else {
									cb1[i].setSelected(true);
								}
							}
						}else{
							String sql2="select * from `attendance_info` where `roll_no`="+intRollNo+" and `date`="+intDate+" and `month`="+intMonth+" and `year`="+intYear+" and `iss_present`="+0;
							ResultSet dbDataset2=conn.stm.executeQuery(sql2);
							if(dbDataset2.next()) {
								//when something found in the database then---------------------
								if(cb1[i].isSelected()) {
									//when check box selected
									String updateSql="update `attendance_info` set `iss_present`="+1+" where `roll_no`="+intRollNo+" and `date`="+intDate+" and `month`="+intMonth+" and `year`="+intYear;
									conn.stm.executeUpdate(updateSql);
								}else {
									//when check box unselected
									int att=JOptionPane.showConfirmDialog(null, "Already Attended, Do you want to update?", "Modification", JOptionPane.YES_NO_OPTION);
									if(att==0) {
										String updateSql="update `attendance_info` set `iss_present`="+0+" where `roll_no`="+intRollNo+" and `date`="+intDate+" and `month`="+intMonth+" and `year`="+intYear;
										conn.stm.executeUpdate(updateSql);
									}else {
										cb1[i].setSelected(true);
									}
								}
							}else {
								//when nothing found in the database then------------------------
//								
							}
						}
						
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}
}



// for getting data from database
class myDb{
	Integer i=0,j=0,k;
//	String x[]= {"Attendance","S.N","Roll.NO","Student's Name","Father's Name"};
	String DbData[][];
	
	
	void setData(Integer date,Integer month, Integer year,String course,int counter) {
		String todayDate=date.toString()+"-"+month.toString()+"-"+year.toString();
		String y[][]=new String[counter][4];
		connection obj=new connection();
		String sql="select * from `student_info` where `course_name`='"+course+"'";
		try {
			ResultSet dbResult=obj.stm.executeQuery(sql);
			while(dbResult.next()) {
				k=i+1;
				y[i][j++]=k.toString();
				y[i][j++]=dbResult.getString("roll_no");
				y[i][j++]=dbResult.getString("stu_name");
				y[i][j++]=dbResult.getString("father");
				i++;
				j=0;
			}
		
		setDbData(y);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void setDbData(String y[][]) {
		DbData=y;
	}
}
