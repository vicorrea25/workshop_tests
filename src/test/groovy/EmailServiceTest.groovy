import api.EmailApi
import model.Email
import org.mockito.Mockito
import spock.lang.Specification

class EmailServiceTest extends Specification {

    EmailApi mockEmailApi = Mockito.mock(EmailApi)
    EmailService emailService = new EmailService(mockEmailApi)


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
        Email email = new Email();
        email.setId(2L)
        email.setEmail(mail)
        when:
        def resultOfEmailMethod = emailService.save(mail)
        then:
        resultOfEmailMethod.getEmail() == mail

    }

    def "should return an error when trying to save an email"() {
        when:
        emailService.save(mail)
        then:
        thrown(expectedException)
        where:
        mail | expectedException | exceptionMessage
        null | RuntimeException  | "Email should not be empty"
        ""   | RuntimeException  | "Email should not be empty"
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

    def "should throw a exception when update an e-mail"() {
        when:
        emailService.update(id, newEmail)
        then:
        thrown(expectedException)
        where:
         id     | newEmail   | expectedException | exceptionMessage
         1      |    null    | RuntimeException  | "Email should not be empty"
         1      |    ""      | RuntimeException  | "Email should not be empty"
         null   |    ""      | RuntimeException  | "ID should not be empty"
         0      |  "xpto@"   | RuntimeException  | "ID should not be empty"
    }


}
