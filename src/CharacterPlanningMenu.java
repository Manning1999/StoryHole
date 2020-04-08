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

public class CharacterPlanningMenu extends Object{
	
	static ResultSet resultSet = null;
	//private String currentStory = "'Story 2'";
	 static Connection connection = null;
     static java.sql.Statement statement = null;
	
     static DefaultListModel characterListElements = new DefaultListModel();

	private static JFrame frame;
	
	private static final JButton characterMenuBackButton = new JButton("Back");
	private static JTextField nameTextField;
	private static JTextField ageTextField;
	private static JTextField raceNationalityTextField;
	private static JTextField genderTextField;
	static JTextPane txtpnYouHaveNot = new JTextPane();
	static JButton newCharacterButton = new JButton("New Character");
	static JPanel characterPanel = new JPanel();
	static JLabel ageLabel = new JLabel("Age:");
	static JButton saveChangesButton = new JButton("Save Changes");
	static JLabel raceNationalityLabel = new JLabel("Race/Nationality:");
	static JTextArea appearanceTextArea = new JTextArea();
	static JLabel appearanceLabel = new JLabel("Appearance");
	static JLabel personalityLabel = new JLabel("Personality");
	static JTextArea personalityTextArea = new JTextArea();
	static JLabel lblGendersex = new JLabel("Gender/Sex:");
	private static final JList friendsList = new JList();
	private static final JList enemyList = new JList();
	private static final JButton viewCharacter = new JButton("View");
	private static final JButton removeButton = new JButton("-");
	private static final JButton btnNewButton = new JButton("Add Friend");
	private static JList characterList = new JList();
	private static final JButton button = new JButton("View");
	private static final JButton button_1 = new JButton("-");
	private static final JButton btnAddEnemy = new JButton("Add Enemy");
	private static final JLabel affiliationLabel = new JLabel("Affiliation:");
	private static final JTextField affiliationTextField = new JTextField();
	
	
	
	
	private static ArrayList<Integer> characterIDList = new ArrayList<Integer>();

	
	static Boolean characterMenuIsCreated = false;
	private Boolean isAddingFriend = false;
	private Boolean isAddingEnemy = false;
	

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
		

		if(Main.CountRowsInTableWithConditions("Character", "Story", "(SELECT ID FROM Story WHERE Title = " + Main._currentStory() + ")") == 0) {
			
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
				isAddingEnemy = false;
				isAddingFriend = false;
				
				nameTextField.setText("Name");
				ageTextField.setText("");
				raceNationalityTextField.setText("");
				appearanceTextArea.setText("");
				genderTextField.setText("");
				affiliationTextField.setText("");
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

					Main.ExecuteSQLStatement("INSERT INTO Character(ID, CharacterName, Age, RaceOrNationality, DOB, Appearance, Personality, Story, Gender, Affiliation) VALUES(" + 3 + ",'" + nameTextField.getText() + "','" + ageTextField.getText() + "','" + raceNationalityTextField.getText() + "','DOB','" + appearanceTextArea.getText() + "','" + personalityTextArea.getText() + "'," + 2 + ",'"+ genderTextField.getText() + "','" + affiliationTextField.getText() +"')");
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
		
		enemyList.setBounds(613, 203, 113, 156);	
		characterPanel.add(enemyList);
		
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
		
		characterPanel.add(characterMenuBackButton);
		characterMenuBackButton.setBounds(10, 11, 89, 23);
		//frame.getContentPane().add(characterMenuBackButton);
		
		
		
		
		
	}
	
	
	//Shows or hides the character editing menu
		public static void SetCharacterMenu(Boolean set) throws SQLException {
			
				frame = Main._frame();
			
				//show/hide menu items accordingly
				if(characterMenuIsCreated == true) {
					
					nameTextField.setVisible(set);
					txtpnYouHaveNot.setVisible(set);
					characterList.setVisible(set);
					newCharacterButton.setVisible(set);
					ageTextField.setVisible(set);
					raceNationalityTextField.setVisible(set);
					appearanceTextArea.setVisible(set);
					genderTextField.setVisible(set);
					characterList.clearSelection();
					characterPanel.setVisible(set);
					ageLabel.setVisible(set);
					appearanceLabel.setVisible(set);
					personalityLabel.setVisible(set);
					removeButton.setVisible(set);
					affiliationTextField.setVisible(set);
					affiliationLabel.setVisible(set);
					characterMenuBackButton.setVisible(set);
					ShowCharacterList();
					
					if(set == true) {
						//Disable the main menu items
						StoryMenu.SetStoryMenu(false);
					}
					else {
						txtpnYouHaveNot.setVisible(false);
					}
					
					
				} //if this is the first time going to the character editing menu then create all the menu items
				else {
				
					if(set == true) {
						
						StoryMenu.SetStoryMenu(false);
						
						characterMenuIsCreated = true;
						
						
						//Show a message saying no characters have been created if there are no characters associated with the story
						if(Main.CountRowsInTableWithConditions("Character", "Story", "(SELECT ID FROM Story WHERE Title = '" + Main._currentStory() + "')") == 0) {
							txtpnYouHaveNot.setBackground(SystemColor.controlShadow);
							txtpnYouHaveNot.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
							txtpnYouHaveNot.setText("You have not created any characters yet");
							txtpnYouHaveNot.setBounds(17, 141, 195, 70);
							frame.getContentPane().add(txtpnYouHaveNot);
						}

						
						//If a character/element in the JList is clicked then update the character details to show the details of the clicked character
						characterList = new JList(ShowCharacterList());
						characterList.addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent e) {
								ShowCharacterDetails(characterList.getSelectedIndex());
								characterList.setSelectedIndex(characterList.getSelectedIndex());
							}
						});
						
