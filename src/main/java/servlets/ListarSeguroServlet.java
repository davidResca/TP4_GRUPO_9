package servlets;

import dao.SeguroDAO;
import dominio.Seguro;

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
		SeguroDAO seguroDAO = new SeguroDAO();
		ArrayList<Seguro> lista = seguroDAO.listarSeguros(0);

		request.setAttribute("lista", lista);
		RequestDispatcher rd = request.getRequestDispatcher("ListarSeguro.jsp");
		rd.forward(request, response);
	}
}
