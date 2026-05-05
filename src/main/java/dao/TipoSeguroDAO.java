package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dominio.TipoSeguro;

public class TipoSeguroDAO {
    
    private String host = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String pass = "root";
    private String dbName = "SegurosGroup";

    public TipoSeguroDAO() {}

    public ArrayList<TipoSeguro> obtenerTodos() {
        ArrayList<TipoSeguro> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "select idTipo, descripcion from tipoSeguros"; 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(host + dbName, user, pass);
            ps = cn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {                
                
                TipoSeguro tipo = new TipoSeguro();                
                
                tipo.setId(rs.getInt("idTipo"));
                tipo.setDescripcion(rs.getString("descripcion"));                
                
                lista.add(tipo);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
}
