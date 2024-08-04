package frc.robot.subsystems.intake.pivot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class PivotIOSim implements PivotIO {
  // For instructions on how to implement this class, refer to the README.md file

  private SingleJointedArmSim m_sim;
  // define more members here as necessary

  public PivotIOSim() {
    // TODO: Implement this constructor
  }

  @Override
  public void updateInputs(PivotInputs inputs) {
    m_sim.update(0.02);

    inputs.voltage = getVoltage();
    inputs.velocityRadPerSec = getVelocityRadPerSec();
    inputs.angleRad = getAngle().getRadians();
  }

  @Override
  public void setVoltage(double voltage) {
    // TODO: Implement this method
  }

  @Override
  public double getVoltage() {
    // TODO: Implement this method
    return 0.0;
  }

  @Override
  public double getVelocityRadPerSec() {
    // TODO: Implement this method
    return 0.0;
  }

  @Override
  public Rotation2d getAngle() {
    // TODO: Implement this method
    return null;
  }
}
