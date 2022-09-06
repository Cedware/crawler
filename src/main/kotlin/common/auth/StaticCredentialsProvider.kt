package common.auth

class StaticCredentialsProvider(private val credentials: Map<String, Credentials>): CredentialsProvider {

    override fun getCredentials(service: String): Credentials? {

        return credentials[service]

    }


}