package console.operations;

import console.Operation;
import console.OperationType;
import org.springframework.stereotype.Component;
import service.AccountService;

import java.util.Scanner;

@Component
public class AccountClose implements Operation {
    private final AccountService accountService;

    public AccountClose(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public OperationType getType() {
        return OperationType.valueOf("ACCOUNT_CLOSE");
    }

    @Override
    public void execute(Scanner sc) {
        try {
            System.out.println("Enter account ID to close:");

            String input = sc.nextLine().trim();
            Long accountId = Long.parseLong(input);

            accountService.closeAccount(accountId);
            System.out.printf("Account with ID %d has been closed.\n", accountId);
        } catch (IllegalStateException e) {
            System.out.println("This is the user's only account. It cannot be closed.");
        } catch (IllegalArgumentException e) {
            System.out.println("This account ID is not exist.");
        }
    }
}
