import config.AppConfig;
import console.OperationsConsoleListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try {
            ConfigurableApplicationContext context =
                    new AnnotationConfigApplicationContext(AppConfig.class);
            context.registerShutdownHook();

            OperationsConsoleListener listener = context.getBean(OperationsConsoleListener.class);
            listener.run();
        } catch (Exception e) {
            Throwable cause = e;
            while (cause.getCause() != null) {
                cause = cause.getCause();
            }
            System.err.println("=== НАСТОЯЩАЯ ПРИЧИНА ОШИБКИ ===");
            cause.printStackTrace();
        }
    }
}