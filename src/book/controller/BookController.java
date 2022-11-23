package book.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import book.dao.BookDAO;
import book.dao.JDBCConnector;
import book.view.BookInsertView;
import book.view.BookSearchView;
import book.view.BookUpdateView;
import book.vo.BookVO;

public class BookController extends JFrame {
	BookSearchView searchPan;
	BookDAO dao;
	ArrayList<BookVO> bookVOList;
	JComboBox<String> combo;
	BookInsertView insertPan;
	BookUpdateView updatePan;
	JTable table;
	static final int YES = 0;
	JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
	
	public BookController() {
		
		searchPan = new BookSearchView();
		
		combo = searchPan.getCombo();
		dao = new BookDAO();
		bookVOList = dao.select(searchPan.getSearchWord(), combo.getSelectedIndex());
		searchPan.setBookVOList(bookVOList);
		searchPan.initView();
		
		JButton btnSearch = searchPan.getBtnSearch();
		btnSearch.addActionListener(btnL);
		
//		도서 추가 화면
		insertPan = new BookInsertView();
		bookVOList = dao.select("", 0);
		insertPan.setBookVOList(bookVOList);
		insertPan.initView();
		JButton btnAdd = insertPan.getBtnAdd();
		btnAdd.addActionListener(btnAddL);		
		
//		도서 수정 화면	
		updatePan = new BookUpdateView();
		bookVOList = dao.select("", 0);
		updatePan.setBookVOList(bookVOList);
		updatePan.initView();
		JButton btnUpdate = updatePan.getBtnUpdate();
		btnUpdate.addActionListener(btnUpdateL);
		table = updatePan.getTable();	
		table.addMouseListener(tableL);
		
		tab.add("도서검색", searchPan);
		tab.add("도서추가", insertPan);
		tab.add("도서수정 및 삭제", updatePan);
		
		tab.addMouseListener(tabL);
		
		add(tab);
		setTitle("도서관리시스템");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(500, 300, 600, 500);
		setVisible(true);
	}
	
	MouseAdapter tableL = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2) {
				int result = JOptionPane.showConfirmDialog(BookController.this, "정말로 삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(result == YES) {//예: 0, 아니오:1
					BookVO vo = updatePan.neededUpdateData();
					dao.delete(vo);
					bookVOList = dao.select("", 0);
					updatePan.setBookVOList(bookVOList);
					updatePan.putSearchResult();
				}
			}
			
			if(e.getClickCount() == 1) {
				int rowIndex = table.getSelectedRow();
				updatePan.setTextField(rowIndex);
			}

		}
	};

	ActionListener btnL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			bookVOList = dao.select(searchPan.getSearchWord(), combo.getSelectedIndex());
			searchPan.setBookVOList(bookVOList);
			searchPan.putSearchResult();
		}
	};
	
	ActionListener btnAddL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			BookVO vo = insertPan.neededInsertData();
			dao.insert(vo);
			bookVOList = dao.select("", 0);
			insertPan.setBookVOList(bookVOList);
			insertPan.putSearchResult();
			insertPan.initInsertData();
		}
	};
	
	ActionListener btnUpdateL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			BookVO vo = updatePan.neededUpdateData();
			dao.update(vo);
			bookVOList = dao.select("", 0);
			updatePan.setBookVOList(bookVOList);
			updatePan.putSearchResult();
		}
	};
	
	MouseAdapter tabL = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			int tabIndex = tab.getSelectedIndex();
			if(tabIndex == 0) {
				bookVOList = dao.select("", 0);
				searchPan.setBookVOList(bookVOList);
				searchPan.putSearchResult();
			}else if(tabIndex == 1) {
				bookVOList = dao.select("", 0);
				insertPan.setBookVOList(bookVOList);
				insertPan.putSearchResult();
			}else {
				bookVOList = dao.select("", 2);
				updatePan.setBookVOList(bookVOList);
				updatePan.putSearchResult();
			}
		}
	};
	
	public static void main(String[] args) {
		new BookController();
	}

}








