package dev.mfayaz.jcmake.parser.utils

fun conditionalComma(hasMore: Boolean): String {
  return if (hasMore) {
    ","
  } else {
    ""
  }
}

fun getClosingBraces(hasMore: Boolean): String {
  return "}${conditionalComma(hasMore)}"
}

fun getRootText(rootKey: String): String {
  return if (rootKey.isNotEmpty()) {
    "\"${rootKey}\": {"
  } else {
    "{"
  }
}
