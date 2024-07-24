package kitchprogram;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class AddEditIngredients extends JFrame {

	private JPanel contentPane;
	private JTextField nameTf;
	private JTextField quantityTf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEditIngredients frame = new AddEditIngredients();
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
	public AddEditIngredients() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLbl = new JLabel("Add Item");
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setForeground(new Color(194, 144, 107));
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 32));
		titleLbl.setBounds(207, -1, 185, 89);
		contentPane.add(titleLbl);
		
		JLabel fieldsLbl = new JLabel("<html> Item Name  <br><br> Quantity  <html>");
		fieldsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldsLbl.setBounds(50, 58, 129, 140);
		contentPane.add(fieldsLbl);
		
		nameTf = new JTextField();
		nameTf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameTf.setColumns(10);
		nameTf.setBounds(231, 88, 320, 34);
		contentPane.add(nameTf);
		
		quantityTf = new JTextField();
		quantityTf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		quantityTf.setColumns(10);
		quantityTf.setBounds(231, 132, 160, 34);
		contentPane.add(quantityTf);
		
		JComboBox unitMenu = new JComboBox(new Object[]{});
		unitMenu.setSelectedIndex(0);
		unitMenu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		unitMenu.setBackground(Color.WHITE);
		unitMenu.setBounds(391, 132, 160, 34);
		contentPane.add(unitMenu);
	}
}
