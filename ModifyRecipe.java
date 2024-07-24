package kitchprogram;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JComboBox;

public class AddEditRecipe extends JFrame {

	private JPanel contentPane;
	private JTextField nameTf;
	private JTextField servingsTf;
	private JTextField prepTf;
	private JTextField cookTf;
	private JLabel servingsLbl;
	private JLabel prepLbl;
	private JLabel cookLbl;
	private JLabel instructionsLbl;
	private JLabel fieldsLbl;
	private JTextField ingredientTf;
	private JTextField quantityTf;
	private JTextArea instructionsTa;
	private JComboBox unitMenu;
	private JButton clearBtn;
	static int selRow;
	static int createButton;
	static String function2;
	static String recipeName2;
	
	DefaultTableModel ingredientsDtm = new DefaultTableModel();
	private JTable ingredientsTable = new JTable(ingredientsDtm);
	final Object[] columnIng = new Object[2];
	
	private static JTable groceryTable;
	static DefaultTableModel groceryDtm = new DefaultTableModel();
	final Object[] columnGro = new Object[6];
	
	private static JTable inventoryTable;
	static DefaultTableModel inventoryDtm = new DefaultTableModel();
	final Object[] columnInv = new Object[7];
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEditRecipe frame = new AddEditRecipe(createButton, function2, recipeName2);
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
	public AddEditRecipe(int createButton2, String function, String recipeName) {
		
		createButton = createButton2;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLbl = new JLabel("Add Recipe");
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setForeground(new Color(194, 144, 107));
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 32));
		titleLbl.setBounds(378, 0, 185, 89);
		contentPane.add(titleLbl);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		JLabel nameLbl = new JLabel("Recipe Name");
		nameLbl.setBackground(new Color(255, 255, 255));
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameLbl.setBounds(34, 100, 120, 34);
		nameLbl.setOpaque(true);
		nameLbl.setBorder(border);
		contentPane.add(nameLbl);
		
		servingsLbl = new JLabel("Servings");
		servingsLbl.setBackground(new Color(255, 255, 255));
		servingsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		servingsLbl.setBounds(34, 150, 120, 34);
		servingsLbl.setOpaque(true);
		servingsLbl.setBorder(border);
		contentPane.add(servingsLbl);
		
		prepLbl = new JLabel("Prep Time");
		prepLbl.setBackground(new Color(255, 255, 255));
		prepLbl.setHorizontalAlignment(SwingConstants.LEFT);
		prepLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		prepLbl.setBounds(34, 183, 120, 34);
		prepLbl.setOpaque(true);
		prepLbl.setBorder(border);
		contentPane.add(prepLbl);
		
		cookLbl = new JLabel("Cook Time");
		cookLbl.setBackground(new Color(255, 255, 255));
		cookLbl.setHorizontalAlignment(SwingConstants.LEFT);
		cookLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cookLbl.setBounds(34, 216, 120, 34);
		cookLbl.setOpaque(true);
		cookLbl.setBorder(border);
		contentPane.add(cookLbl);
		
		nameTf = new JTextField();
		nameTf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameTf.setBounds(153, 100, 281, 34);
		nameTf.setBorder(border);
		contentPane.add(nameTf);
		nameTf.setColumns(10);
		
		servingsTf = new JTextField();
		servingsTf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		servingsTf.setColumns(10);
		servingsTf.setBounds(153, 150, 281, 34);
		servingsTf.setBorder(border);
		contentPane.add(servingsTf);
		
		prepTf = new JTextField();
		prepTf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		prepTf.setColumns(10);
		prepTf.setBounds(153, 183, 281, 34);
		prepTf.setBorder(border);
		contentPane.add(prepTf);
		
		cookTf = new JTextField();
		cookTf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cookTf.setColumns(10);
		cookTf.setBounds(153, 216, 281, 34);
		cookTf.setBorder(border);
		contentPane.add(cookTf);
		
		instructionsLbl = new JLabel("Instructions");
		instructionsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		instructionsLbl.setOpaque(true);
		instructionsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		instructionsLbl.setBackground(Color.WHITE);
		instructionsLbl.setBounds(495, 100, 400, 34);
		instructionsLbl.setBorder(border);
		contentPane.add(instructionsLbl);
		
		instructionsTa = new JTextArea();
		instructionsTa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		instructionsTa.setWrapStyleWord(true);
		instructionsTa.setLineWrap(true);
		instructionsTa.setBorder(border);
		instructionsTa.setBounds(495, 133, 400, 555);
		contentPane.add(instructionsTa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 266, 400, 313);
		contentPane.add(scrollPane);
		
		ingredientsTable = new JTable();
		ingredientsTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ingredientsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selRow = ingredientsTable.getSelectedRow();
				ingredientTf.setText((String)ingredientsTable.getValueAt(selRow, 1));
				
				String quantityValue = ingredientsTable.getValueAt(selRow, 0).toString();
				String [] quantityParts = quantityValue.split(" ");
				String q = (quantityParts[0]);
				String u = (quantityParts[1]);

				quantityTf.setText(q);
				unitMenu.setSelectedItem(u);
			}
		});
		Object[] columnHeaders = {"Quantity", "Item Name"}; 
		ingredientsDtm.setColumnIdentifiers(columnHeaders);
		ingredientsTable.setModel(ingredientsDtm);
		ingredientsTable.setDefaultEditor(Object.class, null);
		ingredientsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		scrollPane.setViewportView(ingredientsTable);
		
		fieldsLbl = new JLabel("<html> Ingredient  <br><br> Quantity  <html>");
		fieldsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldsLbl.setBounds(34, 578, 129, 140);
		contentPane.add(fieldsLbl);
		
		ingredientTf = new JTextField();
		ingredientTf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ingredientTf.setColumns(10);
		ingredientTf.setBounds(153, 609, 281, 34);
		contentPane.add(ingredientTf);
		
		quantityTf = new JTextField();
		quantityTf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		quantityTf.setColumns(10);
		quantityTf.setBounds(153, 654, 140, 34);
		contentPane.add(quantityTf);
		
		if(function.equals("edit")) {
			
			try
			{
				File f = new File("recipe1.txt");
				FileReader in = new FileReader (f);
				BufferedReader r = new BufferedReader (in);
				
				String n = r.readLine();
				
				if(recipeName.equals(n)) {
					String servings = r.readLine();
					String prep = r.readLine();
					String cook = r.readLine();
					String instructions = r.readLine();
					
					nameTf.setText(recipeName);
					servingsTf.setText(servings);
					prepTf.setText(prep);
					cookTf.setText(cook);
					instructionsTa.setText(instructions);
				}
				
			}
			catch (Exception e)
			{
				
			}
			
		}
		
		String units [] = {"Unit...","pcs","ml","L","tsp","tbsp","cup","g","kg"};
		
		unitMenu = new JComboBox(units);
		unitMenu.setSelectedIndex(0);
		unitMenu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		unitMenu.setBackground(Color.WHITE);
		unitMenu.setBounds(293, 654, 141, 34);
		contentPane.add(unitMenu);
		
		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createButton = createButton + 1;
				saveRecipe();
				saveRecipeNames();
				saveButton();
			}
		});
		saveBtn.setForeground(new Color(194, 144, 107));
		saveBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		saveBtn.setFocusPainted(false);
		saveBtn.setBackground(Color.WHITE);
		saveBtn.setBounds(682, 712, 100, 40);
		contentPane.add(saveBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setForeground(new Color(194, 144, 107));
		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cancelBtn.setFocusPainted(false);
		cancelBtn.setBackground(Color.WHITE);
		cancelBtn.setBounds(795, 712, 100, 40);
		contentPane.add(cancelBtn);
		
		JButton addBtn = new JButton("Add Item");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u;
				
				if(unitMenu.getSelectedItem().toString() == "Unit...") {
					u = "";
				}
				else {
					u = unitMenu.getSelectedItem().toString();
				}
				
				String i = ingredientTf.getText();
				String q = quantityTf.getText();
				
				columnIng[0] = q + " " + u;
				columnIng[1] = i;
				
				ingredientsDtm.addRow(columnIng);
			}
		});
		addBtn.setForeground(new Color(194, 144, 107));
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addBtn.setFocusPainted(false);
		addBtn.setBackground(Color.WHITE);
		addBtn.setBounds(34, 712, 150, 40);
		contentPane.add(addBtn);
		
		JButton editBtn = new JButton("Edit Item");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String u;
				
				if(unitMenu.getSelectedItem().toString() == "Unit...") {
					u = "";
				}
				else {
					u = unitMenu.getSelectedItem().toString();
				}
				
				String q = quantityTf.getText();
				String i = ingredientTf.getText();
				
				ingredientsDtm.setValueAt(q + " " + u, selRow, 0);
				ingredientsDtm.setValueAt(i, selRow, 1);
			}
		});
		editBtn.setForeground(new Color(194, 144, 107));
		editBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		editBtn.setFocusPainted(false);
		editBtn.setBackground(Color.WHITE);
		editBtn.setBounds(194, 712, 150, 40);
		contentPane.add(editBtn);
		
		clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingredientTf.setText(null);
				unitMenu.setSelectedItem(null);
				quantityTf.setText(null);
			}
		});
		clearBtn.setForeground(new Color(194, 144, 107));
		clearBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		clearBtn.setFocusPainted(false);
		clearBtn.setBackground(Color.WHITE);
		clearBtn.setBounds(354, 712, 80, 40);
		contentPane.add(clearBtn);
		
	}
	
	public void saveRecipe() {
		String recipeName = nameTf.getText();
		String servings = servingsTf.getText();
		String prep = prepTf.getText();
		String cook = cookTf.getText();
		
		String instructions = instructionsTa.getText();
		
		String fileName = "";
		
		if(createButton  == 1) {
			fileName = "recipe1";
		}
		
		if(createButton  == 2) {
			fileName = "recipe2";
		}
		
		if(createButton  == 3) {
			fileName = "recipe3";
		}
		
		if(createButton  == 4) {
			fileName = "recipe4";
		}
		
		if(createButton  == 5) {
			fileName = "recipe5";
		}
		
		
		int rowCount = ingredientsDtm.getRowCount();

		try 
		{
			File f1 = new File(fileName + ".txt"); // Creates the text file
			FileOutputStream in = new FileOutputStream(f1); // Prepares the text file for printing
			PrintWriter w = new PrintWriter(in); // Allows for the text fill to be printed
			
			w.println(recipeName);
			w.println(servings);
			w.println(prep);
			w.println(cook);
			w.println(instructions);

			w.close();
		}
		catch (Exception ea)
		{

		}
		
		try
		{
			File f2 = new File(fileName  + "Ing.txt");
			FileOutputStream in2 = new FileOutputStream(f2);
			PrintWriter w2 = new PrintWriter(in2);
			
			for(int x = 0; x < rowCount; x++)
			{
				for(int y = 0; y  < 2; y++)
				{
					w2.println((String)ingredientsDtm.getValueAt(x, y));
				}
			}
			
			w2.close();
			
		}
		catch (Exception e2)
		{

		}
		
		Recipe f1 = new Recipe(groceryTable, groceryDtm, inventoryTable, inventoryDtm, createButton, ingredientsTable, ingredientsDtm);
		f1.setVisible(true);
		dispose();
	}
	
	public void saveRecipeNames() {
		
		String recipeName = nameTf.getText();
		
		try
		{
			File f1 = new File("RecipeNames.txt");
			FileOutputStream in = new FileOutputStream(f1, true);
			PrintWriter w = new PrintWriter(in);
			
			w.println(recipeName);
			w.close();
		}
		catch (Exception e1) {
			
		}
	}
	
	public void saveButton() {
		try
		{
			File f1 = new File("Button.txt");
			FileOutputStream in = new FileOutputStream(f1);
			PrintWriter w = new PrintWriter(in);
			
			w.println(createButton);
			w.close();
		}
		catch (Exception e1)
		{
			
		}
	}
	
}
