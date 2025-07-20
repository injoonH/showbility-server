# Showbility

## Getting Started

Copy `.env.example` to `.env` and fill in the required environment variables:

```sh
cp .env.example .env
```

You can create passwords and secrets using the `openssl` command:

```sh
openssl rand -base64 64
```

Use the following command to start the development environment:

```sh
docker compose -f .docker/compose.local.yaml --env-file .env up -d
```

You can check swagger documentation at <http://localhost:8080/swagger-ui/index.html>.
