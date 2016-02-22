package uo.sdi.acciones;

import uo.sdi.acciones.impl.UsuarioLogic;
import uo.sdi.acciones.impl.ViajeLogic;
import uo.sdi.acciones.logic.UsuarioLogica;
import uo.sdi.acciones.logic.ViajeLogica;

public class LogicaFactory {

    public static UsuarioLogica nuevoUsuario(){
	return new UsuarioLogic();
	
    }
    public static ViajeLogica nuevoViaje(){
   	return new ViajeLogic();
   	
       }
}
