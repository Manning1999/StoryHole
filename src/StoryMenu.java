import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class StoryMenu {

	private static JFrame frame;
	
	private static final JButton storyMenuBackButton = new JButton("Back");
	private static JButton charactersButton = new JButton("Characters");
	private static JButton chapterPlanningButton = new JButton("Chapter Planning");
	private static JButton locationsButton = new JButton("Locations");
	private static JButton languagesButton = new JButton("Languages");
	private static JLabel planningLabel = new JLabel("PLANNING");
	private static JLabel writingLabel = new JLabel("WRITING");
	private static JLabel lblNewLabel = new JLabel("Title");
	
	private static Boolean storyMenuIsCreated = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoryMenu window = new StoryMenu();
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
	public StoryMenu() {
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
		
		charactersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

	public static void SetStoryMenu(Boolean set) throws SQLException {
		
		frame = Main._frame();
		
		if(set == true) {
			CharacterPlanningMenu.SetCharacterMenu(false);
			Main.SetMainMenu(false);
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
							CharacterPlanningMenu.SetCharacterMenu(true);
							
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
							Main.SetMainMenu(true);
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

}
