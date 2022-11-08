package book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import book.vo.BookVO;

public class BookDAO {
	ArrayList<BookVO> bookVOList;  
	
	
	public ArrayList<BookVO> select(Connection con, String searchWord, int selectedIndex){
		bookVOList = new ArrayList<BookVO>();
		String[] columnName = {"name", "publish", "author"};
		System.out.println(selectedIndex);
		try {
			String sql = "select isbn, name, publish, author, price, category_name from book, category where book.category=category.category and "+columnName[selectedIndex]+" like ?";
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
				vo.setCategory(rs.getString("category_name"));
				bookVOList.add(vo);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return bookVOList;
	}

}
