import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HastaIslemleri {

	private Connection con = null;
	
	private Statement statement = null;
	
	private PreparedStatement preparedStatement = null;
	
	public HastaIslemleri() {
		
		String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.db_ismi + "?useUnicode=true&characterEncoding=utf8";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			
			System.out.println("Driver bulunamadı...");
		}
		
		try {
			
			con = DriverManager.getConnection(url, Database.kullanici_adi, Database.parola);
			System.out.println("Bağlantı başarılı...");			
		}
		catch(SQLException e) {
			
			System.out.println("Bağlantı başarısız...");
		}
	}
	
	public void adminEkle(String username, String password) {
		
		String sorgu = "INSERT INTO Adminler (username, password) VALUES (?, ?)";
		
		try {
			
			preparedStatement = con.prepareStatement(sorgu);
			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public boolean girisYap(String username, String password) {
		
		String sorgu = "SELECT * FROM Adminler where username = ? and password = ?";
		
		try {
			
			preparedStatement = con.prepareStatement(sorgu);
			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			return rs.next();
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public ArrayList<Hasta> hastalariGetir(){
		
		ArrayList<Hasta> cikti = new ArrayList<Hasta>();
		
		String sorgu = "SELECT * FROM Patients";
		
		try {
			
			statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sorgu);
			
			while(rs.next()) {
				
				int id = rs.getInt("id");
				String ad = rs.getString("ad");
				String soyad = rs.getString("soyad");
				String tc = rs.getString("tc");
				String refakatci = rs.getString("refakatci");
				
				cikti.add(new Hasta(id, ad, soyad, tc, refakatci));
			}
			
			return cikti;
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public void hastaEkle(String ad, String soyad, String tc, String refakatci) {
		
		String sorgu = "INSERT INTO Patients (ad,soyad,tc,refakatci) VALUES (?,?,?,?)";
		
		try {
			
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setString(1, ad);
			preparedStatement.setString(2, soyad);
			preparedStatement.setString(3, tc);
			preparedStatement.setString(4, refakatci);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void hastaGuncelle(int id, String yeni_ad, String yeni_soyad, String yeni_tc, String yeni_refakatci) {
		
		String sorgu = "UPDATE Patients set ad = ?, soyad = ?, tc = ?, refakatci = ? where id = ?";
		
		try {
			
			preparedStatement = con.prepareStatement(sorgu);
			
			preparedStatement.setString(1, yeni_ad);
			preparedStatement.setString(2, yeni_soyad);
			preparedStatement.setString(3, yeni_tc);
			preparedStatement.setString(4, yeni_refakatci);
			preparedStatement.setInt(5, id);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void hastaSil(int id) {
		
		String sorgu = "DELETE from Patients where id = ?";
		
		try {
			
			preparedStatement = con.prepareStatement(sorgu);
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}
