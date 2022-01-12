# problem-spring-web-example

_Provides an example implementation of the  [Problem Spring Web](https://github.com/zalando/problem-spring-web)_

Problem Spring Web is a library that provides an implementation of [RFC4708: Problem Details for HTTP APIs](https://datatracker.ietf.org/doc/html/rfc7807). 

## Features
* `BasicThrowableTrait` transforms an `Exception` to a Problem. Optionally allowing the _detail_ of a Problem to be generic. Useful to not expose (possible) API internals.
* `ApiErrorTrait` transforms an `ApiException` to a Problem.
* `ApiException` to have fine-grained control on which HTTP status and title to use for a Problem.
* `DefaultProblemHandler` to delegate `ApiException` to `ApiErrorTrait`, otherwise `BasicThrowableTrait`.