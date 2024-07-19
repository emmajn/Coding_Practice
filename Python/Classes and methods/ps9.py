# 6.00 Problem Set 9
#
# Name:
# Collaborators:
# Time:

from string import *

SHAPE_FILENAME = "shapes.txt"

class Shape(object):
    def area(self):
        raise AttributeException("Subclasses should override this method.")

class Square(Shape):
    def __init__(self, h):
        """
        h: length of side of the square
        """
        self.side = float(h)
    def area(self):
        """
        Returns area of the square
        """
        return self.side**2
    def __str__(self):
        return 'Square with side ' + str(self.side)
    def __eq__(self, other):
        """
        Two squares are equal if they have the same dimension.
        other: object to check for equality
        """
        return type(other) == Square and self.side == other.side

class Circle(Shape):
    def __init__(self, radius):
        """
        radius: radius of the circle
        """
        self.radius = float(radius)
    def area(self):
        """
        Returns approximate area of the circle
        """
        return 3.14159*(self.radius**2)
    def __str__(self):
        return 'Circle with radius ' + str(self.radius)
    def __eq__(self, other):
        """
        Two circles are equal if they have the same radius.
        other: object to check for equality
        """
        return type(other) == Circle and self.radius == other.radius

#
# Problem 1: Create the Triangle class
#

class Triangle(Shape):
    def __init__(self, h, b):
        """
        h: Height of Triangle
        b: Base of Triangle
        """
        self.height = float(h)
        self.base = float(b)
    def area(self):
        """
        Returns area of the square
        """
        return self.height*self.base/2
    def __str__(self):
        return 'Triangle with Base ' + str(self.base) + ' and height ' + str(self.height)
    def __eq__(self, other):
        """
        Two triangles are equal if they have the same dimension.
        other: object to check for equality
        """
        return type(other) == Triangle and self.base == other.base and self.height == other.height

#
# Problem 2: Create the ShapeSet class
#
## TO DO: Fill in the following code skeleton according to the
##    specifications.

class ShapeSet:
    def __init__(self):
        """
        Initialize any needed variables
        """
        self.shape_list = []
        self.index = 0
    def addShape(self, sh):
        """
        Add shape sh to the set; no two shapes in the set may be
        identical
        sh: shape to be added
        """
        if self.shape_list:
            for current_list in self.shape_list:
                if sh.__eq__(current_list) == 1:
                    return self.shape_list
        return self.shape_list.append(sh)
        
    def __iter__(self):
        """
        Return an iterator that allows you to iterate over the set of
        shapes, one shape at a time
        """
        self.index = 0
        return self

    def __next__(self):
        ''''Returns the next value from team object's lists '''
        if self.index < len(self.shape_list):
            self.index +=1
            return self.shape_list[self.index].__str__()
       # End of Iteration
        raise StopIteration
        
    def __str__(self):
        """
        Return the string representation for a set, which consists of
        the string representation of each shape, categorized by type
        (circles, then squares, then triangles)
        """
        strings = []
        for i in range(len(self.shape_list)):
            strings.append( self.shape_list[i].__str__())
        return '\n'.join(strings)  
#
# Problem 3: Find the largest shapes in a ShapeSet
#
def findLargest(shapes):
    """
    Returns a tuple containing the elements of ShapeSet with the
       largest area.
    shapes: ShapeSet
    """
    largest_shape_list = []
    largest_area = 0
    for shape in shapes.shape_list:
        print(shape, shape.area())
        if shape.area() > largest_area:
            largest_area = shape.area()
            largest_shape_list = [shape]
            
        elif shape.area() == largest_area:
            largest_shape_list.append(shape)
        else:
            pass
    return tuple(largest_shape_list)
       
            
#
# Problem 4: Read shapes from a file into a ShapeSet
#
def readShapesFromFile(filename):
    """
    Retrieves shape information from the given file.
    Creates and returns a ShapeSet with the shapes found.
    filename: string
    """
    inputFile = open(filename)
    Shape_set = ShapeSet()
    for line in inputFile:
        shape_info =  line.split(',')
        shape_type = shape_info[0]
        
        if shape_type == 'triangle':

            Shape_set.addShape(Triangle(shape_info[1],shape_info[2]))
        if shape_type == 'square':
            Shape_set.addShape(Square(shape_info[1]))
        if shape_type == 'circle':
            Shape_set.addShape(Circle(shape_info[1]))
    return Shape_set

##readShapesFromFile(filename)
##ss = ShapeSet()
##ss.addShape(Triangle(1.2,2.5))
##ss.addShape(Circle(4))
##ss.addShape(Square(3.6))
##ss.addShape(Triangle(1.6,6.4))
##ss.addShape(Circle(2.2))
##ss.addShape(Triangle(4,100))
##ss.addShape(Triangle(50,8))
##str(ss)
##iter(ss)
##largest = findLargest(ss)
ss = readShapesFromFile("shapes.txt")
print(ss)
