package no.uib.fma008.info233.v15.oblig2.gui;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This class is responsible for setting up the GUI interface including a frame,
 * menu, text-field, buttons and load/save interface (JFileChooser)
 * 
 * @author fma008
 * @version 1.0
 *
 */
public class GUI extends JFrame implements Serializable {

	private static final long serialVersionUID = 649333599355559251L;
	private JFrame frame;
	private JMenuBar menubar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenuItem search = new JMenuItem("Search");
	private JMenuItem load = new JMenuItem("Load");
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem quit = new JMenuItem("Quit");
	private JPanel toolbar = new JPanel();
	private JButton info102 = new JButton("INFO102");
	private JButton info103 = new JButton("INFO103");
	private JButton info110 = new JButton("INFO110");
	private JButton info132 = new JButton("INFO132");
	private JButton info216 = new JButton("INFO216");
	private JButton info233 = new JButton("INFO233");
	private JButton info262 = new JButton("INFO262");
	private JLabel week = new JLabel("");
	private JTextArea textField = new JTextArea();
	private JFileChooser jFileChooser = new JFileChooser(new File("."));
	private JScrollPane scroll = new JScrollPane(textField,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	/**
	 * constructor
	 */
	public GUI() {
		prepareGUI();
	}

	/**
	 * method for setting up GUI with frame, buttons, file-menu and text-field
	 */
	public void prepareGUI() {
		frame = new JFrame("UIB Calendar");
		frame.setJMenuBar(menubar);
		menubar.add(fileMenu);
		fileMenu.add(search);
		fileMenu.add(load);
		fileMenu.add(save);
		fileMenu.add(quit);

		frame.add(scroll);
		textField.setLineWrap(true);
		textField.setEditable(false);

		frame.add(toolbar);
		toolbar.setLayout(new GridLayout(0, 1));
		toolbar.add(info102);
		toolbar.add(info103);
		toolbar.add(info110);
		toolbar.add(info132);
		toolbar.add(info216);
		toolbar.add(info233);
		toolbar.add(info262);
		toolbar.add(week).setBounds(300, 100, 2, 2);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.setLayout(new GridLayout(0, 1));
		frame.setSize(350, 700);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	/**
	 * method for String input in textField
	 * 
	 * @param text
	 *            the text to set
	 */
	public void inputTextField(String text) {
		String line = textField.getText();
		textField.setText(line + text);
		getTextField().setCaretPosition(0);
	}

	/**
	 * 
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * 
	 * @return the search
	 */
	public JMenuItem getSearch() {
		return search;
	}

	/**
	 * @return the load
	 */
	public JMenuItem getLoad() {
		return load;
	}

	/**
	 * @return the save
	 */
	public JMenuItem getSave() {
		return save;
	}

	/**
	 * @return the quit
	 */
	public JMenuItem getQuit() {
		return quit;
	}

	/**
	 * @return the info102
	 */
	public JButton getInfo102() {
		return info102;
	}

	/**
	 * @return the info103
	 */
	public JButton getInfo103() {
		return info103;
	}

	/**
	 * @return the info110
	 */
	public JButton getInfo110() {
		return info110;
	}

	/**
	 * @return the info132
	 */
	public JButton getInfo132() {
		return info132;
	}

	/**
	 * @return the info216
	 */
	public JButton getInfo216() {
		return info216;
	}

	/**
	 * @return the info233
	 */
	public JButton getInfo233() {
		return info233;
	}

	/**
	 * @return the info262
	 */
	public JButton getInfo262() {
		return info262;
	}

	/**
	 * @return the textField
	 */
	public JTextArea getTextField() {
		return textField;
	}

	/**
	 * @return the week
	 */
	public JLabel getWeek() {
		return week;
	}

	/**
	 * @param week
	 *            the week to set
	 */
	public void setWeek(JLabel week) {
		this.week = week;
	}

	/**
	 * @return the jFileChooser
	 */
	public JFileChooser getjFileChooser() {
		return jFileChooser;
	}

}
