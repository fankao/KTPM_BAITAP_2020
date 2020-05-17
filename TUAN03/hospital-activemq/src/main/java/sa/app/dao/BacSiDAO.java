package sa.app.dao;

import sa.app.entity.BacSi;

public interface BacSiDAO {
	BacSi save (BacSi bacSi);
	BacSi findById(String id);
}
