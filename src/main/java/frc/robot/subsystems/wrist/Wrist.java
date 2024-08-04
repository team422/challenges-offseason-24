package frc.robot.subsystems.wrist;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wrist extends SubsystemBase {
  // For instructions on how to implement this class, refer to the README.md file

  public Wrist(WristIO io, PIDController controller) {
    // TODO: Implement the constructor
  }

  @Override
  public void periodic() {
    // TODO: Implement this method
  }

  public void setDesiredAngle(Rotation2d angle) {
    // TODO: Implement this method
  }

  public Command setDesiredAngleCommand(Rotation2d angle) {
    // TODO: Implement this method
    return null;
  }

  public boolean withinTolerance() {
    // TODO: Implement this method
    return false;
  }

  public WristInputsAutoLogged getInputs() {
    // TODO: Implement this method
    return null;
  }
}
