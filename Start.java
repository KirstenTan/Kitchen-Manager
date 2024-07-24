package kitchprogram;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import atmcode.ATMCode4;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Start extends JFrame {

	private JPanel contentPane;
	static int sm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
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
	public Start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(196, 144, 107));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nameLbl = new JLabel("SmartCook");
		nameLbl.setForeground(new Color(0, 0, 0));
		nameLbl.setFont(new Font("Tahoma", Font.BOLD, 40));
		nameLbl.setBounds(482, 401, 256, 105);
		contentPane.add(nameLbl);
		
		JLabel logoLbl = new JLabel("");
		logoLbl.setIcon(new ImageIcon(Start.class.getResource("/assets2/Chef's Hat.png")));
		logoLbl.setBounds(446, 165, 306, 312);
		contentPane.add(logoLbl);
		
		JButton groceryBtn = new JButton("Start!");
		groceryBtn.setBackground(new Color(255, 255, 255));
		groceryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Grocery f1 = new Grocery(null, null);
				f1.setVisible(true);
				dispose();
			}
		});
		groceryBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		groceryBtn.setBounds(469, 501, 250, 43);
		groceryBtn.setOpaque(true);
		groceryBtn.setFocusPainted(false);
		groceryBtn.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		contentPane.add(groceryBtn);
	}
	
}
