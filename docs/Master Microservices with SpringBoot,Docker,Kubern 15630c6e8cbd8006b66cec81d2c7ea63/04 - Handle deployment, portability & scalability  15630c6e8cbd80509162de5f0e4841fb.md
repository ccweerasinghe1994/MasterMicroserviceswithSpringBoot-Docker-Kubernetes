# 04 - Handle deployment, portability & scalability of microservices using Docker

## **001 Introduction to challenges while building, deploying microservices ✅**

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image.png)

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%201.png)

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%202.png)

## **002 What are Containers & how they are different from VMs ✅**

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%203.png)

## **003 Definition of Containers, Containerization, Docker ✅**

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%204.png)

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%205.png)

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%206.png)

## **004 Introduction to Docker components & its architecture ✅**

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%207.png)

## **005 Docker installation & docker hub introduction ✅**

[Docker Desktop: The #1 Containerization Tool for Developers | Docker](https://www.docker.com/products/docker-desktop/)

## **006 Introduction to the three approaches for Docker image generation ✅**

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%208.png)

## **007 Generate Docker Image of Accounts microservice with Dockerfile - Part 1 ✅**

```powershell
.\mvnw clean install
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------< com.cgnexus:accounts >------------------------
[INFO] Building accounts 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- clean:3.4.1:clean (default-clean) @ accounts ---
[INFO] Deleting C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\accounts\target
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ accounts ---
[INFO] Copying 2 resources from src\main\resources to target\classes
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ accounts ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 22 source files with javac [debug parameters release 21] to target\classes
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ accounts ---
[INFO] skip non existing resourceDirectory C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\accounts\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ accounts ---
[INFO] Recompiling the module because of changed dependency.
[INFO] Compiling 1 source file with javac [debug parameters release 21] to target\test-classes
[INFO]
[INFO] --- surefire:3.5.2:test (default-test) @ accounts ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.cgnexus.accounts.AccountsApplicationTests
12:00:55.716 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [com.cgnexus.accounts.AccountsApplicationTests]: AccountsApplicationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
12:00:55.834 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration com.cgnexus.accounts.AccountsApplication for test class com.cgnexus.accounts.AccountsApplicationTests
12:00:56.015 [main] INFO org.springframework.boot.devtools.restart.RestartApplicationListener -- Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.4.3)

12:00:56.338 INFO  [main] c.c.a.AccountsApplicationTests - Starting AccountsApplicationTests using Java 21.0.5 with PID 30256 (started by chama in C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\accounts)
12:00:56.339 INFO  [main] c.c.a.AccountsApplicationTests - No active profile set, falling back to 1 default profile: "default"
12:00:57.580 INFO  [main] o.s.d.r.c.RepositoryConfigurationDelegate - Bootstrapping Spring Data JPA repositories in DEFAULT mode.
12:00:57.639 INFO  [main] o.s.d.r.c.RepositoryConfigurationDelegate - Finished Spring Data repository scanning in 50 ms. Found 2 JPA repository interfaces.
12:00:58.405 INFO  [main] c.z.h.HikariDataSource - HikariPool-1 - Starting...
12:00:58.583 INFO  [main] c.z.h.p.HikariPool - HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:d69c1131-1d91-44c9-80e7-72a0eb4ecdc7 user=SA
12:00:58.585 INFO  [main] c.z.h.HikariDataSource - HikariPool-1 - Start completed.
12:00:58.735 INFO  [main] o.h.j.i.u.LogHelper - HHH000204: Processing PersistenceUnitInfo [name: default]
12:00:58.814 INFO  [main] o.h.Version - HHH000412: Hibernate ORM core version 6.6.8.Final
12:00:58.869 INFO  [main] o.h.c.i.RegionFactoryInitiator - HHH000026: Second-level cache disabled
12:00:59.193 INFO  [main] o.s.o.j.p.SpringPersistenceUnitInfo - No LoadTimeWeaver setup: ignoring JPA class transformer
12:00:59.270 INFO  [main] o.h.o.c.pooling - HHH10001005: Database info:
        Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']
        Database driver: undefined/unknown
        Database version: 2.3.232
        Autocommit mode: undefined/unknown
        Isolation level: undefined/unknown
        Minimum pool size: undefined/unknown
        Maximum pool size: undefined/unknown
12:00:59.964 INFO  [main] o.h.e.t.j.p.i.JtaPlatformInitiator - HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
12:00:59.986 INFO  [main] o.s.o.j.LocalContainerEntityManagerFactoryBean - Initialized JPA EntityManagerFactory for persistence unit 'default'
12:01:00.919 WARN  [main] o.s.b.a.o.j.JpaBaseConfiguration$JpaWebConfiguration - spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
12:01:02.026 INFO  [main] o.s.b.a.e.w.EndpointLinksResolver - Exposing 1 endpoint beneath base path '/actuator'
12:01:02.095 INFO  [main] c.c.a.AccountsApplicationTests - Started AccountsApplicationTests in 6.093 seconds (process running for 7.088)
Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build what is described in Mockito's documentation: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#0.3
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
WARNING: A Java agent has been loaded dynamically (C:\Users\chama\.m2\repository\net\bytebuddy\byte-buddy-agent\1.15.11\byte-buddy-agent-1.15.11.jar)
WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning
WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information
WARNING: Dynamic loading of agents will be disallowed by default in a future release
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 7.039 s -- in com.cgnexus.accounts.AccountsApplicationTests
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- jar:3.4.2:jar (default-jar) @ accounts ---
[INFO] Building jar: C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\accounts\target\accounts-0.0.1-SNAPSHOT.jar
[INFO]
[INFO] --- spring-boot:3.4.3:repackage (repackage) @ accounts ---
[INFO] Replacing main artifact C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\accounts\target\accounts-0.0.1-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\accounts\target\accounts-0.0.1-SNAPSHOT.jar.original
[INFO]
[INFO] --- install:3.1.3:install (default-install) @ accounts ---
[INFO] Installing C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\accounts\pom.xml to C:\Users\chama\.m2\repository\com\cgnexus\accounts\0.0.1-SNAPSHOT\accounts-0.0.1-SNAPSHOT.pom
[INFO] Installing C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\accounts\target\accounts-0.0.1-SNAPSHOT.jar to C:\Users\chama\.m2\repository\com\cgnexus\accounts\0.0.1-SNAPSHOT\accounts-0.0.1-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  13.732 s
[INFO] Finished at: 2025-03-13T12:01:04+05:30
[INFO] ------------------------------------------------------------------------
```

