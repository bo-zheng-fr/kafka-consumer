package com.adeo.legacyadapterfornewposeditor.laneinterface.kafka_consumer;

import java.io.IOException;
import java.io.InputStream;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.kafka.common.serialization.Deserializer;
import org.nrf_arts.ixretail.namespace.avro.POSLogRoot;

public class AvroMessageDeserializer implements Deserializer<POSLogRoot> {

  @Override
  public POSLogRoot deserialize(String topic, byte[] data) {

    try {
      if (data != null) {
        DatumReader<POSLogRoot> reader = new SpecificDatumReader<>(POSLogRoot.getClassSchema());
        Decoder decoder = DecoderFactory.get().binaryDecoder(data, null);
        return reader.read(null, decoder);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
