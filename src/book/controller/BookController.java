package book.controller;

import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JFrame;

import book.dao.BookDAO;
import book.dao.JDBCConnector;
import book.view.BookSearchView;
import book.vo.BookVO;

public class BookController extends JFrame {
	
	public BookController() {
		Connection con = JDBCConnector.getCon();
		BookSearchView searchPan = new BookSearchView();
		BookDAO dao = new BookDAO();
		ArrayList<BookVO> bookVOList = dao.select(con, searchPan.getSearchWord());
		searchPan.setBookVOList(bookVOList);
		searchPan.initView();
		
		add(searchPan);
		setTitle("도서관리시스템");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(500, 300, 600, 500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new BookController();
	}

}








