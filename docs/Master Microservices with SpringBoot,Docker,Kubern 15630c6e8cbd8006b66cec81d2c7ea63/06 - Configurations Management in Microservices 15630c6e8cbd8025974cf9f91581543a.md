# 06 - Configurations Management in Microservices

## Introduction to Configurations Management challenges inside microservices ✅

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image.png)

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%201.png)

## How Configurations work in Spring Boot ✅

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%202.png)

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%203.png)

## Reading configurations using @Value annotation ✅

```powershell

build:
  version: "1.0.0"
```

```powershell
package com.cgnexus.accounts.controller;

@Tag(
        name = "CRUD REST APIs for Accounts",
        description = "Create, Read, Update and Delete account details"
)
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class AccountsController {
    

    @Value("${build.version}")
    private String buildVersion;
    
    @GetMapping("/build-version")
    public ResponseEntity<String> getBuildVersion() {
        return ResponseEntity.ok(buildVersion);
    }
}

```

```powershell
~\OneDrive\Documents\MYCODING_PROJECTS_2025\SELF_STUDY_PEOJECTS\Microservices\MasterMicroserviceswithSpringBoot-Docker-Kubernetes\loans git:[main]
Invoke-WebRequest -Uri "http://localhost:8082/api/build-version" -Method Get -Headers @{
    "User-Agent" = "IntelliJ HTTP Client/IntelliJ IDEA 2024.3.4.1"
    "Accept-Encoding" = "br, deflate, gzip, x-gzip"
    "Accept" = "*/*"
}

StatusCode        : 200                                                                                                                                                                                                                                          
StatusDescription :                                                                                                                                                                                                                                              
Content           : 1.0.0                                                                                                                                                                                                                                        
RawContent        : HTTP/1.1 200                                                                                                                                                                                                                                 
                    Keep-Alive: timeout=60
                    Connection: keep-alive
                    Content-Length: 5
                    Content-Type: application/json
                    Date: Fri, 14 Mar 2025 09:15:51 GMT
                    
                    1.0.0
Forms             : {}
Headers           : {[Keep-Alive, timeout=60], [Connection, keep-alive], [Content-Length, 5], [Content-Type, application/json]...}
Images            : {}
InputFields       : {}
Links             : {}
ParsedHtml        : mshtml.HTMLDocumentClass
RawContentLength  : 5
```

## Reading configurations using Environment interface ✅

```java
package com.cgnexus.accounts.controller;

import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.dto.ErrorResponseDTO;
import com.cgnexus.accounts.dto.ResponseDTO;
import com.cgnexus.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.cgnexus.accounts.constants.AccountConstants.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Tag(
        name = "CRUD REST APIs for Accounts",
        description = "Create, Read, Update and Delete account details"
)
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class AccountsController {
    private final IAccountService accountService;
    
    private final Environment environment;
    
    @Value("${build.version}")
    private String buildVersion;

    @Operation(
            summary = "Get Java Version REST API",
            description = "REST API to get the Java version"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR"
            )
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.ok(environment.getProperty("JAVA_HOME"));
    }
}

```

## Reading configurations using @ConfigurationProperties ✅

```java
build:
  version: "1.0.0"

accounts:
  message: "Hello from accounts service"
  contactDetails:
    email: "cgnexus@gmail.com"
    name: "CG Nexus"
  onCallSupport:
    - (555) 555-5555
    - (666) 666-6666
```

```java
package com.cgnexus.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
public record AccountContactInfoDTO(String message, Map<String, String> contactDetails, List<String> onCallSupport) {

}

```

```java
package com.cgnexus.accounts;

import com.cgnexus.accounts.dto.AccountContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountContactInfoDTO.class})
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}

```

```java
package com.cgnexus.accounts.controller;

import com.cgnexus.accounts.dto.AccountContactInfoDTO;
import com.cgnexus.accounts.dto.CustomerDTO;
import com.cgnexus.accounts.dto.ErrorResponseDTO;
import com.cgnexus.accounts.dto.ResponseDTO;
import com.cgnexus.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.cgnexus.accounts.constants.AccountConstants.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Tag(
        name = "CRUD REST APIs for Accounts",
        description = "Create, Read, Update and Delete account details"
)
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class AccountsController {

    @Value("${build.version}")
    private String buildVersion;

    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfoDTO> getContactInformation() {
        return ResponseEntity.ok(accountContactInfoDTO);
    }
}

```

```powershell
Invoke-WebRequest http://localhost:8082/api/contact-info

StatusCode        : 200                                                                                                                                                                                          StatusDescription :                                                                                                                                                                                              Content           : {"message":"Hello from accounts service","contactDetails":{"email":"cgnexus@gmail.com","name":"CG Nexus"},"onCallSupport":["(555) 555-5555","(666) 666-6666"]}                               RawContent        : HTTP/1.1 200                                                                                                                                                                                 
                    Transfer-Encoding: chunked
                    Keep-Alive: timeout=60
                    Connection: keep-alive
                    Content-Type: application/json
                    Date: Fri, 14 Mar 2025 09:50:14 GMT

                    {"message":"Hello from accounts servic...
Forms             : {}
Headers           : {[Transfer-Encoding, chunked], [Keep-Alive, timeout=60], [Connection, keep-alive], [Content-Type, application/json]...}
Images            : {}
InputFields       : {}
Links             : {}
ParsedHtml        : mshtml.HTMLDocumentClass
RawContentLength  : 158

```

## Introduction to Spring Boot profiles ✅

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%204.png)

## Demo of Spring Boot profiles inside accounts microservice ✅

```yaml
spring:
  profiles:
    active: "qa"
  application:
    name: accounts
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: ''
  h2:
    console:
      enabled: true
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
  config:
    import:
      - "application_prod.yml"
      - "application_qa.yml"

server:
  port: 8082
logging:
  pattern:
    console: ${LOG_PATTERN_CONSOLE:%green(%d{HH:mm:ss.SSS}) %blue(%-5level) %red([%thread]) %yellow(%logger{15}) - %msg%n}

docker:
  username: ${DOCKER_USERNAME}
  password: ${DOCKER_PASSWORD}

build:
  version: "3.0.0"

accounts:
  message: "Hello from accounts service"
  contactDetails:
    email: "cgnexus@gmail.com"
    name: "CG Nexus"
  onCallSupport:
    - (555) 555-5555
    - (666) 666-6666
```

```yaml
spring:
  config:
    activate:
      on-profile: "prod"

build:
  version: "1.0.0"

accounts:
  message: "Hello from accounts service from prod"
  contactDetails:
    email: "cgnexus-prod@gmail.com"
    name: "CG Nexus Prod"
  onCallSupport:
    - (777) 777-7777
    - (888) 888-8888

```

```yaml
spring:
  config:
    activate:
      on-profile: "qa"

build:
  version: "2.0.0"

accounts:
  message: "Hello from accounts service from qa"
  contactDetails:
    email: "cgnexus-qa@gmail.com"
    name: "CG Nexus QA"
  onCallSupport:
    - (111) 111-1111
    - (222) 222-2222

```

## Activating the profile using command-line, JVM &  environment options ✅

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%205.png)

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%206.png)

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%207.png)

## Assignment to make SpringBoot profile changes inside loans & cards microservices ✅

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%208.png)

```java
 Invoke-WebRequest http://localhost:8082/api/build-version

StatusCode        : 200                                                                                                                                                                                          StatusDescription :                                                                                                                                                                                              Content           : 2.2.2                                                                                                                                                                                        RawContent        : HTTP/1.1 200                                                                                                                                                                                 
                    Keep-Alive: timeout=60
                    Connection: keep-alive
                    Content-Length: 5
                    Content-Type: application/json
                    Date: Fri, 14 Mar 2025 22:53:30 GMT

                    2.2.2
Forms             : {}
Headers           : {[Keep-Alive, timeout=60], [Connection, keep-alive], [Content-Length, 5], [Content-Type, application/json]...}
Images            : {}
InputFields       : {}
Links             : {}
ParsedHtml        : mshtml.HTMLDocumentClass
RawContentLength  : 5

```

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%209.png)

```java
 Invoke-WebRequest http://localhost:8082/api/build-version

StatusCode        : 200                                                                                                                                                                                          StatusDescription :                                                                                                                                                                                              Content           : 3.2.2                                                                                                                                                                                        RawContent        : HTTP/1.1 200                                                                                                                                                                                 
                    Content-Length: 5
                    Content-Type: application/json
                    Date: Fri, 14 Mar 2025 22:58:35 GMT

                    3.2.2
Forms             : {}
Headers           : {[Content-Length, 5], [Content-Type, application/json], [Date, Fri, 14 Mar 2025 22:58:35 GMT]}
Images            : {}
InputFields       : {}
Links             : {}
ParsedHtml        : mshtml.HTMLDocumentClass
RawContentLength  : 5

```

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2010.png)

```java
Invoke-WebRequest http://localhost:8082/api/build-version

StatusCode        : 200                                                                                                                                                                                          StatusDescription :                                                                                                                                                                                              Content           : 8.2.2                                                                                                                                                                                        RawContent        : HTTP/1.1 200                                                                                                                                                                                 
                    Content-Length: 5
                    Content-Type: application/json
                    Date: Fri, 14 Mar 2025 23:02:23 GMT

                    8.2.2
Forms             : {}
Headers           : {[Content-Length, 5], [Content-Type, application/json], [Date, Fri, 14 Mar 2025 23:02:23 GMT]}
Images            : {}
InputFields       : {}
Links             : {}
ParsedHtml        : mshtml.HTMLDocumentClass
RawContentLength  : 5

```

## Demo of Spring Boot profile changes inside loans & cards microservices ✅

do the same for the other services

## Drawbacks of externalized configurations using SpringBoot alone ✅

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2011.png)

## Introduction to Spring Cloud Config ✅

[Spring Cloud Config](https://spring.io/projects/spring-cloud-config)

Spring Cloud Config provides server and client-side support for externalized configuration in a distributed system. With the Config Server you have a central place to manage external properties for applications across all environments. The concepts on both client and server map identically to the Spring `Environment` and `PropertySource` abstractions, so they fit very well with Spring applications, but can be used with any application running in any language. As an application moves through the deployment pipeline from dev to test and into production you can manage the configuration between those environments and be certain that applications have everything they need to run when they migrate. The default implementation of the server storage backend uses git so it easily supports labelled versions of configuration environments, as well as being accessible to a wide range of tooling for managing the content. It is easy to add alternative implementations and plug them in with Spring configuration.

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2012.png)

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2013.png)

## Building Config Server using Spring Cloud Config ✅

[Spring Cloud Config :: Spring Cloud Config](https://docs.spring.io/spring-cloud-config/reference/)

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.4.3</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.cgnexus</groupId>
	<artifactId>configserver</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>configserver</name>
	<description>Config server for cgnexus microservices</description>
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
		<spring-cloud.version>2024.0.0</spring-cloud.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```

```java
package com.cgnexus.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigserverApplication.class, args);
    }

}

```

```java
spring:
  application:
    name: configserver

server:
  port: 8071

```

```java

```

## Updating Accounts Microservice to read properties from Config Server ✅

```java
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
    
        <properties>
        <java.version>21</java.version>
        <spring-cloud.version>2024.0.0</spring-cloud.version>
    </properties>
    
            <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
```

```java
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
    <artifactId>accounts</artifactId>
    <version>0.0.2</version>
    <name>accounts</name>
    <description>microservice for accounts</description>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
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
        <spring-cloud.version>2024.0.0</spring-cloud.version>
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
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
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
                            <username>${docker.username}</username>
                            <password>${docker.password}</password>
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
        </plugins>
    </build>

</project>

```

```java
server:
  port: 8080

spring:
  application:
    name: "accounts"
  profiles:
    active: "prod"
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: ''
  h2:
    console:
      enabled: true
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
  config:
    import: "configserver:http://localhost:8071/"

logging:
  pattern:
    console: ${LOG_PATTERN_CONSOLE:%green(%d{HH:mm:ss.SSS}) %blue(%-5level) %red([%thread]) %yellow(%logger{15}) - %msg%n}

docker:
  username: ${DOCKER_USERNAME}
  password: ${DOCKER_PASSWORD}

```

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2014.png)

## Updating Loans & Cards Microservice to read properties from Config Server ✅

do the same 

## Reading configurations from a file system location ✅

```java
spring:
  application:
    name: "configserver"
  profiles:
    active: native
  cloud:
    config:
      server:
        native:
          search-locations: "file:///C://Games//config"
server:
  port: 8071

```

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2015.png)

## Reading configurations from a GitHub repository ✅

```java
spring:
  application:
    name: "configserver"
  profiles:
    active: git
  cloud:
    config:
      server:
        git:
          uri: "https://github.com/ccweerasinghe1994/MasterMicroserviceswithSpringBoot-Docker-Kubernetes-cloud-config.git"
          default-label: "main"
          timeout: 5
          clone-on-start: true
          force-pull: true
server:
  port: 8071

```

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2016.png)

## Encryption & Decryption of properties inside Config Server ✅

```java
spring:
  application:
    name: "configserver"
  profiles:
    active: git
  cloud:
    config:
      server:
        git:
          uri: "https://github.com/ccweerasinghe1994/MasterMicroserviceswithSpringBoot-Docker-Kubernetes-cloud-config.git"
          default-label: "main"
          timeout: 5
          clone-on-start: true
          force-pull: true
server:
  port: 8071

encrypt:
  key: ${ENCRYPT_KEY:6FG74HR6T7DFG4GF97SRD4SSDF46S7FSF46SD74HT7D4}

```

```yaml
curl --location 'http://localhost:8071/encrypt' \
--header 'Content-Type: text/plain' \
--data-raw 'aishwarya@eazybank.com'

0adca1127a824797d9498e8d721842f854e6d546217426196d88e1a0c0587cbdc04831ede89f45856c9e216fc4b27eb4
```

```yaml
spring:
  application:
    name: "accounts"
build:
  version: "1.0.0"

accounts:
  message: "Hello from accounts service from prod"
  contactDetails:
    email: "{cipher}0adca1127a824797d9498e8d721842f854e6d546217426196d88e1a0c0587cbdc04831ede89f45856c9e216fc4b27eb4"
    name: "CG Nexus Prod"
  onCallSupport:
    - (777) 777-7777
    - (888) 888-8888

```

```yaml
curl --location 'http://localhost:8071/decrypt' \
--header 'Content-Type: text/plain' \
--data 'f83ac5a34ba5f4657ae8e863c063c1b69efb4d23d0c431eb134f8d1b3ed0823dee0a4b627d091455b4c6600353a3682c'
```

## Refresh configurations at runtime using refresh actuator path ✅

```java
package com.cgnexus.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class AccountContactInfoDTO {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}

```

```java
server:
  port: 8080

spring:
  application:
    name: "accounts"
  profiles:
    active: "prod"
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: ''
  h2:
    console:
      enabled: true
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
  config:
    import: "configserver:http://localhost:8071/"

logging:
  pattern:
    console: ${LOG_PATTERN_CONSOLE:%green(%d{HH:mm:ss.SSS}) %blue(%-5level) %red([%thread]) %yellow(%logger{15}) - %msg%n}

docker:
  username: ${DOCKER_USERNAME}
  password: ${DOCKER_PASSWORD}

management:
  endpoints:
    web:
      exposure:
        include: "*"
```

```java
curl --location --request POST 'http://localhost:8080/actuator/refresh' \
--data ''
```

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2017.png)

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2018.png)

do the same for loans and cards

## Refresh configurations at runtime using Spring Cloud Bus ✅

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2019.png)

Spring Cloud Bus links the nodes of a distributed system with a lightweight message broker. This broker can then be used to broadcast state changes (such as configuration changes) or other management instructions. A key idea is that the bus is like a distributed actuator for a Spring Boot application that is scaled out. However, it can also be used as a communication channel between apps. This project provides starters for either an AMQP broker or Kafka as the transport.

[Introduction :: Spring Cloud Bus](https://docs.spring.io/spring-cloud-bus/reference/)

Lets install rabitmq

[Installing RabbitMQ | RabbitMQ](https://www.rabbitmq.com/docs/download)

```yaml
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.0-management
```

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2020.png)

```java
  rabbitmq:
    host: "localhost"
    port: 5672
    username: "guest"
    password: "guest"
```

```java
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
            <version>4.2.0</version>
        </dependency>