```powershell
Get-ChildItem *.jar

    Directory: C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\accounts\target

Mode                 LastWriteTime         Length Name
----                 -------------         ------ ----
-a----         3/13/2025  12:01 PM       61993511 accounts-0.0.1-SNAPSHOT.jar

```

```powershell
.\mvnw spring-boot:run
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------< com.cgnexus:accounts >------------------------
[INFO] Building accounts 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> spring-boot:3.4.3:run (default-cli) > test-compile @ accounts >>>
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ accounts ---
[INFO] Copying 2 resources from src\main\resources to target\classes
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ accounts ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ accounts ---
[INFO] skip non existing resourceDirectory C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\accounts\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ accounts ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] <<< spring-boot:3.4.3:run (default-cli) < test-compile @ accounts <<<
[INFO]
[INFO]
[INFO] --- spring-boot:3.4.3:run (default-cli) @ accounts ---
[INFO] Attaching agents: []

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.4.3)

12:06:28.132 INFO  [restartedMain] c.c.a.AccountsApplication - Starting AccountsApplication using Java 21.0.5 with PID 15868 (C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\accounts\target\classes started by chama in C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\accounts)
12:06:28.134 INFO  [restartedMain] c.c.a.AccountsApplication - No active profile set, falling back to 1 default profile: "default"
12:06:28.181 INFO  [restartedMain] o.s.b.d.e.DevToolsPropertyDefaultsPostProcessor - Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
12:06:28.181 INFO  [restartedMain] o.s.b.d.e.DevToolsPropertyDefaultsPostProcessor - For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
12:06:28.933 INFO  [restartedMain] o.s.d.r.c.RepositoryConfigurationDelegate - Bootstrapping Spring Data JPA repositories in DEFAULT mode.
12:06:28.979 INFO  [restartedMain] o.s.d.r.c.RepositoryConfigurationDelegate - Finished Spring Data repository scanning in 38 ms. Found 2 JPA repository interfaces.
12:06:29.575 INFO  [restartedMain] o.s.b.w.e.t.TomcatWebServer - Tomcat initialized with port 8082 (http)
12:06:29.585 INFO  [restartedMain] o.a.c.c.StandardService - Starting service [Tomcat]
12:06:29.586 INFO  [restartedMain] o.a.c.c.StandardEngine - Starting Servlet engine: [Apache Tomcat/10.1.36]
12:06:29.627 INFO  [restartedMain] o.a.c.c.C.[.[.[/] - Initializing Spring embedded WebApplicationContext
12:06:29.628 INFO  [restartedMain] o.s.b.w.s.c.ServletWebServerApplicationContext - Root WebApplicationContext: initialization completed in 1446 ms
12:06:29.807 INFO  [restartedMain] c.z.h.HikariDataSource - HikariPool-1 - Starting...
12:06:29.929 INFO  [restartedMain] c.z.h.p.HikariPool - HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:88ee57b2-d714-485d-b713-d332c0e81614 user=SA
12:06:29.932 INFO  [restartedMain] c.z.h.HikariDataSource - HikariPool-1 - Start completed.
12:06:30.021 INFO  [restartedMain] o.h.j.i.u.LogHelper - HHH000204: Processing PersistenceUnitInfo [name: default]
12:06:30.058 INFO  [restartedMain] o.h.Version - HHH000412: Hibernate ORM core version 6.6.8.Final
12:06:30.081 INFO  [restartedMain] o.h.c.i.RegionFactoryInitiator - HHH000026: Second-level cache disabled
12:06:30.278 INFO  [restartedMain] o.s.o.j.p.SpringPersistenceUnitInfo - No LoadTimeWeaver setup: ignoring JPA class transformer
12:06:30.338 INFO  [restartedMain] o.h.o.c.pooling - HHH10001005: Database info:
        Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']
        Database driver: undefined/unknown
        Database version: 2.3.232
        Autocommit mode: undefined/unknown
        Isolation level: undefined/unknown
        Minimum pool size: undefined/unknown
        Maximum pool size: undefined/unknown
12:06:30.934 INFO  [restartedMain] o.h.e.t.j.p.i.JtaPlatformInitiator - HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
12:06:30.957 INFO  [restartedMain] o.s.o.j.LocalContainerEntityManagerFactoryBean - Initialized JPA EntityManagerFactory for persistence unit 'default'
12:06:31.405 WARN  [restartedMain] o.s.b.a.o.j.JpaBaseConfiguration$JpaWebConfiguration - spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
12:06:31.942 INFO  [restartedMain] o.s.b.a.h.H2ConsoleAutoConfiguration - H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:88ee57b2-d714-485d-b713-d332c0e81614'
12:06:31.994 INFO  [restartedMain] o.s.b.d.a.OptionalLiveReloadServer - LiveReload server is running on port 35729
12:06:32.006 INFO  [restartedMain] o.s.b.a.e.w.EndpointLinksResolver - Exposing 1 endpoint beneath base path '/actuator'
12:06:32.079 INFO  [restartedMain] o.s.b.w.e.t.TomcatWebServer - Tomcat started on port 8082 (http) with context path '/'
12:06:32.089 INFO  [restartedMain] c.c.a.AccountsApplication - Started AccountsApplication in 4.255 seconds (process running for 4.546)
12:07:12.475 INFO  [SpringApplicationShutdownHook] o.s.b.w.e.t.GracefulShutdown - Commencing graceful shutdown. Waiting for active requests to complete
12:07:12.489 INFO  [tomcat-shutdown] o.s.b.w.e.t.GracefulShutdown - Graceful shutdown complete
12:07:12.493 INFO  [SpringApplicationShutdownHook] o.s.o.j.LocalContainerEntityManagerFactoryBean - Closing JPA EntityManagerFactory for persistence unit 'default'
12:07:12.497 WARN  [SpringApplicationShutdownHook] o.s.b.f.s.DisposableBeanAdapter - Invocation of destroy method failed on bean with name 'inMemoryDatabaseShutdownExecutor': org.h2.jdbc.JdbcSQLNonTransientConnectionException: Database is already closed (to disable automatic closing at VM shutdown, add ";DB_CLOSE_ON_EXIT=FALSE" to the db URL) [90121-232]
12:07:12.497 INFO  [SpringApplicationShutdownHook] c.z.h.HikariDataSource - HikariPool-1 - Shutdown initiated...
12:07:12.499 INFO  [SpringApplicationShutdownHook] c.z.h.HikariDataSource - HikariPool-1 - Shutdown completed.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  46.817 s
[INFO] Finished at: 2025-03-13T12:07:12+05:30
[INFO] ------------------------------------------------------------------------
Terminate batch job (Y/N)?
^C
```

