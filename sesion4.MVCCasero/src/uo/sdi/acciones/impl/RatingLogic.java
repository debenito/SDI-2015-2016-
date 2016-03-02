package uo.sdi.acciones.impl;

import java.util.List;

import uo.sdi.acciones.logic.RatingLogica;
import uo.sdi.model.Rating;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.RatingDao;

public class RatingLogic implements RatingLogica{
    private RatingDao dao = PersistenceFactory.newRatingDao();

    @Override
    public List<Rating> comentariosSobrePromotor() {
	
	return dao.comentariosSobrePromotor();
    }

}
