import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;

class Book {
    private String titulo;

    public String gettitulo() {
        return titulo;
    }
}

class user {
    private String name;

    public String getName() {
        return name;
    }
}

public class library {
    private ArrayList<user> user;
    private ArrayList<Book> books;

    public library() {
        this.user = new ArrayList<user>();
        this.books = new ArrayList<Book>();
    }

    public ArrayList<user> getUser() {
        return user;
    }

    public void setUser(ArrayList<user> user) {
        this.user = user;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void addbook(Book book) {
        books.add(book);
        System.out.println("Livro adicionado: " + book.gettitulo());
    }

    public void addUser(user user) {
        this.user.add(user);
        System.out.println("Usuário adicionado: " + user.getName());
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.gettitulo().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // Book not found
    }
    public UserDefinedFileAttributeView findUserByName(String name) {
        for (user user : user) {
            if (user.getName().equalsIgnoreCase(name)) {
                return null; // User found
            }
        }
        return null; // User not found
    }
    
}

