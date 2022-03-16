import api.EmailApi
import api.NumberApi
import org.mockito.Mockito
import spock.lang.Specification

class ListHelperTest extends Specification {

    NumberApi numberApi = Mockito.mock(NumberApi)
    ListHelper helper = new ListHelper(numberApi)

    def "should return the lowest value"() {
        given:
        Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
        when:
        int returnValue = helper.returnTheLowestValue();
        then:
        returnValue == expected
        where:
        values | expected
        [6, 9, 15, -2, 92, 11] as int[] | -2
    }

    def "should return error if informed a empty list in method --> returnTheLowestValue"() {
        given:
        Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
        when:
        helper.returnTheLowestValue();
        then:
        thrown(expectedException)
        where:
             values | expectedException
        [] as int[] | RuntimeException
             null   | RuntimeException
    }

    def "should return the maximum value"() {
        given:
        Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
        when:
        int returnValue = helper.returnTheMaximumValue();
        then:
        returnValue == expected
        where:
        values | expected
        [6, 9, 15, -2, 92, 11] as int[] | 92
    }

    def "should return error if informed a empty list in method --> returnTheMaximumValue"() {
        given:
        Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
        when:
        helper.returnTheMaximumValue();
        then:
        thrown(expectedException)
        where:
        values | expectedException
        [] as int[] | RuntimeException
        null   | RuntimeException
    }

    def "should return the sequence of elements"() {
        given:
        Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
        when:
        int returnValue = helper.returnTheSequence();
        then:
        returnValue == expected
        where:
        values | expected
        [6, 9, 15, -2, 92, 11] as int[] | 6
    }

    def "should return error if informed a empty list in method --> returnTheSequence"() {
        given:
        Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
        when:
        helper.returnTheSequence();
        then:
        thrown(expectedException)
        where:
        values | expectedException
        [] as int[] | RuntimeException
        null   | RuntimeException
    }

    def "should return the average of values"() {
        given:
        Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
        when:
        String returnValue = helper.returnAverageValue();
        then:
        returnValue == expected
        where:
        values | expected
        [6, 9, 15, -2, 92, 11] as int[] | "21.833333"
    }

    def "should return error if informed a empty list in method --> returnAverageValue"() {
        given:
        Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
        when:
        helper.returnAverageValue();
        then:
        thrown(expectedException)
        where:
        values | expectedException
        [] as int[] | RuntimeException
        null   | RuntimeException
    }

    def "should validate if array is in conditions"() {
        given:
        Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
        when:
        Boolean returnValue = helper.verifyNumberOfElementsAndTheSequenceToReturnABooleanValue();
        then:
        returnValue == expected
        where:
        values | expected
        [6, 9, 15, -2, 92, 11] as int[] | true
        [6, 9, 15, -2, 92] as int[] | false
        [6, 9, 15, -2, 0, 11] as int[] | false

    }

    def "should return error if informed a empty list in method --> verifyNumberOfElementsAndTheSequenceToReturnABooleanValue"() {
        given:
        Mockito.when(numberApi.getListOfNumbers()).thenReturn(values)
        when:
        helper.verifyNumberOfElementsAndTheSequenceToReturnABooleanValue();
        then:
        thrown(expectedException)
        where:
        values | expectedException
        [] as int[] | RuntimeException
        null   | RuntimeException
    }
}