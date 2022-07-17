import static org.junit.Assert.assertEquals;

import cs3500.animator.view.ViewCreator.ViewType;
import org.junit.Test;

/**
 * a class to make sure an interactive view can be created from view creator.
 */
public class ViewCreator2Test extends ViewCreatorTest {
  ViewType interactive;

  //examples for the tests
  protected void examples2() {
    interactive = ViewType.INTERACTIVE;
  }

  //tests making an interactive view
  @Test
  public void testInteractive() {
    examples2();
    examples();
    assertEquals(cs3500.animator.view.InteractiveAnimationView.class,
        create.create(interactive, 10, model).getClass());
  }

}
