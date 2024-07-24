package kitchprogram;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AddEditItem extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField nameTf;
	private JTextField brandTf;
	private JComboBox categoryMenu;
	private JTextField quantityTf;
	private JComboBox unitMenu;
	private JComboBox monthMenu;
	private JComboBox dayMenu;
	private JComboBox yearMenu;
	private JTextField descriptionTf;
	private JTextField smQuantityTf;
	private JComboBox smUnitMenu;
	
	private JButton cancelBtn;
	
	static String sm;
	
	static String page2;
	static String function2;
	static int selRow2; 
	
	private static JTable groceryTable2;
	static DefaultTableModel groceryDtm2 = new DefaultTableModel();
	final Object[] columnGro = new Object[6];
	
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
					AddEditItem frame = new AddEditItem(groceryTable2, groceryDtm2, inventoryTable2, inventoryDtm2, page2, function2, selRow2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param dtm 
	 * @param table 
	 */

	public AddEditItem (JTable groceryTable, DefaultTableModel groceryDtm, JTable inventoryTable, DefaultTableModel inventoryDtm, String page, String function, int selRow) {
		
		groceryTable2 = groceryTable;
		groceryDtm2 = groceryDtm;
		inventoryTable2 = inventoryTable;
		inventoryDtm2 = inventoryDtm;
		page2 = page;
		function2 = function;
		selRow2 = selRow;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 620, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLbl = new JLabel("Add Item");
		if(function == "edit" ) {
			titleLbl.setText("Edit Item");
		}
		titleLbl.setForeground(new Color(194, 144, 107));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 32));
		titleLbl.setBounds(207, -1, 185, 89);
		contentPane.add(titleLbl);
		
		JLabel fieldsLbl = new JLabel("<html> Item Name <br><br> Brand <br><br> Category <br><br> Quantity <br><br> Expiry <br><br> Description <html>");
		fieldsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldsLbl.setBounds(49, 48, 129, 341);
		contentPane.add(fieldsLbl);
		
		nameTf = new JTextField();
		nameTf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameTf.setBounds(231, 88, 320, 34);
		contentPane.add(nameTf);
		nameTf.setColumns(10);
		
		brandTf = new JTextField();
		brandTf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		brandTf.setColumns(10);
		brandTf.setBounds(231, 132, 320, 34);
		contentPane.add(brandTf);
		
		String categories [] = {"","Baking Goods","Beverages","Bread & Bakery","Canned Goods","Condiments & Spices","Dairy","Frozen Foods","Fruits","Grains","Kitchen Tools","Meat","Seafood","Snacks","Utensils","Vegetables"};
		
		categoryMenu = new JComboBox(categories);
		categoryMenu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		categoryMenu.setBackground(new Color(255, 255, 255));
		categoryMenu.setBounds(231, 176, 320, 34);
		categoryMenu.setSelectedIndex(0);
		categoryMenu.addActionListener(this);
		contentPane.add(categoryMenu);
		
		quantityTf = new JTextField();
		quantityTf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		quantityTf.setColumns(10);
		quantityTf.setBounds(231, 220, 160, 34);
		contentPane.add(quantityTf);
		
		String units [] = {"","pcs","ml","L","tsp","tbsp","cup","g","kg"};
		
		unitMenu = new JComboBox(units);
		unitMenu.setSelectedIndex(0);
		unitMenu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		unitMenu.setBackground(Color.WHITE);
		unitMenu.setBounds(391, 220, 160, 34);
		contentPane.add(unitMenu);
		
		String months [] = {"","Jan","Feb","March","April","May","June","July","Aug","Sept","Oct","Nov","Dec"};
		
		monthMenu = new JComboBox(months);
		monthMenu.setSelectedIndex(0);
		monthMenu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		monthMenu.setBackground(Color.WHITE);
		monthMenu.setBounds(231, 264, 120, 34);
		contentPane.add(monthMenu);
		
		String days [] = new String [32];
			days[0] = null;
			int y = 1;
			for(int x = 1; x < 32; x++) {
				days[x] = y + "";
				y++;
			}
		
		dayMenu = new JComboBox(days);
		dayMenu.setSelectedIndex(0);
		dayMenu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dayMenu.setBackground(Color.WHITE);
		dayMenu.setBounds(351, 264, 80, 34);
		contentPane.add(dayMenu);
		
		String years [] = new String [30];
			years[0] = null;
			int z = 2023;
			for(int x = 1; x < 30; x++) {
				years[x] = z + "";
				z++;
			}
		
		yearMenu = new JComboBox(years);
		yearMenu.setSelectedIndex(0);
		yearMenu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		yearMenu.setBackground(Color.WHITE);
		yearMenu.setBounds(431, 264, 120, 34);
		contentPane.add(yearMenu);
		
		descriptionTf = new JTextField();
		descriptionTf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		descriptionTf.setColumns(10);
		descriptionTf.setBounds(231, 309, 320, 34);
		contentPane.add(descriptionTf);
		
		if(page == "inventory") {
			fieldsLbl.setText("<html> Item Name <br><br> Brand <br><br> Category <br><br> Quantity <br><br> Expiry <br><br> Description <br><br> Set Min. <html>");
			fieldsLbl.setBounds(49, 48, 129, 375);
			smQuantityTf = new JTextField();
			smQuantityTf.setFont(new Font("Tahoma", Font.PLAIN, 18));
			smQuantityTf.setColumns(10);
			smQuantityTf.setBounds(231, 353, 160, 34);
			contentPane.add(smQuantityTf);
			
			smUnitMenu = new JComboBox(units);
			smUnitMenu.setSelectedIndex(0);
			smUnitMenu.setFont(new Font("Tahoma", Font.PLAIN, 18));
			smUnitMenu.setBackground(Color.WHITE);
			smUnitMenu.setBounds(391, 353, 160, 34);
			contentPane.add(smUnitMenu);
		}
		
		if(function == "edit") {
			
			if(page == "grocery") {
				
				String q = "";
				String u = "";
				String m = "";
				String d = "";
				String year = "";
				
				String qu = groceryTable.getValueAt(selRow, 3).toString();
				String [] quParts = qu.split(" ");
				if(quParts.length == 2) {
					q = quParts[0];
					u = quParts[1];
				}
				
				String expiry = groceryTable.getValueAt(selRow, 4).toString();
				String [] exParts = expiry.split(" ");
				if(exParts.length == 2) {
					m = exParts[0];
					d = exParts[1];
					year = exParts[2];
				}
				
				nameTf.setText((String)groceryTable.getValueAt(selRow, 0));
				brandTf.setText((String)groceryTable.getValueAt(selRow, 1));
				categoryMenu.setSelectedItem((String)groceryTable.getValueAt(selRow, 2));
				quantityTf.setText(q);
				unitMenu.setSelectedItem(u);
				monthMenu.setSelectedItem(m);
				dayMenu.setSelectedItem(d);
				yearMenu.setSelectedItem(year);
				descriptionTf.setText((String)groceryTable.getValueAt(selRow, 5));
			}
			
			if(page == "inventory") {
				
				String q = "";
				String u = "";
				String m = "";
				String d = "";
				String year = "";
				String smq = "";
				String smu = "";
				
				String qu = inventoryTable.getValueAt(selRow, 3).toString();
				String [] quParts = qu.split(" ");
				if(quParts.length == 2) {
					q = quParts[0];
					u = quParts[1];
				}
				
				String expiry = inventoryTable.getValueAt(selRow, 4).toString();
				String [] exParts = expiry.split(" ");
				if(exParts.length == 2) {
					m = exParts[0];
					d = exParts[1];
					year = exParts[2];
				}
				
				String smqu = inventoryTable.getValueAt(selRow, 6).toString();
				String [] smquParts = smqu.split(" ");
				if(smquParts.length == 2) {
					smq = smquParts[0];
					smu = smquParts[1];
				}
				
				nameTf.setText((String)inventoryTable.getValueAt(selRow, 0));
				brandTf.setText((String)inventoryTable.getValueAt(selRow, 1));
				categoryMenu.setSelectedItem((String)inventoryTable.getValueAt(selRow, 2));
				quantityTf.setText(q);
				unitMenu.setSelectedItem(u);
				monthMenu.setSelectedItem(m);
				dayMenu.setSelectedItem(d);
				yearMenu.setSelectedItem(year);
				descriptionTf.setText((String)inventoryTable.getValueAt(selRow, 5));
				smQuantityTf.setText(smq);
				smUnitMenu.setSelectedItem(smu);
			}
			
		}
		
		JButton saveBtn = new JButton("Save");
		saveBtn = new JButton("Save");
		saveBtn.setForeground(new Color(194, 144, 107));
		saveBtn.setBackground(new Color(255, 255, 255));
		saveBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		saveBtn.setFocusPainted(false);
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Get values
				
				String quantity = quantityTf.getText();
				String unit;
					if(unitMenu.getSelectedItem() == null) {
						unit = "";
					}
					else {
						unit = unitMenu.getSelectedItem().toString();
					}
				String month = monthMenu.getSelectedItem().toString();
				String day;
					if(dayMenu.getSelectedItem() == null) {
						day = "";
					}
					else {
						day = dayMenu.getSelectedItem().toString();
					}
				String year;
					if(yearMenu.getSelectedItem() == null) {
						year = "";
					}
					else {
						year = yearMenu.getSelectedItem().toString();
					}
					
				String n = nameTf.getText();
				String b = brandTf.getText();
				String c = categoryMenu.getSelectedItem().toString();
				String q = quantity + " " + unit;
				String ex = month + " " + day + " " + year;
				String d = descriptionTf.getText();
				
				if(page == "grocery") {
					
					if(function == "add") {
						
						columnGro[0] = n;
						columnGro[1] = b;
						columnGro[2] = c;
						columnGro[3] = q;
						columnGro[4] = ex;
						columnGro[5] = d;
						
						groceryDtm.addRow(columnGro);
					}
					
					if(function == "edit") {
						groceryDtm.setValueAt(n, selRow, 0);
						groceryDtm.setValueAt(b, selRow, 1);
						groceryDtm.setValueAt(c, selRow, 2);
						groceryDtm.setValueAt(q, selRow, 3);
						groceryDtm.setValueAt(ex, selRow, 4);
						groceryDtm.setValueAt(d, selRow, 5);
					}
					
					saveGrocery();
					Grocery f1 = new Grocery(inventoryTable, inventoryDtm);
					f1.setVisible(true);
					dispose();
				}
				
				if(page == "inventory") {
					
					//Get additional values
					 
					String smQuantity = smQuantityTf.getText();
					String smUnit;
						if(smUnitMenu.getSelectedItem() == null) {
							smUnit = "";
						}
						else {
							smUnit = smUnitMenu.getSelectedItem().toString();
						}
						
					String sm = smQuantity + " " + smUnit;
					
					if(function == "add") {
			
						columnInv[0] = n;
						columnInv[1] = b;
						columnInv[2] = c;
						columnInv[3] = q;
						columnInv[4] = ex;
						columnInv[5] = d;
						columnInv[6] = sm;
						
						inventoryDtm.addRow(columnInv);
					}
					
					if(function == "edit") {
						inventoryDtm.setValueAt(n, selRow, 0);
						inventoryDtm.setValueAt(b, selRow, 1);
						inventoryDtm.setValueAt(c, selRow, 2);
						inventoryDtm.setValueAt(q, selRow, 3);
						inventoryDtm.setValueAt(ex, selRow, 4);
						inventoryDtm.setValueAt(d, selRow, 5);
						inventoryDtm.setValueAt(sm, selRow, 6);
					}
					
					saveInventory();
					
					Inventory f2 = new Inventory(groceryTable, groceryDtm);
					f2.setVisible(true);
					dispose();
				}
			}
		});
		saveBtn.setBounds(338, 453, 100, 40);
		contentPane.add(saveBtn);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(page == "grocery") {
					Grocery f1 = new Grocery(null, null);
					f1.setVisible(true);
					dispose();
				}
				if(page == "inventory") {
					Inventory f2 = new Inventory(null, null);
					f2.setVisible(true);
					dispose();
				}
			}
		});
		cancelBtn.setForeground(new Color(194, 144, 107));
		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cancelBtn.setFocusPainted(false);
		cancelBtn.setBackground(Color.WHITE);
		cancelBtn.setBounds(451, 453, 100, 40);
		contentPane.add(cancelBtn);
		
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

	public void saveInventory() {
		
		int numOfRows = inventoryDtm2.getRowCount();

		try 
		{
			File f = new File("InventoryList.txt"); 
			FileOutputStream in = new FileOutputStream(f); 
			PrintWriter w = new PrintWriter(in); 

			for(int x = 0; x < numOfRows; x++)
			{
				for(int y = 0 ; y < 7; y++)
				{
					w.println((String) inventoryDtm2.getValueAt(x, y));
				}	
			}

			w.close();
		}
		catch (Exception ea)
		{

		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