```powershell
java -jar target\accounts-0.0.1-SNAPSHOT.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.4.3)

12:07:33.086 INFO  [main] c.c.a.AccountsApplication - Starting AccountsApplication v0.0.1-SNAPSHOT using Java 21.0.5 with PID 31388 (C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\accounts\target\accounts-0.0.1-SNAPSHOT.jar started by chama in C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\accounts)
12:07:33.089 INFO  [main] c.c.a.AccountsApplication - No active profile set, falling back to 1 default profile: "default"
12:07:34.377 INFO  [main] o.s.d.r.c.RepositoryConfigurationDelegate - Bootstrapping Spring Data JPA repositories in DEFAULT mode.
12:07:34.423 INFO  [main] o.s.d.r.c.RepositoryConfigurationDelegate - Finished Spring Data repository scanning in 38 ms. Found 2 JPA repository interfaces.
12:07:35.544 INFO  [main] o.s.b.w.e.t.TomcatWebServer - Tomcat initialized with port 8082 (http)
12:07:35.561 INFO  [main] o.a.c.c.StandardService - Starting service [Tomcat]
12:07:35.562 INFO  [main] o.a.c.c.StandardEngine - Starting Servlet engine: [Apache Tomcat/10.1.36]
12:07:35.598 INFO  [main] o.a.c.c.C.[.[.[/] - Initializing Spring embedded WebApplicationContext
12:07:35.599 INFO  [main] o.s.b.w.s.c.ServletWebServerApplicationContext - Root WebApplicationContext: initialization completed in 2439 ms
12:07:35.909 INFO  [main] c.z.h.HikariDataSource - HikariPool-1 - Starting...
12:07:36.140 INFO  [main] c.z.h.p.HikariPool - HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:5b277076-990d-4665-9bb8-08a7b1ebf229 user=SA
12:07:36.142 INFO  [main] c.z.h.HikariDataSource - HikariPool-1 - Start completed.
12:07:36.376 INFO  [main] o.h.j.i.u.LogHelper - HHH000204: Processing PersistenceUnitInfo [name: default]
12:07:36.533 INFO  [main] o.h.Version - HHH000412: Hibernate ORM core version 6.6.8.Final
12:07:36.580 INFO  [main] o.h.c.i.RegionFactoryInitiator - HHH000026: Second-level cache disabled
12:07:36.816 INFO  [main] o.s.o.j.p.SpringPersistenceUnitInfo - No LoadTimeWeaver setup: ignoring JPA class transformer
12:07:36.873 INFO  [main] o.h.o.c.pooling - HHH10001005: Database info:
        Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']
        Database driver: undefined/unknown
        Database version: 2.3.232
        Autocommit mode: undefined/unknown
        Isolation level: undefined/unknown
        Minimum pool size: undefined/unknown
        Maximum pool size: undefined/unknown
12:07:37.583 INFO  [main] o.h.e.t.j.p.i.JtaPlatformInitiator - HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
12:07:37.604 INFO  [main] o.s.o.j.LocalContainerEntityManagerFactoryBean - Initialized JPA EntityManagerFactory for persistence unit 'default'
12:07:38.253 WARN  [main] o.s.b.a.o.j.JpaBaseConfiguration$JpaWebConfiguration - spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
12:07:39.392 INFO  [main] o.s.b.a.e.w.EndpointLinksResolver - Exposing 1 endpoint beneath base path '/actuator'
12:07:39.516 INFO  [main] o.s.b.w.e.t.TomcatWebServer - Tomcat started on port 8082 (http) with context path '/'
12:07:39.528 INFO  [main] c.c.a.AccountsApplication - Started AccountsApplication in 6.918 seconds (process running for 7.485)
12:08:41.404 INFO  [SpringApplicationShutdownHook] o.s.b.w.e.t.GracefulShutdown - Commencing graceful shutdown. Waiting for active requests to complete
12:08:41.416 INFO  [tomcat-shutdown] o.s.b.w.e.t.GracefulShutdown - Graceful shutdown complete
12:08:41.422 INFO  [SpringApplicationShutdownHook] o.s.o.j.LocalContainerEntityManagerFactoryBean - Closing JPA EntityManagerFactory for persistence unit 'default'
12:08:41.425 INFO  [SpringApplicationShutdownHook] c.z.h.HikariDataSource - HikariPool-1 - Shutdown initiated...
12:08:41.427 INFO  [SpringApplicationShutdownHook] c.z.h.HikariDataSource - HikariPool-1 - Shutdown completed.
```

## **008 Generate Docker Image of Accounts microservice with Dockerfile - Part 2 ✅**

```docker
FROM openjdk:21-jdk-slim

LABEL "org.opencontainers.image.authors"="cgnexus.com"

# Add the application's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]
```

## **009 Generate Docker Image of Accounts microservice with Dockerfile - Part 3**

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%209.png)

