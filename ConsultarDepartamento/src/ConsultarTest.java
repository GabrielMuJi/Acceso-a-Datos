import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ConsultarTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResultSet resul = null;
		PreparedStatement sentencia = null;
		Connection conexion = null;
		int departamento = Integer.parseInt(args[0]);
		try
		{
			//Cargamos el driver
			Class.forName("com.mysql.jdbc.Driver");
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/accesodatos","root","rooterino");
			String sql= "SELECT COUNT(nombre) FROM departamentos WHERE numero= ?";
			sentencia = (PreparedStatement) conexion.prepareStatement(sql);
			sentencia.setInt(1, departamento);
			resul = sentencia.executeQuery();
			while (resul.next()) {
				int existencia = resul.getInt(1);
				if (existencia==0) {
					System.out.println("El departamento buscado no existe");
					return;
				}
			}
			sql= "SELECT apellido,salario,oficio FROM empleados WHERE dept_no= ?";
			sentencia = (PreparedStatement) conexion.prepareStatement(sql);
			sentencia.setInt(1, departamento);
			resul = sentencia.executeQuery();
			while (resul.next()) {
				System.out.println(resul.getString(1)+" -- "+resul.getInt(2)+" -- "+resul.getString(3));
			}
			sql = "SELECT AVG(salario) FROM empleados WHERE dept_no= ?";
			sentencia = (PreparedStatement) conexion.prepareStatement(sql);
			sentencia.setInt(1, departamento);
			resul = sentencia.executeQuery();
			while (resul.next()) {
				System.out.println("El salario medio del departamento buscado es: "+resul.getInt(1));
			}
			sql = "SELECT COUNT(apellido) FROM empleados WHERE dept_no= ?";
			sentencia = (PreparedStatement) conexion.prepareStatement(sql);
			sentencia.setInt(1, departamento);
			resul = sentencia.executeQuery();
			while (resul.next()) {
				System.out.println("En el departamento buscado hay "+resul.getInt(1)+" personas");
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
