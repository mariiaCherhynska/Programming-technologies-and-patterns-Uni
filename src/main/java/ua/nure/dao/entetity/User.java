package ua.nure.dao.entetity;

public class User {
    private int id;
    private String userName;
    private Role role;

    public User(int id, String userName, Role role) {
        this.id = id;
        this.userName = userName;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", role=" + role +
                '}';
    }

    public static class Builder{
        private int id;
        private String userName;
        private Role role;


        public User.Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }
        public User.Builder setId(int id) {
            this.id = id;
            return this;
        }
        public User.Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        public User build(){
            return new User(id, userName, role);
        }
    }
}


