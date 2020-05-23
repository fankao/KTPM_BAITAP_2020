package sa.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import sa.app.entity.BenhNhan;

public class BenhNhanDAOImpl implements BenhNhanDAO {
	private EntityManager em;

	public BenhNhanDAOImpl() {
		em = Persistence.createEntityManagerFactory("hospital-activemq").createEntityManager();
	}

	@Override
	public BenhNhan findById(String msbn) {
		// TODO Auto-generated method stub
		return em.find(BenhNhan.class, msbn);
	}

	@Override
	public BenhNhan findByCMND(String cmnd) {
		List<BenhNhan> list = em.createQuery("FROM BenhNhan bn WHERE bn.socmnd =:cmnd", BenhNhan.class)
				.setParameter("cmnd", cmnd).getResultList();
		return list.size() == 0 ? null : list.get(0);
	}

}
