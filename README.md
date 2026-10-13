# 📚 Sistema de Biblioteca

Sistema de gerenciamento de biblioteca desenvolvido em **Java**, com foco em **orientação a objetos**. Controla o acervo de livros, os leitores, os empréstimos com cálculo de multa e as reservas de livros, diferenciando os perfis de funcionário (atendente e bibliotecário).

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)

## 🎯 Objetivo

Praticar modelagem e programação orientada a objetos (herança, encapsulamento e associações) aplicadas a um problema real: o controle de empréstimos, devoluções, multas e reservas de uma biblioteca.

## ✨ Funcionalidades

- Cadastro e atualização de livros e leitores
- Empréstimo e devolução de livros, com registro do funcionário responsável
- Renovação de empréstimos
- Cálculo e pagamento de multa por atraso
- Reserva de livros que já estão emprestados
- Consulta dos livros emprestados e reservados por leitor
- Dois perfis de funcionário: Atendente (`Clerk`) e Bibliotecário (`Librarian`)

## 🧩 Modelagem

![Diagrama de classes](diagrama-classes.png)

| Classe | Responsabilidade |
|---|---|
| `Person` *(abstrata)* | Dados em comum: id, nome, senha, endereço e telefone |
| `Borrower` | Leitor. Herda de `Person` e gerencia seus empréstimos e reservas |
| `Staff` | Funcionário. Herda de `Person` e possui salário |
| `Clerk` | Atendente. Herda de `Staff` e possui número da mesa |
| `Librarian` | Bibliotecário. Herda de `Staff` e possui número da sala |
| `Book` | Livro: título, autor, assunto e situação (emprestado ou não) |
| `Loan` | Empréstimo: datas, multa e renovação |
| `HoldRequest` | Reserva de um livro feita por um leitor |

**Relacionamentos**

- `Borrower` e `Staff` são `Person`; `Clerk` e `Librarian` são `Staff`
- Um `Borrower` possui vários `Loan` e várias `HoldRequest`
- Um `Book` pode ter várias `HoldRequest`, e cada `Loan` é feito sobre um `Book`
- Cada `Loan` registra o funcionário que emprestou (*issuer*) e o que recebeu a devolução (*receiver*)

## ▶️ Como executar

**Pré-requisitos:** Java 17+ e Git.

```bash
git clone https://github.com/kakams21/Sistema-de-Biblioteca.git
cd Sistema-de-Biblioteca
javac -d out $(find src -name "*.java")
java -cp out Main
```

## 💡 Aprendizados

- Modelagem com UML e implementação fiel ao diagrama de classes
- Herança, classes abstratas e encapsulamento em Java
- Associações entre objetos (1 para N) e regras de negócio como multa e reserva
- Versionamento com Git e GitHub

## 🚀 Próximos passos

- [ ] Persistência dos dados em banco SQL
- [ ] Interface web ou gráfica
- [ ] Senhas criptografadas
- [ ] Testes automatizados

## 👨‍💻 Autor

**Kauan**, estudante de Engenharia de Software

[![GitHub](https://img.shields.io/badge/GitHub-kakams21-181717?style=flat&logo=github)](https://github.com/kakams21)
