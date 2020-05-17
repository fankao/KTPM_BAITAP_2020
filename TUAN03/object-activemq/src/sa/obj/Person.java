package sa.obj;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	private long mssv;
	private String hoTen;
	private Date ngaySinh;
	public Person(long mssv, String hoTen, Date ngaySinh) {
		super();
		this.mssv = mssv;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
	}
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	public long getMssv() {
		return mssv;
	}

	public void setMssv(long mssv) {
		this.mssv = mssv;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	@Override
	public String toString() {
		return "Person [mssv=" + mssv + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + "]";
	}
	
	
	

}
