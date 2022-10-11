package com.bp3.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class SimpleEdgeSerializer extends StdSerializer<SimpleEdge> {
  public SimpleEdgeSerializer() {
    this(null);
  }

  protected SimpleEdgeSerializer(final Class<SimpleEdge> t) {
    super(t);
  }

  @Override
  public void serialize(final SimpleEdge value, final JsonGenerator jgen, final SerializerProvider provider)
      throws IOException {
    jgen.writeStartObject();
    jgen.writeNumberField("from", Integer.parseInt(value.getFrom()));
    jgen.writeNumberField("to", Integer.parseInt(value.getTo()));
    jgen.writeEndObject();
  }
}
