### access h2 db
http://localhost:8080/h2-console/login.do

### coinmarketcap call example
curl --location 'https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?limit=2' \
--header 'X-CMC_PRO_API_KEY: 1cd4a0da-128c-4a6c-bac2-3a61717bb7f9' \
--header 'Accept: application/json'