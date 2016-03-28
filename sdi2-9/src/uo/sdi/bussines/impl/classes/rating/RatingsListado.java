package uo.sdi.bussines.impl.classes.rating;

import java.util.List;

import uo.sdi.infraestructure.Factories;
import uo.sdi.model.Rating;

public class RatingsListado {
	public List<Rating> comentariosSobrePromotor() {

		return Factories.persistence.newRatingDao().comentariosSobrePromotor();
	    }
}
