package items;

public class Chat {
    public int Id;
    public String Name;

    public Chat(int id, String name){
        Id = id;
        Name= name;
    }

    @Override
    public boolean equals(Object obj) {
            return (Id == ((Chat)obj).Id && Name.equals(((Chat)obj).Name));
    }
}
