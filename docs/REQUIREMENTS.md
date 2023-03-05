# Functional requirements
## Actors:
* Guest. Can read the main page and donate money.

* User. He/she is authorized user. Can everything as guest and more like been able to see donation history and his/her profile.

* Volunteer. He/she is authorized user who can start fundraisings, view and accept requests from militaries, see his/her profile.

* Military. He/she is authorized user who can create requests and see his/her own requests, see his/her profile.

## Use-cases
### Common
All time values and diapasons mean the timezone obtained from application configuration (configured before application starts).
Any user can donate money on choosen fundraisings, view any public profile and see own donations history.

### User
After login/registration user see his/her own main page (main page differ from user to user depending in his/her role). At feed page user sees actual fundraisings and is able do donate money on choosen. Also he/she sees history of past donations and progress of fundraising campaigns that he/she has donated to.

### Volunteer
After login/registration volunteer wait for admin's approvement. Afterwise he/she receive letter on email and is able to use full amount of provided funcionality. From now volunteer sees main page and can see requests from militaries and start fundraising companies for them or start new one on their own initiative. Like common user can donate money.

### Military
After login/registration military wait for admin's approvement. Afterwise he/she receive letter on email and is able to use full amount of provided funcionality. From now military sees main page and can see requests from other militaries and his/her own, but can't take them or start fundraising company for this requests. Also is able to create new request and like common user donate money.

## API specification
Keep all the request and response data structures and error codes.

#### Error responses
```json
HTTP 400
{
    "errorCode": "snake_case_meaningful_string",
    "errorMessage": "English user friendly message"
}
```
All business validation errors must have HTTP code `400`.

GET request with wrong ID must return HTTP code `404`.

Bad or expired authentication token leads to HTTP code `401`.

Trying proceed unauthorized operation (like Military starting fundraising company for other military) will lead to HTTP code `403`.

When something went wrong with database leads to HTTP code `502`. 

### Dictionaries
```json
GET /home

Response:
{
    "Fundraisings": "ArrayList<Fundraising>"
}
```

### Me endpoint
Me endpoint for volunteer role
```json
GET /me

Response:
{
    "email": "your@email.com",
    "role": "Volunteer",
    "id": "some-UUID",
    "name" : "some-name",
    "surname" : "some-surname",
    "father-name" : "some-father-name",
    "social-media-link" : "some-social-media-link",
    "MyFundraisings" : "some-user's-fundraisings"
}
```

Me endpoint for military and user roles
```json
GET /me

Response:
{
    "email": "your@email.com",
    "role": "Volunteer",
    "id": "some-UUID",
    "name" : "some-name",
    "surname" : "some-surname",
    "father-name" : "some-father-name",
}
```
For the Guest, response should be same as for any other endpoint, not allowed for Guests:
```json
HTTP 403
{
    "errorCode": "not_authorized",
    "errorMessage": "You are not authorized to use this functionality"
}
```

### Admin approvement screen
Page at which current admin can see approvement requests from volunteers and militaries.
```json
GET /approvement

Response:
{
    "email": "your@email.com",
    "role": "Admin",
    "id": "some-UUID",
    "name" : "some-name",
    "surname" : "some-surname",
    "Users" : "some-ArrayList<User>"
}
```

### Create new fundraisings and requests
```json
POST /volunteer/<volunteersId>/fundraising

{
    "whom": "2nd division",
    "sum": "10000",
    "fundraisingName": "Maviks",
    "type": "tecnique",
    "cardNum" : "111222333444555",
    "description" : "some-description",
    "photo" : "photo"
}
```

#### Response:
```json
{
    "whom": "2nd division",
    "sum": "10000",
    "fundraisingName": "Maviks",
    "type": "tecnique",
    "cardNum" : "111222333444555",
    "description" : "some-description",
    "photo" : "photo",
    "id" : "some-UUID"
}
```

#### Errors:
user_not_found
negative_sum
wrong_card_format



```json
POST /military/<militaryId>/request

{
    "whom": "2nd division",
    "fundraisingName": "Maviks",
    "description" : "some-description",
    "grey-zone" : true
}
```
#### Response:
```json
POST /military/<militaryId>/request

{
    "whom": "2nd division",
    "fundraisingName": "Maviks",
    "description" : "some-description",
    "grey-zone" : true,
    "id" : "some-uuid"
}
```

#### Errors:
user_not_found
wrong_card_format

