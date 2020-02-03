
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
	
	
	
	private String currentStory;
	DefaultListModel characterListElements = new DefaultListModel();
	private ArrayList<Integer> characterIDList = new ArrayList<Integer>();
	

	private JFrame frame;
	JPanel panel = new JPanel();

	
	
	//Main Menu Components
	JScrollPane scrollPane = new JScrollPane();
	JTextPane descriptionLabel = new JTextPane();
	JButton newStoryButton = new JButton("New Story");
	JButton editStoryButton = new JButton("Edit Story");
	
	
	
	//New story components
	private JTextField titleTextField;
	JLabel synopsisLabel = new JLabel("Synopsis");
	JList list = new JList();
	private JList genreList;
	JLabel genreLabel = new JLabel("Primary Genre");
	TextArea synopsisTextArea = new TextArea();
	private JTextField authorTextField;
	private JButton backButton = new JButton("Back");
	Button createStoryButton = new Button("Create Story");
	JLabel authorLabel = new JLabel("Author");
	
	
	
	//Character Menu Components
	private final JButton characterMenuBackButton = new JButton("Back");
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
	private JList characterList = new JList();
	private final JButton button = new JButton("View");
	private final JButton button_1 = new JButton("-");
	private final JButton btnAddEnemy = new JButton("Add Enemy");
	private final JLabel affiliationLabel = new JLabel("Affiliation:");
	private final JTextField affiliationTextField = new JTextField();
	
	
	
	//Story Menu components
	private final JButton storyMenuBackButton = new JButton("Back");
	JButton charactersButton = new JButton("Characters");
	JButton chapterPlanningButton = new JButton("Chapter Planning");
	JButton locationsButton = new JButton("Locations");
	JButton languagesButton = new JButton("Languages");
	JLabel planningLabel = new JLabel("PLANNING");
	JLabel writingLabel = new JLabel("WRITING");
	JLabel lblNewLabel = new JLabel("Title");

	
	
	
	
	 Connection connection = null;
     java.sql.Statement statement = null;
	
	ResultSet resultSet = null;
	
	
	
	Boolean mainMenuIsCreated = false;
	Boolean createdNewStoryMenu = false;
	Boolean characterMenuIsCreated = false;
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
	}
	
	
	
	private void SetMainMenu(Boolean set) throws SQLException {
		
		
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
						SetNewStoryMenu(true);
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
						SetStoryMenu(true);
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
					SetNewStoryMenu(false);
					SetCharacterMenu(false);
					SetStoryMenu(false);
					LoadStoriesFromDatabase();
				}
				
				scrollPane.setVisible(set);
				descriptionLabel.setVisible(set);
				newStoryButton.setVisible(set);
				editStoryButton.setVisible(set);
				
			}
	}
	
	
	
	
	
	private void SetNewStoryMenu(Boolean set) throws SQLException {
		
		if(createdNewStoryMenu == false && set == true) {
			
			SetMainMenu(false);
			createdNewStoryMenu = true;
			
			titleTextField = new JTextField();
			titleTextField.setFont(new Font("Tahoma", Font.BOLD, 29));
			titleTextField.setHorizontalAlignment(SwingConstants.CENTER);
			titleTextField.setText("Title");
			titleTextField.setToolTipText("Title");
			titleTextField.setBounds(356, 29, 262, 56);
			frame.getContentPane().add(titleTextField);
			titleTextField.setColumns(10);
			
			
			list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			DefaultListModel listModel = new DefaultListModel();
			listModel.addElement("Test 1");
			genreList=new JList(listModel);
			
			genreList.setBounds(40, 136, 135, 263);
			frame.getContentPane().add(genreList);
			

			genreLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
			genreLabel.setHorizontalAlignment(SwingConstants.CENTER);
			genreLabel.setBounds(40, 89, 135, 37);
			frame.getContentPane().add(genreLabel);
			

			synopsisTextArea.setBounds(598, 125, 372, 425);
			frame.getContentPane().add(synopsisTextArea);
			

			synopsisLabel.setBounds(718, 89, 135, 27);
			genreLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
			frame.getContentPane().add(synopsisLabel);
			


			createStoryButton.setFont(new Font("Dialog", Font.BOLD, 16));
			createStoryButton.setBounds(370, 531, 135, 37);
			createStoryButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
		            Date date = new Date();
		            
					System.out.println("INSERT  INTO Story VALUES(" + CountRowsInTable("Story") + 1 + ",'" + titleTextField.getText() + "','" + authorTextField.getText() + "'," + date + ",'" + synopsisTextArea.getText() + "')");
					
					
					ExecuteSQLStatement("INSERT  INTO Story VALUES(" + CountRowsInTable("Story") + 1 + ",'" + titleTextField.getText() + "','" + authorTextField.getText() + "','" + date + "','" + synopsisTextArea.getText() + "')");
				}
			});
			frame.getContentPane().add(createStoryButton);
			
			

			authorTextField = new JTextField();
			authorTextField.setBounds(40, 473, 172, 37);
			frame.getContentPane().add(authorTextField);
			authorTextField.setColumns(10);
			

			authorLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
			authorLabel.setBounds(89, 426, 86, 49);
			frame.getContentPane().add(authorLabel);
			
			
			
			backButton.setBounds(10, 11, 89, 23);
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						SetMainMenu(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			frame.getContentPane().add(backButton);
		}
		else {
			
			if(set == true) {
				SetMainMenu(false);
			}
			
			
			if(createdNewStoryMenu == true) {
				titleTextField.setVisible(set);
				backButton.setVisible(set);
				authorLabel.setVisible(set);
				authorTextField.setVisible(set);
				createStoryButton.setVisible(set);
				synopsisLabel.setVisible(set);
				synopsisTextArea.setVisible(set);
				genreLabel.setVisible(set);
				genreList.setVisible(set);
			}
		}
		
	}
	
	
	private void SetStoryMenu(Boolean set) throws SQLException {
		
		if(set == true) {
			SetCharacterMenu(false);
			SetMainMenu(false);
		}
		
		if(storyMenuIsCreated == true) {
			
			charactersButton.setVisible(set);
			chapterPlanningButton.setVisible(set);
			languagesButton.setVisible(set);
			locationsButton.setVisible(set);
			languagesButton.setVisible(set);
			planningLabel.setVisible(set);
			writingLabel.setVisible(set);
			lblNewLabel.setVisible(set);
			storyMenuBackButton.setVisible(set);
		}
		else {
			
			if(set == true) {
				storyMenuIsCreated = true;
	
				charactersButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							SetCharacterMenu(true);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				
				
				storyMenuBackButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				storyMenuBackButton.setBounds(10, 20, 89, 23);
				
				frame.getContentPane().add(storyMenuBackButton);
				storyMenuBackButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							SetMainMenu(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
	
				charactersButton.setBounds(135, 267, 175, 64);
				frame.getContentPane().add(charactersButton);
				
	
				chapterPlanningButton.setBounds(676, 333, 175, 64);
				frame.getContentPane().add(chapterPlanningButton);
				
	
				locationsButton.setBounds(135, 501, 175, 64);
				frame.getContentPane().add(locationsButton);
				
	
				languagesButton.setBounds(135, 384, 175, 64);
				frame.getContentPane().add(languagesButton);
				
	
				planningLabel.setFont(new Font("Tahoma", Font.BOLD, 41));
				planningLabel.setBounds(119, 131, 232, 106);
				frame.getContentPane().add(planningLabel);
				
	
				writingLabel.setFont(new Font("Tahoma", Font.BOLD, 41));
				writingLabel.setBounds(656, 131, 232, 106);
				frame.getContentPane().add(writingLabel);
				
	
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
				lblNewLabel.setBounds(341, 11, 346, 99);
				frame.getContentPane().add(lblNewLabel);
			}
		}
		
		
		
	}
	
	private void SetCharacterMenu(Boolean set) throws SQLException {
		
		
			System.out.println(characterMenuIsCreated);
		
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
					SetStoryMenu(false);
				}
				else {
					txtpnYouHaveNot.setVisible(false);
				}
				
				
			}
			else {
			
				if(set == true) {
					
					SetStoryMenu(false);
					
					characterMenuIsCreated = true;
					
					if(CountRowsInTableWithConditions("Character", "Story", "(SELECT ID FROM Story WHERE Title = '" + currentStory + "')") == 0) {
						txtpnYouHaveNot.setBackground(SystemColor.controlShadow);
						txtpnYouHaveNot.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
						txtpnYouHaveNot.setText("You have not created any characters yet");
						txtpnYouHaveNot.setBounds(17, 141, 195, 70);
						frame.getContentPane().add(txtpnYouHaveNot);
					}

					
					characterList = new JList(ShowCharacterList());
					characterList.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent e) {
							ShowCharacterDetails(characterList.getSelectedIndex());
						}
					});
					
					
					
					
					characterMenuBackButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								SetStoryMenu(true);
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
								SetMainMenu(true);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
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
									ExecuteSQLStatement("INSERT INTO Character(ID, CharacterName, Age, RaceOrNationality, DOB, Appearance, Personality, Story, Gender, Affiliation) VALUES(" + (CountRowsInTable("Character") + 1) + ",'" + nameTextField.getText() + "','" + ageTextField.getText() + "','" + raceNationalityTextField.getText() + "','DOB','" + appearanceTextArea.getText() + "','" + personalityTextArea.getText() + "', (SELECT ID FROM Story WHERE Title = '" + currentStory + "'),'"+ genderTextField.getText() + "','" + affiliationTextField.getText() +"')");
								}
								else {
									ExecuteSQLStatement("UPDATE Character SET CharacterName = '" + nameTextField.getText() + "', Age = '" + ageTextField.getText() + "', RaceOrNationality = '" + raceNationalityTextField.getText() + "', DOB = 'DOB', Appearance = '" + appearanceTextArea.getText() + "', Personality = '" + personalityTextArea.getText() + "', Gender = '"+ genderTextField.getText() + "', Affiliation = '" + affiliationTextField.getText() +"' WHERE ID = " + characterIDList.get(characterList.getSelectedIndex()));
								}
								
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
				}
			}
		}
			
		
		
	
	
	
	
	private DefaultListModel ShowCharacterList() throws SQLException {
		
		
		if(CountRowsInTableWithConditions("Character", "Story", "(SELECT ID FROM Story WHERE Title = '" + currentStory + "')") == 0) {
			txtpnYouHaveNot.setVisible(true);
		}
		else {
			txtpnYouHaveNot.setVisible(false);
		}
		
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
	        
	      rs = statement.executeQuery("SELECT * FROM Character WHERE Story = (SELECT ID FROM Story WHERE Title = '" + currentStory + "')");
	      
	      
	      characterIDList.clear();
	      
	      while(rs.next()) {
	    	  characterIDList.add(rs.getInt(1));
	    	  characterListElements.addElement(rs.getString(2));
	    	  System.out.println("SELECT * FROM Character WHERE Story = (SELECT ID FROM Story WHERE Title = " + currentStory + ")          Result: " + rs.getString(2));
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
	
	private void ExecuteSQLStatement(String newStatement) {
		
			
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
	
	
	
	
	//Shows the description on the main menu
	private void ShowDescription(int key) {
		
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
	
	
	private void LoadStoriesFromDatabase() {
		
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

	
	private void ShowCategories() {
		
	}
	
	
	
	
}

