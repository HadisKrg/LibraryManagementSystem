import model.Book;
import model.Member;
import Exception.MemberNotFoundException;
import Exception.BookNotFoundException;
import repository.BookRepository;
import repository.MemberRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static void main() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        BookRepository bookRepository = new BookRepository();
        MemberRepository memberRepository = new MemberRepository();

        Book book1 = new Book("The Midnight Library", "Matt Haig", 390, 8);
        Book book2 = new Book("Atomic Habits", "James Clear", 420, 10);
        Book book3 = new Book("Animal Farm", "George Orwell", 280, 7);
        Book book4 = new Book("1984", "George Orwell", 350, 6);
        Book book5 = new Book("The Alchemist", "Paulo Coelho", 320, 9);

        bookRepository.insertBook(book1);
        bookRepository.insertBook(book2);
        bookRepository.insertBook(book3);
        bookRepository.insertBook(book4);
        bookRepository.insertBook(book5);

        Member member1=new Member("Hadis","01234");
        Member member2=new Member("Sara","98745");
        Member member3=new Member("Mahshid","025896");

        memberRepository.registerMember(member1);
        memberRepository.registerMember(member2);
        memberRepository.registerMember(member3);


        while (true) {
            System.out.println(
                    "1. Update the price of a book.  \n" +
                            "2. Delete a book.  \n" +
                            "3. Delete a member \n" +
                            "4.Exit\n" +
                            "Choice:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Enter id:");
                    int id = scanner.nextInt();
                    System.out.println("Enter price:");
                    double price = scanner.nextDouble();
                    try {
                        bookRepository.updatePrice(id, price);
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 2: {
                    System.out.println("Enter id:");
                    int id = scanner.nextInt();
                    try{
                    bookRepository.delete(id);
                    }catch (BookNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 3: {
                    System.out.println("Enter id:");
                    int id = scanner.nextInt();
                    try {
                        memberRepository.deleteMember(id);
                    }catch (MemberNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}