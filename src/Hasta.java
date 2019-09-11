
public class Hasta {

	private int id;
	private String ad;
	private String soyad;
	private String tc;
	private String refakatci;
	
	public Hasta(int id, String ad, String soyad, String tc, String refakatci) {
		super();
		this.id = id;
		this.ad = ad;
		this.soyad = soyad;
		this.tc = tc;
		this.refakatci = refakatci;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	public String getRefakatci() {
		return refakatci;
	}

	public void setRefakatci(String refakatci) {
		this.refakatci = refakatci;
	}
	
	
}
