package bank;

import java.math.BigDecimal;

/**
 * Интерфейс для вычисления комиссии при снятии средств.
 */
public interface TransactionFee {
    /**
     * Метод для вычисления комиссии.
     * Возвращает фактическую сумму, которая будет списана/учтена после вычета комиссии
     * (или наоборот, в зависимости от логики).
     */
    BigDecimal applyFee(BigDecimal amount);
}
