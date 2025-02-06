package bank;

import java.math.BigDecimal;

/**
 * Дебетовый счёт, только без ухода в минус.
 * Реализует TransactionValidator, чтобы ограничить транзакции > 10000.
 */
public class DebitAccount extends BankAccount implements TransactionValidator {

    public DebitAccount(String accountNumber, BigDecimal balance, String accountHolder) {
        super(accountNumber, balance, accountHolder);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        // Сначала проверяем валидацию
        if (!validate(amount)) {
            System.out.println("Операция отклонена: сумма " + amount
                    + " превышает лимит для дебетового счёта (10_000).");
            return;
        }

        // Проверяем, достаточно ли средств
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            System.out.println("Счёт " + accountNumber + ": снятие " + amount
                    + ". Текущий баланс: " + balance);
        } else {
            System.out.println("Операция отклонена: недостаточно средств на счёте " + accountNumber);
        }
    }

    /**
     * Запрет на транзакции > 10_000.
     */
    @Override
    public boolean validate(BigDecimal amount) {
        BigDecimal limit = new BigDecimal("10000");
        return amount.compareTo(limit) <= 0;
    }
}
