/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


/**
 * Interface for the adaptable priority queue ADT. The Entry instance
 * returned by the insert method can later be used to remove the
 * entry, or to modify that entry's key or value.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V> {

    /**
     * Removes the given entry from the priority queue.
     *
     * @param entry an entry of this priority queue
     * @throws IllegalArgumentException if e is not a valid entry for the priority queue.
     */
    void remove(Entry<K, V> entry) throws IllegalArgumentException;

    /**
     * Replaces the key of an entry.
     *
     * @param entry an entry of this priority queue
     * @param key   the new key
     * @throws IllegalArgumentException if e is not a valid entry for the priority queue.
     */
    void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException;

    /**
     * Replaces the value of an entry.
     *
     * @param entry an entry of this priority queue
     * @param value the new value
     * @throws IllegalArgumentException if e is not a valid entry for the priority queue.
     */
    void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException;
}
