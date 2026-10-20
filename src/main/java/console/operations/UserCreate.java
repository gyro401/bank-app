package console.operations;

import console.Operation;
import console.OperationType;
import model.User;
import org.springframework.stereotype.Component;
import service.AccountService;
import service.UserService;

import java.util.Scanner;

@Component
public class UserCreate implements Operation {
    private final AccountService accountService;
    private final UserService userService;

    public UserCreate(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public OperationType getType() {
        return OperationType.valueOf("USER_CREATE");
    }

    @Override
    public void execute(Scanner sc) {
        try {
            while (true) {
                System.out.println("Enter login for new user:");

                String login = sc.nextLine();

                User user = userService.userCreate(login);
                accountService.createAccount(user.getId());

                System.out.println("User created: " + user.toString());
                break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("User with this login already exists. Please try again.");
        }
    }
}
