package book.view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import book.vo.BookVO;

public class BookSearchView extends JPanel {
	JTable table;
	DefaultTableModel model;
	ArrayList<BookVO> bookVOList;
	String[] header = {"도서번호", "도서명", "출판사", "저자명", "도서가격", "카테고리"};
	JLabel lbl;
	JTextField textSearch;
	JButton btnSearch;
	String searchWord = "";
	
	public BookSearchView() {
		setLayout(new BorderLayout());
		lbl = new JLabel("검색어: ");
		textSearch = new JTextField(20);
		btnSearch = new JButton("검색");
	}

}











