package frc.robot.subsystems.arm;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.geometry.Rotation2d;

public class ArmIOKraken implements ArmIO {
  // For instructions on how to implement this class, refer to the README.md file
  private TalonFX m_motor;

  public ArmIOKraken(int port) {
    // TODO: Implement this method
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
  public double getVelocityRadiansPerSecond() {
    // TODO: Implement this method
    return 0.0;
  }

  @Override
  public Rotation2d getPosition() {
    // TODO: Implement this method
    return null;
  }

  @Override
  public Object getMotor() {
    // DO NOT MODIFY THIS METHOD
    return m_motor;
  }
}
