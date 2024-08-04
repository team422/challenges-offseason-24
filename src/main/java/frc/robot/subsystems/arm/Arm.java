package frc.robot.subsystems.arm;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  private ArmIO m_io;

  private PIDController m_controller;
  private double m_setpointRad;

  public Arm(ArmIO io, PIDController controller) {
    m_io = io;
    m_controller = controller;
  }

  @Override
  public void periodic() {
    double curAngleRad = m_io.getPosition().getRadians();
    double output = m_controller.calculate(curAngleRad, m_setpointRad);
    m_io.setVoltage(output);
  }

  public void setSetpoint(Rotation2d setpoint) {
    m_setpointRad = setpoint.getRadians();
  }
}
