package bank;

import java.math.BigDecimal;
import java.util.List;

/**
 * Обработчик транзакций.
 * processTransaction для всех счетов в списке вызывает withdraw(amount).
 */
public class TransactionProcessor {

    public void processTransaction(List<BankAccount> accounts, BigDecimal amount) {
        for (BankAccount account : accounts) {
            account.withdraw(amount);
        }
    }
}
