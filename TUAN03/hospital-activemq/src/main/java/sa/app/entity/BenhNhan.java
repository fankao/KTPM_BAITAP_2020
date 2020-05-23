package sa.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BENHNHAN")
public class BenhNhan implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private String msbn;
	private String socmnd;
	@Column(columnDefinition = "NVARCHAR(150)")
	private String hoten;
	@Column(columnDefinition = "NVARCHAR(200)")
	private String diachi;
	
	public BenhNhan() {
		// TODO Auto-generated constructor stub
	}

	public BenhNhan(String msbn, String socmnd, String hoten, String diachi) {
		super();
		this.msbn = msbn;
		this.socmnd = socmnd;
		this.hoten = hoten;
		this.diachi = diachi;
	}

	public String getMsbn() {
		return msbn;
	}

	public void setMsbn(String msbn) {
		this.msbn = msbn;
	}

	public String getSocmnd() {
		return socmnd;
	}

	public void setSocmnd(String socmnd) {
		this.socmnd = socmnd;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((msbn == null) ? 0 : msbn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BenhNhan other = (BenhNhan) obj;
		if (msbn == null) {
			if (other.msbn != null)
				return false;
		} else if (!msbn.equals(other.msbn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getMsbn();
	}
	
	
	
}
