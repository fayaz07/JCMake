package dev.mfayaz.jcmakedemo.data

object TestData {
  const val simpleForm1 =
    "{\"name\":\"sarah\",\"age\":24,\"phone\":9123456789,\"weight\":60.3,\"grade\":\"A\",\"active\":true,\"password\":{\"value\":\"password\",\"error\":\"password doesn't meet required criteria\"},\"rollNumber\":{\"value\":83748},\"bmi\":{\"value\":23.4}}"

  const val nestedJsonLevel2 =
    "{\"name\":\"sarah\",\"age\":24,\"rollNumber\":{\"value\":\"A123\"},\"marks\":{\"cgpa\":4.7}}"

  const val nestedJsonLevel2WithTFObject =
    "{\"name\":\"sarah\",\"age\":24,\"rollNumber\":{\"value\":\"A123\"},\"marks\":{\"cgpa\":{\"value\":4.7}}}"

  const val nestedJsonLevel3 =
    "{\"name\":\"sarah\",\"age\":24,\"rollNumber\":{\"value\":\"A123\"},\"marks\":{\"cgpa\":4.7,\"semester\":{\"sem-1-1\":4.8,\"sem-1-2\":4.6}}}"

  const val nestedJsonLevel4 =
    "{\"name\":\"sarah\",\"age\":24,\"rollNumber\":{\"value\":\"A123\"},\"marks\":{\"cgpa\":4.7,\"semester\":{\"sem-1-1\":4.8,\"sub-1-1\":{\"sub-a\":4.7,\"sub-b\":4.8},\"sem-1-2\":4.6,\"sub-1-2\":{\"sub-c\":4.7,\"sub-d\":4.8}}}}"
}
