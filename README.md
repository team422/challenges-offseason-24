# Challenge 5

## Description
This challenge is a return to form from Challenge 2. Rather than implementing a subsystem, you will be making implementations of interfaces to interact with motors. But unlike Challenge 2, this time you will be making simulated motors instead of real ones. Also, there are two interfaces to implement.

This challenge is themed around our Intake subsystem for the 2024 season. The intake was a mechanism with rollers, attached to a pivot. The pivot could go to any angle, and the rollers could spin in either direction. Since these two parts of the Intake are so different, we decided to make two separate interfaces for them. The first interface is `PivotIO`, and the second is `RollerIO`.

## Challenge
The files you will be working with are in the `src/main/java/frc/robot/subsystems/intake` directory. The two files are called `PivotIOSim.java` and `RollerIOSim.java`, and these files are in the `pivot` and `rollers` folders, respectively.

## PivotIO
Notice that `PivotIOSim` implements `PivotIO`. `PivotIO.java` has already been implemented and should not be modified.

The `PivotIOSim` class will be modeling an arm, and will thus use the `SingleJointedArmSim` class from WPILib. This has already been declared for you.

IMPORTANT: The Pivot uses a NEO motor (not a Neo550), so you will need to use a NEO motor for the `SingleJointedArmSim` object.

### Methods
You will need to implement the following methods in `PivotIOSim`:
- `PivotIOSim()` - This is the constructor for the `PivotIOSim` class. It should instantiate the `SingleJointedArmSim` object. Many of the parameters for the `SingleJointedArmSim` object can be found in `Constants.java`, in the `Constants.IntakeConstants` class. The only ones that are not present are the initial angle of the arm and whether to simulate gravity. The initial angle should be 120 degrees (must be converted to radians for `SingleJointedArmSim`), and gravity should not be simulated.
- `void setVoltage(double voltage)` - This method should set the voltage of the simulated motor.
- `double getVoltage()` - This method should return the voltage of the simulated motor. The `SingleJointedArmSim` object does not have a method to get the voltage, so you will need to keep track of the voltage yourself.
- `double getVelocityRadPerSec()` - This method should return the velocity of the simulated motor in radians per second.
- `Rotation2d getAngle()` - This method should return the angle of the simulated motor as a Rotation2d object. Make sure you use the right units when creating the Rotation2d object.

### Member Variables
As mentioned previously, a `SingleJointedArmSim` object has been declared in the `PivotIOSim` class. You will need to use this object to simulate the arm. You may declare more member variables as needed. (Hint: You will need at least one)

## RollerIO
Notice that `RollerIOSim` implements `RollerIO`. `RollerIO.java` has already been implemented and should not be modified.

The `RollerIOSim` class will be modeling intake rollers, and does not have to be as complex as the `PivotIOSim` class, as the angle does not matter. You will need to use the `DCMotorSim` class from WPILib. Like before, this has already been declared for you.

IMPORTANT: The rollers use a Kraken X60 motor, so you will need to use one of these motors for the `DCMotorSim` object.

### Methods
You will need to implement the following methods in `RollerIOSim`:
- `RollerIOSim()` - This is the constructor for the `RollerIOSim` class. It should instantiate the `DCMotorSim` object. Since the `DCMotorSim` class is much simpler, there are only two physics constants, both of which can be found in `Constants.java`, in the `Constants.IntakeConstants` class.
- `void setVoltage(double voltage)` - This method should set the voltage of the simulated motor.
- `double getVoltage()` - This method should return the voltage of the simulated motor. The `DCMotorSim` object does not have a method to get the voltage, so you will need to keep track of the voltage yourself.
- `double getVelocityRadPerSec()` - This method should return the velocity of the simulated motor in radians per second.

### Member Variables
Like before, a `DCMotorSim` object has been declared in the `RollerIOSim` class. You will need to use this object to simulate the rollers. You may declare more member variables as needed. (Hint: Like before, you will need at least one)

## Additional Information
The `updateInputs()` method in the `PivotIOSim` and `RollerIOSim` classes has already been implemented for you. This method is called by the `Intake` class to update the simulated motors, and will call the methods that you make 50 times per second. You should not modify this method.

## Tips
- Make sure you are using the correct units and constants for the `SingleJointedArmSim` and `DCMotorSim` objects. The tests have been set up so that you must provide the correct physics constants.
- If you are unsure of how to use the `SingleJointedArmSim` or `DCMotorSim` classes, you can look at the WPILib documentation for more information. The documentation for the [`SingleJointedArmSim`](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/simulation/SingleJointedArmSim.html) and [`DCMotorSim`](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/simulation/DCMotorSim.html) classes can be found on the WPILib website.
- Both the `SingleJointedArmSim` and `DCMotorSim` classes take a `DCMotor` object as a parameter in their constructors. The `DCMotor` class has static methods to get a `DCMotor` object for a specific motor that you can use (e.g. `DCMotor.getFalcon500(1)`)
- As alluded to earlier, you will need to keep track of the voltage of the simulated motors yourself. This is because the `SingleJointedArmSim` and `DCMotorSim` classes do not have methods to get the voltage of the motor. The easiest way to do this is to declare a member variable to store the voltage, and update it in the `setVoltage()` method.
