package controller;

import dao.BookDAO;
import model.Book;
import java.util.List;

public class BookController {
	
	private BookDAO bookDAO;
	
	public BookController() {
		this.bookDAO = new BookDAO();
	}
	
	//Add book
	public boolean addBook(Book book) {
		return bookDAO.addBook(book);
	}
	
	//Update book
	public boolean updateBook(Book book) {
		return bookDAO.updateBook(book);
	}
	
	//Delete book
	public boolean deleteBook(int bookId) {
		return bookDAO.deleteBook(bookId);
	}
	
	//Get all books
	public List<Book> getAllBooks() {
		return bookDAO.getAllBooks();
	}
	
	//Get book by title or author
	public List<Book> searchBooks(String keyword) {
		return bookDAO.searchBooks(keyword);
	}
	
}

