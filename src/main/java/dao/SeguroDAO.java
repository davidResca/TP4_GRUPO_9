package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dominio.Seguro;
import dominio.TipoSeguro;

public class SeguroDAO {
    
    private String host ="jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String pass = "root";
    private String dbName = "SegurosGroup";

    public SeguroDAO() {}

    public int agregarSeguro(Seguro seguro) {
        String query = "INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) VALUES (?, ?, ?, ?)";
        int filas = 0;
        Connection cn = null;
        PreparedStatement ps = null;

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(host + dbName, user, pass);
            ps = cn.prepareStatement(query);
            
            ps.setString(1, seguro.getDescripcion());
            ps.setInt(2, seguro.getTipoSeguro().getId());
            ps.setDouble(3, seguro.getCostoContratacion());
            ps.setDouble(4, seguro.getCostoAsegurado());
            
            filas = ps.executeUpdate();
        } catch (Exception e) {
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

    public ArrayList<Seguro> listarSeguros(int idTipo) {
        ArrayList<Seguro> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT s.idSeguro, s.descripcion, s.costoContratacion, s.costoAsegurado," +
        " t.idTipo, t.descripcion as descTipoSeg " + "FROM seguros s JOIN tipoSeguros t ON s.idTipo = t.idTipo ";
        
        if (idTipo != 0) {
            query += "WHERE s.idTipo = ? ";
        }
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(host + dbName, user, pass);
            ps = cn.prepareStatement(query);
            
            if(idTipo != 0) {
                ps.setInt(1, idTipo);
            }
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
                TipoSeguro tipo = new TipoSeguro();
                tipo.setId(rs.getInt("idTipo"));
                tipo.setDescripcion(rs.getString("descTipoSeg"));
                
                Seguro s = new Seguro();
                s.setIdSeguro(rs.getInt("idSeguro"));
                s.setDescripcion(rs.getString("descripcion"));
                s.setTipoSeguro(tipo);
                s.setCostoContratacion(rs.getDouble("costoContratacion"));
                s.setCostoAsegurado(rs.getDouble("costoAsegurado"));
                
                lista.add(s);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
             try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
    
    public int obtenerProximoId() {
        String query = "SELECT MAX(idSeguro) FROM seguros"; 
        int proximoId = 0;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
            cn = DriverManager.getConnection(host + dbName, user, pass);
            st = cn.createStatement();
            rs = st.executeQuery(query);
            if(rs.next()) {
                proximoId = rs.getInt(1) + 1;
            }
        } catch(Exception e) { // Faltaba cerrar el try antes de este catch
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return proximoId;
    } 
} 
