import api.EmailApi;
import api.NumberApi;

import java.util.Arrays;

/**
 *
 * Your task is to process a sequence of integer numbers to determine the following statistics:
 *
 *     o) minimum value
 *     o) maximum value
 *     o) number of elements in the sequence
 *     o) average value
 *     o) number of elements in the sequence == 6 && average value >= 20
 *
 * For example: [6, 9, 15, -2, 92, 11]
 *
 *     o) minimum value = -2
 *     o) maximum value = 92
 *     o) number of elements in the sequence = 6
 *     o) average value = 21.833333
 *     o) true
 *
 * EXTRA CHALLENGE:
 * Get the list of numbers from an API (check EmailApi for examples)
 */
public class ListHelper {

    private final NumberApi numberApi;

    public ListHelper(NumberApi numberApi) {
        this.numberApi = numberApi;
    }

    private int[] getNumbers() {
        return numberApi.getListOfNumbers();
    }

    public int returnTheLowestValue(){
        if(getNumbers().length == 0) {
            throw new RuntimeException("Lista vazia");
        }

        return Arrays.stream(getNumbers()).min().getAsInt();
    }

    public int returnTheMaximumValue(){
        if(getNumbers().length == 0) {
            throw new RuntimeException("Lista vazia");
        }
        return Arrays.stream(getNumbers()).max().getAsInt();
    }

    public Long returnTheSequence(){
        if(getNumbers().length == 0) {
            throw new RuntimeException("Lista vazia");
        }
        return Arrays.stream(getNumbers()).count();
    }

    public String returnAverageValue(){
        if(getNumbers().length == 0) {
            throw new RuntimeException("Lista vazia");
        }
        Double result = Arrays.stream(getNumbers()).average().getAsDouble();
        String resultString = String.format("%.6f", result);

        return resultString;
    }

    public Boolean verifyNumberOfElementsAndTheSequenceToReturnABooleanValue(){
        Boolean result = false;
        if(getNumbers().length == 0) {
            throw new RuntimeException("Lista vazia");
        }
        Double average = Arrays.stream(getNumbers()).average().getAsDouble();
        Long numberOfElements = Arrays.stream(getNumbers()).count();

        if(average >= 20 && numberOfElements == 6) {
            result = true;
        }

        return result;
    }
}
