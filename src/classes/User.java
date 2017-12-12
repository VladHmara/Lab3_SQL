package classes;

public class User {
    public int Id;
    public String FirstName;
    public String LastdName;

    public User(int id, String firstName, String lastdName){
        Id = id;
        FirstName = firstName;
        LastdName = lastdName;
    }

    @Override
    public boolean equals(Object obj) {
            return (Id == ((User)obj).Id && FirstName.equals(((User)obj).FirstName) && LastdName.equals (((User)obj).LastdName));
    }
}