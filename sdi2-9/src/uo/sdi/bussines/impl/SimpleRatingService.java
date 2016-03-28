package uo.sdi.bussines.impl;

import java.util.List;

import uo.sdi.bussines.RatingService;
import uo.sdi.bussines.impl.classes.rating.RatingsListado;
import uo.sdi.model.Rating;

public class SimpleRatingService implements RatingService {
  
    @Override
    public List<Rating> comentariosSobrePromotor() {

	return new RatingsListado().comentariosSobrePromotor();
    }

}
