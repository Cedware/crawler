package common

import dataTypes.Document

interface Crawler {

    suspend fun crawlDocuments()

}