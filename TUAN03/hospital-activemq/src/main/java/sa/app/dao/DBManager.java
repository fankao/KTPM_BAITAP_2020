package sa.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DBManager {
	private static DBManager instance = null;
	private EntityManager em;

	public DBManager() {
		em = Persistence.createEntityManagerFactory("hospital-activemq").createEntityManager();
	}

	public synchronized static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	public EntityManager getEntityManager() {
		return em;
	}
}
