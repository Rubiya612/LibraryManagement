package dao;

import model.Book;
import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BookDAO {
	
	//ADD
	public boolean addBook(Book book) {
		String sql = "INSERT into LMS_BOOKS (TITLE, AUTHOR, PUBLISHER_ID, ISBN, PUB_YEAR) VALUES (?, ?, ?, ?, ?)";
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
		String sql = "UPDATE LMS_BOOKS SET TITLE = ?, AUTHOR = ?, PUBLISHER_ID = ?, ISBN = ?, PUB_YEAR = ? WHERE BOOK_ID = ?";
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
		String sql = "DELETE FROM LMS_BOOKS WHERE BOOK_ID = ?";
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
		String sql = "SELECT * FROM LMS_BOOKS where BOOK_ID = ?";
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
		String sql = "SELECT * FROM LMS_BOOKS";
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
	//Rubiya
	public static int getTotalBooks() {
	    String sql = "SELECT COUNT(*) FROM LMS_BOOKS";
	    try (Connection conn = DBConnection.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        if (rs.next()) return rs.getInt(1);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}

	
	//Search by title or author
	public List<Book> searchBooks(String keyword) {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM LMS_BOOKS WHERE TITLE LIKE ? OR AUTHOR LIKE ?";
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
		String sql = "SELECT COUNT(*) AS TOTAL FROM LMS_BOOKS";
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