```powershell
docker build -t magises/accounts:section4 .

[+] Building 3.7s (8/8) FINISHED                                                                                                                                                            docker:desktop-linux
 => [internal] load build definition from Dockerfile                                                                                                                                                        0.0s
 => => transferring dockerfile: 500B                                                                                                                                                                        0.0s
 => [internal] load metadata for docker.io/library/openjdk:21-jdk-slim                                                                                                                                      1.8s
 => [auth] library/openjdk:pull token for registry-1.docker.io                                                                                                                                              0.0s
 => [internal] load .dockerignore                                                                                                                                                                           0.0s
 => => transferring context: 620B                                                                                                                                                                           0.0s
 => [internal] load build context                                                                                                                                                                           1.6s
 => => transferring context: 62.01MB                                                                                                                                                                        1.6s
 => CACHED [1/2] FROM docker.io/library/openjdk:21-jdk-slim@sha256:7072053847a8a05d7f3a14ebc778a90b38c50ce7e8f199382128a53385160688                                                                         0.0s
 => [2/2] COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar                                                                                                                               0.1s
 => exporting to image                                                                                                                                                                                      0.1s
 => => exporting layers                                                                                                                                                                                     0.1s
 => => writing image sha256:d4de3a235890e6f6fd3a410721c59b4bb1d3416c8f7b8f6eda992209f9a193fa                                                                                                                0.0s
 => => naming to docker.io/magises/accounts:section4                                                                                                                                                        0.0s

View build details: docker-desktop://dashboard/build/desktop-linux/desktop-linux/i6pykl9zrry5s2xp3cr9031dv

What's next:
    View a summary of image vulnerabilities and recommendations → docker scout quickview
```

```powershell
docker image inspect  magises/accounts:section4
[
    {
        "Id": "sha256:d4de3a235890e6f6fd3a410721c59b4bb1d3416c8f7b8f6eda992209f9a193fa",
        "RepoTags": [
            "magises/accounts:section4"
        ],
        "RepoDigests": [],
        "Parent": "",
        "Comment": "buildkit.dockerfile.v0",
        "Created": "2025-03-13T08:06:36.331034149Z",
        "DockerVersion": "",
        "Author": "",
        "Config": {
            "Hostname": "",
            "Domainname": "",
            "User": "",
            "AttachStdin": false,
            "AttachStdout": false,
            "AttachStderr": false,
            "Tty": false,
            "OpenStdin": false,
            "StdinOnce": false,
            "Env": [
                "PATH=/usr/local/openjdk-21/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin",
                "JAVA_HOME=/usr/local/openjdk-21",
                "LANG=C.UTF-8",
                "JAVA_VERSION=21"
            ],
            "Cmd": null,
            "Image": "",
            "Volumes": null,
            "WorkingDir": "",
            "Entrypoint": [
                "java",
                "-jar",
                "accounts-0.0.1-SNAPSHOT.jar"
            ],
            "OnBuild": null,
            "Labels": {
                "org.opencontainers.image.authors": "eazybytes.com"
            }
        },
        "Architecture": "amd64",
        "Os": "linux",
        "Size": 500878941,
        "GraphDriver": {
            "Data": {
                "LowerDir": "/var/lib/docker/overlay2/9df98098b9c46f21cc2f0a791ca58acf139825d64d84fc57d0787fbf28ffbf2a/diff:/var/lib/docker/overlay2/ab97577536931b22fbdc10ea9a8a8cecb5bb36d557dafe0b6a1624c082e3149e/diff:/var/lib/docker/overlay2/ae9258e18089d4705dc84d595e796287d67b92aa5383879d1c8ff62e53fb1189/diff",
                "MergedDir": "/var/lib/docker/overlay2/ml4312s4mugf053ptmyp25ogn/merged",
                "UpperDir": "/var/lib/docker/overlay2/ml4312s4mugf053ptmyp25ogn/diff",
                "WorkDir": "/var/lib/docker/overlay2/ml4312s4mugf053ptmyp25ogn/work"
            },
            "Name": "overlay2"
        },
        "RootFS": {
            "Type": "layers",
            "Layers": [
                "sha256:d310e774110ab038b30c6a5f7b7f7dd527dbe527854496bd30194b9ee6ea496e",
                "sha256:0ac7ecf8a41c6c682f3194a046f3be4db6950df016790c9dec3fb429dec54ec6",
                "sha256:659a8c4ba776d3d4d1006ee50db0eb93133ea3acddf224bd9036e965510e698f",
                "sha256:8a128b518d474cb9d6f549955f45c1adf34b838d08785545268a5caedacf65a3"
            ]
        },
        "Metadata": {
            "LastTagTime": "2025-03-13T08:06:36.45443926Z"
        }
    }
]
```

## **010 Running accounts microservice as a Docker container**

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%2010.png)

```powershell
docker container run -d -p 8082:8082 magises/accounts:section4
9c25026d24106bb575b3a1bd6c6de4de799e472f906c6848644e6c66f55d50e1
```

```powershell
docker container ls
CONTAINER ID   IMAGE                       COMMAND                  CREATED          STATUS                 PORTS                    NAMES
9c25026d2410   magises/accounts:section4   "java -jar accounts-…"   46 seconds ago   Up 45 seconds          0.0.0.0:8082->8082/tcp   fervent_wiles
0c173a383510   postgres:17.2               "docker-entrypoint.s…"   7 hours ago      Up 7 hours (healthy)   0.0.0.0:5432->5432/tcp   postgres-db
```

## **011 Challenges with Dockerfile approach to generate a Docker image**

## **012 Generate Docker Image of Loans microservice with Buildpacks**

[Cloud Native Buildpacks](https://buildpacks.io/)

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%2011.png)

