import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.function.BiPredicate;
import java.sql.ResultSet;
import javax.lang.model.element.Element;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class LocationMenu {

	private JFrame frame;
	private static JButton backButton = new JButton("Back");
	static JButton newLocationButton = new JButton("New Location");
	static JPanel locationPanel = new JPanel();
	static JButton saveChangesButton = new JButton("Save Changes");
	private static JTextField nameTextField;
	
	
	JTree tree;
	DefaultMutableTreeNode baseTreeRoot = new DefaultMutableTreeNode("");
	DefaultMutableTreeNode filteredTreeRoot = new DefaultMutableTreeNode("");
	
	static Connection connection = null;
	static java.sql.Statement statement = null;
	
	private DefaultListModel locationListElements;
	
	private DefaultComboBoxModel comboBoxListElements;
	private final JButton sortButton = new JButton("Sort (ABC)");
	private JTextField txtSearch;
	JComboBox parentLocationComboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocationMenu window = new LocationMenu();
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
	public LocationMenu() {
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
		frame.setMinimumSize(new Dimension(1023, 634));
		
		

		backButton.setBounds(10, 11, 89, 23);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					StoryMenu.SetStoryMenu(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(backButton);
		
		
		locationPanel.setBackground(SystemColor.activeCaptionBorder);
		locationPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(180, 180, 180), null, null, new Color(200, 200, 200)));
		locationPanel.setForeground(SystemColor.scrollbar);
		locationPanel.setBounds(236, 67, 761, 484);
		frame.getContentPane().add(locationPanel);
		locationPanel.setLayout(null);
		
		
		
		newLocationButton.setBounds(10, 39, 206, 50);
		frame.getContentPane().add(newLocationButton);
		newLocationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					//Clear all the fields to make way for a new one
			}
		});
		
		
		
		
		saveChangesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					ShowLocationList();
			
			}
		});
		

		saveChangesButton.setBounds(321, 450, 101, 23);
		locationPanel.add(saveChangesButton);
		
		
		nameTextField = new JTextField();
		nameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		nameTextField.setText("Location Name");
		nameTextField.setBounds(250, 11, 228, 40);
		locationPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		
		
		JLabel controllingFactionLabel = new JLabel("Controlling Faction:");
		controllingFactionLabel.setBounds(36, 90, 106, 14);
		locationPanel.add(controllingFactionLabel);
		
		
		
		JLabel parentLocationLabel = new JLabel("Parent Location:");
		parentLocationLabel.setBounds(36, 178, 106, 14);
		locationPanel.add(parentLocationLabel);
		
		JComboBox controllingFactionComboBox = new JComboBox();
		controllingFactionComboBox.setBounds(137, 87, 142, 23);
		locationPanel.add(controllingFactionComboBox);
		
		
		parentLocationComboBox.setBounds(137, 175, 142, 23);
		locationPanel.add(parentLocationComboBox);
		
		
		tree = new JTree(ShowLocationListV2());
		tree.setBounds(10, 156, 216, 395);
		frame.getContentPane().add(tree);
		tree.setRootVisible(false);
		
    
		
		JButton filterButton = new JButton("Filter");
		filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		filterButton.setBounds(10, 94, 89, 23);
		frame.getContentPane().add(filterButton);
		sortButton.setHorizontalAlignment(SwingConstants.LEFT);
		sortButton.setBounds(127, 94, 89, 23);
		
		frame.getContentPane().add(sortButton);
		
		txtSearch = new JTextField();
		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtSearch.getText() == "") {
					
				}
				else {
					System.out.println("Filtering now");
					filteredTreeRoot = new DefaultMutableTreeNode();
					SearchLocationTree((DefaultMutableTreeNode) tree.getModel().getRoot());	
					tree = new JTree(filteredTreeRoot);
				}
			
				
			}
		});
		
		txtSearch.setText("test");
		txtSearch.setBounds(10, 125, 206, 20);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
			
	}
	
	
	
	private void GetParentLocations(TreeNode root) {
		
		try {
			parentLocationComboBox.addItem(root.toString());
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		Enumeration children = root.children();
		if(children != null) {
			while(children.hasMoreElements()) {
				GetParentLocations((TreeNode) children.nextElement());
			}
		}
			
	}
	
	
	
	
	//Test Version
	public DefaultMutableTreeNode ShowLocationList() {
		
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("");
        DefaultMutableTreeNode tier2 = new DefaultMutableTreeNode("Tier 2");
        

       for(int i = 0; i < 5; i++) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode("tier 3 " + i);
            tier2.add(node);
        }
       
       root.add(tier2);
       root.add(tier2);

       
       GetParentLocations(root);
     
       return root;
		
	}
	
	
	
	//This one will create the tree from the database
	public DefaultMutableTreeNode ShowLocationListV2() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("");
		
		
		
		ResultSet rs = null;

		try {
			
			
			//tree.removeAll();
			
			 String msAccDB = "C:/Users/manni/eclipse-workspace/StoryPlanner" + "/StoryPlanner.accdb";
	         String dbURL = "jdbc:ucanaccess://" + msAccDB; 
	         
			
			// Step 2.A: Create and 
	        // get connection using DriverManager class
	        connection = DriverManager.getConnection(dbURL); 
	
	        // Step 2.B: Creating JDBC Statement 
	        statement = connection.createStatement();
	
	        // Step 2.C: Executing SQL and 
	        // retrieve data into ResultSet
	        
	        
	        
	        //Testing purposes statement
	        rs = statement.executeQuery("SELECT * FROM Location WHERE Story = (SELECT ID FROM Story WHERE Title = 'Mannings Story') AND ParentLocation IS NULL ");
	        
	        
	        //Live statement
	        //rs = statement.executeQuery("SELECT * FROM Location WHERE Story = (SELECT ID FROM Story WHERE Title = '" + Main._currentStory() + "')");
	     
	      
	    
	      //Iterate through all the root nodes
	      while(rs.next()) {   	 
	    	  //root.add(new DefaultMutableTreeNode(rs.getString(2)));
	    	  baseTreeRoot.add(new DefaultMutableTreeNode(rs.getString(2)));
	    	  ResultSet childSet = statement.executeQuery("SELECT * FROM Location WHERE Story = (SELECT ID FROM Story WHERE Title = 'Mannings Story') AND ParentLocation = " + rs.getString(2));
				while(childSet.next()) {
					GetTreeChildren(new DefaultMutableTreeNode(rs.getString(2)));
				}
	    	  
	    	 System.out.println(rs.getString(2));
	      }
	      
		}catch(Exception e){
			System.out.println("Failed to read: " + e);
		}
				
		
		
		
		
		 return root;
	}
	
	
	public void GetTreeChildren(DefaultMutableTreeNode node) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("");
		
		
		
		ResultSet rs = null;
		
		
		 try {
			rs = statement.executeQuery("SELECT * FROM Location WHERE Story = (SELECT ID FROM Story WHERE Title = 'Mannings Story') AND ParentLocation = " + node.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		 
		 try {
			while(rs.next()) {
			
				ResultSet childSet = statement.executeQuery("SELECT * FROM Location WHERE Story = (SELECT ID FROM Story WHERE Title = 'Mannings Story') AND ParentLocation = " + node.toString());
				while(childSet.next()) {
					GetTreeChildren(new DefaultMutableTreeNode(rs.getString(2)));
				}
				node.add(new DefaultMutableTreeNode(rs.getString(2)));
			
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		//baseTreeRoot.getChildAt(baseTreeRoot.getIndex(node)).add(node)
		
		
	}
	
	
	
	
	
	//TODO: Finish search function
	public void SearchLocationTree(DefaultMutableTreeNode node) {
	    System.out.println(node.toString());
	    
	    
	    
	    if(node.toString().contains(txtSearch.getText())) {

	    	filteredTreeRoot.add(node);
	    	
	    	System.out.println("Added " + node.toString());
	    	
	    }
	    
	    if (((DefaultMutableTreeNode) node).getChildCount() >= 0) {
	      for (Enumeration e = ((DefaultMutableTreeNode) node).children(); e.hasMoreElements();) {
	        TreeNode n = (TreeNode) e.nextElement();
	        SearchLocationTree((DefaultMutableTreeNode) n);
	      }
	    }
	    
	    
	  }
}
