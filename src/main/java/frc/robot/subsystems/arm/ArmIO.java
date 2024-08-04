package frc.robot.subsystems.arm;

import edu.wpi.first.math.geometry.Rotation2d;

public interface ArmIO {
  /**
   * Set the voltage of the arm
   *
   * @param voltage - The voltage to apply to the arm
   */
  public void setVoltage(double voltage);

  /**
   * Get the current voltage of the arm
   *
   * @return - The applied voltage of the arm
   */
  public double getVoltage();

  /**
   * Get the current velocity of the arm in radians per second
   *
   * @return - The current velocity of the arm
   */
  public double getVelocityRadiansPerSecond();

  /**
   * Get the current position of the arm as a Rotation2d
   *
   * @return - The current position of the arm
   */
  public Rotation2d getPosition();

  public Object getMotor();
}
