package data

import dataTypes.Document

interface DocumentStore {

    fun storeDocuments(documents: List<Document>)

}