package dev.mfayaz.jcmake.data

object TestData {
  const val simpleForm1 =
    "{\"name\":\"sarah\",\"age\":24,\"phone\":9123456789,\"weight\":60.3,\"grade\":\"A\",\"active\":true,\"password\":{\"value\":\"password\",\"error\":\"password doesn't meet required criteria\"},\"rollNumber\":{\"value\":83748},\"bmi\":{\"value\":23.4}}"

  const val nestedJsonLevel2 =
    "{\"name\":\"sarah\",\"age\":24,\"rollNumber\":{\"value\":\"A123\"},\"marks\":{\"cgpa\":4.7}}"
}
