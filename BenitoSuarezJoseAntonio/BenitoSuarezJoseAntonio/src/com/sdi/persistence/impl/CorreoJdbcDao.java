package com.sdi.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sdi.conf.Conf;
import com.sdi.model.Contacto;
import com.sdi.model.Correo;
import com.sdi.persistence.CorreoDao;
import com.sdi.persistence.exception.AlreadyPersistedException;
import com.sdi.persistence.exception.NotPersistedException;
import com.sdi.persistence.exception.PersistenceException;

public class CorreoJdbcDao implements CorreoDao {
    private Connection con = null;

    @Override
    public List<Correo> getCorreos() {
	// TODO Auto-generated method stub
	PreparedStatement ps = null;
	ResultSet rs = null;

	List<Correo> correos = new ArrayList<Correo>();

	try {
	    // En una implemenntaci��n m��s sofisticada estas constantes habr��a
	    // que sacarlas a un sistema de configuraci��n:
	    // xml, properties, descriptores de despliege, etc
	    // String SQL_DRV = "org.hsqldb.jdbcDriver";
	    // String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

	    // Obtenemos la conexi��n a la base de datos.
	    // Class.forName(SQL_DRV);
	    // con = DriverManager.getConnection(SQL_URL, "sa", "");
	    ps = con.prepareStatement(Conf.get("GETCORREOS"));
	    rs = ps.executeQuery();

	    while (rs.next()) {
		Correo correo = new Correo();
		correo.setId(rs.getInt("ID"));
		correo.setFechahora(rs.getLong("FECHAHORA"));
		correo.setAsunto(rs.getString("ASUNTO"));
		correo.setCuerpo(rs.getString("CUERPO"));
		correo.setCarpeta(rs.getInt("CARPETA"));
		correo.setLogin_Usuario(rs.getString("LOGIN_USUARIO"));

		correos.add(correo);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Invalid SQL or database schema", e);
	} finally {
	    if (rs != null) {
		try {
		    rs.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    if (ps != null) {
		try {
		    ps.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    // if (con != null) {try{ con.close(); } catch (Exception ex){}};
	}

	return correos;
    }

    public List<Contacto> getDestinatariosCorreo(int idCorreo) {
	// TODO Auto-generated method stub
	PreparedStatement ps = null;
	ResultSet rs = null;

	List<Contacto> contactos = new ArrayList<Contacto>();

	try {
	    // En una implemenntaci��n m��s sofisticada estas constantes habr��a
	    // que sacarlas a un sistema de configuraci��n:
	    // xml, properties, descriptores de despliege, etc
	    // String SQL_DRV = "org.hsqldb.jdbcDriver";
	    // String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

	    // Obtenemos la conexi��n a la base de datos.
	    // Class.forName(SQL_DRV);
	    // con = DriverManager.getConnection(SQL_URL, "sa", "");
	    ps = con.prepareStatement(Conf.get("GETDESTINATARIOS"));
	    ps.setInt(1, idCorreo);
	    rs = ps.executeQuery();

	    while (rs.next()) {
		Contacto contacto = new Contacto();
		contacto.setId(rs.getInt("ID"));
		contacto.setEmail(rs.getString("EMAIL"));
		contacto.setNombre(rs.getString("NOMBRE"));
		contacto.setApellidos(rs.getString("APELLIDOS"));
		contacto.setDireccion(rs.getString("DIRECCION"));
		contacto.setCiudad(rs.getString("CIUDAD"));
		contacto.setAgenda_Usuario(rs.getString("AGENDA_USUARIO"));

		contactos.add(contacto);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Invalid SQL or database schema", e);
	} finally {
	    if (rs != null) {
		try {
		    rs.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    if (ps != null) {
		try {
		    ps.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    // if (con != null) {try{ con.close(); } catch (Exception ex){}};
	}

	return contactos;
    }

    @Override
    public List<Correo> getLoginCorreos(String login) {
	// TODO Auto-generated method stub
	PreparedStatement ps = null;
	ResultSet rs = null;

	List<Correo> correos = new ArrayList<Correo>();

	try {
	    // En una implemenntaci��n m��s sofisticada estas constantes habr��a
	    // que sacarlas a un sistema de configuraci��n:
	    // xml, properties, descriptores de despliege, etc
	    // String SQL_DRV = "org.hsqldb.jdbcDriver";
	    // String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

	    // Obtenemos la conexi��n a la base de datos.
	    // Class.forName(SQL_DRV);
	    // con = DriverManager.getConnection(SQL_URL, "sa", "");
	    ps = con.prepareStatement(Conf.get("GETLOGINCORREOS"));
	    ps.setString(1, login);
	    rs = ps.executeQuery();

	    while (rs.next()) {
		Correo correo = new Correo();
		correo.setId(rs.getInt("ID"));
		correo.setFechahora(rs.getLong("FECHAHORA"));
		correo.setAsunto(rs.getString("ASUNTO"));
		correo.setCuerpo(rs.getString("CUERPO"));
		correo.setCarpeta(rs.getInt("CARPETA"));
		correo.setLogin_Usuario(rs.getString("LOGIN_USUARIO"));

		correos.add(correo);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Invalid SQL or database schema", e);
	} finally {
	    if (rs != null) {
		try {
		    rs.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    if (ps != null) {
		try {
		    ps.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    // if (con != null) {try{ con.close(); } catch (Exception ex){}};
	}

	return correos;
    }

    @Override
    public List<Correo> getLoginCarpetaCorreos(String login, int carpeta) {
	// TODO Auto-generated method stub
	PreparedStatement ps = null;
	ResultSet rs = null;

	List<Correo> correos = new ArrayList<Correo>();

	try {
	    // En una implemenntaci��n m��s sofisticada estas constantes habr��a
	    // que sacarlas a un sistema de configuraci��n:
	    // xml, properties, descriptores de despliege, etc
	    // String SQL_DRV = "org.hsqldb.jdbcDriver";
	    // String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

	    // Obtenemos la conexi��n a la base de datos.
	    // Class.forName(SQL_DRV);
	    // con = DriverManager.getConnection(SQL_URL, "sa", "");
	    ps = con.prepareStatement(Conf.get("GETLOGINCARPETA"));
	    ps.setString(1, login);
	    ps.setInt(2, carpeta);
	    rs = ps.executeQuery();

	    while (rs.next()) {
		Correo correo = new Correo();
		correo.setId(rs.getInt("ID"));
		correo.setFechahora(rs.getLong("FECHAHORA"));
		correo.setAsunto(rs.getString("ASUNTO"));
		correo.setCuerpo(rs.getString("CUERPO"));
		correo.setCarpeta(rs.getInt("CARPETA"));
		correo.setLogin_Usuario(rs.getString("LOGIN_USUARIO"));

		correos.add(correo);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Invalid SQL or database schema", e);
	} finally {
	    if (rs != null) {
		try {
		    rs.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    if (ps != null) {
		try {
		    ps.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    // if (con != null) {try{ con.close(); } catch (Exception ex){}};
	}

	return correos;
    }

    @Override
    public int save(Correo a) throws AlreadyPersistedException {
	// TODO Auto-generated method stub
	PreparedStatement ps = null;
	int id_insertado = 0;
	try {
	    // En una implementaci��n m��s sofisticada estas constantes habr��a
	    // que sacarlas a un sistema de configuraci��n:
	    // xml, properties, descriptores de despliege, etc
	    // String SQL_DRV = "org.hsqldb.jdbcDriver";
	    // String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

	    // Obtenemos la conexi��n a la base de datos.
	    // Class.forName(SQL_DRV);
	    // con = DriverManager.getConnection(SQL_URL, "sa", "");

	    ps = con.prepareStatement(Conf.get("SAVECORREO"),
		    Statement.RETURN_GENERATED_KEYS);

	    ps.setDouble(1, a.getFechahora());
	    ps.setString(2, a.getAsunto());
	    ps.setString(3, a.getCuerpo());
	    ps.setInt(4, a.getCarpeta());
	    ps.setString(5, a.getLogin_Usuario());

	    int updated = ps.executeUpdate();
	    if (updated != 1) {
		throw new AlreadyPersistedException("Correo " + a
			+ " already persisted.");
	    }

	    if (updated == 1) {
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
		    id_insertado = generatedKeys.getInt(1);
		}
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
	    // if (con != null) {try{ con.close();} catch (Exception ex){}};
	}
	return id_insertado;

    }

    @Override
    public void update(Correo a) throws NotPersistedException {
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
	    ps = con.prepareStatement(Conf.get("UPDATECORREO"));

	    ps.setDouble(1, a.getFechahora());
	    ps.setString(2, a.getAsunto());
	    ps.setString(3, a.getCuerpo());
	    ps.setInt(4, a.getCarpeta());
	    ps.setString(5, a.getLogin_Usuario());
	    ps.setLong(6, a.getId());

	    rows = ps.executeUpdate();
	    if (rows != 1) {
		throw new NotPersistedException("Correo " + a + " not found");
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
    public void delete(int id) throws NotPersistedException {
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
	    ps = con.prepareStatement(Conf.get("DELETECORREO"));

	    ps.setLong(1, id);

	    rows = ps.executeUpdate();
	    if (rows != 1) {
		throw new NotPersistedException("Correo " + id + " not found");
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
    public void deleteCorreosLogin(String login) {
	// TODO Auto-generated method stub

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
	    ps = con.prepareStatement(Conf.get("DELETECORREOSLOGIN"));

	    ps.setString(1, login);

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
    public void deleteCorreos() {
	// TODO Auto-generated method stub

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
	    ps = con.prepareStatement(Conf.get("DELETECORREOS"));

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
	    ps = con.prepareStatement(Conf.get("REINICIARIDCORREOS"));

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
