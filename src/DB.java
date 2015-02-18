import java.sql.*;

public class DB {

		public Connection conn=null;
		DB(){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/libmanagement";
				conn = DriverManager.getConnection(url, "root", "vikas");
				System.out.println("conn established with database. wow..:)");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		public ResultSet runSql(String sql) throws SQLException {
			Statement sta = conn.createStatement();
			return sta.executeQuery(sql);
		}
	 
		public boolean runSql2(String sql) throws SQLException {
			Statement sta = conn.createStatement();
			return sta.execute(sql);
		}
	 
		@Override
		protected void finalize() throws Throwable {
			if (conn != null || !conn.isClosed()) {
				conn.close();
			}
		}
}
