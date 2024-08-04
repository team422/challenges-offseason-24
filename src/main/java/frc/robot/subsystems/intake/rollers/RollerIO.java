package frc.robot.subsystems.intake.rollers;

import org.littletonrobotics.junction.AutoLog;

public interface RollerIO {
  @AutoLog
  public static class RollerInputs {
    public double voltage;
    public double velocityRadPerSec;
  }

  public void updateInputs(RollerInputs inputs);

  public void setVoltage(double voltage);

  public double getVoltage();

  public double getVelocityRadPerSec();
}
