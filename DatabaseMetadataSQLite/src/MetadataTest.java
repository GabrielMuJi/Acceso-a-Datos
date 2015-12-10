import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MetadataTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

       try {
    	 Class.forName("org.sqlite.JDBC");
		 Connection conexion = (Connection) DriverManager.getConnection("jdbc:sqlite:C:/practicas/programacion/departamentos.db");
    	 DatabaseMetaData dbmd = conexion.getMetaData();
    	 ResultSet resul = null;
    	 String nombre = dbmd.getDatabaseProductName();
    	 String driver = dbmd.getDriverName();
    	 String url = dbmd.getURL();
    	 String usuario = dbmd.getUserName();
    	 System.out.println("==================================================");
    	 System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS");
    	 System.out.println("==================================================");
    	 System.out.println("Nombre: "+nombre);
    	 System.out.println("Driver: "+driver);
    	 System.out.println("URL: "+url);
    	 System.out.println("Usuario: "+usuario);
    	 System.out.println("==================================================");
    	 System.out.println("INFORMACIÓN SOBRE LAS TABLAS");
    	 System.out.println("==================================================");
    	 resul = dbmd.getTables(null, "departamentos", null, null);
    	 while (resul.next()) {
    		 String catalogo = resul.getString(1);
    		 String esquema = resul.getString(2);
    		 String tabla = resul.getString(3);
    		 String tipo = resul.getString(4);
    		 System.out.println(tipo + " - Catalogo: " + catalogo + ", Esquema: " + esquema + ", Nombre: " + tabla);
    		 Statement sentencia = conexion.createStatement();
        	 ResultSet rs = sentencia.executeQuery("Select * from departamentos");
        	 ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
        	 int nColumnas = rsmd.getColumnCount();
        	 String nula;
        	 System.out.println("Número de columnas recuperadas: "+ nColumnas);
        	 for (int i = 1; i<=nColumnas; i++)
        	 {
        		 System.out.println("Columna " +i+":");
        		 System.out.println(" Nombre : " +rsmd.getColumnName(i));
        		 System.out.println(" Tipo: " + rsmd.getColumnTypeName(i));
        		 if (rsmd.isNullable(i)==0) nula="NO";
        		 else nula="SI";
        		 System.out.println(" Puede ser nula?: "+nula);
        		 System.out.println(" Máximo ancho de columna: "+rsmd.getColumnDisplaySize(i));
        	 }
    	 }
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