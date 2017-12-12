package Classes;

public class UserHash {
    public String Hash;
    public int UserId;

    public UserHash(String hash, int userId){
        Hash = hash;
        UserId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if(Hash.equals(((UserHash)obj).Hash)&& UserId == ((UserHash)obj).UserId)
            return true;
        else
            return false;
    }
}