package dev.mfayaz.jcmake.parser

fun String.parseDouble(): Double {
  return this.toDoubleOrNull() ?: 0.0
}

fun String.parseInt(): Int {
  return this.toIntOrNull() ?: 0
}

fun String.parseLong(): Long {
  return this.toLongOrNull() ?: 0
}
