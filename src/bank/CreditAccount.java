package bank;

import java.math.BigDecimal;

/**
 * Кредитный счёт, позволяет уходить в минус до creditLimit.
 * Реализует TransactionFee (комиссия 1%) и TransactionValidator (лимит 5000).
 */
public class CreditAccount extends BankAccount implements TransactionFee, TransactionValidator {

    private BigDecimal creditLimit;

    public CreditAccount(String accountNumber, BigDecimal balance, String accountHolder, BigDecimal creditLimit) {
        super(accountNumber, balance, accountHolder);
        this.creditLimit = creditLimit;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        // Сначала проверяем валидацию
        if (!validate(amount)) {
            System.out.println("Операция отклонена: сумма " + amount
                    + " превышает лимит для кредитного счёта (5_000).");
            return;
        }

        // Вычитаем комиссию из суммы (по условию: "комиссия вычитается из суммы")
        BigDecimal finalAmountToWithdraw = applyFee(amount);

        // Проверяем, не превысим ли кредитный лимит
        BigDecimal newBalance = this.balance.subtract(finalAmountToWithdraw);

        if (newBalance.compareTo(creditLimit) >= 0) {
            this.balance = newBalance;
            System.out.println("Счёт " + accountNumber + ": снятие (с комиссией) "
                    + finalAmountToWithdraw + ". Текущий баланс: " + balance);
        } else {
            System.out.println("Операция отклонена: превышение кредитного лимита ("
                    + creditLimit + ") на счёте " + accountNumber);
        }
    }

    /**
     * Комиссия 1%: из суммы amount вычитается 1%.
     * Если на руках хотим ровно 'amount', то со счёта уйдёт чуть больше,
     * но в примере реализуем логику: "клиент хочет снять amount,
     * а на руки получает (amount - 1%)". Здесь покажем вариант:
     * возвращаем (amount - fee).
     */
    @Override
    public BigDecimal applyFee(BigDecimal amount) {
        BigDecimal fee = amount.multiply(new BigDecimal("0.01")); // 1%
        return amount.subtract(fee);
    }

    /**
     * Запрет на транзакции > 5_000.
     */
    @Override
    public boolean validate(BigDecimal amount) {
        BigDecimal limit = new BigDecimal("5000");
        return amount.compareTo(limit) <= 0;
    }
}
