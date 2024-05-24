# LDMS

## Run

> ```
> ./mvnm spring-boot:run
> ```

## End-point: localhost:8080/api/loan

### Method: POST

> ```
> localhost:8080/api/loan
> ```

### Body (**raw**)

```json
{
	"principal": 5000,
	"annualInterestRate": 5.5,
	"termInMonths": 24,
	"balloonPayment": 0
}
```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: localhost:8080/api/loan/{id}

### Method: GET

> ```
> localhost:8080/api/loan/:loan
> ```

### Body (**raw**)

```json
{
    "id": 1,
    "principal": 5000.0,
    "annualInterestRate": 5.5,
    "termInMonths": 24,
    "balloonPayment": 0.0,
    "schedule": [
        {
            "principalPayment": 197.5616141240141,
            "interestPayment": 22.916666666666668,
            "remainingBalance": 4802.438385875986,
            "month": 1
        },
        {
            "principalPayment": 198.46710485541584,
            "interestPayment": 22.011175935264934,
            "remainingBalance": 4603.97128102057,
            "month": 2
        },
        {
            "principalPayment": 199.37674575266982,
            "interestPayment": 21.101535038010947,
            "remainingBalance": 4404.5945352679,
            "month": 3
        },
        ...
    ]
}
```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: localhost:8080/api/loan

### Method: GET

> ```
> localhost:8080/api/loan
> ```

### Body (**raw**)

```json
[
    {
        "id": 1,
        "principal": 5000.0,
        "annualInterestRate": 5.5,
        "termInMonths": 24,
        "balloonPayment": 0.0,
        "schedule": [
           ...
        ]
    },
    {
        "id": 2,
        "principal": 5000.0,
        "annualInterestRate": 5.5,
        "termInMonths": 24,
        "balloonPayment": 0.0,
        "schedule": [
            ...
        ]
    },
    {
        "id": 3,
        "principal": 5000.0,
        "annualInterestRate": 5.5,
        "termInMonths": 24,
        "balloonPayment": 0.0,
        "schedule": [
            ...
        ]
    },
    ...
]

```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
