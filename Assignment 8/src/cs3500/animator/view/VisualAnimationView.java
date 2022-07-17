package cs3500.animator.view;

import cs3500.animator.model.IBackground;
import cs3500.animator.model.Shape;
import java.awt.Dimension;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * a class that JFrame to draw the animation. draws the shapes onto a canvas and gets refreshed
 * making a animation.
 */
public class VisualAnimationView extends JFrame implements IVisualView {

  private final int tempo;
  private VisualJPanel jpanel;

  /**
   * constructor for VisualView.
   *
   * @param ticksPerSecond the amount fo tick per second the model draws
   */
  public VisualAnimationView(IViewModel<Shape> animator, int ticksPerSecond) {
    if (animator == null) {
      throw new IllegalArgumentException("Null animator");
    }
    if (ticksPerSecond < 1) {
      throw new IllegalArgumentException("tempo is less than 1");
    }
    this.tempo = ticksPerSecond;
    this.jpanel = new VisualJPanel(animator);

    setTitle("Animation");

    setResizable(true);

    setBackgroundInfo(animator.getBackground());

    add(new JScrollPane(jpanel));

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }


  /**
   * sets up the background information for this interactive view.
   *
   * @param bg the background to be referenced.
   */
  private void setBackgroundInfo(IBackground bg) {
    //jpanel
    jpanel.setBounds(bg.getXBound(), bg.getYBound(), bg.getWidth(), bg.getHeight());
    jpanel.setPreferredSize(new Dimension(bg.getWidth(), bg.getHeight()));
    //frame
    setSize(400, 400);
    setMaximumSize(new Dimension(bg.getWidth() + 20, bg.getHeight() + 20));
  }

  @Override
  public void renderAnimate(List<Shape> currShapes) {
    jpanel.drawAnimation(currShapes);
  }


  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public void render(Appendable out) throws IOException, UnsupportedOperationException {
    throw new UnsupportedOperationException("does not support this operation");
  }

  @Override
  public void setLoop(boolean loop) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("does not support this operation");
  }
}
