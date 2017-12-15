package items;


public class User {
    public int Id;
    public String FirstName;
    public String LastName;

    public User(int id, String firstName, String lastName){
        Id = id;
        FirstName = firstName;
        LastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
            return (Id == ((User)obj).Id && FirstName.equals(((User)obj).FirstName) && LastName.equals (((User)obj).LastName));
    }
}
