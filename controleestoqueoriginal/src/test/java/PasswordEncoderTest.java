import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Senha admin codificada: " + encoder.encode("senhaSeguraAdmin"));
        System.out.println("Senha user codificada: " + encoder.encode("senhaSeguraUser"));
    }
}
