package cs3500.animator.create;

import cs3500.animator.model.ChangeTime;
import cs3500.animator.model.Color;
import cs3500.animator.model.Command;
import cs3500.animator.model.IColor;
import cs3500.animator.model.ICommands;
import cs3500.animator.model.IPosition;
import cs3500.animator.model.ITotalSize;
import cs3500.animator.model.Position;
import cs3500.animator.model.TotalSize;
import java.io.IOException;

/**
 * makes a animation of a tree in the raw form. the tree starts with all the nodes stacked on top of
 * each other. as the animation goes on the nodes become bigger and stack making a triangle. the
 * process is reversed once the nodes get extremly close
 */
public class BSTAnimation implements ICreate {

  private final int height;
  private final int width;
  private final int radius;
  private final int totalDepth;
  private int identifier;

  /**
   * a constructor for the BSTAnimation.
   *
   * @param depth the depth of the tree
   */
  public BSTAnimation(int depth) {
    this.height = 500;
    this.width = 700;
    this.radius = 20;
    this.totalDepth = depth;
    this.identifier = 0;
  }


  @Override
  public void makeAnimation(Appendable out) throws IOException {
    BinaryTree tree = makeTree(0, width / 2, width / 2);
    out.append("canvas 0 0 ").append(String.valueOf(width)).append(" ")
        .append(String.valueOf(height))
        .append("\n");
    tree.writeShapeNames(out);
    tree.writeAnimationDetails(out);
  }

  /**
   * makes a binary tree to represent the tree animation.
   *
   * @param depth current depth of the tree
   * @param x     the x position of the node
   * @param y     the y position of the node
   * @return made binary tree
   */
  private BinaryTree makeTree(int depth, int x, int y) {
    if (totalDepth > depth) {
      identifier += 1;
      String name = "S" + identifier;
      int startY = height / totalDepth * depth;
      int startX = width / 2;
      int endY = depth * radius;
      int endRed = 255 / totalDepth * depth;
      int endBlue = 255 / totalDepth * (totalDepth - depth);
      ICommands commands = new Command(name, new ChangeTime(0, 100), new Color(0, 0, 0),
          new Color(endRed, 200, endBlue), new Position(startX, startY), new Position(x, endY),
          new TotalSize(0, 0), new TotalSize(radius, radius));
      BinaryTree current = new BinaryTree(commands, makeTree(depth + 1, x - radius, y),
          makeTree(depth + 1, x + radius, y));
      return current;
    }

    return null;
  }

  /**
   * represents a binary tree for storing data.
   */
  private static class BinaryTree {

    private ICommands comd;
    private BinaryTree left;
    private BinaryTree right;

    /**
     * a constructor for a binary tree node.
     *
     * @param comd  the comand for the tree
     * @param left  left child
     * @param right right child
     */
    BinaryTree(ICommands comd, BinaryTree left, BinaryTree right) {
      this.comd = comd;
      this.left = left;
      this.right = right;
    }

    /**
     * initlizes the shaoes in the appendable.
     *
     * @param out the appendable to be written to
     * @throws IOException if appendable fails
     */
    private void writeShapeNames(Appendable out) throws IOException {
      out.append("shape ").append(comd.getShape()).append(" plus\n");
      if (this.left != null) {
        left.writeShapeNames(out);
        right.writeShapeNames(out);
      }
    }

    /**
     * makes the animation details for the tree.
     *
     * @param out the appendable to be written to
     * @throws IOException of te appendable cant transmit
     */
    private void writeAnimationDetails(Appendable out) throws IOException {

      String start = helpWrite(comd.getTicks().getStart(), comd.getStartPosition(),
          comd.getStartSize(), comd.getStartColor()).toString();

      String end = helpWrite(comd.getTicks().getEnd(), comd.getEndPosition(),
          comd.getEndSize(), comd.getEndColor()).toString();

      // adding to the out
      out.append("motion ").append(comd.getShape()).append(" ")
          .append(String.format(start, comd.getTicks().getStart())).append(
          "    ")
          .append(String.format(end, comd.getTicks().getEnd())).append("\n");

      // reverse
      out.append("motion ").append(comd.getShape()).append(" ")
          .append(String.format(end, comd.getTicks().getEnd())).append("    ")
          .append(String.format(start, comd.getTicks().getEnd() * 2)).append(
          "\n");

      if (this.left != null) {
        this.left.writeAnimationDetails(out);
        this.right.writeAnimationDetails(out);
      }

    }

    /**
     * helps write the data to the appendable.
     *
     * @param tick tick for command
     * @param pos  postions for command
     * @param dim  size for command
     * @param col  color for command
     * @return String builder of the command
     * @throws IOException if the appendable cant transmit
     */
    private StringBuilder helpWrite(int tick, IPosition pos, ITotalSize dim, IColor col)
        throws IOException {
      StringBuilder sb = new StringBuilder();
      sb.append("%d ")
          .append(String.valueOf((int) pos.getX())).append(" ")
          .append(String.valueOf((int) pos.getY())).append(" ")
          .append(String.valueOf((int) dim.getTotalWidth())).append(" ")
          .append(String.valueOf((int) dim.getTotalHeight())).append(" ")
          .append(String.valueOf((int) col.getRed())).append(" ")
          .append(String.valueOf((int) col.getGreen()))
          .append(" ").append(String.valueOf((int) col.getBlue()));

      return sb;
    }
  }
}
