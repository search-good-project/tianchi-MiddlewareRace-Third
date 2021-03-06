package io.openmessaging.demo;

import java.io.File;

public class Topic {

	private final String path;
	public final String bucket;

	private final PersistenceFile logFile; // Log File
	private final WriteBuffer writeBuffer; // Write Buffer

	public Topic(String bucket) {
		this.bucket = bucket;
		path = System.getProperty("path") + "/" + bucket;
		// topic dir
		File file = new File(path);
		if (file.exists()) {
			if (!file.isDirectory())
				throw new ClientOMSException(path + "不是一个目录");
		} else {
			file.mkdirs();
		}
		logFile = new PersistenceFile(path, Constants.LOG_FILE_NAME);
		writeBuffer = new WriteBuffer(logFile);
	}

	// for Producer
	public WriteBuffer getWriteBuffer() {
		return writeBuffer;
	}

	// for Consumer
	public PersistenceFile getLogFile() {
		return logFile;
	}

}
