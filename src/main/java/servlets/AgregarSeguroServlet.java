package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SeguroDAO;
import dominio.Seguro;
import dominio.TipoSeguro;

/**
 * Servlet implementation class AgregarSeguroServlet
 */
@WebServlet("/AgregarSeguroServlet")
public class AgregarSeguroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AgregarSeguroServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		int filas=0;
		if(request.getParameter("btnAceptarSeguro")!= null) {
			Seguro seguro = new Seguro();
			seguro.setDescripcion(request.getParameter("txtDescripcion"));
			
			TipoSeguro tipo = new TipoSeguro();
	        int idTipo = Integer.parseInt(request.getParameter("selectTipoSeguro"));
	        tipo.setId(idTipo); 
	        
	        
	        seguro.setTipoSeguro(tipo);
			seguro.setCostoContratacion(Double.parseDouble(request.getParameter("txtCostoContrartacion")));
			seguro.setCostoAsegurado(Double.parseDouble(request.getParameter("txtMaximoAsegurado")));
			
			SeguroDAO seguroDAO = new SeguroDAO();
			filas=seguroDAO.agregarSeguro(seguro);
		}
		 
		request.setAttribute("cantFilas",filas);
		
		RequestDispatcher rd1 = request.getRequestDispatcher("/AgregarSeguro.jsp");
		rd1.forward(request, response);
	}

}
