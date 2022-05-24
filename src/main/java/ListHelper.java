import api.EmailApi;
import api.NumberApi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * Your task is to process a sequence of integer numbers to determine the following statistics:
 * <p>
 * o) minimum value
 * o) maximum value
 * o) number of elements in the sequence
 * o) average value
 * o) number of elements in the sequence == 6 && average value >= 20
 * <p>
 * For example: [6, 9, 15, -2, 92, 11]
 * <p>
 * o) minimum value = -2
 * o) maximum value = 92
 * o) number of elements in the sequence = 6
 * o) average value = 21.833333
 * o) true
 * <p>
 * EXTRA CHALLENGE:
 * Get the list of numbers from an API (check EmailApi for examples)
 */
public class ListHelper {

    private final NumberApi numberApi;

    public ListHelper(NumberApi numberApi) {
        this.numberApi = numberApi;
    }

    private int[] getNumbers() {
        int[] numbers = numberApi.getListOfNumbers();
        if (numbers == null || numbers.length == 0) {
            throw new RuntimeException("Lista vazia");
        }
        return numbers;
    }

    public int getLowest() {
        return Arrays.stream(getNumbers()).min().getAsInt();
    }

    public int getMaximum() {
        return Arrays.stream(getNumbers()).max().getAsInt();
    }

    private Long getSequence(int[] numbers) {
            return Arrays.stream(numbers).count();
    }

    public Long getSequence() {
            return getSequence(getNumbers());
    }

    private Double getAverage(int[] numbers) {
        Double result = 0.0;

        result = Arrays.stream(numbers).average().getAsDouble();

        return new BigDecimal(result.toString()).setScale(6, RoundingMode.HALF_UP).doubleValue();
    }

    public Double getAverage() {
        return getAverage(getNumbers());
    }

    public Boolean isValid() {
        Boolean result = false;
        int[] numbers = getNumbers();
        if (getAverage(numbers) >= 20 && getSequence(numbers) == 6) {
            result = true;
        }

        return result;
    }
}
