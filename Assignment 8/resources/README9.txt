LEVEL1:
new interface added IPlusFill that extends IInteractiveView
new class PlusFillView that extends InteractiveView and implementsIPlusFill. Takes in an
instance of IPlusFillVisualJPanel which extends IVisualJpanel. The class (PlusFillVisualJPanel) can set a view to reference to be able to tell if shapes should be
drawn or not it also includes the option to draw a new shape (plus). The view adds a button to the existing interactive view and updates the
wording of the button to be fill if the animation is outined or outline if the animation is filled.
new Controller PlusFillController that extends Interactivecontroller and adds two nested
classes to deal with changing the animation to fill or outline mode or providing necessary information about the new button
new shape type enum to account for the addition of plus shape
new class plus that extends basic shape to initialize a plus shape

LEVEL2:
new class DiscreteView which extends Interactive animation view. Does not take in any extra arguments in the constructor or have any extra methods. The view adds a button to the existing interactive view for the user to select discrete viewing or not.
new Controller DiscreteController which extends interactive controller and adds two nested
classes to deal with changing the animation to discrete or normal playing mode or providing necessary information about the new button. Also contains a private boolean to tell whether the view should be in discrete or normal view.

LEVEL3:
new interface ISimpleCommands holds two methods for getting shapes name and getting the ticks it is being changed in.
new Interface ITempoCommands extends ISimpleCommands and holds one method, to get the tempo that is changing
updated interface ICommands extends ISimpleCommands and has all methods as previous except for getShape and getTicks()
updated animation reader to add new method for adding a tempo and returning the builder.
updated animation builder adds case for tempo in switch statement of parse file to be able to call addTempo which is a new method added that finds the start and end tick and the tempo change.
updated basic animator to include new method get commands which returns all commands and has a new switch statement in the builder to add a new shape "plus"
updated Animator to add new method getCommands to be able to see all commands relevant to the animation as opposed to those only associated to a shape.
new interface ISlowMo which extends IInteractiveView and holds one method, to get the correct tempo the animation should be running at at a given tick and takes into account the tempo change commands
new class SlowMoView which extends IInteractiveView and implements ISlowMoView. This view does not add any visual functionality to the existing interactive view