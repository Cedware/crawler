package reddit.auth

import common.Crawler
import common.auth.TokenProvider
import data.DocumentStore
import dataTypes.Document
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import reddit.auth.responses.Listing
import reddit.auth.responses.RedditPost

class RedditCrawler(private val tokenProvider: TokenProvider,
                    private val httpClient: HttpClient,
                    private val documentStore: DocumentStore,
                    private val subreddit: String): Crawler {

    override suspend fun crawlDocuments() {

        var rateLimitRemaining: Int? = null

        val token = tokenProvider.getToken()

        while (rateLimitRemaining == null || rateLimitRemaining > 0) {
            val response = httpClient.get("https://reddit.com/r/${subreddit}/new.json")
            val listing: Listing<RedditPost> = response.body()
            rateLimitRemaining = response.headers.get("x-ratelimit-remaining")?.toInt()
            val documents = listing.data.children.map { Document(it.data.name, it.data.selftext) }
            documentStore.storeDocuments(documents)
        }



    }

}