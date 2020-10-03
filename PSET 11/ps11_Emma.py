# Problem Set 11: Simulating robots
# Name:
# Collaborators:
# Time:

import math
import random
import ps11_visualize
import numpy
import matplotlib.pyplot as plt

# === Provided classes

class Position(object):
    """
    A Position represents a location in a two-dimensional room.
    """
    def __init__(self, x, y):
        """
        Initializes a position with coordinates (x, y).

        x: a real number indicating the x-coordinate
        y: a real number indicating the y-coordinate
        """
        self.x = x
        self.y = y
    def getX(self):
        return self.x
    def getY(self):
        return self.y
    def getNewPosition(self, angle, speed):
        """
        Computes and returns the new Position after a single clock-tick has
        passed, with this object as the current position, and with the
        specified angle and speed.

        Does NOT test whether the returned position fits inside the room.

        angle: integer representing angle in degrees, 0 <= angle < 360
        speed: positive float representing speed

        Returns: a Position object representing the new position.
        """
        old_x, old_y = self.getX(), self.getY()
        # Compute the change in position
        delta_y = speed * math.cos(math.radians(angle))
        delta_x = speed * math.sin(math.radians(angle))
        # Add that to the existing position
        new_x = old_x + delta_x
        new_y = old_y + delta_y
        return Position(new_x, new_y)


# === Problems 1 and 2

class RectangularRoom(object):
    """
    A RectangularRoom represents a rectangular region containing clean or dirty
    tiles.

    A room has a width and a height and contains (width * height) tiles. At any
    particular time, each of these tiles is either clean or dirty.
    """
    def __init__(self, width, height):
        """
        Initializes a rectangular room with the specified width and height.
        Initially, no tiles in the room have been cleaned.

        width: an integer > 0
        height: an integer > 0
        """
        self.width = width
        self.height = height
        self.clean_dict = {}
    def cleanTileAtPosition(self, pos):
        """
        Mark the tile under the position POS as cleaned.
        Assumes that POS represents a valid position inside this room.

        pos: a Position
        True: Space Cleaned
        False: Space Durty
        """

        self.clean_dict[(math.floor(pos.x), math.floor(pos.y))] = True
        
        
    def isTileCleaned(self, m, n):
        """
        Return True if the tile (m, n) has been cleaned.

        Assumes that (m, n) represents a valid tile inside the room.

        m: an integer
        n: an integer
        returns: True if (m, n) is cleaned, False otherwise
        """
        
        return self.clean_dict.get((m, n),False)
        
    def getNumTiles(self):
        """
        Return the total number of tiles in the room.

        returns: an integer
        """
        return self.width * self.height
    def getNumCleanedTiles(self):
        """
        Return the total number of clean tiles in the room.

        returns: an integer
        """
        return len(self.clean_dict)
    def getRandomPosition(self):
        """
        Return a random position inside the room.

        returns: a Position object.
        """
        x = random.randrange(self.width)
        y = random.randrange(self.height)
        return Position(x,y)
    def isPositionInRoom(self, pos):
        """
        Return True if POS is inside the room.

        pos: a Position object.
        returns: True if POS is in the room, False otherwise.
        """
        if 0 <= pos.x < self.width and 0 <= pos.y < self.height:
            return True
        return False 


class BaseRobot(object):
    """
    Represents a robot cleaning a particular room.

    At all times the robot has a particular position and direction in
    the room.  The robot also has a fixed speed.

    Subclasses of BaseRobot should provide movement strategies by
    implementing updatePositionAndClean(), which simulates a single
    time-step.
    """
    def __init__(self, room, speed):
        """
        Initializes a Robot with the given speed in the specified
        room. The robot initially has a random direction d and a
        random position p in the room.

        The direction d is an integer satisfying 0 <= d < 360; it
        specifies an angle in degrees.

        p is a Position object giving the robot's position.

        room:  a RectangularRoom object.
        speed: a float (speed > 0)
        """
        x = random.randrange(room.width)
        y = random.randrange(room.height)
        
        self.s = speed
        self.p = Position(x, y)
        self.d = random.randrange(0,360)
        self.room = room
        
    def getRobotPosition(self):
        """
        Return the position of the robot.

        returns: a Position object giving the robot's position.
        """
        return self.p
        
    def getRobotDirection(self):
        """
        Return the direction of the robot.

        returns: an integer d giving the direction of the robot as an angle in
        degrees, 0 <= d < 360.
        """
        return self.d
    def setRobotPosition(self, position):
        """
        Set the position of the robot to POSITION.

        position: a Position object.
        """
        self.p = position
        
    def setRobotDirection(self, direction):
        """
        Set the direction of the robot to DIRECTION.

        direction: integer representing an angle in degrees
        """
        self.d = direction


