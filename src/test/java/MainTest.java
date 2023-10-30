
import com.example.springsecurity.entities.Address;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

@Component

public class MainTest {
    @Test
    void test() throws JsonProcessingException {
        Address address=new Address();
        address.setCity("Nam Dinh");
        address.setZipCode("123");
        address.setStreet("Tran Phu");
        String result=new ObjectMapper().writeValueAsString(address);
        System.out.println(result);
    }
}
