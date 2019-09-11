import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HastaEkrani extends JFrame {

	private JPanel contentPane;
	private JTable hasta_tablosu;
	private DefaultTableModel model = new DefaultTableModel();
	HastaIslemleri islemler = new HastaIslemleri();
	private JTextField ara_textfield;
	private JTextField hasta_adi_textfield;
	private JTextField hasta_soyadi_textfield;
	private JTextField hasta_tc_textfield;
	private JLabel lblRefakati;
	private JTextField refakatci_textfield;
	private JButton hasta_ekle_butonu;
	private JButton hasta_guncelle_butonu;
	private JButton hasta_sil_butonu;

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
		hasta_tablosu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int secilensatir = hasta_tablosu.getSelectedRow();
				
				hasta_adi_textfield.setText(model.getValueAt(secilensatir, 1).toString());
				hasta_soyadi_textfield.setText(model.getValueAt(secilensatir, 2).toString());
				hasta_tc_textfield.setText(model.getValueAt(secilensatir, 3).toString());
				refakatci_textfield.setText(model.getValueAt(secilensatir, 4).toString());

			}
		});
		hasta_tablosu.setBounds(12, 227, 892, 258);
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
		
		ara_textfield = new JTextField();
		ara_textfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String ara = ara_textfield.getText();
				
				dinamikAra(ara);
			}
		});
		ara_textfield.setBounds(12, 23, 892, 31);
		contentPane.add(ara_textfield);
		ara_textfield.setColumns(10);
		
		JLabel lblHastaAd = new JLabel("Hasta Adı:");
		lblHastaAd.setBounds(22, 66, 99, 15);
		contentPane.add(lblHastaAd);
		
		hasta_adi_textfield = new JTextField();
		hasta_adi_textfield.setBounds(131, 64, 169, 19);
		contentPane.add(hasta_adi_textfield);
		hasta_adi_textfield.setColumns(10);
		
		JLabel lblHastaSoyad = new JLabel("Hasta Soyad:");
		lblHastaSoyad.setBounds(22, 93, 99, 15);
		contentPane.add(lblHastaSoyad);
		
		hasta_soyadi_textfield = new JTextField();
		hasta_soyadi_textfield.setColumns(10);
		hasta_soyadi_textfield.setBounds(131, 91, 169, 19);
		contentPane.add(hasta_soyadi_textfield);
		
		JLabel lblTc = new JLabel("TC:");
		lblTc.setBounds(22, 120, 99, 15);
		contentPane.add(lblTc);
		
		hasta_tc_textfield = new JTextField();
		hasta_tc_textfield.setColumns(10);
		hasta_tc_textfield.setBounds(131, 118, 169, 19);
		contentPane.add(hasta_tc_textfield);
		
		lblRefakati = new JLabel("Refakatçi:");
		lblRefakati.setBounds(22, 148, 99, 15);
		contentPane.add(lblRefakati);
		
		refakatci_textfield = new JTextField();
		refakatci_textfield.setColumns(10);
		refakatci_textfield.setBounds(131, 146, 169, 19);
		contentPane.add(refakatci_textfield);
		
		hasta_ekle_butonu = new JButton("Hasta Ekle");
		hasta_ekle_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ad = hasta_adi_textfield.getText();
				String soyad = hasta_soyadi_textfield.getText();
				String tc = hasta_tc_textfield.getText();
				String refakatci = refakatci_textfield.getText();
				
				islemler.hastaEkle(ad, soyad, tc, refakatci);
				
				hastalariGoruntule();
			}
		});
		hasta_ekle_butonu.setBounds(731, 61, 149, 47);
		contentPane.add(hasta_ekle_butonu);
		
		hasta_guncelle_butonu = new JButton("Hasta Güncelle");
		hasta_guncelle_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ad = hasta_adi_textfield.getText();
				String soyad = hasta_soyadi_textfield.getText();
				String tc = hasta_tc_textfield.getText();
				String refakatci = refakatci_textfield.getText();
				
				int secilensatir = hasta_tablosu.getSelectedRow();
				
				if(secilensatir == -1) {
					
					if(model.getRowCount() == 0) {
						
						JOptionPane.showMessageDialog(HastaEkrani.this, "Hasta tablosu şu anda boş.");
					}
					else {
						
						JOptionPane.showMessageDialog(HastaEkrani.this, "Lütfen güncellenecek bir hasta seçin.");
					}
				}
				else {
					
					int id = (int)model.getValueAt(secilensatir, 0);
					
					islemler.hastaGuncelle(id, ad, soyad, tc, refakatci);
					
					hastalariGoruntule();
					
					JOptionPane.showMessageDialog(HastaEkrani.this, "Hasta başarıyla güncellendi.");
				}
			}
		});
		hasta_guncelle_butonu.setBounds(731, 115, 149, 47);
		contentPane.add(hasta_guncelle_butonu);
		
		hasta_sil_butonu = new JButton("Hasta Sil");
		hasta_sil_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int secilensatir = hasta_tablosu.getSelectedRow();
				
				if(secilensatir == -1) {
					
					if(model.getRowCount() == 0) {
						
						JOptionPane.showMessageDialog(HastaEkrani.this, "Hasta tablosu şu anda boş.");
					}
					else {
						
						JOptionPane.showMessageDialog(HastaEkrani.this, "Lütfen silinecek bir hasta seçin.");
					}
				}
				else {
					
					int id = (int)model.getValueAt(secilensatir, 0);
					
					islemler.hastaSil(id);
					
					hastalariGoruntule();
					
					JOptionPane.showMessageDialog(HastaEkrani.this, "Hasta başarıyla silindi.");
				}
			}
		});
		hasta_sil_butonu.setBounds(731, 168, 149, 47);
		contentPane.add(hasta_sil_butonu);
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
	
	public void dinamikAra(String ara) {
		
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		
		hasta_tablosu.setRowSorter(tr);
		
		tr.setRowFilter(RowFilter.regexFilter(ara));
	}
}
