package sa.app.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "BACSI")
public class BacSi implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String msbacsy;
	@Column(columnDefinition = "NVARCHAR(150)")
	private String hotenbacsy;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "KHAMBENH", joinColumns = @JoinColumn(name = "msbacsy", referencedColumnName = "msbacsy"))
	private Set<KhamBenh> dsKhamBenh = new HashSet<KhamBenh>();

	public BacSi() {
		this.setMsbacsy("");
	}

	public BacSi(String msbacsy, String hotenbacsy) {
		super();
		this.msbacsy = msbacsy;
		this.hotenbacsy = hotenbacsy;
	}

	public String getMsbacsy() {
		return msbacsy;
	}

	public void setMsbacsy(String msbacsy) {
		this.msbacsy = msbacsy;
	}

	public String getHotenbacsy() {
		return hotenbacsy;
	}

	public void setHotenbacsy(String hotenbacsy) {
		this.hotenbacsy = hotenbacsy;
	}

	public Set<KhamBenh> getDsKhamBenh() {
		return dsKhamBenh;
	}

	public void setDsKhamBenh(Set<KhamBenh> dsKhamBenh) {
		this.dsKhamBenh = dsKhamBenh;
	}

	public void luuTTKhamBenh(KhamBenh khamBenh) {
		this.dsKhamBenh.add(khamBenh);
	}
}
