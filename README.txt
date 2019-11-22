# back-end

Example of an eatz object:

{
	"createdDate": 1574186065969, //long
	"eatzid": 26, //id/int
    "title": "title2323", // string, use as category or whatever's clever
    "carbs": 5, //int
    "proteins": 4, //int
    "fats": 3 //int
}


expected user endpoints using 0auth2:
{POST} /createnewuser
 {
                "username": "username",
                "fullname": "Jack Seymour",
                "password" : "password"
            }
{POST}/login
		//authorization type will be 'implicit'
	Grant Type: Password Credentials
	Client ID: lambda
	Client Secret: lambdasecret
	&
	Username
	password
	Client Auth: Basic Auth Header
{GET}/users/getuserinfo
//will return details for the user logged in, may not be super useful
// usereatz will be a list of previous meals
{
    "userid": 25,
    "username": "jseymour",
    "fullname": "jack seymour",
    "usereatz": [
        {
            "createdDate": 1574197823878,
            "eatzid": 26,
            "title": "test3",
            "carbs": 1,
            "proteins": 1,
            "fats": 1
        }
    ]
}
data endpoints:
//will consume a json object and create a meal for the gigapet
 {post} /eatz/create
sent body:
{
    "title": "sphaget",
    "carbs": 1,
    "proteins": 1,
    "fats": 1
}
returned success:
//returned object will have a long indicating time created,
// an id for future reference
// and a reference to the user who created the object.
{
    "createdDate": 1574198189961,
    "eatzid": 27,
    "title": "sphaget",
    "carbs": 1,
    "proteins": 1,
    "fats": 1,
    "user": {
        "userid": 25,
        "username": "jseymour",
        "fullname": "jack seymour"
    }
}
{get}/eatz/id/[eatzid]
//will return a single eatz meal by id
{
    "createdDate": 1574198189961,
    "eatzid": 27,
    "title": "sphaget",
    "carbs": 1,
    "proteins": 1,
    "fats": 1,
    "user": {
        "userid": 25,
        "username": "jseymour",
        "fullname": "jack seymour"
    }
}
{get}/eatz/alleatzforuser
//will return all eatz for the user
[
    {
        "createdDate": 1574197823878,
        "eatzid": 26,
        "title": "test3",
        "carbs": 1,
        "proteins": 1,
        "fats": 1,
        "user": {
            "userid": 25,
            "username": "jseymour",
            "fullname": "jack seymour"
        }
    },
    {
        "createdDate": 1574198189961,
        "eatzid": 27,
        "title": "sphaget",
        "carbs": 1,
        "proteins": 1,
        "fats": 1,
        "user": {
            "userid": 25,
            "username": "jseymour",
            "fullname": "jack seymour"
        }
    }
]
{delete}/eatz/delete/ [eatzid]
//will delete the specified eatz and return a blank body 
//with a 200 OK response code
{PUT}/eatz/update/[eatzid]
//consumes a json object of form and updates the eatz at the id in the path
{
    "title": "not sphaghet",
    "carbs": 3,
    "proteins": 3,
    "fats": 3
}
returned success:
//will return the updated object if successful
{
    "createdDate": 1574198547798,
    "eatzid": 27,
    "title": "not sphaghet",
    "carbs": 4,
    "proteins": 4,
    "fats": 4,
    "user": {
        "userid": 23,
        "username": "jseymour",
        "fullname": "jack seymour"
    }
}
