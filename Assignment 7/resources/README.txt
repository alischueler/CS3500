IAnimator:
 this is the interface for the model. It is needed because it holds all public methods
the model must implement in order to enforce the rules and constraints of any given animation.
CHANGE ASSIGNMENT 6: we added a methods for removing commands and shapes and adding commands and shapes and a
method for getting the last tick of the animation which is important for the views.
DESIGN CHOICE ASSIGNMENT 7: we chose not to have this interface extend the view model to avoid any
accidental slip ups where this model could be used instead of viewmodel and thus granting a view mutable
access the the model which is the whole reason for creating a viewmodel.

BasicAnimator:
this is the class that implements a basic model's rules and constraints. We represent the commands
as a hashmap of every shape and their associated commands (list<ICommands>) and keep up with the
current shapes being represented and their states through a List of shape.
CHANGE ASSIGNMENT 6: we now represent commands as their own class Commands to store and access them more efficiently,
all information about the shapes are now stored in the shapes and commands are checked and
initialized in the shapes, we have a builder that reads a file and adds shapes, canvas, and commands
 to the model, constructor is not private since the builder was added

Shape:
this is the interface for a shape that would be in the animation. It holds all public
methods like getters and setters, and checking if commands are correct / should be added/filling
in gaps of commands
CHANGE ASSIGNMENT 6: we added methods to check commands to the shape, this would make our design easier to extend
 to other objects or shapes

BasicShape:
This is the super class that implements shape and holds all the information for a given basic
shape including all the commands, the current command of the shape, and all its current values.
CHANGE ASSIGNMENT 6: we now store commands in the shapes and all the current information about them which makes
it easier to add shapes to our animation

Rectangle:
This is a subclass that extends BasicShape and holds only a constructor to contract that type of
shape and pass the information along to super
CHANGE: this was added in so that it will be easier to abstract shapes if we need to add more

Ellipse:
This is a subclass that extends BasicShape and holds only a constructor to contract that type of
shape and pass the information along to super
CHANGE: this was added in so that it will be easier to abstract shapes if we need to add more

ShapeType:
This is an enum that holds the possible shape types the animation will hold and their corresponding
string values
CHANGE ASSIGNMENT 6: this was added in so that it will be easier to add a shape type to the enum if we need to
add more

Color:
represents a color with r, g, and b values to compose it. It holds getters and setters
for a given color and can update the whole color

Position:
represents a position on the cartesian plane with x and y values to compose it. It holds
getters and setters for individual x and y values and can update the whole position.

TotalSize:
represents the totalize of a given 2d object from top to bottom and left to right with
height and width values to compose it. It holds getters and setters for individual height and
width values and can also update the whole size of the object.

SimpleBackground:
represents a simple version of a background, it holds the getters for the bound, abound, height,
and width
CHANGE ASSIGNMENT 6: removed all setters and color information, now only have getters because the background
stays white, only its sounds and dimensions can vary from animation to animation.

SimpleAnimatorView:
represents the view of the animation. Currently, holds a toString method that allows for
all moves to be visualized in a chart format for each shape. Receives this information from the model.
CHANGE ASSIGNMENT 6: change how the method receives information from the shape because updated how commands are stored

IAnimatorView:
represents the public interface that has methods to render the animation to a given spendable,
draw it on a jpanel, get or set the tempo
CHANGE ASSIGNMENT 7: now only contains a method for rendering a text output to an appendable or set loop.

IVisualView:
represents the interface that has methods to renderanimate for visual views along with getting the tempo
of the view. extends IAnimator view.

IInteractiveView:
represents the interface that has methods to add listeners to the interactive view, set the tempo,
clear the text box of the side panel, get the tempo the client wanted to change the animation to, update
the tempo displayed on the view, and get if the animation loops or not. extends IVisualView


SVGAnimationView:
A view for automatically turing the passed model into a .svg file. this file can be used to run
animations n web browsers. Call render to get the svg code. Its outputted as a single line of text.
CHANGE ASSIGNMENT 7: now takes in an IViewModel instead of an IAnimator.

VisualAnimationView:
represents the visual animation, frame by frame with a scrollable and resizable panel to be able to
see all portions of the animation despite the size of your screen
CHANGE ASSIGNMENT 7: render now throws an UnsupportedOperationException because it does not write
anything to a file. Also changed that the view never loops and the tempo is not able to be changed to
give these features to the interactive view. also now takes in an IViewModel instead of an IAnimator.

