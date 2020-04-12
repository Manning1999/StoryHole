//Description:
// This class is used primarily for planning out the new story menu and it's functions. This class will not be used in the final build but it's code will be copied across to the Main class


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

public class NewStory {

	private static JFrame frame;
	private static JTextField titleTextField;
	
	
	//NewStory menu components
	private static JLabel synopsisLabel = new JLabel("Synopsis");
	
	private static JList list = new JList();
	private static JList genreList;
	
	private static JLabel genreLabel = new JLabel("Primary Genre");
	private static TextArea synopsisTextArea = new TextArea();
	private static JTextField authorTextField;
	private static Main.CustomButton backButton = new Main.CustomButton("Back");
	private static Main.CustomButton createStoryButton = new Main.CustomButton("Create Story");
	private static JLabel authorLabel = new JLabel("Author");
	
	static Boolean createdNewStoryMenu = false;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewStory window = new NewStory();
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
	public NewStory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Main.getInstance().SetImages();
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1023, 634);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setContentPane(new Main.ImagePanel(Main.backgroundImage));
		
		
		titleTextField = new JTextField();
		titleTextField.setFont(new Font("Tahoma", Font.BOLD, 29));
		titleTextField.setHorizontalAlignment(SwingConstants.CENTER);
		titleTextField.setText("Title");
		titleTextField.setToolTipText("Title");
		titleTextField.setBounds(356, 50, 262, 56);
		frame.getContentPane().add(titleTextField);
		titleTextField.setColumns(10);
		
		
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Test 1");
		genreList=new JList(listModel);
		
		
		
		
		genreList.setBounds(90, 136, 135, 263);
		frame.getContentPane().add(genreList);
		

		genreLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
		genreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		genreLabel.setBounds(80, 89, 135, 37);
		frame.getContentPane().add(genreLabel);
		

		synopsisTextArea.setBounds(598, 125, 372, 425);
		frame.getContentPane().add(synopsisTextArea);
		

		synopsisLabel.setBounds(718, 89, 135, 27);
		genreLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
		frame.getContentPane().add(synopsisLabel);
		

		createStoryButton.setFont(new Font("Dialog", Font.BOLD, 16));

		createStoryButton.setBounds(370, 500, 135, 37);
		createStoryButton.SetButtonVariation(1);
		frame.getContentPane().add(createStoryButton);
		
		authorTextField = new JTextField();
		authorTextField.setBounds(80, 473, 172, 37);
		frame.getContentPane().add(authorTextField);
		authorTextField.setColumns(10);
		

		authorLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		authorLabel.setBounds(89, 426, 86, 49);
		frame.getContentPane().add(authorLabel);
		

		backButton.setBounds(10, 11, 55, 23);
		backButton.SetButtonVariation(1);
		frame.getContentPane().add(backButton);
	}
	
	public static void SetNewStoryMenu(Boolean set) throws SQLException {
		
	frame = Main._frame();
	
		if(createdNewStoryMenu == false && set == true) {
			
			Main.SetMainMenu(false);
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
			createStoryButton.SetButtonVariation(1);
			createStoryButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
		            Date date = new Date();
		            
					System.out.println("INSERT  INTO Story VALUES(" + Main.CountRowsInTable("Story") + 1 + ",'" + titleTextField.getText() + "','" + authorTextField.getText() + "'," + date + ",'" + synopsisTextArea.getText() + "')");
					
					
					Main.ExecuteSQLStatement("INSERT  INTO Story VALUES(" + Main.CountRowsInTable("Story") + 1 + ",'" + titleTextField.getText() + "','" + authorTextField.getText() + "','" + date + "','" + synopsisTextArea.getText() + "')");
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
			backButton.SetButtonVariation(1);
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Main.SetMainMenu(true);
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
				Main.SetMainMenu(false);
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
	
	
}
