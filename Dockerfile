# Dockerfile: the recipe for building the TCMS app image.
#
# It uses a MULTI-STAGE build (two FROM lines):
#   Stage 1 "build" = the kitchen: a big image with Maven + JDK that compiles the code.
#   Stage 2         = the plate:   a small image with only a Java runtime and the final jar.
# Only stage 2 becomes the final image. Maven, the source code and the downloaded
# libraries stay behind in stage 1, so the image we run on the VPS is small and clean.

# ---------- Stage 1: build the jar ----------

# Start from an official image that already has Maven 3.9 and Java 17 (JDK).
# Why: nobody needs Maven or a JDK installed on their machine or on Jenkins, only Docker.
#      The build is identical everywhere.
# "AS build" gives this stage a name, so stage 2 can copy files out of it.
FROM maven:3.9-eclipse-temurin-17 AS build

# Work inside /build (created if missing). Every next command runs from this folder.
WORKDIR /build

# Copy the whole project from our machine into the image.
# Files listed in .dockerignore (target/, .git/, .env, ...) are NOT copied.
COPY . .

# Compile, run the tests and package the fat jar for all modules.
#   -B             = batch mode: plain logs, no colors or progress bars (better in Jenkins).
#   clean package  = delete old output, then compile + test + build the jar.
#                    If one test fails, this line fails, and the whole build stops.
#   --mount=type=cache,target=/root/.m2
#                  = keep Maven's downloaded libraries between builds,
#                    so we don't download hundreds of MB every time.
RUN --mount=type=cache,target=/root/.m2 mvn -B clean package

# ---------- Stage 2: the image we actually run ----------

# Start again from a SMALL image: only the Java 17 runtime (JRE), no Maven, no JDK.
# Why: smaller image = faster to pull, and fewer tools inside = less for an attacker to use.
FROM eclipse-temurin:17-jre

# The app lives in /app.
WORKDIR /app

# Create a normal system user "spring" (uid 1001).
# Why: by default a container runs as root. If someone breaks into the app,
#      they should not get root inside the container.
RUN useradd --system --uid 1001 spring

# Take ONLY the finished jar from stage 1 and name it app.jar.
# api-app is the module that assembles all the other modules into one runnable jar.
COPY --from=build /build/api-app/target/api-app-1.0.0.jar app.jar

# From here on, everything (including the app itself) runs as "spring", not root.
USER spring

# Documents that the app listens on port 8080 inside the container.
# It does NOT open the port to the outside: "ports:" in docker-compose.yaml does that.
EXPOSE 8080

# The command that starts when the container starts: run the fat jar.
ENTRYPOINT ["java", "-jar", "app.jar"]
