IAnimator
this is the interface for the model. It is needed because it holds all public methods the model must implement in order to enforce the rules and constraints of any given animation.

BasicAnimator
this is the class that implements a basic model's rules and constraints.

Shape
this is the interface for a shape that would be in the animation. It holds all public methods like getters and setters.

BasicShape
This is the class that implements shape and holds all the information for a given basic shape including all the start values for the shape and all the end values for the shape along with when the change starts and when the change ends.

Color
represents a color with r, g, and b values to compose it. It holds getters and setters for a given color and can update the whole color

Position
represents a position on the cartesian plane with x and y values to compose it. It holds getters and setters for individual x and y values and can update the whole position.

TotalSize
represents the totalize of a given 2d object from top to bottom and left to right with height and width values to compose it. It holds getters and setters for individual height and width values and can also update the whole size of the object.

SimpleBackground
represents a simple version of a background that extends totalize but also has a shape and color associated with it. It holds the getters and setters from totalize along with the getters and setters specific to the background.

SimpleAnimatorView
represents the view of the animation. Currently, holds a toString method that allows for all moves to be visualized in a chart format for each shape. Receives this information from the model.