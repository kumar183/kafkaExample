package com.info.kafka.app;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.hyperic.sigar.SigarException;

import com.info.kafka.constants.CommonConstants;
import com.info.kafka.jsonserializer.JsonSerializer;
import com.info.kafka.model.SystemDetails;
import com.info.kafka.producer.ProducerCreator;
import com.info.kafka.system.SystemInfo;

public class ProducerApp {
	public static SystemInfo sys = new SystemInfo();
	public static void main(String[] args) throws SigarException, InterruptedException {
		produceDta();

	}
	
	public static void produceDta() throws InterruptedException, SigarException {
		Producer<Long, String> producer = ProducerCreator.createProducer();

		for (int index = 0; index < CommonConstants.MESSAGE_COUNT; index++) {
			Thread.sleep(1000);
			String value = toJson();
			final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(CommonConstants.TOPIC_NAME, (long)index, value);
			try {
				RecordMetadata metadata = producer.send(record).get();
				System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
						+ " with offset " + metadata.offset());
			} catch (ExecutionException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			} catch (InterruptedException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			}
		}
	}
	
	public static String toJson() throws SigarException{
		
		SystemDetails sysDetails = new SystemDetails();
		sysDetails.setTimestamp(System.currentTimeMillis());
		sysDetails.setCpuUtilization(sys.getCPUIdlePerc());
		sysDetails.setMemoryUsage(sys.getSystemUsedMemory());
		sysDetails.setTotalMemory(sys.getSystemTotalMemory());
		
		return JsonSerializer.serialize(sysDetails);
		
	}

}
