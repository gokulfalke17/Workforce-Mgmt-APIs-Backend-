**Railse Backend Assignment – Workforce Management API**
This is a basic Workforce Management API made with Java 17, Spring Boot, and Gradle. It uses in-memory storage (no database).
  -> Fixed a bug where reassigning a task created duplicates — now the old task gets cancelled
  -> Improved the task list for a date range — it now shows ongoing tasks too
  -> Added task priority (HIGH, MEDIUM, LOW)
  -> Created endpoints to update priority and view tasks by priority
  -> Added comments and activity history for each task, so you can track changes and discussions
