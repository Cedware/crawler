package data

import com.mongodb.client.MongoClient
import dataTypes.Document
import org.litote.kmongo.KMongo
import org.litote.kmongo.*
class MongoDocumentStore(
    private val mongoClient: MongoClient
): DocumentStore {
    override fun storeDocuments(documents: List<Document>) {
        val documentCollection = mongoClient.getDatabase("test").getCollection<Document>("documents")
        documentCollection.insertMany(documents)
    }
}