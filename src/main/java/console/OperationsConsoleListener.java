package console;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class OperationsConsoleListener implements Runnable{
    private final Map<OperationType, Operation> operations = new HashMap<>();

    public OperationsConsoleListener(List<Operation> operationList) {
        for (Operation op : operationList) {
            if (op.getType() != null)
                operations.put(op.getType(), op);
        }
    }


    void init() {
        new Thread(this).start();
    }


    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("""
                        Please enter one of operation type:
                        -ACCOUNT_CREATE
                        -SHOW_ALL_USERS
                        -ACCOUNT_CLOSE
                        -ACCOUNT_WITHDRAW
                        -ACCOUNT_DEPOSIT
                        -ACCOUNT_TRANSFER
                        -USER_CREATE""");

                String line = sc.nextLine().trim().toUpperCase();

                OperationType type = OperationType.valueOf(line);
                Operation operation = operations.get(type);

                if (operation != null) {
                    operation.execute(sc);
                } else {
                    System.out.println("Операция " + type + " еще не реализована или не зарегистрирована!");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода: неверный формат данных или тип операции ("
                        + e.getMessage() + ")");
            } catch (IllegalStateException e) {
                System.out.println("Ошибка выполнения операции: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Произошла непредвиденная ошибка " + e.getMessage());
            }
        }
    }


}
