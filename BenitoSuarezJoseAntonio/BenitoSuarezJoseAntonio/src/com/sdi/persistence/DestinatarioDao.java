package com.sdi.persistence;

import java.sql.Connection;

import com.sdi.model.Destinatario;
import com.sdi.persistence.exception.AlreadyPersistedException;
import com.sdi.persistence.exception.NotPersistedException;

public interface DestinatarioDao {

    void save(Destinatario a) throws AlreadyPersistedException;

    void delete(int id_Correo) throws NotPersistedException;

    void reiniciaID();

    void setConection(Connection c);

}
