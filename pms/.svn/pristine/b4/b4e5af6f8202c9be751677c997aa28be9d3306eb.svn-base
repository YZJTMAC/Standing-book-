package pms_test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Project_year_summary_Data {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(123);
		String driver="com.mysql.jdbc.Driver";  
	    String url="jdbc:mysql://192.168.0.230:3306/dsy-pms";//orcl为sid  
	    String user="root";  
	    String password="password";  
	    Connection conn=null;  
	     Statement stat=null;   
	     Statement stat1=null;  
	    try {  
	        //1、注册驱动  
	        Class.forName(driver);  
	        //2、获取连接  
	         conn= DriverManager.getConnection(url, user, password);  
	         //System.out.println(conn);  
	        //3、创建statement对象  
	        stat1=conn.createStatement();
	        stat=conn.createStatement();
	         //4、执行sql语句  
	         String sql="select * from project_summary_old";  
	         ResultSet rs = stat1.executeQuery(sql);
	         //System.out.println(stat.execute(sql));  
	         //5、处理结果集,如果有的话就处理，没有就不用处理，当然insert语句就不用处理了  
	         while (rs.next()) {
	        	 	int projectID = rs.getInt("project_id");
					BigDecimal carryover_tw = rs.getBigDecimal("carryover_tw");
					BigDecimal carryover_sx = rs.getBigDecimal("carryover_sx");
					BigDecimal carryover_se = rs.getBigDecimal("carryover_se");
					BigDecimal carryover_cost_tw = rs.getBigDecimal("carryover_cost_tw");
					BigDecimal carryover_cost_th = rs.getBigDecimal("carryover_cost_th");
					BigDecimal carryover_cost_fo = rs.getBigDecimal("carryover_cost_fo");
					BigDecimal carryover_cost_ff = rs.getBigDecimal("carryover_cost_ff");
					BigDecimal carryover_cost_sx = rs.getBigDecimal("carryover_cost_sx");
					BigDecimal carryover_cost_se = rs.getBigDecimal("carryover_cost_se");
					System.out.println("ok");
					String sql4 = "insert into project_year_summary (project_id,year,overAmount) values" +
							"("+projectID+",12,"+carryover_cost_tw+")";
					stat.execute(sql4);
					String sql5 = "insert into project_year_summary (project_id,year,overAmount) values" +
							"("+projectID+",13,"+carryover_cost_th+")";
					stat.execute(sql5);
					String sql6 = "insert into project_year_summary (project_id,year,overAmount) values" +
							"("+projectID+",14,"+carryover_cost_fo+")";
					stat.execute(sql6);
					String sql7 = "insert into project_year_summary (project_id,year,incomeAmount,overAmount) values" +
							"("+projectID+",15,"+carryover_tw+","+carryover_cost_ff+")";
					stat.execute(sql7);
					String sql8 = "insert into project_year_summary (project_id,year,incomeAmount,overAmount) values" +
							"("+projectID+",16,"+carryover_sx+","+carryover_cost_sx+")";
					stat.execute(sql8);
					String sql9 = "insert into project_year_summary (project_id,year,incomeAmount,overAmount) values" +
							"("+projectID+",17,"+carryover_se+","+carryover_cost_se+")";
					stat.execute(sql9);
				}
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	    finally{  
	        //6、关闭资源  
	        try {  
	            if(stat!=null)stat.close();  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  try {  
	            if(stat1!=null)stat1.close();  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }
	        try {  
	            if(conn!=null)conn.close();  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	    } 
	}

}
