package console;

import java.util.Scanner;

public interface Operation {
    OperationType getType();
    void execute(Scanner sc);
}


