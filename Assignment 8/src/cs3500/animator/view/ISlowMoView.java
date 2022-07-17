package cs3500.animator.view;

/**
 * a public interface that allows the slow mo function of an interactive view to exist.
 */
public interface ISlowMoView extends IInteractiveView {

  /**
   * determines current the tempo of the animation.
   *
   * @param tick the current tick of the animation.
   * @return the correct tempo of the animation.
   */
  int getCorrectTempo(int tick);
}
