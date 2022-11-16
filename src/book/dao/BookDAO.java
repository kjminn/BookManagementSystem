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
			String sql = "select isbn, name, publish, author, price, category_name from book, category where book.category=category.category_id and "+columnName[selectedIndex]+" like ?";
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
	
	public void insert(Connection con, BookVO vo) {
		String sql = "insert into book values(?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getIsbn());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPublish());
			pstmt.setString(4, vo.getAuthor());
			pstmt.setInt(5, vo.getPrice());
			int categoryId = 0;
			switch (vo.getCategoryName()) {
			case "IT도서":
				categoryId = 10;
				break;
			case "소설":
				categoryId = 20;
				break;
			case "비소설":
				categoryId = 30;
				break;
			case "경제":
				categoryId = 40;
				break;
			case "사회":
				categoryId = 50;
				break;				
			}
			pstmt.setInt(6, categoryId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	

}
