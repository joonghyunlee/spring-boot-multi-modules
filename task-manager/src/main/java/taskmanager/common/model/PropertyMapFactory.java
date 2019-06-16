package taskmanager.common.model;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.PropertyMap;
import taskmanager.domain.task.model.TaskDefinition;
import taskmanager.domain.task.model.TaskDefinitionEntity;

public class PropertyMapFactory {
  public static class TaskDefinitionMap extends PropertyMap<TaskDefinitionEntity, TaskDefinition> {
    @Override
    protected void configure() {
      map().setId(source.getId());
      map().setName(source.getName());
      map().setType(source.getType());
      map().setDescription(source.getDescription());
    }
  }

  public static List<PropertyMap<?, ?>> getMaps() {
    List<PropertyMap<?, ?>> maps = new ArrayList<PropertyMap<?, ?>>();

    maps.add(new TaskDefinitionMap());

    return maps;
  }
}
