import java.util.ArrayList;
import java.util.List;

class User {
    private final String name;
    private final List<Book> borrowedBooks = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    @Override
    public String toString() {
        return name + " (livros emprestados: " + borrowedBooks.size() + ")";
    }
}