package uo.sdi.bussines;

public interface ServicesFactory {

	UsuarioService nuevoUsuario();

	ViajeService nuevoViaje();

	RatingService nuevoRating();

	ApplicationService nuevoApplication();

}
