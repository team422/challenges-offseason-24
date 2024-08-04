package frc.robot.subsystems.intake.rollers;

import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class RollerIOSim implements RollerIO {
  // For instructions on how to implement this class, refer to the README.md file

  private DCMotorSim m_sim;
  // define more members here as necessary

  public RollerIOSim() {
    // TODO: Implement this constructor
  }

  @Override
  public void updateInputs(RollerInputs inputs) {
    m_sim.update(0.02);

    inputs.voltage = getVoltage();
    inputs.velocityRadPerSec = getVelocityRadPerSec();
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
}
