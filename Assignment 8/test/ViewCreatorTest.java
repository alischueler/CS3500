
import static org.junit.Assert.assertEquals;

import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.ViewCreator;
import cs3500.animator.view.ViewCreator.ViewType;
import org.junit.Test;

/**
 * tests the ViewCreator.
 */
public class ViewCreatorTest {

  IAnimator<Shape> model;
  ViewType text;
  ViewType vis;
  ViewType svg;
  ViewCreator create;

  //examples for the tests
  protected void examples() {
    model = new BasicAnimator.Builder().declareShape("s", "ellipse")
        .setBounds(5, 5, 5, 5).addMotion("s", 2, 4, 5, 3,
            2, 2, 3, 4, 5, 4, 5, 6, 7, 7, 6, 7)
        .build();
    text = ViewType.TEXT;
    vis = ViewType.VISUAL;
    svg = ViewType.SVG;
    create = new ViewCreator();
  }

  //tests making a textual view
  @Test
  public void testTextual() {
    examples();
    assertEquals(cs3500.animator.view.TextualAnimatorView.class,
        create.create(text, 3, model).getClass());
  }

  //tests making a visual view
  @Test
  public void testVisual() {
    examples();
    assertEquals(cs3500.animator.view.VisualAnimationView.class,
        create.create(vis, 10, model).getClass());
  }

  //tests making a svg view
  @Test
  public void testSVG() {
    examples();
    assertEquals(cs3500.animator.view.SVGAnimatorView.class,
        create.create(svg, 1, model).getClass());
  }
}