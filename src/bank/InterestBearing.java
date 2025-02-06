package bank;

/**
 * Интерфейс для накопительных счетов (начисление процентов).
 */
public interface InterestBearing {
    /**
     * Начисляет проценты (логика зависит от реализации).
     */
    void applyInterest();
}
