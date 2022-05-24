import api.EmailApi
import api.NumberApi
import org.mockito.Mockito
import spock.lang.Specification
import spock.lang.Unroll

class ListHelperTest extends Specification {

    NumberApi numberApi
    ListHelper helper

    def setup() {
        numberApi = Mockito.mock(NumberApi)
        helper = new ListHelper(numberApi)
    }

    @Unroll
    def "should return the lowest value"() {
        given:
            Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
        when:
            int returnValue = helper.getLowest();
        then:
            returnValue == expected
        where:
            values                          | expected
            [6, 9, 15, -2, 92, 11] as int[] | -2
    }

    @Unroll
    def "should return error if informed a empty list"() {
        given:
            Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
        when:
            helper.getNumbers();
        then:
            thrown(RuntimeException)
        where:
            values << [[] as int[], null]
    }

    @Unroll
    def "should return the maximum value"() {
        given:
            Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
        when:
            int returnValue = helper.getMaximum();
        then:
            returnValue == expected
        where:
            values                          | expected
            [6, 9, 15, -2, 92, 11] as int[] | 92
            [-6, -9, -15, -2, -92, -11] as int[] | -2
    }

    @Unroll
    def "should return the sequence of elements"() {
        given:
            Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
        when:
            int returnValue = helper.getSequence();
        then:
            returnValue == expected
        where:
            values                          | expected
            [6, 9, 15, -2, 92, 11] as int[] | 6
    }

    @Unroll
    def "should return the average of values"() {
        given:
            Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
        expect:
            helper.getAverage() == expected
        where:
            values                          | expected
            [6, 9, 15, -2, 92, 11] as int[] | 21.833333
    }

    @Unroll
    def "should validate if array is in conditions"() {
        given:
            Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
            def count = 0
        when:
            Boolean returnValue = helper.isValid();
        then:
            returnValue == expected
        where:
            values                          | expected
            [6, 9, 15, -2, 92, 11] as int[] | true
            [6, 9, 15, -2, 92] as int[]     | false
            [6, 9, 15, -2, 0, 11] as int[]  | false

    }

    @Unroll
    def "should validate who many times the API is called"() {
        given:
            NumberApi numberApi = Mock()
            ListHelper listHelper = new ListHelper(numberApi)
        when:
            Boolean returnValue = listHelper.isValid();
        then:
            returnValue == expected
            1 * numberApi.getListOfNumbers() >> values

        where:
            values                          | expected
            [6, 9, 15, -2, 92, 11] as int[] | true
            [6, 9, 15, -2, 92] as int[]     | false
            [6, 9, 15, -2, 0, 11] as int[]  | false

    }

}