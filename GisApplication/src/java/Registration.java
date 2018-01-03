
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Registration
{
  public boolean validateUserLogin(String uname, String pwd)
  {
        
        boolean flag = false;
        Connection con = null;
        try
        {
             con = createConnection();
             if(con != null)
             {
                Statement stat = con.createStatement();
                String qry = "SELECT * FROM login_master WHERE ID = '"+uname+"' AND passwd = '"+pwd+"' ";
                ResultSet rs = stat.executeQuery(qry);
                if(rs.next())
                {
                    flag = true;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(con != null)
            {
                try
                {
                    con.close();
                }
                catch (SQLException e)
                {
     e.printStackTrace();
    }
   }
  }
  return flag;
 }
 
 public Connection createConnection() {
  System.out.println("Createing postgres DataBase Connection");
  Connection connection = null;

  try
  {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","root");
  }
  catch (ClassNotFoundException | SQLException e)
  {
        return null;
  }
  if(connection != null)
  {
     System.out.println("Connection created successfully....");
  }
  return connection;
 }
}

