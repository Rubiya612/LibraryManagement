package model;

public class Book {

	private int bookId;
	private String title;
	private String author;
	private int publisherId;
	private String isbn;
	private String pubYear;
	
	public Book(int bookId, String title, String author, int publisherId, String isbn, String pubYear) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publisherId = publisherId;
		this.isbn = isbn;
		this.pubYear = pubYear;
	}
	
	public Book() {
	}
	
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPubYear() {
		return pubYear;
	}
	public void setPubYear(String pubYear) {
		this.pubYear = pubYear;
	}
	
}
