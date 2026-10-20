package console.operations;

import console.Operation;
import console.OperationType;
import org.springframework.stereotype.Component;
import service.AccountService;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class AccountDeposit implements Operation {
    private final AccountService accountService;

    public AccountDeposit(AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public OperationType getType() {
        return OperationType.valueOf("ACCOUNT_DEPOSIT");
    }

    @Override
    public void execute(Scanner sc)  {
        try {
            System.out.println("Enter account ID:");

            String input = sc.nextLine().trim();
            Long accountId = Long.parseLong(input);

            System.out.println("Enter amount to deposit:");
            input = sc.nextLine().trim();
            BigDecimal amount = new BigDecimal(input);

            accountService.deposit(accountId, amount);

            System.out.printf("Amount %f deposited to account ID: %d", amount, accountId);
        } catch (IllegalArgumentException e) {
            System.out.println("");
        }
    }
}
