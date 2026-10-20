package console.operations;

import console.Operation;
import console.OperationType;
import model.User;
import org.springframework.stereotype.Component;
import service.UserService;

import java.util.List;
import java.util.Scanner;

@Component
public class ShowAllUsers implements Operation {
    private final UserService userService;

    public ShowAllUsers(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OperationType getType() {
        return OperationType.valueOf("SHOW_ALL_USERS");
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println("List of all users:");

        List<User> users = userService.showAllUsers();
        users.forEach(System.out::println);
    }
}
