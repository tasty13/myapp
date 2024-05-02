package bitcamp.myapp;

import bitcamp.myapp.annotation.LoginUserArgumentResolver;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableTransactionManagement
@PropertySource({
    "file:${user.home}/config/jdbc.properties",
    "file:${user.home}/config/ncp.properties",
    "file:${user.home}/config/ncp-secret.properties"
})
@Controller
public class App implements WebMvcConfigurer {

  @Autowired
  LoginUserArgumentResolver loginUserArgumentResolver;

  public static void main(String[] args) throws Exception {
    System.out.println("과제관리 시스템 서버 실행!");
    SpringApplication.run(App.class, args);

    Properties props = System.getProperties();
    Set<Entry<Object, Object>> entrySet = props.entrySet();
    for (Entry<Object, Object> entry : entrySet){
      System.out.printf("$s=%s\n", entry.getKey(), entry.getValue());
    }
  }

  @GetMapping("/home")
  public void home() {
  }

  @GetMapping("/about")
  public void about() {
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(loginUserArgumentResolver);
  }
}
