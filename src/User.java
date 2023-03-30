/**
 * @author Trevor Hartman
 * @author Rachelle Iloff
 * created 3/29/2023
 * @since version 1.0
 */
//## PART 1 - Create User Class
//
//* Create a Class called User that has 2 String instance variables:
//```java
//private String username;
//private String passHash;
//```
//* Create a User constructor that has 2 parameters **username** and **passHash** and assigns these parameters to its instance variables.
//* Create 2 methods getUsername and getPassHash that return the instance variables respectively.

public class User {
    private String userName;
    private String passHash;
    public User(String userName, String passHash) {
        this.userName = userName;
        this.passHash = passHash;
    }
    public String getUsername() {
        return this.userName;
    }
    public String getPassHash() {
        return this.passHash;
    }
}
