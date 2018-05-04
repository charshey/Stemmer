import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class HashMap<K, V> implements Map<K, V> {
  AList<K, V>[] table;
  int capacity;
  double rehashThreshold;
  int n = 0;
  int modCount = 0;

  public HashMap() {
    this(3);
  }

  public HashMap(int n) {
    table = newTable(n);
    for (int i = 0; i < table.length; i++)
      table[i] = new AList<>();
    capacity = n;
    rehashThreshold = 0.25;
  }

  /**
   * Stores a new key, value pair as an Association.
   *
   * @param key   the key to be stored
   * @param value the value to be stored
   * @return ans the old value associated with this key, or null if it is a new key.
   */
  public V put(K key, V value) {
    int i = hash(key);
    Assoc<K, V> a = table[i].get(key);
    if (a == null) {
      table[i].addFront(new Assoc<K, V>(key, value));
      n++;
      modCount++;
      return null;
    }
    V ans = a.value;
    a.value = value;
    modCount++;

    while(getLoadFactor() > rehashThreshold)
      rehash();

    return ans;
  }


  /**
   * Retrieves the value associated with a given key.
   *
   * @param key the key to be accessed
   * @return value associated with the given key
   */
  public V get(K key) {
    int i = hash(key);
    Assoc<K, V> a = table[i].get(key);
    if (a == null)
      return null;
    return a.value;
  }


  /**
   * Removes the given key and its associated value from the Hashmap.
   *
   * @param key the key to be removed
   * @param val the value associated with the given key
   */
  public V remove(K key) {
    int i = hash(key);
    Assoc<K, V> temp = table[i].get(key);
    if (temp == null)
      return null;
    V val = temp.value;
    table[i].remove(0);
    n--;
    modCount++;
    return val;
  }

  /**
   * Returns the number of associations contained in this HashMap.
   *
   * @return n the number of associations contained in this HashMap
   */

  public int size() {
    return n;
  }

  private double getLoadFactor(){
   return (double) n / (double) capacity;
  }

  /**
   * Hashes a given key and returns the location it should be stored in the HashMap.
   *
   * @param key the key to be hashed
   * @return the location in the HashMap to store the key
   */
  public int hash(K key) {
    return Math.abs(key.hashCode()) % table.length;
  }

  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    Iterator<K> keys = keys();
    while(keys.hasNext()){
      K k = keys.next();
      sb.append(k);
      sb.append("=");
      sb.append(this.get(k));
      if(keys.hasNext())
        sb.append(", ");
    }
    sb.append("}");
    return sb.toString();
  }

  /**
   *
   * Increases the size of the array to the smallest prime greater
   * than double the current size that is of the form 4j + 3, and then
   * moves all the key/value associations into the new array.
   *
   *
   * @throws RuntimeException if the table is already at maximum capacity.
   */

  public void rehash() {

    // if the capacity is already at or above max capacity, throw exception.
    if (this.capacity >= Constants.MAX_CAPACITY)
      throw new RuntimeException();

    // set the new size to zero and take the double of the current capacity.
    int newSize = 0;
    int mult = 2 * capacity;

    if ((long) mult >= Integer.MAX_VALUE) // if the doubled value is bigger than the int max value
      newSize = Constants.MAX_CAPACITY; // just set the new size to the max capacity constant.

    while (true) { // keep looping until value is found
      if (Util.isPrime(mult) && ((mult - 3) % 4 == 0)) { // if the value is of form 4j+3 and prime
        if ((long) mult >= Integer.MAX_VALUE) { // if it's too big
          newSize = Constants.MAX_CAPACITY; // just set capacity to the max capacity const
          break; // you're done here
        } else {
          newSize = mult; // if it's of that form and not too big then it's the new size
          break;
        }
      } else
        mult++; // if not of that form than just increment from there and try to find a prime of the form 4j+3.
    }

    // copy the old table
    AList<K,V>[] table = this.table;

    // set the new table to a new table of the size found above, and set the capacity
    HashMap map = new HashMap(newSize);
    table = map.table;

    capacity = newSize;
    int origN = n;

    // initialize every slot in the new table with an empty AList
    for (int i = 0; i < newSize; i++)
      table[i] = new AList<>();

    // for each slot in the old table
    for (AList a : table) {
      Iterator<Assoc<K,V>> it = a.iterator();
      while (it.hasNext()) { // iterate through the list in that slot and hash each next item into the new table with put
        Assoc<K,V> next = it.next();
        put(next.key, next.value);
      }
    }
    // set n to what it was before (all the same Assocs in the table)
    n = origN;

  }


  /**
   * Technical workaround for creating a generic array.
   */
  @SafeVarargs
  private static <K, V> AList<K, V>[] newTable(int length,
                                               AList<K, V>... table) {
    return java.util.Arrays.copyOf(table, length);
  }

  public Iterator<V> values() {
    return new Iterator<V>() {
      int i = 0; //index in table of current assoc list
      int j = -1; //index in list of current assoc
      V value = skip(); //current value of
      int expectedMC = modCount;

      V skip() {
        j++;
        if (j < table[i].size())
          return table[i].get(j).value;
        i++;
        j = 0;
        while (i < table.length  && table[i].isEmpty())
          i++;
        if (i == table.length)
          return null;
        return table[i].get(j).value;
      }

      public boolean hasNext() {
        return value != null;
      }

      public V next() {

        if(expectedMC != modCount)
          throw new ConcurrentModificationException();
        V ans = value;
        value = skip();
        return ans;
      }
    };
  }

  public Iterator<K> keys() {
    return new Iterator<K>() {
      int i = 0; //index in table of current assoc list
      int j = -1; //index in list of current assoc
      K key = skip(); //current value of
      int expectedMC = modCount;

      K skip() {
        j++;
        if (j < table[i].size())
          return table[i].get(j).key;
        i++;
        j = 0;
        while (i < table.length  && table[i].isEmpty())
          i++;
        if (i == table.length)
          return null;
        return table[i].get(j).key;
      }

      public boolean hasNext() {
        return key != null;
      }

      public K next() {
        if(expectedMC != modCount)
          throw new ConcurrentModificationException();
        K ans = key;
        key = skip();
        return ans;
      }
    };
  }

  private static class Util{
    public static boolean isPrime(int n) {
      if (n < 2)
        return false;
      if (n == 2 || n == 3)
        return true;
      if (n % 2 == 0 || n % 3 == 0)
        return false;
      long sqrtN = (long) Math.sqrt(n) + 1;
      for (int i = 6; i <= sqrtN; i += 6) {
        if (n % (i - 1) == 0 || n % (i + 1) == 0)
          return false;
      }
      return true;
    }
  }

}