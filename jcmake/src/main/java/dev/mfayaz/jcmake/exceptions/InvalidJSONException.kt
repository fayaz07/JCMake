package dev.mfayaz.jcmake.exceptions

class InvalidJSONException(json: String) : JCMakeException("Invalid JSON Exception\n${json}")