```

## Refresh config at runtime using Spring Cloud Bus & Spring Cloud Config monitor ✅

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2021.png)

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2022.png)

create a web hook in the config github repo since u can’t use [localhost](http://localhost) for githooks.

we will use 

[Hookdeck - A reliable Event Gateway for event-driven applications](https://hookdeck.com/)

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2023.png)

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2024.png)

i use npm to install the cli

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2025.png)

then start the cli 

```powershell
hookdeck listen 8071 Source --cli-path /monitor
```

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2026.png)

then add this config to config server

```yaml
  rabbitmq:
    host: "localhost"
    port: 5672
    username: "guest"
    password: "guest"
```

and this dependency to config server

```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-monitor</artifactId>
        </dependency>
```

## Updating Docker Compose file to adapt Config Server changes - Part 1 ✅

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2027.png)

```yaml
services:
  configserver:
    image: magises/configserver:s6
    container_name: configserver-ms
    ports:
      - "8071:8071"
    deploy:
      resources:
        limits:
          memory: 700m
    networks:
      - cgnexus-ms
    env_file:
      - .env

  accounts:
    image: magises/accounts:s6
    container_name: accounts-ms
    ports:
      - "8080:8080"
    deploy:
      resources:
        limits:
          memory: 700m
    networks:
      - cgnexus-ms
    environment:
      - SPRING_APPLICATION_NAME:"accounts"
      - SPRING_CONFIG_IMPORT:"configserver:http://configserver:8071"
      - SPRING_PROFILES_ACTIVE:"default"
    env_file:
      - .env
  cards:
    image: magises/cards:s6
    container_name: cards-ms
    ports:
      - "9000:9000"
    deploy:
      resources:
        limits:
          memory: 700m
    networks:
      - cgnexus-ms
    environment:
      - SPRING_APPLICATION_NAME:"cards"
      - SPRING_CONFIG_IMPORT:"configserver:http://configserver:8071"
      - SPRING_PROFILES_ACTIVE:"default"
    env_file:
      - .env
  loans:
    image: magises/loans:s6
    container_name: loans-ms
    ports:
      - "8090:8090"
    deploy:
      resources:
        limits:
          memory: 700m
    networks:
      - cgnexus-ms
    environment:
      - SPRING_APPLICATION_NAME:loans"
      - SPRING_CONFIG_IMPORT:"configserver:http://configserver:8071"
      - SPRING_PROFILES_ACTIVE:"default"
    env_file:
      - .env
networks:
  cgnexus-ms:
    driver: bridge
```

## Introduction to Liveness and Readiness probes ✅

add these configuration to config server.

```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
  health:
    readinessstate:
      enabled: true
    livenessstate:
      enabled: true
  endpoint:
    health:
      probes:
        enabled: true
```

```powershell
Invoke-WebRequest http://localhost:8071/actuator/health

StatusCode        : 200
StatusDescription :
Content           : {123, 34, 115, 116...}
RawContent        : HTTP/1.1 200
                    Transfer-Encoding: chunked
                    Content-Type: application/vnd.spring-boot.actuator.v3+json
                    Date: Sun, 16 Mar 2025 04:11:39 GMT

                    {"status":"UP","groups":["liveness","readiness"]}
Headers           : {[Transfer-Encoding, chunked], [Content-Type, application/vnd.spring-boot.actuator.v3+json], [Date, Sun, 16 Mar 2025 04:11:39 GMT]}
RawContentLength  : 49

```

```powershell
Invoke-WebRequest http://localhost:8071/actuator/health/liveness

StatusCode        : 200                                                                                                                                                                                          StatusDescription :                                                                                                                                                                                              Content           : {123, 34, 115, 116...}                                                                                                                                                                       RawContent        : HTTP/1.1 200                                                                                                                                                                                 
                    Transfer-Encoding: chunked
                    Content-Type: application/vnd.spring-boot.actuator.v3+json
                    Date: Sun, 16 Mar 2025 04:12:47 GMT

                    {"status":"UP"}
Headers           : {[Transfer-Encoding, chunked], [Content-Type, application/vnd.spring-boot.actuator.v3+json], [Date, Sun, 16 Mar 2025 04:12:47 GMT]}
RawContentLength  : 15

```

```powershell
Invoke-WebRequest http://localhost:8071/actuator/health/readiness

StatusCode        : 200
StatusDescription :
Content           : {123, 34, 115, 116...}
RawContent        : HTTP/1.1 200
                    Transfer-Encoding: chunked
                    Content-Type: application/vnd.spring-boot.actuator.v3+json
                    Date: Sun, 16 Mar 2025 04:13:12 GMT

                    {"status":"UP"}
Headers           : {[Transfer-Encoding, chunked], [Content-Type, application/vnd.spring-boot.actuator.v3+json], [Date, Sun, 16 Mar 2025 04:13:12 GMT]}
RawContentLength  : 15

```

## Updating Docker Compose file to adapt Config Server changes - Part 2 ✅

```yaml
services:
  rabbitmq:
    image: rabbitmq:4.0-management
    container_name: rabbitmq
    hostname: rabbitmq
    ports:
      - "5672:5672"
      - "15672:15672"
    healthcheck:
      test: rabbitmq-diagnostics check_port_connectivity
      interval: 10s
      timeout: 5s
      retries: 10
      start_period: 5s
    networks:
      - cgnexus-ms
    deploy:
      resources:
        limits:
          memory: 700m

  configserver:
    image: magises/configserver:s6
    container_name: configserver-ms
    ports:
      - "8071:8071"
    healthcheck:
      test: "curl --fail http://localhost:8071/actuator/health/readiness | grep UP || exit 1" # check if the server is up
      interval: 10s # check every 10 seconds
      timeout: 5s # wait 5 seconds before considering the check to have failed
      retries: 10 # retry 10 times before giving up
      start_period: 10s # wait for 10 seconds before running the first check
    deploy:
      resources:
        limits:
          memory: 700m
    networks:
      - cgnexus-ms
    env_file:
      - .env
    depends_on:
      rabbitmq:
        condition: service_healthy

  accounts:
    image: magises/accounts:s6
    container_name: accounts-ms
    ports:
      - "8080:8080"
    deploy:
      resources:
        limits:
          memory: 700m
    networks:
      - cgnexus-ms
    environment:
      - SPRING_APPLICATION_NAME:"accounts"
      - SPRING_CONFIG_IMPORT:"configserver:http://configserver:8071"
      - SPRING_PROFILES_ACTIVE:"default"
    env_file:
      - .env
    depends_on:
      configserver:
        condition: service_healthy
  cards:
    image: magises/cards:s6
    container_name: cards-ms
    ports:
      - "9000:9000"
    deploy:
      resources:
        limits:
          memory: 700m
    networks:
      - cgnexus-ms
    environment:
      - SPRING_APPLICATION_NAME:"cards"
      - SPRING_CONFIG_IMPORT:"configserver:http://configserver:8071"
      - SPRING_PROFILES_ACTIVE:"default"
    env_file:
      - .env
    depends_on:
      configserver:
        condition: service_healthy
  loans:
    image: magises/loans:s6
    container_name: loans-ms
    ports:
      - "8090:8090"
    deploy:
      resources:
        limits:
          memory: 700m
    networks:
      - cgnexus-ms
    environment:
      - SPRING_APPLICATION_NAME:loans"
      - SPRING_CONFIG_IMPORT:"configserver:http://configserver:8071"
      - SPRING_PROFILES_ACTIVE:"default"
    env_file:
      - .env
    depends_on:
      configserver:
        condition: service_healthy
networks:
  cgnexus-ms:
    driver: bridge
```

## Optimizing Docker Compose file ✅

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2028.png)

```yaml
services:
  network-deploy-service:
    networks:
      - cgnexus-ms
  microservice-base-config:
    extends:
      service: network-deploy-service
    deploy:
      resources:
        limits:
          memory: 700m
    environment:
      - SPRING_RABBITMQ_HOST: "rabbitmq"
  microservice-configserver-config:
    extends:
      service: microservice-base-config
    environment:
      SPRING_PROFILES_ACTIVE: default
      SPRING_CONFIG_IMPORT: "configserver:http://configserver:8071/"
```

```yaml
services:
  rabbitmq:
    image: rabbitmq:4.0-management
    container_name: rabbitmq
    hostname: rabbitmq
    ports:
      - "5672:5672"
      - "15672:15672"
    healthcheck:
      test: rabbitmq-diagnostics check_port_connectivity
      interval: 10s
      timeout: 5s
      retries: 10
      start_period: 5s
    extends:
      file: common-config.yml
      service: network-deploy-service

  configserver:
    image: magises/configserver:s6
    container_name: configserver-ms
    ports:
      - "8071:8071"
    healthcheck:
      test: "curl --fail http://localhost:8071/actuator/health/readiness | grep UP || exit 1" # check if the server is up
      interval: 10s # check every 10 seconds
      timeout: 5s # wait 5 seconds before considering the check to have failed
      retries: 10 # retry 10 times before giving up
      start_period: 10s # wait for 10 seconds before running the first check
    env_file:
      - .env
    depends_on:
      rabbitmq:
        condition: service_healthy
    extends:
      file: common-config.yml
      service: microservice-base-config

  accounts:
    image: magises/accounts:s6
    container_name: accounts-ms
    ports:
      - "8080:8080"
    environment:
      - SPRING_APPLICATION_NAME:"accounts"
      - SPRING_CONFIG_IMPORT:"configserver:http://configserver:8071"
    env_file:
      - .env
    depends_on:
      configserver:
        condition: service_healthy
    extends:
      file: common-config.yml
      service: microservice-configserver-config
  cards:
    image: magises/cards:s6
    container_name: cards-ms
    ports:
      - "9000:9000"
    deploy:
      resources:
        limits:
          memory: 700m
    networks:
      - cgnexus-ms
    environment:
      - SPRING_APPLICATION_NAME:"cards"
    env_file:
      - .env
    depends_on:
      configserver:
        condition: service_healthy
    extends:
      file: common-config.yml
      service: microservice-configserver-config
  loans:
    image: magises/loans:s6
    container_name: loans-ms
    ports:
      - "8090:8090"
    deploy:
      resources:
        limits:
          memory: 700m
    networks:
      - cgnexus-ms
    environment:
      - SPRING_APPLICATION_NAME:loans"
    env_file:
      - .env
    depends_on:
      configserver:
        condition: service_healthy
    extends:
      file: common-config.yml
      service: microservice-configserver-config
networks:
  cgnexus-ms:
    driver: bridge
```

## Generating Docker images and pushing them into Docker Hub ✅

```powershell
.\mvnw compile jib:build
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------< com.cgnexus:accounts >------------------------
[INFO] Building accounts s6
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ accounts ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ accounts ---
[INFO] Recompiling the module because of added or removed source files.
[INFO] Compiling 23 source files with javac [debug parameters release 21] to target\classes
[INFO] 
[INFO] --- jib:3.4.4:build (default-cli) @ accounts ---
[WARNING] 'mainClass' configured in 'maven-jar-plugin' is not a valid Java class: ${start-class}
[INFO] 
[INFO] Containerizing application to magises/accounts:s6...
[WARNING] Base image 'eclipse-temurin:21-jre' does not use a specific image digest - build may not be reproducible
[WARNING] The credential helper (docker-credential-desktop) has nothing for server URL: registry-1.docker.io
[WARNING] 
Got output:

credentials not found in native keychain

[WARNING] The credential helper (docker-credential-desktop) has nothing for server URL: registry.hub.docker.com
[WARNING] 
Got output:

credentials not found in native keychain

[INFO] Using credentials from Docker config (C:\Users\chama\.docker\config.json) for magises/accounts:s6
[INFO] The base image requires auth. Trying again for eclipse-temurin:21-jre...
[WARNING] The credential helper (docker-credential-desktop) has nothing for server URL: registry-1.docker.io
[WARNING] 
Got output:

credentials not found in native keychain

[WARNING] The credential helper (docker-credential-desktop) has nothing for server URL: registry.hub.docker.com
[WARNING] 
Got output:

credentials not found in native keychain

[INFO] Using credentials from Docker config (C:\Users\chama\.docker\config.json) for eclipse-temurin:21-jre
[INFO] Using base image with digest: sha256:9cc79f292357e78e18a76e09ec4c0f25c1db041748768028af0bef44ead3bb07
[INFO]
[INFO] Container entrypoint set to [java, -cp, @/app/jib-classpath-file, com.cgnexus.accounts.AccountsApplication]
[INFO]
[INFO] Built and pushed image as magises/accounts:s6
[INFO] Executing tasks:
[INFO] [==============================] 100.0% complete
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  20.468 s
[INFO] Finished at: 2025-03-16T10:42:34+05:30
[INFO] ------------------------------------------------------------------------
```

```powershell
.\mvnw compile jib:build
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------------< com.cgnexus:cards >--------------------------
[INFO] Building cards s6
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ cards ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ cards ---
[INFO] Recompiling the module because of added or removed source files.
[INFO] Compiling 17 source files with javac [debug parameters release 21] to target\classes
[INFO] 
[INFO] --- jib:3.4.4:build (default-cli) @ cards ---
[WARNING] 'mainClass' configured in 'maven-jar-plugin' is not a valid Java class: ${start-class}
[INFO] 
[INFO] Containerizing application to magises/cards:s6...
[WARNING] Base image 'eclipse-temurin:21-jre' does not use a specific image digest - build may not be reproducible
[WARNING] The credential helper (docker-credential-desktop) has nothing for server URL: registry-1.docker.io
[WARNING] 
Got output:

credentials not found in native keychain

[WARNING] The credential helper (docker-credential-desktop) has nothing for server URL: registry.hub.docker.com
[WARNING] 
Got output:

credentials not found in native keychain

[INFO] Using credentials from Docker config (C:\Users\chama\.docker\config.json) for magises/cards:s6
[INFO] The base image requires auth. Trying again for eclipse-temurin:21-jre...
[WARNING] The credential helper (docker-credential-desktop) has nothing for server URL: registry-1.docker.io
[WARNING] 
Got output:

credentials not found in native keychain

[WARNING] The credential helper (docker-credential-desktop) has nothing for server URL: registry.hub.docker.com
[WARNING] 
Got output:

credentials not found in native keychain

[INFO] Using credentials from Docker config (C:\Users\chama\.docker\config.json) for eclipse-temurin:21-jre
[INFO] Using base image with digest: sha256:9cc79f292357e78e18a76e09ec4c0f25c1db041748768028af0bef44ead3bb07
[INFO]
[INFO] Container entrypoint set to [java, -cp, @/app/jib-classpath-file, com.cgnexus.cards.CardsApplication]
[INFO]
[INFO] Built and pushed image as magises/cards:s6
[INFO] Executing tasks:
[INFO] [============================  ] 91.7% complete
[INFO] > launching layer pushers
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  19.520 s
[INFO] Finished at: 2025-03-16T10:43:40+05:30
[INFO] ------------------------------------------------------------------------
```

```xml
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
                            <username>${docker.username}</username>
                            <password>${docker.password}</password>
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
```

add this to config server

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2029.png)

```powershell
services:
  rabbit:
    image: rabbitmq:4.0-management
    hostname: rabbitmq
    ports:
      - "5672:5672"
      - "15672:15672"
    healthcheck:
      test: rabbitmq-diagnostics check_port_connectivity
      interval: 10s
      timeout: 5s
      retries: 10
      start_period: 5s
    extends:
      file: common-config.yml
      service: network-deploy-service

  configserver:
    image: magises/configserver:s6
    container_name: configserver-ms
    ports:
      - "8071:8071"
    depends_on:
      rabbit:
        condition: service_healthy
        restart: true
    healthcheck:
      test: "curl --fail --silent localhost:8071/actuator/health/readiness | grep UP || exit 1"
      interval: 10s
      timeout: 5s
      retries: 10
      start_period: 10s
    extends:
      file: common-config.yml
      service: microservice-base-config

  accounts:
    image: magises/accounts:s6
    container_name: accounts-ms
    ports:
      - "8080:8080"
    depends_on:
      configserver:
        condition: service_healthy
        restart: true
    environment:
      SPRING_APPLICATION_NAME: "accounts"
    extends:
      file: common-config.yml
      service: microservice-configserver-config

  loans:
    image: "eazybytes/loans:s6"
    container_name: loans-ms
    ports:
      - "8090:8090"
    depends_on:
      configserver:
        condition: service_healthy
        restart: true
    environment:
      SPRING_APPLICATION_NAME: "loans"
    extends:
      file: common-config.yml
      service: microservice-configserver-config

  cards:
    image: "eazybytes/cards:s6"
    container_name: cards-ms
    ports:
      - "9000:9000"
    depends_on:
      configserver:
        condition: service_healthy
        restart: true
    environment:
      SPRING_APPLICATION_NAME: "cards"
    extends:
      file: common-config.yml
      service: microservice-configserver-config

