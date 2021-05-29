package linkstaterouting;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class routingframe {

	static public HashMap<char[],Integer> routemap = new HashMap<char[],Integer>();
	static ArrayList<Character> nodes = new ArrayList<>();
	
	static int node=0,V;
	int graph[][] = new int[][]{ { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }};
			
	private JFrame frame;
	private JTextField textField;
	
	private JTextField[] textFieldno1= new JTextField[50];
	private JTextField[] textFieldno2= new JTextField[50];
	private JTextField[] textFieldno3= new JTextField[50];
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JButton btnsetnode;

	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	
	/**
	 * Create the application.
	 */
	public routingframe() {
		initialize();
		displayrout();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnNewButton = new JButton("Enter");
		btnNewButton.setBounds(272, 37, 103, 23);
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel = new JLabel("Enter the numbe of edges:");
		lblNewLabel.setBounds(32, 41, 157, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(199, 38, 63, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Please enter nodes in order a,b,c,.....(node a is start) ");
		lblNewLabel_2.setBounds(109, 11, 218, 19);
		frame.getContentPane().add(lblNewLabel_2);
		
		
		
		
			
		
	}
	
	
	
	private void displayrout() {
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int edges =Integer.parseInt(textField.getText());
				int x =100;
				int y = 75;
				
				lblNewLabel_1 = new JLabel("Start node:");
				lblNewLabel_1.setToolTipText("");
				lblNewLabel_1.setBounds(10, 75, 74, 23);
				frame.getContentPane().add(lblNewLabel_1);
				for(int i=0;i<edges;i++) {
					
					textFieldno1[i] = new JTextField();
					textFieldno1[i].setBounds(x, y, 46, 20);
					frame.getContentPane().add(textFieldno1[i]);
					textFieldno1[i].setColumns(10);
					
					textFieldno2[i] = new JTextField();
					textFieldno2[i].setBounds(x+100, y, 46, 20);
					frame.getContentPane().add(textFieldno2[i]);
					textFieldno2[i].setColumns(10);
					
					textFieldno3[i] = new JTextField();
					textFieldno3[i].setBounds(x+250, y, 46, 20);
					frame.getContentPane().add(textFieldno3[i]);
					textFieldno3[i].setColumns(10);
					
					y=y+50;

					}
				int Y= y;
				btnsetnode = new JButton("Enter values");
				btnsetnode.setBounds(272, y+20, 120, 23);
				frame.getContentPane().add(btnsetnode);
				btnsetnode.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
				for(int i=0;i<edges;i++) {
							String tsedges = textFieldno1[i].getText();
							String teedges = textFieldno2[i].getText();
							int EDGE = Integer.parseInt(textFieldno3[i].getText());
							
							char chs = tsedges.charAt(0);
							char che = teedges.charAt(0);
													
							
							char[] rout = new char[2];
							rout[0]= Character.toLowerCase(chs);
							rout[1]= Character.toLowerCase(che);
						    
							nodes.add(chs);
							nodes.add(che);
							routemap.put(rout, EDGE);
				     }
				
				
				
				ArrayList<Character> newList = new ArrayList<Character>();
				for(Character n:nodes) {
					if (!newList.contains(n)) {
						newList.add(n);
						node++;
		
		            }
				}
				V = node;
				
				
				
				for(Map.Entry<char[],Integer> table : routemap.entrySet())
				{
					char[] k1 = table.getKey();
					int v1 = table.getValue();
					
					int x= (int)k1[0]-97;
					int y= (int)k1[1]-97;
					graph[x][y]=v1;
					
				}
			
				int dist[] = new int[V];
				Boolean[] sptSet = new Boolean[V];
				for (int i = 0; i < V; i++) {
		            dist[i] = Integer.MAX_VALUE;
		            sptSet[i] = false;
		        }
				
				dist[0] = 0;
				for(int count=0;count<V-1;count++) {
					int min = Integer.MAX_VALUE, min_index = -1;
					for (int v = 0; v < V; v++)
			            if (sptSet[v] == false && dist[v] <= min) {
			                min = dist[v];
			                min_index = v;
			            }
					
					int u = min_index;
					sptSet[u] = true;
					
					 for (int v = 0; v < V; v++) {
						 if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
			                    dist[v] = dist[u] + graph[u][v];
					 }
					
				}
				
				
				String text = " node a is start node :\nSource \t Vertex \t Distance from Source\n";
				frame.remove(btnsetnode);
				SwingUtilities.updateComponentTreeUI(frame);
				JTextArea textArea = new JTextArea();
				for (int i = 0; i < V; i++)
	            {
	        	char a = (char) (i+97);
	        	if(dist[i]>200000) {
	        		text = text+  ("a" +"\t" + a + " \t " +"never reached\n");
	        		continue;
	        	}
	        		
	        	text = text+  ("a" +"\t" + a + " \t " + dist[i]+"\n");
	            }
				
				textArea.setText(text);
				textArea.setBounds(109, Y+20, 370, 194);
				frame.getContentPane().add(textArea);
				
			
				
			}

					
		});
		
				SwingUtilities.updateComponentTreeUI(frame);
			
			}

		
		});
			
	}
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					routingframe window = new routingframe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		});
	
		
	}
}
