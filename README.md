# SnippetShare

## Start the Application

### Start MySQL Database

```bash
$ cd apps/db                                                 # navigate into db folder
$ docker build -t snippetshare-db:latest .                   # build the image for mysql
$ docker run -p 127.0.0.1:3306:3306 snippetshare-db:latest   # run mysql container
```

### Start Spring Boot

```bash
$ ./gradew bootRun
```