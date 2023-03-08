package dev.mfayaz.jcmake.exceptions

class JSONParseException(json: String) : JCMakeException("Unable to parse json${json}")
