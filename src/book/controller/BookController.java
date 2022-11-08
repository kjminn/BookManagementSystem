package book.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import book.dao.BookDAO;
import book.dao.JDBCConnector;
import book.view.BookSearchView;
import book.vo.BookVO;

public class BookController extends JFrame {
	BookSearchView searchPan;
	BookDAO dao;
	ArrayList<BookVO> bookVOList;
	Connection con;
	JComboBox<String> combo;
	
	public BookController() {
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		con = JDBCConnector.getCon();
		searchPan = new BookSearchView();
		combo = searchPan.getCombo();
		dao = new BookDAO();
		bookVOList = dao.select(con, searchPan.getSearchWord(), combo.getSelectedIndex());
		searchPan.setBookVOList(bookVOList);
		searchPan.initView();
		
		JButton btnSearch = searchPan.getBtnSearch();
		btnSearch.addActionListener(btnL);
		
		tab.add("도서검색", searchPan);
		
		add(tab);
		setTitle("도서관리시스템");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(500, 300, 600, 500);
		setVisible(true);
	}

	ActionListener btnL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			bookVOList = dao.select(con, searchPan.getSearchWord(), combo.getSelectedIndex());
			searchPan.setBookVOList(bookVOList);
			searchPan.putSearchResult();
		}
	};
	
	public static void main(String[] args) {
		new BookController();
	}

}








