package bitcamp.myapp.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// 사용자가 입력한 암호와 DB에 저장된 암호 비교
public class SimplePasswordEncoder implements PasswordEncoder {

  @Override
  public String encode(CharSequence rawPassword) {
    // 사용자가 입력한 암호를 암호화하여 리턴

    try {
      // 사용자가 입력한 암호를 암호화시켜 리턴하는 것을 보여주기 위한 예이다.
      // Base64는 암호 알고리즘 아님! encode()라는 메서드의 역할을 이해하기 위해서 사용
      return Base64.getEncoder()
          .encodeToString(rawPassword.toString().getBytes(StandardCharsets.UTF_8.name()));
    } catch (Exception e) {
      throw new RuntimeException("지원하지 않는 Charset입니다.");
    }
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    // DB에 보관된 암호와 사용자가 입력한 암호가 같은지 비교하여 리턴

    // 현재는 암호화를 따로 하지 않기 때문에 원래 암호 그대로 비교
    return encodedPassword.matches(this.encode(rawPassword));
  }
}
