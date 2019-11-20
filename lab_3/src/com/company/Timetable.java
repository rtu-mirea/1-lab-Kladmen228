package com.company;
import java.util.Scanner;
import java.util.ArrayList;

public class Timetable
{
    private static ArrayList<Professor> users;
    private static ArrayList<Request> requests;
    private static Pairs[][] pairs;
    private static Professor currentUser;
    private int rooms;
    private int groups;

    public static void main(String[] args) {
        Timetable timetbl = new Timetable();
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                Timetable.pairs[i][j] = new Pairs();
            }
        }
        Scanner in = new Scanner(System.in);
        int opt = -1;
        while (opt != 0) {
            opt = -1;
            System.out.println("\nСистема автоматического составления расписания");
            System.out.println("[0] - Выход из программы");
            System.out.println("[1] - Вход");
            System.out.println("[2] - Регистрация");
            System.out.println("[3] - Меню администратора");
            try {
                opt = in.nextInt();
            }
            catch (IllegalArgumentException e) {
                System.out.println("Некорректный ввод");
                continue;
            }
            switch (opt) {
                case 1: {
                    try{
                        if (timetbl.logUser()) {
                            while (opt != 0) {
                                System.out.println("Здравствуйте, " + Timetable.currentUser.getName() + "!");
                                System.out.println("[0] - Выход в главное меню");
                                System.out.println("[1] - Подача заявки");
                                System.out.println("[2] - Ваше расписание");
                                Scanner in4 = new Scanner(System.in);
                                opt = in4.nextInt();
                                switch (opt) {
                                    case 1: {
                                        Scanner in5 = new Scanner(System.in);
                                        System.out.print("Введите название дисциплины: ");
                                        String disc = in5.nextLine();
                                        System.out.print("Введите группу: ");
                                        int group = in5.nextInt();
                                        System.out.print("Введите количество пар: ");
                                        int pairs = in5.nextInt();
                                        timetbl.addRequest(disc, group, pairs);
                                        continue;
                                    }
                                    case 2: {
                                        timetbl.printPairs(Timetable.currentUser);
                                    }
                                    case 0: {
                                        continue;
                                    }
                                    default: {
                                        System.out.println("Некорректный ввод");
                                    }
                                }
                            }
                            opt = -1;
                            continue;
                        }
                    }
                    catch (NullPointerException e){
                        System.out.println("Данный пользователь не зарегистрирован");
                        continue;
                    }
                }
                case 2: {
                    Scanner in3 = new Scanner(System.in);
                    System.out.print("Введите Ваше имя: ");
                    String name = in3.nextLine();
                    System.out.print("Введите Ваш логин: ");
                    String login = in3.nextLine();
                    System.out.print("Введите Ваш пароль: ");
                    String pass = in3.nextLine();
                    timetbl.addUser(name, login, pass);
                    continue;
                }
                case 3: {
                    Scanner in2 = new Scanner(System.in);
                    System.out.print("Введите логин: ");
                    String login = in2.next();
                    System.out.print("Введите пароль: ");
                    String pass = in2.next();
                    if (login.equals("admin") && pass.equals("admin")) {
                        while (opt != 0) {
                            System.out.println("Здравствуйте, администратор!");
                            System.out.println("[0] - Выход в главное меню");
                            System.out.println("[1] - Указать количество аудиторий");
                            System.out.println("[2] - Указать количество групп");
                            opt = in2.nextInt();
                            switch (opt) {
                                case 1: {
                                    System.out.println("Введите количество аудиторий: ");
                                    int rooms = in2.nextInt();
                                    timetbl.setRooms(rooms);
                                    continue;
                                }
                                case 2: {
                                    System.out.println("Введите количество групп: ");
                                    int groups = in2.nextInt();
                                    timetbl.setGroups(groups);
                                    continue;
                                }
                                case 3: {
                                    timetbl.processRequests();
                                    System.out.println("Расписание составлено!");
                                }
                            }
                        }
                    }
                    opt = -1;
                    continue;
                }
                case 0: {
                    System.out.print("Работа завершена");
                    break;
                }
                default: {
                    System.out.println("Некорректный ввод");
                    opt = -1;
                }
            }
        }
    }

    private boolean logUser() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите логин: ");
        String login = in.nextLine();
        System.out.print("Введите пароль: ");
        String password = in.nextLine();
        Timetable.currentUser = this.findUser(login, password);
        return Timetable.currentUser.enter(login, password);
    }

    private void addUser(String name, String login, String password) {
        if (this.findUser(login, password) != null) {
            System.out.println("Пользователь уже зарегистрирован");
            return;
        }
        Timetable.currentUser = new Professor(name, login, password);
        Timetable.users.add(Timetable.currentUser);
    }

    private void addRequest(String disc, int group, int pairs) {
        Request r = new Request(Timetable.currentUser, disc, group, pairs);
        Timetable.requests.add(r);
    }

    private void processRequests() {
        for (Request r : Timetable.requests) {
            int number = r.getPairs();
            for (int i = 0; i < 6; ++i) {
                for (int j = 0; j < 6; ++j) {
                    int room = 1;
                    if (Timetable.pairs[i][j].getRooms() < this.rooms && number > 0) {
                        while (Timetable.pairs[i][j].checkRoom(room)) {
                            ++room;
                        }
                        if (!Timetable.pairs[i][j].checkGroup(r.getGroup()) && !Timetable.pairs[i][j].checkProfessor(r.getRequester())) {
                            Timetable.pairs[i][j].setPair(r.getRequester(), r.getDiscipline(), room, r.getGroup(), j, i);
                            Timetable.pairs[i][j].Group_(r.getGroup());
                            Timetable.pairs[i][j].Prof_(r.getRequester());
                            Timetable.pairs[i][j].Room_(room);
                            --number;
                        }
                    }
                }
            }
        }
    }

    private void printPairs(Professor user) {
        System.out.println("Ваше расписание:");
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                Timetable.pairs[i][j].printPair(user.getName());
            }
        }
    }

    private Professor findUser(String login, String password) {
        for (Professor i : Timetable.users) {
            if (i.getLogin().equals(login) && i.getPassword().equals(password)) {
                return i;
            }
        }
        return null;
    }

    private void setRooms(int rooms) {
        this.rooms = rooms;
    }

    private void setGroups(int groups) {
        this.groups = groups;
    }

    static {
        Timetable.users = new ArrayList<>();
        Timetable.requests = new ArrayList<>();
        Timetable.pairs = new Pairs[6][6];
    }
}