import api.EmailApi
import model.Email
import org.mockito.Mockito
import spock.lang.Specification
import spock.lang.Unroll

class EmailServiceTest extends Specification {

    EmailApi mockEmailApi
    EmailService emailService

    def setup() {
        mockEmailApi = Mockito.mock(EmailApi);
        emailService = new EmailService(mockEmailApi)
    }



    def "should return ordered list"() {
        given:
            List<Email> mockedList = [
                    new Email(1L, 'dbc@mail.com'),
                    new Email(2L, 'abc@mail.com'),
                    new Email(3L, 'cbc@mail.com'),
                    new Email(4L, 'bbc@mail.com')
            ]

            Mockito.when(mockEmailApi.fetchList()).thenReturn(mockedList)

        when:
            def result = emailService.orderedList()
        then:
            result == [
                    new Email(2L, 'abc@mail.com'),
                    new Email(4L, 'bbc@mail.com'),
                    new Email(3L, 'cbc@mail.com'),
                    new Email(1L, 'dbc@mail.com')
            ]
    }

    def "should return a empty list of emails"() {
        given:
            List<Email> emptyList = null
            Mockito.when(mockEmailApi.fetchList()).thenReturn(emptyList)
        when:
            def result = emailService.orderedList()
        then:
            result == []
    }

    def "should save an e-mail"() {
        given:
            def mail = "xpto@gmail.com"
        when:
            def resultOfEmailMethod = emailService.save(mail)
        then:
            resultOfEmailMethod.getEmail() == mail
            resultOfEmailMethod.getId() > 0
    }

    @Unroll
    def "should return an error when trying to save an email"() {
        when:
            emailService.save(mail)
        then:
            def exception = thrown(RuntimeException)
            exception.getMessage() == "Email should not be empty"
        where:
            mail | _
            null | _
            ""   | _
    }

    def "should update an e-mail"() {
        given:
            Long id = 1L;
            String newEmail = "xpto@gmail.com"

            Email email = new Email()
            email.setEmail(newEmail)
            email.setId(id)

            Mockito.when(mockEmailApi.get(id)).thenReturn(email)
        when:
            def result = emailService.update(id, newEmail)
        then:
            result.getEmail() == newEmail
            result.getId() == id
    }

    @Unroll
    def "should throw a exception when update an e-mail"() {
        when:
            emailService.update(id, newEmail)
        then:
            def result = thrown(RuntimeException)
            result.getMessage() == exceptionMessage
        where:
            id   | newEmail || exceptionMessage
            1    | null     || "Email should not be empty"
            1    | ""       || "Email should not be empty"
            null | "xpto@"  || "ID should not be empty"
            0    | "xpto@"  || "ID should not be empty"
    }


}
