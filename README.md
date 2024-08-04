# Challenge 6

## Description
For the past couple of challenges, you have been implementing parts of a subsystem. This challenge is different. The Flywheel subsystem has already been implemented, and it is your job to set it up in `RobotContainer.java`. This will include instantiating the subsystem and setting up button bindings.

## Challenge
The file you will be working with is in the `src/main/java/frc/robot` directory. The file is called `RobotContainer.java`.

### Methods
You must implement the following methods in `RobotContainer.java`:
- `configureSubsystems()` - This method should instantiate the Flywheel subsystem. The Flywheel subsystem requires a `FlywheelIO` and `PIDController` object. You must instantiate new objects of both of these classes and pass them into the Flywheel subsystem constructor. Any constants that are needed for these objects are in the `Constants.FlywheelConstants` class. Use these constants when instantiating the objects.
- `configureButtonBindings()` - This method should set up the button bindings for the Flywheel subsystem. `DriverControls` has already been implemented and instantiated in `RobotContainer`. It has one method: `runFlywheel()`, which returns a `Trigger`. You must bind the `Trigger` to the Flywheel subsystem's `setDesiredVelocityCommand()` method, such that when the button is initially pressed, the `Command` object returned by `setDesiredVelocityCommand()` is scheduled with the desired velocity set to `Constants.FlywheelConstants.kVelocitySetpoint`. When the button is released, the `setDesiredVelocityCommand()` is scheduled with the desired velocity set to 0.0. Each of these commands should be run a single time when the button is pressed or released.

### Member Variables
You must define the following member variables:
- `Flywheel m_flywheel` - This is the Flywheel object that will be instantiated in the `configureSubsystems()` method.

You may name the member variables whatever you like, but the types must be correct.

## Additional Information
The Flywheel subsystem has already been implemented. You should NOT modify any files in the `src/main/java/frc/robot/subsystems directory`. You should only modify the `RobotContainer.java` file. There are checks in place to ensure that you do not modify the Flywheel subsystem, as all of the tests break if the Flywheel subsystem is modified.

The only method you will need to use is the `setDesiredVelocityCommand()` method. This method takes in a double as a parameter and returns a `Command` that sets the desired velocity of the Flywheel subsystem to the given value when initialized, using `Commands.runOnce()`.

## Tips
- Use the `Constants.FlywheelConstants` class to get the constants needed to instantiate the Flywheel subsystem.
- If you are confused on how to set up button bindings, you can look at the documentation for the `Trigger` class, or you can look at the `RobotContainer.java` file in any of the previous challenges, as they all have button bindings set up. The documentation for the `Trigger` class can be found [here](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj2/command/button/Trigger.html). If you still aren't sure about what `Trigger` methods to use, don't hesitate to ask for help.

## Addendum
This challenge is not super difficult, but it is easily the most technically complex so as far as the unit tests go. I haven't been talking too much about those before now, but the code in the unit tests has completely changed the way I see the Java language, for the worse. If you want to experience my pain, you can look at the `src/test/java/Challenge6.java` file. I have left some comments that try to explain what's going on, but it's still a mess. I had to make an entire new class just to make the buttonBindings one work.
