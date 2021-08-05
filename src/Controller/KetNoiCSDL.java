package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import dictionary.Words;

public class KetNoiCSDL {
	private Connection connection;
	public KetNoiCSDL() throws SQLException {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connectionURL = "jdbc:sqlserver://THANHDIEP\\SQLEXPRESS:1433;databaseName=Dictionary;integratedSecurity=true;";
                connection = DriverManager.getConnection(connectionURL);
                System.out.println("Kết nối CSDL thành công!");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                System.out.println("Kết nối CSDL thất bại!");
                System.err.println(e.getMessage());
            }
	}
	//ghi du lieu vao sql
	public boolean addSQL(Words w) {
		String sql = "INSERT INTO tblDictionary(word,mean,example) VALUES(?,?,?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, w.getWord());
			ps.setString(2, w.getMean());
			ps.setString(3, w.getExample());
			
			if(ps.executeUpdate()>0) {
                            System.out.println("Thêm dữ liệu thành công!");
                            return true;
			}
		} catch (SQLException e) {
                    System.err.println("Error: "+e.toString());
		}finally {
                    try {
                            connection.close();

                    } catch (SQLException e2) {
                            // TODO: handle exception
                    }
		}
		return false;
	}
	//update data in SQL
	public boolean updateSQL(Words w) {
		String sql = "UPDATE tblDictionary SET mean=?, example=? WHERE word=?";
		
		try {
                    PreparedStatement ps = connection.prepareStatement(sql);

                    ps.setString(3, w.getWord());
                    ps.setString(1, w.getMean());
                    ps.setString(2, w.getExample());


                    if(ps.executeUpdate()>0) {
                            System.out.println("Cập nhật dữ liệu thành công!");
                    }

                    return true;
		} catch (SQLException e) {
			System.err.println("Error: "+e.toString());
		}finally {
                    try {
                        connection.close();

                    } catch (SQLException e2) {
                            // TODO: handle exception
                    }
		}
		return false;
	}
	//delete data in SQL
	public boolean deleteSQL(String word) {
		String sql = "DELETE tblDictionary WHERE word=?";
		
		try {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setString(1, word);

                    if(ps.executeUpdate()>0) {
                            System.err.println("Xóa dữ liệu thành công!");
                    }
                    return true;
		} catch (SQLException e) {
                    System.err.println("Error: "+e.toString());
		}finally {
                    try {
                            connection.close();

                    } catch (SQLException e2) {
                            // TODO: handle exception
                    }
		}
		return false;
	}
	//doc du lieu tu sql
	public static ArrayList<Words> getListWords(){
            ArrayList<Words> list= new ArrayList<Words>();
            Connection c = null;

            try {
                    String connectionURL = "jdbc:sqlserver://THANHDIEP\\SQLEXPRESS:1433;databaseName=Dictionary;integratedSecurity=true;";
                    c = DriverManager.getConnection(connectionURL);

                    String sql = "SELECT * FROM tblDictionary ORDER BY word ASC";

                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery(sql);
                    while(rs.next()) {
                            Words w = new Words(rs.getString("word"), rs.getString("mean"),rs.getString("example"));

//				w.setWord(rs.getString(1));//word==1
//				w.setMean(rs.getString(2));//mean==2
//				w.setExample(rs.getString(3));//example==3
//				
                            list.add(w);
                    }
            } catch (SQLException e) {
                System.err.println("Error: "+e.toString());
            }finally {
                    try {
                            c.close();
                    } catch (SQLException e2) {
                            // TODO: handle exception
                    }
            }
            return list;
	}
	//find by word
	public static ArrayList<Words> findWord(String word){
            ArrayList<Words> list= new ArrayList<Words>();
            Connection c = null;

            try {
                String connectionURL = "jdbc:sqlserver://THANHDIEP\\SQLEXPRESS:1433;databaseName=Dictionary;integratedSecurity=true;";
                c = DriverManager.getConnection(connectionURL);

                String sql = "SELECT * FROM tblDictionary WHERE word = ?";

                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1, word);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                        Words w = new Words(rs.getString("word"), rs.getString("mean"),rs.getString("example"));

//				w.setWord(rs.getString(1));//word==1
//				w.setMean(rs.getString(2));//mean==2
//				w.setExample(rs.getString(3));//example==3
//				
                        list.add(w);
                }
            } catch (SQLException e) {
                    System.err.println("Error: "+e.toString());
            }finally {
                try {
                        c.close();
                } catch (SQLException e2) {
                        // TODO: handle exception
                }
            }
            return list;
	}
	public static void main(String[] args) throws SQLException {
		KetNoiCSDL kn = new KetNoiCSDL();
		//Words w = new Words("phone", "Điện thoại", "John has just bought a new phone");
//		kn.deleteSQL("");
//		System.out.println("=> "+kn.getListWords());
	}
}
