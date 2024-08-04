package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {
  @AutoLog
  public static class IntakeInputs {
    public double rollerVoltage;
    public double rollerVelocity;
    public double rollerCurrent;
  }

  public void updateInputs(IntakeInputs inputs);

  public void setRollerVoltage(double voltage);

  public double getRollerVoltage();

  public double getRollerVelocityRPM();
}
