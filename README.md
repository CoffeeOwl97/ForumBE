# Forum App Backend Service
This application serves as the back end for the forum application.

This application is being developed as per requirements of coursework
for CMP6210 Cloud Computing at Birmingham City University.

## Setup

### Requirements

- JDK 8 - available via [Oracle](https://www.oracle.com/java), or alternatives such as [OpenJDK](https://openjdk.java.net/)
- PostgreSQL - available via the [PostgreSQL](https://www.postgresql.org) website. 

It's recommended that you make use of a database management tool such as pgAdmin. The official installer from
PostgreSQL's website will give you the option to install pgAdmin.

### Configuring Properties files

This project makes use of application properties files. These allow us to run this service in different environments, 
pointing to specific property configuration profiles depending upon which environment we are running in.
To set up these properties files follow the steps outlined below.


1. Make a copy of `*/ForumBE/src/main/resources/example-application.properties` in the same location with the name
`application.properties`.
2. Make a copy of `*/ForumBE/src/main/resources/example-application-dev.properties` in the same location with the name
`application-dev.properties`.
    1. If you chose a port other than the default port (5432) when setting up your database, then change the port in 
    the url for the `spring.datasource.url` property.
3. Make a copy of `*/ForumBE/src/main/resources/example-application-prod.properties` in the same location with the name
`application-prod.properties`.

### Database

To set up your database, follow the steps outlined below.
1. Use your database management tool (such as pgAdmin) to create a new database called `forumDB`.
2. Create a new user to access this database. Ensure that they have read and write access.
3. Populate your database schema using the schema file found in the root project directory (`*/ForumBE/schema`)
    1. This can be done in pgAdmin by right clicking on your database and using the `restore` tool, selecting the 
    schema file as the file to restore from.
4. In `application-dev.properties`, populate `spring.datasource.username` and `spring.datasource.password` respectively
with the username and password for the database user you created earlier.

### Building

This project makes use of [Lombok](https://projectlombok.org/). To get this project to build in InteliJ Idea you
will need to download the Lombok plugin and enable annotation processors. 
 - The Lombok plugin can be found under `Preferences -> Plugins` and then search for the 'Lombok' plugin. 
 You will need to restart your IDE to activate this plugin.
 - To enable annotation processing you need to tick the checkbox in the preferences. This can be found under
 `Preferences` by searching for `Annotation Processors`. If this does not display the setting, then you can find it
 manually under `Preferences -> Build, Execution, Deployment -> Compiler -> Annotation Processors`. 

This project is built using [Gradle](https://gradle.org). The Gradle wrapper has been included with the project
so Gradle does not need to be downloaded to build the project.

To build with the Gradle wrapper enter the following into a terminal
```zsh
./gradlew clean build
```

### Running

The application makes use of multiple configuration profiles which are switched out or overridden depending on the
environment that the application is to be run in. The environment to run the application in can be declared in a couple
of ways.

- In Intellij Idea Ultimate edition you can simply enter the name of the environment into the 'Active Profiles' text
box of your Spring Boot run configuration.
- If you have Intellij Idea community edition then you can manually add the parameter 'spring.profiles.active' and then
provide a profile such as 'dev' or 'prod'.
- If running from the command line then use the following command to start the application with the profile set.
```zsh
./gradlew bootRun --args ' --spring.profiles.active={profile}'
```
Replace {profile} with the desired profile.

e.g.
```
./gradlew bootRun --args ' --spring.profiles.active=dev'
```