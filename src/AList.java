
import java.util.Iterator;

public class AList<K, V> extends DoublyLinkedList<Assoc<K, V>> {

  /**
   * Inserts the association a at the front of this list.
   * @param a the association to be added
   */

  public void addFront(Assoc<K, V> a) {
    Node temp = head.next;
    Node front = new Node(a, head, head.next);
    temp.prev = front;
    head.next = front;
    n++;
    modCount++;
  }
  
  /**
   * Returns the association with the given key in this map, if it
   * exists, and null otherwise. Self-adjusts by moving the returned
   * association to the front.
   * @param key the search key
   * @return the association containing the search key, or null if not found
   */

  public Assoc<K, V> get(K key) {
    Assoc<K, V> a;
    Iterator<Assoc<K, V>> it = iterator();
    while (it.hasNext()) {
      a = it.next();
      if (key.equals(a.key)) {
        //it.remove();
        //addFront(a);
        return a;
      }
    }
    return null;
  }

}
