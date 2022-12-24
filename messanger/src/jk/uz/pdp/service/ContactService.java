package jk.uz.pdp.service;

import jk.uz.pdp.model.Contact;
import jk.uz.pdp.model.ContactListType;
import jk.uz.pdp.model.User;

public class ContactService {
    private static Contact[] contactList = new Contact[100];
    private int index = 0;



    /**
    bu funksiyada contact qo'shish qilingan contactUser va current user kirib keladi. agar oldin qo'shilgan bo'sa false qaytaradi
     qo'shilmagan bo'sa contactListga qo'shib ketadi.
    **/
    public boolean addContact(User currentUser, User contactUser) {
        boolean isTrue = checkContact(currentUser, contactUser);
        if (isTrue) {
            return false;
        }
        contactList[index++] = new Contact(currentUser.getId(), contactUser.getId());
        return true;
    }

    /**
     * bu funksida esa contaclarni ko'rish qilingan. Bunda current user va contactListType degan o'zgarmas o'zgaruvchan kirib kealdi.
     * Boshida foreach bo'yicha contactlistni aylanib chiqamiz. agar contact nullga teng bo'masa ifda tekshiramiz cntni sanatib ketamiz.
     * chunki bitta massivga saqlab ketish kerak lekin qanchalik massiv olishni bilmaganimiz uchun sanatib ketamiz. shunchalik massiv yaratamiz.
     **/
    public User[] getContactList(User currentUser, ContactListType contactListType) {
        int cnt = 0;
        for (Contact contact : contactList) {
            if (contact != null) {
                if (contactListType.equals(ContactListType.CONTACT_LIST)) {
                    if (contact.getUserId().equals(currentUser.getId())) {
                        cnt++;
                    }
                } else if (contactListType.equals(ContactListType.SUBSCRIBE_LIST)) {
                    if (contact.getContactId().equals(currentUser.getId())) {
                        cnt++;
                    }
                }
            }
        }
        return this.fillUserFromContactList(new User[cnt], currentUser, contactListType);
    }

    private User[] fillUserFromContactList(User[] users, User currentUser, ContactListType contactListType) {
        int index = 0;
        for (Contact contact : contactList) {
            if (contact != null) {
                if (contactListType.equals(ContactListType.CONTACT_LIST)) {
                    if (contact.getUserId().equals(currentUser.getId())) {
                        users[index ++] = UserService.getUser(contact.getContactId());
                    }
                } else if (contactListType.equals(ContactListType.SUBSCRIBE_LIST)) {
                    if (contact.getContactId().equals(currentUser.getId())) {
                        users[index ++] = UserService.getUser(contact.getUserId());
                    }
                }
            }
        }
        return users;
    }
/**
   Bu funksiyada contactlarni tekshiramiz. Bunga currentUser va contactUserlar kirib keladi. foreach bo'yicha contactList bo'yicha aylanib chiqamiz.
    agar contact nullga teng bo'masa true bo'ladi chunki bu contact yo'q bo'ladi.


   **/
    private boolean checkContact(User currentUser, User contactUser) {
        for (Contact contact : contactList) {
            if (contact != null) {
                if (contact.getUserId().equals(currentUser.getId()) && contact.getContactId().equals(contactUser.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

}
