package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheel extends SubsystemBase {
  // DO NOT MODIFY THIS FILE

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

    double pidVoltage = m_controller.calculate(m_inputs.velocity);
    m_io.setVoltage(pidVoltage);
  }

  public void setDesiredVelocity(double velocity) {
    m_controller.setSetpoint(velocity);
  }

  public Command setDesiredVelocityCommand(double velocity) {
    return Commands.runOnce(() -> setDesiredVelocity(velocity));
  }

  public boolean atSetpoint() {
    return m_controller.atSetpoint();
  }

  public double getSetpoint() {
    return m_controller.getSetpoint();
  }
}
