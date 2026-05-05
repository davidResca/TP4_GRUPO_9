package servlets;

import dao.SeguroDAO;
import dao.TipoSeguroDAO;
import dominio.Seguro;
import dominio.TipoSeguro;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListarSeguroServlet")
public class ListarSeguroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public ListarSeguroServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        TipoSeguroDAO tipoDAO = new TipoSeguroDAO();
        ArrayList<TipoSeguro> tipos = tipoDAO.obtenerTodos();
        request.setAttribute("tipos", tipos);
        
        int idFiltro = 0;       
        
        String paramFiltro = request.getParameter("ddlFiltroTipo"); 
        
       
        if (paramFiltro != null && !paramFiltro.isEmpty()) {
            idFiltro = Integer.parseInt(paramFiltro);
        }
        
        SeguroDAO seguroDAO = new SeguroDAO();
        ArrayList<Seguro> lista = seguroDAO.listarSeguros(idFiltro);
        request.setAttribute("lista", lista);

        RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguro.jsp"); 
        rd.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
