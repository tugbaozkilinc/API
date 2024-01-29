package graphql;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GraphQLQueryTestWithPojo {

    @Test
    public void getAllUsersWithPojo() {
        RestAssured.baseURI = "https://hasura.io";
        GraphQLQuery query = new GraphQLQuery();
        query.setQuery("query ($limit: Int!, $name: String!) {\n" +
                "  users(limit: $limit, where: {name: {_eq: $name}}) {\n" +
                "    id\n" +
                "    name\n" +
                "  }\n" +
                "}");
        GraphQLQueryVariable variable = new GraphQLQueryVariable();
        variable.setLimit(10);
        variable.setName("tui.glen");
        query.setVariables(variable);

        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDY1YjRiMmU4ODVkMzE1MGFhNDlkYTIyMCJ9LCJuaWNrbmFtZSI6InR1Z2Jhb3praWxpbmMyMiIsIm5hbWUiOiJ0dWdiYW96a2lsaW5jMjJAZ21haWwuY29tIiwicGljdHVyZSI6Imh0dHBzOi8vcy5ncmF2YXRhci5jb20vYXZhdGFyLzU0NDE5NDZhOGY1ODExMDFiZGFkMWI4YzQwOTY4NzE4P3M9NDgwJnI9cGcmZD1odHRwcyUzQSUyRiUyRmNkbi5hdXRoMC5jb20lMkZhdmF0YXJzJTJGdHUucG5nIiwidXBkYXRlZF9hdCI6IjIwMjQtMDEtMjdUMDc6Mzg6MTcuOTE2WiIsImlzcyI6Imh0dHBzOi8vZ3JhcGhxbC10dXRvcmlhbHMuYXV0aDAuY29tLyIsImF1ZCI6IlAzOHFuRm8xbEZBUUpyemt1bi0td0V6cWxqVk5HY1dXIiwiaWF0IjoxNzA2NTExMjkxLCJleHAiOjE3MDY1NDcyOTEsInN1YiI6ImF1dGgwfDY1YjRiMmU4ODVkMzE1MGFhNDlkYTIyMCIsImF0X2hhc2giOiJVYi1TSUpNc1oxZk5tLTNtRlRPQUhBIiwic2lkIjoidUlfQ3ZtOWZBQkpOZnRWdlhXSWtpRWFnbWIwQ2Fmb3giLCJub25jZSI6Im1ZMi44TnZGVm1WbGVTRklBd0o4NUVZUElNNk5NR1ZVIn0.kBTqCd6DONl56Mp8HWuWFWoFZFCsFml5ZpXkHFCVbHJhJe4Bg3S54h7a5sdLzJ7PkTDnLeVoJVmuIZgVnJZSxjuPo-wPL_4D7QIDnwPArONlKXXKF6iF3WcBRYzJA4x7lqerkaCXT8LOwOod9ae9d2pZmOdOh4ROyOoSgbpbZ8s81Fzr2MDVx7Vt70Atg42IppqAuDt_k5Dk7bd-kEX0PZEtkSSlcRpgnt4UJysmM2XwObW486AhASO4YvIFv3CcTGMGEmDttQyDrrIj6-qr9E7FixVC7a8vN6zjf-y7GmjCiUX0mJmBMsGZDCxjOyAPm6w9PgRi8YxH-66xlt9blw")
                .body(query)
                .when().log().all()
                .post("/learn/graphql")
                .then().log().all()
                .assertThat().statusCode(200).and().body("data.users[0].name", equalTo("tui.glen"));
    }

}
