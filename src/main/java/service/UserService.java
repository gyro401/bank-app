package service;

import model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final Map<Long, User> users = new ConcurrentHashMap<>();


    public User userCreate(String login) throws IllegalArgumentException{
        Set<String> setOfLogins
                = users
                .values()
                .stream()
                .map(user -> user.getLogin())
                .collect(Collectors.toSet());

        if (setOfLogins.contains(login)) {

            throw new IllegalArgumentException();
        }

        Long newId = idGenerator.getAndIncrement();
        User user = new User(newId, login);
        users.put(newId, user);
        return user;
    }

    public Map<Long, User> getUsers() {
        return users;
    }

    public User findById(Long id) {
        return users.get(id) ;
    }

    public List<User> showAllUsers() {
        return users.values().stream().toList();
    }

    public void removeAccount(Long userId, Long accountId) {
        users.get(userId).getAccountList().removeIf(account -> account.getId().equals(accountId));
    }

}
