import spock.lang.Specification
import spock.lang.Unroll

class CalculatorTest extends Specification {

    def "should sum"() {
        given:
            int valueA = 1
            int valueB = 2
        when:
            int result = Calculator.sum(valueA, valueB)
        then:
            result == (valueA + valueB)
    }

    @Unroll
    def "should divide"() {
        when:
            float result = Calculator.divide(valueA, valueB)
        then:
            result == expectedResult
        where:
            valueA | valueB | expectedResult
            1.0f    | 1.0f    | 1f
            1.0f    | 0f      | 0f
    }

    def "should do an absolute sum"() {
        when:
            def resultOfAbsolutSum = Calculator.absoluteSum(a, b)
        then:
            resultOfAbsolutSum == expectedResult
        where:
            a | b | expectedResult
            1 | -6   | 7
           -1 | -8   | 9
            1 | null | null
         null | null | null


    }
}
