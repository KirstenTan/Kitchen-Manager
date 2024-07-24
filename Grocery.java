package kitchprogram;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Grocery extends JFrame {

	private JPanel contentPane;
	private JTextField searchTf;
	static String page;
	static String function; 
	static int bought; 
	static int selRow; 
	
	DefaultTableModel groceryDtm = new DefaultTableModel();
	private JTable groceryTable = new JTable(groceryDtm);
	final Object[] columnGro = new Object[6];
	
	private TableRowSorter<TableModel> rowSorter
    = new TableRowSorter<>(groceryTable.getModel());
	
	private static JTable inventoryTable2;
	static DefaultTableModel inventoryDtm2 = new DefaultTableModel();
	final Object[] columnInv = new Object[7];
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grocery frame = new Grocery(inventoryTable2, inventoryDtm2);
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
	public Grocery(JTable inventoryTable, DefaultTableModel inventoryDtm) {
		
		page = "grocery";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(264, 62, 912, 690);
		contentPane.add(scrollPane);
		
		groceryTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		groceryTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selRow = groceryTable.getSelectedRow();
			}
		});
		scrollPane.setViewportView(groceryTable);
		scrollPane.setViewportView(groceryTable);
		Object[] columnHeaders = {"Item Name","Brand", "Category", "Quantity", "Expiry", "Description"}; 
		groceryDtm.setColumnIdentifiers(columnHeaders);
		groceryTable.setModel(groceryDtm);
		groceryTable.setRowSorter(rowSorter);
		groceryTable.getColumnModel().getColumn(0).setPreferredWidth(120);
		groceryTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		groceryTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		groceryTable.getColumnModel().getColumn(5).setPreferredWidth(100);
		groceryTable.setDefaultEditor(Object.class, null);
		
		searchTf = new JTextField();	
		searchTf.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		searchTf.setForeground(new Color(0, 0, 0));
		searchTf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		searchTf.setBounds(594, 8, 578, 38);
		contentPane.add(searchTf);
		searchTf.setColumns(10);
		
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
                throw new UnsupportedOperationException("Not supported yet."); 
            }});
		 
		JButton buyAllBtn = new JButton("Buy All");
		buyAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int rowCountGro = groceryDtm.getRowCount();
				
				try
				{
					File f = new File("InventoryList.txt");
					FileWriter fw = new FileWriter(f, true);
					PrintWriter w = new PrintWriter(fw);
					
					for(int x = 0; x < rowCountGro; x++){
						for(int y = 0 ; y < 6; y++){
							w.println((String) groceryDtm.getValueAt(x, y));
						}	
						w.println("");
					}
					
					w.close();
				}
				catch (Exception e2)
				{
					
				}
				
				int rowCount = groceryDtm.getRowCount();
				for(int x = rowCount - 1; x >= 0; x--) {
					groceryDtm.removeRow(x);
				}
				saveGrocery();
			}
		});
		buyAllBtn.setBackground(new Color(255, 255, 255));
		buyAllBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buyAllBtn.setBounds(20, 422, 212, 44);
		buyAllBtn.setContentAreaFilled(true);
		buyAllBtn.setFocusPainted(false);
		contentPane.add(buyAllBtn);
		
		JButton buyBtn = new JButton("Buy Item");
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					File f = new File("InventoryList.txt");
					FileWriter fw = new FileWriter(f, true);
					PrintWriter w = new PrintWriter(fw);
					
					for(int x = 0; x < 6; x++) {
						w.println((String)groceryTable.getValueAt(selRow, x));
					}
					
					w.println("");
					
					w.close();
					fw.close();
				}
				catch (Exception e2)
				{
					
				}
					
				groceryDtm.removeRow(selRow);
				saveGrocery();
				
			}
		});
		buyBtn.setBackground(new Color(255, 255, 255));
		buyBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buyBtn.setBounds(20, 367, 212, 44);
		buyBtn.setContentAreaFilled(true);
		buyBtn.setFocusPainted(false);
		contentPane.add(buyBtn);
		
		JButton deleteBtn = new JButton("Delete Item");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				groceryDtm.removeRow(selRow);
				saveGrocery();
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
		
		JLabel groceryLbl = new JLabel("<html>Grocery<br>List<html>");
		groceryLbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		groceryLbl.setBounds(20, 72, 219, 106);
		contentPane.add(groceryLbl);
		
		JButton recipeBtn = new JButton("Recipes");
		recipeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Recipe f3 = new Recipe(groceryTable, groceryDtm, inventoryTable, inventoryDtm, 0, null, null);
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
		
		// Load Grocery File
		
		try
		{
			// Open file
			
			File f = new File( "GroceryList.txt");
			FileReader in = new FileReader (f);
			BufferedReader r = new BufferedReader(in);

			// Read the number of lines that contains content
			
			File f2 = new File( "GroceryList.txt"); 
			FileReader in2 = new FileReader (f2);
			BufferedReader r2 = new BufferedReader(in2); 
			
			int lines = 0;
			while (r2.readLine() != null) 
			{
				lines++;
			}
			r2.close();

			// Add each line back to the table
			
			for(int rw = 0; rw < lines/6; rw++)
			{
				for(int cl = 0; cl < 6; cl++)
				{
					columnGro[cl] = r.readLine();
				}
				groceryDtm.addRow(columnGro);
			}
			r.close();

		}
		catch(Exception e2)
		{

		}
		
	}
	
	public void saveGrocery() {
		int rowCount = groceryDtm.getRowCount();
		
		// Print table content into text file
		
		try 
		{
			File f = new File("GroceryList.txt");
			FileOutputStream in = new FileOutputStream(f); 
			PrintWriter w = new PrintWriter(in);

			for(int x = 0; x < rowCount; x++)
			{
				for(int y = 0 ; y < 6; y++)
				{
					w.println((String) groceryDtm.getValueAt(x, y));
				}	
			}

			w.close();
		}
		catch (Exception ea)
		{

		}
	}
	
	public void AddRowToJTable(Object[] dataRow) {
		DefaultTableModel groceryDtm = (DefaultTableModel)groceryTable.getModel();
		groceryDtm.addRow(dataRow);
	}
}
