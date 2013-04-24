package com.redpois0n;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class FrameAdd extends JDialog {
	private JTextField txtURL;
	private JTextField txtFileName;
	private JTextField txtExtension;
	private JComboBox<String> cbPath;
	private JCheckBox chckbxShellExecute;

	public FrameAdd() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrameAdd.class.getResource("/com/redpois0n/icons/add_file.png")));
		setModal(true);
		setTitle("Add Download");
		setResizable(false);
		setBounds(100, 100, 317, 210);
		
		JLabel lblUrl = new JLabel("URL:");
		
		txtURL = new JTextField();
		txtURL.setText("http://example.com/file.exe");
		txtURL.setColumns(10);
		
		JLabel lblFileName = new JLabel("File name:");
		
		JLabel lblExtension = new JLabel("Extension:");
		
		txtFileName = new JTextField();
		txtFileName.setText("Downloaded");
		txtFileName.setColumns(10);
		
		txtExtension = new JTextField();
		txtExtension.setText("exe");
		txtExtension.setColumns(10);
		
		JLabel lblPath = new JLabel("Path:");
		
		cbPath = new JComboBox<String>();
		cbPath.setModel(new DefaultComboBoxModel<String>(Path.paths));
		
		chckbxShellExecute = new JCheckBox("Shell execute");
		chckbxShellExecute.setSelected(true);
		
		JButton btnAddFile = new JButton("Add file");
		btnAddFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String url = txtURL.getText().trim();
					String fileName = txtFileName.getText().trim();
					String extension = txtExtension.getText().trim();
					String path = cbPath.getSelectedItem().toString();
					boolean shellExecute = chckbxShellExecute.isSelected();
					
					Frame.getInstance().getModel().addRow(new Object[] { url, fileName, extension, path, shellExecute });
					
					setVisible(false);
					dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Add Download", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddFile.setIcon(new ImageIcon(FrameAdd.class.getResource("/com/redpois0n/icons/add_file.png")));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnCancel.setIcon(new ImageIcon(FrameAdd.class.getResource("/com/redpois0n/icons/delete.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblUrl)
										.addGap(42)
										.addComponent(txtURL, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))
									.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addComponent(lblFileName)
										.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
										.addComponent(txtFileName, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))
									.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addComponent(lblExtension)
										.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(cbPath, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(txtExtension, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
											.addComponent(chckbxShellExecute))))
								.addContainerGap(9, Short.MAX_VALUE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblPath)
								.addContainerGap(275, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddFile)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUrl)
						.addComponent(txtURL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFileName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFileName))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtExtension, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExtension))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPath)
						.addComponent(cbPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxShellExecute)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddFile)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		setLocationRelativeTo(null);

	}
}
