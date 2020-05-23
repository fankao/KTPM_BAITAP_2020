package sa.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class KhamBenh implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "msbn")
	private BenhNhan benhNhan;
	
	private LocalDateTime ngayKham;
	
	@Column(columnDefinition = "NVARCHAR(1000)")
	private String ghiChu;

	public KhamBenh() {
		super();
	}

	public KhamBenh(BenhNhan benhNhan, LocalDateTime ngayKham, String ghiChu) {
		super();
		this.benhNhan = benhNhan;
		this.ngayKham = ngayKham;
		this.ghiChu = ghiChu;
	}

	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}

	public LocalDateTime getNgayKham() {
		return ngayKham;
	}

	public void setNgayKham(LocalDateTime ngayKham) {
		this.ngayKham = ngayKham;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((benhNhan == null) ? 0 : benhNhan.hashCode());
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
		KhamBenh other = (KhamBenh) obj;
		if (benhNhan == null) {
			if (other.benhNhan != null)
				return false;
		} else if (!benhNhan.equals(other.benhNhan))
			return false;
		return true;
	}

}
