package sa.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import sa.app.entity.BacSi;

public class BacSiDAOImpl implements BacSiDAO {
	private EntityManager em;

	public BacSiDAOImpl() {
		em = Persistence.createEntityManagerFactory("hospital-activemq").createEntityManager();
	}

	@Override
	public BacSi save(BacSi bacSi) {
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			if (em.find(BacSi.class, bacSi.getMsbacsy()) != null) {
				em.merge(bacSi);
			} else {
				em.persist(bacSi);
			}
			trans.commit();
			return bacSi;
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BacSi findById(String id) {
		return em.find(BacSi.class, id);
	}

}
