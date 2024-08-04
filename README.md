# Challenge 4

## Description
This challenge is about creating a Wrist subsystem. Unlike the Intake from the last challenge, the Wrist subsystem will need to move an arm to an angle, and will thus involve PID. The Wrist subsystem will be given desired angles one at a time and will have to move the motor to that angle using PID. The goal of this challenge is to finish the subsystem by implementing the necessary methods and member variables, to eventually be able to move to a desired angle within 3 seconds.

`WristIO` has already been implemented, so you only need to use its methods to finish the Wrist subsystem.

This challenge is a bit more difficult than the previous ones, so be sure to read the instructions carefully. I believe in you!

## Challenge
The file you will be working with is in the `src/main/java/frc/robot/subsystems/wrist` directory. The file is called `Wrist.java`.

### Methods
You must implement the following methods for the Wrist subsystem:
- `Wrist(WristIO io, PIDController controller)` - This is the constructor for the Wrist subsystem. It takes in a `WristIO` object and a `PIDController` object. You should store these objects in member variables. The controller will already have its kP, kI, and kD values set, so you do not need to worry about that. The controller will also have a tolerance of 3 degrees.
- `void periodic()` - This method will be called periodically. You should use this method to update the inputs to the `WristIO` object and to calculate the output of the PID Controller. When calculating the output, you should put the current angle and desired angle as the inputs to the controller, both in **DEGREES**. You should then set the voltage of the motor to the output of the controller.
- `void setDesiredAngle(Rotation2d angle)` - This method should set the new desired angle for the Wrist subsystem. The angle will be between -120 and 120 degrees.
- `Command setDesiredAngleCommand(Rotation2d angle)` - This method should return a `Command` that sets the desired angle for the Wrist subsystem on initialization. The angle will be between -120 and 120 degrees.
- `boolean withinTolerance()` - This method should return true if the current angle is within 3 degrees of the desired angle, and false otherwise. The PID Controller will have its tolerance set already, so use that to determine if the current angle is within the desired angle.
- `WristInputsAutoLogged getInputs()` - This method should return the `WristInputsAutoLogged` object that keeps track of inputs to the Wrist subsystem.

### Member Variables
You must define the following member variables:
- `WristIO m_io` - This is the `WristIO` object that the Wrist subsystem will use to interact with the hardware.
- `WristInputsAutoLogged m_inputs` - This is the `WristInputsAutoLogged` object that the Wrist subsystem will use to keep track of motor inputs.
- `PIDController m_controller` - This is the `PIDController` object that the Wrist subsystem will use to calculate the output voltage to the motor.

More member variables may be needed, depending on your implementation.

## Additional Information
The `WristIO` class has already been implemented and has the following methods. You may need to use at least two of these methods depending on your implementation of the Wrist subsystem:
- `void updateInputs(WristInputs inputs)` - This method updates the inputs to the `WristIO` object, and should be called 50 times per second.
- `void setVoltage(double voltage)` - This method sets the voltage of the motor to the given value.
- `Rotation2d getAngle()` - This method returns the current angle of the motor.

The desired angle should initially be set to 0 degrees until a new desired angle is set.

## Tips
- Make sure to use the updateInputs method in the periodic method to update the inputs to the WristIO object. If you don't do this, the simulated motor will not move.
- As mentioned before, the PID Controller has its tolerance set in **DEGREES**, so be sure you use degrees when calculating the output of the controller.
- The main thing that matters for this challenge is whether the Wrist subsystem can move to a desired angle within 3 seconds. If you do it right, the PID Controller should be able to do most of the work for you.
- Remember that you can check out `RobotContainer.java` to see how your Wrist subsystem will be instantiated and used.