```powershell
~\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\loans git:[main]
.\mvnw spring-boot:build-image
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------< com.eazybytes:loans >-------------------------
[INFO] Building loans 0.0.1
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> spring-boot:3.4.3:build-image (default-cli) > package @ loans >>>
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ loans ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ loans ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ loans ---
[INFO] skip non existing resourceDirectory C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\loans\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ loans ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- surefire:3.5.2:test (default-test) @ loans ---
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.eazybytes.loans.LoansApplicationTests
16:18:57.264 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [com.eazybytes.loans.LoansApplicationTests]: LoansApplicationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
16:18:57.378 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration com.eazybytes.loans.LoansApplication for test class com.eazybytes.loans.LoansApplicationTests
16:18:57.530 [main] INFO org.springframework.boot.devtools.restart.RestartApplicationListener -- Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.4.3)

2025-03-13T16:18:57.837+05:30  INFO 29160 --- [           main] c.eazybytes.loans.LoansApplicationTests  : Starting LoansApplicationTests using Java 21.0.5 with PID 29160 (started by chama in C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\loans)
2025-03-13T16:18:57.839+05:30  INFO 29160 --- [           main] c.eazybytes.loans.LoansApplicationTests  : No active profile set, falling back to 1 default profile: "default"
2025-03-13T16:18:59.065+05:30  INFO 29160 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2025-03-13T16:18:59.119+05:30  INFO 29160 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 45 ms. Found 1 JPA repository interface.
2025-03-13T16:18:59.934+05:30  INFO 29160 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2025-03-13T16:19:00.130+05:30  INFO 29160 --- [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:testdb user=SA
2025-03-13T16:19:00.132+05:30  INFO 29160 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2025-03-13T16:19:00.298+05:30  INFO 29160 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2025-03-13T16:19:00.365+05:30  INFO 29160 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.6.8.Final
2025-03-13T16:19:00.408+05:30  INFO 29160 --- [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2025-03-13T16:19:00.669+05:30  INFO 29160 --- [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2025-03-13T16:19:00.701+05:30  WARN 29160 --- [           main] org.hibernate.orm.deprecation            : HHH90000025: H2Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
2025-03-13T16:19:00.713+05:30  INFO 29160 --- [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
        Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']
        Database driver: undefined/unknown
        Database version: 2.3.232
        Autocommit mode: undefined/unknown
        Isolation level: undefined/unknown
        Minimum pool size: undefined/unknown
        Maximum pool size: undefined/unknown
2025-03-13T16:19:01.384+05:30  INFO 29160 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Hibernate: alter table if exists loans alter column loan_id set data type bigint
Hibernate: alter table if exists loans alter column created_at set data type timestamp(6)
Hibernate: alter table if exists loans alter column created_by set data type varchar(255)
Hibernate: alter table if exists loans alter column updated_at set data type timestamp(6)
Hibernate: alter table if exists loans alter column updated_by set data type varchar(255)
Hibernate: alter table if exists loans alter column loan_number set data type varchar(255)
Hibernate: alter table if exists loans alter column loan_type set data type varchar(255)
Hibernate: alter table if exists loans alter column mobile_number set data type varchar(255)
2025-03-13T16:19:01.437+05:30  INFO 29160 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2025-03-13T16:19:02.378+05:30  WARN 29160 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2025-03-13T16:19:03.510+05:30  INFO 29160 --- [           main] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:testdb'
2025-03-13T16:19:03.549+05:30  INFO 29160 --- [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 1 endpoint beneath base path '/actuator'
2025-03-13T16:19:03.653+05:30  INFO 29160 --- [           main] c.eazybytes.loans.LoansApplicationTests  : Started LoansApplicationTests in 6.135 seconds (process running for 7.025)
Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build what is described in Mockito's documentation: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#0.3
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
WARNING: A Java agent has been loaded dynamically (C:\Users\chama\.m2\repository\net\bytebuddy\byte-buddy-agent\1.15.11\byte-buddy-agent-1.15.11.jar)
WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning
WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information
WARNING: Dynamic loading of agents will be disallowed by default in a future release
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 7.032 s -- in com.eazybytes.loans.LoansApplicationTests
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- jar:3.4.2:jar (default-jar) @ loans ---
[INFO] Building jar: C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\loans\target\loans-0.0.1.jar
[INFO]
[INFO] --- spring-boot:3.4.3:repackage (repackage) @ loans ---
[INFO] Replacing main artifact C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\loans\target\loans-0.0.1.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to C:\Users\chama\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\loans\target\loans-0.0.1.jar.original
[INFO]
[INFO] <<< spring-boot:3.4.3:build-image (default-cli) < package @ loans <<<
[INFO]
[INFO]
[INFO] --- spring-boot:3.4.3:build-image (default-cli) @ loans ---
[INFO] Building image 'docker.io/magises/loans:0.0.1'
[INFO]
[INFO]  > Pulling builder image 'docker.io/paketobuildpacks/builder-jammy-java-tiny:latest' 1%
[INFO]  > Pulling builder image 'docker.io/paketobuildpacks/builder-jammy-java-tiny:latest' 3%
[INFO]  > Pulling builder image 'docker.io/paketobuildpacks/builder-jammy-java-tiny:latest' 5%
[INFO]  > Pulling builder image 'docker.io/paketobuildpacks/builder-jammy-java-tiny:latest' 7%
[INFO]  > Pulling builder image 'docker.io/paketobuildpacks/builder-jammy-java-tiny:latest' 10%
[INFO]  > Pulling builder image 'docker.io/paketobuildpacks/builder-jammy-java-tiny:latest' 13%
[INFO]  > Pulling builder image 'docker.io/paketobuildpacks/builder-jammy-java-tiny:latest' 20%
[INFO]  > Pulling builder image 'docker.io/paketobuildpacks/builder-jammy-java-tiny:latest' 57%
[INFO]  > Pulling builder image 'docker.io/paketobuildpacks/builder-jammy-java-tiny:latest' 66%
[INFO]  > Pulling builder image 'docker.io/paketobuildpacks/builder-jammy-java-tiny:latest' 78%
[INFO]  > Pulling builder image 'docker.io/paketobuildpacks/builder-jammy-java-tiny:latest' 100%
[INFO]  > Pulled builder image 'paketobuildpacks/builder-jammy-java-tiny@sha256:13144ab1719ebe32977e653961900c62debf72b08e5090fa5da293c17eb3de17'
[INFO]  > Pulling run image 'docker.io/paketobuildpacks/run-jammy-tiny:latest' for platform 'linux/amd64' 100%
[INFO]  > Pulled run image 'paketobuildpacks/run-jammy-tiny@sha256:0c5ac79d549c4b077a7d857631f817f8b573f5da2c109a51f320ee584d44d3f2'
[INFO]  > Executing lifecycle version v0.20.6
[INFO]  > Using build cache volume 'pack-cache-c9d885610bdb.build'
[INFO]
[INFO]  > Running creator
[INFO]     [creator]     ===> ANALYZING
[INFO]     [creator]     Image with name "docker.io/magises/loans:0.0.1" not found
[INFO]     [creator]     ===> DETECTING
[INFO]     [creator]     target distro name/version labels not found, reading /etc/os-release file
[INFO]     [creator]     6 of 26 buildpacks participating
[INFO]     [creator]     paketo-buildpacks/ca-certificates   3.9.1
[INFO]     [creator]     paketo-buildpacks/bellsoft-liberica 11.0.5
[INFO]     [creator]     paketo-buildpacks/syft              2.8.0
[INFO]     [creator]     paketo-buildpacks/executable-jar    6.12.1
[INFO]     [creator]     paketo-buildpacks/dist-zip          5.9.1
[INFO]     [creator]     paketo-buildpacks/spring-boot       5.32.1
[INFO]     [creator]     ===> RESTORING
[INFO]     [creator]     ===> BUILDING
[INFO]     [creator]     target distro name/version labels not found, reading /etc/os-release file
[INFO]     [creator]
[INFO]     [creator]     Paketo Buildpack for CA Certificates 3.9.1
[INFO]     [creator]       https://github.com/paketo-buildpacks/ca-certificates
[INFO]     [creator]       Build Configuration:
[INFO]     [creator]         $BP_EMBED_CERTS                    false  Embed certificates into the image
[INFO]     [creator]         $BP_ENABLE_RUNTIME_CERT_BINDING    true   Deprecated: Enable/disable certificate helper layer to add certs at runtime
[INFO]     [creator]         $BP_RUNTIME_CERT_BINDING_DISABLED  false  Disable certificate helper layer to add certs at runtime
[INFO]     [creator]       Launch Helper: Contributing to layer
[INFO]     [creator]         Creating /layers/paketo-buildpacks_ca-certificates/helper/exec.d/ca-certificates-helper
[INFO]     [creator]
[INFO]     [creator]     Paketo Buildpack for BellSoft Liberica 11.0.5
[INFO]     [creator]       https://github.com/paketo-buildpacks/bellsoft-liberica
[INFO]     [creator]       Build Configuration:
[INFO]     [creator]         $BP_JVM_JLINK_ARGS           --no-man-pages --no-header-files --strip-debug --compress=1  configure custom link arguments (--output must be omitted)
[INFO]     [creator]         $BP_JVM_JLINK_ENABLED        false                                                        enables running jlink tool to generate custom JRE
[INFO]     [creator]         $BP_JVM_TYPE                 JRE                                                          the JVM type - JDK or JRE
[INFO]     [creator]         $BP_JVM_VERSION              21                                                           the Java version
[INFO]     [creator]       Launch Configuration:
[INFO]     [creator]         $BPL_DEBUG_ENABLED           false                                                        enables Java remote debugging support
[INFO]     [creator]         $BPL_DEBUG_PORT              8000                                                         configure the remote debugging port
[INFO]     [creator]         $BPL_DEBUG_SUSPEND           false                                                        configure whether to suspend execution until a debugger has attached
[INFO]     [creator]         $BPL_HEAP_DUMP_PATH                                                                       write heap dumps on error to this path
[INFO]     [creator]         $BPL_JAVA_NMT_ENABLED        true                                                         enables Java Native Memory Tracking (NMT)
[INFO]     [creator]         $BPL_JAVA_NMT_LEVEL          summary                                                      configure level of NMT, summary or detail
[INFO]     [creator]         $BPL_JFR_ARGS                                                                             configure custom Java Flight Recording (JFR) arguments
[INFO]     [creator]         $BPL_JFR_ENABLED             false                                                        enables Java Flight Recording (JFR)
[INFO]     [creator]         $BPL_JMX_ENABLED             false                                                        enables Java Management Extensions (JMX)
[INFO]     [creator]         $BPL_JMX_PORT                5000                                                         configure the JMX port
[INFO]     [creator]         $BPL_JVM_HEAD_ROOM           0                                                            the headroom in memory calculation
[INFO]     [creator]         $BPL_JVM_LOADED_CLASS_COUNT  35% of classes                                               the number of loaded classes in memory calculation
[INFO]     [creator]         $BPL_JVM_THREAD_COUNT        250                                                          the number of threads in memory calculation
[INFO]     [creator]         $JAVA_TOOL_OPTIONS                                                                        the JVM launch flags
[INFO]     [creator]         Using Java version 21 extracted from MANIFEST.MF
[INFO]     [creator]       BellSoft Liberica JRE 21.0.6: Contributing to layer
[INFO]     [creator]         Downloading from https://github.com/bell-sw/Liberica/releases/download/21.0.6+10/bellsoft-jre21.0.6+10-linux-amd64.tar.gz
[INFO]     [creator]         Verifying checksum
[INFO]     [creator]         Expanding to /layers/paketo-buildpacks_bellsoft-liberica/jre
[INFO]     [creator]         Adding 146 container CA certificates to JVM truststore
[INFO]     [creator]         Writing env.launch/BPI_APPLICATION_PATH.default
[INFO]     [creator]         Writing env.launch/BPI_JVM_CACERTS.default
[INFO]     [creator]         Writing env.launch/BPI_JVM_CLASS_COUNT.default
[INFO]     [creator]         Writing env.launch/BPI_JVM_SECURITY_PROVIDERS.default
[INFO]     [creator]         Writing env.launch/JAVA_HOME.default
[INFO]     [creator]         Writing env.launch/JAVA_TOOL_OPTIONS.append
[INFO]     [creator]         Writing env.launch/JAVA_TOOL_OPTIONS.delim
[INFO]     [creator]         Writing env.launch/MALLOC_ARENA_MAX.default
[INFO]     [creator]       Launch Helper: Contributing to layer
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/java-opts
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/jvm-heap
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/link-local-dns
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/memory-calculator
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/security-providers-configurer
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/jmx
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/jfr
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/openssl-certificate-loader
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/security-providers-classpath-9
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/debug-9
[INFO]     [creator]         Creating /layers/paketo-buildpacks_bellsoft-liberica/helper/exec.d/nmt
[INFO]     [creator]       Java Security Properties: Contributing to layer
[INFO]     [creator]         Writing env.launch/JAVA_SECURITY_PROPERTIES.default
[INFO]     [creator]         Writing env.launch/JAVA_TOOL_OPTIONS.append
[INFO]     [creator]         Writing env.launch/JAVA_TOOL_OPTIONS.delim
[INFO]     [creator]
[INFO]     [creator]     Paketo Buildpack for Syft 2.8.0
[INFO]     [creator]       https://github.com/paketo-buildpacks/syft
[INFO]     [creator]         Downloading from https://github.com/anchore/syft/releases/download/v1.20.0/syft_1.20.0_linux_amd64.tar.gz
[INFO]     [creator]         Verifying checksum
[INFO]     [creator]         Writing env.build/SYFT_CHECK_FOR_APP_UPDATE.default
[INFO]     [creator]
[INFO]     [creator]     Paketo Buildpack for Executable JAR 6.12.1
[INFO]     [creator]       https://github.com/paketo-buildpacks/executable-jar
[INFO]     [creator]       Class Path: Contributing to layer
[INFO]     [creator]         Writing env/CLASSPATH.delim
[INFO]     [creator]         Writing env/CLASSPATH.prepend
[INFO]     [creator]       Process types:
[INFO]     [creator]         executable-jar: java org.springframework.boot.loader.launch.JarLauncher (direct)
[INFO]     [creator]         task:           java org.springframework.boot.loader.launch.JarLauncher (direct)
[INFO]     [creator]         web:            java org.springframework.boot.loader.launch.JarLauncher (direct)
[INFO]     [creator]
[INFO]     [creator]     Paketo Buildpack for Spring Boot 5.32.1
[INFO]     [creator]       https://github.com/paketo-buildpacks/spring-boot
[INFO]     [creator]       Build Configuration:
[INFO]     [creator]         $BPL_JVM_CDS_ENABLED                 false  whether to enable CDS optimizations at runtime
[INFO]     [creator]         $BPL_SPRING_AOT_ENABLED              false  whether to enable Spring AOT at runtime
[INFO]     [creator]         $BP_JVM_CDS_ENABLED                  false  whether to enable CDS & perform JVM training run
[INFO]     [creator]         $BP_SPRING_AOT_ENABLED               false  whether to enable Spring AOT
[INFO]     [creator]         $BP_SPRING_CLOUD_BINDINGS_DISABLED   false  whether to contribute Spring Boot cloud bindings support
[INFO]     [creator]         $BP_SPRING_CLOUD_BINDINGS_VERSION    1      default version of Spring Cloud Bindings library to contribute
[INFO]     [creator]       Launch Configuration:
[INFO]     [creator]         $BPL_SPRING_CLOUD_BINDINGS_DISABLED  false  whether to auto-configure Spring Boot environment properties from bindings
[INFO]     [creator]         $BPL_SPRING_CLOUD_BINDINGS_ENABLED   true   Deprecated - whether to auto-configure Spring Boot environment properties from bindings
[INFO]     [creator]       Creating slices from layers index
[INFO]     [creator]         dependencies (60.8 MB)
[INFO]     [creator]         spring-boot-loader (459.3 KB)
[INFO]     [creator]         snapshot-dependencies (0.0 B)
[INFO]     [creator]         application (126.2 KB)
[INFO]     [creator]       Spring Cloud Bindings 2.0.4: Contributing to layer
[INFO]     [creator]         Downloading from https://repo1.maven.org/maven2/org/springframework/cloud/spring-cloud-bindings/2.0.4/spring-cloud-bindings-2.0.4.jar
[INFO]     [creator]         Verifying checksum
[INFO]     [creator]         Copying to /layers/paketo-buildpacks_spring-boot/spring-cloud-bindings
[INFO]     [creator]       Web Application Type: Contributing to layer
[INFO]     [creator]         Servlet web application detected
[INFO]     [creator]         Writing env.launch/BPL_JVM_THREAD_COUNT.default
[INFO]     [creator]       Launch Helper: Contributing to layer
[INFO]     [creator]         Creating /layers/paketo-buildpacks_spring-boot/helper/exec.d/spring-cloud-bindings
[INFO]     [creator]       4 application slices
[INFO]     [creator]       Image labels:
[INFO]     [creator]         org.opencontainers.image.title
[INFO]     [creator]         org.opencontainers.image.version
[INFO]     [creator]         org.springframework.boot.version
[INFO]     [creator]     ===> EXPORTING
[INFO]     [creator]     Adding layer 'paketo-buildpacks/ca-certificates:helper'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/bellsoft-liberica:helper'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/bellsoft-liberica:java-security-properties'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/bellsoft-liberica:jre'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/executable-jar:classpath'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/spring-boot:helper'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/spring-boot:spring-cloud-bindings'
[INFO]     [creator]     Adding layer 'paketo-buildpacks/spring-boot:web-application-type'
[INFO]     [creator]     Adding layer 'buildpacksio/lifecycle:launch.sbom'
[INFO]     [creator]     Added 5/5 app layer(s)
[INFO]     [creator]     Adding layer 'buildpacksio/lifecycle:launcher'
[INFO]     [creator]     Adding layer 'buildpacksio/lifecycle:config'
[INFO]     [creator]     Adding layer 'buildpacksio/lifecycle:process-types'
[INFO]     [creator]     Adding label 'io.buildpacks.lifecycle.metadata'
[INFO]     [creator]     Adding label 'io.buildpacks.build.metadata'
[INFO]     [creator]     Adding label 'io.buildpacks.project.metadata'
[INFO]     [creator]     Adding label 'org.opencontainers.image.title'
[INFO]     [creator]     Adding label 'org.opencontainers.image.version'
[INFO]     [creator]     Adding label 'org.springframework.boot.version'
[INFO]     [creator]     Setting default process type 'web'
[INFO]     [creator]     Saving docker.io/magises/loans:0.0.1...
[INFO]     [creator]     *** Images (85c59950aef2):
[INFO]     [creator]           docker.io/magises/loans:0.0.1
[INFO]     [creator]     Adding cache layer 'paketo-buildpacks/syft:syft'
[INFO]     [creator]     Adding cache layer 'paketo-buildpacks/spring-boot:spring-cloud-bindings'
[INFO]     [creator]     Adding cache layer 'buildpacksio/lifecycle:cache.sbom'
[INFO]
[INFO] Successfully built image 'docker.io/magises/loans:0.0.1'
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  01:06 min
[INFO] Finished at: 2025-03-13T16:20:01+05:30
[INFO] ------------------------------------------------------------------------
```

