import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class neww {

	private JFrame frame;
	private JTextField txtbname;
	private JTable table;
	private JTextField txtbedition;
	private JTextField txtbprice;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					neww window = new neww();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public neww() {
		initialize();
		Connect();
		table_load();
		
	}
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/bookshop", "root","infinity24");
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex) 
	        {
	        	   ex.printStackTrace();
	        }

	    }
	 
	 
	 
	   public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select * from book");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	
	
	
	
	
	
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 594, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "REGISTRION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(30, 70, 254, 205);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("BOOK NAME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 39, 85, 14);
		panel.add(lblNewLabel_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(105, 39, 106, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("EDITION");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(10, 74, 85, 14);
		panel.add(lblNewLabel_1_1);
		
		txtbedition = new JTextField();
		txtbedition.setColumns(10);
		txtbedition.setBounds(105, 74, 106, 20);
		panel.add(txtbedition);
		
		JLabel lblNewLabel_1_2 = new JLabel("PRICE");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(10, 109, 85, 14);
		panel.add(lblNewLabel_1_2);
		
		txtbprice = new JTextField();
		txtbprice.setColumns(10);
		txtbprice.setBounds(105, 109, 106, 20);
		panel.add(txtbprice);
		
		JLabel lblNewLabel = new JLabel("BOOKSHOP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(274, 24, 97, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String bname,edition,price;
				bname = txtbname.getText();
				edition = txtbedition.getText();
				price = txtbprice.getText();
							
				 try {
					pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
						           
					txtbname.setText("");
					txtbedition.setText("");
					txtbprice.setText("");
					txtbname.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
			        }
				
				
				
				
				
				
				
			}
		});
		
		btnNewButton.setBounds(30, 286, 73, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.setBounds(121, 286, 73, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CLEAR");
		btnNewButton_2.setBounds(212, 286, 72, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "RESULT", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(356, 70, 212, 231);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(30, 340, 260, 46);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("BOOK ID");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1.setBounds(10, 15, 85, 14);
		panel_1.add(lblNewLabel_1_1_1);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 try {
			          
			            String id = txtbid.getText();

			                pst = con.prepareStatement("select name,edition,price from book where id = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			              
			                String name = rs.getString(1);
			                String edition = rs.getString(2);
			                String price = rs.getString(3);
			                
			                txtbname.setText(name);
			                txtbedition.setText(edition);
			                txtbprice.setText(price);
			                
			                
			            }   
			            else
			            {
			            	txtbname.setText("");
			            	txtbedition.setText("");
			                txtbprice.setText("");
			                 
			            }
			            


			        } 
				
				 catch (SQLException ex) {
			           
			        }
				
				
				
				
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(105, 15, 106, 20);
		panel_1.add(txtbid);
		
		JButton btnNewButton_3 = new JButton("UPDATE");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                 String bname,edition,price,bid;
				
				
				bname = txtbname.getText();
				edition = txtbedition.getText();
				price = txtbprice.getText();
				bid  = txtbid.getText();
				
				 try {
						pst = con.prepareStatement("update book set name= ?,edition=?,price=? where id =?");
						pst.setString(1, bname);
			            pst.setString(2, edition);
			            pst.setString(3, price);
			            pst.setString(4, bid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Update!!!!!");
			            table_load();
			           
			            txtbname.setText("");
			            txtbedition.setText("");
			            txtbprice.setText("");
			            txtbname.requestFocus();
					}

		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
	
				
			
			}
		});
		btnNewButton_3.setBounds(366, 320, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("DELETE");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String bid;
				bid  = txtbid.getText();
				
				 try {
						pst = con.prepareStatement("delete from book where id =?");
				
			            pst.setString(1, bid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
			            table_load();
			           
			            txtbname.setText("");
			            txtbedition.setText("");
			            txtbprice.setText("");
			            txtbname.requestFocus();
					}

		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				
				
				
				
			}
			
		});
		btnNewButton_3_1.setBounds(479, 320, 89, 23);
		frame.getContentPane().add(btnNewButton_3_1);
	}

}
