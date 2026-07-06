package repository;

import model.Book;
import Exception.BookNotFoundException;
import util.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 Insert a new book.
 Find a book by id.
 Update a book's price.
 Delete a book.
 */
public class BookRepository {
    public Book insertBook(Book book) throws SQLException {
        String insert="insert into book (title, author, price, stock) VALUES (?,?,?,?);";
        try ( Connection connection=DatabaseConfig.getConnection();
              PreparedStatement statement= connection.prepareStatement(insert)){
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setDouble(3,book.getPrice());
            statement.setInt(4,book.getStock());
            statement.executeUpdate();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return book;
    }

    public Book findById(int id) throws SQLException {
        String findQuery="select * from book where id=?;";
        try ( Connection connection=DatabaseConfig.getConnection();
                PreparedStatement statement=connection.prepareStatement(findQuery)){
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock")
                );
                return book;
            }else {
                throw new BookNotFoundException("Book not found");
            }

        }catch (SQLException e){
            throw e;
        }
    }

    public void updatePrice(int id,double newPrice) throws SQLException {
        String updatePrice="update book set price=? where id=?;";
        try (Connection connection=DatabaseConfig.getConnection();
             PreparedStatement statement=connection.prepareStatement(updatePrice)){
            findById(id);
            statement.setDouble(1,newPrice);
            statement.setInt(2,id);
            int result=statement.executeUpdate();
            if (result>0){
                System.out.println("Price updated");
            }
        }catch (SQLException e){
           throw e;
        }
    }

    public void delete(int id) throws SQLException {
        String delete="delete from book where id=?;";
        try (Connection connection=DatabaseConfig.getConnection();
             PreparedStatement statement= connection.prepareStatement(delete);){
            findById(id);
            statement.setInt(1,id);
            int result=statement.executeUpdate();
            if (result > 0){
                System.out.println("Book deleted");}
        }catch (SQLException e){
            throw e;
        }
    }
}
