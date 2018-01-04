Start env in docker:
```sh
sbt stage && docker-compose up --build
```

or run on localhost:
```sh
docker run --rm -d -it -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=postgres postgres && \
  sbt stage && \
  target/universal/stage/bin/rds-test
```
