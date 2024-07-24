package kitchprogram;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.awt.event.ActionEvent;

public class Inventory extends JFrame {

	private JPanel contentPane;
	private JTextField searchTf;
	static String function;
	static String page;
	static int selRow;
	static int createButton;
	static int actualQuantity;
	static int actualMin;
	static int multiplier;
	static int multiplier2;
	
	DefaultTableModel inventoryDtm = new DefaultTableModel();
	private JTable inventoryTable = new JTable(inventoryDtm);
	final Object[] columnInv = new Object[7];
	
	private TableRowSorter<TableModel> rowSorter
    = new TableRowSorter<>(inventoryTable.getModel());

	private static JTable groceryTable2;
	static DefaultTableModel groceryDtm2 = new DefaultTableModel();
	final Object[] columnGro = new Object[6];
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory frame = new Inventory(groceryTable2, groceryDtm2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param displayFile2 
	 */
	public Inventory(JTable groceryTable, DefaultTableModel groceryDtm) {
		
		groceryTable2 = groceryTable;
		groceryDtm2 = groceryDtm;
		page = "inventory";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(264, 62, 912, 690);
		contentPane.add(scrollPane);
		
		inventoryTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inventoryTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selRow = inventoryTable.getSelectedRow();
			}
		});
		scrollPane.setViewportView(inventoryTable);
		scrollPane.setViewportView(inventoryTable);
		Object[] columnHeaders = {"Item Name","Brand", "Category", "Quantity", "Expiry", "Description", "Min."}; 
		inventoryDtm.setColumnIdentifiers(columnHeaders);
		inventoryTable.setModel(inventoryDtm);
		inventoryTable.setRowSorter(rowSorter);
		inventoryTable.getColumnModel().getColumn(0).setPreferredWidth(120);
		inventoryTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		inventoryTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		inventoryTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		inventoryTable.setDefaultEditor(Object.class, null);
		
		JButton deleteBtn = new JButton("Delete Item");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryDtm.removeRow(selRow);
				saveInventory();
			}
		});
		deleteBtn.setBackground(new Color(255, 255, 255));
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		deleteBtn.setBounds(20, 312, 212, 44);
		deleteBtn.setContentAreaFilled(true);
		deleteBtn.setFocusPainted(false);
		contentPane.add(deleteBtn);
		
		JButton editBtn = new JButton("Edit Item");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function = "edit";
				AddEditItem f4 = new AddEditItem(groceryTable, groceryDtm, inventoryTable, inventoryDtm, page, function, selRow);
				f4.setVisible(true);
				dispose();
			}
		});
		editBtn.setBackground(new Color(255, 255, 255));
		editBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		editBtn.setBounds(20, 257, 212, 44);
		editBtn.setContentAreaFilled(true);
		editBtn.setFocusPainted(false);
		contentPane.add(editBtn);
		
		JButton addBtn = new JButton("Add Item");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function = "add";
				AddEditItem f4 = new AddEditItem(groceryTable, groceryDtm, inventoryTable, inventoryDtm, page, function, selRow);
				f4.setVisible(true);
				dispose();
			}
		});
		addBtn.setBackground(new Color(255, 255, 255));
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addBtn.setBounds(20, 202, 212, 44);
		addBtn.setContentAreaFilled(true);
		addBtn.setFocusPainted(false);
		contentPane.add(addBtn);
		
		JLabel inventoryLbl = new JLabel("<html>Inventory<br>List<html>");
		inventoryLbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		inventoryLbl.setBounds(20, 72, 219, 106);
		contentPane.add(inventoryLbl);
		
		searchTf = new JTextField();
		searchTf.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		searchTf.setForeground(new Color(128, 128, 128));
		searchTf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		searchTf.setBounds(594, 8, 578, 38);
		contentPane.add(searchTf);
		searchTf.setColumns(10);
		
		inventoryTable.setRowSorter(rowSorter);
		
		searchTf.getDocument().addDocumentListener(new DocumentListener(){
  
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchTf.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchTf.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }});
		
		JButton recipeBtn = new JButton("Recipes");
		recipeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Recipe f3 = new Recipe(groceryTable, groceryDtm, inventoryTable, inventoryDtm, createButton, null, null);
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
		
		// Load Inventory File
		
		try
		{	
			// Open file
			
			File f = new File( "InventoryList.txt");
			FileReader in = new FileReader (f);
			BufferedReader r = new BufferedReader(in);

			// Read the number of lines that contains content
			
			File f2 = new File( "InventoryList.txt");
			FileReader in2 = new FileReader (f2);
			BufferedReader r2 = new BufferedReader(in2); 
			int lines = 0;
			while (r2.readLine() != null) 
			{
				lines++;
			}
			r2.close();

			// Add each line back into the table
			
			for(int rw = 0; rw < lines/7; rw++)
			{
				for(int cl = 0; cl < 7; cl++)
				{
					columnInv[cl] = r.readLine();
				}
				inventoryDtm.addRow(columnInv);
			}
			r.close();

		}
		catch(Exception e2)
		{

		}
		
		checkMin();
		
	}
	
	public void saveInventory () {
		int numOfRows = inventoryDtm.getRowCount();
		
		// Print table content into the text file
		
		try 
		{
			File f = new File("InventoryList.txt");
			FileOutputStream in = new FileOutputStream(f);
			PrintWriter w = new PrintWriter(in);

			for(int x = 0; x < numOfRows; x++)
			{
				for(int y = 0 ; y < 7; y++)
				{
					w.println((String) inventoryDtm.getValueAt(x, y));
				}	
			}

			w.close();
		}
		catch (Exception ea)
		{

		}
	}
	
	public void checkMin () {
	
		try {
			
			// Open files
			
			File f = new File( "InventoryList.txt");
			FileReader in = new FileReader (f);
			BufferedReader r = new BufferedReader(in);
			
			File f2 = new File( "InventoryList.txt");
			FileReader in2 = new FileReader (f2);
			BufferedReader r2 = new BufferedReader(in2); 
			
			int lines = 0;
			while (r2.readLine() != null) 
			{
				lines++;
			}
			r2.close();
			
			for(int rw = 0; rw < lines/7; rw++) {
			
				//Read lines
				
				String n = r.readLine();
				r.readLine();
				r.readLine();
				String qu = r.readLine();
				r.readLine();
				r.readLine();
				String smqu = r.readLine();
				
				//Split Quantity and Min
				
				double q = 0;
				String u = "";
				double smq = 0;
				String smu = "";
				
				String [] quParts = qu.split(" ");
				if(quParts.length == 2) {
					q = Double.parseDouble(quParts[0]);
					u = quParts[1];
					
					System.out.println(q);
				}
				
				String [] smquParts = smqu.split(" ");
				if(smquParts.length == 2) {
					smq = Double.parseDouble(smquParts[0]);
					smu = smquParts[1];
				}
				
				// Unit Conversions for "Unit"
				
				if(u.equals(smu) || u.equals("ml") || u.equals("g") ) {
					multiplier = 1;
				}
				
				if(u.equals("L") || u.equals("kg")) {
					multiplier = 1000;
				}
				
				if(u.equals("tsp")) {
					multiplier = 5;
				}
				
				if(u.equals("tbsp")) {
					multiplier = 15;
				}
				
				if(u.equals("cup")) {
					multiplier = 240;
				}
				
				// Unit Conversions for "Min Unit"
				
				if(smu.equals(u) || smu.equals("ml") || smu.equals("g")) {
					multiplier2 = 1;
				}
				
				if(smu.equals("L") || smu.equals("kg")) {
					multiplier2 = 1000;
				}
				
				if(smu.equals("tsp")) {
					multiplier2 = 5;
				}
				
				if(smu.equals("tbsp")) {
					multiplier2 = 15;
				}
				
				if(smu.equals("cup")) {
					multiplier2 = 240;
				}
					
				double actualQuantity = (double)multiplier * q;
				double actualMin = (double)multiplier2 * smq;
				
				// Check if item is in the Grocery List
				
				boolean isPresent = isItemPresent(n, "GroceryList.txt");
				
			    if (isPresent) {
			    	
			    } 
			    else {
			    	if(actualQuantity < actualMin) {
			    		
			    		double result = (double)(actualMin - actualQuantity)/multiplier2;
			    		String missing = result + " " + smu;
			    		
			    		columnGro[0] = n;
			    		columnGro[1] = "";
			    		columnGro[2] = "";
			    		columnGro[3] = missing;
			    		columnGro[4] = "";
			    		columnGro[5] = "";
			    		
			    		groceryDtm2.addRow(columnGro);
						
					}		            
			    }
					
			}
			r.close();
			
		}
		catch (Exception e)
		{
			
		}
		 
		saveGrocery();
		
	}
	
	
	public void saveGrocery() {
		
		int numOfRows = groceryDtm2.getRowCount();
		
		// Print table content into text file
		
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
	
	 private static boolean isItemPresent(String n, String filename) {
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader(filename));

	            String line;
	            while ((line = reader.readLine()) != null) {
	                if (line.contains(n)) { // check if the line contains the string
	                    reader.close();
	                    return true;
	                }
	            }

	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return false;
	    }
	
}
