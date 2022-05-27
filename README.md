[![Netlify Status](https://api.netlify.com/api/v1/badges/8b980a42-3362-4211-aa0f-1c8cf02e0b31/deploy-status)](https://app.netlify.com/sites/leoncamp/deploys)

# Accomodation booking REST API · SpringBoot
*Updated: 2022-05-26. Francisco Coya*

API Rest using Spring Boot framework for booking system management.

## Requirements

- NodeJS (v16.14.2 or higher). [https://nodejs.org/es/download/](https://nodejs.org/es/download/)
  - Verify Node version: `node -v`
  - Verify NPM version: `npm -v`
 
 - Code Editor(e.g. VS Code). [https://code.visualstudio.com/download](https://code.visualstu-dio.com/download)
 - MySQL Workbench (Version 8 or higher). [https://dev.mysql.com/downloads/workbench/](https://dev.mysql.com/downloads/workbench/)
 - Java JDK version 8 or higher.

## Instalation

1. Open MySQL Workbench and execute the sql script file.
  - Database requires these user account:  
      **username**: manager1  
      **password**: 1234  
      all privileges for testing purposes.  
    
2. Unzip the .jar file which contains the SNAPSHOT .jar file.
3. Open a terminal and run this command: 
```java 
  java -jar api-0.0.1-SANPSHOT.jar
```
4. Open a browser or test with HTTP client like Postman with the endpoint: `http://localhost:8080/api`
  

## Examples

1. Get accommodation data by register number: `/accomodations/0001234A`

```json
{
    "registerNumber": "0001234A",
    "description": "El moderno apartamento cuenta con una habitación independiente con cama de matrimonio y tv. El salón tiene un sofá cama doble, tv y un agradable calefactor eléctrico led. Cocina totalmente equipada y utensilios básicos para cocinar. Baño amplio con ducha. Calefacción y wifi incluidos.\ncuenta con un dormitorio con cama doble y televisor. La sala de estar cuenta con un sofá cama para 2 personas, televisor y un cómodo calentador eléctrico led. Cocina totalmente equipada. El baño tiene una ducha. Sistema de calefacción y WiFi incluido.",
    "numOfBeds": 3,
    "numOfBathRooms": 1,
    "numOfBedRooms": 2,
    "pricePerNight": 35.0000,
    "numOfGuests": 4,
    "area": 75.0000,
    "idAccomodationCategory": {
        "id": 1,
        "accomodationCategory": "Apartamento"
    },
    "idAccomodationLocation": {
        "id": 1,
        "latitude": 42.59855643669309,
        "longitude": -5.576348640183169,
        "direction": "Av. de Roma, 3",
        "city": "León",
        "zip": "24001"
    },
    "accomodationImages": [
        {
            "accomodationAccImageId": {
                "idAccomodation": "0001234A",
                "idAccomodationImage": {
                    "id": 5,
                    "imageUrl": "https://images.unsplash.com/photo-1553444859-788c4b385b13?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1169&q=80"
                }
            }
        },
        {
            "accomodationAccImageId": {
                "idAccomodation": "0001234A",
                "idAccomodationImage": {
                    "id": 4,
                    "imageUrl": "https://images.unsplash.com/photo-1493663284031-b7e3aefcae8e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"
                }
            }
        },
        {
            "accomodationAccImageId": {
                "idAccomodation": "0001234A",
                "idAccomodationImage": {
                    "id": 2,
                    "imageUrl": "https://images.unsplash.com/photo-1522771739844-6a9f6d5f14af?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1171&q=80"
                }
            }
        },
        
        { ··· }
    ],
    "accomodationRules": [
        {
            "accomodationAccRuleId": {
                "idAccomodation": "0001234A",
                "idAccomodationRule": {
                    "id": 1,
                    "rule": "Prohibido fumar"
                }
            }
        },
        {
            "accomodationAccRuleId": {
                "idAccomodation": "0001234A",
                "idAccomodationRule": {
                    "id": 3,
                    "rule": "Están totalmente prohibidas las fiestas y eventos"
                }
            }
        },
        {
            "accomodationAccRuleId": {
                "idAccomodation": "0001234A",
                "idAccomodationRule": {
                    "id": 2,
                    "rule": "No se admiten mascotas"
                }
            }
        }
    ],
    "accomodationServices": [
        {
            "accomodationAccServiceId": {
                "idAccomodation": "0001234A",
                "idAccomodationService": {
                    "id": 20,
                    "denomination": "Microondas"
                }
            }
        },
        {
            "accomodationAccServiceId": {
                "idAccomodation": "0001234A",
                "idAccomodationService": {
                    "id": 2,
                    "denomination": "Calefacción"
                }
            }
        },
        {
            "accomodationAccServiceId": {
                "idAccomodation": "0001234A",
                "idAccomodationService": {
                    "id": 13,
                    "denomination": "Lavadora"
                }
            }
        },
        
        { ··· }
    ],
    "promoCodes": [
        {
            "promoCodeAccomodationId": {
                "idAccomodation": "0001234A",
                "idPromoCode": {
                    "serial_num": "XCmQOJpyjO",
                    "amountPercentange": 10.0000,
                    "dateStart": [
                        2022,
                        3,
                        20,
                        0,
                        0
                    ],
                    "dateEnd": [
                        2022,
                        3,
                        27,
                        0,
                        0
                    ],
                    "idUser": {
                        "id": 1,
                        "name": "Samuel",
                        "surname": "Cooper",
                        "email": "samuel.cooper@gmail.com",
                        "phone": "773893743 ",
                        "profileImage": "data:image/jpeg;base64,{...}",
                        "idUserConfiguration": {
                            "idUserConfiguration": 6,
                            "idLanguage": {
                                "id": 1,
                                "language": "ES"
                            },
                            "idCurrency": {
                                "idCurrency": 2,
                                "currencyAlphanumericCode": "EUR",
                                "currencyName": "Euro",
                                "currencyCode": 978
                            }
                        },
                        "createdAt": [
                            2022,
                            5,
                            20,
                            11,
                            21,
                            41
                        ],
                        "dni": "15412808W",
                        "bio": "¡Me encanta viajar! Por ello, ofrezco no sólo un hogar en Granada a la gente que viene de fuera, sino que me encantará aconsejarles sobre los sitios más bonitos para pasear, mis bares o restaurantes favoritos, las vistas más bonitas de la ciudad,...",
                        "direction": "C/ Canónigo Valiño, 28",
                        "emailVerified": false,
                        "phoneVerified": false,
                        "dniVerified": false,
                        "verified": false
                    },
                    "createdAt": [
                        2022,
                        5,
                        20,
                        11,
                        21,
                        41
                    ]
                }
            }
        }
    ],
    "idUserHost": {
        "id": 1,
        "name": "Samuel",
        "surname": "Cooper",
        "email": "samuel.cooper@gmail.com",
        "phone": "773893743 ",
        "profileImage": "{...}",
        "idUserConfiguration": {
            "idUserConfiguration": 6,
            "idLanguage": {
                "id": 1,
                "language": "ES"
            },
            "idCurrency": {
                "idCurrency": 2,
                "currencyAlphanumericCode": "EUR",
                "currencyName": "Euro",
                "currencyCode": 978
            }
        },
        "createdAt": [
            2022,
            5,
            20,
            11,
            21,
            41
        ],
        "dni": "15412808W",
        "bio": "¡Me encanta viajar! Por ello, ofrezco no sólo un hogar en Granada a la gente que viene de fuera, sino que me encantará aconsejarles sobre los sitios más bonitos para pasear, mis bares o restaurantes favoritos, las vistas más bonitas de la ciudad,...",
        "direction": "C/ Canónigo Valiño, 28",
        "emailVerified": false,
        "phoneVerified": false,
        "dniVerified": false,
        "verified": false
    },
    "createdAt": [
        2022,
        5,
        20,
        11,
        21,
        41
    ]
}

```
