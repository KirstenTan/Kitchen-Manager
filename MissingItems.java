package kitchprogram;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class MissingItems extends JFrame {

	private JPanel contentPane;
	static String missingItems2;
	static String addMissing;
	static int createButton;
	
	private static JTable groceryTable;
	static DefaultTableModel groceryDtm = new DefaultTableModel();
	final Object[] columnGro = new Object[6];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MissingItems frame = new MissingItems(missingItems2, groceryTable, groceryDtm);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MissingItems(String missingItems, JTable groceryTable2, DefaultTableModel groceryDtm2) {
		
		missingItems2 = missingItems;
		groceryTable = groceryTable2;
		groceryDtm = groceryDtm2;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 400, 680);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(223, 193, 172));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel warningLbl = new JLabel("New label");
		warningLbl.setIcon(new ImageIcon(MissingItems.class.getResource("/assets2/Warning Icon.png")));
		warningLbl.setBackground(new Color(255, 255, 255));
		warningLbl.setBounds(165, 30, 50, 50);
		contentPane.add(warningLbl);
		
		JLabel questionLbl = new JLabel("<html>Add missing ingredients <br> to grocery list? <html>");
		questionLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		questionLbl.setBounds(55, 85, 265, 82);
		contentPane.add(questionLbl);
		
		JTextArea ingredientsTa = new JTextArea();
		ingredientsTa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ingredientsTa.setEditable(false);
		ingredientsTa.setBounds(55, 178, 277, 364);
		ingredientsTa.setOpaque(false);
		ingredientsTa.setText(missingItems);
		contentPane.add(ingredientsTa);
		
		JButton yesBtn = new JButton("Yes");
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String [] parts = missingItems.split("\n");
				
				for(int z = 0; z < parts.length; z++) {
					String line = parts[z];
					String [] words = line.split(" ");
					String quantity = words[0];
					String unit = words[1];
					
					StringBuilder sb = new StringBuilder();
					String nameParts = "";
					
					for(int y = 2; y < words.length; y++) {
						nameParts = words[y];
						sb.append(nameParts).append("\n");
					}
					
					String name = sb.toString();
					
					System.out.println("Missing Items: " + quantity + " " + unit + " " + name);
					
					try
					{
						File f = new File("GroceryList.txt");
						FileOutputStream in = new FileOutputStream(f, true);
						PrintWriter w = new PrintWriter(in);
						
						w.println(name);
						w.println();
						w.println();
						w.println(quantity + " " + unit);
						w.println();
						w.println();
						
						w.close();
					}
					catch (Exception e2)
					{
						
					}
				
				}
		
				dispose();
			}
		});
		yesBtn.setForeground(new Color(0, 0, 0));
		yesBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		yesBtn.setFocusPainted(false);
		yesBtn.setBackground(new Color(225, 225, 225));
		yesBtn.setOpaque(true);
		yesBtn.setBounds(55, 574, 120, 40);
		contentPane.add(yesBtn);
		
		JButton noBtn = new JButton("No");
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		noBtn.setForeground(new Color(0, 0, 0));
		noBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		noBtn.setFocusPainted(false);
		noBtn.setBackground(new Color(225, 225, 225));
		noBtn.setOpaque(true);
		noBtn.setBounds(209, 574, 120, 40);
		contentPane.add(noBtn);
	}
}
