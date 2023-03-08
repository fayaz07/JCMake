package dev.mfayaz.jcmake.parser

import dev.mfayaz.jcmake.data.TestData.simpleForm1
import dev.mfayaz.jcmake.exceptions.InvalidJSONException
import dev.mfayaz.jcmake.exceptions.JSONParseException
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.Test

class JSONUIParserTest {
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
    val parser = JSONUIParser(simpleForm1)
    parser.typeOf("name") shouldBe FieldType.String
  }

  @Test
  fun `when passed a key to typeOf which holds a Char value should return String field type`() {
    val parser = JSONUIParser(simpleForm1)
    parser.typeOf("grade") shouldBe FieldType.String
  }

  @Test
  fun `when passed a key to typeOf which holds a Boolean value should return Boolean field type`() {
    val parser = JSONUIParser(simpleForm1)
    parser.typeOf("active") shouldBe FieldType.Boolean
  }

  @Test
  fun `when passed a key to typeOf which holds a Int value should return Long field type`() {
    val parser = JSONUIParser(simpleForm1)
    parser.typeOf("age") shouldBe FieldType.Long
  }

  @Test
  fun `when passed a key to typeOf which holds a Long value should return Long field type`() {
    val parser = JSONUIParser(simpleForm1)
    parser.typeOf("phone") shouldBe FieldType.Long
  }

  @Test
  fun `when passed a key to typeOf which holds a Float value should return Float field type`() {
    val parser = JSONUIParser(simpleForm1)
    parser.typeOf("weight") shouldBe FieldType.Double
  }
}
