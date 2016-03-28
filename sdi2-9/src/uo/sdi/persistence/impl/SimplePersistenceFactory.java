package uo.sdi.persistence.impl;

import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.RatingDao;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.Transaction;
import uo.sdi.persistence.TripDao;
import uo.sdi.persistence.UserDao;

/**
 * Implementaci??????n de la factoria que devuelve implementaci??????n de la capa
 * de persistencia con Jdbc 
 * 
 * @author Enrique
 *
 */
public class SimplePersistenceFactory implements PersistenceFactory {

	public Transaction newTransaction() {
		return new TransactionJdbcImpl();
	    }

	    public  RatingDao newRatingDao() {
		return new RatingDaoJdbcImpl();
	    }

	    public  UserDao newUserDao() {
		return new UserDaoJdbcImpl();
	    }

	    public  TripDao newTripDao() {
		return new TripDaoJdbcImpl();
	    }

	    public  SeatDao newSeatDao() {
		return new SeatDaoJdbcImpl();
	    }

	    public  ApplicationDao newApplicationDao() {
		return new ApplicationDaoJdbcImpl();
	    }
	

}
