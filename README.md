# Spring REST Security EntryPoint Handler (Bean)

Custom `AuthenticationEntryPoint` and `AccessDeniedHandler` implemented as Spring **beans** and wired into `SecurityConfig`.  Returns clean JSON error responses for **401 Unauthorized** and **403 Forbidden**.

---

## Overview

By default Spring Security may return an HTML error page or plain text when authentication or authorization fails. This repository demonstrates replacing those defaults with consistent JSON error responses produced by handlers declared as beans — a compact, DI-friendly approach suitable for larger Spring projects.  

Implementation difference vs other repos: handlers are provided as `@Bean` factory methods, rather than [implemented as separate @Component classes](https://github.com/Dmitrii-Russu-Labs-Snippets/spring-rest-security-entrypointHandler-component) or [defined inline as lambdas](https://github.com/Dmitrii-Russu-Labs-Snippets/spring-rest-security-entrypointHandler-lambda) in the `SecurityConfig`.

---

## Features

- **401 Unauthorized** → handled by an `AuthenticationEntryPoint` bean  
- **403 Forbidden** → handled by an `AccessDeniedHandler` bean  
- JSON responses include `status` and `timestamp` (easy to extend with `path`, `message`, `traceId`)  
- Handlers are implemented as beans (factory methods / `@Bean`) and registered via `http.exceptionHandling(...)`  
- Easy to inject dependencies (e.g. `ObjectMapper`) and unit-test

---

## Example JSON responses

**401 Unauthorized** (Content-Type: `application/json`)
```json
{
  "status": 401,
  "timestamp": "2025-09-27T09:41:21.124Z"
}
```
**403 Forbidden** (Content-Type: `application/json`)
```json
{
  "status": 403,
  "timestamp": "2025-09-27T09:41:21.124Z"
}
```

Note: examples are minimal — in your project you can (and probably should) add message, path, and traceId for better observability.

---

## How to Run

```
./mvnw spring-boot:run
```

---

## Example curl

401 (no credentials)

```
curl -i http://localhost:8080/auth/user
```

401 (wrong credentials)

```
curl -i -u wrong:wrong http://localhost:8080/auth/user
```

403 (authenticated but not authorized)

```
curl -i -u ann:1234 http://localhost:8080/auth/admin
```

200 (authorized)

```
curl -i -u jack:123 http://localhost:8080/auth/admin
```

---

Related

- [spring-rest-security-entrypointHandler-component](https://github.com/Dmitrii-Russu-Labs-Snippets/spring-rest-security-entrypointHandler-component) — handlers as separate components  

- [spring-rest-security-entrypointHandler-lambda](https://github.com/Dmitrii-Russu-Labs-Snippets/spring-rest-security-entrypointHandler-lambda) — compact implementation using lambdas in SecurityConfig  
