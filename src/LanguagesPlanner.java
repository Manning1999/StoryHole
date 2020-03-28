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

	private JFrame frame;
	static JPanel wordsDefinitionPanel = new JPanel();
	JList wordList = new JList();
	JList languageList = new JList();
	
	
	static DefaultListModel languageListElements = new DefaultListModel();
	static DefaultListModel wordsInLanguageListElements = new DefaultListModel();
	private static ArrayList<Integer> languageIDList = new ArrayList<Integer>();
	static JButton newLanguageButton = new JButton("New Language");
	private JTextField wordTextField;
	

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
				ShowLanguageWords(languageList.getSelectedIndex() + 1);
				//selectedLanguage = languageList.getSelectedIndex() + 1;
			    }
			}
		});
		languageList.setBackground(SystemColor.activeCaptionBorder);
		languageList.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, SystemColor.controlShadow, SystemColor.activeCaptionBorder));
		languageList.setBounds(10, 128, 206, 456);
		frame.getContentPane().add(languageList);
	

		
		
		
		newLanguageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		wordsDefinitionPanel.add(wordList);
		
			
		
		JButton newWordButton = new JButton("New Word");
		newWordButton.setBounds(10, 45, 158, 34);
		wordsDefinitionPanel.add(newWordButton);
		
		wordTextField = new JTextField();
		wordTextField.setBounds(178, 118, 86, 20);
		wordsDefinitionPanel.add(wordTextField);
		wordTextField.setColumns(10);
		
		JTextArea definitionTextArea = new JTextArea();
		definitionTextArea.setBounds(178, 164, 278, 293);
		wordsDefinitionPanel.add(definitionTextArea);
		
		JLabel wordLabel = new JLabel("Word");
		wordLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		wordLabel.setBounds(178, 103, 60, 14);
		wordsDefinitionPanel.add(wordLabel);
		
		JLabel definitionLabel = new JLabel("Definition");
		definitionLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		definitionLabel.setBounds(178, 149, 124, 14);
		wordsDefinitionPanel.add(definitionLabel);
		
		JPanel languageDetailsPanel = new JPanel();
		languageDetailsPanel.setBackground(Color.LIGHT_GRAY);
		languageDetailsPanel.setBounds(223, 67, 290, 517);
		frame.getContentPane().add(languageDetailsPanel);
		languageDetailsPanel.setLayout(null);
		
		JLabel languageDetailsLabel = new JLabel("Language Details");
		languageDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		languageDetailsLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		languageDetailsLabel.setBounds(58, 11, 173, 19);
		languageDetailsPanel.add(languageDetailsLabel);
		
		JComboBox originLocationComboBox = new JComboBox();
		originLocationComboBox.setBounds(21, 60, 121, 27);
		languageDetailsPanel.add(originLocationComboBox);
	}
	
	
	
	private DefaultListModel ShowLanguageList() {
		ResultSet rs = null;
		
		try {
			
			
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
	      
	      
	      languageIDList.clear();
	      
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
		System.out.println("Got here " + key);
		if(key == -1) {
		//	wordsDefinitionPanel.setVisible(false);
		}
		else {
			
			ResultSet rs = null;

			try {
				
				
				wordsInLanguageListElements.removeAllElements();
				
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
		    	  //languageIDList.add(rs.getInt(1));
		    	  wordsInLanguageListElements.addElement(rs.getString(2));
		    	  System.out.println("SELECT * FROM WordInLanguage WHERE Language = (SELECT ID FROM Language WHERE ID = " + key + ")          Result: " + rs.getString(2) + "     " + wordsInLanguageListElements.size());
		      }
		      
		      
		      
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		//wordList = new JList(wordsInLanguageListElements);
		
		return wordsInLanguageListElements;
		
	}
	
	
	private void ShowLanguageDetails(int key) {
		
		
	}
	
	private void ShowWordDetails(int key) {
		
	}
	
	
	
	
}
