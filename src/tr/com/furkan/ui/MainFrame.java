package tr.com.furkan.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tr.com.furkan.abs.AFrame;
import tr.com.furkan.dal.CustomerDal;
import tr.com.furkan.type.Customer;

public class MainFrame extends AFrame{

	public MainFrame() {
		// TODO Auto-generated constructor stub
		initFrame("Müþteri Ekle", initPanel());
	}

	@Override
	public JPanel initPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel(new BorderLayout());
		JPanel buttonsJPanel = new JPanel(new GridLayout(5,2));
		JLabel searchLabel = new JLabel("Kiþi ara: ", JLabel.RIGHT);
		panel.add(buttonsJPanel);
		JTextField searchField = new JTextField(10);
		buttonsJPanel.add(searchField);
		JLabel nameJLabel = new JLabel("Adýnýz: ", JLabel.RIGHT);
		buttonsJPanel.add(nameJLabel);
		JTextField nameField = new JTextField(10);
		buttonsJPanel.add(nameField);
		
		JLabel surnameJLabel = new JLabel("Soyadý: ", JLabel.RIGHT);
		buttonsJPanel.add(surnameJLabel);
		JTextField surnameField = new JTextField(10);
		buttonsJPanel.add(surnameField);
		
		JButton saveButton = new JButton("Kaydet");
		buttonsJPanel.add(saveButton);
		JButton updateButton = new JButton("Düzenle");
		buttonsJPanel.add(updateButton);
		JButton deleteButton = new JButton("Sil");
		buttonsJPanel.add(deleteButton);
		JList list = new JList(new CustomerDal().getList().toArray());
		JScrollPane pane = new JScrollPane(list);
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Customer contract = new Customer();
				
				contract.setName(nameField.getText());
				contract.setSurname(surnameField.getText());
				
				new CustomerDal().insert(contract);
				JOptionPane.showMessageDialog(nameField, contract.getName()+ " " 
				+ contract.getSurname() + " adlý kiþi baþarýyla kaydedilmiþtir.");
				
				list.setListData(new CustomerDal().getList().toArray());
			}
			
		});
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				Customer contract = (Customer) list.getSelectedValue();
				if(contract!=null) {
				Customer contDal = new CustomerDal().getById(contract.getId());
				nameField.setText(contDal.getName());
				surnameField.setText(contDal.getSurname());
				
			}
				else {
					list.setSelectedIndex(0);
				}
			}});
		
		searchField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				list.setListData(new CustomerDal().getSearch(searchField.getText()).toArray());
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			
				
			}
		});
		
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Customer cast = (Customer) list.getSelectedValue();
				Customer contract = new Customer();
				contract.setId(cast.getId());
				contract.setName(nameField.getText());
				contract.setSurname(surnameField.getText());
				
				new CustomerDal().update(contract);
				JOptionPane.showMessageDialog(nameField, contract.getName()+" "+ contract.getSurname()+ " adlý kiþi düzenlenmiþtir.");
				list.setListData(new CustomerDal().getList().toArray());
				
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Customer contract=(Customer) list.getSelectedValue();
				
				new CustomerDal().delete(contract);
				JOptionPane.showMessageDialog(nameField, contract.getName()+ " "+ contract.getSurname()+ " adlý kiþi silinmiþtir.");
				list.setListData(new CustomerDal().getList().toArray());
				
			}
		});
		
		
		panel.add(buttonsJPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.SOUTH);
		
		return panel;
	}

}
