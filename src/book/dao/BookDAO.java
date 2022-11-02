package book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import book.vo.BookVO;

public class BookDAO {
	ArrayList<BookVO> bookVOList = new ArrayList<BookVO>();
	
	
	public ArrayList<BookVO> select(Connection con, String searchWord){
		
		try {
			String sql = "select * from book where name like ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+searchWord+"%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BookVO vo = new BookVO();
				vo.setIsbn(rs.getInt("isbn"));
				vo.setName(rs.getString("name"));
				vo.setPublish(rs.getString("publish"));
				vo.setAuthor(rs.getString("author"));
				vo.setPrice(rs.getInt("price"));
				vo.setCategory(rs.getInt("category"));
				bookVOList.add(vo);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return bookVOList;
	}

}
