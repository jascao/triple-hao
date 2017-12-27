package hackathon.utils;

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

		FileUtils.readFileAndUpload(fileName);

		MongoDBUtils.updateProcess(processId);

		System.out.println("线程" + Thread.currentThread().getName() + "执行完毕");
	}
}
