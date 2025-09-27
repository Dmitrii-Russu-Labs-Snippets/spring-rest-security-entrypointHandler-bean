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
or
{
  "status": "403 FORBIDDEN",
  "timestamp": "2025-09-27T09:42:03.567Z"
}
