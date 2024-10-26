package api.test;

import api.endpoint.BaseAPI;
import api.endpoint.Users;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UsersStepdefs extends BaseAPI {
    public Users users = new Users();
    @When("retrieve list of users")
    public void retrieveListOfUsers() {
        users.listUser();
    }

    @Then("retrieve users response status code should be {int}")
    public void retrieveUsersResponseStatusCodeShouldBeCode(int code) {
      Assert.assertEquals(users.getListUsersStatusCode(),code);
    }

    @Then("create users response status code should be {int}")
    public void createUsersResponseStatusCodeShouldBeCode(int code) {
        Assert.assertEquals(users.getCreateUsersStatusCode(),code);
    }

    @When("create new record of user with {string} and {string}")
    public void createNewRecordOfUserWithAnd(String name , String job) {
        users.createNewUser(name,job);
    }

    @When("update user with {string} and {string} through {string}")
    public void updateUserWithAndThroughId(String name, String job,String id) {
        users.updateUser(name,job,id);
    }

    @Then("update users response status code should be {int}")
    public void updateUsersResponseStatusCodeShouldBeCode(int code) {
        Assert.assertEquals(users.getUpdateUsersStatusCode(),code);
    }



    @And("Schema of response should be valid as {string}")
    public void schemaOfResponseShouldBeValidAs(String schemaPath) {
            Response retrievedResponse = users.listUser();
           retrievedResponse.then().body(matchesJsonSchemaInClasspath(schemaPath));
        }


    @And("check that data of user updated successfully")
    public void checkThatDataOfUserUpdatedSuccessfullyAnd() {
        Assert.assertFalse(users.getDateOfUpdate().equals(null));

    }
    @And("check that data of user created successfully")
    public void checkThatDataOfUserCreatedSuccessfully() {
        Assert.assertFalse(users.getDateOfCreation().equals(null));
    }
}



