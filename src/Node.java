/**
 * Abstract Node Class for Cities and Towns.
 * @param <N>
 */
public class Node<N> {

    public N city;

    /**
     * City / Town Node Constructor
     * @param city name of the city or town to be created.
     */
    public Node(N city) {
        this.city = city;
    }

    /**
     * Getter for City / Town of a Node
     * @return the City or Town name.
     */
    public N getCity() {
        return city;
    }
}
