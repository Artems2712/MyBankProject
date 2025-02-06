package bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Демонстрационный класс с методом main().
 */
public class Main {
    public static void main(String[] args) {
        // Создаём несколько счетов
        DebitAccount debitAccount = new DebitAccount(
                "DA-001",
                new BigDecimal("10000"),
                "Иван Иванов"
        );

        CreditAccount creditAccount = new CreditAccount(
                "CA-001",
                new BigDecimal("0"),
                "Пётр Петров",
                new BigDecimal("-5000") // Разрешаем до -5000
        );

        SavingsAccount savingsAccount = new SavingsAccount(
                "SA-001",
                new BigDecimal("20000"),
                "Сергей Сидоров"
        );

        // Добавляем в список
        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(debitAccount);
        accounts.add(creditAccount);
        accounts.add(savingsAccount);

        // Создаём обработчик транзакций
        TransactionProcessor processor = new TransactionProcessor();

        // 1) Пробуем снять 2000 со всех счетов
        System.out.println("--- Транзакция на 2000 ---");
        processor.processTransaction(accounts, new BigDecimal("2000"));
        System.out.println();

        // 2) Пополняем кредитный счёт на 3000
        creditAccount.deposit(new BigDecimal("3000"));
        System.out.println();

        // 3) Пробуем снять 6000 (CreditAccount > 5000, должно выдать отказ)
        System.out.println("--- Транзакция на 6000 ---");
        processor.processTransaction(accounts, new BigDecimal("6000"));
        System.out.println();

        // 4) Начисляем проценты на накопительном счёте
        savingsAccount.applyInterest();
        System.out.println();

        // 5) Пробуем снять 9000 с дебетового счёта (<= 10000, должно пройти)
        System.out.println("--- Транзакция на 9000 (только DebitAccount) ---");
        debitAccount.withdraw(new BigDecimal("9000"));
        System.out.println();

        // 6) Пробуем снять 11000 с дебетового счёта (> 10000, отказ)
        System.out.println("--- Транзакция на 11000 (только DebitAccount) ---");
        debitAccount.withdraw(new BigDecimal("11000"));
        System.out.println();
    }
}
