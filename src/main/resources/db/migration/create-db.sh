psql postgresql://${DB_USERNAME}:${DB_PASSWORD}@localhost:5432 -c "DROP DATABASE IF EXISTS kaly_db"
psql postgresql://${DB_USERNAME}:${DB_PASSWORD}@localhost:5432 -c "CREATE DATABASE kaly_db"