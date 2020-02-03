import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class CharacterPlanningMenu {
	
	ResultSet resultSet = null;
	private String currentStory = "'Story 2'";
	 Connection connection = null;
     java.sql.Statement statement = null;
	
     DefaultListModel characterListElements = new DefaultListModel();

	private JFrame frame;
	private JTextField nameTextField;
	private JTextField ageTextField;
	private JTextField raceNationalityTextField;
	private JTextField genderTextField;
	JTextPane txtpnYouHaveNot = new JTextPane();

	JButton newCharacterButton = new JButton("New Character");
	JPanel characterPanel = new JPanel();
	JLabel ageLabel = new JLabel("Age:");
	JButton saveChangesButton = new JButton("Save Changes");
	JLabel raceNationalityLabel = new JLabel("Race/Nationality:");
	JTextArea appearanceTextArea = new JTextArea();
	JLabel appearanceLabel = new JLabel("Appearance");
	JLabel personalityLabel = new JLabel("Personality");
	JTextArea personalityTextArea = new JTextArea();
	JLabel lblGendersex = new JLabel("Gender/Sex:");
	private final JList friendsList = new JList();
	private final JButton viewCharacter = new JButton("View");
	private final JButton removeButton = new JButton("-");
	private final JButton btnNewButton = new JButton("Add Friend");
	private final JList list = new JList();
	private final JButton button = new JButton("View");
	private final JButton button_1 = new JButton("-");
	private final JButton btnAddEnemy = new JButton("Add Enemy");
	private final JLabel affiliationLabel = new JLabel("Affiliation:");
	private final JTextField affiliationTextField = new JTextField();
	
	
	
	private ArrayList<Integer> characterIDList = new ArrayList<Integer>();
	private final JButton characterMenuBackButton = new JButton("Back");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CharacterPlanningMenu window = new CharacterPlanningMenu();
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
	public CharacterPlanningMenu() throws SQLException {
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
		

		if(CountRowsInTableWithConditions("Character", "Story", "(SELECT ID FROM Story WHERE Title = " + currentStory + ")") == 0) {
			
			txtpnYouHaveNot.setBackground(SystemColor.controlShadow);
			txtpnYouHaveNot.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			txtpnYouHaveNot.setText("You have not created any characters yet");
			txtpnYouHaveNot.setBounds(17, 141, 195, 70);
			frame.getContentPane().add(txtpnYouHaveNot);
			
		}
		
		JList characterList = new JList(ShowCharacterList());
		characterList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ShowCharacterDetails(characterList.getSelectedIndex());
			}
		});
		characterList.setBackground(SystemColor.activeCaptionBorder);
		characterList.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, SystemColor.controlShadow, SystemColor.activeCaptionBorder));
		characterList.setBounds(10, 128, 206, 456);
		frame.getContentPane().add(characterList);
		
		
		
		newCharacterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameTextField.setText("Name");
				ageTextField.setText("");
				raceNationalityTextField.setText("");
				appearanceTextArea.setText("");
				genderTextField.setText("");
			}
		});
		

		newCharacterButton.setBounds(10, 67, 206, 50);
		frame.getContentPane().add(newCharacterButton);
		

		characterPanel.setBackground(SystemColor.activeCaptionBorder);
		characterPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(180, 180, 180), null, null, new Color(200, 200, 200)));
		characterPanel.setForeground(SystemColor.scrollbar);
		characterPanel.setBounds(236, 67, 761, 484);
		frame.getContentPane().add(characterPanel);
		characterPanel.setLayout(null);
		saveChangesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					ExecuteSQLStatement("INSERT INTO Character(ID, CharacterName, Age, RaceOrNationality, DOB, Appearance, Personality, Story, Gender, Affiliation) VALUES(" + 3 + ",'" + nameTextField.getText() + "','" + ageTextField.getText() + "','" + raceNationalityTextField.getText() + "','DOB','" + appearanceTextArea.getText() + "','" + personalityTextArea.getText() + "'," + 2 + ",'"+ genderTextField.getText() + "','" + affiliationTextField.getText() +"')");
					try {
						ShowCharacterList();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
			}
		});
		

		saveChangesButton.setBounds(321, 450, 101, 23);
		characterPanel.add(saveChangesButton);
		
		nameTextField = new JTextField();
		nameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		nameTextField.setText("Name");
		nameTextField.setBounds(250, 11, 228, 40);
		characterPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		ageTextField = new JTextField();
		ageTextField.setBounds(178, 77, 86, 20);
		characterPanel.add(ageTextField);
		ageTextField.setColumns(10);
		

		ageLabel.setBounds(150, 80, 31, 14);
		characterPanel.add(ageLabel);
		

		raceNationalityLabel.setBounds(82, 124, 126, 14);
		characterPanel.add(raceNationalityLabel);
		
		raceNationalityTextField = new JTextField();
		raceNationalityTextField.setColumns(10);
		raceNationalityTextField.setBounds(178, 121, 86, 20);
		characterPanel.add(raceNationalityTextField);
		

		appearanceTextArea.setBounds(10, 199, 176, 156);
		characterPanel.add(appearanceTextArea);
		

		appearanceLabel.setBounds(69, 178, 66, 14);
		characterPanel.add(appearanceLabel);
		

		personalityLabel.setBounds(280, 178, 66, 14);
		characterPanel.add(personalityLabel);
		

		personalityTextArea.setBounds(221, 199, 176, 156);
		characterPanel.add(personalityTextArea);
		

		lblGendersex.setBounds(382, 80, 83, 14);
		characterPanel.add(lblGendersex);
		
		genderTextField = new JTextField();
		genderTextField.setColumns(10);
		genderTextField.setBounds(454, 80, 86, 20);
		characterPanel.add(genderTextField);
		friendsList.setBounds(474, 203, 113, 156);
		
		characterPanel.add(friendsList);
		viewCharacter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		viewCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		viewCharacter.setBounds(474, 360, 66, 23);
		
		characterPanel.add(viewCharacter);
		removeButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		removeButton.setBounds(539, 360, 48, 23);
		
		characterPanel.add(removeButton);
		btnNewButton.setBounds(474, 178, 113, 23);
		
		characterPanel.add(btnNewButton);
		list.setBounds(613, 203, 113, 156);
		
		characterPanel.add(list);
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(613, 360, 66, 23);
		
		characterPanel.add(button);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		button_1.setBounds(678, 360, 48, 23);
		
		characterPanel.add(button_1);
		btnAddEnemy.setBounds(613, 178, 113, 23);
		
		characterPanel.add(btnAddEnemy);
		affiliationLabel.setBounds(397, 127, 56, 14);
		
		characterPanel.add(affiliationLabel);
		affiliationTextField.setColumns(10);
		affiliationTextField.setBounds(454, 121, 86, 20);
		
		characterPanel.add(affiliationTextField);
		
		characterPanel.add(list);
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(613, 360, 66, 23);
		
		characterPanel.add(button);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		button_1.setBounds(678, 360, 48, 23);
		
		characterPanel.add(button_1);
		btnAddEnemy.setBounds(613, 178, 113, 23);
		
		characterPanel.add(btnAddEnemy);
		affiliationLabel.setBounds(397, 127, 56, 14);
		
		characterPanel.add(affiliationLabel);
		affiliationTextField.setColumns(10);
		affiliationTextField.setBounds(454, 121, 86, 20);
		
		characterPanel.add(affiliationTextField);
		characterMenuBackButton.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(characterMenuBackButton);
		characterMenuBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
	}
	
	
	private DefaultListModel ShowCharacterList() throws SQLException {
	
		

	
		ResultSet rs = null;

		try {
			
			
			characterListElements.removeAllElements();
			
			 String msAccDB = "C:/Users/manni/eclipse-workspace/StoryPlanner" + "/StoryPlanner.accdb";
	         String dbURL = "jdbc:ucanaccess://" + msAccDB; 
	         
			
			// Step 2.A: Create and 
	        // get connection using DriverManager class
	        connection = DriverManager.getConnection(dbURL); 
	
	        // Step 2.B: Creating JDBC Statement 
	        statement = connection.createStatement();
	
	        // Step 2.C: Executing SQL and 
	        // retrieve data into ResultSet
	        
	      rs = statement.executeQuery("SELECT * FROM Character WHERE Story = (SELECT ID FROM Story WHERE Title = " + currentStory + ")");
	      
	      
	      characterIDList.clear();
	      
	      while(rs.next()) {
	    	  characterIDList.add(rs.getInt(1));
	    	  characterListElements.addElement(rs.getString(2));
	    	  System.out.println("SELECT * FROM Character WHERE Story = (SELECT ID FROM Story WHERE Title = '" + currentStory + "')          Result: " + rs.getString(2));
	      }
	      
		}catch(Exception e){
			System.out.println(e);
		}
				
		
		return characterListElements;
		
		
	}
	
	private void ShowCharacterDetails(int key) {
		
		System.out.println(key + 1);
		key += 1;
		nameTextField.setText(SearchDatabase("CharacterName", "Character", Integer.toString(key), "ID"));
		ageTextField.setText(SearchDatabase("Age", "Character", Integer.toString(key), "ID"));
		raceNationalityTextField.setText(SearchDatabase("RaceOrNationality", "Character", Integer.toString(key), "ID"));
		appearanceTextArea.setText(SearchDatabase("Appearance", "Character", Integer.toString(key), "ID"));
		genderTextField.setText(SearchDatabase("Gender", "Character", Integer.toString(key), "ID"));
		personalityTextArea.setText(SearchDatabase("Personality", "Character", Integer.toString(key), "ID"));
		
	}
	
	
	private int CountRowsInTable(String table) {
		
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
	
	
	
	
	
	private int CountRowsInTableWithConditions(String table, String field, String condition) {
		
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



	
	
	private void ExecuteSQLStatement(String newStatement) {
		
		System.out.println(newStatement);
		
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
	        
	     //   SearchDatabase("*", "Characters", "Story", Integer.toString(i)
	        
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
