package console.operations;

import console.Operation;
import console.OperationType;
import org.springframework.stereotype.Component;
import service.AccountService;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class AccountWithdraw implements Operation {
    private final AccountService accountService;

    public AccountWithdraw(AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public OperationType getType() {
        return OperationType.valueOf("ACCOUNT_WITHDRAW");
    }
    @Override
    public void execute(Scanner sc) {
        try {
            System.out.println("Enter account ID to withdraw from:");
            String input = sc.nextLine().trim();
            Long accountId = Long.parseLong(input);

            System.out.println("Enter amount to withdraw:");
            input = sc.nextLine().trim();
            BigDecimal amount = new BigDecimal(input);

            accountService.withdraw(accountId, amount);
            System.out.println("The funds have been successfully withdrawn");
        } catch (IllegalStateException e) {
            System.out.println("There are not enough funds in the account.");
        }
    }
}
