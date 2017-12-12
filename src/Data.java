import Classes.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Data {
    List<Chat> ChatList = new ArrayList<Chat>();
    List<ChatUser> ChatUserList = new ArrayList<ChatUser>();
    List<Message> MessageList = new ArrayList<Message>();
    List<User> UserList = new ArrayList<User>();
    List<UserHash> UserHashList = new ArrayList<UserHash>();

    public Data() throws SQLException{
        GetChat();
        GetChatUser();
        GetMessage();
        GetUser();
        GetUserHash();
    }

    private void GetChat() throws SQLException{
        Statement stmt = MyConnection.connection.createStatement();
        String sql = "SELECT * FROM Chat";
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()) {
            Chat chat = new Chat(result.getInt(1), result.getString(2));
            ChatList.add(chat);
        }
    }
    private void GetChatUser() throws SQLException{
        Statement stmt = MyConnection.connection.createStatement();
        String sql = "SELECT * FROM chat_user";
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()) {
            ChatUser chatUser = new ChatUser(result.getInt(1), result.getInt(2),result.getInt(3));
            ChatUserList.add(chatUser);
        }
    }
    private void GetMessage() throws SQLException{
        Statement stmt = MyConnection.connection.createStatement();
        String sql = "SELECT * FROM message";
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()) {
            Message message = new Message(result.getInt(1), result.getInt(2),result.getInt(3),result.getString(4));
            MessageList.add(message);
        }
    }
    private void GetUser() throws SQLException{
        Statement stmt = MyConnection.connection.createStatement();
        String sql = "SELECT * FROM \"user\"";
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()) {
            User user = new User(result.getInt(1), result.getString(2), result.getString(3));
            UserList.add(user);
        }
    }
    private void GetUserHash() throws SQLException{
        Statement stmt = MyConnection.connection.createStatement();
        String sql = "SELECT * FROM user_hash";
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()) {
            UserHash userHash = new UserHash(result.getString(1), result.getInt(2));
            UserHashList.add(userHash);
        }
    }
}
