FROM mysql:8.0

ENV MYSQL_DATABASE=snippetshare \
    MYSQL_ROOT_PASSWORD=default

# copy setup-script for creating initital database-tables
COPY migrations/* /docker-entrypoint-initdb.d/

EXPOSE 3306