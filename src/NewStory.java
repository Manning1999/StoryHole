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
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

public class NewStory {

	private JFrame frame;
	private JTextField titleTextField;
	
	
	//NewStory menu components
	JLabel synopsisLabel = new JLabel("Synopsis");
	
	JList list = new JList();
	private JList genreList;
	
	JLabel genreLabel = new JLabel("Primary Genre");
	TextArea synopsisTextArea = new TextArea();
	private JTextField authorTextField;
	private JButton backButton;
	Button createStoryButton = new Button("Create Story");
	JLabel authorLabel = new JLabel("Author");
	
	

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
		frame = new JFrame();
		frame.setBounds(100, 100, 1023, 634);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
		frame.getContentPane().add(createStoryButton);
		
		authorTextField = new JTextField();
		authorTextField.setBounds(40, 473, 172, 37);
		frame.getContentPane().add(authorTextField);
		authorTextField.setColumns(10);
		

		authorLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		authorLabel.setBounds(89, 426, 86, 49);
		frame.getContentPane().add(authorLabel);
		
		backButton = new JButton("Back");
		backButton.setBounds(10, 11, 55, 23);
		frame.getContentPane().add(backButton);
	}
	
	
	
	
}
