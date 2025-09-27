# Spring Security JSON Errors

Custom `AuthenticationEntryPoint` and `AccessDeniedHandler` for Spring Security to return **JSON error responses** (401/403).

## Overview

By default, Spring Security returns either an HTML error page or a plain text message when authentication or authorization fails.  
This project demonstrates how to replace those defaults with clean **JSON error responses**, making your API more consistent and client-friendly.

## Features

- `401 Unauthorized` → handled by a custom `AuthenticationEntryPoint`  
- `403 Forbidden` → handled by a custom `AccessDeniedHandler`  
- JSON responses include status and timestamp  
- Can be easily extended with extra fields (e.g., `path`, `traceId`)

## Example Error Response

```json
{
  "status": "401 UNAUTHORIZED",
  "timestamp": "2025-09-27T09:41:21.124Z"
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
Then try calling a protected endpoint without authentication:
```
curl -u anny@gmail.com:1234 http://localhost:8080/auth/user
```
You’ll get a next JSON error response:
```json
{
  "status": "401 UNAUTHORIZED",
  "timestamp": "2025-09-27T09:41:21.124Z"
}
```
Then try calling a protected endpoint without authorization:
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
