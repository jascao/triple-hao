package hackathon.utils;

import java.util.Date;

public class UploadThread extends Thread {

	private String fileName;
	private String processId;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public void run() {

		System.out.println("进入线程" + Thread.currentThread().getName() + " [fileName]" + fileName);

		Date start = new Date();
		FileUtils.readFileAndUpload(fileName);

		MongoDBUtils.updateProcess(processId);
		Date end = new Date();

		System.out.println("线程" + Thread.currentThread().getName() + "执行完毕");

		long d = end.getTime() - start.getTime();
		System.out.println("线程" + Thread.currentThread().getName() + "执行完毕");

		System.out.println("共耗时" + d + "ms");
	}
}
