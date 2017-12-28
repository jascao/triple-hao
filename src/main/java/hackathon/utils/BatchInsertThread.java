package hackathon.utils;

import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class BatchInsertThread extends Thread {

	List<Document> documents;

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public void run() {
		// database
		MongoClient mongoClient = new MongoClient(MongoDBUtils.DB_IP, MongoDBUtils.DB_PORT);
		MongoDatabase mgdb = mongoClient.getDatabase(MongoDBUtils.DB_DATABASE_NAME);
		MongoCollection<Document> collection = mgdb.getCollection(MongoDBUtils.DB_COLLECTION_CUSTOMER);

		collection.insertMany(documents);

		mongoClient.close();
		System.out.println("[-----------------]Batch add " + documents.size() + "|" + (new Date()).getTime());
	}
}
