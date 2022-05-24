import spock.lang.Specification
import spock.lang.Unroll;

public class RemoveDuplicatedTest extends Specification {

    @Unroll
    def "should remove duplicate numbers and verify empty list"() {
        when:
            def result = RemoveDuplicated.removeDuplicate(value)
        then:
            result == expected
        where:
            value                       | expected
            [1, 2, 3, 3, 4, 4] as int[] | [1, 2, 3, 4] as int[]
            [] as int[]                 | [] as int[]
            null                        | [] as int[]
    }
}