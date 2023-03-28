package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import pojos.States;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    private String name;
    private ArrayList<States> states;

    public Country() {
    }

    public Country(String name, ArrayList<States> states) {
        this.name = name;
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<States> getStates() {
        return states;
    }

    public void setStates(ArrayList<States> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", states=" + states +
                '}';
    }

}
