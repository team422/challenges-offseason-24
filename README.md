# Challenge 2

## Description
This challenge is about interfacing with a TalonFX Motor. The `TalonFX` class can be used for both Krakens and Falcons, but for this challenge, we will be pretending that we are using it for a Kraken. The goal of this challenge is to write a class that will interface with the TalonFX motor.

Unlike the last challenge, the rest of the challenges will not try to trick you with the description. But you will still need to read the description carefully to understand what you need to do.

## Challenge
The file you will be working with is in the `src/main/java/frc/robot/subsystems/arm` directory. The file is called `ArmIOKraken.java`.

Notice that `ArmIOKraken` implements `ArmIO`. `ArmIO.java` and `Arm.java` have already been implemented and should not be modified.

### Methods
You will need to implement the following methods in `ArmIOKraken`:
- `ArmIOKraken(int port)` - This is the constructor for the `ArmIOKraken` class. It should initialize the TalonFX motor with the given port.
- `void setVoltage(double voltage)` - This method should set the voltage of the TalonFX motor to the given voltage.
- `double getVoltage()` - This method should return the voltage of the TalonFX motor.
- `double getVelocityRadiansPerSecond()` - This method should return the velocity of the TalonFX motor in radians per second. Remember that the `TalonFX` object will return the velocity in rotations per second, so you will need to convert the units.
- `Rotation2d getAngle()` - This method should return the current angle of the TalonFX motor, as a `Rotation2d` object. Make sure you are using the correct units when initializing the `Rotation2d` object.

## Additional Information
The `TalonFX` object has already been declared at the top of the file, but you will still need to instantiate it in the constructor.

There is a method, `Object getMotor()`, that has already been implemented. Do not modify this method. This method is used to help test your code and should return the `TalonFX` object.

## Tips
- Make sure you know what units you are using at all times. Remember that the `TalonFX` methods will tell you what unit they return.
- Be sure you are using `StatusSignals` correctly. You have to get the value from the `StatusSignal` and then convert it to the correct unit. You can't just return the `StatusSignal`.
- If you are confused about how the `TalonFX` class works, you can look at the documentation. The documentation can be found [here](https://api.ctr-electronics.com/phoenix6/release/java/com/ctre/phoenix6/hardware/TalonFX.html).
- Remember that you can always ask for help if you are stuck. I want you guys to finish these challenges, so don't be afraid to ask for help.
