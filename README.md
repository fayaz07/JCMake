# JCMake

This library takes JSON as input and produces Jetpack Compose's Composables as output.

## Development Environment

![Android Studio Version](https://img.shields.io/badge/android--studio-giraffee--c9-success) ![Gradle Version](https://img.shields.io/badge/gradle-v8.0-blue) ![Kotlin Version](https://img.shields.io/badge/kotlin-v1.7.20-blue) ![AGP](https://img.shields.io/badge/agp-v8.1.0-alpha09-blue)

## Supported Composables

- TextFields
- TextFields with Custom error message
- Switches
- Nested JSON Support
- Pretty JSON

## Examples and Demo

### Demo app 
User will get updates whenever any field is updated, alternatively they can pull latest updates too. 
The `Send` button in the demo screenshot will do that.
<img src="screenshots/demo.png" width="33%" />

#### Simple JSON with nested Key-Value and Error values
```json
{
  "name": "sarah",
  "age": 24,
  "phone": 9123456789,
  "weight": 60.3,
  "grade": "A",
  "active": true,
  "password": {
    "value": "password",
    "error": "password doesn't meet required criteria"
  },
  "rollNumber": {
    "value": 83748
  },
  "bmi": {
    "value": 23.4
  }
}
```

#### Output
<img src="screenshots/s1.png" width="33%" />

#### Nested Level - 2
```json
{
  "name": "sarah",
  "age": 24,
  "rollNumber": {
    "value": "A123"
  },
  "marks": {
    "cgpa": 4.7
   }
}
```

#### Output
<img src="screenshots/nested_2.png" width="33%" />

#### Nested Level - 3
```json
{
  "name": "sarah",
  "age": 24,
  "rollNumber": {
    "value": "A123"
  },
  "marks": {
    "cgpa": 4.7,
    "semester": {
      "sem-1-1": 4.8,
      "sem-1-2": 4.6
    }
  }
}
```

#### Output
<img src="screenshots/nested_3.png" width="33%" />

#### Nested Level - 4
```json
{
  "name": "sarah",
  "age": 24,
  "rollNumber": {
    "value": "A123"
  },
  "marks": {
    "cgpa": 4.7,
    "semester": {
      "sem-1-1": 4.8,
      "sub-1-1": {
        "sub-a": 4.7,
        "sub-b": 4.8
      },
      "sem-1-2": 4.6,
      "sub-1-2": {
        "sub-c": 4.7,
        "sub-d": 4.8
      }
    }
  }
}
```

#### Output
<img src="screenshots/nested_4.png" width="33%" />

#### Print JSON to UI
<img src="screenshots/pretty_json_1.png" width="33%" />