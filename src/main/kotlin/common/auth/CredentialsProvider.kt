package common.auth

interface CredentialsProvider {

    fun getCredentials(service: String): Credentials?

}