package test.java;

import org.junit.*;
import org.junit.runners.MethodSorters;
import liquibase.exception.LiquibaseException;

import classes.*;
import main.*;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.*;

import static junit.framework.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {

    @BeforeClass
    public static void beforeClass()throws SQLException{
        MyConnection.connect();
        Statement stmt = MyConnection.connection.createStatement();
        String sql = "DELETE FROM public.databasechangelog; DROP TABLE IF EXISTS user_copy;";
        stmt.execute(sql);
    }
    @AfterClass
    public static void afterClass()throws SQLException{
        MyConnection.disconnect();
    }

    @Test
    public void v1createTablesTest() throws SQLException, LiquibaseException, ClassNotFoundException{
        List<String> tables = new ArrayList<String>(Arrays.asList( "chat", "chat_user", "message", "user", "user_hash"));
        Main.updateChangelog("1.0");
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
    public void v7dropTablesTest() throws SQLException, LiquibaseException, ClassNotFoundException{
        List<String> tables = new ArrayList<String>(Arrays.asList( "chat", "chat_user", "message", "user", "user_hash"));
        Main.updateChangelog("2.0");
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
    public void v7dropUserHashTest() throws SQLException, LiquibaseException, ClassNotFoundException{
        Main.updateChangelog("2.1");
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
    public void v7dropChatUserTest() throws SQLException, LiquibaseException, ClassNotFoundException{
        Main.updateChangelog("2.2");
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
    public void v7dropMessageTest() throws SQLException, LiquibaseException, ClassNotFoundException{
        Main.updateChangelog("2.3");
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
    public void v7dropUserTest() throws SQLException, LiquibaseException, ClassNotFoundException{
        Main.updateChangelog("2.4");
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
    public void v7dropChatTest() throws SQLException, LiquibaseException, ClassNotFoundException{
        Main.updateChangelog("2.5");
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
    public void v2setDataTest1() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.updateChangelog("3.0");
        Data d = new Data();
        Chat chat = new Chat(1,"Chat1");
        assertEquals(true,d.getChatList().get(0).equals(chat));
    }
    @Test
    public void v2setDataTest2() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.updateChangelog("3.0");
        Data d = new Data();
        User user = new User(1,"Alex","Pupkin");
        assertEquals(true,d.getUserList().get(0).equals(user));
    }
    @Test
    public void v2setDataTest3() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.updateChangelog("3.0");
        Data d = new Data();
        UserHash userHash = new UserHash("asddvfgj95q",1);
        System.out.println(d.getUserHashList().get(0).Hash + " " + d.getUserHashList().get(0).UserId);
        assertEquals(true,d.getUserHashList().get(0).equals(userHash));
      }
    @Test
    public void v2setDataTest4() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.updateChangelog("3.0");
        Data d = new Data();
        ChatUser chatUser = new ChatUser(1,1,1);
        assertEquals(true,d.getChatUserList().get(0).equals(chatUser));
      }
    @Test
    public void v2setRowTest() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.updateChangelog("3.1");
        Data d = new Data();
        User user = new User(5,"Alex","Bearer");
        assertEquals(true,d.getUserList().get(4).equals(user));
    }
    @Test
    public void v2updateRowTest() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.updateChangelog("3.2");
        Data d = new Data();
        UserHash userHash = new UserHash("gfhrt41saaa",3);
        assertEquals(true,d.getUserHashList().get(3).equals(userHash));
    }

    @Test
    public void v4RemoveAllRowsTest1() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.updateChangelog("5.0");
        Data d = new Data();
        assertEquals(0,d.getChatList().size());
    }
    @Test
    public void v4RemoveAllRowsTest2() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.updateChangelog("5.0");
        Data d = new Data();
        assertEquals(0,d.getChatUserList().size());
    }
    @Test
    public void v4RemoveAllRowsTest3() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.updateChangelog("5.0");
        Data d = new Data();
        assertEquals(0,d.getMessageList().size());
    }
    @Test
    public void v4RemoveAllRowsTest4() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.updateChangelog("5.0");
        Data d = new Data();
        assertEquals(0,d.getUserList().size());
    }
    @Test
    public void v4RemoveAllRowsTest5() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.updateChangelog("5.0");
        Data d = new Data();
        assertEquals(0,d.getUserHashList().size());
    }

    @Test
    public void v5RenameColumnTest() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.updateChangelog("6.0");
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
    public void v3RemoveColumnTest() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.updateChangelog("6.1");
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
    public void v6RemoveTableTest() throws SQLException, LiquibaseException, ClassNotFoundException{//переписать
        Main.updateChangelog("6.2");
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
