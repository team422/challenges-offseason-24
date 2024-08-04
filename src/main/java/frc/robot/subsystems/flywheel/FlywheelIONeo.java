package frc.robot.subsystems.flywheel;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

public class FlywheelIONeo implements FlywheelIO {
  private CANSparkMax m_flywheelMotor;
  private RelativeEncoder m_flywheelEncoder;

  public FlywheelIONeo(int motorPort) {
    m_flywheelMotor = new CANSparkMax(motorPort, MotorType.kBrushless);
    m_flywheelEncoder = m_flywheelMotor.getEncoder();
  }

  @Override
  public void updateInputs(FlywheelInputs inputs) {
    inputs.curVelocity = m_flywheelEncoder.getVelocity();
    inputs.curVoltage = m_flywheelMotor.getAppliedOutput();
    inputs.curAmps = m_flywheelMotor.getOutputCurrent();
  }

  @Override
  public void setFlywheelVoltage(double voltage) {
    m_flywheelMotor.setVoltage(voltage);
  }
}
