# Pismobank

Pismobank is a simple sample of a bank-transaction application.

## Running

Create a jar file

```bash
./mvnw clean package
```

Docker
```bash
docker-compose up
```



## Usage
The application contains 3 endpoints:

GET /accounts/{accountId} - search accounts by id
 ```
  {
      "id": 1,
      "documentNumber": "233123123123"
  }
 ```

POST /accounts - register a new account
  ```
   {
      "documentNumber": "233123123123"
   }
  ```

POST /transactions - register a new transaction  ​
​```
​{
​"account_id":1,
​"operation_type_id": 4,
​"amount": 230.00   
​}
​```

