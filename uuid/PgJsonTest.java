package com.postgresql.learning;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

public class PgJsonTest {

  public static void main(String[] args) throws ClassNotFoundException,
                                                SQLException, ParseException {
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
    
    Instant instant = Instant.now();
    System.out.println("instant - " + instant);
    
    Date date11 = new Date();
    TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
    Calendar cal = Calendar.getInstance(TimeZone.getDefault());
    date11 = cal.getTime();
    System.out.println("date11 - " + date11);
    
    final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssss");
    f.setTimeZone(TimeZone.getTimeZone("GMT"));
    System.out.println("SimpleDateFormat - " + f.format(new Date()));
    
    ZonedDateTime currentDate1 = ZonedDateTime.now( ZoneOffset.UTC );
    System.out.println("currentDate1 - " + currentDate1);
    
    Date currentDate = new Date();
    DateFormat datefmt = new SimpleDateFormat("yyyy-MM-DD-HH:mm:ss.sss");
    System.out.println("datefmt " + datefmt.format(new Date()));
    String d1 = datefmt.format(new Date());
    System.out.println("d1 " + d1);
    // You can also insert new records via JDBC
    st = conn.createStatement();
    String ss = "{}";
    	    System.out.println(ss.toString());
    	    
   
    //c '{\"currentDate\": + \'+toString(d1)+\'}'::jsonb);");
    rs.close();
    st.close();
    
    // You can also insert new records via JDBC
    
    //Date will return local time in Java  
    Date localTime = new Date(); 
   
    //creating DateFormat for converting time from local timezone to GMT
    DateFormat converter = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
   
    //getting GMT timezone, you can get any timezone e.g. UTC
    converter.setTimeZone(TimeZone.getTimeZone("GMT"));
   
    System.out.println("local time : " + localTime);;
    System.out.println("time in GMT : " + converter.format(localTime));
   
    Date initial = new Date();
    DateFormat dateFormatter = DateFormat.getInstance();
    dateFormatter.setTimeZone (TimeZone.getTimeZone("GMT"));
    // String gmtS = dateFormatter.format(initial);
    //System.out.println("gmtS=" + gmtS);
    // Date gmt = dateFormatter.parse(gmtS);

    // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    // System.out.println("initial = " + sdf.format(initial));
    // System.out.println("gtm     = " + sdf.format(gmt));
    
    Date date = new Date();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    // Tell the SimpleDateFormat that you want to display dates in the GMT timezone
    df.setTimeZone(TimeZone.getTimeZone("GMT"));
    System.out.println("df - " + df.format(date));
    long longId = 1001L;
    short shortId = 185;
    String query = "insert into public.user (user_data " +
               ") values ('{\"firstName\": \"Ram\", \"longId\": "+ longId +","
               //+ "\"shortId\": \""+ shortId +"\","
               + "\"shortId\": "+ shortId +","
               //+ "\"dob\": "+ date +"::text,"
               + "\"currentDate\": \""+ df.format(initial) +"\"}'" +
               "::jsonb);";
    st = conn.createStatement();
    System.out.println(query);
    st.execute(query);
    rs.close();
    st.close();
}
}

