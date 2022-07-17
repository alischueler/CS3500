package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the most basic form of a animator. The animator has a map of commands which it uses to
 * make the animation. The K for this animator is a Shape object.
 */
public final class BasicAnimator implements IAnimator<Shape> {

  // INVARIANT: allShapes, b and commands are not null
  private SimpleBackground b;
  private List<Shape> allShapes;
  private Map<String, List<ISimpleCommands>> commands;
  private boolean started;


  /**
   * Constructs a BasicAnimator.
   *
   * @param shapes     represent the shapes that have been created for this animation
   * @param commands   represent the commands that have been created for this animation
   * @param background represent the background of the animation
   */
  private BasicAnimator(List<Shape> shapes, Map<String, List<ISimpleCommands>> commands,
      SimpleBackground background) {
    if (background == null) {
      throwIllArg("background is null");
    }
    if (shapes == null) {
      throwIllArg("shapes are null");
    }
    if (commands == null) {
      throwIllArg("commands are null");
    }
    this.b = background;
    this.allShapes = shapes;
    this.commands = commands;
    this.started = false;
  }

  /**
   * A Builder to add Shapes and motions to this BasicAnimator.
   */
  public static final class Builder implements AnimationBuilder<IAnimator<Shape>> {

    private List<Shape> shapes = new ArrayList<>();
    private Map<String, List<ISimpleCommands>> commands = new HashMap<>();
    private SimpleBackground background;

    @Override
    public IAnimator<Shape> build() {
      return new BasicAnimator(shapes, commands, background);
    }

