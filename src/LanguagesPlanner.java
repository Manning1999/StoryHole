import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class LanguagesPlanner {
	
	
	static ResultSet resultSet = null;
	private String currentStory = "'Story 1'";
	 static Connection connection = null;
     static java.sql.Statement statement = null;
     
    static int selectedLanguage = -1;

    //All the gui components that are used
	private JFrame frame;
	static JPanel wordsDefinitionPanel = new JPanel();
	JList<String> wordList = new JList<String>();
	JList<String> languageList = new JList<String>();
	JLabel lblLanguageDetails = new JLabel("Language Details:");
	JButton newWordButton = new JButton("New Word");
	JTextArea definitionTextArea = new JTextArea();
	JLabel wordLabel = new JLabel("Word");
	JLabel definitionLabel = new JLabel("Definition");
	JButton saveWordButton = new JButton("Save");
	JPanel languageDetailsPanel = new JPanel();
	JLabel languageDetailsLabel = new JLabel("Language Details");
	JComboBox<String> originLocationComboBox = new JComboBox<String>();
	JButton saveLanguageButton = new JButton("Save");
	JLabel originLocationLabel = new JLabel("Origin Location");
	JLabel lblLanguageName = new JLabel("Language Name");
	
	
	static DefaultListModel languageListElements = new DefaultListModel();
	static DefaultListModel wordsInLanguageListElements = new DefaultListModel();
	
	
	//These two lists keep track of the IDs for elements in the JLists because their ID may not be equal to their position in the JList
	/*	E.g.
	 *  JList Position : ID in Database	- language name
	 * 				  0:1 - English
	 * 				  1:3 - French
	 * 				  2:4 - Spanish
	 * 				  3:5 - Japanese
	 *				  4:8 - Mandarin
	 *
	 */
	private static ArrayList<Integer> languageIDList = new ArrayList<Integer>();
	private static ArrayList<Integer> wordIDList = new ArrayList<Integer>();
	private static ArrayList<Integer> originIDList = new ArrayList<Integer>();
	
	
	static JButton newLanguageButton = new JButton("New Language");
	private JTextField wordTextField;
	private JTextField languageNameTextField;
	JTextArea languageDetailsTextArea = new JTextArea();

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LanguagesPlanner window = new LanguagesPlanner();
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
	public LanguagesPlanner() {
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
		
		
		languageList = new JList(ShowLanguageList());
		languageList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) { 

			    if (!evt.getValueIsAdjusting()) {//This line prevents double events
			    	if(languageList.getSelectedIndex() != -1) {
					ShowLanguageWords(languageIDList.get(languageList.getSelectedIndex()));
					ShowLanguageDetails(languageIDList.get(languageList.getSelectedIndex()));
			    	}
			    
			    }
			}
		});
		languageList.setBackground(SystemColor.activeCaptionBorder);
		languageList.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, SystemColor.controlShadow, SystemColor.activeCaptionBorder));
		languageList.setBounds(10, 128, 206, 456);
		frame.getContentPane().add(languageList);
	

		
		
		
		newLanguageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewLanguage();
			}
		});
		
		newLanguageButton.setBounds(10, 67, 206, 50);
		frame.getContentPane().add(newLanguageButton);
		
		
		
		wordsDefinitionPanel.setBackground(SystemColor.activeCaptionBorder);
		wordsDefinitionPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(180, 180, 180), null, null, new Color(200, 200, 200)));
		wordsDefinitionPanel.setForeground(SystemColor.scrollbar);
		wordsDefinitionPanel.setBounds(523, 67, 474, 517);
		frame.getContentPane().add(wordsDefinitionPanel);
		wordsDefinitionPanel.setLayout(null);
		
		wordList = new JList(ShowLanguageWords(selectedLanguage));
		wordList.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, SystemColor.controlShadow, SystemColor.activeCaptionBorder));
		wordList.setBackground(Color.GRAY);
		wordList.setBounds(10, 88, 158, 369);
		wordList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) { 

			    if (!evt.getValueIsAdjusting()) {//This line prevents double events
				
			    	try {
				    	System.out.println("Word selection being set to: " + wordList.getSelectedIndex());
				    	ShowWordDetails(wordIDList.get(wordList.getSelectedIndex()));
			    	}
			    	catch(Exception e) {
			    		System.out.println("Nothing in the list");
			    	}
			    }
			}
		});
		wordsDefinitionPanel.add(wordList);
		newWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewWord();
			}
		});
		
			
		

		newWordButton.setBounds(10, 45, 158, 34);
		wordsDefinitionPanel.add(newWordButton);
		
		wordTextField = new JTextField();
		wordTextField.setBounds(178, 118, 86, 20);
		wordsDefinitionPanel.add(wordTextField);
		wordTextField.setColumns(10);
		

		definitionTextArea.setBounds(178, 164, 278, 293);
		wordsDefinitionPanel.add(definitionTextArea);
		

		wordLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		wordLabel.setBounds(178, 103, 60, 14);
		wordsDefinitionPanel.add(wordLabel);
		

		definitionLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		definitionLabel.setBounds(178, 149, 124, 14);
		wordsDefinitionPanel.add(definitionLabel);
		

		saveWordButton.setBounds(213, 483, 89, 23);

		
		
		saveWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveWordDetails();
			}
		});
		wordsDefinitionPanel.add(saveWordButton);
		

		languageDetailsPanel.setBackground(Color.LIGHT_GRAY);
		languageDetailsPanel.setBounds(223, 67, 290, 517);
		frame.getContentPane().add(languageDetailsPanel);
		languageDetailsPanel.setLayout(null);
		

		languageDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		languageDetailsLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		languageDetailsLabel.setBounds(58, 11, 173, 19);
		languageDetailsPanel.add(languageDetailsLabel);
		

		originLocationComboBox.setBounds(21, 119, 169, 29);
		languageDetailsPanel.add(originLocationComboBox);
		AddLocationsToComboBox();
		
		saveLanguageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveLanguageDetails();
			}
		});
		

		saveLanguageButton.setBounds(101, 483, 89, 23);
		languageDetailsPanel.add(saveLanguageButton);
		

		originLocationLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		originLocationLabel.setBounds(21, 97, 169, 19);
		languageDetailsPanel.add(originLocationLabel);
		
		languageNameTextField = new JTextField();
		languageNameTextField.setColumns(10);
		languageNameTextField.setBounds(21, 56, 139, 20);
		languageDetailsPanel.add(languageNameTextField);
		

		lblLanguageName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLanguageName.setBounds(21, 41, 210, 14);
		languageDetailsPanel.add(lblLanguageName);
		

		languageDetailsTextArea.setBounds(21, 193, 245, 255);
		languageDetailsPanel.add(languageDetailsTextArea);
		

		lblLanguageDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLanguageDetails.setBounds(21, 171, 169, 19);
		languageDetailsPanel.add(lblLanguageDetails);
	}
	
	
	
	private DefaultListModel ShowLanguageList() {
		ResultSet rs = null;
		
		try {
			
			languageIDList.clear();
			languageListElements.removeAllElements();
			
			 String msAccDB = "C:/Users/manni/eclipse-workspace/StoryPlanner" + "/StoryPlanner.accdb";
	         String dbURL = "jdbc:ucanaccess://" + msAccDB; 
	         
			
			// Step 2.A: Create and 
	        // get connection using DriverManager class
	        connection = DriverManager.getConnection(dbURL); 
	
	        // Step 2.B: Creating JDBC Statement 
	        statement = connection.createStatement();
	
	        // Step 2.C: Executing SQL and 
	        // retrieve data into ResultSet
	        
	        rs = statement.executeQuery("SELECT * FROM Language WHERE Story = (SELECT ID FROM Story WHERE Title = '" + "Mannings Story" + "')");
	    //  rs = statement.executeQuery("SELECT * FROM Language WHERE Story = (SELECT ID FROM Story WHERE Title = '" + Main._currentStory() + "')");
	      
	      
	      
	      
	      while(rs.next()) {
	    	  languageIDList.add(rs.getInt(1));
	    	  languageListElements.addElement(rs.getString(2));
	    	  System.out.println("SELECT * FROM Language WHERE Story = (SELECT ID FROM Story WHERE Title = '" + "Mannings Story" + "')          Result: " + rs.getString(2));
	      }
	      
		}catch(Exception e){
			System.out.println(e);
		}
				
		
		
		return languageListElements;
			
	}
	
	
	private DefaultListModel ShowLanguageWords(int key) {
		
		wordIDList.clear();
		wordsInLanguageListElements.removeAllElements();
		
		if(key == -1) {
		//	wordsDefinitionPanel.setVisible(false);
		}
		else {
			
			ResultSet rs = null;

			try {
				
				
				
				//Step 1: Define the location of the database
				 String msAccDB = "C:/Users/manni/eclipse-workspace/StoryPlanner" + "/StoryPlanner.accdb";
		         String dbURL = "jdbc:ucanaccess://" + msAccDB; 
		         
				
				// Step 2.A: Create and 
		        // get connection using DriverManager class
		        connection = DriverManager.getConnection(dbURL); 
		
		        // Step 2.B: Creating JDBC Statement 
		        statement = connection.createStatement();
		
		        // Step 2.C: Executing SQL and 
		        // retrieve data into ResultSet
		        
		        rs = statement.executeQuery("SELECT * FROM WordInLanguage WHERE Language = (SELECT ID FROM Language WHERE ID = " + key + ")");
		    //  rs = statement.executeQuery("SELECT * FROM Language WHERE Story = (SELECT ID FROM Story WHERE Title = '" + Main._currentStory() + "')");
		      
		     //   System.out.println(Main.CountRowsInTableWithConditions("WordInLanguage", "Language", "1"));
		      
		       
		      
		      
		      while(rs.next()) {
		    	  wordIDList.add(rs.getInt(1));
		    	  wordsInLanguageListElements.addElement(rs.getString(2));
		    	  System.out.println("SELECT * FROM WordInLanguage WHERE Language = (SELECT ID FROM Language WHERE ID = " + key + ")          Result: " + rs.getString(2) + "     " + wordsInLanguageListElements.size());
		      }
		      
		      
		      
			}catch(Exception e){
				System.out.println("Problem here: " + e + key);
			}
		}
		
		//wordList = new JList(wordsInLanguageListElements);
		
		return wordsInLanguageListElements;
		
	}
	
	
	private void AddLocationsToComboBox() {
		
		originIDList.clear();
		originLocationComboBox.removeAllItems();
		originLocationComboBox.addItem("Choose Location");
		 originIDList.add(-1);
		ResultSet rs = null;

		try {
			
			
			
			//Step 1: Define the location of the database
			 String msAccDB = "C:/Users/manni/eclipse-workspace/StoryPlanner" + "/StoryPlanner.accdb";
	         String dbURL = "jdbc:ucanaccess://" + msAccDB; 
	         
			
			// Step 2.A: Create and 
	        // get connection using DriverManager class
	        connection = DriverManager.getConnection(dbURL); 
	
	        // Step 2.B: Creating JDBC Statement 
	        statement = connection.createStatement();
	
	        // Step 2.C: Executing SQL and 
	        // retrieve data into ResultSet
	        
	        rs = statement.executeQuery("SELECT * FROM Location WHERE Story = " + 10);
	    
	       
	      
	      
	      while(rs.next()) {
	    	  System.out.println(rs.getInt(1) + " : " + rs.getString(2));
	    	  try {
	    	  originIDList.add(rs.getInt(1));
	    	  originLocationComboBox.addItem(rs.getString(2));
	    	  }
	    	  catch(Exception e) {
	    		  
	    	  }
	    	 
	      }
	      
	      
	      
		}catch(Exception e){
			System.out.println("Problem getting locations " + e);
		}
		
	}
	
	
	
	
	
	//-1 resets the relevant fields
	private void ShowLanguageDetails(int key) {
		
		ShowWordDetails(-1);
		try {
			if(key == -1) {
				
				try {
					
					languageDetailsTextArea.setText("");
					languageNameTextField.setText("");
				}
				catch(Exception e) {
					System.out.println("Something not working " + e);
				}
				
			}
			else {
				
				try {
					languageDetailsTextArea.setText(Main.SearchDatabase("Details", "Language", "ID", Integer.toString(key)));
					languageNameTextField.setText(Main.SearchDatabase("LanguageName", "Language", "ID", Integer.toString(key)));
					int languageOrigin = Integer.parseInt(Main.SearchDatabase("Origin", "Language", "ID", Integer.toString(key)));
					if(languageOrigin == -1) languageOrigin = 0;
					originLocationComboBox.setSelectedIndex(languageOrigin);
				}
				catch(Exception e) {
					System.out.println("Didn't work" + e);
				}
				
			}
		}
		catch(Exception e) {
			System.out.println("Error in language details: " + e);
		}
	}
	
	
	
	//-1 resets the relevant fields
	private void ShowWordDetails(int key) {
		System.out.println("Got here");
		try {
		if(key == -1) {
			wordList.removeAll();
			definitionTextArea.setText("");
			wordTextField.setText("");
			wordList.clearSelection();
		}
		else {
			
	
			definitionTextArea.setText(Main.SearchDatabase("Definition", "WordInLanguage", "ID", Integer.toString(key)));
			wordTextField.setText(Main.SearchDatabase("Word", "WordInLanguage", "ID", Integer.toString(key)));
		}
		}
		catch(Exception e) {
			System.out.println("Error int word details: " + e);
		}
		
		
	}
	
	private void NewLanguage() {
		languageList.clearSelection();
		languageDetailsTextArea.setText("");
		languageNameTextField.setText("");
		originLocationComboBox.setSelectedIndex(0);
	}
	
	private void NewWord() {
		definitionTextArea.setText("");
		wordTextField.setText("");
		wordList.clearSelection();
	}
	
	
	
	
	private void SaveLanguageDetails() {
		
		//Determine whether a new language is being created or a language is being updated
		if(languageList.getSelectedIndex() != -1) {
			Main.ExecuteSQLStatement("UPDATE Language SET LanguageName = '" + languageNameTextField.getText() + "', Origin = '" + originLocationComboBox.getSelectedIndex() + "', Story = '" + 10 + "', Details = '" + languageDetailsTextArea.getText() +"' WHERE ID = " + languageIDList.get(languageList.getSelectedIndex())+";");
			System.out.println("UPDATE Language SET LanguageName = '" + languageNameTextField.getText() + "', Origin = '" + originLocationComboBox.getSelectedIndex() + "', Story = '" + 10 + "', Details = '" + languageDetailsTextArea.getText() +"' WHERE ID = " + languageIDList.get(languageList.getSelectedIndex())+";");
		
		
		}
		else {
			
			Main.ExecuteSQLStatement("INSERT INTO Language(LanguageName, Origin, Story, Details) VALUES('" + languageNameTextField.getText() + "', " + originLocationComboBox.getSelectedIndex() + ",'" + 10 + "', '" + languageDetailsTextArea.getText() + "');");
			System.out.println("INSERT INTO Language(LanguageName, Origin, Story, Details) VALUES('" + languageNameTextField.getText() + "', '" + originLocationComboBox.getSelectedIndex() + "','" + 10 + "', '" + languageDetailsTextArea.getText() + "');");
			
		}
		ShowLanguageList();
	}
	
	
	
	
	private void SaveWordDetails() {
		//Determine whether a new word is being created or a word is being update
		if(wordList.getSelectedIndex() != -1) {
			Main.ExecuteSQLStatement("UPDATE WordInLanguage Set Word = '" + wordTextField.getText() + "', Definition = '" + wordTextField.getText() + "', Language = '" + languageIDList.get(languageList.getSelectedIndex())+";");
		}
		else {
			Main.ExecuteSQLStatement("INSERT INTO WordInLanguage(Word, Definition, Language) VALUES('" + wordTextField.getText() + "','" + wordTextField.getText() + "','" + languageIDList.get(languageList.getSelectedIndex())+"');");
		}
		ShowLanguageWords(languageIDList.get(languageList.getSelectedIndex()));
	}
}
