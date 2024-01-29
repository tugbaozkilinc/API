package graphql;

public class GraphQLQuery {

    //This is the main POJO class for complete query and json variable

    private String query;

    private Object variables;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Object getVariables() {
        return variables;
    }

    public void setVariables(Object variables) {
        this.variables = variables;
    }

}
