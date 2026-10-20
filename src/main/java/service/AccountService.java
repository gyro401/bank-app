package service;

import model.Account;
import model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AccountService {
    private final UserService userService;
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final Map<Long, Account> accounts = new ConcurrentHashMap<>();

    @Value("${account.default-amount}")
    private BigDecimal amount;

    @Value("${account.transfer-commission}")
    private BigDecimal transferCommission;

    public AccountService (UserService userService) {
        this.userService = userService;
    }


    public Account createAccount(Long userId) throws IllegalArgumentException{
        if (!userService.getUsers().containsKey(userId)) throw new IllegalArgumentException();

        User user = userService.getUsers().get(userId);
        Long newId = idGenerator.getAndIncrement();
        Account account = new Account(newId, userId, amount);

        user.getAccountList().add(account);
        accounts.put(newId, account);
        return account;
    }

    public void deposit(Long accountId, BigDecimal amount) throws IllegalArgumentException {
        if (!accounts.containsKey(accountId)) throw new IllegalArgumentException();

        Account account = accounts.get(accountId);
        account.setAmount(account.getAmount().add(amount));
    }

    public void withdraw(Long accountId, BigDecimal amount) throws IllegalArgumentException, IllegalStateException{
        if (!accounts.containsKey(accountId)) throw new IllegalArgumentException();

        Account account = accounts.get(accountId);
        if (account.getAmount().subtract(amount).compareTo(BigDecimal.ZERO) < 0) throw new IllegalStateException();

        account.setAmount(account.getAmount().subtract(amount));
    }


    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
         {
            boolean fromIsTo = accounts.get(fromAccountId).getUserId()
                    .equals(accounts.get(toAccountId).getUserId());

            if (fromIsTo) {
                withdraw(fromAccountId, amount);
                deposit(toAccountId, amount);
            } else {
                BigDecimal commission
                        = amount.multiply(transferCommission).divide(BigDecimal.valueOf(100),
                        4, RoundingMode.HALF_UP);

                withdraw(fromAccountId, amount.add(commission));
                deposit(toAccountId, amount);
            }
        }
    }

    public void closeAccount(Long accountId) throws IllegalStateException, IllegalArgumentException {
        if (!accounts.containsKey(accountId)) throw new IllegalArgumentException();

        Long userId = accounts.get(accountId).getUserId();
        User user = userService.getUsers().get(userId);
        if (user.getAccountList().size() == 1) throw new IllegalStateException();


        user.getAccountList().removeIf(account -> account.getId().equals(accountId));
        accounts.remove(accountId);

    }
}
