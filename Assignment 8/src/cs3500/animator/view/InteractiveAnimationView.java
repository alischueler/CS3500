package cs3500.animator.view;

import cs3500.animator.controller.IButtonListener;
import cs3500.animator.model.IBackground;
import cs3500.animator.model.Shape;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * A class to represent the interactive animation, an instance of IInteravtiveView and therefore
 * IAnimatorView.
 */
public class InteractiveAnimationView extends JFrame implements IInteractiveView {

  private final IVisualJPanel jpanel;
  protected JPanel innerSide = new JPanel();
  protected int tempo;
  private boolean loop;
  protected JPanel side;
  protected JMenuBar menubar;
  protected JMenu menu = new JMenu("Information");
  private JMenuItem startInfo;
  private JMenuItem pauseInfo;
  private JMenuItem resumeInfo;
  private JMenuItem restartInfo;
  private JMenuItem loopInfo;
  private JMenuItem tempoInfo;
  private JButton start;
  private JButton pause;
  private JButton resume;
  private JButton restart;
  private JButton change;
  private JRadioButton loopButton;
  private JLabel currTempo;
  private JTextField tempoSet;


  /**
   * Constructs an interactive animation view.
   *
   * @param jpanel the jpanel the animation will be displayed on.
   * @param model  the view model the view will reference
   * @param tempo  the tempo the animation will run at.
   */
  public InteractiveAnimationView(IVisualJPanel jpanel, IViewModel<Shape> model, int tempo) {
    if (jpanel == null) {
      throw new IllegalArgumentException("JPanel is null");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }
    if (tempo < 1) {
      throw new IllegalArgumentException("tempo is less than 1");
    }
    this.jpanel = jpanel;
    this.loop = false;
    this.tempo = tempo;
    this.side = new JPanel();

    setBackgroundInfo(model.getBackground(), this.jpanel);

    JScrollPane jpanelscroll = new JScrollPane((VisualJPanel) this.jpanel);
    jpanelscroll.getViewport().setView((VisualJPanel) this.jpanel);
    setLayout(new BorderLayout());
    add(jpanelscroll, BorderLayout.WEST);

    setUpSidePanel();

    JScrollPane sidescroll = new JScrollPane(side);
    sidescroll.getViewport().setView(side);
    add(sidescroll, BorderLayout.EAST);

    setMenuInfo();

    setJMenuBar(menubar);

    add(new JScrollPane((VisualJPanel) this.jpanel));
    setTitle("Interactive Animation");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
  }

  /**
   * sets up menu information for the user to understand each interactive option.
   */
  protected void setMenuInfo() {
    startInfo = new JMenuItem("Start");
    startInfo.setActionCommand("start info");
    pauseInfo = new JMenuItem("Pause");
    pauseInfo.setActionCommand("pause info");
    resumeInfo = new JMenuItem("Resume");
    resumeInfo.setActionCommand("resume info");
    restartInfo = new JMenuItem("Restart");
    restartInfo.setActionCommand("restart info");
    loopInfo = new JMenuItem("Loop");
    loopInfo.setActionCommand("loop info");
    tempoInfo = new JMenuItem("Change Tempo");
    tempoInfo.setActionCommand("tempo info");

    menu.add(startInfo);
    menu.add(pauseInfo);
    menu.add(resumeInfo);
    menu.add(restartInfo);
    menu.add(loopInfo);
    menu.add(tempoInfo);

    menubar = new JMenuBar();

    menubar.add(menu);

  }

  /**
   * initializes the interactive side panel of the interactive view.
   */
  protected void setUpSidePanel() {
    innerSide.setSize(new Dimension(250, 200));
    innerSide.setMaximumSize(new Dimension(250, 250));
    innerSide.setLayout(new GridLayout(9, 1));
    side.setSize(new Dimension(250, 300));
    side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));

    start = new JButton("START");
    start.setActionCommand("START");
    innerSide.add(start);

    pause = new JButton("PAUSE");
    pause.setActionCommand("PAUSE");
    innerSide.add(pause);

    resume = new JButton("RESUME");
    resume.setActionCommand("RESUME");
    innerSide.add(resume);

    restart = new JButton("RESTART");
    restart.setActionCommand("RESTART");
    innerSide.add(restart);

    loopButton = new JRadioButton("LOOP");
    loopButton.setActionCommand("LOOP");
    innerSide.add(loopButton);
    loopButton.setHorizontalAlignment(JRadioButton.CENTER);

    currTempo = new JLabel("Current Tempo: " + tempo);
    JLabel tempoLabel = new JLabel("Change Tempo Below");
    tempoSet = new JTextField(5);
    change = new JButton("Change Tempo!");
    change.setActionCommand("change");

    innerSide.add(currTempo);
    currTempo.setHorizontalAlignment(JLabel.CENTER);
    innerSide.add(tempoLabel);
    tempoLabel.setHorizontalAlignment(JLabel.CENTER);
    innerSide.add(tempoSet);
    innerSide.add(change);

    side.add(innerSide);
  }

  @Override
  public void render(Appendable out) throws IOException {
    throw new UnsupportedOperationException("does not append anything");
  }

  @Override
  public boolean getLoop() {
    return loop;
  }

  @Override
  public void setLoop(boolean loop) {
    this.loop = loop;
  }

  @Override
  public void setTempo(int tempo) {
    if (tempo < 1) {
      throw new IllegalArgumentException("tempo cannot be less than 1");
    }
    this.tempo = tempo;
  }

  @Override
  public int getTempo() {
    return tempo;
  }

  @Override
  public void renderAnimate(List<Shape> shapes) {
    jpanel.drawAnimation(shapes);
  }

  @Override
  public void addActionListener(IButtonListener actionListener)
      throws UnsupportedOperationException {
    //side panel
    start.addActionListener(actionListener);
    pause.addActionListener(actionListener);
    resume.addActionListener(actionListener);
    restart.addActionListener(actionListener);
    loopButton.addActionListener(actionListener);
    change.addActionListener(actionListener);
    //menu bar
    startInfo.addActionListener(actionListener);
    pauseInfo.addActionListener(actionListener);
    restartInfo.addActionListener(actionListener);
    resumeInfo.addActionListener(actionListener);
    loopInfo.addActionListener(actionListener);
    tempoInfo.addActionListener(actionListener);
  }

  @Override
  public void clearText() {
    tempoSet.setText("");
  }

  @Override
  public void updateTempo() {
    currTempo.setText("Current Tempo: " + tempo);
  }

  /**
   * sets up the background information for this interactive view.
   *
   * @param bg the background to be referenced.
   */
  private void setBackgroundInfo(IBackground bg, IVisualJPanel jpanel) {
    //jpanel
    JPanel jpanel1 = (JPanel) jpanel;
    jpanel1.setBounds(bg.getXBound(), bg.getYBound(), bg.getWidth(), bg.getHeight());
    jpanel1.setPreferredSize(new Dimension(bg.getWidth(), bg.getHeight()));
    jpanel1.setMaximumSize(new Dimension(bg.getWidth(), bg.getHeight()));
    //frame
    setSize(400, 400);
    setMinimumSize(new Dimension(side.getWidth() + 50, side.getHeight()));
    setMaximumSize(new Dimension(Math.max(bg.getWidth() + 160, side.getWidth() + 50),
        Math.max(bg.getHeight() + 50, side.getHeight())));
  }

  @Override
  public String getNewTempo() {
    return this.tempoSet.getText();
  }
}