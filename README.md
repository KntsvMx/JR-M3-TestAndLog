# TestAndLog Project

This project involves extending an existing Java application for simulating horse races by adding robust testing and logging capabilities. The solution ensures high code quality with comprehensive test coverage and detailed logs for debugging and analysis. Also use 
GitHub Actions for checking the completion of the test, logging information and build process.

## Key Enhancements

### Testing
- **JUnit 5**: Added unit and integration tests for the following classes:
    1. **Horse**:
        - Constructor validation (null, blank strings, negative speed/distance).
        - Methods: `getName`, `getSpeed`, `getDistance`, `move`.
    2. **Hippodrome**:
        - Constructor validation (null or empty horse list).
        - Methods: `getHorses`, `move`, `getWinner`.
    3. **Main**:
        - Execution time test with `@Timeout` (disabled by default).

- **Parameterized Tests**: Used for scenarios requiring multiple inputs (e.g., different whitespace values in Horse constructor).
- **Mocking**: Leveraged `MockedStatic` for mocking static methods such as `getRandomDouble`.

### Logging
- **Log4j2 with SLF4J**: Implemented structured logging with YAML configuration.
    - Logs written to `logs/hippodrome.log` with daily rolling and retention of up to 7 days.
    - Log levels:
        - **INFO**: Main application flow (e.g., race start/end, winner).
        - **ERROR**: Constructor validation failures.
        - **DEBUG**: Object creation details.

- **Log Structure Examples**:
    - `INFO Main: Race started with 7 participants.`
    - `INFO Main: Race ended. Winner: Cherry.`
    - `ERROR Hippodrome: Horses list is null.`
    - `DEBUG Horse: Created Horse, name [Lobster], speed [2.8].`

## Configuration Highlights
- **RollingFile Appender**:
    - Log file format: `hippodrome.<date>.log`.
    - Rotation policy: Daily.
    - Retention policy: Deletes files older than 7 days.
      - Example YAML snippet:
        ```yaml
        Configuration:
          status: all
          appenders:
            RollingFile:
              name: RollingFile
              fileName: logs/hippodrome.log
              filePattern: logs/hippodrome.%d{yyyy-MM-dd}.log
            PatternLayout:
                pattern: "%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"
            Policies:
                TimeBasedTriggeringPolicy:
                  interval: 1
                  modulate: true
            DefaultRolloverStrategy:
                Delete:
                  basePath: logs
                  ifLastModified: 7d
                  ifFileName: "hippodrome-*.log"
        ```

## Final Output
1. **Unit Tests**: All specified tests implemented and passing, ensuring validation and method functionality.
2. **Logging**: Logs provide a clear and detailed trace of application behavior, aiding in debugging and monitoring.
3. **Reusable Configuration**: Log4j2 YAML configuration ensures maintainability and scalability for future enhancements.

## How to Build and Run

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd hippodrome
   ```

2. Install dependencies and build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   java -jar target/hippodrome.jar
   ```

4. View logs:
    - Current logs: `logs/hippodrome.log`
    - Archived logs: `logs/hippodrome.<date>.log`

---
This README outlines the added functionality, ensuring the project meets all specified requirements for testing and logging. Feel free to suggest further refinements!

