import static org.junit.Assert.assertEquals;

import cs3500.animator.model.Color;
import cs3500.animator.model.Position;
import cs3500.animator.model.TotalSize;
import cs3500.animator.tweener.Tweener;
import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.view.ShapeViewModel;
import java.util.List;
import org.junit.Test;

/**
 * a class for testing the Tweener.
 */
public class TestTweener {


  AnimationBuilder<IAnimator<Shape>> emptyBuilder;

  TestLambda makeBob;
  TestLambda bob010Inc;
  TestLambda bob1020Inc;

  TestLambda makeJeff;
  TestLambda jeff010Inc;
  TestLambda jeff1020Inc;

  TestLambda makeStaySameRect;
  TestLambda staySameRect010;
  TestLambda staySameRect1020;

  TestLambda makeNoTimeGap;
  TestLambda noTimeGap11;

  TestLambda bobDelayed;
  TestLambda bobDecreasing;


  /**
   * a FunctionalInterface that allows for the stacking of commands. for builders allows for easy
   * creation of IAnimators. allows for switching and swapping the order/commands used.
   */
  @FunctionalInterface
  private interface TestLambda {

    /**
     * method to run the TestLambda.
     *
     * @param b the builder to add the command to
     * @return the passed builder which is mutated
     */
    AnimationBuilder<IAnimator<Shape>> apply(AnimationBuilder<IAnimator<Shape>> b);
  }

