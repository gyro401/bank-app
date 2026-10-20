package console.operations;

import console.Operation;
import console.OperationType;
import org.springframework.stereotype.Component;
import service.AccountService;

import java.util.Scanner;

@Component
public class AccountCreate implements Operation {
    private final AccountService accountService;

    public AccountCreate(AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public OperationType getType() {
        return OperationType.valueOf("ACCOUNT_CREATE");
    }

    @Override
    public void execute(Scanner sc) {
        try {
            System.out.println("Enter the user id for which to create an account:");

            String input = sc.nextLine().trim();
            Long userId = Long.parseLong(input);

            Long accountId = accountService.createAccount(userId).getId();

            System.out.printf("New account created with ID: %d for user: %s \n", accountId, userId);
        } catch (IllegalArgumentException e) {
            System.out.println("This user ID is not exist.");
        }
    }
}
