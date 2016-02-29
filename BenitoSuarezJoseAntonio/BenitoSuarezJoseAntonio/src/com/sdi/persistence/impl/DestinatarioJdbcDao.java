package com.sdi.persistence.impl;

import java.sql.*;

import com.sdi.conf.Conf;
import com.sdi.model.Destinatario;
import com.sdi.persistence.DestinatarioDao;
import com.sdi.persistence.exception.*;

public class DestinatarioJdbcDao implements DestinatarioDao {
    private Connection con = null;

    @Override
    public void save(Destinatario a) throws AlreadyPersistedException {
	// TODO Auto-generated method stub
	PreparedStatement ps = null;
	int rows = 0;

	try {
	    // En una implementaci��n m��s sofisticada estas constantes habr��a
	    // que sacarlas a un sistema de configuraci��n:
	    // xml, properties, descriptores de despliege, etc
	    // String SQL_DRV = "org.hsqldb.jdbcDriver";
	    // String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

	    // Obtenemos la conexi��n a la base de datos.
	    // Class.forName(SQL_DRV);
	    // con = DriverManager.getConnection(SQL_URL, "sa", "");
	    ps = con.prepareStatement(Conf.get("SAVEDESTINATARIO"));

	    ps.setInt(1, a.getId_Correo());
	    ps.setInt(2, a.getId_Contacto());

	    rows = ps.executeUpdate();
	    if (rows != 1) {
		throw new AlreadyPersistedException("Destinatario " + a
			+ " already persisted");
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Invalid SQL or database schema"
		    + ps.toString(), e);
	} finally {
	    if (ps != null) {
		try {
		    ps.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    // if (con != null) {try{ con.close(); } catch (Exception ex){}};
	}

    }

    @Override
    public void delete(int id_Correo) throws NotPersistedException {
	// TODO Auto-generated method stub

	PreparedStatement ps = null;
	int rows = 0;

	try {
	    // En una implementaci��n m��s sofisticada estas constantes habr��a
	    // que sacarlas a un sistema de configuraci��n:
	    // xml, properties, descriptores de despliege, etc
	    // String SQL_DRV = "org.hsqldb.jdbcDriver";
	    // String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

	    // Obtenemos la conexi��n a la base de datos.
	    // Class.forName(SQL_DRV);
	    // con = DriverManager.getConnection(SQL_URL, "sa", "");
	    ps = con.prepareStatement(Conf.get("DELETEDESTINATARIO"));

	    ps.setLong(1, id_Correo);

	    rows = ps.executeUpdate();
	    if (rows == 0) {
		throw new NotPersistedException("Correo " + id_Correo
			+ " not found");
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Invalid SQL or database schema", e);
	} finally {
	    if (ps != null) {
		try {
		    ps.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    // if (con != null) {try{ con.close(); } catch (Exception ex){}};
	}

    }

    @Override
    public void reiniciaID() {
	PreparedStatement ps = null;

	try {
	    // En una implementaci��n m��s sofisticada estas constantes habr��a
	    // que sacarlas a un sistema de configuraci��n:
	    // xml, properties, descriptores de despliege, etc
	    // String SQL_DRV = "org.hsqldb.jdbcDriver";
	    // String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

	    // Obtenemos la conexi��n a la base de datos.
	    // Class.forName(SQL_DRV);
	    // con = DriverManager.getConnection(SQL_URL, "sa", "");
	    ps = con.prepareStatement(Conf.get("REINICIARIDDESTINATARIO"));

	    ps.executeUpdate();

	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Invalid SQL or database schema", e);
	} finally {
	    if (ps != null) {
		try {
		    ps.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    // if (con != null) {try{ con.close(); } catch (Exception ex){}};
	}
    }

    @Override
    public void setConection(Connection c) {
	this.con = c;

    }

}
