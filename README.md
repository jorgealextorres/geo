# geo
Test to get the gelocalization from an address.

### **Direct call:**

    curl -X GET \
      http://localhost:9001/getGeo \
      -H 'Host: localhost:9001' \
      -d 'Barcelona, Barcelona '
  
 **Response:**
 
    {
        "originalSearch": "Barcelona, Barcelona ",
        "result": {
            "latitude": 41.38804,
            "longitude": 2.17001
        }
    }
    
**Error:**

    {
        "originalSearch": "Barcelona, Barcelona ",
        "error": "An error was raised. Please contact the provider for more information."
    }
 
### **Call using a file:**
 
**File content:** file.txt
 
     Barcelona, Barcelona 
     C. Vilamari, 50, Barcelona 
     Pl. Jar Jar Binks, 42, Barcelona 
     Felip II, 323, Barcelona 08015 Barcelona 
     calle de las doncellas, 10, Barcelona 
     Plaza España Gran Vía de la Corte Catalana, 423, 08015 Barcelona 
     inAtlas
     Fuente de Montjuic 
     nowhere 
     Melbourne 
     Universidade de Campinas, Brasil
 
**Call:**

     curl -X POST \
       http://localhost:9001/getGeoFromFile \
       -H 'Host: localhost:9001' \
       -F file=@/home/user/Documents/projects/geo/file/test_file.txt
 
 **Response:**
 
     {
         "locations": [
             {
                 "originalSearch": "Barcelona, Barcelona ",
                 "result": {
                     "latitude": 41.38804,
                     "longitude": 2.17001
                 }
             },
             {
                 "originalSearch": "C. Vilamari, 50, Barcelona ",
                 "result": {
                     "latitude": 41.37757,
                     "longitude": 2.15125
                 }
             },
             {
                 "originalSearch": "Pl. Jar Jar Binks, 42, Barcelona ",
                 "result": {}
             },
             {
                 "originalSearch": "Felip II, 323, Barcelona 08015 Barcelona ",
                 "result": {
                     "latitude": 41.4285,
                     "longitude": 2.17545
                 }
             },
             {
                 "originalSearch": "calle de las doncellas, 10, Barcelona ",
                 "result": {
                     "latitude": 41.42462,
                     "longitude": 2.09817
                 }
             },
             {
                 "originalSearch": "Plaza España Gran Vía de la Corte Catalana, 423, 08015 Barcelona ",
                 "result": {
                     "latitude": 41.37786,
                     "longitude": 2.15248
                 }
             },
             {
                 "originalSearch": "inAtlas",
                 "result": {}
             },
             {
                 "originalSearch": "Fuente de Montjuic ",
                 "result": {
                     "latitude": 22.93465,
                     "longitude": -102.70441
                 }
             },
             {
                 "originalSearch": "nowhere ",
                 "result": {
                     "latitude": 35.15673,
                     "longitude": -98.44252
                 }
             },
             {
                 "originalSearch": "Melbourne ",
                 "result": {
                     "latitude": -37.81739,
                     "longitude": 144.96752
                 }
             },
             {
                 "originalSearch": "Universidade de Campinas, Brasil",
                 "result": {
                     "latitude": -22.83873,
                     "longitude": -47.05347
                 }
             }
         ],
         "errors": []
     }
     
     
  Error:
  
  * general error:
  
        "An error was raised. Please contact the provider for more information."
 
 * indivual error
 
 
         {
             "locations": [],
             "errors": [
                 {
                     "originalSearch": "Barcelona, Barcelona ",
                     "error": "An error was raised. Please contact the provider for more information."
                 },
                 {
                     "originalSearch": "C. Vilamari, 50, Barcelona ",
                     "error": "An error was raised. Please contact the provider for more information."
                 },
                 {
                     "originalSearch": "Pl. Jar Jar Binks, 42, Barcelona ",
                     "error": "An error was raised. Please contact the provider for more information."
                 },
                 {
                     "originalSearch": "Felip II, 323, Barcelona 08015 Barcelona ",
                     "error": "An error was raised. Please contact the provider for more information."
                 },
                 {
                     "originalSearch": "calle de las doncellas, 10, Barcelona ",
                     "error": "An error was raised. Please contact the provider for more information."
                 },
                 {
                     "originalSearch": "Plaza España Gran Vía de la Corte Catalana, 423, 08015 Barcelona ",
                     "error": "An error was raised. Please contact the provider for more information."
                 },
                 {
                     "originalSearch": "inAtlas",
                     "error": "An error was raised. Please contact the provider for more information."
                 },
                 {
                     "originalSearch": "Fuente de Montjuic ",
                     "error": "An error was raised. Please contact the provider for more information."
                 },
                 {
                     "originalSearch": "nowhere ",
                     "error": "An error was raised. Please contact the provider for more information."
                 },
                 {
                     "originalSearch": "Melbourne ",
                     "error": "An error was raised. Please contact the provider for more information."
                 },
                 {
                     "originalSearch": "Universidade de Campinas, Brasil",
                     "error": "An error was raised. Please contact the provider for more information."
                 }
             ]
         }
 
