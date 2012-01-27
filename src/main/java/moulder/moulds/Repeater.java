package moulder.moulds;

import moulder.Moulder;
import moulder.Value;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A moulder that repeats its input element
 * 
 * @author jawher
 * 
 * @param <T>
 *            the type of the data to be associated with the repeated elements
 */
public class Repeater<T> implements Moulder {
	private Value<? extends Iterable<T>> items;

	/**
	 * Creates a moulder that for evey item <code>e</code> in the
	 * {@link Iterable} returned by the supplied value, generates a copy of its
	 * input element with e as its associated data
	 * 
	 * @param items
	 */
	public Repeater(Value<? extends Iterable<T>> items) {
		this.items = items;
	}

    public List<Node> process(Element element) {
        List<Node> res = new ArrayList<Node>();
		Iterator<T> it = items.get().iterator();
		while (it.hasNext()) {
            it.next();
			res.add(copy(element));
		}
		return res;
	}
    
    private Element copy(Element e) {
        Element res = e.ownerDocument().createElement(e.tagName());
        for(Attribute a:e.attributes()){
            res.attr(a.getKey(), a.getValue());
        }
        res.html(e.html());
        return res;
    }

}