## **013 Generate Docker Image of Cards microservice with Google Jib**

```powershell
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.3</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.cgnexus</groupId>
    <artifactId>cards</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>cards</name>
    <description>cards microservice</description>
    <packaging>jar</packaging>
    <url/>
    <licenses>
        <license/>
    </licenses>
    <developers>
        <developer/>
    </developers>
    <scm>
        <connection/>
        <developerConnection/>
        <tag/>
        <url/>
    </scm>
    <properties>
        <java.version>21</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.8.5</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>com.google.cloud.tools</groupId>
                <artifactId>jib-maven-plugin</artifactId>
                <version>3.4.4</version>
                <configuration>
                    <from>
                        <image>eclipse-temurin:21-jre</image>
                        <auth>
                            <username>magises</username>
                            <password>dckr_pat_XXXXXXXXXXXXXXXXXXXX</password>
                        </auth>
                    </from>
                    <to>
                        <image>docker.io/magises/${project.artifactId}:${project.version}</image>
                        <auth>
                            <username>${docker.username}</username>
                            <password>${docker.password}</password>
                        </auth>
                    </to>

                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>

```

```powershell
spring:
  application:
    name: cards
server:
  port: 8081
  servlet:
    context-path: /api
logging:
  pattern:
    console: ${LOG_PATTERN_CONSOLE:%green(%d{HH:mm:ss.SSS}) %blue(%-5level) %red([%thread]) %yellow(%logger{15}) - %msg%n}
docker:
  username: ${DOCKER_USERNAME}
  password: ${DOCKER_PASSWORD}
```

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%2012.png)