IVisualJPanel:
the interface that holds the method an instance of this interface must contain to paint and repaint
a visual.

VisualJPanel:
an instance of IVisualJPanel. represents the Panel aspect of the visual view, colors what shapes should be drawn at every tick using
repaint and paint component. Adjusts for the x and y bounds of the background

InteractiveAnimationView:
We choose not to extend the VisualAnimation view and use the VisualJPanel class as a componant because
this class only needs the JPanel to add to a new Frame with an interactive side bar portion and a menu
for the user to be able to see what each interactive buttons purpose is. The assignment
started that we needed to reuse the JPanel class but not our JFrame class which is why we used composition
instead of inheritance. takes in a VisualJPanel, IViewModel, and a tempo. Sets up all buttons to have
action listeners, does not allow the tempo to be changed to something less than 1.

ViewCreator:
Creates a view using the enum it was given as well as the model and tempo. Will create either
textual, svg, visual, or interactive
CHANGE ASSIGNMENT 7: we added a new enum interactive and added it to the switch statement as well to
 account for the new interactive view for this assignment.

Controller Creator:
creates a controller using the view enum it was given as well as a model. Will create either controller, visual controller, or interactive controller

IController:
public interface for starting the animation and allowing the client to visualize it

Controller:
Starts the animation and appends it to the given output if svg or textual. displays as a jframe and
 jpanel based on the timer created
CHANGE ASSIGNMENT 7: The initialization of the timer is now done in a helper method called in runAnimate. This will
allow the timer to be updated easier when the tempo is changed. We also changed the start tick to be 1
because all of the animations start at tick 1, not at tick 0. Controller now only represents the textual
views (only vies that are instance of only IAnimatorViews) and checks this through a private checkInstance
method called in the constructor.

VisualController:
A controller to deal with visual animations, it is an extension of controller so the super constructor
call deals with checking that this controller represents views that are an instance of visualviews but not
interactive views. Along with the super constructor of the controller, the contstructor also casts the
view to a visual view to be able to access all visual view methods.
The public startanimate method starts the animation and also initializes and starts the timer for the
animation to appear. After the animation has run through all commands once, the last frame is displayed
indefinitely.
does not care if appendable is null because it is not apending anything to it.

InteractiveController:
a controller to deal with interactive animations, it is an extension of visualcontroller so the super
constructor call deals with checking that this controller represents views that are instances of
IInteractiveView. Along with the super constructor of the controller, the contstructor also casts the
view to an interactive view to be able to access all interactive view methods. This controller's timer
also sees if the view was set to loop or not in the timer, if it is then the animation will run indefinitely.
If it was not set to loop then it will go back to the first frame and stay there indefinitely.
Contains nested classes that each extend runnable and have only one "run()" method for each of the buttons
that have listeners.
does not care if appendable is null because it is not apending anything to it.

IButtonListener:
An interface which extends a ActionListener and adds one method called setButtonClickedActionMap. This method allows the client to set a map for button pressed events. 

ButtonListener:
Inherits IButtonListener. This class is meant to be given a map of string and buttons. The string represents the name of the button and the Runner represents the action to be performed when the button is clicked.

BSTAnimation:
This animation makes a Triangle of circles. These circles all start along a horizontal point at the start. They then grow and move outward forming a triangle. The colors also change with the depth of the tree.

BubbleSort:
Sorts an animation using bubble sort. The bar highlighted in red is the first bar one being compared. once the animation is over all the bars turn green.

ICreate:
A interface for creating animations. Has one method which is the epicenter for starting animations. call this method and the animation will be written to the given spendable.

IViewModel:
contains only methods that do not change the the animations (no add or remove methods)

ShapeViewModel:
an instance of IViewModel for shapes. a true read only version of the basic animator model for the vies to interact with to avoid accidental changes to the
model that would in turn change the correct output. returns deep copies of any method that returns
anything that can be changed accidentally.

ITWeener:
A interface that defines the actions of the tweener classes. these classes move the shapes in a
animation to a specific tick. That tick is given to the tweener in the get state at method.

Tweener:
Moves the given models shapes to the correct location at the given tick. call get state at repeatedly to make an animation.

Excellence:
reads the command line arguments and creates the correct animation from it.
CHANGE ASSIGNMENT 7: the controller is now created by a factor method depending on what type of view
was created.

MakeAutoGeneratedAnimation:
makes a bts or bubble sort animation depending on the command line string.
The format of the string is:
  {bubble or bst} {number of bars/nodes} {output file}
  Ex: bubble 10 out.txt


