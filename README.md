# BANK APP  application

This application contains two services one is account-service and the other one is transaction-service.
## Download the app
Get the gode from gitlab by clonning the repo

    git clone https://github.com/dimimai/bankApp.git

## Run the two services
In each service under target folder there is a jar file. Executer both jar files to start the services. The paths define the place where you can execute tha jar files.

    path: account-service/target/
    java -jar account-service-0.0.1-SNAPSHOT.jar

    path: transaction-service/target/
    java -jar transaction-service-0.0.1-SNAPSHOT.jar

# H2 Databasse
To check the embedded databeses for the service you can login in to the h2 console for each service.

account-service

    http://localhost:8000/h2-console

transaction-service

    http://localhost:8100/h2-console

Use for connecting in both the following:

        Driver Class: org.h2.Driver
        JDBC URl: jdbc:h2:mem:testdb
        username: sa
# REST API

The REST API to the bank app is described below.

## Account-service
Below the endpoints for the account service which will listen at

    http://localhost:8000
### Request
Create a new account for an existing custommer with giving the customerId.
`POST   /customers/{customerId}/accounts`

    http://localhost:8000/customers/{customerId}/accounts
    Accept: application/json
    {
        "iban": "string",
    	"balance": 1000,
    	"accountType": "CURRENT"
    }

    Types
        accountType	[ CURRENT, SAVINGS ]
        balance	number($double)
        iban	string
        id	integer($int64)
### Responses
Code 201

    Status: 201 Created
    Content-Type: application/json;charset=UTF-8
        {
        "id": 1,
        "iban": "string",
        "balance": 1000,
        "accountType": "CURRENT"
    }

Code 400

        Status: 400 Bad Request
        Content-Type: application/json;charset=UTF-8
            {
            "timestamp": "2019-06-10T10:06:41.910+0000",
            "message": [
                "Proper account type is required"
            ],
            "details": "uri=/customers/1001/accounts"
            }
Code 404  - Not found when customer does not exist
Code 500  - When an internal server error happened

## Get all transactions

### Request
Get all transactions for a customer with passing the customerId
`GET /accounts/{customerId}/transactions`

    http://localhost:8000/accounts/{customerId}/transactions
### Response

    Status: 200 OK
    Content-Type: application/json;charset=UTF-8
    {
        "name": "John",
        "sureName": "Doe",
        "balance": 6000,
        "transactions": [
            {
                "id": 2,
                "amount": 1000,
                "accountId": 1
            },
            {
                "id": 3,
                "amount": 1000,
                "accountId": 1
            }
        ]
    }

Code 404  - Not found when account does not exist
Code 500  - When an internal server error happened

## Transaction-service
Below the endpoints for the transaction service which will listen at

    http://localhost:8100
### Request
Create a new transaction for an existing account.
`POST   /transactions`

    http://localhost:8100/transactions
    Accept: application/json
    {
    	"amount": 1000,
    	"accountId": "1"
    }

     Types
            amount	number($double)
            accountId	integer($int64)

### Responses
Code 201

    Status: 201 Created
    Content-Type: application/json;charset=UTF-8
       {
            "id": 1,
            "amount": 1000,
            "accountId": 1
        }

### Request
Get all transactions for a customer with passing the accountId
`GET /transactions/{accountId}`

    http://localhost:8100/transactions/{accountId}

### Response

    Status: 200 OK
    Content-Type: application/json;charset=UTF-8

    [
        {
            "id": 2,
            "amount": 1000,
            "accountId": 1
        },
        {
            "id": 3,
            "amount": 1000,
            "accountId": 1
        }
    ]