## **014 Compare Dockerfile, Buildpacks, Jib approaches**

[Features · Cloud Native Buildpacks](https://buildpacks.io/features/)

## **015 Pushing Docker images from your local to remote Docker hub repository**

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%2013.png)

```powershell
docker image push magises/loans:0.0.1
The push refers to repository [docker.io/magises/loans]
1dc94a70dbaa: Pushed
e43997f07112: Pushed
cd1584485537: Pushed
5f70bf18a086: Mounted from paketobuildpacks/builder-jammy-java-tiny
1af0d9771d22: Pushed
09173eaeddc8: Pushed
4a494b39bfae: Pushed
9bda140c58d2: Pushed
366ce7d1a7f9: Pushed
f0e9078fd509: Pushed
bce90ec363c9: Pushed
417e5bfc3c82: Pushed
62b50afe2649: Pushed
bea0a3dc2651: Pushed
2dc59b4c39ea: Pushed
c09460372b3b: Pushed
3ad1a70f7e4f: Mounted from paketobuildpacks/run-jammy-tiny
23458f0da9ee: Mounted from paketobuildpacks/run-jammy-tiny
39dea5268285: Mounted from paketobuildpacks/run-jammy-tiny
0.0.1: digest: sha256:0f0fbc4e91bcea95cfea1f0ca4642ea161ab41e252aea142f748ae8858b85452 size: 4496
```

