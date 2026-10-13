import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    private static String normalize(String value) {
        return value == null ? "" : value.trim();
    }

    static class Book {
        private final String title;
        private final String author;
        private boolean available;

        public Book(String title, String author) {
            this.title = normalize(title);
            this.author = normalize(author);
            this.available = true;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        @Override
        public String toString() {
            return title + " - " + author + (available ? " (Disponível)" : " (Emprestado)");
        }
    }

    static class User {
        private final String name;
        private final List<Book> borrowedBooks;

        public User(String name) {
            this.name = normalize(name);
            this.borrowedBooks = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public List<Book> getBorrowedBooks() {
            return borrowedBooks;
        }

        public boolean hasBook(String title) {
            String normalizedTitle = normalize(title);
            for (Book book : borrowedBooks) {
                if (book.getTitle().equalsIgnoreCase(normalizedTitle)) {
                    return true;
                }
            }
            return false;
        }

        public void borrowBook(Book book) {
            borrowedBooks.add(book);
        }

        public void returnBook(Book book) {
            borrowedBooks.removeIf(b -> b.getTitle().equalsIgnoreCase(book.getTitle()));
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class Library {
        private final List<Book> books;
        private final List<User> users;

        public Library() {
            books = new ArrayList<>();
            users = new ArrayList<>();
        }

        public boolean addBook(Book book) {
            if (book == null || book.getTitle().isEmpty()) {
                return false;
            }
            if (findBookByTitle(book.getTitle()) != null) {
                return false;
            }
            books.add(book);
            return true;
        }

        public boolean addUser(User user) {
            if (user == null || user.getName().isEmpty()) {
                return false;
            }
            if (findUserByName(user.getName()) != null) {
                return false;
            }
            users.add(user);
            return true;
        }

        public void listBooks() {
            if (books.isEmpty()) {
                System.out.println("Nenhum livro cadastrado.");
                return;
            }
            for (Book book : books) {
                System.out.println(book);
            }
        }

        public void listUsers() {
            if (users.isEmpty()) {
                System.out.println("Nenhum usuário cadastrado.");
                return;
            }
            for (User user : users) {
                System.out.println(user.getName() + " - Livros emprestados: " + user.getBorrowedBooks().size());
            }
        }

        public Book findBookByTitle(String title) {
            String normalizedTitle = normalize(title);
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(normalizedTitle)) {
                    return book;
                }
            }
            return null;
        }

        public User findUserByName(String name) {
            String normalizedName = normalize(name);
            for (User user : users) {
                if (user.getName().equalsIgnoreCase(normalizedName)) {
                    return user;
                }
            }
            return null;
        }

        public void lendBook(String userName, String bookTitle) {
            User user = findUserByName(userName);
            Book book = findBookByTitle(bookTitle);

            if (user == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            if (book == null) {
                System.out.println("Livro não encontrado.");
                return;
            }

            if (!book.isAvailable()) {
                System.out.println(user.hasBook(bookTitle)
                        ? "Este usuário já está com este livro."
                        : "Livro indisponível para empréstimo.");
                return;
            }

            user.borrowBook(book);
            book.setAvailable(false);
            System.out.println("Livro emprestado com sucesso.");
        }

        public void returnBook(String userName, String bookTitle) {
            User user = findUserByName(userName);
            Book book = findBookByTitle(bookTitle);

            if (user == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            if (book == null) {
                System.out.println("Livro não encontrado.");
                return;
            }

            if (!user.hasBook(bookTitle)) {
                System.out.println("Este usuário não está com este livro.");
                return;
            }

            user.returnBook(book);
            book.setAvailable(true);
            System.out.println("Livro devolvido com sucesso.");
        }
    }

    // Lê uma linha; se a entrada acabou (Ctrl+D / Ctrl+Z), devolve vazio sem lançar exceção
    private static String ask(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.hasNextLine() ? scanner.nextLine().trim() : "";
    }

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.addUser(new User("Alice"));
        library.addUser(new User("Bob"));

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Listar livros");
            System.out.println("2. Listar usuários");
            System.out.println("3. Emprestar livro");
            System.out.println("4. Devolver livro");
            System.out.println("5. Cadastrar livro");
            System.out.println("6. Cadastrar usuário");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            if (!scanner.hasNextLine()) {
                break;
            }

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número de 1 a 7.");
                continue;
            }

            switch (choice) {
                case 1 -> library.listBooks();
                case 2 -> library.listUsers();
                case 3 -> {
                    String userName = ask(scanner, "Digite o nome do usuário: ");
                    String bookTitle = ask(scanner, "Digite o título do livro: ");
                    library.lendBook(userName, bookTitle);
                }
                case 4 -> {
                    String userName = ask(scanner, "Digite o nome do usuário: ");
                    String bookTitle = ask(scanner, "Digite o título do livro: ");
                    library.returnBook(userName, bookTitle);
                }
                case 5 -> {
                    String title = ask(scanner, "Título do livro: ");
                    String author = ask(scanner, "Autor: ");
                    if (library.addBook(new Book(title, author))) {
                        System.out.println("Livro cadastrado com sucesso.");
                    } else {
                        System.out.println("Não foi possível cadastrar (título vazio ou já existente).");
                    }
                }
                case 6 -> {
                    String name = ask(scanner, "Nome do usuário: ");
                    if (library.addUser(new User(name))) {
                        System.out.println("Usuário cadastrado com sucesso.");
                    } else {
                        System.out.println("Não foi possível cadastrar (nome vazio ou já existente).");
                    }
                }
                case 7 -> {
                    System.out.println("Saindo...");
                    running = false;
                }
                default -> System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}