  /**
   * a method that makes objects for testing.
   */
  protected void examples() {

    emptyBuilder = new BasicAnimator.Builder().setBounds(0, 0, 500, 300);

    makeBob = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.declareShape("bob", "rectangle");
      return builder;
    };
    bob010Inc = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
          10, 10, 10, 20, 20, 20, 20, 20);
      return builder;
    };
    bob1020Inc = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("bob", 10, 10, 10, 20, 20, 20, 20,
          20, 20, 20, 30, 40, 30, 120, 100, 80);
      return builder;
    };

    makeJeff = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.declareShape("jeff", "ellipse");
      return builder;
    };
    jeff010Inc = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("jeff", 0, 0, 0, 10, 10, 0, 0,
          0, 10, 10, 10, 20, 20, 20, 20, 20);
      return builder;
    };
    jeff1020Inc = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("jeff", 10, 10, 10, 20, 20, 20, 20,
          20, 20, 20, 30, 40, 30, 120, 100, 80);
      return builder;
    };

    // shapes that stay in the same place
    makeStaySameRect = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.declareShape("SSR", "ellipse");
      return builder;
    };

    staySameRect010 = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("SSR", 0, 10, 10, 10, 10, 10, 10,
          10, 10, 10, 10, 10, 10, 10, 10, 10);
      return builder;
    };

    staySameRect1020 = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("SSR", 10, 10, 10, 10, 10, 10, 10,
          10, 20, 10, 10, 10, 10, 10, 10, 10);
      return builder;
    };

    makeNoTimeGap = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.declareShape("NoTimeGap", "ellipse");
      return builder;
    };

    noTimeGap11 = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("NoTimeGap", 1, 10, 10, 10, 10, 10, 10,
          10, 1, 10, 10, 10, 10, 10, 10, 10);
      return builder;
    };

    bobDelayed = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("bob", 5, 0, 0, 10, 10, 0, 0, 0,
          10, 10, 10, 20, 20, 20, 20, 20);
      return builder;
    };

    bobDecreasing = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("bob", 0, 10, 20, 10, 20, 10, 20,
          40, 10, 0, 10, 0, 10, 0, 10, 30);
      return builder;
    };
  }


  /**
   * makes a tweener from the given builder.
   *
   * @param builder the builder to be turned into a model
   * @return a list of shapes which are from the given builder
   */
  private Tweener makeTweener(AnimationBuilder<IAnimator<Shape>> builder) {
    IAnimator<Shape> model = builder.build();
    model.startAnimator();
    return new Tweener(new ShapeViewModel(model));
  }
  /*
   * -------------------------------------- getStateAt --------------------------------------
   */

  /**
   * reversing the movement of the tick.
   */
  @Test
  public void testGetStateAtReverse() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff010Inc
        .apply(makeJeff.apply(bob010Inc.apply(makeBob.apply(emptyBuilder))));
    Tweener tween = makeTweener(builder);
    tween.getStateAt(20);
    List<Shape> l = tween.getStateAt(10);

    assertEquals(l.get(0).getCurrColor(), new Color(20, 20, 20));
    assertEquals(l.get(0).getCurrentPosition(), new Position(10, 10));
    assertEquals(l.get(0).getCurrentDimension(), new TotalSize(20, 20));

    assertEquals(l.get(1).getCurrColor(), new Color(20, 20, 20));
    assertEquals(l.get(1).getCurrentPosition(), new Position(10, 10));
    assertEquals(l.get(1).getCurrentDimension(), new TotalSize(20, 20));
  }

  /**
   * a shape that's going down in tweaning.
   */
  @Test
  public void testGettingSmaller() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = bobDecreasing.apply(makeBob.apply(emptyBuilder));
    Tweener tween = makeTweener(builder);
    List<Shape> l = tween.getStateAt(5);
    // 0, 10, 0, 10, 0, 10, 30

    // assertEquals(l.get(0).getCurrColor(), new Color(10, 15, 35));
    assertEquals(l.get(0).getCurrentPosition(), new Position(5, 15));
    assertEquals(l.get(0).getCurrentDimension(), new TotalSize(5, 15));
  }

  /**
   * a shape the jumps to a middle position from zero.
   */
  @Test
  public void testMiddleJump() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = bob1020Inc.apply(makeBob.apply(emptyBuilder));
    Tweener tween = makeTweener(builder);
    List<Shape> l = tween.getStateAt(15);

    assertEquals(l.get(0).getCurrentPosition(), new Position(15, 20));
    assertEquals(l.get(0).getCurrentDimension(), new TotalSize(30, 25));
    assertEquals(l.get(0).getCurrColor(), new Color(70, 60, 50));
  }

  /**
   * a shape on its last tick with no replacement does jumps there.
   */
  @Test
  public void testLastTick() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = bob1020Inc.apply(makeBob.apply(emptyBuilder));
    Tweener tween = makeTweener(builder);
    List<Shape> l = tween.getStateAt(20);

    assertEquals(l.get(0).getCurrentPosition(), new Position(20, 30));
    assertEquals(l.get(0).getCurrentDimension(), new TotalSize(40, 30));
    assertEquals(l.get(0).getCurrColor(), new Color(120, 100, 80));
  }

  /**
   * a shape on its last tick with no replacement does not jump there.
   */
  @Test
  public void testLastTickJumpoFromZero() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = bob1020Inc.apply(makeBob.apply(emptyBuilder));
    Tweener tween = makeTweener(builder);

    for (int i = 0; i < 20; i++) {
      tween.getStateAt(i);
    }
    List<Shape> l = tween.getStateAt(20);

    assertEquals(l.get(0).getCurrentPosition(), new Position(20, 30));
    assertEquals(l.get(0).getCurrentDimension(), new TotalSize(40, 30));
    assertEquals(l.get(0).getCurrColor(), new Color(120, 100, 80));
  }


  /**
   * nothing is on the board bc the tick is so high.
   */
  @Test
  public void testTickHigh() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = bob1020Inc.apply(makeBob.apply(emptyBuilder));
    Tweener tween = makeTweener(builder);
    List<Shape> l = tween.getStateAt(21);

    assertEquals(l.size(), 0);

  }


  /**
   * returns the correct state at at the first tick.
   */
  @Test
  public void testWithJumpFive() {
    examples();

    AnimationBuilder<IAnimator<Shape>> builder = bob1020Inc.apply(makeBob.apply(emptyBuilder));
    Tweener tween = makeTweener(builder);
    List<Shape> l = tween.getStateAt(0);
    assertEquals(l.size(), 0);

  }

  /**
   * returns the correct state when there is olny shapes that should not show up yet.
   */
  @Test
  public void testHiddingAtStart() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder =
        jeff1020Inc
            .apply(makeJeff.apply(bob1020Inc.apply(bobDelayed.apply(makeBob.apply(emptyBuilder)))));
    Tweener tween = makeTweener(builder);
    List<Shape> l = tween.getStateAt(1);
    assertEquals(l.size(), 0);

    l = tween.getStateAt(4);
    assertEquals(l.size(), 0);
  }

  /**
   * testing when one shape should be hidden and one should be shown.
   */
  @Test
  public void testOneHiddenOneShown() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder =
        jeff1020Inc
            .apply(makeJeff.apply(bob1020Inc.apply(bobDelayed.apply(makeBob.apply(emptyBuilder)))));
    Tweener tween = makeTweener(builder);

    List<Shape> l = tween.getStateAt(5);
    assertEquals(l.size(), 1);
    assertEquals(l.get(0).getCurrentPosition(), new Position(0, 0));
    assertEquals(l.get(0).getCurrentDimension(), new TotalSize(10, 10));
    assertEquals(l.get(0).getCurrColor(), new Color(0, 0, 0));
  }


  /**
   * testing a shape that stays in the same spot.
   */
  @Test
  public void testWithShapeStaySame() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder =
        staySameRect1020.apply(staySameRect010.apply(makeStaySameRect.apply(emptyBuilder)));
    Tweener tween = makeTweener(builder);
    List<Shape> l = tween.getStateAt(1);
    assertEquals(l.get(0).getCurrentPosition(), new Position(10, 10));
    assertEquals(l.get(0).getCurrentDimension(), new TotalSize(10, 10));
    assertEquals(l.get(0).getCurrColor(), new Color(10, 10, 10));

    l = tween.getStateAt(5);
    assertEquals(l.get(0).getCurrentPosition(), new Position(10, 10));
    assertEquals(l.get(0).getCurrentDimension(), new TotalSize(10, 10));
    assertEquals(l.get(0).getCurrColor(), new Color(10, 10, 10));

    l = tween.getStateAt(10);
    assertEquals(l.get(0).getCurrentPosition(), new Position(10, 10));
    assertEquals(l.get(0).getCurrentDimension(), new TotalSize(10, 10));
    assertEquals(l.get(0).getCurrColor(), new Color(10, 10, 10));
  }


  /**
   * playing a full tween.
   */
  @Test
  public void testIncByOneTick() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder =
        bobDelayed.apply(makeBob.apply(emptyBuilder));
    Tweener tween = makeTweener(builder);

    List<Shape> l = tween.getStateAt(5);
    assertEquals(new Position(0, 0), l.get(0).getCurrentPosition());
    assertEquals(new TotalSize(10, 10), l.get(0).getCurrentDimension());
    assertEquals(new Color(0, 0, 0), l.get(0).getCurrColor());

    l = tween.getStateAt(6);
    assertEquals(new Position(2, 2), l.get(0).getCurrentPosition());
    assertEquals(new TotalSize(12, 12), l.get(0).getCurrentDimension());
    assertEquals(new Color(4, 4, 4), l.get(0).getCurrColor());

    l = tween.getStateAt(7);
    assertEquals(new Position(4, 4), l.get(0).getCurrentPosition());
    assertEquals(new TotalSize(14, 14), l.get(0).getCurrentDimension());
    assertEquals(new Color(8, 8, 8), l.get(0).getCurrColor());

    l = tween.getStateAt(8);
    assertEquals(new Position(6, 6), l.get(0).getCurrentPosition());
    assertEquals(new TotalSize(16, 16), l.get(0).getCurrentDimension());
    assertEquals(new Color(12, 12, 12), l.get(0).getCurrColor());

    l = tween.getStateAt(9);
    assertEquals(new Position(8, 8), l.get(0).getCurrentPosition());
    assertEquals(new TotalSize(18, 18), l.get(0).getCurrentDimension());
    assertEquals(new Color(16, 16, 16), l.get(0).getCurrColor());

    l = tween.getStateAt(10);
    assertEquals(new Position(10, 10), l.get(0).getCurrentPosition());
    assertEquals(new TotalSize(20, 20), l.get(0).getCurrentDimension());
    assertEquals(new Color(20, 20, 20), l.get(0).getCurrColor());

    l = tween.getStateAt(11);
    assertEquals(l.size(), 0);


  }

  /**
   * throw error when the tick is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void tickNegative() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder =
        staySameRect1020.apply(staySameRect010.apply(makeStaySameRect.apply(emptyBuilder)));
    Tweener tween = makeTweener(builder);
    tween.getStateAt(-1);
  }

  /**
   * throw error when the animation hasnt started.
   */
  @Test(expected = IllegalStateException.class)
  public void gameHasntStarted() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder =
        staySameRect1020.apply(staySameRect010.apply(makeStaySameRect.apply(emptyBuilder)));
    IAnimator<Shape> model = builder.build();
    new Tweener(new ShapeViewModel(model)).getStateAt(10);
  }
}