```powershell
docker image push magises/accounts:section4
The push refers to repository [docker.io/magises/accounts]
8a128b518d47: Pushed
659a8c4ba776: Mounted from library/openjdk
0ac7ecf8a41c: Mounted from library/openjdk
d310e774110a: Mounted from library/openjdk
section4: digest: sha256:a83f884a7758efa49668dfb6378a4963a29164ed5c0af4104c70417a69ad5808 size: 1165
```

## **016 Introduction to Docker Compose**

```yaml
services:
  accounts:
    image: magises/accounts:section4
    container_name: accounts-ms
    ports:
      - "8082:8082"
    deploy:
      resources:
        limits:
          memory: 700m
    networks:
      - cgnexus-ms
  cards:
    image: magises/cards:0.0.1-SNAPSHOT
    container_name: cards-ms
    ports:
      - "8081:8081"
    deploy:
      resources:
        limits:
          memory: 700m
    networks:
      - cgnexus-ms
  loans:
    image: magises/loans:0.0.1
    container_name: loans-ms
    ports:
      - "8090:8090"
    deploy:
      resources:
        limits:
          memory: 700m
    networks:
      - cgnexus-ms

networks:
  cgnexus-ms:
    driver: bridge
```

## **017 Running all microservice containers using Docker Compose command**

```powershell
docker compose up -d

[+] Running 3/3
 ✔ Container loans-ms     Started                                                                                                                                                                           0.4s
 ✔ Container accounts-ms  Started                                                                                                                                                                           0.2s
 ✔ Container cards-ms     Started                                                                                                                                                                           0.4s
```

```powershell
docker container ls
CONTAINER ID   IMAGE                          COMMAND                  CREATED         STATUS         PORTS                    NAMES
ca7bc7ffa69d   magises/accounts:section4      "java -jar accounts-…"   2 minutes ago   Up 5 seconds   0.0.0.0:8082->8082/tcp   accounts-ms
c3735c236ae5   magises/cards:0.0.1-SNAPSHOT   "java -cp @/app/jib-…"   2 minutes ago   Up 5 seconds   0.0.0.0:8081->8081/tcp   cards-ms
c50699ffe5bb   magises/loans:0.0.1            "java -cp @/app/jib-…"   2 minutes ago   Up 5 seconds   0.0.0.0:8090->8090/tcp   loans-ms
```

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%2014.png)

```powershell
docker compose down

[+] Running 4/4
 ✔ Container accounts-ms                                                   Removed                                                                                                                          0.8s
 ✔ Container loans-ms                                                      Removed                                                                                                                          1.5s
 ✔ Container cards-ms                                                      Removed                                                                                                                          1.3s
 ✔ Network mastermicroserviceswithspringboot-docker-kubernetes_cgnexus-ms  Removed                                                                                                                          0.7s
```

## **018 Demo of docker compose commands**

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%2015.png)

## **019 Deep dive on Docker commands**

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%2016.png)

![image.png](04%20-%20Handle%20deployment,%20portability%20&%20scalability%20%2015630c6e8cbd80509162de5f0e4841fb/image%2017.png)

## **020 Introduction to Docker extensions and LogsExplorer**

## **021 Funny memes of Docker**
