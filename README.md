# Showbility

## Getting Started

Use the following command to start the development environment:

```sh
docker compose -f .docker/compose.local.yaml --env-file .env up -d
```

You can check swagger documentation at <http://localhost:8080/swagger-ui/index.html>.

Generate migration scripts with:

```sh
./gradlew generateMigrationScripts -Pdescription="This is description"
```