class Robot(BaseRobot):
    """
    A Robot is a BaseRobot with the standard movement strategy.

    At each time-step, a Robot attempts to move in its current
    direction; when it hits a wall, it chooses a new direction
    randomly.
    """
    def updatePositionAndClean(self):
        """
        Simulate the passage of a single time-step.

        Move the robot to a new position and mark the tile it is on as having
        been cleaned.
        """
        if self.room.isPositionInRoom(self.p.getNewPosition(self.d, self.s)):
            self.p = self.p.getNewPosition(self.d, self.s)
            self.room.cleanTileAtPosition(self.p)
        else:
            while not self.room.isPositionInRoom(self.p.getNewPosition(self.d, self.s)):
                direction = random.randrange(0,360)
                self.setRobotDirection(direction)
            self.p = self.p.getNewPosition(self.d, self.s)
            self.room.cleanTileAtPosition(self.p)
        return self.p


# === Problem 3

def runSimulation(num_robots, speed, width, height, min_coverage, num_trials,
                  robot_type, visualize):
    """
    Runs NUM_TRIALS trials of the simulation and returns a list of
    lists, one per trial. The list for a trial has an element for each
    timestep of that trial, the value of which is the percentage of
    the room that is clean after that timestep. Each trial stops when
    MIN_COVERAGE of the room is clean.

    The simulation is run with NUM_ROBOTS robots of type ROBOT_TYPE,
    each with speed SPEED, in a room of dimensions WIDTH x HEIGHT.

    Visualization is turned on when boolean VISUALIZE is set to True.

    num_robots: an int (num_robots > 0)
    speed: a float (speed > 0)
    width: an int (width > 0)
    height: an int (height > 0)
    min_coverage: a float (0 <= min_coverage <= 1.0)
    num_trials: an int (num_trials > 0)
    robot_type: class of robot to be instantiated (e.g. Robot or
                RandomWalkRobot)
    visualize: a boolean (True to turn on visualization)
    """
    trial_list = []
    for trial in range(1, num_trials + 1):
        percentage_cleaned = 0
        room = RectangularRoom(width, height)
        robots = []
        time_step_list = [(0,0)]
        time_step = 0 
        for robot_id in range(0,num_robots):
            # Initialize robots           
            robots.append(robot_type(room, speed))
            robots[robot_id].pos = room.getRandomPosition()
        if visualize:
            anim = ps11_visualize.RobotVisualization(num_robots, width, height) 
        while percentage_cleaned < min_coverage:
            time_step = time_step + 1
            for robot_id in range(0,num_robots):
                robots[robot_id].updatePositionAndClean()
            percentage_cleaned = room.getNumCleanedTiles()/room.getNumTiles()
            time_step_list.append((time_step, percentage_cleaned))
            if visualize:
                anim.update(room, robots)
        trial_list.append(time_step_list)
        
        if visualize:
            anim.done()          
    return trial_list
            
avg = runSimulation(10, 1.0, 15, 20, 0.8, 10, Robot, False)
##anim = ps11_visualize.RobotVisualization(num_robots, width, height, 1) 

# === Provided function
def computeMeans(list_of_lists):
    """
    Returns a list as long as the longest list in LIST_OF_LISTS, where
    the value at index i is the average of the values at index i in
    all of LIST_OF_LISTS' lists.

    Lists shorter than the longest list are padded with their final
    value to be the same length.
    """
    # Find length of longest list
    longest = 0
    for lst in list_of_lists:
        if len(lst) > longest:
           longest = len(lst)
    # Get totals
    tots = [0]*(longest)
    for lst in list_of_lists:
        for i in range(longest):
            if i < len(lst):
                tots[i] += lst[i]
            else:
                tots[i] += lst[-1]
    # Convert tots to an array to make averaging across each index easier
    tots = pylab.array(tots)
    # Compute means
    means = tots/float(len(list_of_lists))
    return means


# === Problem 4
def showPlot1():
    """
    Produces a plot showing dependence of cleaning time on room size.
    """
    room_size = [5, 10, 15, 20, 25]
    cleaning_time_list = []
    for room_dim  in room_size: 
        avg = runSimulation(1, 1.0, room_dim, room_dim, 0.75, 10, Robot, False)
        time_step_list = []
        for trials in range(10):
            time_step_list.append(len(avg[trials]))
        cleaning_time_list.append(numpy.mean(time_step_list))
    plt.plot(room_size, cleaning_time_list)
    # naming the x axis 
    plt.xlabel('Room Size') 
    # naming the y axis 
    plt.ylabel('Time to Clean') 
      
    # giving a title to my graph 
    plt.title('Time to Clean as a Function of Room Size')
    plt.show() 
##showPlot1()    

