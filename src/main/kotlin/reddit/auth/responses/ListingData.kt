package reddit.auth.responses

data class ListingData<T>(val after: String?, val children: List<ListingItem<T>>)