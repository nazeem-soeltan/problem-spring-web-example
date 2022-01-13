# problem-spring-web-example

_Provides an example implementation of the [Problem Spring Web](https://github.com/zalando/problem-spring-web)_

Problem Spring Web is a library that provides an implementation of [RFC4708: Problem Details for HTTP APIs](https://datatracker.ietf.org/doc/html/rfc7807). 

## Features

* `DefaultThrowableAdviceTrait` transforms an _Exception_ to a _Problem_. Optionally allowing the _detail_ of a _Problem_ to be generic. Useful to not expose (possible) API internals.
* `ApiErrorTrait` transforms an `ApiException` to a _Problem_.
* `ApiException` to have fine-grained control on which HTTP status and title to use for a _Problem_.
* `ApiErrorHandler` to delegate `ApiException` to `ApiErrorAdviceTrait`, otherwise `DefaultThrowableAdviceTrait`.

## Usage

Clone this repository

```shell
git clone git@github.com:nazeem-soeltan/problem-spring-web-example.git
```

Run the application. In the root directory of the repository

```shell
./mvnw spring-boot:run
```

Get exception keys list

```shell
curl --request GET \
  --url http://localhost:8080/api/exception-keys \
  --header 'X-Consumer-Authorities: ROLE_DEVELOPER' \
  --header 'X-Consumer-Username: nazeem'
```

Use key to throw exception

```shell
curl --request GET \
  --url http://localhost:8080/api/exception-keys/access-denied \
  --header 'X-Consumer-Authorities: ROLE_DEVELOPER' \
  --header 'X-Consumer-Username: nazeem'
```
