package dao;

import model.Book;
import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookDAO {
	
	//ADD
	public boolean addBook(Book book) {
		String sql = "Insert into LMS_BOOKS (TITLE, AUTHOR, PUBLISHER_ID, ISBN, PUB_YEAR) values (?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setInt(3, book.getPublisherId());
			stmt.setString(4, book.getIsbn());
			stmt.setString(5, book.getPubYear());
			
			return stmt.executeUpdate() > 0;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	
	//UPDATE
	public boolean updateBook(Book book) {
		String sql = "Update LMS_BOOKS set TITLE = ?, AUTHOR = ?, PUBLISHER_ID = ?, ISBN = ?, PUB_YEAR = ? where BOOK_ID = ?";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setInt(3, book.getPublisherId());
			stmt.setString(4, book.getIsbn());
			stmt.setString(5, book.getPubYear());
			stmt.setInt(6, book.getBookId());
			
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//DELETE
	public boolean deleteBook(int bookId) {
		String sql = "Delete from LMS_BOOKS where BOOK_ID = ?";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, bookId);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//GET by ID
	public Book getBookById(int bookId) {
		String sql = "Select * from LMS_BOOKS where BOOK_ID = ?";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, bookId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Book(rs.getInt("BOOK_ID"), rs.getString("TITLE"), rs.getString("AUTHOR"),
						rs.getInt("PUBLISHER_ID"), rs.getString("ISBN"), rs.getString("PUB_YEAR"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//GET all
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		String sql = "Select * from LMS_BOOKS";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				books.add(new Book(rs.getInt("BOOK_ID"), rs.getString("TITLE"), rs.getString("AUTHOR"),
						rs.getInt("PUBLISHER_ID"), rs.getString("ISBN"), rs.getString("PUB_YEAR")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	//Search by title or author
	public List<Book> searchBooks(String keyword) {
		List<Book> books = new ArrayList<>();
		String sql = "Select * from LMS_BOOKS where TITLE like ? or AUTHOR like ?";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				books.add(new Book(rs.getInt("BOOK_ID"), rs.getString("TITLE"), rs.getString("AUTHOR"),
						rs.getInt("PUBLISHER_ID"), rs.getString("ISBN"), rs.getString("PUB_YEAR")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	//Count total books
	public int countTotalBooks() {
		String sql = "Select count(*) as total from LMS_BOOKS";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