def showPlot2():
    """
    Produces a plot showing dependence of cleaning time on number of robots.
    """
    num_robots = range(1,11)
    cleaning_time_list = []
    for robot_id  in num_robots: 
        avg = runSimulation(robot_id, 1.0, 25, 25, 0.75, 10, Robot, False)
        time_step_list = []
        for trials in range(10):
            time_step_list.append(len(avg[trials]))
        cleaning_time_list.append(numpy.mean(time_step_list))
    plt.plot(num_robots, cleaning_time_list)
    # naming the x axis 
    plt.xlabel('Number of Robots') 
    # naming the y axis 
    plt.ylabel('Time to Clean') 
      
    # giving a title to my graph 
    plt.title('Time to Clean as a Function of Room Size')
    plt.show()
##showPlot2()

def showPlot3():
    """
    Produces a plot showing dependence of cleaning time on room shape.
    """
    room_size = [(20, 20), (25, 16), (40, 10), (50, 8), (80, 5), (100,4)]
    cleaning_time_list = []
    for room_dim  in room_size: 
        avg = runSimulation(1, 1.0, room_dim[0], room_dim[1], 0.75, 10, Robot, False)
        time_step_list = []
        for trials in range(10):
            time_step_list.append(len(avg[trials]))
        cleaning_time_list.append(numpy.mean(time_step_list))
    plt.plot(range(len(room_size)), cleaning_time_list)
    # naming the x axis 
    plt.xlabel('Room Shape') 
    # naming the y axis 
    plt.ylabel('Time to Clean') 
      
    # giving a title to my graph 
    plt.title('Time to Clean as a Function of Room Size')
    plt.show()
##showPlot3()    

def showPlot4():
    """
    Produces a plot showing cleaning time vs. percentage cleaned, for
    each of 1-5 robots.
    """
    coverages = [0.25, 0.5, 0.75, 1.0]
    cleaning_time_list = []
    for coverage  in coverages: 
        avg = runSimulation(1, 1.0, 25, 25, coverage, 10, Robot, False)
        time_step_list = []
        for trials in range(10):
            time_step_list.append(len(avg[trials]))
        cleaning_time_list.append(numpy.mean(time_step_list))
    plt.plot(range(len(coverages)), cleaning_time_list)
    # naming the x axis 
    plt.xlabel('Room Coverage') 
    # naming the y axis 
    plt.ylabel('Time to Clean') 
      
    # giving a title to my graph 
    plt.title('Time to Clean as a Function of Room Size')
    plt.show()

##showPlot4()

# === Problem 5

class RandomWalkRobot(BaseRobot):
    """
    A RandomWalkRobot is a robot with the "random walk" movement
    strategy: it chooses a new direction at random after each
    time-step.
    """
    def updatePositionAndClean(self):
        """
        Simulate the passage of a single time-step.

        Move the robot to a new position and mark the tile it is on as having
        been cleaned.
        """
        direction = random.randrange(0,360)
        self.setRobotDirection(direction)
        if self.room.isPositionInRoom(self.p.getNewPosition(self.d, self.s)):
            self.p = self.p.getNewPosition(self.d, self.s)
            self.room.cleanTileAtPosition(self.p)
        else:
            while not self.room.isPositionInRoom(self.p.getNewPosition(self.d, self.s)):
                direction = random.randrange(0,360)
                self.setRobotDirection(direction)
            self.p = self.p.getNewPosition(self.d, self.s)
            self.room.cleanTileAtPosition(self.p)
        return self.p
avg = runSimulation(10, 1.0, 15, 20, 0.8, 10, RandomWalkRobot, False)

# === Problem 6

def showPlot5():
    """
    Produces a plot comparing the two robot strategies.
    """
    room_size = [5, 10, 15, 20, 25]
    cleaning_time_list = []
    cleaning_time_list_rand = []
    for room_dim  in room_size: 
        avg = runSimulation(10, 1.0, room_dim, room_dim, 0.8, 10, Robot, False)
        avg_rand = runSimulation(10, 1.0, room_dim, room_dim, 0.8, 10, RandomWalkRobot, False)
        time_step_list = []
        time_step_list_rand = []
        for trials in range(10):
            time_step_list.append(len(avg[trials]))
            time_step_list_rand.append(len(avg_rand[trials]))
        cleaning_time_list.append(numpy.mean(time_step_list))
        cleaning_time_list_rand.append(numpy.mean(time_step_list_rand))
    plt.plot(room_size, cleaning_time_list, 'r--',label = 'Normal')
    plt.plot(room_size, cleaning_time_list_rand, 'bs', label = 'Random Walk')
    # naming the x axis 
    plt.xlabel('Room Size') 
    # naming the y axis 
    plt.ylabel('Time to Clean') 
      
    # giving a title to my graph 
    plt.title('Time to Clean as a Function of Room Size')
    plt.legend()
    plt.show()
    
showPlot5()
