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
        GraphQLQuery graphQLQuery = new GraphQLQuery();
        graphQLQuery.setQuery("query ($limit: Int!, $name: String!) {\n" +
                "  users(limit: $limit, where: {name: {_eq: $name}}) {\n" +
                "    id\n" +
                "    name\n" +
                "  }\n" +
                "}");
        GraphQLQueryVariable graphQLQueryVariable = new GraphQLQueryVariable();
        graphQLQueryVariable.setLimit(10);
        graphQLQueryVariable.setName("tui.glen");
        graphQLQuery.setVariables(graphQLQueryVariable);

        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDY1YjRiMmU4ODVkMzE1MGFhNDlkYTIyMCJ9LCJuaWNrbmFtZSI6InR1Z2Jhb3praWxpbmMyMiIsIm5hbWUiOiJ0dWdiYW96a2lsaW5jMjJAZ21haWwuY29tIiwicGljdHVyZSI6Imh0dHBzOi8vcy5ncmF2YXRhci5jb20vYXZhdGFyLzU0NDE5NDZhOGY1ODExMDFiZGFkMWI4YzQwOTY4NzE4P3M9NDgwJnI9cGcmZD1odHRwcyUzQSUyRiUyRmNkbi5hdXRoMC5jb20lMkZhdmF0YXJzJTJGdHUucG5nIiwidXBkYXRlZF9hdCI6IjIwMjQtMDItMDFUMTE6MjA6MDAuOTMyWiIsImlzcyI6Imh0dHBzOi8vZ3JhcGhxbC10dXRvcmlhbHMuYXV0aDAuY29tLyIsImF1ZCI6IlAzOHFuRm8xbEZBUUpyemt1bi0td0V6cWxqVk5HY1dXIiwiaWF0IjoxNzA2Nzg2NDAyLCJleHAiOjE3MDY4MjI0MDIsInN1YiI6ImF1dGgwfDY1YjRiMmU4ODVkMzE1MGFhNDlkYTIyMCIsImF0X2hhc2giOiJqZkprSUs4ZVhMRWUtbXZBZk0yQTJ3Iiwic2lkIjoiM0YxbHUyVkx0Y1QwNUE0S194bFpGMmxCUWxPZkd6NGciLCJub25jZSI6IjJyTnktMmlqWktsOVBHSTkzaS15cVN6d3ZtVHRyTklVIn0.O8bGDf3m6PKCRwtJzdz8NOu5JDvCFuHjalELH8BZ8uVXvMURTaE9gFMOCj3DDgTea49xoAOBdzTWA2ObdRL38o5iQiAPGg2xg6VDAFlG_Z0wKqI2HKs5HLsw81kmVQXmgjYMqlCF_nWVMa67PuKb2Hw5w72DLqAc7xzGORBXIUHki7XsuPWO19cvz4Y73pxE0KvbM6brq9Gqb7IIWDmnKGDUiOMwczVTN9dth_H9XHu6O_Qg3fOWbqhqi-srGBGeThG4gQOTdhdoER22HS284qk3wruYcc7XRgcqHzvmMuxKNnLiNB70xKQ4lXNfAUFYYbf9y2PMeAqP6ubFs76Kdw")
                .body(graphQLQuery)
                .when().log().all()
                .post("/learn/graphql")
                .then().log().all()
                .assertThat().statusCode(200).and().body("data.users[0].name", equalTo("tui.glen"));
    }

}
