package com.postgresql.learning;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class PgJsonTest {

  public static void main(String[] args) throws ClassNotFoundException,
                                                SQLException {
	Connection conn = null;
    Class.forName("org.postgresql.Driver");

    /*
     * In the following lines, replace 'testdb' with the name of your db
     * and username.
     */
    //String url = "jdbc:postgresql://localhost:5432", "postgres", "postgres");
    
    Properties props = new Properties();
    props.setProperty("postgres","postgres");
    conn = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "postgres");

    // A basic SELECT statement.
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM public.user");
    while (rs.next())
    {
       System.out.print("Contents of Column 1");
       System.out.println(rs.getString(1));
    }
    rs.close();
    st.close();
    
    /*
     * An example of using one of the more advanced SELECT queries 
     */
    st = conn.createStatement();
    rs = st.executeQuery(
        "select * from public.user where user_data @> " +
        "'{ \"a\": \"2\"}'");
    while (rs.next())
    {
       System.out.print("Results of Query");
       System.out.println(rs.getString(1));
    }
    
    Date currentDate = new Date();
    DateFormat datefmt = new SimpleDateFormat("yyyy-MM-DD-HH:mm:ss.SSSS");
    System.out.println(datefmt.format(new Date()));
    String d1 = datefmt.format(new Date());
    d1 = "abc";
    System.out.println(d1);
    // You can also insert new records via JDBC
    st = conn.createStatement();
    String ss = "{}";
    	    System.out.println(ss.toString());
    	    
   
    //c '{\"currentDate\": + \'+toString(d1)+\'}'::jsonb);");
    rs.close();
    st.close();
}
}

