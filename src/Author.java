import java.util.Date;

public class Author {
    private int authorId;
    private String firstName;
    private String lastName;
    private String bio;
    private Date birthday;


    Author(int authorId, String firstName, String lastName, String bio){
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

    Author(int authorId, String firstName, String lastName, String bio, Date birthday){
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.birthday = birthday;
    }

    public int getAuthorId(){
        return authorId;
    }

    public void setAuthorId(int authorId){
        this.authorId = authorId;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getBio(){
        return bio;
    }

    public void setBio(String bio){
        this.bio = bio;
    }

    public Date getBirthday(){
        return birthday;
    }

    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }
}
