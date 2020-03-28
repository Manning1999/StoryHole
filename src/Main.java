//Description:
//This class is used for everything 

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JPanel;
import com.mysql.cj.xdevapi.Statement;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;


public class Main {
	
	
	
	private static String currentStory;
	public static String _currentStory() {return currentStory;}
	
	private static DefaultListModel characterListElements = new DefaultListModel();
	private ArrayList<Integer> characterIDList = new ArrayList<Integer>();
	

	private static JFrame frame;
	public static JFrame _frame() {return frame;}
	
	static JPanel panel = new JPanel();
	public static JPanel _panel() {return panel;}

	
	

	static JScrollPane scrollPane = new JScrollPane();
	static JTextPane descriptionLabel = new JTextPane();
	static JButton newStoryButton = new JButton("New Story");
	static JButton editStoryButton = new JButton("Edit Story");
	

	 static Connection connection = null;
     static java.sql.Statement statement = null;
	
	static ResultSet resultSet = null;
	
	
	
	static Boolean mainMenuIsCreated = false;


	Boolean storyMenuIsCreated = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Main() throws SQLException {
		initialize();
		
		 
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 1023, 634);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setMinimumSize(new Dimension(1023, 634));
		
		
		SetMainMenu(true);
		

		LoadStoriesFromDatabase();

		scrollPane.setBounds(10, 86, 291, 498);
		frame.getContentPane().add(scrollPane);
		
		
		scrollPane.setViewportView(panel);
		panel.setBorder(BorderFactory.createLineBorder(new Color(220,220,220), 5));
		panel.setBackground(new Color(211,211,211));
		panel.setLayout(null);

		newStoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NewStory.SetNewStoryMenu(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		newStoryButton.setBounds(10, 25, 291, 50);
		frame.getContentPane().add(newStoryButton);

		editStoryButton.setBounds(633, 542, 145, 50);
		frame.getContentPane().add(editStoryButton);
		editStoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					StoryMenu.SetStoryMenu(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		descriptionLabel.setBounds(413, 92, 571, 439);
		frame.getContentPane().add(descriptionLabel);
		
		
	}
	
	
	
	
	public static void SetMainMenu(Boolean set) throws SQLException {
		
		
		if(mainMenuIsCreated == false) {
			
			mainMenuIsCreated = true;
			
			
			scrollPane.setBounds(10, 86, 291, 498);
			frame.getContentPane().add(scrollPane);
			
			
			scrollPane.setViewportView(panel);
			panel.setBorder(BorderFactory.createLineBorder(new Color(220,220,220), 5));
			panel.setBackground(new Color(211,211,211));
			panel.setLayout(null);
	
			newStoryButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						NewStory.SetNewStoryMenu(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			
			newStoryButton.setBounds(10, 25, 291, 50);
			frame.getContentPane().add(newStoryButton);
	
			editStoryButton.setBounds(633, 542, 145, 50);
			frame.getContentPane().add(editStoryButton);
			editStoryButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						StoryMenu.SetStoryMenu(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
	
			descriptionLabel.setBounds(413, 92, 571, 439);
			frame.getContentPane().add(descriptionLabel);
			
			/*frame.addComponentListener(new ComponentAdapter() {
				   public void componentResized(ComponentEvent componentEvent) {
					   
					   for(Component component : frame.getComponents()){
						   
					   
						   descriptionLabel.setLocation(frame.getWidth()*1/4, frame.getHeight()*1/4);
						   descriptionLabel.setSize(frame.getWidth()*1/2, frame.getHeight()*1/2);
					   }
				   }
					   
				});*/
			
			}
			else {
				
				if(set==true) {
					NewStory.SetNewStoryMenu(false);
					CharacterPlanningMenu.SetCharacterMenu(false);
					StoryMenu.SetStoryMenu(false);
					LoadStoriesFromDatabase();
				}
				
				scrollPane.setVisible(set);
				descriptionLabel.setVisible(set);
				newStoryButton.setVisible(set);
				editStoryButton.setVisible(set);
				
			}
	}
	
	
	
	
	
	
	
	
	
	
	
	//This updates the character information screen to display the relavent information for whatever character is selected in the Character editing menu
	
	
	//Counts the number of rows in a table that fit a given condition
	public static int CountRowsInTableWithConditions(String table, String field, String condition) {
		
		int result = 0;
		ResultSet rs;
		
		try {
			
			 String msAccDB = "C:/Users/manni/eclipse-workspace/StoryPlanner" + "/StoryPlanner.accdb";
	         String dbURL = "jdbc:ucanaccess://" + msAccDB; 
	         
			
			// Step 2.A: Create and 
	        // get connection using DriverManager class
	        connection = DriverManager.getConnection(dbURL); 
	
	        // Step 2.B: Creating JDBC Statement 
	        statement = connection.createStatement();
	
	        // Step 2.C: Executing SQL and 
	        // retrieve data into ResultSet
	        
	       // ("Character", "Story", "(SELECT ID FROM Story WHERE Title = " + currentStory + ")")
	        System.out.println("SELECT COUNT(*) FROM " + table + " WHERE " + field + " = " + condition);
	      rs = statement.executeQuery("SELECT COUNT(*) FROM " + table + " WHERE " + field + " = " + condition);
	      
	      rs.next();
	      result = rs.getInt(1);
	      System.out.println(result);
	       
	       statement.close();
	       connection.close();
	       
	       System.out.println("Finished counting rows with condition");
		}
		catch(Exception e) {
			
			System.out.println("Failed to count rows with condition:: " + e);
			
		}
		
		
		
		return result;
	}
	
	//A generic function that will perform any SQL statetement it is given but does nto return any values
	public static void ExecuteSQLStatement(String newStatement) {
		
			
			try {
			
				 String msAccDB = "C:/Users/manni/eclipse-workspace/StoryPlanner" + "/StoryPlanner.accdb";
		         String dbURL = "jdbc:ucanaccess://" + msAccDB; 
		         
				
				// Step 2.A: Create and 
		        // get connection using DriverManager class
		        connection = DriverManager.getConnection(dbURL); 
		
		        // Step 2.B: Creating JDBC Statement 
		        statement = connection.createStatement();
		
		        // Step 2.C: Executing SQL and 
		        // retrieve data into ResultSet
		        
		      
		       statement.execute(newStatement);
		       
		       statement.close();
		       connection.close();
			}
			catch(Exception e) {
				
				System.out.println("Failed to execute statement: " + e);
				
			}
			
			System.out.println("Finished executing statement");
			
		       
	}
	
	//Counts the number of rows in a table without any conditions
	public static int CountRowsInTable(String table) {
		
		int result = 0;
		ResultSet rs;
		
		try {
			
			 String msAccDB = "C:/Users/manni/eclipse-workspace/StoryPlanner" + "/StoryPlanner.accdb";
	         String dbURL = "jdbc:ucanaccess://" + msAccDB; 
	         
			
			// Step 2.A: Create and 
	        // get connection using DriverManager class
	        connection = DriverManager.getConnection(dbURL); 
	
	        // Step 2.B: Creating JDBC Statement 
	        statement = connection.createStatement();
	
	        // Step 2.C: Executing SQL and 
	        // retrieve data into ResultSet
	        
	      rs = statement.executeQuery("Select * from " + table);
	      rs = statement.executeQuery("SELECT COUNT(*) FROM " + table);
	      
	      rs.next();
	      result = rs.getInt(1);
	       
	       statement.close();
	       connection.close();
		}
		catch(Exception e) {
			
			System.out.println("Failed to execute statement: " + e);
			
		}
		
		System.out.println("Finished executing statement");
		
		return result;
	}
	
	
	
	
	//Shows the description of the selected story on the main menu
	public static void ShowDescription(int key) {
		
		System.out.println("Showing description for " + key);
		
		try {
		SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss a yyyy");
		String date = null;
		date = SearchDatabase("CreationDate", "Story", "ID", Integer.toString(key));
		
		
		
		
		descriptionLabel.setText("Date Created: " + date + " \r\n\r\n" + SearchDatabase("Synopsis", "Story", "ID", Integer.toString(key)));
		//storyTitleLabel.setText(SearchDatabase("Title", "Story", "ID", Integer.toString(key + 1));
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	
	private static void LoadStoriesFromDatabase() {
		
		 // variables
       
        
 
        // Step 1: Loading or 
        // registering Oracle JDBC driver class
        try {
 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) {
 
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }
 

        // Step 2: Opening database connection
        try {
 
            String msAccDB = "C:/Users/manni/eclipse-workspace/StoryPlanner"
                    + "/StoryPlanner.accdb";
            String dbURL = "jdbc:ucanaccess://"
                    + msAccDB; 
            

 
            // Step 2.A: Create and 
            // get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL); 

            // Step 2.B: Creating JDBC Statement 
            statement = connection.createStatement();

            // Step 2.C: Executing SQL and 
            // retrieve data into ResultSet
            resultSet = ((java.sql.Statement) statement).executeQuery("SELECT * FROM Story");

            

            int i = 0;
            // Iterate through the resultset and create a button for each story in the resultset
            
            while(resultSet.next()) {
            	
                System.out.println(resultSet.getInt(1) + "\t" + 
                        resultSet.getString(2) + "\t" + 
                        resultSet.getString(3) + "\t" +
                        resultSet.getString(4));
                
                final int key = resultSet.getInt(1);
                
                JButton storyButton = new JButton(resultSet.getString(2));
        		storyButton.setBounds(10, 11 + (i * 60), 269, 43);
        		
        		//When the button is clicked, show it's description and set the story currently being looked at or edited to that button
        		storyButton.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				
							ShowDescription(key);

							currentStory = SearchDatabase("Title", "Story", "ID", Integer.toString(key));
        			}
        		});
        		panel.add(storyButton);
        		i++;
            }
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
            System.out.println("failed to read database");
        }
        
    }
	
	
	private void CloseDatabase() {
		
		

            // Step 3: Closing database connection
            try {
                if(null != connection) {
                    // cleanup resources, once after processing
                    resultSet.close();
                    statement.close();
 
                    // and then finally close connection
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
	}
	
	
	//Searches for a field in the specified table that matches a the given paramaters
	public static String SearchDatabase(String fieldToRetrieve, String table, String valueToCompare, String fieldToCompare) {

		
		
		
		String value = null;
		
		try {
		
			 String msAccDB = "C:/Users/manni/eclipse-workspace/StoryPlanner"
	                 + "/StoryPlanner.accdb";
	         String dbURL = "jdbc:ucanaccess://"
	                 + msAccDB; 
	         
			
			// Step 2.A: Create and 
	        // get connection using DriverManager class
	        connection = DriverManager.getConnection(dbURL); 
	
	        // Step 2.B: Creating JDBC Statement 
	        statement = connection.createStatement();
	
	        // Step 2.C: Executing SQL and 
	        // retrieve data into ResultSet
	        
	        if(valueToCompare == null || fieldToCompare == null) {
	        	return null;
	        }
	        else {
	        	resultSet = ((java.sql.Statement) statement).executeQuery("SELECT " + fieldToRetrieve + " FROM " + table + " WHERE " + fieldToCompare + " = " + valueToCompare);
	        }
	
	        
	
	        // processing returned data and printing into console
	        while(resultSet.next()) {
	        	
	            value = resultSet.getString(1);
	            
	        }
		}
		catch(Exception e) {
			
		}
		
		
		return value;
		
	}
	
	
	
	
	

	


	private void ShowCategories() {
		
	}
	
	
	
	
}

