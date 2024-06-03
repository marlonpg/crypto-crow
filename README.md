### access h2 db
http://localhost:8080/h2-console/login.do

### coinmarketcap call example
curl --location 'https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?limit=2' \
--header 'X-CMC_PRO_API_KEY: ' \
--header 'Accept: application/json'
