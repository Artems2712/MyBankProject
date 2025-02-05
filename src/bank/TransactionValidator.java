package bank;

/**
 * Интерфейс валидации транзакций.
 */
public interface TransactionValidator {
    /**
     * Проверяет, допустима ли транзакция на указанную сумму.
     */
    boolean validate(java.math.BigDecimal amount);
}
