
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
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
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JPanel;
import com.mysql.cj.xdevapi.Statement;
import javax.swing.JTextPane;


public class Main {

	private JFrame frame;
	JPanel panel = new JPanel();

	
	
	//Main Menu Components
	JScrollPane scrollPane = new JScrollPane();
	JTextPane descriptionLabel = new JTextPane();
	JButton newStoryButton = new JButton("New Story");
	JButton editStoryButton = new JButton("Edit Story");
	
	
	
	
	 Connection connection = null;
     java.sql.Statement statement = null;
	
	ResultSet resultSet = null;
	
	
	
	Boolean mainMenuIsCreated = false;

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
	 */
	public Main() {
		initialize();
		
		 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1023, 634);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setMinimumSize(new Dimension(1023, 634));
		
		
		SetMainMenu(true);
		
		
		LoadDatabase();
	}
	
	
	private void SetMainMenu(Boolean set) {
		
		
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
			}
		});
		newStoryButton.setBounds(10, 25, 291, 50);
		frame.getContentPane().add(newStoryButton);

		editStoryButton.setBounds(633, 542, 145, 50);
		frame.getContentPane().add(editStoryButton);

		descriptionLabel.setBounds(413, 92, 571, 439);
		frame.getContentPane().add(descriptionLabel);
		
		frame.addComponentListener(new ComponentAdapter() {
			   public void componentResized(ComponentEvent componentEvent) {
				   
				   for(Component component : frame.getComponents()){
					   
				   
				   descriptionLabel.setLocation(frame.getWidth()*1/4, frame.getHeight()*1/4);
				   descriptionLabel.setSize(frame.getWidth()*1/2, frame.getHeight()*1/2);
				   }
			   }
				   
			});
		
		}
		else {
			scrollPane.setVisible(set);
			descriptionLabel.setVisible(set);
			newStoryButton.setVisible(set);
			editStoryButton.setVisible(set);
			
		}
	}
	
	
	private void SetNewStoryMenu(Boolean set) {
		
		
	}
	
	
	
	private void SetEditStoryOptionsMenu(Boolean set) {
		
	}
	
	private void ShowDescription(int key) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(SearchDatabase("CreationDate", "Story", "ID", Integer.toString(key + 1)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		descriptionLabel.setText("Date Created: " + formatter.format(date) + " \r\n\r\n" + SearchDatabase("Synopsis", "Story", "ID", Integer.toString(key + 1)));
		
		
	}
	
	
	private void LoadDatabase() {
		
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
            // processing returned data and printing into console
            while(resultSet.next()) {
            	
                System.out.println(resultSet.getInt(1) + "\t" + 
                        resultSet.getString(2) + "\t" + 
                        resultSet.getString(3) + "\t" +
                        resultSet.getString(4));
                
                final int key = i;
                
                JButton storyButton = new JButton(resultSet.getString(2));
        		storyButton.setBounds(10, 11 + (i * 60), 269, 43);
        		storyButton.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				
							ShowDescription(key);
						
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
	
	
	private String SearchDatabase(String fieldToRetrieve, String table, String valueToCompare, String fieldToCompare) {
		
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

}

