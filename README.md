# Challenge 3

## Description
This challenge is about creating an Intake subsystem. On this hypothetical robot, the intake subsystem does not move, and is comprised of just a motor attached to a wheel. The goal of this challenge is to finish the subsystem by implementing the necessary methods and member variables.

`IntakeIO` has already been implemented, so you only need to use its methods to finish the Intake subsystem.

## Challenge
The file you will be working with is in the `src/main/java/frc/robot/subsystems/intake` directory. The file is called `Intake.java`.

### Methods
You must implement the following methods for the Intake subsystem:
- `Intake(IntakeIO io)` - This is the constructor for the Intake subsystem. It takes in an `IntakeIO` object as a parameter and should initialize the `IntakeIO` and `IntakeInputsAutoLogged` member variables.
- `void periodic()` - This method will be called periodically. You should use this method to update the inputs to the `IntakeIO` object.
- `void setRollerVoltage(double voltage)` - This method should set the voltage of the roller motor. The voltage will be between -12 and 12 volts.
- `Command setVoltageCommand(double voltage)` - This method should return a `Command` that sets the voltage of the roller motor on initialization. The voltage will be between -12 and 12 volts.
- `IntakeInputsAutoLogged getInputs()` - This method should return the `IntakeInputsAutoLogged` object that keeps track of inputs to the intake subsystem.

### Member Variables
Also, you must define the following member variables:
- `IntakeIO m_io` - This is the `IntakeIO` object that the Intake subsystem will use to interact with the hardware.
- `IntakeInputsAutoLogged m_inputs` - This is an `IntakeInputsAutoLogged` object that will be used to keep track of motor inputs.

You may name the variables whatever you want, but the type of the variables must be the same as the type specified above. The names above are suggested names according to our standard.

## Additional Information
The `IntakeIO` class has already been implemented and has the following methods. You will need to use at least two of these methods in the Intake subsystem:
- `void updateInputs(IntakeInputs inputs)` - This method updates the inputs to the IntakeIO object.
- `void setRollerVoltage(double voltage)` - This method sets the voltage of the roller motor. The voltage will be between -12 and 12 volts.
- `double getRollerVoltage()` - This method returns the voltage of the roller motor.
- `double getRollerVelocityRPM()` - This method returns the velocity of the roller motor in RPM.

## Tips
- Remember to use the `updateInputs` method in the periodic method to update the inputs to the `IntakeIO` object. If you do not, the simulated motor will not move.
- If you are unsure of how `Command` works and how to use it, the `Commands` class will be helpful. The documentation can be found [here](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj2/command/Commands.html).
- If you are unsure of how to use the `IntakeInputsAutoLogged` class, you can look at [this example](https://github.com/team422/frc-24/blob/threadedDrive/src/main/java/frc/robot/subsystems/shooter/Shooter.java#L109) from our FRC-24 codebase.
