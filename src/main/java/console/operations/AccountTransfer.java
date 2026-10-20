package console.operations;

import console.Operation;
import console.OperationType;
import org.springframework.stereotype.Component;
import service.AccountService;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class AccountTransfer implements Operation {
    private final AccountService accountService;

    public AccountTransfer(AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public OperationType getType() {
        return OperationType.valueOf("ACCOUNT_TRANSFER");
    }

    @Override
    public void execute(Scanner sc) {
        try {
            System.out.println("Enter source account ID:");
            String input = sc.nextLine().trim();
            Long sourceAccountId = Long.parseLong(input);

            System.out.println("Enter target account ID:");
            input = sc.nextLine().trim();
            Long targetAccountId = Long.parseLong(input);

            System.out.println("Enter amount to transfer:");
            input = sc.nextLine().trim();
            BigDecimal amount = new BigDecimal(input);

            accountService.transfer(sourceAccountId, targetAccountId, amount);
            System.out.printf("Amount %f%n transferred from account ID %d%n to account ID %d%n.\n",
                    amount, sourceAccountId, targetAccountId);
        } catch (IllegalStateException e) {
            System.out.println("");
        }
    }
}
