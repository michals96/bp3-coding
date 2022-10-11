package com.bp3.model;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class SimpleNodeSerializer extends StdSerializer<SimpleNode> {
  public SimpleNodeSerializer() {
    this(null);
  }

  protected SimpleNodeSerializer(final Class<SimpleNode> t) {
    super(t);
  }

  @Override
  public void serialize(final SimpleNode value, final JsonGenerator jgen, final SerializerProvider provider)
      throws IOException {
    jgen.writeStartObject();
    jgen.writeNumberField("id", Integer.parseInt(value.getId()));
    jgen.writeStringField("name", value.getName());
    jgen.writeStringField("type", value.getType().toString());
    jgen.writeEndObject();
  }
}
