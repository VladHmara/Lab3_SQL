public class ChatUser {
    public  int Id;
    public int ChatId;
    public int UserId;

    public ChatUser(int id, int chatId, int userId){
        Id = id;
        ChatId = chatId;
        UserId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if(Id == ((ChatUser)obj).Id && ChatId == ((ChatUser)obj).ChatId && UserId == ((ChatUser)obj).UserId)
            return true;
        else
            return false;
    }
}
