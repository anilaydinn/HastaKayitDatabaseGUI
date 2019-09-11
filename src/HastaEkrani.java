import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HastaEkrani extends JFrame {

	private JPanel contentPane;
	private JTable hasta_tablosu;
	private DefaultTableModel model = new DefaultTableModel();
	HastaIslemleri islemler = new HastaIslemleri();

	/**
	 * Create the frame.
	 */
	public HastaEkrani() {
		setTitle("Hasta Ekranı");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		hasta_tablosu = new JTable();
		hasta_tablosu.setBounds(12, 137, 892, 348);
		contentPane.add(hasta_tablosu);
		setLocationRelativeTo(null);
		hasta_tablosu.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		hasta_tablosu.getColumnModel().getColumn(0).setResizable(false);
		hasta_tablosu.getColumnModel().getColumn(1).setResizable(false);
		hasta_tablosu.getColumnModel().getColumn(2).setResizable(false);
		hasta_tablosu.getColumnModel().getColumn(3).setResizable(false);
		hasta_tablosu.getColumnModel().getColumn(4).setResizable(false);
		model = (DefaultTableModel) hasta_tablosu.getModel();
		hastalariGoruntule();
		
	}
	
	public void hastalariGoruntule() {
		
		model.setRowCount(0);
		
		ArrayList<Hasta> hastalar = new ArrayList<Hasta>();
		
		hastalar = islemler.hastalariGetir();
		
		if(hastalar != null) {
			
			for(Hasta hasta : hastalar) {
				
				Object[] eklenecek = {hasta.getId(), hasta.getAd(), hasta.getSoyad(), hasta.getTc(), hasta.getRefakatci()};
				
				model.addRow(eklenecek);
			}
		}
	}
}
