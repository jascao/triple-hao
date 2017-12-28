package hackathon.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class FileUtils {

	public static String SPLIT = ",";
	public static String DEFAULT_FILEPATH = "/Users/jascao/Documents/test.csv";
	public static int BATCH_NUM = 10000;

	public static String UploadFile(String filePath) {
		// get Process Id
		String processId = MongoDBUtils.insertProcess(filePath);

		// Start Thread
		UploadThread t = new UploadThread();
		t.setFileName(filePath);
		t.setProcessId(processId);
		t.start();

		return processId;
	}

	public static void readFileAndUpload(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			file = new File(DEFAULT_FILEPATH);
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = reader.readLine();

			// head info
			String[] heads = tempString.split(SPLIT);
			int total_num = heads.length;

			// database
			MongoClient mongoClient = new MongoClient(MongoDBUtils.DB_IP, MongoDBUtils.DB_PORT);
			MongoDatabase mgdb = mongoClient.getDatabase(MongoDBUtils.DB_DATABASE_NAME);
			MongoCollection<Document> collection = mgdb.getCollection(MongoDBUtils.DB_COLLECTION_CUSTOMER);

			// data
			List<Document> documents = new ArrayList<Document>();

			int num = 0;
			while ((tempString = reader.readLine()) != null) {
				documents.add(setDocument(tempString, heads, total_num));
				num++;

				if (num % BATCH_NUM == 0) {
					collection.insertMany(documents);
					documents = new ArrayList<Document>();
					System.out.println("[-----------------]Batch add " + BATCH_NUM);
				}
			}
			if (num % BATCH_NUM != 0) {
				System.out.println("[-----------------]Batch last add " + num % BATCH_NUM);
				collection.insertMany(documents);
			}

			mongoClient.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	private static Document setDocument(String tempString, String[] heads, int total_num) {
		Document doc = new Document();

		String[] datas = tempString.split(SPLIT);
		for (int i = 0; i < total_num; i++) {
			doc.append(heads[i], datas[i]);
		}

		return doc;
	}
}
