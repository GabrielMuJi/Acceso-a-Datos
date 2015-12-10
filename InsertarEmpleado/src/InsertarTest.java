import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
public class InsertarTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try 
		{
			if (args.length!=7) {
				throw new IllegalArgumentException();
			}
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/accesodatos","root","rooterino");
			String emple_no = args[0];
			String apellido = args[1];
			String oficio = args[2];
			String dir = args[3];
			String salario = args[4];
			String comision = args[5];
			String dept_no = args[6];
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = (Date) Calendar.getInstance().getTime();
			String fecha_alta = dateFormat.format(date);
			if (Integer.parseInt(salario)<=0) {
				throw new IllegalArgumentException();
			}
			String sql = "INSERT INTO empleados VALUES ("+emple_no+",'"+apellido+"','"+oficio+"',"+dir+","+salario+","+comision+","+dept_no+",'"+fecha_alta+"')";
			System.out.println(sql);
			Statement sentencia = conexion.createStatement();
			int filas = sentencia.executeUpdate(sql);
			System.out.println("Filas afectadas: "+filas);
			sentencia.close();
			conexion.close();
		}
		catch (ClassNotFoundException cn) 
		{
			cn.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
