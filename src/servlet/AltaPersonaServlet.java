package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Persona;
import negocio.GestorPersonas;

/**
 * Servlet implementation class AltaPersonaServlet
 */
public class AltaPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaPersonaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String sEdad = request.getParameter("edad");
		String sPeso = request.getParameter("peso");
		
		double dPeso = Double.parseDouble(sPeso);
		int iEdad = Integer.parseInt(sEdad);
		
		GestorPersonas gp = new GestorPersonas();
		Persona persona = new Persona();
		persona.setNombre(nombre);
		persona.setEdad(iEdad);
		persona.setPeso(dPeso);
		
		boolean correcto = gp.alta(persona);
		if(correcto){
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}else{
			//Todo
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
