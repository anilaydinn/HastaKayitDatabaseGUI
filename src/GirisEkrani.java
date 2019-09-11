import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GirisEkrani extends JFrame {

	private JPanel contentPane;
	private JTextField kullanici_adi_textfield;
	private JLabel lblParola;
	private JPasswordField parola_passwordfield;
	HastaIslemleri islemler = new HastaIslemleri();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GirisEkrani frame = new GirisEkrani();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GirisEkrani() {
		setTitle("Admin Giriş Ekranı");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 367, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblKullancAd = new JLabel("Kullanıcı Adı:");
		lblKullancAd.setBounds(12, 14, 100, 15);
		contentPane.add(lblKullancAd);
		
		kullanici_adi_textfield = new JTextField();
		kullanici_adi_textfield.setBounds(133, 12, 124, 19);
		contentPane.add(kullanici_adi_textfield);
		kullanici_adi_textfield.setColumns(10);
		
		lblParola = new JLabel("Parola:");
		lblParola.setBounds(12, 56, 100, 15);
		contentPane.add(lblParola);
		
		parola_passwordfield = new JPasswordField();
		parola_passwordfield.setBounds(133, 54, 124, 19);
		contentPane.add(parola_passwordfield);
		
		JButton giris_yap_btn = new JButton("Giriş Yap");
		giris_yap_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = kullanici_adi_textfield.getText();
				String password = new String(parola_passwordfield.getPassword());
				
				boolean girisbasarili = islemler.girisYap(username, password);
				
				if(girisbasarili) {
					
					HastaEkrani hastaEkrani = new HastaEkrani();
					hastaEkrani.setVisible(true);
					setVisible(false);
				}
				else {
					
					JOptionPane.showMessageDialog(GirisEkrani.this, "Kullanıcı Adı veya Parola Yanlış !");
				}
			}
		});
		giris_yap_btn.setBounds(215, 95, 114, 25);
		contentPane.add(giris_yap_btn);
		
		JButton kayit_ol_btn = new JButton("Kayıt Ol");
		kayit_ol_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminKayitEkrani adminKayitEkran = new AdminKayitEkrani();
				adminKayitEkran.setVisible(true);
				setVisible(false);
			}
		});
		kayit_ol_btn.setBounds(32, 95, 114, 25);
		contentPane.add(kayit_ol_btn);
	}
}
