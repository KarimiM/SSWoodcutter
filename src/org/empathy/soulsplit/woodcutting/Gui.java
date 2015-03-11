package org.empathy.soulsplit.woodcutting;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9207874311091582181L;
	private JPanel contentPane;
	JCheckBox powerCut = new JCheckBox();
	String[] comboTypes = { "Regular", "Oaks", "Willows", "Maples", "Yews", "Magics" };
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox comboTypesList = new JComboBox(comboTypes);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
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
	public Gui() {
		setTitle("eWoodcutter - SoulSplit");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Create the combo box
		comboTypesList.setBounds(108, 97, 100, 20);
		contentPane.add(comboTypesList);

		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedIndex(e);
			}
		});
		start.setBounds(108, 191, 89, 23);
		contentPane.add(start);

		powerCut.setText("Powercut");
		powerCut.setBounds(108, 137, 100, 20);
		contentPane.add(powerCut);

		JLabel label = new JLabel("Choose Tree Type", JLabel.CENTER);
		label.setFont(new Font("Serif", Font.ITALIC, 16));
		label.setBounds(81, 66, 154, 20);
		contentPane.add(label);

	}

	private void selectedIndex(ActionEvent e) {
		if (powerCut.isSelected()) {
			Drop.powerCut = true;
			EWoodcutter.mode = "Powercut Logs";
		}
		else {
			EWoodcutter.mode = "Bank Logs";
		}
		switch (comboTypesList.getSelectedIndex()) {
		case 0:// reg
			EWoodcutter.treeData = TreeData.REG;
			
			this.dispose();
			break;
		case 1:// oak
			EWoodcutter.treeData = TreeData.OAK;
			
			this.dispose();
			break;
		case 2:// willow
			EWoodcutter.treeData = TreeData.WILLOW;
			
			this.dispose();
			break;
		case 3:// maple
			EWoodcutter.treeData = TreeData.MAPLE;
			
			this.dispose();
			break;
		case 4:// yew
			EWoodcutter.treeData = TreeData.YEW;
			
			this.dispose();
			break;
		case 5:// magic
			EWoodcutter.treeData = TreeData.MAGIC;
			
			this.dispose();
			break;

		}
	}
}
