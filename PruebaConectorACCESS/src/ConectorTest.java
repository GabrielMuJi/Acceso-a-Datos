
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConectorTest {

	public static void main(String[] args) {
		ResultSet resul = null;
		Statement sentencia = null;
		Connection conexion = null;
		
		try
		{
			//Cargamos el driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conexion = (Connection) DriverManager.getConnection("jdbc:odbc:EJEMPLO-DEPARTAMENTOS");
			sentencia = (Statement) conexion.createStatement();	
			resul = sentencia.executeQuery("SELECT * FROM departamentos");
			while (resul.next()) {
				System.out.println(resul.getInt(1)+" -- "+resul.getString(2)+" -- "+resul.getString(3));
			}
	    } 
		catch (ClassNotFoundException cn) 
		{
			cn.printStackTrace();
	    }
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				resul.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				sentencia.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}