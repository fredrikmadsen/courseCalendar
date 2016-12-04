package no.uib.fma008.info233.v15.oblig2.uib_rom_app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import no.uib.fma008.info233.v15.oblig2.gui.GUI;
import no.uib.fma008.info233.v15.oblig2.io.Parser;
import no.uib.fma008.info233.v15.oblig2.util.Utilities;

/**
 * This class is responsible for setting up the calendar application. The
 * constructor initialize an new parser and gui. Events on buttons in gui (like
 * selecting/clicking subject) will call parsing method and display results.
 * 
 * @author fma008
 * @version 1.0
 *
 */
public class UibRomApp implements ActionListener, Serializable {

	private static final long serialVersionUID = 9051855156235437783L;
	private Parser parser;
	private GUI gui;
	private String emneTo;
	private int week;

	/**
	 * constructor
	 */
	public UibRomApp() {
		this.parser = new Parser();
		this.gui = new GUI();
		addListeners(this);
		setWeek(Utilities.getWeek());
		this.gui.getWeek().setText(" Week: " + week + "");
	}

	/**
	 * method for adding ActionListeners to frame, button and menu
	 * 
	 * @param listener
	 */
	public void addListeners(ActionListener listener) {
		this.gui.getSearch().addActionListener(listener);
		this.gui.getLoad().addActionListener(listener);
		this.gui.getSave().addActionListener(listener);
		this.gui.getQuit().addActionListener(listener);
		this.gui.getInfo102().addActionListener(listener);
		this.gui.getInfo103().addActionListener(listener);
		this.gui.getInfo110().addActionListener(listener);
		this.gui.getInfo132().addActionListener(listener);
		this.gui.getInfo216().addActionListener(listener);
		this.gui.getInfo233().addActionListener(listener);
		this.gui.getInfo262().addActionListener(listener);
	}

	/**
	 * method for handling different ActionEvents in GUI
	 * 
	 * @param e
	 *            the ActionEvent e
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gui.getSearch()) {
			String subject = JOptionPane.showInputDialog(gui.getFrame(),
					"Enter subject code (example info110)");
			activityToGui(subject);
		}
		if (e.getSource() == gui.getLoad()) {
			readFromFiles();
		}
		if (e.getSource() == gui.getSave()) {
			saveToFiles();
		}
		if (e.getSource() == gui.getQuit()) {
			System.exit(0);
		}
		if (e.getSource() == gui.getInfo102()) {
			activityToGui("info102");
		}
		if (e.getSource() == gui.getInfo103()) {
			activityToGui("info103");
		}
		if (e.getSource() == gui.getInfo110()) {
			activityToGui("info110");
		}
		if (e.getSource() == gui.getInfo132()) {
			activityToGui("info132");
		}
		if (e.getSource() == gui.getInfo216()) {
			activityToGui("info216");
		}
		if (e.getSource() == gui.getInfo233()) {
			activityToGui("info233");
		}
		if (e.getSource() == gui.getInfo262()) {
			activityToGui("info262");
		}
	}

	/**
	 * method for initiate parsing, retrieve activities and display them in GUI
	 * 
	 * @param emne
	 *            the emne to set
	 */
	public void activityToGui(String emne) {
		gui.getTextField().setText("");
		parser.setEmne(emne);
		parser.docToLists();
		gui.inputTextField(" Current calendar subject: "
				+ parser.getEmne().toUpperCase() + "\n"
				+ parser.printActivityStrings());
	}

	/**
	 * saves the UibRomApp object to disk using JFileChooser interface
	 * 
	 */
	private void saveToFiles() {
		if (gui.getjFileChooser().showSaveDialog(this.gui) == JFileChooser.APPROVE_OPTION) {
			save(gui.getjFileChooser().getSelectedFile());
		}
	}

	/**
	 * saves the UibRomApp object to disk.
	 * 
	 * @param file
	 *            the file to save
	 */
	private void save(File file) {
		try {
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(file));
			byte[] b = (gui.getTextField().getText()).getBytes();
			out.write(b, 0, b.length);
			out.close();

			gui.getTextField().setText(file.getName() + " saved ");
		}

		catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Error occurred when saving file", "alert",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Reads a UibRomApp object from disk.
	 */
	private void readFromFiles() {
		// gui.getjFileChooser();
		if (gui.getjFileChooser().showOpenDialog(this.gui) == JFileChooser.APPROVE_OPTION) {
			open(gui.getjFileChooser().getSelectedFile());
		}
	}

	/**
	 * Open file
	 * 
	 * @param file
	 *            the file to read
	 */
	private void open(File file) {
		try {
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(file));
			byte[] b = new byte[in.available()];
			in.read(b, 0, b.length);
			gui.getTextField().setText("");
			gui.getTextField().append(new String(b, 0, b.length));
			in.close();

			gui.inputTextField((file.getName() + " opened"));
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Error occurred when opening file", "alert",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * @return the week
	 */
	public int getWeek() {
		return week;
	}

	/**
	 * @param week
	 *            the week to set
	 */
	public void setWeek(int week) {
		this.week = week;
	}

	/**
	 * @return the emneTo
	 */
	public String getEmneTo() {
		return emneTo;
	}

	/**
	 * @param emneToSet
	 *            the emneTo to set
	 */
	public void setEmneTo(String emneTo) {
		this.emneTo = emneTo;
	}

	/**
	 * main method for initializing UibRomApp and run the application
	 *
	 * @param String
	 *            [] args
	 * 
	 */
	public static void main(String[] args) {
		new UibRomApp();
	}

}