networks:
  cgnexus-ms:
    driver: "bridge"
```

```yaml
server:
  port: 8080
spring:
  application:
    name: "accounts"
  profiles:
    active: ${SPRING_PROFILES_ACTIVE:"prod"}
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: ''
  h2:
    console:
      enabled: true
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
  config:
    import: ${SPRING_CONFIG_IMPORT:"configserver:http://localhost:8071/"}
  rabbitmq:
    host: "localhost"
    port: 5672
    username: "guest"
    password: "guest"

logging:
  pattern:
    console: ${LOG_PATTERN_CONSOLE:%green(%d{HH:mm:ss.SSS}) %blue(%-5level) %red([%thread]) %yellow(%logger{15}) - %msg%n}

docker:
  username: ${DOCKER_USERNAME}
  password: ${DOCKER_PASSWORD}

management:
  endpoints:
    web:
      exposure:
        include: "*"
```

```yaml
server:
  port: 9000
spring:
  application:
    name: "cards"
  profiles:
    active: ${SPRING_PROFILES_ACTIVE:"prod"}
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: ''
  h2:
    console:
      enabled: true
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
  config:
    import: ${SPRING_CONFIG_IMPORT:"configserver:http://localhost:8071/"}
  rabbitmq:
    host: "localhost"
    port: 5672
    username: "guest"
    password: "guest"

logging:
  pattern:
    console: ${LOG_PATTERN_CONSOLE:%green(%d{HH:mm:ss.SSS}) %blue(%-5level) %red([%thread]) %yellow(%logger{15}) - %msg%n}

docker:
  username: ${DOCKER_USERNAME}
  password: ${DOCKER_PASSWORD}

management:
  endpoints:
    web:
      exposure:
        include: "*"
```

```yaml
spring:
  application:
    name: "configserver"
  profiles:
    # active: native
    active: git
  cloud:
    config:
      server:
        # native:
        # search-locations: "classpath:/config"
        # search-locations: "file:///Users//eazybytes//Documents//config"
        git:
          uri: "https://github.com/ccweerasinghe1994/MasterMicroserviceswithSpringBoot-Docker-Kubernetes-cloud-config.git"
          default-label: "main"
          timeout: 5
          clone-on-start: true
          force-pull: true
  rabbitmq:
    host: "localhost"
    port: 5672
    username: "guest"
    password: "guest"

management:
  endpoints:
    web:
      exposure:
        include: "*"
  health:
    readiness-state:
      enabled: true
    liveness-state:
      enabled: true
  endpoint:
    health:
      probes:
        enabled: true

encrypt:
  key: ${ENCRYPT_KEY:"45D81EC1EF61DF9AD8D3E5BB397F9"}

server:
  port: 8071
```

```yaml
server:
  port: 8090
spring:
  application:
    name: "loans"
  profiles:
    active: ${SPRING_PROFILES_ACTIVE:"prod"}
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: ''
  h2:
    console:
      enabled: true
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
  config:
    import: ${SPRING_CONFIG_IMPORT:"configserver:http://localhost:8071/"}
  rabbitmq:
    host: "localhost"
    port: 5672
    username: "guest"
    password: "guest"
logging:
  pattern:
    console: ${LOG_PATTERN_CONSOLE:%green(%d{HH:mm:ss.SSS}) %blue(%-5level) %red([%thread]) %yellow(%logger{15}) - %msg%n}

docker:
  username: ${DOCKER_USERNAME}
  password: ${DOCKER_PASSWORD}

management:
  endpoints:
    web:
      exposure:
        include: "*"
```

```yaml
services:
  network-deploy-service:
    networks:
      - cgnexus-ms
  microservice-base-config:
    extends:
      service: network-deploy-service
    deploy:
      resources:
        limits:
          memory: 700m
    environment:
      SPRING_RABBITMQ_HOST: "rabbit"

  microservice-configserver-config:
    extends:
      service: microservice-base-config
    environment:
      SPRING_PROFILES_ACTIVE: default
      SPRING_CONFIG_IMPORT: "configserver:http://configserver:8071/"
```

```yaml
services:
  rabbit:
    image: rabbitmq:4.0-management
    hostname: rabbitmq
    ports:
      - "5672:5672"
      - "15672:15672"
    healthcheck:
      test: rabbitmq-diagnostics check_port_connectivity
      interval: 10s
      timeout: 5s
      retries: 10
      start_period: 5s
    extends:
      file: common-config.yml
      service: network-deploy-service

  configserver:
    image: magises/configserver:s6
    container_name: configserver-ms
    ports:
      - "8071:8071"
    depends_on:
      rabbit:
        condition: service_healthy
        restart: true
    healthcheck:
      test: "curl --fail --silent localhost:8071/actuator/health/readiness | grep UP || exit 1"
      interval: 10s
      timeout: 5s
      retries: 10
      start_period: 10s
    extends:
      file: common-config.yml
      service: microservice-base-config

  accounts:
    image: magises/accounts:s6
    container_name: accounts-ms
    ports:
      - "8080:8080"
    depends_on:
      configserver:
        condition: service_healthy
        restart: true
    environment:
      SPRING_APPLICATION_NAME: "accounts"
    extends:
      file: common-config.yml
      service: microservice-configserver-config

  loans:
    image: "eazybytes/loans:s6"
    container_name: loans-ms
    ports:
      - "8090:8090"
    depends_on:
      configserver:
        condition: service_healthy
        restart: true
    environment:
      SPRING_APPLICATION_NAME: "loans"
    extends:
      file: common-config.yml
      service: microservice-configserver-config

  cards:
    image: "eazybytes/cards:s6"
    container_name: cards-ms
    ports:
      - "9000:9000"
    depends_on:
      configserver:
        condition: service_healthy
        restart: true
    environment:
      SPRING_APPLICATION_NAME: "cards"
    extends:
      file: common-config.yml
      service: microservice-configserver-config

networks:
  cgnexus-ms:
    driver: "bridge"
```

```java

```

## Testing Config Server changes end to end using Docker compose & default profile ✅

```java
curl --location 'http://localhost:9000/api/contact-info' | jq
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   173    0   173    0     0  45694      0 --:--:-- --:--:-- --:--:-- 57666
{
  "message": "Hello from cards service from docker ",
  "contactDetails": {
    "email": "cgnexus-prod@gmail.com",
    "name": "CG Nexus"
  },
  "onCallSupport": [
    "(777) 777-7777",
    "(888) 888-8888"
  ]
}
```

## Preparing Docker Compose files for QA & prod profiles ✅

![image.png](06%20-%20Configurations%20Management%20in%20Microservices%2015630c6e8cbd8025974cf9f91581543a/image%2030.png)

```yaml
services:
  network-deploy-service:
    networks:
      - cgnexus-ms
  microservice-base-config:
    extends:
      service: network-deploy-service
    deploy:
      resources:
        limits:
          memory: 700m
    environment:
      SPRING_RABBITMQ_HOST: "rabbit"

  microservice-configserver-config:
    extends:
      service: microservice-base-config
    environment:
      SPRING_PROFILES_ACTIVE: qa
      SPRING_CONFIG_IMPORT: "configserver:http://configserver:8071/"
```

```yaml
services:
  network-deploy-service:
    networks:
      - cgnexus-ms
  microservice-base-config:
    extends:
      service: network-deploy-service
    deploy:
      resources:
        limits:
          memory: 700m
    environment:
      SPRING_RABBITMQ_HOST: "rabbit"

  microservice-configserver-config:
    extends:
      service: microservice-base-config
    environment:
      SPRING_PROFILES_ACTIVE: prod
      SPRING_CONFIG_IMPORT: "configserver:http://configserver:8071/"
```

```bash
docker compose up

[+] Running 6/6
 ✔ Network prod_cgnexus-ms    Created                                                                                                                                                                                                                                   0.0s 
 ✔ Container prod-rabbit-1    Created                                                                                                                                                                                                                                   0.1s 
 ✔ Container configserver-ms  Created                                                                                                                                                                                                                                   0.0s 
 ✔ Container cards-ms         Created                                                                                                                                                                                                                                   0.1s 
 ✔ Container accounts-ms      Created                                                                                                                                                                                                                                   0.1s 
 ✔ Container loans-ms         Created                                                                                                                                                                                                                                   0.1s 
