# OpenAI Java API Client for Models

[![Build Status](https://travis-ci.com/pdkst/models.svg?branch=main)](https://travis-ci.com/pdkst/models)
[![codecov](https://codecov.io/gh/pdkst/models/branch/main/graph/badge.svg)](https://codecov.io/gh/pdkst/models)
[![Maven Central](https://img.shields.io/maven-central/v/com.pdkst.models/openai-java.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.pdkst.models%22%20AND%20a:%22openai-java%22)
[![Apache License 2.0](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/pdkst/models/blob/main/LICENSE)

This is a Java client for the OpenAI API, which allows you to access models through a simple and easy-to-use interface. The client supports both synchronous and asynchronous requests, and can be easily integrated into your Java projects.

## Installation

To use the OpenAI Java API Client in your project, add the following Maven dependency to your `pom.xml` file:

```xml
<dependency>
<groupId>io.github.pdkst.models</groupId>
<artifactId>models-openai</artifactId>
<version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Usage

To use the OpenAI Java API Client, you first need to create an instance of the `OpenAI` class, which provides methods for accessing the various OpenAI APIs. You can then call the desired method with the appropriate parameters to perform the desired action.

Here's an example of how to use the `OpenAI` class to generate text using the model:

```java
final OpenaiOptions options = new OpenaiOptions();
options.key("sk-**********");
OpenaiClient client = new OpenaiClient(options);
final CompletionRequest request = new CompletionRequest();
request.setModel("gpt-3.5-turbo");
request.setStream(false);
request.messages(Message.user("hello"));
final CompletionResponse response = client.chat().completion(request);
assertNotNull(response);
```

In this example, we create an instance of the `OpenAI` class with our API key, define a `CompletionRequest` object with the desired parameters, and use the `client.chat().completion()` method to generate text using the model. Finally, we print the generated text to the console.

For more information on the available methods and parameters, please refer to the [OpenAI API documentation](https://beta.openai.com/docs/).

## Contributing

If you encounter any issues or have any suggestions for improvements, please feel free to open an issue or submit a pull request.

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](https://github.com/pdkst/models/blob/main/LICENSE) file for more details.

