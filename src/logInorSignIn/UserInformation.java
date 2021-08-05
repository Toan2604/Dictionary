/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logInorSignIn;

/**
 *
 * @author Thanh Diệp
 */
public class UserInformation {
    private String user, pass;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public UserInformation(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public UserInformation() { }

    @Override
    public String toString() {
        return "user = " + user + ", pass = " + pass+"\n";
    }
    
}
