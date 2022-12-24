package jk.uz.pdp;

import jk.uz.pdp.model.ContactListType;
import jk.uz.pdp.model.User;
import jk.uz.pdp.model.dto.MessageResponseDto;
import jk.uz.pdp.service.ContactService;
import jk.uz.pdp.service.MessageService;
import jk.uz.pdp.service.UserService;

import java.util.Scanner;

public class Main {

    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerStr = new Scanner(System.in);

    static UserService userService = new UserService();
    static ContactService contactService = new ContactService();
    static MessageService messageService = new MessageService();


    public static void main(String[] args) {

        int stepCode = 100;


        while (stepCode != 0) {
            System.out.println("1.Add User, 2.Login, 0.Exit");
            stepCode = scannerInt.nextInt();
            User currentUser = null;
            switch (stepCode) {
                case 1 -> {
                    System.out.println("enter name");
                    String name = scannerStr.nextLine();

                    System.out.println("enter username");
                    String username = scannerStr.nextLine();

                    System.out.println("enter password");
                    String password = scannerStr.nextLine();

                    System.out.println(userService.addUser(name, username, password));
                }
                case 2 -> {
                    System.out.println("enter username");
                    String username = scannerStr.nextLine();

                    System.out.println("enter password");
                    String password = scannerStr.nextLine();

                    currentUser = userService.login(username, password);

                    if (currentUser == null) {
                        System.out.println("please register !!");
                    } else {

                        int stepCode2 = 100;
                        while (stepCode2 != 0) {
                            int notificationCount = messageService.getNotificationCount(currentUser);
                            System.out.println("1.Add Contact, 2.Contact List, 3.Subscribe List, 4.Add Message 5.Get Message List, 6. Notification( " + notificationCount + " ) 0.Exit");
                            stepCode2 = scannerInt.nextInt();

                            switch (stepCode2) {
                                case 1 -> {
                                    System.out.println("enter username");
                                    String username2 = scannerStr.nextLine();
                                    User contactUser = userService.getCheckUser(username2);
                                    if (contactUser == null) {
                                        System.out.println("there is no user");
                                    } else {
                                        System.out.println(contactService.addContact(currentUser, contactUser));
                                    }
                                }
                                case 2 -> {
                                    User[] contactList = contactService.getContactList(currentUser, ContactListType.CONTACT_LIST);
                                    int cnt = 0;
                                    for (User user : contactList) {
                                        System.out.println("=============== " + ++cnt + " - user =========== ");
                                        System.out.println("id: " + user.getId());
                                        System.out.println("name: " + user.getName());
                                        System.out.println("username: " + user.getUsername());
                                        System.out.println("password: " + user.getPassword());
                                    }
                                }
                                case 3 -> {
                                    User[] subscribeList = contactService.getContactList(currentUser, ContactListType.SUBSCRIBE_LIST);
                                    int cnt = 0;
                                    for (User user : subscribeList) {
                                        System.out.println("=============== " + ++cnt + " - user =========== ");
                                        System.out.println("id: " + user.getId());
                                        System.out.println("name: " + user.getName());
                                        System.out.println("username: " + user.getUsername());
                                        System.out.println("password: " + user.getPassword());
                                    }
                                }
                                case 4 -> {
                                    System.out.println("enter username");
                                    String username2 = scannerStr.nextLine();
                                    User receiver = userService.getCheckUser(username2);
                                    System.out.println("enter text");
                                    String text = scannerStr.nextLine();
                                    System.out.println(messageService.add(currentUser, receiver, text));
                                }
                                case 5 -> {
                                    printMessageList(currentUser);
                                }
                                case 6 -> {
                                    User[] notificationUserList = messageService.getNotificationUserList(currentUser, notificationCount);
                                    int cnt = 0;
                                    for (User user : notificationUserList) {
                                        System.out.println(++cnt + "." + user.getUsername());
                                    }
                                    printMessageList(currentUser);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private static void printMessageList(User currentUser){
        System.out.println("enter username");
        String username2 = scannerStr.nextLine();
        User interCurrentUser = userService.getCheckUser(username2);
        MessageResponseDto[] messageList = messageService.getMessageList(currentUser, interCurrentUser);

        for (MessageResponseDto message : messageList) {
            if (message != null) {
                if (message.isCurrentUserIsSender()) {
                    System.out.println("\t\t\t" + message.getMessageText());
                } else {
                    System.out.println(message.getMessageText());
                }
            }
        }
    }
}
