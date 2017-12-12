public class Message {
    public int Id;
    public int FromUserId;
    public int ToChatId;
    public String Content;

    public Message(int id, int fromUserId, int toChatId, String content){
        Id = id;
        FromUserId = fromUserId;
        ToChatId = toChatId;
        Content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if(Id == ((Message)obj).Id && FromUserId == ((Message)obj).FromUserId && ToChatId == ((Message)obj).ToChatId && Content.equals(((Message)obj).Content))
            return true;
        else
            return false;
    }
}
