# Spring REST Security EntryPoint Handler (Bean)

This project demonstrates custom Spring Security error handling using **Spring beans**.

## Overview

By default, Spring Security returns either an HTML error page or a plain text message when authentication or authorization fails.  
This project demonstrates how to replace those defaults with clean **JSON error responses**, making your API more consistent and client-friendly.

## Features
- 401 Unauthorized → handled by AuthenticationEntryPoint bean
- 403 Forbidden → handled by AccessDeniedHandler bean
- JSON responses include `status` and `timestamp`
- Easy to integrate in larger Spring projects

## Example Error Response

```json
{
  "status": "403 FORBIDDEN",
  "timestamp": "2025-09-27T09:42:03.567Z",
  "path": "/auth/admin"
}
```

## Tech Stack

Java 25

Spring Boot 3+

Spring Security 6+

## How to Run
```
./mvnw spring-boot:run
```

`Test endpoints:`

Call a protected endpoint without authentication:
```
curl http://localhost:8080/auth/user
```
You’ll get a next JSON error response:
```json
{
  "status": "401 UNAUTHORIZED",
  "timestamp": "2025-09-27T09:41:21.124Z"
}
```
Call a protected endpoint without required role:
```
curl -u ann@gmail.com:1234 http://localhost:8080/auth/admin
```
You’ll get a next JSON error response:
```json
{
  "status": "403 FORBIDDEN",
  "timestamp": "2025-09-27T09:42:03.567Z"
}
```

This project is a minimal reference implementation.

You can copy and adapt the AuthenticationEntryPoint and AccessDeniedHandler into any Spring Boot REST API project.
