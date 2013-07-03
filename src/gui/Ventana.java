
package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.*;
import ejecucion.*;


public class Ventana extends JFrame {
	
	File archivoEcr, archivoUnf;
	String rutaEcr, rutaUnf;
	boolean flag;
	
	public Ventana() {
		initComponents();
	}

	private void examinarEcrAction(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		int resp = fc.showOpenDialog(this);
		label2.setVisible(false);
		if(resp == JFileChooser.APPROVE_OPTION){
			archivoEcr = fc.getSelectedFile();
		    rutaEcr = archivoEcr.getAbsolutePath();
		    formattedTextField1.setText(rutaEcr);
		}
	}

	private void examinarUnfAction(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		int resp = fc.showOpenDialog(this);
		label2.setVisible(false);
		if(resp == JFileChooser.APPROVE_OPTION){
			archivoUnf = fc.getSelectedFile();
		    rutaUnf = archivoUnf.getAbsolutePath();
		    formattedTextField2.setText(rutaUnf);
		}
	}

	private void guardarEnAction(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int resp = fc.showOpenDialog(this);
		label2.setVisible(false);
		if(resp == JFileChooser.APPROVE_OPTION){
		    String rutaTrace = fc.getSelectedFile().getAbsolutePath();
		    formattedTextField3.setText(rutaTrace);
		}
		
	}

	private void unificarAction(ActionEvent e) {
		label2.setVisible(false);
		if(archivoEcr != null && archivoUnf != null && rutaEcr!= null && rutaUnf != null && formattedTextField3.getText() != null && formattedTextField4.getText() != null){
		    try{
		    	String rutaTrace = formattedTextField3.getText() + "/" + formattedTextField4.getText() + ".trace";
		        flag = Main.ejecutar(rutaEcr, rutaUnf, rutaTrace);
		        if (flag)
		        	label2.setVisible(true);
		    }catch(Exception ex){
		        JOptionPane.showMessageDialog(this, "Ocurri— un error al realizar la acci—n.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }
		}else{
			if (rutaEcr == null || archivoEcr == null){
				JOptionPane.showMessageDialog(this, "No has seleccionado el archivo ECR.", "Error", JOptionPane.ERROR_MESSAGE);
				button1.requestFocusInWindow();
			} else if (rutaUnf == null || archivoUnf == null){
				JOptionPane.showMessageDialog(this, "No has seleccionado el archivo UNF.", "Error", JOptionPane.ERROR_MESSAGE);
				button2.requestFocusInWindow();
			}else if (formattedTextField3.getText() != null) {
				JOptionPane.showMessageDialog(this, "No has seleccionado un directorio donde guardar el archivo TRACE", "Error", JOptionPane.ERROR_MESSAGE);
				button3.requestFocusInWindow();
			} else {
				JOptionPane.showMessageDialog(this, "Introduce un nombre para el archivo TRACE", "Error", JOptionPane.ERROR_MESSAGE);
				formattedTextField4.requestFocusInWindow();
			}
		    return;
		}
	}

	private void initComponents() {
		
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Tobias Rasterious
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		button1 = new JButton();
		button2 = new JButton();
		button3 = new JButton();
		label1 = new JLabel();
		formattedTextField1 = new JFormattedTextField();
		formattedTextField2 = new JFormattedTextField();
		formattedTextField3 = new JFormattedTextField();
		formattedTextField4 = new JFormattedTextField();
		buttonBar = new JPanel();
		label2 = new JLabel();
		label2.setVisible(false);
		okButton = new JButton();

		//======== this ========
		setTitle("Unificador de Estructuras Complejas de Rasgos");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

			// JFormDesigner evaluation mark
			dialogPane.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{

				//---- button1 ----
				button1.setText("Examinar ECR ...");
				button1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						examinarEcrAction(e);
					}
				});

				//---- button2 ----
				button2.setText("Examinar UNF ...");
				button2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						examinarUnfAction(e);
					}
				});

				//---- button3 ----
				button3.setText("Guardar en ...");
				button3.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						guardarEnAction(e);
					}
				});

				//---- label1 ----
				label1.setText("Nombre fichero TRACE");

				GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
				contentPanel.setLayout(contentPanelLayout);
				contentPanelLayout.setHorizontalGroup(
					contentPanelLayout.createParallelGroup()
						.addGroup(contentPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(contentPanelLayout.createParallelGroup()
								.addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
									.addComponent(button1)
									.addComponent(button2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(button3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(label1))
							.addGap(18, 18, 18)
							.addGroup(contentPanelLayout.createParallelGroup()
								.addComponent(formattedTextField4, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
								.addComponent(formattedTextField2, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
								.addComponent(formattedTextField1, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
								.addComponent(formattedTextField3, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
							.addContainerGap())
				);
				contentPanelLayout.setVerticalGroup(
					contentPanelLayout.createParallelGroup()
						.addGroup(contentPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(button1)
								.addComponent(formattedTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18, 18, 18)
							.addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(button2)
								.addComponent(formattedTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18, 18, 18)
							.addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(button3)
								.addComponent(formattedTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(formattedTextField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label1))
							.addContainerGap(28, Short.MAX_VALUE))
				);
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
				((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

				//---- label2 ----
				label2.setText("Unificaci\u00f3n realidaza correctamente");
				buttonBar.add(label2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

				//---- okButton ----
				okButton.setText("Unificar");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						unificarAction(e);
					}
				});
				buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Tobias Rasterious
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JLabel label1;
	private JFormattedTextField formattedTextField1;
	private JFormattedTextField formattedTextField2;
	private JFormattedTextField formattedTextField3;
	private JFormattedTextField formattedTextField4;
	private JPanel buttonBar;
	private JLabel label2;
	private JButton okButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
