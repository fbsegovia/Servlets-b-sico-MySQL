package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import entidades.Persona;
import persistencia.dao.PersonaDao;

public class PersonaDaoMySql implements PersonaDao{

	private Connection conexion;
	
	
	/*BLOQUE ESTÁTICO:
	Los bloques estaticos son ejecutados por java "JUSTO ANTES" de ejecutar
	el metodo "main()", Java busca todos los metodos estaticos que haya en el programa
	y los ejecuta.*/
	static{
		try {
			/*Esta sentencia car del "JAR" que hemos importado una clase que se
			llama "DRIVER" que esta en el paquete "com.mysql.jdbc". Esta clase se
			carga previamente en JAVA para más adelante se llamada y utilizada.*/ 
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver cargado");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver no cargado");
			e.printStackTrace();
		}
	}
	
	/*Métodos básicos de apertura y cierre de conexion con la base de datos*/
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/prueba";
		String usuario = "root";
		String password = "telefonica";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	@Override
	public boolean alta(Persona p) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		String query = "insert into personas (NOMBRE,EDAD,PESO)"
				+ "values (?,?,?)";
		try {
		//Preparamos la query con valores parametrizables(?).
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1,p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setDouble(3, p.getPeso());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0){
				alta = false;
			}else{
				alta = true;
			}
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: "+p);
			alta=false;
			e.printStackTrace();
		}finally{
			cerrarConexion();
		}
	//Otra forma sería == values('"+p.getNombre()+"');
		return alta;
	}
	
	
	@Override
	public boolean baja(int id) {
		if(!abrirConexion()){
			return false;
		}
		boolean borrado = true;
		String query = "delete from personas where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);

			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0){
				borrado = false;
			}else{
				borrado = true;
			}
			
		} catch (SQLException e) {
			System.out.println("baja -> No se ha podido dar de baja el id: "+id);
			e.printStackTrace();
			borrado= false;
		}finally {
			cerrarConexion();
		}
		return borrado;
	}
	
	
	
	@Override
	public boolean modificar(Persona p) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificado = true;
		String query = "update personas set NOMBRE=?, EDAD=?, PESO=? where id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setDouble(3, p.getPeso());
			ps.setInt(4, p.getId());
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0){
				modificado = false;
			}else{
				modificado = true;
			}
		} catch (SQLException e) {
			System.out.println("modificar -> error al modificar la persona: "+p);
			e.printStackTrace();
			modificado= false;
		}finally {
			cerrarConexion();
		}
		return modificado;
	}
	
	
	
	@Override
	public Persona obtener(int id) {
		if(!abrirConexion()){
			return null;
		}
		Persona persona = null;
		String query = "select id,nombre,edad,peso from personas where id =?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			//"executeQuery" esta hecha para "NO MODIFICAR" las bases de datos,
			//mientras que "executeUpdate" esta hecha para "MODIFICAR" la base de datos.
			ps.executeQuery();
			
			//ResultSet = comprueba si es posible acceder al registro de datos y si puede
			//el nexte le hace avanzar.
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				persona = new Persona();
				persona.setId(rs.getInt(1));
				persona.setNombre(rs.getString(2));
				persona.setEdad(rs.getInt(3));
				persona.setPeso(rs.getDouble(4));
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener la persona con id "+id);
			e.printStackTrace();
			persona = null;
		}finally {
			cerrarConexion();
		}
		return persona;
	}
	
	
	
	@Override
	public List<Persona> listar() {
		if(!abrirConexion()){
			return null;
		}
		List<Persona> listaPersonas = new ArrayList<>();
		String query = "select id,nombre,edad,peso from personas";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			//Al no haber ninguna referencia limitante como "por id", se expondran
			//todos los valores de las columnas se
			ps.executeQuery();
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Persona persona = new Persona();
				persona = new Persona();
				persona.setId(rs.getInt(1));
				persona.setNombre(rs.getString(2));
				persona.setEdad(rs.getInt(3));
				persona.setPeso(rs.getDouble(4));
				listaPersonas.add(persona);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener las personas ");
			e.printStackTrace();
		}finally {
			cerrarConexion();
		}
		return listaPersonas;
	}

}