Attaching to accounts-ms, cards-ms, configserver-ms, loans-ms, rabbit-1
rabbit-1         | 2025-03-16 08:47:31.129953+00:00 [notice] <0.45.0> Application syslog exited with reason: stopped
rabbit-1         | 2025-03-16 08:47:31.134283+00:00 [notice] <0.216.0> Logging: switching to configured handler(s); following messages may not be visible in this log output
rabbit-1         | 2025-03-16 08:47:31.134964+00:00 [notice] <0.216.0> Logging: configured log handlers are now ACTIVE
rabbit-1         | 2025-03-16 08:47:31.143043+00:00 [info] <0.216.0> ra: starting system quorum_queues
rabbit-1         | 2025-03-16 08:47:31.143148+00:00 [info] <0.216.0> starting Ra system: quorum_queues in directory: /var/lib/rabbitmq/mnesia/rabbit@rabbitmq/quorum/rabbit@rabbitmq
rabbit-1         | 2025-03-16 08:47:31.191850+00:00 [info] <0.229.0> ra system 'quorum_queues' running pre init for 0 registered servers
rabbit-1         | 2025-03-16 08:47:31.200754+00:00 [info] <0.230.0> ra: meta data store initialised for system quorum_queues. 0 record(s) recovered
rabbit-1         | 2025-03-16 08:47:31.213544+00:00 [notice] <0.235.0> WAL: ra_log_wal init, open tbls: ra_log_open_mem_tables, closed tbls: ra_log_closed_mem_tables
rabbit-1         | 2025-03-16 08:47:31.229173+00:00 [info] <0.237.0> ra_system_recover: ra system 'quorum_queues' server recovery strategy rabbit_quorum_queue:system_recover
rabbit-1         | 2025-03-16 08:47:31.229261+00:00 [info] <0.237.0> [rabbit_quorum_queue:system_recover/1] rabbit not booted, skipping queue recovery
rabbit-1         | 2025-03-16 08:47:31.229382+00:00 [info] <0.216.0> ra: starting system coordination
rabbit-1         | 2025-03-16 08:47:31.229397+00:00 [info] <0.216.0> starting Ra system: coordination in directory: /var/lib/rabbitmq/mnesia/rabbit@rabbitmq/coordination/rabbit@rabbitmq
rabbit-1         | 2025-03-16 08:47:31.231496+00:00 [info] <0.243.0> ra system 'coordination' running pre init for 0 registered servers
rabbit-1         | 2025-03-16 08:47:31.232710+00:00 [info] <0.244.0> ra: meta data store initialised for system coordination. 0 record(s) recovered
rabbit-1         | 2025-03-16 08:47:31.233005+00:00 [notice] <0.249.0> WAL: ra_coordination_log_wal init, open tbls: ra_coordination_log_open_mem_tables, closed tbls: ra_coordination_log_closed_mem_tables
rabbit-1         | 2025-03-16 08:47:31.235647+00:00 [info] <0.216.0> ra: starting system coordination
rabbit-1         | 2025-03-16 08:47:31.235691+00:00 [info] <0.216.0> starting Ra system: coordination in directory: /var/lib/rabbitmq/mnesia/rabbit@rabbitmq/coordination/rabbit@rabbitmq
rabbit-1         | 2025-03-16 08:47:31.315459+00:00 [notice] <0.253.0> RabbitMQ metadata store: candidate -> leader in term: 1 machine version: 1
rabbit-1         | 2025-03-16 08:47:31.451052+00:00 [info] <0.216.0> 
rabbit-1         | 2025-03-16 08:47:31.451052+00:00 [info] <0.216.0>  Starting RabbitMQ 4.0.7 on Erlang 27.3 [jit]
rabbit-1         | 2025-03-16 08:47:31.451052+00:00 [info] <0.216.0>  Copyright (c) 2007-2024 Broadcom Inc and/or its subsidiaries
rabbit-1         | 2025-03-16 08:47:31.451052+00:00 [info] <0.216.0>  Licensed under the MPL 2.0. Website: https://rabbitmq.com
rabbit-1         | 
rabbit-1         |   ##  ##      RabbitMQ 4.0.7
rabbit-1         |   ##  ##
rabbit-1         |   ##########  Copyright (c) 2007-2024 Broadcom Inc and/or its subsidiaries
rabbit-1         |   ######  ##
rabbit-1         |   ##########  Licensed under the MPL 2.0. Website: https://rabbitmq.com
rabbit-1         | 
rabbit-1         |   Erlang:      27.3 [jit]
rabbit-1         |   TLS Library: OpenSSL - OpenSSL 3.3.3 11 Feb 2025
rabbit-1         |   Release series support status: see https://www.rabbitmq.com/release-information
rabbit-1         | 
rabbit-1         |   Doc guides:  https://www.rabbitmq.com/docs
rabbit-1         |   Support:     https://www.rabbitmq.com/docs/contact
rabbit-1         |   Tutorials:   https://www.rabbitmq.com/tutorials
rabbit-1         |   Monitoring:  https://www.rabbitmq.com/docs/monitoring
rabbit-1         |   Upgrading:   https://www.rabbitmq.com/docs/upgrade
rabbit-1         | 
rabbit-1         |   Logs: <stdout>
rabbit-1         | 
rabbit-1         |   Config file(s): /etc/rabbitmq/conf.d/10-defaults.conf
rabbit-1         | 
rabbit-1         |   Starting broker...2025-03-16 08:47:31.452533+00:00 [info] <0.216.0> 
rabbit-1         | 2025-03-16 08:47:31.452533+00:00 [info] <0.216.0>  node           : rabbit@rabbitmq
rabbit-1         | 2025-03-16 08:47:31.452533+00:00 [info] <0.216.0>  home dir       : /var/lib/rabbitmq
rabbit-1         | 2025-03-16 08:47:31.452533+00:00 [info] <0.216.0>  config file(s) : /etc/rabbitmq/conf.d/10-defaults.conf
rabbit-1         | 2025-03-16 08:47:31.452533+00:00 [info] <0.216.0>  cookie hash    : seixNEXw6WjmHZ8is/5wmQ==
rabbit-1         | 2025-03-16 08:47:31.452533+00:00 [info] <0.216.0>  log(s)         : <stdout>
rabbit-1         | 2025-03-16 08:47:31.452533+00:00 [info] <0.216.0>  data dir       : /var/lib/rabbitmq/mnesia/rabbit@rabbitmq
rabbit-1         | 2025-03-16 08:47:31.715762+00:00 [info] <0.216.0> Running boot step pre_boot defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.715826+00:00 [info] <0.216.0> Running boot step rabbit_global_counters defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.716155+00:00 [info] <0.216.0> Running boot step rabbit_osiris_metrics defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.716248+00:00 [info] <0.216.0> Running boot step rabbit_core_metrics defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.717090+00:00 [info] <0.216.0> Running boot step rabbit_alarm defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.726616+00:00 [info] <0.283.0> Memory high watermark set to 9485 MiB (9945757286 bytes) of 15808 MiB (16576262144 bytes) total
rabbit-1         | 2025-03-16 08:47:31.728962+00:00 [info] <0.285.0> Enabling free disk space monitoring (disk free space: 927166066688, total memory: 16576262144)
rabbit-1         | 2025-03-16 08:47:31.729028+00:00 [info] <0.285.0> Disk free limit set to 50MB
rabbit-1         | 2025-03-16 08:47:31.731088+00:00 [info] <0.216.0> Running boot step code_server_cache defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.731180+00:00 [info] <0.216.0> Running boot step file_handle_cache defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.737146+00:00 [info] <0.288.0> Limiting to approx 1048479 file handles (943629 sockets)
rabbit-1         | 2025-03-16 08:47:31.737339+00:00 [info] <0.289.0> FHC read buffering: OFF
rabbit-1         | 2025-03-16 08:47:31.737372+00:00 [info] <0.289.0> FHC write buffering: ON
rabbit-1         | 2025-03-16 08:47:31.737863+00:00 [info] <0.216.0> Running boot step worker_pool defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.737943+00:00 [info] <0.270.0> Will use 20 processes for default worker pool
rabbit-1         | 2025-03-16 08:47:31.737984+00:00 [info] <0.270.0> Starting worker pool 'worker_pool' with 20 processes in it
rabbit-1         | 2025-03-16 08:47:31.738621+00:00 [info] <0.216.0> Running boot step rabbit_registry defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.738707+00:00 [info] <0.216.0> Running boot step database defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.739071+00:00 [info] <0.216.0> Peer discovery: configured backend: rabbit_peer_discovery_classic_config
rabbit-1         | 2025-03-16 08:47:31.740384+00:00 [notice] <0.271.0> Feature flags: attempt to enable `detailed_queues_endpoint`...
rabbit-1         | 2025-03-16 08:47:31.746673+00:00 [notice] <0.271.0> Feature flags: `detailed_queues_endpoint` enabled
rabbit-1         | 2025-03-16 08:47:31.746836+00:00 [notice] <0.271.0> Feature flags: attempt to enable `rabbitmq_4.0.0`...
rabbit-1         | 2025-03-16 08:47:31.754438+00:00 [notice] <0.271.0> Feature flags: `rabbitmq_4.0.0` enabled
rabbit-1         | 2025-03-16 08:47:31.754573+00:00 [notice] <0.271.0> Feature flags: attempt to enable `rabbit_exchange_type_local_random`...
rabbit-1         | 2025-03-16 08:47:31.761668+00:00 [notice] <0.271.0> Feature flags: `rabbit_exchange_type_local_random` enabled
rabbit-1         | 2025-03-16 08:47:31.761860+00:00 [notice] <0.271.0> Feature flags: attempt to enable `quorum_queue_non_voters`...
rabbit-1         | 2025-03-16 08:47:31.767840+00:00 [notice] <0.271.0> Feature flags: `quorum_queue_non_voters` enabled
rabbit-1         | 2025-03-16 08:47:31.768013+00:00 [notice] <0.271.0> Feature flags: attempt to enable `message_containers_deaths_v2`...
rabbit-1         | 2025-03-16 08:47:31.774599+00:00 [notice] <0.271.0> Feature flags: `message_containers_deaths_v2` enabled
rabbit-1         | 2025-03-16 08:47:31.774831+00:00 [info] <0.216.0> DB: virgin node -> run peer discovery
rabbit-1         | 2025-03-16 08:47:31.774904+00:00 [warning] <0.216.0> Classic peer discovery backend: list of nodes does not contain the local node []
rabbit-1         | 2025-03-16 08:47:31.784789+00:00 [notice] <0.45.0> Application mnesia exited with reason: stopped
rabbit-1         | 2025-03-16 08:47:31.896895+00:00 [info] <0.216.0> Waiting for Mnesia tables for 30000 ms, 9 retries left
rabbit-1         | 2025-03-16 08:47:31.897075+00:00 [info] <0.216.0> Successfully synced tables from a peer
rabbit-1         | 2025-03-16 08:47:31.897253+00:00 [info] <0.216.0> Waiting for Mnesia tables for 30000 ms, 9 retries left
rabbit-1         | 2025-03-16 08:47:31.897356+00:00 [info] <0.216.0> Successfully synced tables from a peer
rabbit-1         | 2025-03-16 08:47:31.903638+00:00 [info] <0.216.0> Waiting for Mnesia tables for 30000 ms, 9 retries left
rabbit-1         | 2025-03-16 08:47:31.903811+00:00 [info] <0.216.0> Successfully synced tables from a peer
rabbit-1         | 2025-03-16 08:47:31.904011+00:00 [info] <0.216.0> Running boot step tracking_metadata_store defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.904087+00:00 [info] <0.497.0> Setting up a table for connection tracking on this node: tracked_connection
rabbit-1         | 2025-03-16 08:47:31.904121+00:00 [info] <0.497.0> Setting up a table for per-vhost connection counting on this node: tracked_connection_per_vhost
rabbit-1         | 2025-03-16 08:47:31.904187+00:00 [info] <0.497.0> Setting up a table for per-user connection counting on this node: tracked_connection_per_user
rabbit-1         | 2025-03-16 08:47:31.904263+00:00 [info] <0.497.0> Setting up a table for channel tracking on this node: tracked_channel
rabbit-1         | 2025-03-16 08:47:31.904482+00:00 [info] <0.497.0> Setting up a table for channel tracking on this node: tracked_channel_per_user
rabbit-1         | 2025-03-16 08:47:31.904578+00:00 [info] <0.216.0> Running boot step networking_metadata_store defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.904633+00:00 [info] <0.216.0> Running boot step feature_flags defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.904874+00:00 [info] <0.216.0> Running boot step codec_correctness_check defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.904921+00:00 [info] <0.216.0> Running boot step external_infrastructure defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.904941+00:00 [info] <0.216.0> Running boot step rabbit_event defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.905012+00:00 [info] <0.216.0> Running boot step rabbit_auth_mechanism_amqplain defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.905192+00:00 [info] <0.216.0> Running boot step rabbit_auth_mechanism_anonymous defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.905331+00:00 [info] <0.216.0> Running boot step rabbit_auth_mechanism_cr_demo defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.905444+00:00 [info] <0.216.0> Running boot step rabbit_auth_mechanism_plain defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.905541+00:00 [info] <0.216.0> Running boot step rabbit_exchange_type_direct defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.905596+00:00 [info] <0.216.0> Running boot step rabbit_exchange_type_fanout defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.905704+00:00 [info] <0.216.0> Running boot step rabbit_exchange_type_headers defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.905803+00:00 [info] <0.216.0> Running boot step rabbit_exchange_type_local_random defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.905883+00:00 [info] <0.216.0> Running boot step rabbit_exchange_type_topic defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.905937+00:00 [info] <0.216.0> Running boot step rabbit_priority_queue defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.905962+00:00 [info] <0.216.0> Priority queues enabled, real BQ is rabbit_variable_queue
rabbit-1         | 2025-03-16 08:47:31.906018+00:00 [info] <0.216.0> Running boot step kernel_ready defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.906029+00:00 [info] <0.216.0> Running boot step pg_local_amqp_connection defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.921108+00:00 [info] <0.216.0> Running boot step pg_local_amqp_session defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.921282+00:00 [info] <0.216.0> Running boot step rabbit_sysmon_minder defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.921556+00:00 [info] <0.216.0> Running boot step rabbit_epmd_monitor defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.922507+00:00 [info] <0.506.0> epmd monitor knows us, inter-node communication (distribution) port: 25672
rabbit-1         | 2025-03-16 08:47:31.922675+00:00 [info] <0.216.0> Running boot step guid_generator defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.926466+00:00 [info] <0.216.0> Running boot step rabbit_node_monitor defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.926936+00:00 [info] <0.510.0> Starting rabbit_node_monitor (in ignore mode)
rabbit-1         | 2025-03-16 08:47:31.927082+00:00 [info] <0.216.0> Running boot step delegate_sup defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.927457+00:00 [info] <0.216.0> Running boot step rabbit_fifo_dlx_sup defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.927530+00:00 [info] <0.216.0> Running boot step core_initialized defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.927581+00:00 [info] <0.216.0> Running boot step rabbit_channel_tracking_handler defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.927641+00:00 [info] <0.216.0> Running boot step rabbit_classic_queue defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.927791+00:00 [info] <0.216.0> Running boot step rabbit_connection_tracking_handler defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.927845+00:00 [info] <0.216.0> Running boot step rabbit_definitions_hashing defined by app rabbit
rabbit-1         | 2025-03-16 08:47:31.927882+00:00 [info] <0.216.0> Running boot step rabbit_exchange_parameters defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.099293+00:00 [info] <0.216.0> Running boot step rabbit_policies defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.099669+00:00 [info] <0.216.0> Running boot step rabbit_policy defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.099725+00:00 [info] <0.216.0> Running boot step rabbit_quorum_memory_manager defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.099792+00:00 [info] <0.216.0> Running boot step rabbit_quorum_queue defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.099895+00:00 [info] <0.216.0> Running boot step rabbit_stream_coordinator defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.100063+00:00 [info] <0.216.0> Running boot step rabbit_vhost_limit defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.100151+00:00 [info] <0.216.0> Running boot step rabbit_mgmt_reset_handler defined by app rabbitmq_management
rabbit-1         | 2025-03-16 08:47:32.100285+00:00 [info] <0.216.0> Running boot step rabbit_mgmt_db_handler defined by app rabbitmq_management_agent
rabbit-1         | 2025-03-16 08:47:32.100373+00:00 [info] <0.216.0> Management plugin: using rates mode 'basic'
rabbit-1         | 2025-03-16 08:47:32.100681+00:00 [info] <0.216.0> Running boot step recovery defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.113823+00:00 [info] <0.216.0> Running boot step empty_db_check defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.113904+00:00 [info] <0.216.0> Will seed default virtual host and user...
rabbit-1         | 2025-03-16 08:47:32.113982+00:00 [info] <0.216.0> Adding vhost '/' (description: 'Default virtual host', tags: [])
rabbit-1         | 2025-03-16 08:47:32.127654+00:00 [info] <0.553.0> Making sure data directory '/var/lib/rabbitmq/mnesia/rabbit@rabbitmq/msg_stores/vhosts/628WB79CIFDYO9LJI6DKMI09L' for vhost '/' exists
rabbit-1         | 2025-03-16 08:47:32.128911+00:00 [info] <0.553.0> Setting segment_entry_count for vhost '/' with 0 queues to '2048'
rabbit-1         | 2025-03-16 08:47:32.139485+00:00 [info] <0.553.0> Starting message stores for vhost '/'
rabbit-1         | 2025-03-16 08:47:32.140933+00:00 [info] <0.553.0> Started message store of type transient for vhost '/'
rabbit-1         | 2025-03-16 08:47:32.141606+00:00 [warning] <0.567.0> Message store "628WB79CIFDYO9LJI6DKMI09L/msg_store_persistent": rebuilding indices from scratch
rabbit-1         | 2025-03-16 08:47:32.142484+00:00 [info] <0.553.0> Started message store of type persistent for vhost '/'
rabbit-1         | 2025-03-16 08:47:32.142620+00:00 [info] <0.553.0> Recovering 0 queues of type rabbit_classic_queue took 13ms
rabbit-1         | 2025-03-16 08:47:32.142672+00:00 [info] <0.553.0> Recovering 0 queues of type rabbit_quorum_queue took 0ms
rabbit-1         | 2025-03-16 08:47:32.142706+00:00 [info] <0.553.0> Recovering 0 queues of type rabbit_stream_queue took 0ms
rabbit-1         | 2025-03-16 08:47:32.146302+00:00 [info] <0.216.0> Created user 'guest'
rabbit-1         | 2025-03-16 08:47:32.147828+00:00 [info] <0.216.0> Successfully set user tags for user 'guest' to [administrator]
rabbit-1         | 2025-03-16 08:47:32.149675+00:00 [info] <0.216.0> Successfully set permissions for user 'guest' in virtual host '/' to '.*', '.*', '.*'
rabbit-1         | 2025-03-16 08:47:32.149747+00:00 [info] <0.216.0> Running boot step rabbit_observer_cli defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.149867+00:00 [info] <0.216.0> Running boot step rabbit_core_metrics_gc defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.150011+00:00 [info] <0.216.0> Running boot step background_gc defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.150180+00:00 [info] <0.216.0> Running boot step routing_ready defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.150202+00:00 [info] <0.216.0> Running boot step pre_flight defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.150221+00:00 [info] <0.216.0> Running boot step notify_cluster defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.150262+00:00 [info] <0.216.0> Running boot step networking defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.150358+00:00 [info] <0.216.0> Running boot step rabbit_quorum_queue_periodic_membership_reconciliation defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.150599+00:00 [info] <0.216.0> Running boot step definition_import_worker_pool defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.150780+00:00 [info] <0.270.0> Starting worker pool 'definition_import_pool' with 20 processes in it
rabbit-1         | 2025-03-16 08:47:32.152000+00:00 [info] <0.216.0> Running boot step cluster_name defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.152104+00:00 [info] <0.216.0> Initialising internal cluster ID to 'rabbitmq-cluster-id-HwCzm3ohHfToLmvnqjlpFQ'
rabbit-1         | 2025-03-16 08:47:32.154275+00:00 [info] <0.216.0> Running boot step virtual_host_reconciliation defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.154615+00:00 [info] <0.216.0> Running boot step direct_client defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.154731+00:00 [info] <0.216.0> Running boot step cluster_tags defined by app rabbit
rabbit-1         | 2025-03-16 08:47:32.156876+00:00 [info] <0.216.0> Running boot step rabbit_management_load_definitions defined by app rabbitmq_management
rabbit-1         | 2025-03-16 08:47:32.157004+00:00 [info] <0.620.0> Resetting node maintenance status
rabbit-1         | 2025-03-16 08:47:32.297664+00:00 [warning] <0.642.0> Deprecated features: `management_metrics_collection`: Feature `management_metrics_collection` is deprecated.
rabbit-1         | 2025-03-16 08:47:32.297664+00:00 [warning] <0.642.0> By default, this feature can still be used for now.
rabbit-1         | 2025-03-16 08:47:32.297664+00:00 [warning] <0.642.0> Its use will not be permitted by default in a future minor RabbitMQ version and the feature will be removed from a future major RabbitMQ version; actual versions to be determined.
rabbit-1         | 2025-03-16 08:47:32.297664+00:00 [warning] <0.642.0> To continue using this feature when it is not permitted by default, set the following parameter in your configuration:
rabbit-1         | 2025-03-16 08:47:32.297664+00:00 [warning] <0.642.0>     "deprecated_features.permit.management_metrics_collection = true"
rabbit-1         | 2025-03-16 08:47:32.297664+00:00 [warning] <0.642.0> To test RabbitMQ as if the feature was removed, set this in your configuration:
rabbit-1         | 2025-03-16 08:47:32.297664+00:00 [warning] <0.642.0>     "deprecated_features.permit.management_metrics_collection = false"
rabbit-1         | 2025-03-16 08:47:32.335776+00:00 [info] <0.678.0> Management plugin: HTTP (non-TLS) listener started on port 15672
rabbit-1         | 2025-03-16 08:47:32.335929+00:00 [info] <0.706.0> Statistics database started.
rabbit-1         | 2025-03-16 08:47:32.336024+00:00 [info] <0.705.0> Starting worker pool 'management_worker_pool' with 3 processes in it
rabbit-1         | 2025-03-16 08:47:32.350919+00:00 [info] <0.717.0> Prometheus metrics: HTTP (non-TLS) listener started on port 15692
rabbit-1         | 2025-03-16 08:47:32.351398+00:00 [info] <0.620.0> Ready to start client connection listeners
rabbit-1         | 2025-03-16 08:47:32.352707+00:00 [info] <0.761.0> started TCP listener on [::]:5672
rabbit-1         |  completed with 4 plugins.
rabbit-1         | 2025-03-16 08:47:32.416774+00:00 [info] <0.620.0> Server startup complete; 4 plugins started.
rabbit-1         | 2025-03-16 08:47:32.416774+00:00 [info] <0.620.0>  * rabbitmq_prometheus
rabbit-1         | 2025-03-16 08:47:32.416774+00:00 [info] <0.620.0>  * rabbitmq_management
rabbit-1         | 2025-03-16 08:47:32.416774+00:00 [info] <0.620.0>  * rabbitmq_management_agent
rabbit-1         | 2025-03-16 08:47:32.416774+00:00 [info] <0.620.0>  * rabbitmq_web_dispatch
rabbit-1         | 2025-03-16 08:47:32.558462+00:00 [info] <0.10.0> Time to start RabbitMQ: 3294 ms
configserver-ms  | 
configserver-ms  |   .   ____          _            __ _ _
configserver-ms  |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
configserver-ms  | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
configserver-ms  |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
configserver-ms  |   '  |____| .__|_| |_|_| |_\__, | / / / /
configserver-ms  |  =========|_|==============|___/=/_/_/_/
configserver-ms  | 
configserver-ms  |  :: Spring Boot ::                (v3.4.3)
configserver-ms  | 
configserver-ms  | 2025-03-16T08:47:35.522Z  INFO 1 --- [configserver] [           main] c.c.c.ConfigserverApplication            : Starting ConfigserverApplication using Java 21.0.6 with PID 1 (/app/classes started by root in /)
configserver-ms  | 2025-03-16T08:47:35.523Z  INFO 1 --- [configserver] [           main] c.c.c.ConfigserverApplication            : The following 1 profile is active: "git"
configserver-ms  | 2025-03-16T08:47:36.456Z  INFO 1 --- [configserver] [           main] faultConfiguringBeanFactoryPostProcessor : No bean named 'errorChannel' has been explicitly defined. Therefore, a default PublishSubscribeChannel will be created.
configserver-ms  | 2025-03-16T08:47:36.460Z  INFO 1 --- [configserver] [           main] faultConfiguringBeanFactoryPostProcessor : No bean named 'integrationHeaderChannelRegistry' has been explicitly defined. Therefore, a default DefaultHeaderChannelRegistry will be created.
configserver-ms  | 2025-03-16T08:47:36.529Z  INFO 1 --- [configserver] [           main] o.s.cloud.context.scope.GenericScope     : BeanFactory id=c1d449ac-94dc-351d-8161-b8afec2c0948
configserver-ms  | 2025-03-16T08:47:36.788Z  INFO 1 --- [configserver] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8071 (http)
configserver-ms  | 2025-03-16T08:47:36.799Z  INFO 1 --- [configserver] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
configserver-ms  | 2025-03-16T08:47:36.800Z  INFO 1 --- [configserver] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.36]
configserver-ms  | 2025-03-16T08:47:36.837Z  INFO 1 --- [configserver] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
configserver-ms  | 2025-03-16T08:47:36.838Z  INFO 1 --- [configserver] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1272 ms
configserver-ms  | 2025-03-16T08:47:48.662Z  INFO 1 --- [configserver] [           main] o.s.c.s.m.DirectWithAttributesChannel    : Channel 'configserver.springCloudBusInput' has 1 subscriber(s).
configserver-ms  | 2025-03-16T08:47:48.818Z  INFO 1 --- [configserver] [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 23 endpoints beneath base path '/actuator'
configserver-ms  | 2025-03-16T08:47:48.890Z  INFO 1 --- [configserver] [           main] o.s.i.endpoint.EventDrivenConsumer       : Adding {logging-channel-adapter:_org.springframework.integration.errorLogger} as a subscriber to the 'errorChannel' channel
configserver-ms  | 2025-03-16T08:47:48.890Z  INFO 1 --- [configserver] [           main] o.s.i.channel.PublishSubscribeChannel    : Channel 'configserver.errorChannel' has 1 subscriber(s).
configserver-ms  | 2025-03-16T08:47:48.890Z  INFO 1 --- [configserver] [           main] o.s.i.endpoint.EventDrivenConsumer       : started bean '_org.springframework.integration.errorLogger'
configserver-ms  | 2025-03-16T08:47:48.913Z  INFO 1 --- [configserver] [           main] o.s.c.c.m.FileMonitorConfiguration       : Not monitoring for local config changes
configserver-ms  | 2025-03-16T08:47:48.927Z  INFO 1 --- [configserver] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8071 (http) with context path '/'
configserver-ms  | 2025-03-16T08:47:48.931Z  INFO 1 --- [configserver] [           main] o.s.c.s.binder.DefaultBinderFactory      : Creating binder: rabbit
configserver-ms  | 2025-03-16T08:47:48.931Z  INFO 1 --- [configserver] [           main] o.s.c.s.binder.DefaultBinderFactory      : Constructing binder child context for rabbit
configserver-ms  | 2025-03-16T08:47:48.995Z  INFO 1 --- [configserver] [           main] o.s.c.s.binder.DefaultBinderFactory      : Caching the binder: rabbit
configserver-ms  | 2025-03-16T08:47:49.020Z  INFO 1 --- [configserver] [           main] c.s.b.r.p.RabbitExchangeQueueProvisioner : declaring queue for inbound: springCloudBus.anonymous.QHaAgyFIRFyfY_B8ms-T0Q, bound to: springCloudBus
configserver-ms  | 2025-03-16T08:47:49.024Z  INFO 1 --- [configserver] [           main] o.s.a.r.c.CachingConnectionFactory       : Attempting to connect to: [rabbit:5672]
rabbit-1         | 2025-03-16 08:47:49.032848+00:00 [info] <0.785.0> accepting AMQP connection 172.29.0.3:33382 -> 172.29.0.2:5672
rabbit-1         | 2025-03-16 08:47:49.051781+00:00 [info] <0.785.0> connection 172.29.0.3:33382 -> 172.29.0.2:5672 has a client-provided name: rabbitConnectionFactory#d0fabc8:0
rabbit-1         | 2025-03-16 08:47:49.055803+00:00 [info] <0.785.0> connection 172.29.0.3:33382 -> 172.29.0.2:5672 - rabbitConnectionFactory#d0fabc8:0: user 'guest' authenticated and granted access to vhost '/'
configserver-ms  | 2025-03-16T08:47:49.057Z  INFO 1 --- [configserver] [           main] o.s.a.r.c.CachingConnectionFactory       : Created new connection: rabbitConnectionFactory#d0fabc8:0/SimpleConnection@63541cd4 [delegate=amqp://guest@172.29.0.2:5672/, localPort=33382]
rabbit-1         | 2025-03-16 08:47:49.085975+00:00 [warning] <0.794.0> Deprecated features: `queue_master_locator`: queue-master-locator is deprecated. queue-leader-locator should be used instead (allowed values are 'client-local' and 'balanced')
configserver-ms  | 2025-03-16T08:47:49.104Z  INFO 1 --- [configserver] [           main] o.s.c.stream.binder.BinderErrorChannel   : Channel 'rabbit-1430674428.springCloudBusInput.errors' has 1 subscriber(s).
configserver-ms  | 2025-03-16T08:47:49.105Z  INFO 1 --- [configserver] [           main] o.s.c.stream.binder.BinderErrorChannel   : Channel 'rabbit-1430674428.springCloudBusInput.errors' has 2 subscriber(s).
configserver-ms  | 2025-03-16T08:47:49.124Z  INFO 1 --- [configserver] [           main] o.s.i.a.i.AmqpInboundChannelAdapter      : started bean 'inbound.springCloudBus.anonymous.QHaAgyFIRFyfY_B8ms-T0Q'
configserver-ms  | 2025-03-16T08:47:49.160Z  INFO 1 --- [configserver] [           main] c.c.c.ConfigserverApplication            : Started ConfigserverApplication in 14.044 seconds (process running for 5.586)
configserver-ms  | 2025-03-16T08:47:58.659Z  INFO 1 --- [configserver] [nio-8071-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
configserver-ms  | 2025-03-16T08:47:58.659Z  INFO 1 --- [configserver] [nio-8071-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
configserver-ms  | 2025-03-16T08:47:58.660Z  INFO 1 --- [configserver] [nio-8071-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
loans-ms         | Standard Commons Logging discovery in action with spring-jcl: please remove commons-logging.jar from classpath in order to avoid potential conflicts
cards-ms         | Standard Commons Logging discovery in action with spring-jcl: please remove commons-logging.jar from classpath in order to avoid potential conflicts
loans-ms         | Standard Commons Logging discovery in action with spring-jcl: please remove commons-logging.jar from classpath in order to avoid potential conflicts
configserver-ms  | 2025-03-16T08:48:01.491Z  INFO 1 --- [configserver] [nio-8071-exec-2] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/loans.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
configserver-ms  | 2025-03-16T08:48:02.278Z  INFO 1 --- [configserver] [nio-8071-exec-4] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/accounts.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
configserver-ms  | 2025-03-16T08:48:03.100Z  INFO 1 --- [configserver] [nio-8071-exec-3] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/cards.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
configserver-ms  | 2025-03-16T08:48:03.894Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/accounts-prod.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
configserver-ms  | 2025-03-16T08:48:03.894Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/accounts.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
accounts-ms      | 
accounts-ms      |   .   ____          _            __ _ _
accounts-ms      |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
accounts-ms      | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
accounts-ms      |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
accounts-ms      |   '  |____| .__|_| |_|_| |_\__, | / / / /
accounts-ms      |  =========|_|==============|___/=/_/_/_/
accounts-ms      | 
accounts-ms      |  :: Spring Boot ::                (v3.4.3)
accounts-ms      | 
accounts-ms      | 08:48:04.093 INFO  [restartedMain] c.c.a.AccountsApplication - Starting AccountsApplication using Java 21.0.6 with PID 1 (/app/classes started by root in /)
accounts-ms      | 08:48:04.095 INFO  [restartedMain] c.c.a.AccountsApplication - The following 1 profile is active: "prod"
accounts-ms      | 08:48:04.157 INFO  [restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader - Fetching config from server at : http://configserver:8071/
accounts-ms      | 08:48:04.157 INFO  [restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader - Located environment: name=accounts, profiles=[default], label=null, version=40bf119230b9b8663b12c80818a9a512394625f3, state=
accounts-ms      | 08:48:04.157 INFO  [restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader - Fetching config from server at : http://configserver:8071/
accounts-ms      | 08:48:04.157 INFO  [restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader - Located environment: name=accounts, profiles=[prod], label=null, version=40bf119230b9b8663b12c80818a9a512394625f3, state=
accounts-ms      | 08:48:04.159 INFO  [restartedMain] o.s.b.d.e.DevToolsPropertyDefaultsPostProcessor - Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
accounts-ms      | 08:48:04.159 INFO  [restartedMain] o.s.b.d.e.DevToolsPropertyDefaultsPostProcessor - For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
configserver-ms  | 2025-03-16T08:48:04.678Z  INFO 1 --- [configserver] [nio-8071-exec-5] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/loans-prod.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
configserver-ms  | 2025-03-16T08:48:04.678Z  INFO 1 --- [configserver] [nio-8071-exec-5] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/loans.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
loans-ms         | 
loans-ms         |   .   ____          _            __ _ _
loans-ms         |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
loans-ms         | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
loans-ms         |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
loans-ms         |   '  |____| .__|_| |_|_| |_\__, | / / / /
loans-ms         |  =========|_|==============|___/=/_/_/_/
loans-ms         | 
loans-ms         |  :: Spring Boot ::                (v3.4.1)
loans-ms         | 
loans-ms         | 2025-03-16T08:48:04.866Z  INFO 1 --- [loans] [  restartedMain] com.eazybytes.loans.LoansApplication     : Starting LoansApplication using Java 21.0.5 with PID 1 (/app/classes started by root in /)
loans-ms         | 2025-03-16T08:48:04.868Z  INFO 1 --- [loans] [  restartedMain] com.eazybytes.loans.LoansApplication     : The following 1 profile is active: "prod"
loans-ms         | 2025-03-16T08:48:04.923Z  INFO 1 --- [loans] [  restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://configserver:8071/
loans-ms         | 2025-03-16T08:48:04.923Z  INFO 1 --- [loans] [  restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader   : Located environment: name=loans, profiles=[default], label=null, version=40bf119230b9b8663b12c80818a9a512394625f3, state=
loans-ms         | 2025-03-16T08:48:04.923Z  INFO 1 --- [loans] [  restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://localhost:8071/
loans-ms         | 2025-03-16T08:48:04.923Z  INFO 1 --- [loans] [  restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader   : Exception on Url - http://localhost:8071/:org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8071/loans/default": Connection refused. Will be trying the next url if available
loans-ms         | 2025-03-16T08:48:04.923Z  WARN 1 --- [loans] [  restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader   : Could not locate PropertySource ([ConfigServerConfigDataResource@573d692f uris = array<String>['http://localhost:8071/'], optional = true, profiles = 'default']): I/O error on GET request for "http://localhost:8071/loans/default": Connection refused
loans-ms         | 2025-03-16T08:48:04.923Z  INFO 1 --- [loans] [  restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://configserver:8071/
loans-ms         | 2025-03-16T08:48:04.923Z  INFO 1 --- [loans] [  restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader   : Located environment: name=loans, profiles=[prod], label=null, version=40bf119230b9b8663b12c80818a9a512394625f3, state=
loans-ms         | 2025-03-16T08:48:04.924Z  INFO 1 --- [loans] [  restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://localhost:8071/
loans-ms         | 2025-03-16T08:48:04.924Z  INFO 1 --- [loans] [  restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader   : Exception on Url - http://localhost:8071/:org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8071/loans/prod": Connection refused. Will be trying the next url if available
loans-ms         | 2025-03-16T08:48:04.924Z  WARN 1 --- [loans] [  restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader   : Could not locate PropertySource ([ConfigServerConfigDataResource@361362d0 uris = array<String>['http://localhost:8071/'], optional = true, profiles = 'prod']): I/O error on GET request for "http://localhost:8071/loans/prod": Connection refused
loans-ms         | 2025-03-16T08:48:04.924Z  INFO 1 --- [loans] [  restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://localhost:8071/
loans-ms         | 2025-03-16T08:48:04.924Z  INFO 1 --- [loans] [  restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader   : Exception on Url - http://localhost:8071/:org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8071/loans/default": Connection refused. Will be trying the next url if available
loans-ms         | 2025-03-16T08:48:04.924Z  WARN 1 --- [loans] [  restartedMain] o.s.c.c.c.ConfigServerConfigDataLoader   : Could not locate PropertySource ([ConfigServerConfigDataResource@62056a31 uris = array<String>['http://localhost:8071/'], optional = true, profiles = 'default']): I/O error on GET request for "http://localhost:8071/loans/default": Connection refused
loans-ms         | 2025-03-16T08:48:04.926Z  INFO 1 --- [loans] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
loans-ms         | 2025-03-16T08:48:04.927Z  INFO 1 --- [loans] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
configserver-ms  | 2025-03-16T08:48:05.466Z  INFO 1 --- [configserver] [nio-8071-exec-8] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/cards-prod.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
configserver-ms  | 2025-03-16T08:48:05.467Z  INFO 1 --- [configserver] [nio-8071-exec-8] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/cards.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
accounts-ms      | 08:48:05.499 INFO  [restartedMain] o.s.d.r.c.RepositoryConfigurationDelegate - Bootstrapping Spring Data JPA repositories in DEFAULT mode.
cards-ms         | 
cards-ms         |   .   ____          _            __ _ _
cards-ms         |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
cards-ms         | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
cards-ms         |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
cards-ms         |   '  |____| .__|_| |_|_| |_\__, | / / / /
cards-ms         |  =========|_|==============|___/=/_/_/_/
cards-ms         | 
cards-ms         |  :: Spring Boot ::                (v3.4.1)
cards-ms         | 
accounts-ms      | 08:48:05.681 INFO  [restartedMain] o.s.d.r.c.RepositoryConfigurationDelegate - Finished Spring Data repository scanning in 166 ms. Found 2 JPA repository interfaces.
cards-ms         | 2025-03-16T08:48:05.692Z  INFO 1 --- [cards] [           main] com.eazybytes.cards.CardsApplication     : Starting CardsApplication using Java 21.0.5 with PID 1 (/app/classes started by root in /)
cards-ms         | 2025-03-16T08:48:05.695Z  INFO 1 --- [cards] [           main] com.eazybytes.cards.CardsApplication     : The following 1 profile is active: "prod"
cards-ms         | 2025-03-16T08:48:05.780Z  INFO 1 --- [cards] [           main] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://configserver:8071/
cards-ms         | 2025-03-16T08:48:05.780Z  INFO 1 --- [cards] [           main] o.s.c.c.c.ConfigServerConfigDataLoader   : Located environment: name=cards, profiles=[default], label=null, version=40bf119230b9b8663b12c80818a9a512394625f3, state=
cards-ms         | 2025-03-16T08:48:05.781Z  INFO 1 --- [cards] [           main] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://localhost:8071/
cards-ms         | 2025-03-16T08:48:05.781Z  INFO 1 --- [cards] [           main] o.s.c.c.c.ConfigServerConfigDataLoader   : Exception on Url - http://localhost:8071/:org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8071/cards/default": Connection refused. Will be trying the next url if available
cards-ms         | 2025-03-16T08:48:05.781Z  WARN 1 --- [cards] [           main] o.s.c.c.c.ConfigServerConfigDataLoader   : Could not locate PropertySource ([ConfigServerConfigDataResource@907f2b7 uris = array<String>['http://localhost:8071/'], optional = true, profiles = 'default']): I/O error on GET request for "http://localhost:8071/cards/default": Connection refused
cards-ms         | 2025-03-16T08:48:05.781Z  INFO 1 --- [cards] [           main] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://configserver:8071/
cards-ms         | 2025-03-16T08:48:05.781Z  INFO 1 --- [cards] [           main] o.s.c.c.c.ConfigServerConfigDataLoader   : Located environment: name=cards, profiles=[prod], label=null, version=40bf119230b9b8663b12c80818a9a512394625f3, state=
cards-ms         | 2025-03-16T08:48:05.781Z  INFO 1 --- [cards] [           main] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://localhost:8071/
cards-ms         | 2025-03-16T08:48:05.782Z  INFO 1 --- [cards] [           main] o.s.c.c.c.ConfigServerConfigDataLoader   : Exception on Url - http://localhost:8071/:org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8071/cards/prod": Connection refused. Will be trying the next url if available
cards-ms         | 2025-03-16T08:48:05.782Z  WARN 1 --- [cards] [           main] o.s.c.c.c.ConfigServerConfigDataLoader   : Could not locate PropertySource ([ConfigServerConfigDataResource@df1cff6 uris = array<String>['http://localhost:8071/'], optional = true, profiles = 'prod']): I/O error on GET request for "http://localhost:8071/cards/prod": Connection refused
cards-ms         | 2025-03-16T08:48:05.782Z  INFO 1 --- [cards] [           main] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://localhost:8071/
cards-ms         | 2025-03-16T08:48:05.782Z  INFO 1 --- [cards] [           main] o.s.c.c.c.ConfigServerConfigDataLoader   : Exception on Url - http://localhost:8071/:org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8071/cards/default": Connection refused. Will be trying the next url if available
cards-ms         | 2025-03-16T08:48:05.782Z  WARN 1 --- [cards] [           main] o.s.c.c.c.ConfigServerConfigDataLoader   : Could not locate PropertySource ([ConfigServerConfigDataResource@4925f4f5 uris = array<String>['http://localhost:8071/'], optional = true, profiles = 'default']): I/O error on GET request for "http://localhost:8071/cards/default": Connection refused
accounts-ms      | 08:48:05.965 INFO  [restartedMain] o.s.i.c.DefaultConfiguringBeanFactoryPostProcessor - No bean named 'errorChannel' has been explicitly defined. Therefore, a default PublishSubscribeChannel will be created.
accounts-ms      | 08:48:05.972 INFO  [restartedMain] o.s.i.c.DefaultConfiguringBeanFactoryPostProcessor - No bean named 'integrationHeaderChannelRegistry' has been explicitly defined. Therefore, a default DefaultHeaderChannelRegistry will be created.
accounts-ms      | 08:48:06.107 INFO  [restartedMain] o.s.c.c.s.GenericScope - BeanFactory id=58494d00-109a-3d04-8c13-dd8e65799392
loans-ms         | 2025-03-16T08:48:06.548Z  INFO 1 --- [loans] [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
loans-ms         | 2025-03-16T08:48:06.732Z  INFO 1 --- [loans] [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 170 ms. Found 1 JPA repository interface.
loans-ms         | 2025-03-16T08:48:06.953Z  INFO 1 --- [loans] [  restartedMain] faultConfiguringBeanFactoryPostProcessor : No bean named 'errorChannel' has been explicitly defined. Therefore, a default PublishSubscribeChannel will be created.
loans-ms         | 2025-03-16T08:48:06.968Z  INFO 1 --- [loans] [  restartedMain] faultConfiguringBeanFactoryPostProcessor : No bean named 'integrationHeaderChannelRegistry' has been explicitly defined. Therefore, a default DefaultHeaderChannelRegistry will be created.
accounts-ms      | 08:48:07.091 INFO  [restartedMain] o.s.b.w.e.t.TomcatWebServer - Tomcat initialized with port 8080 (http)
loans-ms         | 2025-03-16T08:48:07.092Z  INFO 1 --- [loans] [  restartedMain] o.s.cloud.context.scope.GenericScope     : BeanFactory id=d3cef844-38e0-3f4c-b213-04aa8a32e420
accounts-ms      | 08:48:07.113 INFO  [restartedMain] o.a.c.c.StandardService - Starting service [Tomcat]
accounts-ms      | 08:48:07.113 INFO  [restartedMain] o.a.c.c.StandardEngine - Starting Servlet engine: [Apache Tomcat/10.1.36]
accounts-ms      | 08:48:07.176 INFO  [restartedMain] o.a.c.c.C.[.[.[/] - Initializing Spring embedded WebApplicationContext
accounts-ms      | 08:48:07.178 INFO  [restartedMain] o.s.b.w.s.c.ServletWebServerApplicationContext - Root WebApplicationContext: initialization completed in 3017 ms
cards-ms         | 2025-03-16T08:48:07.435Z  INFO 1 --- [cards] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
accounts-ms      | 08:48:07.525 INFO  [restartedMain] c.z.h.HikariDataSource - HikariPool-1 - Starting...
cards-ms         | 2025-03-16T08:48:07.654Z  INFO 1 --- [cards] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 203 ms. Found 1 JPA repository interface.
accounts-ms      | 08:48:07.711 INFO  [restartedMain] c.z.h.p.HikariPool - HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:testdb user=SA
accounts-ms      | 08:48:07.715 INFO  [restartedMain] c.z.h.HikariDataSource - HikariPool-1 - Start completed.
cards-ms         | 2025-03-16T08:48:07.870Z  INFO 1 --- [cards] [           main] faultConfiguringBeanFactoryPostProcessor : No bean named 'errorChannel' has been explicitly defined. Therefore, a default PublishSubscribeChannel will be created.
cards-ms         | 2025-03-16T08:48:07.882Z  INFO 1 --- [cards] [           main] faultConfiguringBeanFactoryPostProcessor : No bean named 'integrationHeaderChannelRegistry' has been explicitly defined. Therefore, a default DefaultHeaderChannelRegistry will be created.
accounts-ms      | 08:48:07.887 INFO  [restartedMain] o.h.j.i.u.LogHelper - HHH000204: Processing PersistenceUnitInfo [name: default]
accounts-ms      | 08:48:07.931 INFO  [restartedMain] o.h.Version - HHH000412: Hibernate ORM core version 6.6.8.Final
accounts-ms      | 08:48:07.974 INFO  [restartedMain] o.h.c.i.RegionFactoryInitiator - HHH000026: Second-level cache disabled
cards-ms         | 2025-03-16T08:48:08.026Z  INFO 1 --- [cards] [           main] o.s.cloud.context.scope.GenericScope     : BeanFactory id=a9265224-d5ad-3fe1-b44b-d902222a1a36
loans-ms         | 2025-03-16T08:48:08.189Z  INFO 1 --- [loans] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8090 (http)
loans-ms         | 2025-03-16T08:48:08.208Z  INFO 1 --- [loans] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
loans-ms         | 2025-03-16T08:48:08.209Z  INFO 1 --- [loans] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.34]
accounts-ms      | 08:48:08.261 INFO  [restartedMain] o.s.o.j.p.SpringPersistenceUnitInfo - No LoadTimeWeaver setup: ignoring JPA class transformer
loans-ms         | 2025-03-16T08:48:08.270Z  INFO 1 --- [loans] [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
loans-ms         | 2025-03-16T08:48:08.272Z  INFO 1 --- [loans] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 3339 ms
loans-ms         | Standard Commons Logging discovery in action with spring-jcl: please remove commons-logging.jar from classpath in order to avoid potential conflicts
accounts-ms      | 08:48:08.390 WARN  [restartedMain] o.h.o.deprecation - HHH90000025: H2Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
accounts-ms      | 08:48:08.404 INFO  [restartedMain] o.h.o.c.pooling - HHH10001005: Database info:
accounts-ms      | 	Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']
accounts-ms      | 	Database driver: undefined/unknown
accounts-ms      | 	Database version: 2.3.232
accounts-ms      | 	Autocommit mode: undefined/unknown
accounts-ms      | 	Isolation level: undefined/unknown
accounts-ms      | 	Minimum pool size: undefined/unknown
accounts-ms      | 	Maximum pool size: undefined/unknown
loans-ms         | 2025-03-16T08:48:08.588Z  INFO 1 --- [loans] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
loans-ms         | 2025-03-16T08:48:08.823Z  INFO 1 --- [loans] [  restartedMain] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:testdb user=SA
loans-ms         | 2025-03-16T08:48:08.828Z  INFO 1 --- [loans] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
cards-ms         | 2025-03-16T08:48:08.896Z  INFO 1 --- [cards] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 9000 (http)
cards-ms         | 2025-03-16T08:48:08.917Z  INFO 1 --- [cards] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
cards-ms         | 2025-03-16T08:48:08.917Z  INFO 1 --- [cards] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.34]
loans-ms         | 2025-03-16T08:48:08.979Z  INFO 1 --- [loans] [  restartedMain] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
cards-ms         | 2025-03-16T08:48:09.001Z  INFO 1 --- [cards] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
cards-ms         | 2025-03-16T08:48:09.002Z  INFO 1 --- [cards] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 3215 ms
cards-ms         | Standard Commons Logging discovery in action with spring-jcl: please remove commons-logging.jar from classpath in order to avoid potential conflicts
loans-ms         | 2025-03-16T08:48:09.044Z  INFO 1 --- [loans] [  restartedMain] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.6.4.Final
loans-ms         | 2025-03-16T08:48:09.086Z  INFO 1 --- [loans] [  restartedMain] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
cards-ms         | 2025-03-16T08:48:09.312Z  INFO 1 --- [cards] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
accounts-ms      | 08:48:09.323 INFO  [restartedMain] o.h.e.t.j.p.i.JtaPlatformInitiator - HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
loans-ms         | 2025-03-16T08:48:09.355Z  INFO 1 --- [loans] [  restartedMain] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
accounts-ms      | Hibernate: create table account (account_number bigint not null, created_at timestamp(6), created_by varchar(255), updated_at timestamp(6), updated_by varchar(255), account_type varchar(255), branch_address varchar(255), customer_id bigint, primary key (account_number))
accounts-ms      | Hibernate: alter table if exists customer alter column customer_id set data type bigint
accounts-ms      | Hibernate: alter table if exists customer alter column created_at set data type timestamp(6)
accounts-ms      | Hibernate: alter table if exists customer alter column created_by set data type varchar(255)
accounts-ms      | Hibernate: alter table if exists customer alter column updated_at set data type timestamp(6)
accounts-ms      | Hibernate: alter table if exists customer alter column updated_by set data type varchar(255)
accounts-ms      | Hibernate: alter table if exists customer alter column email set data type varchar(255)
accounts-ms      | Hibernate: alter table if exists customer alter column mobile_number set data type varchar(255)
accounts-ms      | Hibernate: alter table if exists customer alter column name set data type varchar(255)
accounts-ms      | 08:48:09.424 INFO  [restartedMain] o.s.o.j.LocalContainerEntityManagerFactoryBean - Initialized JPA EntityManagerFactory for persistence unit 'default'
loans-ms         | 2025-03-16T08:48:09.497Z  WARN 1 --- [loans] [  restartedMain] org.hibernate.orm.deprecation            : HHH90000025: H2Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
cards-ms         | 2025-03-16T08:48:09.515Z  INFO 1 --- [cards] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:testdb user=SA
cards-ms         | 2025-03-16T08:48:09.518Z  INFO 1 --- [cards] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
loans-ms         | 2025-03-16T08:48:09.520Z  INFO 1 --- [loans] [  restartedMain] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
loans-ms         | 	Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']
loans-ms         | 	Database driver: undefined/unknown
loans-ms         | 	Database version: 2.3.232
loans-ms         | 	Autocommit mode: undefined/unknown
loans-ms         | 	Isolation level: undefined/unknown
loans-ms         | 	Minimum pool size: undefined/unknown
loans-ms         | 	Maximum pool size: undefined/unknown
cards-ms         | 2025-03-16T08:48:09.680Z  INFO 1 --- [cards] [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
cards-ms         | 2025-03-16T08:48:09.744Z  INFO 1 --- [cards] [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.6.4.Final
cards-ms         | 2025-03-16T08:48:09.785Z  INFO 1 --- [cards] [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
cards-ms         | 2025-03-16T08:48:10.073Z  INFO 1 --- [cards] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
cards-ms         | 2025-03-16T08:48:10.205Z  WARN 1 --- [cards] [           main] org.hibernate.orm.deprecation            : HHH90000025: H2Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
cards-ms         | 2025-03-16T08:48:10.228Z  INFO 1 --- [cards] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
cards-ms         | 	Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']
cards-ms         | 	Database driver: undefined/unknown
cards-ms         | 	Database version: 2.3.232
cards-ms         | 	Autocommit mode: undefined/unknown
cards-ms         | 	Isolation level: undefined/unknown
cards-ms         | 	Minimum pool size: undefined/unknown
cards-ms         | 	Maximum pool size: undefined/unknown
accounts-ms      | 08:48:10.330 WARN  [restartedMain] o.s.b.a.o.j.JpaBaseConfiguration$JpaWebConfiguration - spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
loans-ms         | 2025-03-16T08:48:10.472Z  INFO 1 --- [loans] [  restartedMain] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
loans-ms         | Hibernate: alter table if exists loans alter column loan_id set data type bigint
loans-ms         | Hibernate: alter table if exists loans alter column created_at set data type timestamp(6)
loans-ms         | Hibernate: alter table if exists loans alter column created_by set data type varchar(255)
loans-ms         | Hibernate: alter table if exists loans alter column updated_at set data type timestamp(6)
loans-ms         | Hibernate: alter table if exists loans alter column updated_by set data type varchar(255)
loans-ms         | Hibernate: alter table if exists loans alter column loan_number set data type varchar(255)
loans-ms         | Hibernate: alter table if exists loans alter column loan_type set data type varchar(255)
loans-ms         | Hibernate: alter table if exists loans alter column mobile_number set data type varchar(255)
loans-ms         | 2025-03-16T08:48:10.602Z  INFO 1 --- [loans] [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
cards-ms         | 2025-03-16T08:48:11.206Z  INFO 1 --- [cards] [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
cards-ms         | Hibernate: alter table if exists cards alter column card_id set data type bigint
cards-ms         | Hibernate: alter table if exists cards alter column created_at set data type timestamp(6)
cards-ms         | Hibernate: alter table if exists cards alter column created_by set data type varchar(255)
cards-ms         | Hibernate: alter table if exists cards alter column updated_at set data type timestamp(6)
cards-ms         | Hibernate: alter table if exists cards alter column updated_by set data type varchar(255)
cards-ms         | Hibernate: alter table if exists cards alter column card_number set data type varchar(255)
cards-ms         | Hibernate: alter table if exists cards alter column card_type set data type varchar(255)
cards-ms         | Hibernate: alter table if exists cards alter column mobile_number set data type varchar(255)
cards-ms         | 2025-03-16T08:48:11.317Z  INFO 1 --- [cards] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
loans-ms         | 2025-03-16T08:48:11.601Z  WARN 1 --- [loans] [  restartedMain] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
accounts-ms      | 08:48:12.156 INFO  [restartedMain] o.s.c.s.m.DirectWithAttributesChannel - Channel 'accounts.springCloudBusInput' has 1 subscriber(s).
cards-ms         | 2025-03-16T08:48:12.388Z  WARN 1 --- [cards] [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
accounts-ms      | 08:48:12.462 INFO  [restartedMain] o.s.b.a.h.H2ConsoleAutoConfiguration - H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:testdb'
accounts-ms      | 08:48:12.554 INFO  [restartedMain] o.s.b.d.a.OptionalLiveReloadServer - LiveReload server is running on port 35729
accounts-ms      | 08:48:12.664 INFO  [restartedMain] o.s.b.a.e.w.EndpointLinksResolver - Exposing 23 endpoints beneath base path '/actuator'
accounts-ms      | 08:48:12.862 INFO  [restartedMain] o.s.i.e.EventDrivenConsumer - Adding {logging-channel-adapter:_org.springframework.integration.errorLogger} as a subscriber to the 'errorChannel' channel
accounts-ms      | 08:48:12.862 INFO  [restartedMain] o.s.i.c.PublishSubscribeChannel - Channel 'accounts.errorChannel' has 1 subscriber(s).
accounts-ms      | 08:48:12.863 INFO  [restartedMain] o.s.i.e.EventDrivenConsumer - started bean '_org.springframework.integration.errorLogger'
accounts-ms      | 08:48:12.950 INFO  [restartedMain] o.s.b.w.e.t.TomcatWebServer - Tomcat started on port 8080 (http) with context path '/'
accounts-ms      | 08:48:12.956 INFO  [restartedMain] o.s.c.s.b.DefaultBinderFactory - Creating binder: rabbit
accounts-ms      | 08:48:12.956 INFO  [restartedMain] o.s.c.s.b.DefaultBinderFactory - Constructing binder child context for rabbit
accounts-ms      | 08:48:13.097 INFO  [restartedMain] o.s.c.s.b.DefaultBinderFactory - Caching the binder: rabbit
accounts-ms      | 08:48:13.135 INFO  [restartedMain] o.s.c.s.b.r.p.RabbitExchangeQueueProvisioner - declaring queue for inbound: springCloudBus.anonymous.tsfXDBq9Rb2QBbKq5CMkAg, bound to: springCloudBus
accounts-ms      | 08:48:13.145 INFO  [restartedMain] o.s.a.r.c.CachingConnectionFactory - Attempting to connect to: [rabbit:5672]
rabbit-1         | 2025-03-16 08:48:13.163437+00:00 [info] <0.841.0> accepting AMQP connection 172.29.0.5:39052 -> 172.29.0.2:5672
rabbit-1         | 2025-03-16 08:48:13.189068+00:00 [info] <0.841.0> connection 172.29.0.5:39052 -> 172.29.0.2:5672 has a client-provided name: rabbitConnectionFactory#2a685748:0
rabbit-1         | 2025-03-16 08:48:13.196243+00:00 [info] <0.841.0> connection 172.29.0.5:39052 -> 172.29.0.2:5672 - rabbitConnectionFactory#2a685748:0: user 'guest' authenticated and granted access to vhost '/'
accounts-ms      | 08:48:13.201 INFO  [restartedMain] o.s.a.r.c.CachingConnectionFactory - Created new connection: rabbitConnectionFactory#2a685748:0/SimpleConnection@fef693a [delegate=amqp://guest@172.29.0.2:5672/, localPort=39052]
accounts-ms      | 08:48:13.273 INFO  [restartedMain] o.s.c.s.b.BinderErrorChannel - Channel 'rabbit-879011851.springCloudBusInput.errors' has 1 subscriber(s).
accounts-ms      | 08:48:13.274 INFO  [restartedMain] o.s.c.s.b.BinderErrorChannel - Channel 'rabbit-879011851.springCloudBusInput.errors' has 2 subscriber(s).
accounts-ms      | 08:48:13.302 INFO  [restartedMain] o.s.i.a.i.AmqpInboundChannelAdapter - started bean 'inbound.springCloudBus.anonymous.tsfXDBq9Rb2QBbKq5CMkAg'
accounts-ms      | 08:48:13.400 INFO  [restartedMain] c.c.a.AccountsApplication - Started AccountsApplication in 13.219 seconds (process running for 13.998)
loans-ms         | 2025-03-16T08:48:13.547Z  INFO 1 --- [loans] [  restartedMain] o.s.c.s.m.DirectWithAttributesChannel    : Channel 'loans.springCloudBusInput' has 1 subscriber(s).
loans-ms         | 2025-03-16T08:48:13.810Z  INFO 1 --- [loans] [  restartedMain] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:testdb'
loans-ms         | 2025-03-16T08:48:13.893Z  INFO 1 --- [loans] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
loans-ms         | 2025-03-16T08:48:13.974Z  INFO 1 --- [loans] [  restartedMain] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 23 endpoints beneath base path '/actuator'
cards-ms         | 2025-03-16T08:48:14.086Z  INFO 1 --- [cards] [           main] o.s.c.s.m.DirectWithAttributesChannel    : Channel 'cards.springCloudBusInput' has 1 subscriber(s).
loans-ms         | 2025-03-16T08:48:14.131Z  INFO 1 --- [loans] [  restartedMain] o.s.i.endpoint.EventDrivenConsumer       : Adding {logging-channel-adapter:_org.springframework.integration.errorLogger} as a subscriber to the 'errorChannel' channel
loans-ms         | 2025-03-16T08:48:14.131Z  INFO 1 --- [loans] [  restartedMain] o.s.i.channel.PublishSubscribeChannel    : Channel 'loans.errorChannel' has 1 subscriber(s).
loans-ms         | 2025-03-16T08:48:14.131Z  INFO 1 --- [loans] [  restartedMain] o.s.i.endpoint.EventDrivenConsumer       : started bean '_org.springframework.integration.errorLogger'
loans-ms         | 2025-03-16T08:48:14.213Z  INFO 1 --- [loans] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8090 (http) with context path '/'
loans-ms         | 2025-03-16T08:48:14.218Z  INFO 1 --- [loans] [  restartedMain] o.s.c.s.binder.DefaultBinderFactory      : Creating binder: rabbit
loans-ms         | 2025-03-16T08:48:14.218Z  INFO 1 --- [loans] [  restartedMain] o.s.c.s.binder.DefaultBinderFactory      : Constructing binder child context for rabbit
cards-ms         | 2025-03-16T08:48:14.277Z  INFO 1 --- [cards] [           main] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:testdb'
loans-ms         | 2025-03-16T08:48:14.337Z  INFO 1 --- [loans] [  restartedMain] o.s.c.s.binder.DefaultBinderFactory      : Caching the binder: rabbit
loans-ms         | 2025-03-16T08:48:14.364Z  INFO 1 --- [loans] [  restartedMain] c.s.b.r.p.RabbitExchangeQueueProvisioner : declaring queue for inbound: springCloudBus.anonymous.i0nWSoDwQHaFuBWcgYne1g, bound to: springCloudBus
cards-ms         | 2025-03-16T08:48:14.365Z  INFO 1 --- [cards] [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 23 endpoints beneath base path '/actuator'
loans-ms         | 2025-03-16T08:48:14.369Z  INFO 1 --- [loans] [  restartedMain] o.s.a.r.c.CachingConnectionFactory       : Attempting to connect to: [rabbit:5672]
rabbit-1         | 2025-03-16 08:48:14.381734+00:00 [info] <0.861.0> accepting AMQP connection 172.29.0.4:52374 -> 172.29.0.2:5672
rabbit-1         | 2025-03-16 08:48:14.399329+00:00 [info] <0.861.0> connection 172.29.0.4:52374 -> 172.29.0.2:5672 has a client-provided name: rabbitConnectionFactory#1628a4aa:0
rabbit-1         | 2025-03-16 08:48:14.403823+00:00 [info] <0.861.0> connection 172.29.0.4:52374 -> 172.29.0.2:5672 - rabbitConnectionFactory#1628a4aa:0: user 'guest' authenticated and granted access to vhost '/'
loans-ms         | 2025-03-16T08:48:14.409Z  INFO 1 --- [loans] [  restartedMain] o.s.a.r.c.CachingConnectionFactory       : Created new connection: rabbitConnectionFactory#1628a4aa:0/SimpleConnection@38c949c1 [delegate=amqp://guest@172.29.0.2:5672/, localPort=52374]
loans-ms         | 2025-03-16T08:48:14.463Z  INFO 1 --- [loans] [  restartedMain] o.s.c.stream.binder.BinderErrorChannel   : Channel 'rabbit-464543499.springCloudBusInput.errors' has 1 subscriber(s).
loans-ms         | 2025-03-16T08:48:14.465Z  INFO 1 --- [loans] [  restartedMain] o.s.c.stream.binder.BinderErrorChannel   : Channel 'rabbit-464543499.springCloudBusInput.errors' has 2 subscriber(s).
loans-ms         | 2025-03-16T08:48:14.486Z  INFO 1 --- [loans] [  restartedMain] o.s.i.a.i.AmqpInboundChannelAdapter      : started bean 'inbound.springCloudBus.anonymous.i0nWSoDwQHaFuBWcgYne1g'
cards-ms         | 2025-03-16T08:48:14.514Z  INFO 1 --- [cards] [           main] o.s.i.endpoint.EventDrivenConsumer       : Adding {logging-channel-adapter:_org.springframework.integration.errorLogger} as a subscriber to the 'errorChannel' channel
cards-ms         | 2025-03-16T08:48:14.515Z  INFO 1 --- [cards] [           main] o.s.i.channel.PublishSubscribeChannel    : Channel 'cards.errorChannel' has 1 subscriber(s).
cards-ms         | 2025-03-16T08:48:14.515Z  INFO 1 --- [cards] [           main] o.s.i.endpoint.EventDrivenConsumer       : started bean '_org.springframework.integration.errorLogger'
loans-ms         | 2025-03-16T08:48:14.553Z  INFO 1 --- [loans] [  restartedMain] com.eazybytes.loans.LoansApplication     : Started LoansApplication in 14.457 seconds (process running for 15.296)
cards-ms         | 2025-03-16T08:48:14.577Z  INFO 1 --- [cards] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 9000 (http) with context path '/'
cards-ms         | 2025-03-16T08:48:14.581Z  INFO 1 --- [cards] [           main] o.s.c.s.binder.DefaultBinderFactory      : Creating binder: rabbit
cards-ms         | 2025-03-16T08:48:14.581Z  INFO 1 --- [cards] [           main] o.s.c.s.binder.DefaultBinderFactory      : Constructing binder child context for rabbit
cards-ms         | 2025-03-16T08:48:14.698Z  INFO 1 --- [cards] [           main] o.s.c.s.binder.DefaultBinderFactory      : Caching the binder: rabbit
cards-ms         | 2025-03-16T08:48:14.727Z  INFO 1 --- [cards] [           main] c.s.b.r.p.RabbitExchangeQueueProvisioner : declaring queue for inbound: springCloudBus.anonymous.CqpORbcHRBuuRYYuCJacsw, bound to: springCloudBus
cards-ms         | 2025-03-16T08:48:14.731Z  INFO 1 --- [cards] [           main] o.s.a.r.c.CachingConnectionFactory       : Attempting to connect to: [rabbit:5672]
rabbit-1         | 2025-03-16 08:48:14.742941+00:00 [info] <0.881.0> accepting AMQP connection 172.29.0.6:47218 -> 172.29.0.2:5672
rabbit-1         | 2025-03-16 08:48:14.762842+00:00 [info] <0.881.0> connection 172.29.0.6:47218 -> 172.29.0.2:5672 has a client-provided name: rabbitConnectionFactory#79b07ccd:0
rabbit-1         | 2025-03-16 08:48:14.766065+00:00 [info] <0.881.0> connection 172.29.0.6:47218 -> 172.29.0.2:5672 - rabbitConnectionFactory#79b07ccd:0: user 'guest' authenticated and granted access to vhost '/'
cards-ms         | 2025-03-16T08:48:14.770Z  INFO 1 --- [cards] [           main] o.s.a.r.c.CachingConnectionFactory       : Created new connection: rabbitConnectionFactory#79b07ccd:0/SimpleConnection@9a1d8dd [delegate=amqp://guest@172.29.0.2:5672/, localPort=47218]
cards-ms         | 2025-03-16T08:48:14.822Z  INFO 1 --- [cards] [           main] o.s.c.stream.binder.BinderErrorChannel   : Channel 'rabbit-1562565970.springCloudBusInput.errors' has 1 subscriber(s).
cards-ms         | 2025-03-16T08:48:14.824Z  INFO 1 --- [cards] [           main] o.s.c.stream.binder.BinderErrorChannel   : Channel 'rabbit-1562565970.springCloudBusInput.errors' has 2 subscriber(s).
cards-ms         | 2025-03-16T08:48:14.842Z  INFO 1 --- [cards] [           main] o.s.i.a.i.AmqpInboundChannelAdapter      : started bean 'inbound.springCloudBus.anonymous.CqpORbcHRBuuRYYuCJacsw'
cards-ms         | 2025-03-16T08:48:14.886Z  INFO 1 --- [cards] [           main] com.eazybytes.cards.CardsApplication     : Started CardsApplication in 14.771 seconds (process running for 15.486)
cards-ms         | 2025-03-16T08:48:11.956Z  INFO 1 --- [cards] [nio-9000-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
cards-ms         | 2025-03-16T08:48:11.957Z  INFO 1 --- [cards] [nio-9000-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
cards-ms         | 2025-03-16T08:48:11.960Z  INFO 1 --- [cards] [nio-9000-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 3 ms
loans-ms         | 2025-03-16T08:48:24.836Z  INFO 1 --- [loans] [nio-8090-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
loans-ms         | 2025-03-16T08:48:24.836Z  INFO 1 --- [loans] [nio-8090-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
loans-ms         | 2025-03-16T08:48:24.839Z  INFO 1 --- [loans] [nio-8090-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 3 ms
accounts-ms      | 08:48:31.174 INFO  [http-nio-8080-exec-2] o.a.c.c.C.[.[.[/] - Initializing Spring DispatcherServlet 'dispatcherServlet'
accounts-ms      | 08:48:31.174 INFO  [http-nio-8080-exec-2] o.s.w.s.DispatcherServlet - Initializing Servlet 'dispatcherServlet'
accounts-ms      | 08:48:31.175 INFO  [http-nio-8080-exec-2] o.s.w.s.DispatcherServlet - Completed initialization in 1 ms
configserver-ms  | 2025-03-16T08:49:18.975Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.c.c.monitor.PropertyPathEndpoint     : Refresh for: accounts-prod
configserver-ms  | 2025-03-16T08:49:19.001Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.c.s.m.DirectWithAttributesChannel    : Channel 'configserver.springCloudBusOutput' has 1 subscriber(s).
configserver-ms  | 2025-03-16T08:49:19.095Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.a.r.c.CachingConnectionFactory       : Attempting to connect to: [rabbit:5672]
rabbit-1         | 2025-03-16 08:49:19.097421+00:00 [info] <0.1045.0> accepting AMQP connection 172.29.0.3:46708 -> 172.29.0.2:5672
rabbit-1         | 2025-03-16 08:49:19.099123+00:00 [info] <0.1045.0> connection 172.29.0.3:46708 -> 172.29.0.2:5672 has a client-provided name: rabbitConnectionFactory.publisher#d0b1b1:0
rabbit-1         | 2025-03-16 08:49:19.100199+00:00 [info] <0.1045.0> connection 172.29.0.3:46708 -> 172.29.0.2:5672 - rabbitConnectionFactory.publisher#d0b1b1:0: user 'guest' authenticated and granted access to vhost '/'
configserver-ms  | 2025-03-16T08:49:19.100Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.a.r.c.CachingConnectionFactory       : Created new connection: rabbitConnectionFactory.publisher#d0b1b1:0/SimpleConnection@37dbfe02 [delegate=amqp://guest@172.29.0.2:5672/, localPort=46708]
configserver-ms  | 2025-03-16T08:49:19.104Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.amqp.rabbit.core.RabbitAdmin         : Auto-declaring a non-durable, auto-delete, or exclusive Queue (springCloudBus.anonymous.QHaAgyFIRFyfY_B8ms-T0Q) durable:false, auto-delete:true, exclusive:true. It will be redeclared if the broker stops and is restarted while the connection factory is alive, but all messages will be lost.
configserver-ms  | 2025-03-16T08:49:19.114Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.cloud.bus.event.RefreshListener      : Received remote refresh request.
configserver-ms  | 2025-03-16T08:49:19.114Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.cloud.bus.event.RefreshListener      : Refresh not performed, the event was targeting accounts-prod:**
configserver-ms  | 2025-03-16T08:49:19.115Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.c.c.monitor.PropertyPathEndpoint     : Refresh for: accounts
configserver-ms  | 2025-03-16T08:49:19.118Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.cloud.bus.event.RefreshListener      : Received remote refresh request.
configserver-ms  | 2025-03-16T08:49:19.118Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.cloud.bus.event.RefreshListener      : Refresh not performed, the event was targeting accounts:**
configserver-ms  | 2025-03-16T08:49:19.118Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.c.c.monitor.PropertyPathEndpoint     : Refresh for: cards-prod
configserver-ms  | 2025-03-16T08:49:19.121Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.cloud.bus.event.RefreshListener      : Received remote refresh request.
configserver-ms  | 2025-03-16T08:49:19.122Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.cloud.bus.event.RefreshListener      : Refresh not performed, the event was targeting cards-prod:**
configserver-ms  | 2025-03-16T08:49:19.122Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.c.c.monitor.PropertyPathEndpoint     : Refresh for: cards
configserver-ms  | 2025-03-16T08:49:19.124Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.cloud.bus.event.RefreshListener      : Received remote refresh request.
configserver-ms  | 2025-03-16T08:49:19.125Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.cloud.bus.event.RefreshListener      : Refresh not performed, the event was targeting cards:**
configserver-ms  | 2025-03-16T08:49:19.125Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.c.c.monitor.PropertyPathEndpoint     : Refresh for: loans-prod
configserver-ms  | 2025-03-16T08:49:19.127Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.cloud.bus.event.RefreshListener      : Received remote refresh request.
configserver-ms  | 2025-03-16T08:49:19.127Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.cloud.bus.event.RefreshListener      : Refresh not performed, the event was targeting loans-prod:**
configserver-ms  | 2025-03-16T08:49:19.127Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.c.c.monitor.PropertyPathEndpoint     : Refresh for: loans
configserver-ms  | 2025-03-16T08:49:19.129Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.cloud.bus.event.RefreshListener      : Received remote refresh request.
configserver-ms  | 2025-03-16T08:49:19.129Z  INFO 1 --- [configserver] [nio-8071-exec-6] o.s.cloud.bus.event.RefreshListener      : Refresh not performed, the event was targeting loans:**
accounts-ms      | 08:49:19.160 INFO  [springCloudBus.anonymous.tsfXDBq9Rb2QBbKq5CMkAg-1] o.s.c.b.e.RefreshListener - Received remote refresh request.
cards-ms         | 2025-03-16T08:49:19.168Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.cloud.bus.event.RefreshListener      : Received remote refresh request.
loans-ms         | 2025-03-16T08:49:19.170Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.cloud.bus.event.RefreshListener      : Received remote refresh request.
accounts-ms      | 08:49:19.171 INFO  [springCloudBus.anonymous.tsfXDBq9Rb2QBbKq5CMkAg-1] o.s.c.c.c.ConfigServerConfigDataLoader - Fetching config from server at : http://configserver:8071/
cards-ms         | 2025-03-16T08:49:19.178Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://configserver:8071/
loans-ms         | 2025-03-16T08:49:19.180Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://configserver:8071/
configserver-ms  | 2025-03-16T08:49:20.298Z  INFO 1 --- [configserver] [nio-8071-exec-5] .c.s.e.MultipleJGitEnvironmentRepository : Fetched for remote main and found 1 updates
configserver-ms  | 2025-03-16T08:49:20.312Z  INFO 1 --- [configserver] [nio-8071-exec-5] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/accounts.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
accounts-ms      | 08:49:20.315 INFO  [springCloudBus.anonymous.tsfXDBq9Rb2QBbKq5CMkAg-1] o.s.c.c.c.ConfigServerConfigDataLoader - Located environment: name=accounts, profiles=[default], label=null, version=a96ffcce85314d835828acac179faa750d4d4ec3, state=
accounts-ms      | 08:49:20.324 INFO  [springCloudBus.anonymous.tsfXDBq9Rb2QBbKq5CMkAg-1] o.s.c.c.c.ConfigServerConfigDataLoader - Fetching config from server at : http://configserver:8071/
configserver-ms  | 2025-03-16T08:49:21.061Z  INFO 1 --- [configserver] [nio-8071-exec-7] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/loans.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
loans-ms         | 2025-03-16T08:49:21.065Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Located environment: name=loans, profiles=[default], label=null, version=a96ffcce85314d835828acac179faa750d4d4ec3, state=
loans-ms         | 2025-03-16T08:49:21.072Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://localhost:8071/
loans-ms         | 2025-03-16T08:49:21.073Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Exception on Url - http://localhost:8071/:org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8071/loans/default": Connection refused. Will be trying the next url if available
loans-ms         | 2025-03-16T08:49:21.074Z  WARN 1 --- [loans] [HaFuBWcgYne1g-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Could not locate PropertySource ([ConfigServerConfigDataResource@1700e90e uris = array<String>['http://localhost:8071/'], optional = true, profiles = 'default']): I/O error on GET request for "http://localhost:8071/loans/default": Connection refused
loans-ms         | 2025-03-16T08:49:21.077Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://configserver:8071/
configserver-ms  | 2025-03-16T08:49:21.756Z  INFO 1 --- [configserver] [nio-8071-exec-8] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/cards.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
cards-ms         | 2025-03-16T08:49:21.759Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Located environment: name=cards, profiles=[default], label=null, version=a96ffcce85314d835828acac179faa750d4d4ec3, state=
cards-ms         | 2025-03-16T08:49:21.766Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://localhost:8071/
cards-ms         | 2025-03-16T08:49:21.767Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Exception on Url - http://localhost:8071/:org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8071/cards/default": Connection refused. Will be trying the next url if available
cards-ms         | 2025-03-16T08:49:21.767Z  WARN 1 --- [cards] [BuuRYYuCJacsw-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Could not locate PropertySource ([ConfigServerConfigDataResource@7f0dfab9 uris = array<String>['http://localhost:8071/'], optional = true, profiles = 'default']): I/O error on GET request for "http://localhost:8071/cards/default": Connection refused
cards-ms         | 2025-03-16T08:49:21.770Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://configserver:8071/
configserver-ms  | 2025-03-16T08:49:22.463Z  INFO 1 --- [configserver] [io-8071-exec-10] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/loans-prod.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
configserver-ms  | 2025-03-16T08:49:22.463Z  INFO 1 --- [configserver] [io-8071-exec-10] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/loans.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
loans-ms         | 2025-03-16T08:49:22.465Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Located environment: name=loans, profiles=[prod], label=null, version=a96ffcce85314d835828acac179faa750d4d4ec3, state=
loans-ms         | 2025-03-16T08:49:22.476Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://localhost:8071/
loans-ms         | 2025-03-16T08:49:22.477Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Exception on Url - http://localhost:8071/:org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8071/loans/prod": Connection refused. Will be trying the next url if available
loans-ms         | 2025-03-16T08:49:22.477Z  WARN 1 --- [loans] [HaFuBWcgYne1g-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Could not locate PropertySource ([ConfigServerConfigDataResource@2c319f7c uris = array<String>['http://localhost:8071/'], optional = true, profiles = 'prod']): I/O error on GET request for "http://localhost:8071/loans/prod": Connection refused
loans-ms         | 2025-03-16T08:49:22.478Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://localhost:8071/
loans-ms         | 2025-03-16T08:49:22.479Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Exception on Url - http://localhost:8071/:org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8071/loans/default": Connection refused. Will be trying the next url if available
loans-ms         | 2025-03-16T08:49:22.480Z  WARN 1 --- [loans] [HaFuBWcgYne1g-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Could not locate PropertySource ([ConfigServerConfigDataResource@41d949df uris = array<String>['http://localhost:8071/'], optional = true, profiles = 'default']): I/O error on GET request for "http://localhost:8071/loans/default": Connection refused
loans-ms         | 2025-03-16T08:49:22.498Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
loans-ms         | 2025-03-16T08:49:22.613Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.cloud.bus.event.RefreshListener      : Keys refreshed [config.client.version, loans.message]
loans-ms         | 2025-03-16T08:49:22.633Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.c.s.m.DirectWithAttributesChannel    : Channel 'loans.springCloudBusOutput' has 1 subscriber(s).
loans-ms         | 2025-03-16T08:49:22.649Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.a.r.c.CachingConnectionFactory       : Attempting to connect to: [rabbit:5672]
rabbit-1         | 2025-03-16 08:49:22.650944+00:00 [info] <0.1065.0> accepting AMQP connection 172.29.0.4:40778 -> 172.29.0.2:5672
rabbit-1         | 2025-03-16 08:49:22.652392+00:00 [info] <0.1065.0> connection 172.29.0.4:40778 -> 172.29.0.2:5672 has a client-provided name: rabbitConnectionFactory.publisher#46dcf0fa:0
rabbit-1         | 2025-03-16 08:49:22.653844+00:00 [info] <0.1065.0> connection 172.29.0.4:40778 -> 172.29.0.2:5672 - rabbitConnectionFactory.publisher#46dcf0fa:0: user 'guest' authenticated and granted access to vhost '/'
loans-ms         | 2025-03-16T08:49:22.654Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.a.r.c.CachingConnectionFactory       : Created new connection: rabbitConnectionFactory.publisher#46dcf0fa:0/SimpleConnection@3c4a2f50 [delegate=amqp://guest@172.29.0.2:5672/, localPort=40778]
loans-ms         | 2025-03-16T08:49:22.658Z  INFO 1 --- [loans] [HaFuBWcgYne1g-1] o.s.amqp.rabbit.core.RabbitAdmin         : Auto-declaring a non-durable, auto-delete, or exclusive Queue (springCloudBus.anonymous.i0nWSoDwQHaFuBWcgYne1g) durable:false, auto-delete:true, exclusive:true. It will be redeclared if the broker stops and is restarted while the connection factory is alive, but all messages will be lost.
configserver-ms  | 2025-03-16T08:49:23.185Z  INFO 1 --- [configserver] [nio-8071-exec-9] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/accounts-prod.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
configserver-ms  | 2025-03-16T08:49:23.186Z  INFO 1 --- [configserver] [nio-8071-exec-9] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/accounts.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
accounts-ms      | 08:49:23.188 INFO  [springCloudBus.anonymous.tsfXDBq9Rb2QBbKq5CMkAg-1] o.s.c.c.c.ConfigServerConfigDataLoader - Located environment: name=accounts, profiles=[prod], label=null, version=a96ffcce85314d835828acac179faa750d4d4ec3, state=
accounts-ms      | 08:49:23.206 INFO  [springCloudBus.anonymous.tsfXDBq9Rb2QBbKq5CMkAg-1] o.s.b.d.e.DevToolsPropertyDefaultsPostProcessor - Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
accounts-ms      | 08:49:23.310 INFO  [springCloudBus.anonymous.tsfXDBq9Rb2QBbKq5CMkAg-1] o.s.c.b.e.RefreshListener - Keys refreshed [config.client.version, accounts.message]
accounts-ms      | 08:49:23.331 INFO  [springCloudBus.anonymous.tsfXDBq9Rb2QBbKq5CMkAg-1] o.s.c.s.m.DirectWithAttributesChannel - Channel 'accounts.springCloudBusOutput' has 1 subscriber(s).
accounts-ms      | 08:49:23.346 INFO  [springCloudBus.anonymous.tsfXDBq9Rb2QBbKq5CMkAg-1] o.s.a.r.c.CachingConnectionFactory - Attempting to connect to: [rabbit:5672]
rabbit-1         | 2025-03-16 08:49:23.348209+00:00 [info] <0.1085.0> accepting AMQP connection 172.29.0.5:39980 -> 172.29.0.2:5672
rabbit-1         | 2025-03-16 08:49:23.350363+00:00 [info] <0.1085.0> connection 172.29.0.5:39980 -> 172.29.0.2:5672 has a client-provided name: rabbitConnectionFactory.publisher#6199e059:0
rabbit-1         | 2025-03-16 08:49:23.351213+00:00 [info] <0.1085.0> connection 172.29.0.5:39980 -> 172.29.0.2:5672 - rabbitConnectionFactory.publisher#6199e059:0: user 'guest' authenticated and granted access to vhost '/'
accounts-ms      | 08:49:23.351 INFO  [springCloudBus.anonymous.tsfXDBq9Rb2QBbKq5CMkAg-1] o.s.a.r.c.CachingConnectionFactory - Created new connection: rabbitConnectionFactory.publisher#6199e059:0/SimpleConnection@48eb9402 [delegate=amqp://guest@172.29.0.2:5672/, localPort=39980]
accounts-ms      | 08:49:23.354 INFO  [springCloudBus.anonymous.tsfXDBq9Rb2QBbKq5CMkAg-1] o.s.a.r.c.RabbitAdmin - Auto-declaring a non-durable, auto-delete, or exclusive Queue (springCloudBus.anonymous.tsfXDBq9Rb2QBbKq5CMkAg) durable:false, auto-delete:true, exclusive:true. It will be redeclared if the broker stops and is restarted while the connection factory is alive, but all messages will be lost.
configserver-ms  | 2025-03-16T08:49:23.915Z  INFO 1 --- [configserver] [nio-8071-exec-1] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/cards-prod.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
configserver-ms  | 2025-03-16T08:49:23.915Z  INFO 1 --- [configserver] [nio-8071-exec-1] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: Config resource 'file [/tmp/config-repo-14140442385611983785/cards.yml]' via location 'file:/tmp/config-repo-14140442385611983785/'
cards-ms         | 2025-03-16T08:49:23.917Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Located environment: name=cards, profiles=[prod], label=null, version=a96ffcce85314d835828acac179faa750d4d4ec3, state=
cards-ms         | 2025-03-16T08:49:23.923Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://localhost:8071/
cards-ms         | 2025-03-16T08:49:23.924Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Exception on Url - http://localhost:8071/:org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8071/cards/prod": Connection refused. Will be trying the next url if available
cards-ms         | 2025-03-16T08:49:23.924Z  WARN 1 --- [cards] [BuuRYYuCJacsw-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Could not locate PropertySource ([ConfigServerConfigDataResource@124f311d uris = array<String>['http://localhost:8071/'], optional = true, profiles = 'prod']): I/O error on GET request for "http://localhost:8071/cards/prod": Connection refused
cards-ms         | 2025-03-16T08:49:23.924Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Fetching config from server at : http://localhost:8071/
cards-ms         | 2025-03-16T08:49:23.925Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Exception on Url - http://localhost:8071/:org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8071/cards/default": Connection refused. Will be trying the next url if available
cards-ms         | 2025-03-16T08:49:23.925Z  WARN 1 --- [cards] [BuuRYYuCJacsw-1] o.s.c.c.c.ConfigServerConfigDataLoader   : Could not locate PropertySource ([ConfigServerConfigDataResource@34c2a153 uris = array<String>['http://localhost:8071/'], optional = true, profiles = 'default']): I/O error on GET request for "http://localhost:8071/cards/default": Connection refused
cards-ms         | 2025-03-16T08:49:24.029Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.cloud.bus.event.RefreshListener      : Keys refreshed [config.client.version, cards.message]
cards-ms         | 2025-03-16T08:49:24.051Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.c.s.m.DirectWithAttributesChannel    : Channel 'cards.springCloudBusOutput' has 1 subscriber(s).
cards-ms         | 2025-03-16T08:49:24.065Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.a.r.c.CachingConnectionFactory       : Attempting to connect to: [rabbit:5672]
rabbit-1         | 2025-03-16 08:49:24.066882+00:00 [info] <0.1105.0> accepting AMQP connection 172.29.0.6:33100 -> 172.29.0.2:5672
rabbit-1         | 2025-03-16 08:49:24.068555+00:00 [info] <0.1105.0> connection 172.29.0.6:33100 -> 172.29.0.2:5672 has a client-provided name: rabbitConnectionFactory.publisher#3cee925:0
rabbit-1         | 2025-03-16 08:49:24.069475+00:00 [info] <0.1105.0> connection 172.29.0.6:33100 -> 172.29.0.2:5672 - rabbitConnectionFactory.publisher#3cee925:0: user 'guest' authenticated and granted access to vhost '/'
cards-ms         | 2025-03-16T08:49:24.069Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.a.r.c.CachingConnectionFactory       : Created new connection: rabbitConnectionFactory.publisher#3cee925:0/SimpleConnection@5dfe54a9 [delegate=amqp://guest@172.29.0.2:5672/, localPort=33100]
cards-ms         | 2025-03-16T08:49:24.073Z  INFO 1 --- [cards] [BuuRYYuCJacsw-1] o.s.amqp.rabbit.core.RabbitAdmin         : Auto-declaring a non-durable, auto-delete, or exclusive Queue (springCloudBus.anonymous.CqpORbcHRBuuRYYuCJacsw) durable:false, auto-delete:true, exclusive:true. It will be redeclared if the broker stops and is restarted while the connection factory is alive, but all messages will be lost.

v View in Docker Desktop   o View Config   w Enable Watch
```