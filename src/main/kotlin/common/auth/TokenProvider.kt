package common.auth

interface TokenProvider {

    suspend fun getToken(): Token

}