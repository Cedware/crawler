import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import common.auth.Credentials
import data.MongoDocumentStore
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.jackson.*
import kotlinx.coroutines.runBlocking
import org.litote.kmongo.KMongo
import reddit.auth.RedditCrawler
import reddit.auth.RedditTokenProvider

fun main() {
    val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            jackson(){
                propertyNamingStrategy = SnakeCaseStrategy()
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            }
        }
    }

    val tokenProvider = RedditTokenProvider(
        Credentials("room-23", "FSidlD#rt2016"),
        Credentials("f9VZOcOHLcyVDQy4xwbtWQ", "vSzaL1FY7huLbfbzU3zTYziq6q3HCw"),
        httpClient
    )

    val mongoClient = KMongo.createClient("mongodb://localhost:27018")
    val documentStore = MongoDocumentStore(mongoClient)

    val crawler = RedditCrawler(tokenProvider, httpClient, documentStore,"drugs")
    runBlocking {
        crawler.crawlDocuments()
    }


}