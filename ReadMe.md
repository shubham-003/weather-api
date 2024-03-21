Apple Backend Exercise

Requirements
Write an API where
Input: Zipcode
Output: Displays weather forecast for the given zip code
1. Forecast should at least have current temperature
2. Additional points for high/low temperature and/or extended forecast
Important Point:
Solution should cache the forecast for 30 minutes for all subsequent requests by same zipcode. Also, display the indicator in response if result is pulled from the cache.
Submission -> Use source code repository e.g. github to commit the code and share the link with us.
Notes
1. This requirement is open to interpretation
2. If you get stuck, complete as much as you can


// Steps to run solution
- Open code in ide and run the Project on Localhost
- open postman ->
    GET: http://localhost:8080/weather?zipcode=94040 (param: country not given, then default country = us)
    GET: http://localhost:8080/weather?zipcode=560037,in

- list of countries ->
    us - USA
    in - India
