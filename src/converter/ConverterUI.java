package converter;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ConverterUI extends JFrame {
	private UnitConverter unitConverter;
	private JTextField textLeft;
	private JTextField textRight;
	private JButton convert;
	private JButton clear;
	private JLabel equal;
	private JRadioButton leftToRight;
	private JRadioButton rightToLeft;
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JComboBox<Unit> unit1ComboBox;
	private JComboBox<Unit> unit2ComboBox;
	// * true leftToRight otherwise false.*//
	private boolean direction = true;
	private UnitConverter converter = new UnitConverter();
	private Unit[] lengths = converter.getUnit();

	public ConverterUI(UnitConverter convert) {
		super("Length Converter");
		this.unitConverter = convert;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
	}

	private void initComponents() {
		unit1ComboBox = new JComboBox<>(lengths);
		unit2ComboBox = new JComboBox<>(lengths);
		equal = new JLabel("=");
		leftToRight = new JRadioButton("Left -> Right");
		rightToLeft = new JRadioButton("Right -> Left");
		textLeft = new JTextField(10);
		textRight = new JTextField(10);
		convert = new JButton("Convert");
		leftToRight.setSelected(true);
		textRight.setEnabled(false);

		clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textLeft.setText("");
				textRight.setText("");
			}
		});

		leftToRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rightToLeft.setSelected(false);
				leftToRight.setSelected(true);
				direction = true;
				textLeft.setEnabled(true);
				textRight.setEnabled(false);
			}
		});
		rightToLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leftToRight.setSelected(false);
				rightToLeft.setSelected(true);
				direction = false;
				textRight.setEnabled(true);
				textLeft.setEnabled(false);
			}
		});
		convert.addActionListener(actionLeftOrRight());
		textLeft.addActionListener(actionLeftOrRight());
		textRight.addActionListener(actionLeftOrRight());

		panel1.add(textLeft);
		panel1.add(unit1ComboBox);
		panel1.add(equal);
		panel1.add(textRight);
		panel1.add(unit2ComboBox);
		panel1.add(convert);
		panel1.add(clear);

		panel2.add(leftToRight);
		panel2.add(rightToLeft);

		this.add(panel1, BorderLayout.NORTH);
		this.add(panel2, BorderLayout.CENTER);
		this.pack();
	}

	public void run() {
		this.setVisible(true);
	}

	public ActionListener actionLeftOrRight() {
		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Unit unit1 = (Unit) unit1ComboBox.getSelectedItem();
				Unit unit2 = (Unit) unit2ComboBox.getSelectedItem();
				if (direction) {
					try {
						Double.parseDouble(textLeft.getText());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Input is not a number !");
					}
					String convertNum = String.format("%f",
							converter.convert(Double.parseDouble(textLeft.getText()), unit1, unit2));
					textRight.setText(convertNum);
				} else {
					try {
						Double.parseDouble(textRight.getText());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Input is not a number !");
					}
					String convertNum = String.format("%f",
							converter.convert(Double.parseDouble(textRight.getText()), unit2, unit1));
					textLeft.setText(convertNum);
				}
			}
		};
		return action;
	}
}
