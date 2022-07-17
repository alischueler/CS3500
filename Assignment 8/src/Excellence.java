import cs3500.animator.controller.ControllerCreator;
import cs3500.animator.controller.IController;
import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.ViewCreator;
import cs3500.animator.view.ViewCreator.ViewType;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * a main class to take information form command line and then execute the commands.
 */
public final class Excellence {

  /**
   * main method to kick off the program from the comand line.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    //relating to model and input
    InputStream input = null;
    IAnimator<Shape> model = null;

    //for the output
    String out = null;

    //for the view
    String typeView = null;
    int tempo = 1;
    IAnimatorView view = null;

    //to show errors
    JFrame frame = new JFrame();
    frame.setSize(300, 300);

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        try {
          input = new FileInputStream(args[i + 1]);
        } catch (FileNotFoundException f) {
          JOptionPane.showMessageDialog(frame, "no file found");
          break;
        }
      }
      if (args[i].equals("-out")) {
        out = args[i + 1];
      }
      if (args[i].equals("-view")) {
        typeView = args[i + 1];
      }
      if (args[i].equals("-speed")) {
        tempo = Integer.parseInt(args[i + 1]);
      }
    }

    //reading input file initializing model
    if (input == null) {
      JOptionPane.showMessageDialog(frame, "no input file given");
      return;
    } else {
      model = AnimationReader.parseFile(new InputStreamReader(input),
          new BasicAnimator.Builder());
      model.startAnimator();
    }
    //initializing view

    if (typeView == null) {
      JOptionPane.showMessageDialog(frame, "no input view given");
      return;
    } else {
      view = ViewCreator.create(ViewType.valueOf(typeView.toUpperCase()), tempo, model);
    }

    //starting the animation
    IController controller = ControllerCreator.create(ViewType.valueOf(typeView.toUpperCase()),
        model, view);
    if (out == null) {
      controller.startAnimate(System.out);
    } else {
      try {
        FileWriter newOut = new FileWriter(out);
        controller.startAnimate(newOut);
        newOut.flush();
        newOut.close();
      } catch (IOException io) {
        JOptionPane.showMessageDialog(frame, "could not append output");
      }
    }
  }
}
