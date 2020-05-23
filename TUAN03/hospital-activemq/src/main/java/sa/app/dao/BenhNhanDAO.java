package sa.app.dao;

import sa.app.entity.BenhNhan;

public interface BenhNhanDAO {
	BenhNhan findById(String msbn);
	BenhNhan findByCMND(String cmnd);
}
