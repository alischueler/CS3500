package cs3500.animator.tweener;

import java.util.List;

/**
 * a object that tweens a given list of shapes.
 * gets the state of the shapes at a given tick.
 * call this repeadtly to make a animator.
 * @param <K> the type for the thing that's being tweened.
 */
public interface ITweener<K> {

  /**
   * returns a list of k at a specific tick.
   *
   * @param tick the tick to get the state for
   * @return a list of K with each K being tweened to the tick
   * @throws IllegalArgumentException if the tick is bad
   * @throws IllegalStateException    if the animation isn't started
   */
  List<K> getStateAt(int tick) throws IllegalArgumentException,
      IllegalStateException;
}
