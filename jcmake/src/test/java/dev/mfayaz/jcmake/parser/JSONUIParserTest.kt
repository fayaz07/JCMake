package dev.mfayaz.jcmake.parser

import dev.mfayaz.jcmake.data.TestData.simpleForm1
import dev.mfayaz.jcmake.exceptions.InvalidJSONException
import dev.mfayaz.jcmake.exceptions.JSONParseException
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.Before
import org.junit.Test

class JSONUIParserTest {

  private lateinit var simpleJsonParser: JSONUIParser
  @Before
  fun setup() {
    simpleJsonParser = JSONUIParser(simpleForm1)
  }

  @Test
  fun `when empty string is passed should throw exception`() {
    shouldThrow<InvalidJSONException> {
      JSONUIParser("")
    }
  }

  @Test
  fun `when empty json string is passed should throw exception`() {
    shouldThrow<InvalidJSONException> {
      JSONUIParser("{}")
    }
  }

  @Test
  fun `when invalid json string is passed should throw exception`() {
    shouldThrow<JSONParseException> {
      JSONUIParser("{a=b, a:b}")
    }
  }

  @Test
  fun `when passed a valid json, no exceptions thrown`() {
    shouldNotThrowAny {
      JSONUIParser(simpleForm1)
    }
  }

  @Test
  fun `when passed a key to typeOf which holds a String value should return String field type`() {
    simpleJsonParser.typeOf("name") shouldBe FieldType.String
  }

  @Test
  fun `when passed a key to typeOf which holds a Char value should return String field type`() {
    simpleJsonParser.typeOf("grade") shouldBe FieldType.String
  }

  @Test
  fun `when passed a key to typeOf which holds a Boolean value should return Boolean field type`() {
    simpleJsonParser.typeOf("active") shouldBe FieldType.Boolean
  }

  @Test
  fun `when passed a key to typeOf which holds a Int value should return Long field type`() {
    simpleJsonParser.typeOf("age") shouldBe FieldType.Long
  }

  @Test
  fun `when passed a key to typeOf which holds a Long value should return Long field type`() {
    simpleJsonParser.typeOf("phone") shouldBe FieldType.Long
  }

  @Test
  fun `when passed a key to typeOf which holds a Float value should return Float field type`() {
    simpleJsonParser.typeOf("weight") shouldBe FieldType.Double
  }

  @Test
  fun `when passed a key to typeOf which holds an Object should return Object field type`() {
    simpleJsonParser.typeOf("password") shouldBe FieldType.TextFieldObject
  }
}
