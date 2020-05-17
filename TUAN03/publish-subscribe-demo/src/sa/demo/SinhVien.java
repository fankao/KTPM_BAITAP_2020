package sa.demo;

import java.io.Serializable;

public class SinhVien implements Serializable{
	private static final long serialVersionUID = 1L;
	private long masv;
	private String ten;
	private int tuoi;
	public SinhVien(long masv, String ten, int tuoi) {
		super();
		this.masv = masv;
		this.ten = ten;
		this.tuoi = tuoi;
	}
	public long getMasv() {
		return masv;
	}
	public void setMasv(long masv) {
		this.masv = masv;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	@Override
	public String toString() {
		return "SinhVien [masv=" + masv + ", ten=" + ten + ", tuoi=" + tuoi + "]";
	}
	
}
