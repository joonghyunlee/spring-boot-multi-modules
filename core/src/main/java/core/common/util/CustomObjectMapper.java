package core.common.util;

import java.io.DataOutput;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomObjectMapper extends ObjectMapper {
  private static final long serialVersionUID = 1L;

  private void autoconfigureFeatures(Object value) {
    JavaType javaType = _typeFactory.constructType(value.getClass());
    autoconfigureFeatures(javaType);
  }

  private void autoconfigureFeatures(JavaType javaType) {
    Annotation rootAnnotation = javaType.getRawClass().getAnnotation(JsonRootName.class);
    this.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, rootAnnotation != null);
    this.configure(SerializationFeature.WRAP_ROOT_VALUE, rootAnnotation != null);
  }

  @Override
  public void writeValue(DataOutput out, Object value) throws IOException {
    autoconfigureFeatures(value);
    super.writeValue(out, value);
  }

  @Override
  public void writeValue(Writer w, Object value)
      throws IOException, JsonGenerationException, JsonMappingException {
    autoconfigureFeatures(value);
    super.writeValue(w, value);
  }

  @Override
  public byte[] writeValueAsBytes(Object value) throws JsonProcessingException {
    autoconfigureFeatures(value);
    return super.writeValueAsBytes(value);
  }

  @Override
  public String writeValueAsString(Object value) throws JsonProcessingException {
    autoconfigureFeatures(value);
    return super.writeValueAsString(value);
  }

  @Override
  protected Object _readMapAndClose(JsonParser jsonParser, JavaType javaType)
      throws IOException, JsonParseException, JsonMappingException {
    autoconfigureFeatures(javaType);
    return super._readMapAndClose(jsonParser, javaType);
  }
}
