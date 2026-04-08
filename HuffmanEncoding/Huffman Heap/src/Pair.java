/**
 * 
 * @author Cameron Rodgers
 * Pair is use as a data type to hold the char and frequency. 
 *
 * @param <K> Key Char 
 * @param <V> Value Frequency 
 */
public class Pair<K, V> {
	private final K key;
	private final V value;
	
	public Pair(K key, V value) {
		this.key = key;
		this.value = value; 
	}
	public K getKey() {
		return key;
	}
	public V getValue() {
		return value;
	}
}
