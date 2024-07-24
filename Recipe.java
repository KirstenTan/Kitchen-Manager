package kitchprogram;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class Recipe extends JFrame {

	private JPanel contentPane;
	private JLabel recipeNameLbl;
	private JLabel infoLbl;
	private JLabel instructionsLbl;
	private JLabel ingredientsLbl;
	private JLabel bkg;
	private JTextArea instructionsTa;
	private JTextArea ingredientsTa;
	private JButton recipe1Btn;
	private JButton recipe2Btn;
	private JButton recipe3Btn;
	private JButton recipe4Btn;
	private JButton recipe5Btn;
	static String fileName;
	static String recipe1;
	static String recipe2;
	static String recipe3;
	static String recipe4;
	static String recipe5;
	static String function;
	static String recipeName;
	static int multiplier;
	static int multiplier2;
	static int createButton2;
	static int sm;
	
	private static JTable groceryTable2;
	static DefaultTableModel groceryDtm2 = new DefaultTableModel();
	final Object[] columnGro = new Object[6];
	
	private static JTable inventoryTable2;
	static DefaultTableModel inventoryDtm2 = new DefaultTableModel();
	final Object[] columnInv = new Object[7];
	
	static DefaultTableModel ingredientsDtm2 = new DefaultTableModel();
	private static JTable ingredientsTable2 = new JTable(ingredientsDtm2);
	final Object[] columnIng = new Object[2];
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Recipe frame = new Recipe(groceryTable2, groceryDtm2, inventoryTable2, inventoryDtm2, createButton2, ingredientsTable2, ingredientsDtm2);
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
	public Recipe(JTable groceryTable, DefaultTableModel groceryDtm, JTable inventoryTable, DefaultTableModel inventoryDtm, int createButton, JTable ingredientsTable, DefaultTableModel ingredientsDtm) {
		
		groceryTable2 = groceryTable;
		groceryDtm2 = groceryDtm;
		inventoryTable2 = inventoryTable;
		inventoryDtm2 = inventoryDtm;
		createButton2 = createButton;
		
		readRecipeNames();
		readButton();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		recipeNameLbl = new JLabel();
		recipeNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		recipeNameLbl.setBounds(272, 72, 212, 74);
		recipeNameLbl.setVisible(false);
		contentPane.add(recipeNameLbl);
		
		infoLbl = new JLabel("<html> Servings: <br> Prep Time: <br> Cook Time: <html>");
		infoLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		infoLbl.setVerticalAlignment(SwingConstants.TOP);
		infoLbl.setBounds(272, 157, 199, 74);
		infoLbl.setVisible(false);
		contentPane.add(infoLbl);
		
		instructionsLbl = new JLabel("Instructions");
		instructionsLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		instructionsLbl.setBounds(527, 72, 193, 29);
		instructionsLbl.setVisible(false);
		contentPane.add(instructionsLbl);
		
		instructionsTa = new JTextArea();
		instructionsTa.setWrapStyleWord(true);
		instructionsTa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		instructionsTa.setBounds(527, 112, 635, 627);
		instructionsTa.setVisible(false);
		instructionsTa.setLineWrap(true);
		contentPane.add(instructionsTa);

		ingredientsTa = new JTextArea();
		ingredientsTa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ingredientsTa.setWrapStyleWord(true);
		ingredientsTa.setBounds(272, 295, 212, 457);
		ingredientsTa.setVisible(false);		
		ingredientsTa.setLineWrap(true);
		contentPane.add(ingredientsTa);
		
		ingredientsLbl = new JLabel("Ingredients");
		ingredientsLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ingredientsLbl.setBounds(272, 255, 193, 29);
		ingredientsLbl.setVisible(false);
		contentPane.add(ingredientsLbl);
		
		recipe1Btn = new JButton(recipe1);
		recipe1Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileName = "recipe1";
				displayPage();
				displayRecipe();
				checkIngredients();
			}
		});
		recipe1Btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		recipe1Btn.setFocusPainted(false);
		recipe1Btn.setContentAreaFilled(true);
		recipe1Btn.setBackground(Color.WHITE);
		recipe1Btn.setBounds(20, 200, 212, 44);
		recipe1Btn.setVisible(false);
		contentPane.add(recipe1Btn);
		
		recipe2Btn = new JButton(recipe2);
		recipe2Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileName = "recipe2";
				displayPage();
				displayRecipe();
				checkIngredients();
			}
		});
		recipe2Btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		recipe2Btn.setFocusPainted(false);
		recipe2Btn.setContentAreaFilled(true);
		recipe2Btn.setBackground(Color.WHITE);
		recipe2Btn.setBounds(20, 255, 212, 44);
		recipe2Btn.setVisible(false);
		contentPane.add(recipe2Btn);
		
		recipe3Btn = new JButton(recipe3);
		recipe3Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileName = "recipe3";
				displayPage();
				displayRecipe();
				checkIngredients();
			}
		});
		recipe3Btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		recipe3Btn.setFocusPainted(false);
		recipe3Btn.setContentAreaFilled(true);
		recipe3Btn.setBackground(Color.WHITE);
		recipe3Btn.setBounds(20, 310, 212, 44);
		recipe3Btn.setVisible(false);
		contentPane.add(recipe3Btn);
		
		recipe4Btn = new JButton(recipe4);
		recipe4Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileName = "recipe4";
				displayPage();
				displayRecipe();
				checkIngredients();
			}
		});
		recipe4Btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		recipe4Btn.setFocusPainted(false);
		recipe4Btn.setContentAreaFilled(true);
		recipe4Btn.setBackground(Color.WHITE);
		recipe4Btn.setBounds(20, 365, 212, 44);
		recipe4Btn.setVisible(false);
		contentPane.add(recipe4Btn);
		
		recipe5Btn = new JButton(recipe5);
		recipe5Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileName = "recipe5";
				displayPage();
				displayRecipe();
				checkIngredients();
			}
		});
		recipe5Btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		recipe5Btn.setFocusPainted(false);
		recipe5Btn.setContentAreaFilled(true);
		recipe5Btn.setBackground(Color.WHITE);
		recipe5Btn.setBounds(20, 420, 212, 44);
		recipe5Btn.setVisible(false);
		contentPane.add(recipe5Btn);
		
		if(createButton2 >= 1) {
			recipe1Btn.setVisible(true);
		}
		
		if(createButton2 >= 2) {
			recipe2Btn.setVisible(true);
		}
		
		if(createButton2 >= 3) {
			recipe3Btn.setVisible(true);
		}
		if(createButton2 >= 4) {
			recipe4Btn.setVisible(true);
		}
		if(createButton2 >= 5) {
			recipe5Btn.setVisible(true);
		}
		
		JButton deleteBtn = new JButton("Delete Recipe");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Delete content of recipe file
				
				try
				{
					File f = new File(fileName + ".txt");
					FileOutputStream in = new FileOutputStream(f);
					PrintWriter w = new PrintWriter(in);
					
					w.close();
					
					File f2 = new File(fileName + "Ing.txt");
					FileOutputStream in2 = new FileOutputStream(f2);
					PrintWriter w2 = new PrintWriter(in2);
					
					w2.close();
				}
				catch (Exception e2) {
					
				}
				
				displayRecipe();
			}
		});
		deleteBtn.setBackground(new Color(255, 255, 255));
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		deleteBtn.setBounds(20, 695, 212, 44);
		deleteBtn.setContentAreaFilled(true);
		deleteBtn.setFocusPainted(false);
		contentPane.add(deleteBtn);
		
		JButton editBtn = new JButton("Edit Recipe");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function = "edit";
				recipeName = recipeNameLbl.getText();
				ModifyRecipe f4 = new ModifyRecipe (createButton2, function, recipeName);
				f4.setVisible(true);
				dispose();
			}
		});
		editBtn.setBackground(new Color(255, 255, 255));
		editBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		editBtn.setBounds(20, 640, 212, 44);
		editBtn.setContentAreaFilled(true);
		editBtn.setFocusPainted(false);
		contentPane.add(editBtn);
		
		JButton addBtn = new JButton("Add Recipe");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function = "add";
				ModifyRecipe f4 = new ModifyRecipe (createButton2, function, recipeName);
				f4.setVisible(true);
				dispose();
			}
		});
		addBtn.setBackground(new Color(255, 255, 255));
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addBtn.setBounds(20, 585, 212, 44);
		addBtn.setContentAreaFilled(true);
		addBtn.setFocusPainted(false);
		contentPane.add(addBtn);
		
		JLabel recipeLbl = new JLabel("<html>Recipe<br>List<html>");
		recipeLbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		recipeLbl.setBounds(20, 72, 219, 106);
		contentPane.add(recipeLbl);
		
		JButton recipeBtn = new JButton("Recipes");
		recipeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Recipe f3 = new Recipe(groceryTable, groceryDtm, inventoryTable, inventoryDtm, createButton, ingredientsTable, ingredientsDtm);
				f3.setVisible(true);
				dispose();
			}
		});
		recipeBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		recipeBtn.setBounds(485, 13, 99, 29);
		recipeBtn.setOpaque(false);
		recipeBtn.setFocusPainted(false);
		recipeBtn.setBorderPainted(false);
		recipeBtn.setContentAreaFilled(false);
		contentPane.add(recipeBtn);
		
		JButton inventoryBtn = new JButton("Inventory");
		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventory f2 = new Inventory(groceryTable, groceryDtm);
				f2.setVisible(true);
				dispose();
			}
		});
		inventoryBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		inventoryBtn.setBounds(354, 13, 117, 29);
		inventoryBtn.setOpaque(false);
		inventoryBtn.setFocusPainted(false);
		inventoryBtn.setBorderPainted(false);
		inventoryBtn.setContentAreaFilled(false);
		contentPane.add(inventoryBtn);
		
		JButton groceryBtn = new JButton("Grocery");
		groceryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Grocery f1 = new Grocery(inventoryTable, inventoryDtm);
				f1.setVisible(true);
				dispose();
			}
		});
		groceryBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		groceryBtn.setBounds(237, 13, 97, 29);
		groceryBtn.setOpaque(false);
		groceryBtn.setFocusPainted(false);
		groceryBtn.setBorderPainted(false);
		groceryBtn.setContentAreaFilled(false);
		contentPane.add(groceryBtn);
		
		JLabel nameLbl = new JLabel("SmartCook");
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		nameLbl.setBounds(20, 10, 146, 29);
		contentPane.add(nameLbl);
		
		JLabel menuLbl = new JLabel("");
		menuLbl.setBackground(new Color(194, 144, 107));
		menuLbl.setBounds(0, 0, 1200, 54);
		menuLbl.setOpaque(true);
		menuLbl.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		contentPane.add(menuLbl);
		
		JLabel sideLbl = new JLabel("");
		sideLbl.setBackground(new Color(223, 193, 172));
		sideLbl.setBounds(0, 43, 254, 720);
		sideLbl.setOpaque(true);
		sideLbl.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		contentPane.add(sideLbl);
		
		bkg = new JLabel("");
		bkg.setBackground(new Color(255, 255, 255));
		bkg.setBounds(249, 53, 937, 710);
		bkg.setOpaque(true);
		contentPane.add(bkg);
		
		JLabel infoBkg = new JLabel("");
		infoBkg.setBackground(new Color(255, 255, 255));
		infoBkg.setBounds(253, 53, 254, 190);
		infoBkg.setOpaque(true);
		infoBkg.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		contentPane.add(infoBkg);
		
		JLabel ingredientsBkg = new JLabel("");
		ingredientsBkg.setOpaque(true);
		ingredientsBkg.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		ingredientsBkg.setBackground(Color.WHITE);
		ingredientsBkg.setBounds(253, 242, 254, 521);
		contentPane.add(ingredientsBkg);
		
		JLabel instructionsBkg = new JLabel("");
		instructionsBkg.setOpaque(true);
		instructionsBkg.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		instructionsBkg.setBackground(Color.WHITE);
		instructionsBkg.setBounds(506, 53, 680, 710);
		contentPane.add(instructionsBkg);
		
	}
	
	public void readRecipeNames() {
		try
		{
			File f = new File("recipe1.txt");
			FileReader in = new FileReader(f);
			BufferedReader r = new BufferedReader(in);
			
			File f2 = new File("recipe2.txt");
			FileReader in2 = new FileReader(f2);
			BufferedReader r2 = new BufferedReader(in2);
			
			File f3 = new File("recipe3.txt");
			FileReader in3 = new FileReader(f3);
			BufferedReader r3 = new BufferedReader(in3);
			
			File f4 = new File("recipe4.txt");
			FileReader in4 = new FileReader(f4);
			BufferedReader r4 = new BufferedReader(in4);
			
			File f5 = new File("recipe5.txt");
			FileReader in5 = new FileReader(f5);
			BufferedReader r5 = new BufferedReader(in5);
			
			recipe1 = r.readLine();
			recipe2 = r2.readLine();
			recipe3 = r3.readLine();
			recipe4 = r4.readLine();
			recipe5 = r5.readLine();
			
			r.close();
			r2.close();
			r3.close();
			r4.close();
			r5.close();
			
		}
		catch (Exception e)
		{
			
		}
	}
	
	public void readButton() {
		try
		{
			File f1 = new File ("Button.txt");
			FileReader in = new FileReader(f1);
			BufferedReader r = new BufferedReader(in);
			
			createButton2 = Integer.parseInt(r.readLine());
			System.out.println(createButton2);
			r.close();
		}
		catch (Exception e1)
		{
			
		}
	}
	
	public void displayPage() {
		recipeNameLbl.setVisible(true);
		infoLbl.setVisible(true);
		ingredientsLbl.setVisible(true);
		ingredientsTa.setVisible(true);
		instructionsLbl.setVisible(true);
		instructionsTa.setVisible(true);
		bkg.setVisible(false);
	}
	
	public void displayRecipe() {
		try
		{
			File f1 = new File(fileName + ".txt"); // identifies the text file
			FileReader in = new FileReader (f1); // prepares to read the file
			BufferedReader r = new BufferedReader(in); // actually reads and extracts the contents of the file

			//Reads the number of lines that contains content
			File f2 = new File(fileName + ".txt"); // identifies the text file
			FileReader in2 = new FileReader (f2); // prepares to read the file
			BufferedReader r2 = new BufferedReader(in2); 
			
			int lines = 0;
			
			while (r2.readLine() != null) 
			{
				lines++;
			}
			r2.close();
			
			String n = r.readLine();
			String s = r.readLine();
			String p = r.readLine();
			String c = r.readLine();
			
			StringBuilder sb = new StringBuilder(); 
			String line = r.readLine(); 
			
			while (line != null) { 
				sb.append(line).append("\n"); 
				line = r.readLine(); 
			} 
			
			String instructions = sb.toString();
			
			if(n.equals(null)) {
				n = "";
			}
			
			if(s.equals(null)) {
				s = "";
			}
			
			if(p.equals(null)) {
				p = "";
			}
			
			if(c.equals(null)) {
				c = "";
			}
			
			recipeNameLbl.setText(n + "\n");
			infoLbl.setText("<html> Servings: " + s + "<br> Prep Time: " + p + "<br> Cook Time: " + c + "<html>");
			
			instructionsTa.setText(instructions);

			r.close();

		}
		catch(Exception e1)
		{

		}
		
		try
		{
			File f3 = new File(fileName + "Ing.txt"); // identifies the text file
			FileReader in3 = new FileReader (f3); // prepares to read the file
			BufferedReader r3 = new BufferedReader(in3); // actually reads and extracts the contents of the file

			//Reads the number of lines that contains content
			File f4 = new File(fileName + "Ing.txt"); // identifies the text file
			FileReader in4 = new FileReader (f4); // prepares to read the file
			BufferedReader r4 = new BufferedReader(in4); 
			
			int lines = 0;
			
			while (r4.readLine() != null) 
			{
				lines++;
			}
			r4.close();
			
			StringBuilder sb = new StringBuilder(); 
			String line = r3.readLine();
			
			while (line != null) { 
				
				for(int x = 0; x < 1; x++) {
					sb.append(line).append(" "); 
					line = r3.readLine();
				}
				
				sb.append(line).append("\n"); 
				line = r3.readLine();
				
				
			} 
			
			String ingredients = sb.toString();
			ingredientsTa.setText(ingredients);
			
			r3.close();
		}
		catch(Exception e2)
		{

		}
	}
	
	public void checkIngredients() {
		try
		{
			File f = new File(fileName + "Ing.txt"); 
			FileReader in = new FileReader(f);
			BufferedReader r = new BufferedReader(in);
			
			File f2 = new File(fileName + "Ing.txt");
			FileReader in2 = new FileReader(f2);
			BufferedReader r2 = new BufferedReader(in2);
			
			int lines = 0;
			
			while (r2.readLine() != null) 
			{
				lines++;
			}
			r2.close();
			
			StringBuilder sb = new StringBuilder();
			int missing = 0;
			
			for(int line = 0; line < lines/2; line++) {
				String q = r.readLine();
				String n = r.readLine();
				
				if(q != null && n != null) {
					
					//Check if 'n' is present in Inventory List
					int lineNo = isItemPresent(n, "InventoryList.txt"); 

					
					//If 'n' isn't present
			        if (lineNo == -1) {
			        	missing = 1;
			            System.out.println("The string " + n + " was not found in the file.");
			            
			            sb.append(q + " " + n).append("\n");
			           
			        }
			        
			        else {
			        	System.out.println("The string " + n + " was found on line " + lineNo + " of the file.");
			            
			            //lineNo is the line containing 'n'
			            int lineQuantity = lineNo + 3;
			            String qInventory = readLineFromFile(lineQuantity, "InventoryList.txt");

			            if (qInventory != null) {
			                
			                
			            } else {
			               
			            }
			        }
					
				}
			}
			
			if(missing == 1) {
				String missingItems = sb.toString();
		        MissingItems f3 = new MissingItems(missingItems, groceryTable2, groceryDtm2);
	        	f3.setVisible(true);
			}
			
		}
		catch (Exception e)
		{
			
		}
	}
	
	
	private static int isItemPresent(String n, String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;
            int lineNo = 1;
            while ((line = reader.readLine()) != null) {
                if (line.contains(n)) { // check if the line contains the string
                    reader.close();
                    return lineNo;
                }
                lineNo++;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1; // the string was not found in the file
    }
	
	private static String readLineFromFile(int lineNo, String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int currentLine = 1;
            while ((line = reader.readLine()) != null) {
                if (currentLine == lineNo) { // check if the current line number matches the desired line number
                    reader.close();
                    return line;
                }
                currentLine++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // the desired line number was not found in the file
    }
	
	public void saveGrocery() {
		
		int numOfRows = groceryDtm2.getRowCount();

		try 
		{
			File f = new File("GroceryList.txt"); 
			FileOutputStream in = new FileOutputStream(f); 
			PrintWriter w = new PrintWriter(in); 

			for(int x = 0; x < numOfRows; x++)
			{
				for(int y = 0 ; y < 6; y++)
				{
					w.println((String) groceryDtm2.getValueAt(x, y));
				}	
			}

			w.close();
		}
		catch (Exception ea)
		{

		}
	}
}
