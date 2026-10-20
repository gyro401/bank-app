package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class User {
    private final Long id;
    private String login;
    private final List<Account> accountList = new ArrayList<>();

    public User(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public Long getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setLogin(String login) { this.login = login; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", login='" + login + "', accountList=" + accountList + "}";
    }
}
