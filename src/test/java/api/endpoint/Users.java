package api.endpoint;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
public class Users extends  BaseAPI {
    private int listUsersStatusCode ;
    private int createUsersStatusCode ;
    private int updateUsersStatusCode ;
    private String dateOfCreation;
    private String dateOfUpdate;

    public RequestSpecification requestConfiguration(){
        RestAssured.baseURI= BaseURL ;
        RequestSpecification request = given().config(RestAssured.config()
                .encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC))).header("accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + Token);
        return request;
    }

    public Response listUser() {
        Response response = requestConfiguration().when().get(LIST_USERS);
        listUsersStatusCode= response.statusCode();
        return response;
    }

    public Response createNewUser(String name,String job){
          Response response = requestConfiguration().queryParam("name",name).queryParam("job", job).when().post(CREATE_USERS);
        createUsersStatusCode= response.statusCode();
        System.out.println(response.getBody().asString());
        dateOfCreation = response.jsonPath().get("createdAt");
        return response;
    }

    public Response updateUser(String newName,String newJob ,String id ){
        Response response = requestConfiguration().queryParam("name",newName).queryParam("job", newJob).when().put(UPDATE_USERS+id);
        updateUsersStatusCode= response.statusCode();
        dateOfUpdate = response.jsonPath().get("updatedAt");
        return response;
    }

    public int getCreateUsersStatusCode() {
        return createUsersStatusCode;
    }

    public int getListUsersStatusCode() {
        return listUsersStatusCode;
    }

    public int getUpdateUsersStatusCode() {
        return updateUsersStatusCode;
    }

     public String getTotalOfUserListed(){
       return listUser().jsonPath().get("total") ;
     }


    public String getDateOfUpdate() {
        return dateOfUpdate;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }
}
