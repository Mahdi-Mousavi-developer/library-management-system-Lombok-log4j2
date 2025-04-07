package maktabSharifHw.ui;


import maktabSharifHw.exception.GenerallyNotFoundException;

import maktabSharifHw.model.Role;
import maktabSharifHw.repository.BookRepository;
import maktabSharifHw.repository.Impl.BookRepositoryImpl;
import maktabSharifHw.repository.Impl.MemberRepositoryImpl;
import maktabSharifHw.repository.Impl.SubjectRepositoryImpl;
import maktabSharifHw.repository.MemberRepository;
import maktabSharifHw.repository.SubjectRepository;
import maktabSharifHw.service.BookService;
import maktabSharifHw.service.Impl.BookServiceImpl;
import maktabSharifHw.service.Impl.MemberServiceImpl;
import maktabSharifHw.service.Impl.SubjectServiceImpl;
import maktabSharifHw.util.EntityManagerProvider;
import maktabSharifHw.util.utility;

import java.util.Scanner;



public class Menu {
    static EntityManagerProvider entityManagerProvider = new EntityManagerProvider();
    private final static MemberRepository memberRepository = new MemberRepositoryImpl(entityManagerProvider);
    private final static MemberServiceImpl memberService = new MemberServiceImpl(memberRepository);
    private final static BookRepository bookRepository = new BookRepositoryImpl(entityManagerProvider);
    private final static BookService bookService = new BookServiceImpl(bookRepository);
    private final static SubjectRepository subjectRepository = new SubjectRepositoryImpl(entityManagerProvider);
    private final static SubjectServiceImpl subjectService = new SubjectServiceImpl(subjectRepository);
    static Scanner scan = new Scanner(System.in);
    static boolean start = true;

    public static void mainMenu() {
        while (start) {
            start = logInMenu();
        }

    }

    public static boolean logInMenu() {
        System.out.println("\u001B[33m" + "***********welcome**********" + "\u001B[0m");
        int choose = utility.getIntInput("1-> login" + "\n2-> exit");
        while (true) {
            if (choose == 2) {
                return false;
            }
            if (choose == 1) {
                System.out.println("\u001B[33m" + "\n******* login menu *******\n" + "\u001B[0m");
                System.out.println("\u001B[33m" + "please enter your username ->" + "\u001B[0m");
                String username = scan.nextLine();
                System.out.println("\u001B[33m" + "please enter your password ->" + "\u001B[0m");
                String password = scan.nextLine();
                Role role = memberService.login(username, password);
                switch (role) {
                    case MEMBER:
                        memberMenu();
                    case LIBRARIAN:
                        adminMenu();
                }


            }
        }
    }

    private static void memberMenu() {
        while (true) {
            System.out.println("\u001B[31m" + "**************member menu**************" + "\u001B[0m");
            System.out.println("1-> see all subjects");
            System.out.println("2 -> see subjects that have at least one book");
            System.out.println("3 -> see all book information");
            System.out.println("4 -> exit to login page");
            int choose2 = scan.nextInt();
            scan.nextLine();
            if (choose2 == 1) {
                try {
                    subjectService.printAllSubjects();
                } catch (GenerallyNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if (choose2 == 2) {
                subjectService.showSubjectsWhoHaveOneBook();
            } else if (choose2 == 3) {
                try {
                    bookService.printAllBook();
                } catch (GenerallyNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if (choose2==4) {
                logInMenu();
            }
        }
    }


    private static void adminMenu() {
        while (true) {
            System.out.println("\u001B[31m" + "***************admin menu************" + "\u001B[0m");
            System.out.println("1 -> see all subject information");
            System.out.println("2 -> see subjects that have at least one book");
            System.out.println("3 -> delete the subject with its book");
            System.out.println("4 -> create subject");
            System.out.println("5 -> update subject");
            System.out.println("6 -> see all book information");
            System.out.println("7 -> delete book");
            System.out.println("8 -> create book");
            System.out.println("9 -> update book");
            System.out.println("10 -> set subject for book");
            System.out.println("11 -> exit");
            int choose3 = scan.nextInt();
            scan.nextLine();
            if (choose3 == 1) {
                try {
                    subjectService.printAllSubjects();
                } catch (GenerallyNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if (choose3 == 2) {
                subjectService.showSubjectsWhoHaveOneBook();
            } else if (choose3 == 3) {
                System.out.println("what is subject id");
                long id = scan.nextLong();
                scan.nextLine();
                try {
                    subjectService.deleteSubject(id);
                } catch (GenerallyNotFoundException e) {
                    throw new RuntimeException(e);
                }

            } else if (choose3 == 4) {
                System.out.println("what is subject title");
                String title = scan.nextLine();
                System.out.println("what is subject description");
                String description = scan.nextLine();
                subjectService.addSubject(title, description);
            } else if (choose3 == 5) {
                System.out.println("what is subject id");
                long id = scan.nextLong();
                scan.nextLine();
                System.out.println("what is subject title");
                String title = scan.nextLine();
                System.out.println("what is subject description");
                String description = scan.nextLine();
                subjectService.updateSubject(id, title, description);

            } else if (choose3 == 6) {
                try {
                    bookService.printAllBook();
                } catch (GenerallyNotFoundException e) {
                    throw new RuntimeException(e);
                }

            } else if (choose3 == 7) {
                System.out.println("what is Book id");
                long id = scan.nextLong();
                scan.nextLine();
                try {
                    bookService.deleteBook(id);
                } catch (GenerallyNotFoundException e) {
                    throw new RuntimeException(e);
                }

            } else if (choose3 == 8) {
                System.out.println("what is circulation (enter number)");
                long circulation = scan.nextLong();
                scan.nextLine();
                System.out.println("what is author");
                String author = scan.nextLine();
                System.out.println("what is isbn");
                String isbn = scan.nextLine();
                System.out.println("what is publisher");
                String publisher = scan.nextLine();
                System.out.println("what is title");
                String title = scan.nextLine();
                try {
                    bookService.addBook(circulation,author,isbn,publisher,title);
                } catch (GenerallyNotFoundException e) {
                    throw new RuntimeException(e);
                }


            } else if (choose3 == 9) {
                System.out.println("what is book id");
                long id = scan.nextLong();
                scan.nextLine();
                System.out.println("what is circulation (enter number)");
                long circulation = scan.nextLong();
                scan.nextLine();
                System.out.println("what is author");
                String author = scan.nextLine();
                System.out.println("what is isbn");
                String isbn = scan.nextLine();
                System.out.println("what is publisher");
                String publisher = scan.nextLine();
                System.out.println("what is title");
                String title = scan.nextLine();
                bookService.updateBook(id,circulation,author,isbn,publisher,title);
            } else if (choose3 == 10) {
                System.out.println("what is Subject id");
                long subjectId = scan.nextLong();
                scan.nextLine();
                System.out.println("what is book id");
                long bookId = scan.nextLong();
                scan.nextLine();
                bookService.setSubjectToBook(subjectId,bookId);
            } else if (choose3 == 11) {
                logInMenu();
            }

        }
    }
}

