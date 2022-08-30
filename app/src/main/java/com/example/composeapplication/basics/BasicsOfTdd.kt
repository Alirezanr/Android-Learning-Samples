package com.example.composeapplication.basics

const val URL = "https://www.google.com/search?q="
fun main() {
    getSearchUrl_returnsNull()
    getSearchUrl_returnsNonNull()
    getSearchUrl_returnedResultContainsQuery()
    getSearchUrl_returnedResultContainsBaseUrl()
}

fun getSearchUrl_returnsNull() {
    // Test getSearchUrl returns null if query is null
    // 1
    val nullResult = getSearchUrl(null)
    if (nullResult == null) {
        // 2
        print("Success\n")
    } else {
        // 3
        throw AssertionError("Result was not null")
    }
}

fun getSearchUrl_returnsNonNull() {
    // Test getSearchUrl returns null if query is null
    // 1
    val nonNullResult = getSearchUrl("Query")
    if (nonNullResult != null) {
        // 2
        print("Success\n")
    } else {
        // 3
        throw AssertionError("Result was null")
    }
}

fun getSearchUrl_returnedResultContainsQuery() {
    val query = "some_string"
    val result = getSearchUrl(query)
    if (result?.contains(query) == true) {
        print("Success\n")
    } else {
        throw AssertionError("Result does not contain the query")
    }
}

fun getSearchUrl_returnedResultContainsBaseUrl() {
    val query = "some_string"
    val result = getSearchUrl(query)
    if (result?.contains(URL) == true) {
        print("Success\n")
    } else {
        throw AssertionError("Result does not contain the base url")
    }
}

fun getSearchUrl(query: String?): String? {
    return if (query == null) null
    else "$URL$query"
}
