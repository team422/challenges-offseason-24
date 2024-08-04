package frc.robot.subsystems.flywheel;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheel extends SubsystemBase {
  private FlywheelIO m_io;
  public final FlywheelInputsAutoLogged m_inputs;
  private PIDController m_controller;

  public Flywheel(FlywheelIO io, PIDController controller) {
    m_io = io;
    m_inputs = new FlywheelInputsAutoLogged();
    m_controller = controller;
  }

  @Override
  public void periodic() {
    m_io.updateInputs(m_inputs);
    m_io.setFlywheelVoltage(m_controller.calculate(m_inputs.curVelocity));
  }

  /**
   * Set the desired velocity of the flywheel in RPM
   *
   * @param velocity The desired velocity in RPM
   */
  public void setDesiredVelocity(double velocity) {
    m_controller.setSetpoint(velocity);
  }
}
