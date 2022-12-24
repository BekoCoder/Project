package jk.uz.pdp.service;

import jk.uz.pdp.model.Message;
import jk.uz.pdp.model.User;
import jk.uz.pdp.model.dto.MessageResponseDto;

public class MessageService {
    static Message[] messageList = new Message[100];
    private int index = 0;

    /**
     *  bunda messagelarni saqlab borish methodi qilingan.
     *  yuboruvchi, qabul qiluvchi va xabar kirib keladi.
     *  yuborgan va qabul qilingan xabarlarni bitta massivga saqlab boradi.
     **/
    public String add(User sender, User receiver, String messageText) {
        Message message = new Message(sender.getId(), receiver.getId(), messageText, System.currentTimeMillis());
        messageList[index++] = message;
        return "message sent to " + receiver.getUsername() + "!";
    }



    public MessageResponseDto[] getMessageList(User currentUser, User interCurrentUser) {
        Message[] helper = new Message[40];
        int index = 0;
        for (Message message : messageList) {
            if (message != null) {
                if (message.getSender().equals(currentUser.getId())
                    && message.getReceiver().equals(interCurrentUser.getId())){
                    helper[index ++] = message;
                    message.setRead(true);
                }
                if (message.getReceiver().equals(currentUser.getId())
                    && message.getSender().equals(interCurrentUser.getId())){
                    helper[index ++] = message;
                    message.setRead(true);
                }
            }
        }
        return this.buildMessage(helper,currentUser);
    }

    /**
     *  DTO data transfer object -> bu ma'lumotlarni bir-biriga bog'lashda ishlatiladi.
     *  boshida bitta massiv olamiz. foreach da messageList bo'yicha aylanib chiqamiz. message nullga teng bo'masa
     *  yuboruvchini teng bo'sa currentUser ni Id siga ya'ni bunda o'zimiz xabar yuboruvchi bo'lamiz va sms konsolda o'ng tarafda chiqadi.
     *  aks holda qabul qiluvchi bo'lamiz va kelgan sms chap tarafda chiqadi. Bu methodda kelgan smslarni qaysi tarafdan chiqish qilingan.
     * */
    private MessageResponseDto[] buildMessage(Message[] messageList, User currentUser){
        MessageResponseDto[] messageResponseDtoList = new MessageResponseDto[100];
        int index = 0;
        for (Message message: messageList) {
            if (message != null){
                if (message.getSender().equals(currentUser.getId())){
                    messageResponseDtoList[index ++] = new MessageResponseDto(message.getSender(), message.getMessage(),true);
                }else {
                    messageResponseDtoList[index ++] = new MessageResponseDto(message.getReceiver(), message.getMessage(),false);
                }
            }
        }
        return messageResponseDtoList;
    }
 // bunda kelgan bildirishnomalarni sanab ekranga chiqarish qilingan. bundayam foreach bo'yicha aylanib chiqamiz va message nullga teng bo'masa cnt ni bittaga oshiramiz va shuni qaytarib qo'yamiz.
    public int getNotificationCount(User currentUser){
        int cnt = 0;
        for (Message message: messageList) {
            if (message != null){
                if (message.getReceiver().equals(currentUser.getId()) && !message.isRead())
                {
                    cnt ++;
                }
            }
        }
        return cnt;
    }

    /**
     * bunda bildirishnoma funksiyasi qilingan. Boshida notificationUser degan massiv olinadi. foreach bo'yicha messageList bo'yicha aylanib chiqamiz.
     * agar message nullga teng bo'masa va qabul qiluvchi teng bo'lsa current userni id siga massivga qo'shib ketish kerak. Va oxirida shu massivni qaytarib qo'yish kerak.
     **/
    public User[] getNotificationUserList(User currentUser, int cntOfNotificationUser){
        User[] notificationUser = new User[cntOfNotificationUser];
        int index = 0;
        for (Message message: messageList) {
            if (message != null){
                if (message.getReceiver().equals(currentUser.getId()) && !message.isRead())
                {
                    notificationUser[index ++] = UserService.getUser(message.getSender());
                }
            }
        }
        return notificationUser;
    }
}
