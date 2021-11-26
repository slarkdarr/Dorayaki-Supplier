# Dorayaki-Supplier
Merupakan web service sebagai perantara komunikasi dorayaki store dan dorayaki server (pabrik) menggunakan komunikasi SOAP.
Threshold yang digunakan untuk maksimal request adalah 10 per 10 menit untuk ip dan endpoint yang ada.

## Requirements
- apache-maven (3.8.3)
- jdk 11.0.13

## Instalation
- cd worldbreakdown
- mvn install
- mvn clean compile assembly:single
- java -jar .\target\worldbreakdown-1.0-SNAPSHOT-jar-with-dependencies.jar

## Basis data yang digunakan
### requests
id, email, recipe_name, quantity, status, createdAt, updatedAt

## Pembagian Tugas
- setup 13519065
- Rate limiter 13519107
- Add dorayaki
- Request Stock 13519197
- Check request stock 13519197
