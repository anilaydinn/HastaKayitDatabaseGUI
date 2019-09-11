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

public class AdminKayitEkrani extends JFrame {

	private JPanel contentPane;
	private JTextField kullanici_adi_textfield;
	private JPasswordField parola_passwordfield;
	private JPasswordField parola_onayla_passwordfield;
	HastaIslemleri islemler = new HastaIslemleri();

	/**
	 * Create the frame.
	 */
	public AdminKayitEkrani() {
		setTitle("Admin Kayıt Ekranı");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 377, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblKullancAd = new JLabel("Kullanıcı Adı:");
		lblKullancAd.setBounds(29, 12, 102, 15);
		contentPane.add(lblKullancAd);
		
		JLabel lblParola = new JLabel("Parola:");
		lblParola.setBounds(29, 39, 102, 15);
		contentPane.add(lblParola);
		
		JLabel lblParolaOnayla = new JLabel("Parola Onayla:");
		lblParolaOnayla.setBounds(29, 66, 102, 15);
		contentPane.add(lblParolaOnayla);
		
		kullanici_adi_textfield = new JTextField();
		kullanici_adi_textfield.setBounds(156, 10, 180, 19);
		contentPane.add(kullanici_adi_textfield);
		kullanici_adi_textfield.setColumns(10);
		
		parola_passwordfield = new JPasswordField();
		parola_passwordfield.setBounds(156, 37, 180, 19);
		contentPane.add(parola_passwordfield);
		
		parola_onayla_passwordfield = new JPasswordField();
		parola_onayla_passwordfield.setBounds(156, 64, 180, 19);
		contentPane.add(parola_onayla_passwordfield);
		
		JButton kayit_ol_btn = new JButton("Kayıt Ol");
		kayit_ol_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = kullanici_adi_textfield.getText();
				String password = new String(parola_passwordfield.getPassword());
				String repassword = new String(parola_onayla_passwordfield.getPassword());
				
				if(!password.equals(repassword)) {
					
					JOptionPane.showMessageDialog(AdminKayitEkrani.this, "Girdiğin parolalar birbirinden farklı !");
				}
				else {
					
					islemler.adminEkle(username, password);
					JOptionPane.showMessageDialog(AdminKayitEkrani.this, "Yeni Admin Başarıyla Kaydedildi !");
				}
			}
		});
		kayit_ol_btn.setBounds(128, 93, 114, 25);
		contentPane.add(kayit_ol_btn);
	}

}
