package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dominio.Seguro;

public class SeguroDAO {
	
	private String host ="jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "segurosgroup";
	
	public int agregarSeguro(Seguro seguro) {
		
	    String query = "INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) VALUES (?, ?, ?, ?)";
	    int filas = 0;
	    Connection cn = null;
	    PreparedStatement ps = null;

	    try {
	        cn = DriverManager.getConnection(host + dbName, user, pass);
	        ps = cn.prepareStatement(query);
	        
	        
	        ps.setString(1, seguro.getDescripcion());
	        ps.setInt(2, seguro.getTipoSeguro().getId());
	        ps.setDouble(3, seguro.getCostoContratacion());
	        ps.setDouble(4, seguro.getCostoAsegurado());
	        
	        filas = ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (cn != null) cn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return filas;
	}

}
