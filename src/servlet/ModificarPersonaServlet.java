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
 * Servlet implementation class ModificarPersonaServlet
 */
public class ModificarPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarPersonaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String sEdad = request.getParameter("edad");
		String sPeso = request.getParameter("peso");
		String sId = request.getParameter("id");
		
		double dPeso = Double.parseDouble(sPeso);
		int iEdad = Integer.parseInt(sEdad);
		int Iid = Integer.parseInt(sId);
		
		GestorPersonas gp = new GestorPersonas();
		Persona persona = new Persona();
		persona.setId(Iid);
		persona.setNombre(nombre);
		persona.setEdad(iEdad);
		persona.setPeso(dPeso);
		
		boolean correcto = gp.modificar(persona);
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
