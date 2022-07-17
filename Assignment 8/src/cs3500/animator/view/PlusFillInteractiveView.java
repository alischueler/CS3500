package cs3500.animator.view;

import cs3500.animator.controller.IButtonListener;
import cs3500.animator.model.Shape;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

/**
 * An implementation of IPlusFillInteractiveView, using common methods of InteractiveAnimationView.
 */
public class PlusFillInteractiveView extends InteractiveAnimationView implements
    IPlusFillInteractiveView {

  private IPlusFillVisualJPanel jpanel;
  private boolean fill;
  private JRadioButton fillOrOutline;
  private JMenuItem fillInfo;

  /**
   * Constructs a PlusFillInteractiveView.
   *
   * @param jpanel the jpanel the animation will be drawn on
   * @param model  the model to be referenced.
   * @param tempo  the tempo the animation is running at
   */
  public PlusFillInteractiveView(IVisualJPanel jpanel, IViewModel<Shape> model, int tempo) {
    super(jpanel, model, tempo);
    if (!(jpanel instanceof IPlusFillVisualJPanel)) {
      throw new IllegalArgumentException("this jpanel does not work for this view");
    }
    this.jpanel = (PlusFillVisualJPanel) jpanel;
    this.jpanel.setView(this);
    this.fill = true;
  }

  @Override
  public void addActionListener(IButtonListener actionListener) {
    super.addActionListener(actionListener);
    fillOrOutline.addActionListener(actionListener);
    fillInfo.addActionListener(actionListener);
  }

  @Override
  public void setFill(boolean fill) {
    this.fill = fill;
  }

  @Override
  public boolean getFill() {
    return fill;
  }

  @Override
  public void renderAnimate(List<Shape> shapes) {
    jpanel.drawAnimation(shapes);
  }

  /**
   * initializes the interactive side panel of the interactive view.
   */
  protected void setUpSidePanel() {
    super.setUpSidePanel();
    super.innerSide.setSize(new Dimension(250, 300));
    super.innerSide.setMaximumSize(new Dimension(250, 300));
    innerSide.setLayout(new GridLayout(10, 1));
    super.side.setSize(new Dimension(250, 400));

    this.fillOrOutline = new JRadioButton("Outline");
    fillOrOutline.setActionCommand("fill");

    super.innerSide.add(fillOrOutline);
    super.side.add(innerSide);
  }

  /**
   * sets up menu information for the user to understand each interactive option.
   */
  protected void setMenuInfo() {
    super.setMenuInfo();
    this.fillInfo = new JMenuItem("Outline Info");
    fillInfo.setActionCommand("outline info");
    super.menu.add(fillInfo);
  }

}
