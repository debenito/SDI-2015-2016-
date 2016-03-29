package uo.sdi.bussines;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import uo.sdi.persistence.util.Jpa;

public class CommandExecutor {

    public Object execute(Command command) {
	EntityManager em = Jpa.createEntityManager();
	EntityTransaction trx = em.getTransaction();
	trx.begin();

	try {
	    Object res = command.execute();
	    trx.commit();
	    return res;
	} catch (PersistenceException pe) {
	    if (trx != null && trx.isActive())
		trx.rollback();
	    throw pe;
	} finally {
	    if (em != null && em.isOpen())
		em.close();
	}
    }
}
