package bank;

import java.math.BigDecimal;

/**
 * Абстрактный класс банковского счёта.
 */
public abstract class BankAccount {
    protected String accountNumber;
    protected BigDecimal balance;
    protected String accountHolder;

    public BankAccount(String accountNumber, BigDecimal balance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolder = accountHolder;
    }

    /**
     * Абстрактный метод снятия средств.
     * Логику должен реализовать класс-наследник.
     */
    public abstract void withdraw(BigDecimal amount);

    /**
     * Метод пополнения счёта.
     */
    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        System.out.println("Счёт " + accountNumber + " пополнен на " + amount
                + ". Текущий баланс: " + balance);
    }

    // Геттеры (если нужны)
    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}
