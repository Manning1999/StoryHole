import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class StoryMenu {

	private static JFrame frame;
	
	private static final Main.CustomButton storyMenuBackButton = new Main.CustomButton("Back");
	private static Main.CustomButton charactersButton = new Main.CustomButton("Characters");
	private static Main.CustomButton chapterPlanningButton = new Main.CustomButton("Chapter Planning");
	private static Main.CustomButton locationsButton = new Main.CustomButton("Locations");
	private static Main.CustomButton languagesButton = new Main.CustomButton("Languages");
	private static JLabel planningLabel = new JLabel("PLANNING");
	private static JLabel writingLabel = new JLabel("WRITING");
	private static JLabel titleLabel = new JLabel("Title");
	
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
		//Main.getInstance().SetImages();
		//initialize();
		try {
			Main.getInstance().SetImages();
			SetStoryMenu(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Main.getInstance().SetImages();
		try {
			Main.getInstance().SetMainMenu(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1023, 634);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setContentPane(new Main.ImagePanel(Main.backgroundImage));
		
		charactersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		

		charactersButton.setBounds(135, 267, 175, 64);
		charactersButton.SetButtonVariation(1);
		//charactersButton.setIcon(new ImageIcon(Main.buttonUnselectedImage));
		frame.getContentPane().add(charactersButton);
		

		chapterPlanningButton.setBounds(676, 333, 175, 64);
		chapterPlanningButton.SetButtonVariation(1);
		chapterPlanningButton.setIcon(new ImageIcon(Main.buttonUnselectedImage));
		frame.getContentPane().add(chapterPlanningButton);
		

		locationsButton.setBounds(135, 501, 175, 64);
		locationsButton.SetButtonVariation(1);
		locationsButton.setIcon(new ImageIcon(Main.buttonUnselectedImage));
		frame.getContentPane().add(locationsButton);
		

		languagesButton.setBounds(135, 384, 175, 64);
		languagesButton.SetButtonVariation(1);
		languagesButton.setIcon(new ImageIcon(Main.buttonUnselectedImage));
		frame.getContentPane().add(languagesButton);
		

		planningLabel.setFont(new Font("Tahoma", Font.BOLD, 41));
		planningLabel.setBounds(119, 131, 232, 106);
		frame.getContentPane().add(planningLabel);
		

		writingLabel.setFont(new Font("Tahoma", Font.BOLD, 41));
		writingLabel.setBounds(656, 131, 232, 106);
		frame.getContentPane().add(writingLabel);
		

		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		titleLabel.setBounds(339, 68, 346, 99);
		frame.getContentPane().add(titleLabel);
	}

	public static void SetStoryMenu(Boolean set) throws SQLException {
		
		if(Main._frame() == null) {
			frame = new JFrame();
			frame.setBounds(100, 100, 1023, 672);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setMinimumSize(new Dimension(1023, 634));
			Main.ImagePanel backgroundPanel = new Main.ImagePanel(Main.backgroundImage);
			frame.setContentPane(backgroundPanel);
		}
		else {
			frame = Main._frame();
		}
		
		try {
			if(set == true) {
				CharacterPlanningMenu.SetCharacterMenu(false);
				Main.SetMainMenu(false);
			}
		}
		catch(Exception e) {
			System.out.println("This is being run on it's own and cannot deatcivate the other menus");
		}
		
		if(storyMenuIsCreated == true) {
			
			charactersButton.setVisible(set);
			chapterPlanningButton.setVisible(set);
			languagesButton.setVisible(set);
			locationsButton.setVisible(set);
			languagesButton.setVisible(set);
			planningLabel.setVisible(set);
			writingLabel.setVisible(set);
			titleLabel.setVisible(set);
			storyMenuBackButton.setVisible(set);
		}
		else {
			
			if(set == true) {
				storyMenuIsCreated = true;
				
				
				
				storyMenuBackButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				storyMenuBackButton.setBounds(10, 20, 89, 23);
				storyMenuBackButton.SetButtonVariation(1);
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
				charactersButton.SetButtonVariation(1);
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
				frame.getContentPane().add(charactersButton);
				
	
				chapterPlanningButton.setBounds(676, 333, 175, 64);
				chapterPlanningButton.SetButtonVariation(1);
				frame.getContentPane().add(chapterPlanningButton);
				
	
				locationsButton.setBounds(135, 501, 175, 64);
				locationsButton.SetButtonVariation(1);
				frame.getContentPane().add(locationsButton);
				
	
				languagesButton.setBounds(135, 384, 175, 64);
				languagesButton.SetButtonVariation(1);
				frame.getContentPane().add(languagesButton);
				
	
				planningLabel.setFont(new Font("Tahoma", Font.BOLD, 41));
				planningLabel.setBounds(119, 131, 232, 106);
				frame.getContentPane().add(planningLabel);
				
	
				writingLabel.setFont(new Font("Tahoma", Font.BOLD, 41));
				writingLabel.setBounds(656, 131, 232, 106);
				frame.getContentPane().add(writingLabel);
				
	
				titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
				titleLabel.setBounds(341, 11, 346, 99);
				frame.getContentPane().add(titleLabel);
			}
		}	
	}

}
