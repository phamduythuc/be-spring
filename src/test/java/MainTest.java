import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Component
public class MainTest {
    @Autowired
    private UserDetailsService userDetailsService;
    public static void main(String[] args) {
        double v=3.14;
        System.out.println((long)v);
    }
}

@Data
@AllArgsConstructor
class Person {
    private int id;
    private String name;
    private int age;
    private String address;

}

@Data
class PersonDTO {
    private int id;
    private String name;
    private int age;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.age = person.getAge();
    }
}

