package cs3500.animator.create;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * makes a animation of bubble sort.
 */
public class BubbleSort implements ICreate {

  private int height;
  private int width;
  private int bars;
  private int tick;

  /**
   * a constructor for the bubble sort animation.
   * @param bars amount of bars in the animation
   */
  public BubbleSort(int bars) {
    this.height = 500;
    this.width = 500;
    this.bars = bars;
    this.tick = 0;
  }

  @Override
  public void makeAnimation(Appendable out) throws IOException {
    // key position, val height
    HashMap<Integer, List<Integer>> sort = makeRandom();
    addShapes(out, sort);
    initialize(out, sort);

    while (notSorted(sort)) {
      for (int i = 0; i < bars; i++) {
        // swap the data in the positions
        // closer to zero has the bigger bars
        makeColor(out, sort.get(i).get(0), i, sort.get(i).get(1), 255, 0, 2);
        tick += 2;

        // if this is the last bar then ignore the check below
        if (i + 1 >= bars) {
          continue;
        }

        // look to sort the bars
        if (sort.get(i).get(0) < sort.get(i + 1).get(0)) {
          List<Integer> bigger = sort.get(i + 1);
          List<Integer> smaller = sort.get(i);

          swap(out, sort.get(i).get(0), i, sort.get(i).get(1), 1);
          swap(out, sort.get(i + 1).get(0), i + 1, sort.get(i + 1).get(1), -1);

          sort.put(i, bigger);
          sort.put(i + 1, smaller);
          tick += 2;
        }
      }
    }
    makeGreen(out, sort);
  }

  /**
   * Makes a bar green un till every bar is green.
   * @param out the appendable to write the commands to
   * @param sort the data that is sorted
   * @throws IOException if appendable cant be written to
   */
  private void makeGreen(Appendable out, HashMap<Integer, List<Integer>> sort) throws IOException {
    for (int i = 0; i < bars; i++) {
      // swap the data in the positions
      // closer to zero has the bigger
      int duration = (bars - i) * 2;

      makeColor(out, sort.get(i).get(0), i, sort.get(i).get(1), 0, 255, duration);
      tick += 2;
    }
  }

  /**
   * sees if the animation is not sorted.
   *
   * @param sort the data that's currently being used
   * @return if the animation is not sorted
   */
  private boolean notSorted(HashMap<Integer, List<Integer>> sort) {
    boolean out = true;

    for (int i = 0; i < bars; i++) {
      if (i + 1 >= bars) {
        continue;
      }
      if (sort.get(i).get(0) < sort.get(i + 1).get(0)) {
        return true;
      }
    }

    return false;
  }

  /**
   * writes the starting variables for the animation.
   *
   * @param out  the appendable to write to
   * @param sort the data that's being written.
   * @throws IOException if the appendable is not able to transmit
   */
  private void initialize(Appendable out, HashMap<Integer, List<Integer>> sort) throws IOException {
    int barWidth = width / bars;
    for (int i : sort.keySet()) {
      int xPos = barWidth * i;
      int yPos = height - sort.get(i).get(0);

      out.append("motion ").append(String.valueOf(sort.get(i).get(1))).append(" ");
      addAnimation(out, 0, xPos, yPos, barWidth, sort.get(i).get(0), 0, 0, 0);
      out.append("    ");
      addAnimation(out, 0, xPos, yPos, barWidth, sort.get(i).get(0), 0, 0, 0);
      out.append("\n");
    }
  }

  /**
   * making the swap animation for when two bars have to swap.
   *
   * @param out        appendabe to be written to
   * @param bar        the bar height
   * @param position   the position of the bar
   * @param id         the id of the bar
   * @param adjustment move foward or backwards
   * @throws IOException if appendable cant transmit
   */
  private void swap(Appendable out, int bar, int position, int id, int adjustment)
      throws IOException {
    int barWidth = width / bars;
    int barHeight = bar;
    int startXPos = barWidth * position;
    int startYPos = height - bar;
    int endXPos = barWidth * (position + adjustment);
    int endYPos = height - bar;

    out.append("motion ").append(String.valueOf(id)).append(" ");
    addAnimation(out, tick, startXPos, startYPos, barWidth, barHeight, 0, 0, 0);
    out.append("    ");
    addAnimation(out, tick + 1, endXPos, endYPos, barWidth, barHeight, 0, 0, 0);
    out.append("\n");

    String s = out.toString();
  }

  /**
   * adds the shapes and canvas command for the animation.
   *
   * @param out  the appendable to write to
   * @param sort the data to write to the appendable
   * @throws IOException of the appendable cant transmit
   */
  private void addShapes(Appendable out, HashMap<Integer, List<Integer>> sort) throws IOException {
    out.append("canvas 0 0 ").append(String.valueOf(width)).append(" ")
        .append(String.valueOf(height)).append("\n");
    for (int i : sort.keySet()) {
      out.append("shape ").append(String.valueOf(i)).append(" rectangle\n");
    }
  }

  /**
   * makes the bar red for 2 ticks.
   *
   * @param out      the appendable to write to
   * @param bar      the height of the bar
   * @param position the position of the bar
   * @param id       the id of the bar
   * @throws IOException if appendable cant commit
   */
  private void makeColor(Appendable out, int bar, int position, int id, int red, int green,
      int duration) throws IOException {
    int barWidth = width / bars;
    int barHeight = bar;
    int xPos = barWidth * position;
    int yPos = height - bar;
    out.append("motion ").append(String.valueOf(id)).append(" ");
    addAnimation(out, tick, xPos, yPos, barWidth, barHeight, 0, 0, 0);
    out.append("    ");
    addAnimation(out, tick + duration / 2, xPos, yPos, barWidth, barHeight, red, green, 0);
    out.append("\n");

    out.append("motion ").append(String.valueOf(id)).append(" ");
    addAnimation(out, tick + duration / 2, xPos, yPos, barWidth, barHeight, red, green, 0);
    out.append("    ");
    addAnimation(out, tick + duration, xPos, yPos, barWidth, barHeight, 0, 0, 0);
    out.append("\n");

  }

  /**
   * adds the animation command for the animation.
   *
   * @param out the animation for the
   * @param t   tick for the animation
   * @param x   x position
   * @param y   y position
   * @param w   width
   * @param h   height
   * @param r   red
   * @param g   blue
   * @param b   green
   * @throws IOException if the appendable cant transmit
   */
  private void addAnimation(Appendable out, int t, int x, int y, int w, int h, int r, int g,
      int b) throws IOException {
    out.append(String.valueOf(t)).append(" ")
        .append(String.valueOf(x)).append(" ").append(String.valueOf(y)).append(" ")
        .append(String.valueOf(w)).append(" ").append(String.valueOf(h)).append(" ")
        .append(String.valueOf(r)).append(" ").append(String.valueOf(g)).append(" ")
        .append(String.valueOf(b));
  }

  /**
   * makes the hashmap for the animation.
   *
   * @return hash map containing the data to be sorted
   */
  private HashMap<Integer, List<Integer>> makeRandom() {
    // index zero height index 1 is id
    HashMap<Integer, List<Integer>> out = new HashMap<>();
    for (int i = 0; i < bars; i++) {
      int rand = (int) (height * Math.random());
      out.put(i, new ArrayList<>(Arrays.asList(rand, i)));

    }

    return out;
  }
}
