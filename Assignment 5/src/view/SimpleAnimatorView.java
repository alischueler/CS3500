package view;

import model.IAnimator;
import java.util.List;
import java.util.Map;

/**
 * draws a simple string representation of a animation.
 * this class acts as the view for the textual based animator.
 */
public class SimpleAnimatorView {

  IAnimator<?> animator;

  /**
   * constructor for the SimpleAnimatorView.
   * @param animator the IAnimator we want to use in the view
   */
  public SimpleAnimatorView(IAnimator<?> animator) {
    this.animator = animator;
  }

  @Override
  public String toString() {
    Map<String, List<String>> shapeMap = animator.getCommands();
    String out = "";

    for (String key : shapeMap.keySet()) {
      List<String> commands = shapeMap.get(key);

      for (int i = 0; i < commands.size(); i++) {
        String comm = commands.get(i);

        String[] commList = comm.split(" ");
        if (i == 0) {
          out += "Shape " + commList[0] + " " + commList[1] + "\n";
        }

        out += "motion " + commList[0] + " " + commList[2] + " " + commList[4] + " " + commList[5] +
                " " + commList[14] + " " + commList[15] + " " + commList[8] + " " + commList[9]
                + " " + commList[10] + "     ";

        out += commList[3] + " " + commList[6] + " " + commList[7] + " " + commList[16] +
            " " +
            commList[17] + " " + commList[11] + " " + commList[12] + " " + commList[13] + "\n";

      }
      out += "\n";

    }
    if (out.length() > 3) {
      return out.substring(0, out.length() - 2);
    }
    else {
      return out;
    }
  }
}
