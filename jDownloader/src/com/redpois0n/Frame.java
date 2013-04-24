package com.redpois0n;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	
	private static Frame instance;
	private JTable table;
	
	public DefaultTableModel getModel() {
		return (DefaultTableModel)table.getModel();
	}
	
	public static Frame getInstance() {
		return instance;
	}

	public Frame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Frame.class.getResource("/com/redpois0n/icons/icon.png")));
		setTitle("jDownloader - " + Main.getVersion());
		setResizable(false);
		instance = this;
		setBounds(100, 100, 647, 361);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnBuilder = new JMenu("Builder");
		menuBar.add(mnBuilder);
		
		JMenuItem mntmAddFile = new JMenuItem("Add file");
		mntmAddFile.setIcon(new ImageIcon(Frame.class.getResource("/com/redpois0n/icons/add_file.png")));
		mntmAddFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameAdd frame = new FrameAdd();
				frame.setVisible(true);
			}
		});
		mnBuilder.add(mntmAddFile);
		
		mnBuilder.addSeparator();
		
		JMenuItem mntmRemoteSelected = new JMenuItem("Remote selected");
		mntmRemoteSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] rows = table.getSelectedRows();
				
				for (int i : rows) {
					getModel().removeRow(i);
				}
			}
		});
		mntmRemoteSelected.setIcon(new ImageIcon(Frame.class.getResource("/com/redpois0n/icons/remove_file.png")));
		mnBuilder.add(mntmRemoteSelected);
		
		JMenuItem mntmRemoveAll = new JMenuItem("Remove all");
		mntmRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while (getModel().getRowCount() > 0) {
					getModel().removeRow(0);
				}
			}
		});
		mntmRemoveAll.setIcon(new ImageIcon(Frame.class.getResource("/com/redpois0n/icons/remove_file.png")));
		mnBuilder.add(mntmRemoveAll);
		
		mnBuilder.addSeparator();
		
		JMenuItem mntmBuildDownloader = new JMenuItem("Build Downloader");
		mntmBuildDownloader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				build();
			}
		});
		mntmBuildDownloader.setIcon(new ImageIcon(Frame.class.getResource("/com/redpois0n/icons/compile.png")));
		mnBuilder.add(mntmBuildDownloader);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmWebsite = new JMenuItem("Website");
		mntmWebsite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://redpois0n.com"));
				} catch (Exception ex) {	
					ex.printStackTrace();
				}

			}
		});
		mntmWebsite.setIcon(new ImageIcon(Frame.class.getResource("/com/redpois0n/icons/application-browser.png")));
		mnAbout.add(mntmWebsite);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Written by redpois0n\n\r\n\rhttp://redpois0n.com\n\r\n\rCurrent version: " + Main.getVersion(), "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mntmAbout.setIcon(new ImageIcon(Frame.class.getResource("/com/redpois0n/icons/information-frame.png")));
		mnAbout.add(mntmAbout);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"URL", "File Name", "Extension", "Path", "Shell Execute"
			}
		) {
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(25);
		table.getColumnModel().getColumn(0).setPreferredWidth(169);
		scrollPane.setViewportView(table);

	}
	
	public void build() {
		try {
			JFileChooser c = new JFileChooser();
			c.showSaveDialog(null);
			File output = c.getSelectedFile();
			
			if (output != null) {
				long start = System.currentTimeMillis();
				
				List<DownloadFile> files = new ArrayList<DownloadFile>();
				
				for (int i = 0; i < getModel().getRowCount(); i++) {
					DownloadFile file = new DownloadFile();
					
					file.setUrl(getModel().getValueAt(i, 0).toString());
					file.setFileName(getModel().getValueAt(i, 1).toString());
					file.setExtension(getModel().getValueAt(i, 2).toString());
					file.setPath(getModel().getValueAt(i, 3).toString());
					file.setShell(Boolean.parseBoolean(getModel().getValueAt(i, 4).toString()));
					
					files.add(file);
				}
				
				long end = System.currentTimeMillis();
				
				Build.build(output, files);
				
				JOptionPane.showMessageDialog(null, "Successfully built downloader. Took " + (end - start) + " ms.", "jDownloader", JOptionPane.INFORMATION_MESSAGE);
			}		
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to build downloader: " + ex.getClass().getSimpleName() + ": " + ex.getMessage(), "jDownloader", JOptionPane.ERROR_MESSAGE);
		}
	}
}
