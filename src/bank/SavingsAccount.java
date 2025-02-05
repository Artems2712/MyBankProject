package bank;

import java.math.BigDecimal;

/**
 * Накопительный (сберегательный) счёт.
 * Реализует InterestBearing (начисление процентов).
 */
public class SavingsAccount extends BankAccount implements InterestBearing {

    // Допустим, 0.5% в месяц (пример).
    private static final BigDecimal MONTHLY_INTEREST_RATE = new BigDecimal("0.005");

    public SavingsAccount(String accountNumber, BigDecimal balance, String accountHolder) {
        super(accountNumber, balance, accountHolder);
    }

    /**
     * Снятие как у дебетового счёта: без перерасхода.
     */
    @Override
    public void withdraw(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            System.out.println("Счёт (Savings) " + accountNumber + ": снятие "
                    + amount + ". Текущий баланс: " + balance);
        } else {
            System.out.println("Операция отклонена: недостаточно средств на накопительном счёте " + accountNumber);
        }
    }

    /**
     * Ежемесячное начисление процентов.
     */
    @Override
    public void applyInterest() {
        BigDecimal interest = balance.multiply(MONTHLY_INTEREST_RATE);
        balance = balance.add(interest);
        System.out.println("Начислены проценты (" + interest + ") на счёт "
                + accountNumber + ". Новый баланс: " + balance);
    }
}
