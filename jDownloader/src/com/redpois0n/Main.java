package com.redpois0n;

import java.awt.Desktop;
import java.net.URI;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Main {

	public static final String getVersion() {
		return "1.0";
	}
	
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		Frame frame = new Frame();
		frame.setVisible(true);
		
		if (Updater.isAvailable()) {
			JOptionPane.showMessageDialog(null, "A new update is available. Proceeding to www.redpois0n.com to download.", "Update available", JOptionPane.INFORMATION_MESSAGE);
			Desktop.getDesktop().browse(new URI("http://redpois0n.com"));
		}
	}

}
