package maktabSharifHw.util;


import maktabSharifHw.Exception.GenerallyNotFoundException;
import maktabSharifHw.model.Person;
import maktabSharifHw.model.Role;
import maktabSharifHw.repository.Impl.MemberRepositoryImpl;
import maktabSharifHw.repository.Impl.SubjectRepositoryImpl;
import maktabSharifHw.repository.MemberRepository;
import maktabSharifHw.repository.SubjectRepository;
import maktabSharifHw.service.Impl.MemberServiceImpl;
import maktabSharifHw.service.Impl.SubjectServiceImpl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import static javafx.application.Platform.exit;

public class Menu {
    static EntityManagerProvider entityManagerProvider = new EntityManagerProvider();
    private final static MemberRepository memberRepository = new MemberRepositoryImpl(entityManagerProvider);
    private final static MemberServiceImpl memberService = new MemberServiceImpl(memberRepository);

    private final static SubjectRepository subjectRepository = new SubjectRepositoryImpl(entityManagerProvider);
    private final static SubjectServiceImpl subjectService = new  SubjectServiceImpl(subjectRepository);
    static Scanner scan = new Scanner(System.in);
    static boolean start = true;

    public static void mainMenu() {
        while (start) {
            start = logInMenu();
        }

    }

    public static boolean logInMenu() {
        System.out.println("\u001B[33m"+"***********welcome**********"+"\u001B[0m");
        int choose = utility.getIntInput("1-> login" + "\n2-> exit");
        while (true) {
            if (choose == 2) {
                return false;
            }
            if (choose == 1) {
                System.out.println("\u001B[33m"+"\n******* login menu *******\n"+"\u001B[0m");
                System.out.println("\u001B[33m"+"please enter your username ->"+"\u001B[0m");
                String username = scan.nextLine();
                System.out.println("\u001B[33m"+"please enter your password ->"+"\u001B[0m");
                String password = scan.nextLine();
                   Role role = memberService.login(username, password);
                   switch (role){
                       case MEMBER: memberMenu();
                       case LIBRARIAN: adminMenu();
                   }


            }
        }
    }

    private static void memberMenu() {
        while (true) {
            System.out.println("\u001B[31m"+"**************member menu**************"+"\u001B[0m");
            System.out.println("1-> see all subjects");
            System.out.println("2 -> see subjects that have at least one book");
            System.out.println("3 -> exit to login page");
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
                logInMenu();
            }
        }
    }


    private static void adminMenu() {
        while (true) {
            System.out.println("\u001B[31m"+"***************admin menu************"+"\u001B[0m");
            System.out.println("1 -> see all subject information");
            System.out.println("2 -> see subjects that have at least one book");
            System.out.println("3 -> delete subject");
            System.out.println("4 -> create subject");
            System.out.println("5 -> update subject");
            System.out.println("6 -> create book");
            System.out.println("7 -> update book");
            System.out.println("8 -> see  all book information");
            System.out.println("9 -> set subject for book");
            System.out.println("10 -> create member");
            System.out.println("11 -> update member");
            System.out.println("12 -> delete member");
            System.out.println("13 -> see  all member information");
            System.out.println("14 -> exit");
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

//            } else if (choose3 == 6) {
//                System.out.println("what is teacher id");
//                int teacherId = scan.nextInt();
//                scan.nextLine();
//                System.out.println("what is national code");
//                String nationalCode = scan.nextLine();
//                System.out.println("what is new firstname");
//                String firstname = scan.nextLine();
//                System.out.println("what is new last name");
//                String lastname = scan.nextLine();
//                System.out.println("new date born Enter a date (dd/mm/yyyy)");
//                String dob = scan.nextLine();
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                try {
//                    Date dateDob = sdf.parse(dob);
//
//                    System.out.println("what is new course id (it can be null)");
//                    Long courseId = scan.nextLong();
//                    SaveTeacherRequest saveTeacherRequest = new SaveTeacherRequest(teacherId, nationalCode, firstname, lastname, dateDob, courseId);
//                    teacherService.saveAndUpdate(saveTeacherRequest);
//                } catch (ParseException e) {
//                    throw new RuntimeException(e);
//                }
//
//            } else if (choose3 == 7) {
//                System.out.println("what is teacher id");
//                int id = scan.nextInt();
//                scan.nextLine();
//                teacherService.delete(id);
//
//            } else if (choose3 == 8) {
//                teacherService.PrintCountTeacher();
//                teacherService.printAllTeacher();
//
//            } else if (choose3 == 9) {
//                System.out.println("what is national code");
//                String nationalCode = scan.nextLine();
//                System.out.println("what is firstname");
//                String firstname = scan.nextLine();
//                System.out.println("what is last name");
//                String lastname = scan.nextLine();
//                System.out.println("date born Enter a date (dd/mm/yyyy)");
//                String dob = scan.nextLine();
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                try {
//                    Date dateDob = sdf.parse(dob);
//                    System.out.println("what is student gpu");
//                    double gpu = scan.nextDouble();
//                    SaveStudentRequest saveStudentRequest = new SaveStudentRequest(nationalCode, firstname, lastname, dateDob, gpu);
//                    studentService.saveAndUpdate(saveStudentRequest);
//                } catch (ParseException e) {
//                    throw new RuntimeException(e);
//                }
//
//            } else if (choose3 == 10) {
//                System.out.println("what is student id");
//                int id = scan.nextInt();
//                scan.nextLine();
//                System.out.println("what is new national code");
//                String nationalCode = scan.nextLine();
//                System.out.println("what is new firstname");
//                String firstname = scan.nextLine();
//                System.out.println("what is new last name");
//                String lastname = scan.nextLine();
//                System.out.println("new date born Enter a date (dd/mm/yyyy)");
//                String dob = scan.nextLine();
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                try {
//                    Date dateDob = sdf.parse(dob);
//                    System.out.println("what is new student gpu");
//                    double gpu = scan.nextDouble();
//                    SaveStudentRequest saveStudentRequest = new SaveStudentRequest(id, nationalCode, firstname, lastname, dateDob, gpu);
//                    studentService.saveAndUpdate(saveStudentRequest);
//                } catch (ParseException e) {
//                    throw new RuntimeException(e);
//                }
//
//            } else if (choose3 == 11) {
//                System.out.println("what is student id");
//                int id = scan.nextInt();
//                scan.nextLine();
//                studentService.delete(id);
//            } else if (choose3 == 12) {
//                studentService.printCountOfStudent();
//                studentService.printAllStudentList();
//
//            } else if (choose3 == 13) {
//                System.out.println("what is teacher id");
//                int teacherId = scan.nextInt();
//                scan.nextLine();
//                System.out.println("what is teacher national code");
//                String nationalCode = scan.nextLine();
//                System.out.println("what is course id");
//                int courseId = scan.nextInt();
//                scan.nextLine();
//                System.out.println("what is grade");
//                Long gpu = scan.nextLong();
//
//                System.out.println(" exam date Enter a date (dd/mm/yyyy)");
//                String dob = scan.nextLine();
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                try {
//                    Date dateDob = sdf.parse(dob);
//                    SaveExamRequest saveExamRequest = new SaveExamRequest(teacherId, nationalCode, courseId, gpu, dateDob);
//                    examService.saveAndUpdate(saveExamRequest);
//                } catch (ParseException e) {
//                    throw new RuntimeException(e);
//                }
            } else if (choose3 == 14) {
                logInMenu();
            }

        }
    }
}