						characterList.setBackground(SystemColor.activeCaptionBorder);
						characterList.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, SystemColor.controlShadow, SystemColor.activeCaptionBorder));
						characterList.setBounds(10, 128, 206, 456);
						frame.getContentPane().add(characterList);
						
						
						
						
						characterMenuBackButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									StoryMenu.SetStoryMenu(true);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
						characterMenuBackButton.setBounds(10, 20, 89, 23);
						
						frame.getContentPane().add(characterMenuBackButton);
						characterMenuBackButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									Main.SetMainMenu(true);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
						
						
						
						
						
						
						newCharacterButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								nameTextField.setText("Name");
								ageTextField.setText("");
								raceNationalityTextField.setText("");
								appearanceTextArea.setText("");
								genderTextField.setText("");
								characterList.clearSelection();
								affiliationTextField.setText("");
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
									
								
									if(characterList.getSelectedIndex() == -1) {
										Main.ExecuteSQLStatement("INSERT INTO Character(ID, CharacterName, Age, RaceOrNationality, DOB, Appearance, Personality, Story, Gender, Affiliation) VALUES(" + (Main.CountRowsInTable("Character") + 1) + ",'" + nameTextField.getText() + "','" + ageTextField.getText() + "','" + raceNationalityTextField.getText() + "','DOB','" + appearanceTextArea.getText() + "','" + personalityTextArea.getText() + "', (SELECT ID FROM Story WHERE Title = '" + Main._currentStory() + "'),'"+ genderTextField.getText() + "','" + affiliationTextField.getText() +"')");
									}
									else {
										Main.ExecuteSQLStatement("UPDATE Character SET CharacterName = '" + nameTextField.getText() + "', Age = '" + ageTextField.getText() + "', RaceOrNationality = '" + raceNationalityTextField.getText() + "', DOB = 'DOB', Appearance = '" + appearanceTextArea.getText() + "', Personality = '" + personalityTextArea.getText() + "', Gender = '"+ genderTextField.getText() + "', Affiliation = '" + affiliationTextField.getText() +"' WHERE ID = " + characterIDList.get(characterList.getSelectedIndex()));
									}
									
									try {
										ShowCharacterList();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

									run();
									
							}
							
							public void run() {
								try {
									Thread.sleep(5000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								System.out.println("Hiding message now");
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
						
						enemyList.setBounds(613, 203, 113, 156);
						characterPanel.add(enemyList);
						
						
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
					}
				}
			}
	
		
		
		
	private static DefaultListModel ShowCharacterList() throws SQLException {
	
		

	
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
	        
	      rs = statement.executeQuery("SELECT * FROM Character WHERE Story = (SELECT ID FROM Story WHERE Title = '" + Main._currentStory() + "')");
	      
	      
	      characterIDList.clear();
	      
	      while(rs.next()) {
	    	  characterIDList.add(rs.getInt(1));
	    	  characterListElements.addElement(rs.getString(2));
	    	  System.out.println("SELECT * FROM Character WHERE Story = (SELECT ID FROM Story WHERE Title = '" + Main._currentStory() + "')          Result: " + rs.getString(2));
	      }
	      
		}catch(Exception e){
			System.out.println(e);
		}
				
		
		return characterListElements;
		
		
	}
	
	private static void ShowCharacterDetails(int key) {
		
		System.out.println(key + 1);
		key += 1;
		nameTextField.setText(Main.SearchDatabase("CharacterName", "Character", Integer.toString(key), "ID"));
		ageTextField.setText(Main.SearchDatabase("Age", "Character", Integer.toString(key), "ID"));
		raceNationalityTextField.setText(Main.SearchDatabase("RaceOrNationality", "Character", Integer.toString(key), "ID"));
		appearanceTextArea.setText(Main.SearchDatabase("Appearance", "Character", Integer.toString(key), "ID"));
		genderTextField.setText(Main.SearchDatabase("Gender", "Character", Integer.toString(key), "ID"));
		personalityTextArea.setText(Main.SearchDatabase("Personality", "Character", Integer.toString(key), "ID"));
		affiliationTextField.setText(Main.SearchDatabase("Affiliation", "Character", Integer.toString(key), "ID"));
		//characterList.setSelectedIndex(key);
	}
	

	
	
}
