package com.adeo.legacyadapterfornewposeditor.laneinterface.kafka_consumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.nrf_arts.ixretail.namespace.avro.POSLogRoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class KafkaConsumerApplication {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("group.id", "test");
		props.setProperty("enable.auto.commit", "true");
		props.setProperty("auto.commit.interval.ms", "1000");
		props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		KafkaConsumer<String, POSLogRoot> consumer = new KafkaConsumer<>(props,
				new StringDeserializer(), new AvroMessageDeserializer());
		consumer.subscribe(Arrays.asList("poslog-topic"));

		while (true) {
			ConsumerRecords<String, POSLogRoot> records = consumer.poll(Duration.ofMillis(100));

			for (ConsumerRecord<String, POSLogRoot> record : records) {
				System.out.println(
						"Received message: (" + record.key() + ", " + record.value().toString() + ") at offset "
								+ record.offset());
			}
		}
	}
}
