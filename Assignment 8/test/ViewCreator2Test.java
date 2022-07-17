import static org.junit.Assert.assertEquals;

import cs3500.animator.view.ViewCreator.ViewType;
import org.junit.Test;

/**
 * a class to make sure an interactive view can be created from view creator.
 */
public class ViewCreator2Test extends ViewCreatorTest {

  ViewType interactive;
  ViewType level1;
  ViewType level2;
  ViewType level3;

  //examples for the tests
  protected void examples2() {
    interactive = ViewType.INTERACTIVE;
    level1 = ViewType.LEVEL1;
    level2 = ViewType.LEVEL2;
    level3 = ViewType.LEVEL3;
  }

  //tests making an interactive view
  @Test
  public void testInteractive() {
    examples2();
    examples();
    assertEquals(cs3500.animator.view.InteractiveAnimationView.class,
        create.create(interactive, 10, model).getClass());
  }

  //tests making a level1 view
  @Test
  public void testLevel1() {
    examples2();
    examples();
    assertEquals(cs3500.animator.view.PlusFillInteractiveView.class,
        create.create(level1, 10, model).getClass());
  }

  //tests making a level2 view
  @Test
  public void testLevel2() {
    examples2();
    examples();
    assertEquals(cs3500.animator.view.DiscreteView.class,
        create.create(level2, 10, model).getClass());
  }

  //tests making a level3 view
  @Test
  public void testLevel3() {
    examples2();
    examples();
    assertEquals(cs3500.animator.view.SlowMoView.class,
        create.create(level3, 10, model).getClass());
  }

}
