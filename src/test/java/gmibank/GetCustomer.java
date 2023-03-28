package gmibank;

import base_urls.GmiBankBaseUrl;
import gmibank.pojos.Account;
import gmibank.pojos.Country;
import gmibank.pojos.Customer;
import gmibank.pojos.User;
import io.restassured.response.Response;
import org.junit.Test;
import util.ObjectMapperUtils;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static util.AuthenticationGmiBank.generateTokenGmi;

public class GetCustomer extends GmiBankBaseUrl {

    /*
    Given
        https://www.gmibank.com/api/tp-customers/110472
    When
        User sends Get request
    Then
        Status code should be 200
    And
        Response body should be like:
    {
    "id": 110472,
    "firstName": "Melva",
    "lastName": "Bernhard",
    "middleInitial": "A",
    "email": "chas.kuhlman@yahoo.com",
    "mobilePhoneNumber": "192-580-3408",
    "phoneNumber": "192-580-3408",
    "zipCode": "40207",
    "address": "Apt. 634 579 Eliseo Rapids, Deanaside, AZ 53872",
    "city": "New Jordanhaven",
    "ssn": "523-50-1191",
    "createDate": "2021-11-30T21:00:00Z",
    "zelleEnrolled": false,
    "country": {
        "id": 24105,
        "name": "San Jose",
        "states": null
    },
    "state": "",
    "user": {
        "id": 111206,
        "login": "delilah.metz",
        "firstName": "Melva",
        "lastName": "Bernhard",
        "email": "chas.kuhlman@yahoo.com",
        "activated": true,
        "langKey": "en",
        "imageUrl": null,
        "resetDate": null
    },
    "accounts": [
        {
            "id": 2327,
            "description": "omermusteri01 hesap1",
            "balance": 1020600,
            "accountType": "SAVING",
            "accountStatusType": "ACTIVE",
            "createDate": "2020-11-06T23:00:00Z",
            "closedDate": "2024-11-07T23:00:00Z",
            "employee": null,
            "accountlogs": null
        },
        {
            "id": 107250,
            "description": "New Account_6thGenQA_01",
            "balance": 11190,
            "accountType": "CHECKING",
            "accountStatusType": "ACTIVE",
            "createDate": "2021-11-24T23:00:00Z",
            "closedDate": "2022-11-24T23:00:00Z",
            "employee": null,
            "accountlogs": null
        }
    ]
    }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "api", "second", "tp-customers", "third", 110472);

        //Set the expected data
        Country country = new Country(24105, "San Jose", null);
        User user = new User(111206, "delilah.metz", "Melva", "Bernhard", "chas.kuhlman@yahoo.com", true, "en", null, null);
        Account account1 = new Account(2327, "omermusteri01 hesap1", 1020600, "SAVING", "ACTIVE", "2020-11-06T23:00:00Z", "2024-11-07T23:00:00Z", null, null);
        Account account2 = new Account(107250, "New Account_6thGenQA_01", 11190, "CHECKING", "ACTIVE", "2021-11-24T23:00:00Z", "2022-11-24T23:00:00Z", null, null);
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);
        Customer expectedData = new Customer(110472,"Melva","Bernhard","A","chas.kuhlman@yahoo.com","192-580-3408",
                    "192-580-3408","40207","Apt. 634 579 Eliseo Rapids, Deanaside, AZ 53872","New Jordanhaven","523-50-1191",
                       "2021-11-30T21:00:00Z",false, country,"", user, accounts);
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given(spec).headers("Authorization", "Bearer " + generateTokenGmi()).get("/{first}/{second}/{third}");
        response.prettyPrint();

        //Do assertion
        Customer actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), Customer.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        assertEquals(expectedData.getLastName(), actualData.getLastName());
        assertEquals(expectedData.getMiddleInitial(), actualData.getMiddleInitial());
        assertEquals(expectedData.getEmail(), actualData.getEmail());
        assertEquals(expectedData.getMobilePhoneNumber(), actualData.getMobilePhoneNumber());
        assertEquals(expectedData.getPhoneNumber(), actualData.getPhoneNumber());
        assertEquals(expectedData.getZipCode(), actualData.getZipCode());
        assertEquals(expectedData.getAddress(), actualData.getAddress());
        assertEquals(expectedData.getCity(), actualData.getCity());
        assertEquals(expectedData.getSsn(), actualData.getSsn());
        assertEquals(expectedData.getCreateDate(), actualData.getCreateDate());
        assertEquals(expectedData.isZelleEnrolled(), actualData.isZelleEnrolled());
        assertEquals(country.getId(), actualData.getCountry().getId());
        assertEquals(country.getName(), actualData.getCountry().getName());
        assertEquals(country.getStates(), actualData.getCountry().getStates());
        assertEquals(expectedData.getState(), actualData.getState());
        assertEquals(user.getId(), actualData.getUser().getId());
        assertEquals(user.getLogin(), actualData.getUser().getLogin());
        assertEquals(user.getFirstName(), actualData.getUser().getFirstName());
        assertEquals(user.getLastName(), actualData.getUser().getLastName());
        assertEquals(user.getEmail(), actualData.getUser().getEmail());
        assertEquals(user.isActivated(), actualData.getUser().isActivated());
        assertEquals(user.getLangKey(), actualData.getUser().getLangKey());
        assertEquals(user.getImageUrl(), actualData.getUser().getImageUrl());
        assertEquals(user.getResetDate(), actualData.getUser().getResetDate());
        assertEquals(account1.getId(), actualData.getAccounts().get(0).getId());
        assertEquals(account1.getDescription(), actualData.getAccounts().get(0).getDescription());
        assertEquals(account1.getBalance(), actualData.getAccounts().get(0).getBalance());
        assertEquals(account1.getAccountType(), actualData.getAccounts().get(0).getAccountType());
        assertEquals(account1.getAccountStatusType(), actualData.getAccounts().get(0).getAccountStatusType());
        assertEquals(account1.getCreateDate(), actualData.getAccounts().get(0).getCreateDate());
        assertEquals(account1.getClosedDate(), actualData.getAccounts().get(0).getClosedDate());
        assertEquals(account1.getEmployee(), actualData.getAccounts().get(0).getEmployee());
        assertEquals(account1.getAccountlogs(), actualData.getAccounts().get(0).getAccountlogs());
        assertEquals(account2.getId(), actualData.getAccounts().get(1).getId());
        assertEquals(account2.getDescription(), actualData.getAccounts().get(1).getDescription());
        assertEquals(account2.getBalance(), actualData.getAccounts().get(1).getBalance());
        assertEquals(account2.getAccountType(), actualData.getAccounts().get(1).getAccountType());
        assertEquals(account2.getAccountStatusType(), actualData.getAccounts().get(1).getAccountStatusType());
        assertEquals(account2.getCreateDate(), actualData.getAccounts().get(1).getCreateDate());
        assertEquals(account2.getClosedDate(), actualData.getAccounts().get(1).getClosedDate());
        assertEquals(account2.getEmployee(), actualData.getAccounts().get(1).getEmployee());
        assertEquals(account2.getAccountlogs(), actualData.getAccounts().get(1).getAccountlogs());
    }

}
