import org.junit.*;
import org.junit.runners.MethodSorters;
import liquibase.exception.LiquibaseException;
import java.util.concurrent.locks.Lock;

import Classes.*;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import static junit.framework.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {

    @BeforeClass
    public static void beforeClass()throws SQLException{
        MyConnection.connect();
        Statement stmt = MyConnection.connection.createStatement();
        String sql = "DELETE FROM public.databasechangelog; DROP TABLE user_copy;";
        stmt.execute(sql);
    }
    @AfterClass
    public static void afterClass()throws SQLException{
        MyConnection.disconnect();
    }

    @Test
    public void v1_createTablesTest() throws SQLException, LiquibaseException, ClassNotFoundException{
        List<String> tables = new ArrayList<String>(Arrays.asList( "chat", "chat_user", "message", "user", "user_hash"));
        Main.UpdateChangelog("1.0");
        DatabaseMetaData md = MyConnection.connection.getMetaData();
        ResultSet rs = md.getTables (null, "public", null, null);
        while (rs.next ()) {
            if (tables.contains(rs.getString(3)))
            tables.remove(rs.getString (3));
        }
        rs.close ();
        assertEquals(new ArrayList<String>(),tables);
    }

    @Test
    public void v7_dropTablesTest() throws SQLException, LiquibaseException, ClassNotFoundException{
        List<String> tables = new ArrayList<String>(Arrays.asList( "chat", "chat_user", "message", "user", "user_hash"));
        Main.UpdateChangelog("2.0");
        DatabaseMetaData md = MyConnection.connection.getMetaData();
        ResultSet rs = md.getTables (null, "public", null, null);
        boolean flag = true;
        while (rs.next ())
            if(tables.contains(rs.getString (3))) {
                flag = false;
                break;
            }
        rs.close ();
        assertEquals(true,flag);
    }
    @Test
    public void v7_dropUserHashTest() throws SQLException, LiquibaseException, ClassNotFoundException{
        Main.UpdateChangelog("1.0");
        Main.UpdateChangelog("2.1");
        DatabaseMetaData md = MyConnection.connection.getMetaData();
        ResultSet rs = md.getTables (null, "public", null, null);
        boolean flag = true;
        while (rs.next ())
            if("user_hash".equals(rs.getString (3))) {
                flag = false;
                break;
            }
        rs.close ();
        assertEquals(true,flag);
    }
    @Test
    public void v7_dropChatUserTest() throws SQLException, LiquibaseException, ClassNotFoundException{
        Main.UpdateChangelog("2.2");
        DatabaseMetaData md = MyConnection.connection.getMetaData();
        ResultSet rs = md.getTables (null, "public", null, null);
        boolean flag = true;
        while (rs.next ())
            if("chat_user".equals(rs.getString (3))) {
                flag = false;
                break;
            }
        rs.close ();
        assertEquals(true,flag);
    }
    @Test
    public void v7_dropMessageTest() throws SQLException, LiquibaseException, ClassNotFoundException{
        Main.UpdateChangelog("2.3");
        DatabaseMetaData md = MyConnection.connection.getMetaData();
        ResultSet rs = md.getTables (null, "public", null, null);
        boolean flag = true;
        while (rs.next ())
            if("message".equals(rs.getString (3))) {
                flag = false;
                break;
            }
        rs.close ();
        assertEquals(true,flag);
    }
    @Test
    public void v7_dropUserTest() throws SQLException, LiquibaseException, ClassNotFoundException{
        Main.UpdateChangelog("2.4");
        DatabaseMetaData md = MyConnection.connection.getMetaData();
        ResultSet rs = md.getTables (null, "public", null, null);
        boolean flag = true;
        while (rs.next ())
            if("user".equals(rs.getString (3))) {
                flag = false;
                break;
            }
        rs.close ();
        assertEquals(true,flag);
    }
    @Test
    public void v7_dropChatTest() throws SQLException, LiquibaseException, ClassNotFoundException{
        Main.UpdateChangelog("2.5");
        DatabaseMetaData md = MyConnection.connection.getMetaData();
        ResultSet rs = md.getTables (null, "public", null, null);
        boolean flag = true;
        while (rs.next ())
            if("chat".equals(rs.getString (3))) {
                flag = false;
                break;
            }
        rs.close ();
        assertEquals(true,flag);
    }

    @Test
    public void v2_setDataTest1() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.UpdateChangelog("3.0");
        Data data = new Data();
        Chat chat = new Chat(1,"Chat1");
        assertEquals(true,data.ChatList.get(0).equals(chat));
    }
    @Test
    public void v2_setDataTest2() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.UpdateChangelog("3.0");
        Data data = new Data();
        User user = new User(1,"Alex","Pupkin");
        assertEquals(true,data.UserList.get(0).equals(user));
    }
    @Test
    public void v2_setDataTest3() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.UpdateChangelog("3.0");
        Data data = new Data();
        UserHash userHash = new UserHash("asddvfgj95q",1);
        System.out.println(data.UserHashList.get(0).Hash + " " + data.UserHashList.get(0).UserId);
        assertEquals(true,data.UserHashList.get(0).equals(userHash));
      }
    @Test
    public void v2_setDataTest4() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.UpdateChangelog("3.0");
        Data data = new Data();
        ChatUser chatUser = new ChatUser(1,1,1);
        assertEquals(true,data.ChatUserList.get(0).equals(chatUser));
      }
    @Test
    public void v2_setRowTest() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.UpdateChangelog("3.1");
        Data data = new Data();
        User user = new User(5,"Alex","Bearer");
        assertEquals(true,data.UserList.get(4).equals(user));
    }
    @Test
    public void v2_updateRowTest() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.UpdateChangelog("3.2");
        Data data = new Data();
        UserHash userHash = new UserHash("gfhrt41saaa",3);
        assertEquals(true,data.UserHashList.get(3).equals(userHash));
    }

    @Test
    public void v4_RemoveAllRowsTest1() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.UpdateChangelog("5.0");
        Data data = new Data();
        assertEquals(0,data.ChatList.size());
    }
    @Test
    public void v4_RemoveAllRowsTest2() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.UpdateChangelog("5.0");
        Data data = new Data();
        assertEquals(0,data.ChatUserList.size());
    }
    @Test
    public void v4_RemoveAllRowsTest3() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.UpdateChangelog("5.0");
        Data data = new Data();
        assertEquals(0,data.MessageList.size());
    }
    @Test
    public void v4_RemoveAllRowsTest4() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.UpdateChangelog("5.0");
        Data data = new Data();
        assertEquals(0,data.UserList.size());
    }
    @Test
    public void v4_RemoveAllRowsTest5() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.UpdateChangelog("5.0");
        Data data = new Data();
        assertEquals(0,data.UserHashList.size());
    }

    @Test
    public void v5_RenameColumnTest() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.UpdateChangelog("6.0");
        Data data = new Data();
        Statement stmt = MyConnection.connection.createStatement();
        ResultSet set = stmt.executeQuery("SELECT * FROM message");
        ResultSetMetaData md = set.getMetaData();
        boolean flag = false;
        for (int i = 1; i <= md.getColumnCount(); i++) {
            if("content".equals(md.getColumnName(i))) {
                flag = false;
                break;
            }
            if("text".equals(md.getColumnName(i)))
                flag = true;
        }
        assertEquals(true,flag);
    }
    @Test
    public void v3_RemoveColumnTest() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.UpdateChangelog("6.1");
        Data data = new Data();
        Statement stmt = MyConnection.connection.createStatement();
        ResultSet set = stmt.executeQuery("SELECT * FROM chat");
        ResultSetMetaData md = set.getMetaData();
        boolean flag = true;
        for (int i = 1; i <= md.getColumnCount(); i++) {
            if("name".equals(md.getColumnName(i))) {
                flag = false;
                break;
            }
        }
        assertEquals(true,flag);
    }
    @Test
    public void v6_RemoveTableTest() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.UpdateChangelog("6.2");
        DatabaseMetaData md = MyConnection.connection.getMetaData();
        ResultSet rs = md.getTables (null, "public", null, null);
        boolean flag = false;
        while (rs.next ()) {
            if("user".equals(rs.getString(3))) {
                flag = false;
                break;
            }
            if ("user_copy".equals(rs.getString(3)))
                flag = true;
        }
        rs.close ();
        assertEquals(true,flag);
    }

}
