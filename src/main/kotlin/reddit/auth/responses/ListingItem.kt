package reddit.auth.responses

data class ListingItem<T>(val kind: String, val data: T)