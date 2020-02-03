import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StoryMenu {

	private JFrame frame;
	
	
	JButton charactersButton = new JButton("Characters");
	JButton chapterPlanningButton = new JButton("Chapter Planning");
	JButton locationsButton = new JButton("Locations");
	JButton languagesButton = new JButton("Languages");
	JLabel planningLabel = new JLabel("PLANNING");
	JLabel writingLabel = new JLabel("WRITING");
	JLabel lblNewLabel = new JLabel("Title");
	
	
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
}