    @Override
    public AnimationBuilder<IAnimator<Shape>> setBounds(int x, int y, int width, int height) {
      this.background = new SimpleBackground(height, width, x, y);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimator<Shape>> declareShape(String name, String type) {
      if (name == null || type == null) {
        throw new IllegalArgumentException("name or type is null");
      }
      if (type.equals("rectangle")) {
        this.shapes.add(new Rectangle(name));
      }
      if (type.equals("ellipse")) {
        this.shapes.add(new Ellipse(name));
      } else if (type.equals("plus")) {
        this.shapes.add(new Plus(name));
      }
      return this;
    }


    @Override
    public AnimationBuilder<IAnimator<Shape>> addMotion(String name, int t1, int x1, int y1,
        int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {
      if (name == null) {
        throw new IllegalArgumentException("name is null");
      }
      StringBuilder com = new StringBuilder();
      com.append(
          name + " " + t1 + " " + t2 + " " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + w1
              + " " +
              h1 + " " + w2 + " " + h2 + " " + r1 + " " + g1 + " " + b1 + " " + r2 + " " + g2 +
              " " + b2);
      String comToAdd = com.toString();
      ICommands command = new Command(comToAdd);
      if (!this.commands.containsKey(name)) {
        this.commands.put(name, new ArrayList<>());
      }
      this.commands.get(name).add(command);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimator<Shape>> addTempo(int t1, int t2, int tempo) {
      if (!this.commands.containsKey("slow-mo")) {
        this.commands.put("slow-mo", new ArrayList<>());
      }
      this.commands.get("slow-mo").add(new BasicTempoCommand("slow-mo",
          new ChangeTime(t1, t2), tempo));
      return this;
    }
  }


  @Override
  public void startAnimator() throws IllegalArgumentException {
    checkShapes();
    addCommandsToShapes();
    checkGaps();
    checkCommands(this.commands);
    this.started = true;
  }

  /**
   * checks for gaps in the commands of shapes.
   */
  private void checkGaps() {
    for (Shape s : allShapes) {
      s.checkGaps();
    }
  }

  /**
   * checks to see if any commands overlap.
   *
   * @param com the commands to check
   */
  private void checkCommands(Map<String, List<ISimpleCommands>> com) {
    for (String key : com.keySet()) {
      for (int i = 0; i < com.get(key).size(); i++) {
        for (int n = 0; n < com.get(key).size(); n++) {
          if (i == n) {
            continue;
          }
          ISimpleCommands c1 = com.get(key).get(i);
          ISimpleCommands c2 = com.get(key).get(n);
          int existingStart = c1.getTicks().getStart();
          int existingEnd = c1.getTicks().getEnd();
          int newStart = c2.getTicks().getStart();
          int newEnd = c2.getTicks().getEnd();

          if (existingStart == newStart && existingEnd == newEnd ||
              (newStart > existingStart && newStart < existingEnd)
              || (newEnd > existingStart && newEnd < existingEnd)) {
            throw new IllegalArgumentException("Commands overlap");
          }
        }
      }
    }
  }

  /**
   * Checks the shapes to make sure there are no duplicate names.
   */
  private void checkShapes() {
    for (int i = 0; i < allShapes.size(); i++) {
      for (int j = 0; j < allShapes.size(); j++) {
        if (i != j) {
          if (allShapes.get(i).getShapeName().equals(allShapes.get(j).getShapeName())) {
            throwIllArg("duplicate shapes");
          }
        }
      }
    }
  }

  /**
   * adds the given map of commands to a shape.
   */
  private void addCommandsToShapes() {

    for (cs3500.animator.model.Shape s : allShapes) {
      for (String name : commands.keySet()) {
        if (s.getShapeName().equals(name)) {
          for (ISimpleCommands com : commands.get(name)) {
            if (com.getShape().equals(name)) {
              s.addCommand((ICommands) com);
            }
          }
        }
      }
    }
  }

  /**
   * throws a IllegalArgumentException with the given message.
   *
   * @param msg message to be thrown
   * @throws IllegalArgumentException when started
   */
  private void throwIllArg(String msg) throws IllegalArgumentException {
    throw new IllegalArgumentException(msg);
  }

  @Override
  public void addCommand(ICommands command)
      throws IllegalArgumentException, IllegalStateException {

    //checking if the command is valid
    if (command == null) {
      throwIllArg("command is null");
    }

    String name = command.getShape();

    try {
      for (Shape s : allShapes) {
        if (s.getShapeName().equals(name)) {
          s.addCommand(command);
          s.checkGaps();
          //adding the command to the hashmap of commands
          if (commands.containsKey(name)) {
            commands.get(name).add(command);
          } else {
            List<ISimpleCommands> newList = new ArrayList<>();
            newList.add(command);
            commands.put(name, newList);
          }
        }
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("command exists in the shape");
    }
  }

  @Override
  public void addShape(Shape s) throws IllegalArgumentException,
      IllegalStateException {
    if (s == null) {
      throwIllArg("Shape is null");
    }

    if (s.getShapeType().getType().equals("rectangle")) {
      s = new Rectangle(s.getShapeName());
    } else if (s.getShapeType().getType().equals("ellipse")) {
      s = new Ellipse(s.getShapeName());
    } else if (s.getShapeType().getType().equals("plus")) {
      s = new Ellipse(s.getShapeName());
    }

    for (Shape shape : allShapes) {
      if (s.equals(shape) || s.getShapeName().equals(shape.getShapeName())) {
        throwIllArg("Shape already exists");
      }
    }
    allShapes.add(s);
    if (!commands.containsKey(s.getShapeName())) {
      commands.put(s.getShapeName(), new ArrayList<>());
    } else {
      for (ISimpleCommands command : commands.get(s.getShapeName())) {
        s.addCommand((ICommands) command);
        s.checkGaps();
      }
    }

  }

  @Override
  public void removeShape(cs3500.animator.model.Shape s)
      throws IllegalStateException, IllegalArgumentException {

    if (s == null) {
      throwIllArg("Shape can not be null");
    } else if (allShapes.contains(s)) {
      allShapes.remove(s);
    } else if (commands.containsKey(s.getShapeName())) {
      commands.remove(s.getShapeName());
    } else {
      throwIllArg("Shape does not exist in this animation");
    }
  }

  @Override
  public void removeCommand(ICommands command)
      throws IllegalStateException, IllegalArgumentException {
    if (command == null) {
      throwIllArg("command is null");
    }
    if (!checkAllValues(command)) {
      throwIllArg("command does not exist for this shape");
    }
    if (commands.containsKey(command.getShape())) {
      for (cs3500.animator.model.Shape s : allShapes) {
        if (s.getShapeName().equals(command.getShape())) {
          s.removeCommand(command);
        }
      }
    } else {
      throwIllArg("command does not exist");
    }
  }

  /**
   * checks if the command to be removed exists in the values of the shape it changes and removes it
   * if possible.
   *
   * @param commandToRemove the command to be removed in the form of an arraylist
   * @return true if the command has been removed, false if otherwise.
   */
  private boolean checkAllValues(ICommands commandToRemove) {
    boolean out = false;
    if (commands.containsKey(commandToRemove.getShape())) {
      for (ISimpleCommands existingCommand : commands.get(commandToRemove.getShape())) {
        if (existingCommand != null) {
          if (existingCommand.getShape().equals(commandToRemove.getShape())) {
            if (commandToRemove.equals(existingCommand)) {
              commands.remove(commandToRemove.getShape(), existingCommand);
              out = true;
            }
          }
        }
      }
    }
    return out;
  }

  @Override
  public List<Shape> getShapes() throws IllegalStateException {
    List<Shape> out = new ArrayList<>();
    for (Shape x : allShapes) {
      Shape addMe = new BasicShape(x.getShapeName(), x.getShapeType());
      for (ICommands com : x.getCommands()) {
        addMe.addCommand(com);
      }
      out.add(addMe);
    }
    return out;
  }

  @Override
  public SimpleBackground getBackground() {
    return new SimpleBackground(this.b.getHeight(), this.b.getWidth(), this.b.getXBound(),
        this.b.getYBound());
  }

  @Override
  public int getLastTick() throws IllegalStateException {
    int lastTick = 0;
    for (Shape s : allShapes) {
      if (s.getVeryLastTick() > lastTick) {
        lastTick = s.getVeryLastTick();
      }
    }
    return lastTick;
  }

  @Override
  public boolean isStarted() {
    return this.started;
  }

  @Override
  public List<ISimpleCommands> getCommands() {
    List<ISimpleCommands> out = new ArrayList<>();

    for (List<ISimpleCommands> c : this.commands.values()) {
      out.addAll(c);
    }
    return out;
  }
}

