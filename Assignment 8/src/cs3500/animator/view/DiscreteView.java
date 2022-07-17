package cs3500.animator.view;

import cs3500.animator.controller.IButtonListener;
import cs3500.animator.model.Shape;
import java.awt.GridLayout;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

/**
 * an implementation of interactiveview that allows for discrete playing.
 */
public class DiscreteView extends InteractiveAnimationView {

  private JRadioButton discrete;
  private JMenuItem discreteInfo;

  /**
   * Constructs an interactive animation view.
   *
   * @param jpanel the jpanel the animation will be displayed on.
   * @param model  the view model the view will reference
   * @param tempo  the tempo the animation will run at.
   */
  public DiscreteView(IVisualJPanel jpanel, IViewModel<Shape> model, int tempo) {
    super(jpanel, model, tempo);
  }

  /**
   * sets up menu information for the user to understand each interactive option.
   */
  protected void setUpSidePanel() {
    super.setUpSidePanel();
    innerSide.setLayout(new GridLayout(10, 1));

    discrete = new JRadioButton("Discrete");
    discrete.setHorizontalAlignment(JRadioButton.CENTER);
    discrete.setActionCommand("discrete");
    innerSide.add(discrete);
    side.add(innerSide);
  }

  /**
   * sets up menu information for the user to understand each interactive option.
   */
  protected void setMenuInfo() {
    super.setMenuInfo();
    this.discreteInfo = new JMenuItem("Discrete Info");
    discreteInfo.setActionCommand("discrete info");
    super.menu.add(discreteInfo);
  }

  @Override
  public void addActionListener(IButtonListener actionListener)
      throws UnsupportedOperationException {
    super.addActionListener(actionListener);
    discrete.addActionListener(actionListener);
    discreteInfo.addActionListener(actionListener);
  }
}
