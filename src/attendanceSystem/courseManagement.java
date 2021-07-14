package attendanceSystem;
/** 
 * @author Satya Narayan Mishra
 */
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class courseManagement extends JFrame implements ActionListener{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					courseManagement window = new courseManagement();
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
	public courseManagement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private int i=0;
	private JTextField EntryDate;
	private JTextField courseName;
	private JTextField newCourse;
	private JTextField discription;
	private JTextField EntryTime;
	private JTable table;
	private JButton btnClose,btnUpdate,btnDelete;
	private void initialize() {
		setBounds(300, 80, 900, 534);
		
		JLabel header = new JLabel("Course Management");
		header.setForeground(new Color(139, 0, 0));
		header.setFont(new Font("Cambria Math", Font.BOLD, 20));
		header.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(header, BorderLayout.NORTH);
				
		connection conn=new connection();
		Panel panel = new Panel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(10, 11, 420, 412);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEntryDate = new JLabel("Entry Date");
		lblEntryDate.setBounds(36, 273, 91, 14);
		panel_1.add(lblEntryDate);
		
		EntryDate = new JTextField();
		EntryDate.setText("");
		EntryDate.setColumns(10);
		EntryDate.setBounds(153, 270, 223, 20);
		panel_1.add(EntryDate);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setBounds(36, 94, 91, 14);
		panel_1.add(lblCourseName);
		
		courseName = new JTextField();		
		courseName.setText("");
		courseName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String mSql="select * from `course_info` where `course_name`='"+courseName.getText().strip()+"'";
				try {
					ResultSet data=conn.stm.executeQuery(mSql);
					if(data.next()) {
						newCourse.setText(courseName.getText().strip());
						discription.setText(data.getString("description"));
						EntryTime.setText(data.getString("c_time"));
						EntryDate.setText(data.getString("c_date"));
					}else {
						newCourse.setText("");
						discription.setText("");
						EntryDate.setText("");
						EntryTime.setText("");
					}
				}catch(Exception exxx) {
					exxx.printStackTrace();
				}
			}
		});
		courseName.setColumns(10);
		courseName.setBounds(153, 91, 223, 20);
		panel_1.add(courseName);
		
		JLabel lblECN = new JLabel("Edit Course Name");
		lblECN.setBounds(36, 141, 130, 14);
		panel_1.add(lblECN);
		
		newCourse = new JTextField();
		newCourse.setText("");
		newCourse.setColumns(10);
		newCourse.setBounds(153, 138, 223, 20);
		panel_1.add(newCourse);
		
		JLabel lblDiscription = new JLabel("Discription");
		lblDiscription.setBounds(36, 185, 91, 14);
		panel_1.add(lblDiscription);
		
		discription = new JTextField();
		discription.setText("");
		discription.setColumns(10);
		discription.setBounds(153, 182, 223, 20);
		panel_1.add(discription);
		
		JLabel lblTime = new JLabel("Entry Time");
		lblTime.setBounds(36, 228, 91, 14);
		panel_1.add(lblTime);
		
		EntryTime = new JTextField();
		EntryTime.setText("");
		EntryTime.setColumns(10);
		EntryTime.setBounds(153, 225, 223, 20);
		panel_1.add(EntryTime);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(77, 332, 89, 23);
		panel_1.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(176, 332, 89, 23);
		panel_1.add(btnDelete);
		btnDelete.addActionListener(this);
		
		btnClose = new JButton("Close");
		btnClose.setBounds(275, 332, 89, 23);
		panel_1.add(btnClose);
		btnClose.addActionListener(this);
		
		Panel panel_2 = new Panel();
		panel_2.setBounds(436, 11, 423, 412);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		
		String query="select * from `course_info`";
		ResultSet data1;
		try {
			data1 = conn.stm.executeQuery(query);
			int counter=0,i=0,j=0;
			while(data1.next()) {
				counter+=1;
			}
			String x[]= {"S.N","Course Name","Description","Entry Date","Entry Time"};
			String y[][]=new String[counter][5];
			Resultset data=(Resultset) conn.stm.executeQuery(query);
			while(((ResultSet) data).next()) {
				Integer sr=i+1;
				y[i][j++]=sr.toString();
				y[i][j++]=((ResultSet) data).getString("course_name");
				y[i][j++]=((ResultSet) data).getString("description");
				y[i][j++]=((ResultSet) data).getString("c_time");
				y[i][j++]=((ResultSet) data).getString("c_date");
				i++;
				j=0;
			}
			table = new JTable(y,x);
			table.setBounds(10, 11, 403, 18);
			JScrollPane titleSP=new JScrollPane(table);
			panel_2.add(titleSP);
			titleSP.setBounds(10, 11, 403, 390);				
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnClose)) {
			this.setVisible(false);
		}else {
			String course_name=courseName.getText().strip();
			String new_course=newCourse.getText().strip();
			String discriptions=discription.getText().strip();
			String entry_time=EntryTime.getText().strip();
			String entry_date=EntryDate.getText().strip();
			
			if(course_name.isEmpty() || new_course.isEmpty() || discriptions.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Opps ! all field required..", "Warning", JOptionPane.WARNING_MESSAGE);
			}else {
				connection conn=new connection();
				if(e.getSource().equals(btnUpdate)) {
					String sql="update `course_info` set `course_name`='"+new_course+"', `description`='"+discriptions+"' where `course_name`='"+course_name+"'";
					try {
						conn.stm.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Record Updated successfull...", "Success", JOptionPane.WARNING_MESSAGE);
						this.hide();
						new courseManagement().setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}else if(e.getSource().equals(btnDelete)) {
					int val=JOptionPane.showConfirmDialog(null, "After clicking you lose all record from database \n Do you want to delete this record?", "Conformation", JOptionPane.YES_NO_OPTION);					
					if(val==0) {
						// clicked on yes button
						String stuSQL="select * from `student_info` where `course_name`='"+course_name;
						try {
							ResultSet stuData=conn.stm.executeQuery(stuSQL);
							while(stuData.next()) {
								Integer rol=Integer.parseInt(stuData.getString("roll_no"));
								String attSql="delete from `attendance_info` where `roll_no`="+rol;
								String stuDel="delete from `student_info` where `roll_no`="+rol;
								conn.stm.executeUpdate(attSql);
								conn.stm.executeUpdate(stuDel);
							}
						} catch (NumberFormatException | SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						String sql1="delete from `student_info` where `course_name`='"+course_name+"'";
						String sql2="delete from `course_info` where `course_name`='"+course_name+"'";
						try {
							conn.stm.executeUpdate(sql1);
							conn.stm.executeUpdate(sql2);
							JOptionPane.showMessageDialog(null, "Record Deleted successfull");
							this.hide();
							new courseManagement().setVisible(true);
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
