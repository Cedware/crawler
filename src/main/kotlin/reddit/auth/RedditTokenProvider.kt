package reddit.auth

import common.auth.Credentials
import common.auth.Token
import common.auth.TokenProvider
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import reddit.auth.responses.RedditTokenResponse

class RedditTokenProvider(
    private val userCredentials: Credentials,
    private val appCredentials: Credentials,
    private val httpClient: HttpClient
): TokenProvider {


    override suspend fun getToken(): Token {
        val response: RedditTokenResponse = httpClient.post {
            url("https://www.reddit.com/api/v1/access_token")
            formData {
                parameter("username", userCredentials.user)
                parameter("password", userCredentials.password)
                parameter("grant_type", "password")
            }
            basicAuth(appCredentials.user, appCredentials.password)
        }.body()
        return Token(response.accessToken)
    }

}