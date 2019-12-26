package core.common.config;

import core.support.service.BaseClient;
import java.util.Set;
import org.reflections.Reflections;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Configuration
@Import(HttpClientConfiguration.class)
public class ServiceClientConfiguration {

  @Component
  @ConfigurationProperties(prefix = "service")
  public static class ClientProperties {

    private String username;
    private String password;
    private String url;
  }

  @Bean
  public String commonStringBean() {
    return "Common Bean";
  }

  @Bean
  public BeanFactoryPostProcessor beanFactoryPostProcessor() {
    return bf -> {
      BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) bf;

      Reflections reflections = new Reflections("core.support");
      Set<Class<? extends BaseClient>> clientClasses = reflections.getSubTypesOf(BaseClient.class);

      clientClasses.forEach(clientClass -> {
        StringBuilder sb = new StringBuilder(clientClass.getName());
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        String beanName = sb.toString();

        beanFactory.registerBeanDefinition(beanName,
            BeanDefinitionBuilder.genericBeanDefinition(clientClass)
                .addConstructorArgReference("commonStringBean")
                .getBeanDefinition());
      });
    };
  }
}
