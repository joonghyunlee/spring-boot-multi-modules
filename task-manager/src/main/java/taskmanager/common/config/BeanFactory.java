package taskmanager.common.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import taskmanager.common.model.PropertyMapFactory;

@Configuration
public class BeanFactory {
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper mapper = new ModelMapper();

    for (PropertyMap<?, ?> map : PropertyMapFactory.getMaps()) {
      mapper.addMappings(map);
    }

    return mapper;
  }
}
