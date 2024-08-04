package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

public class FlywheelIONeo implements FlywheelIO {
  private CANSparkMax m_motor;
  private RelativeEncoder m_encoder;

  public FlywheelIONeo(int motorPort) {
    m_motor = new CANSparkMax(motorPort, MotorType.kBrushless);
    m_encoder = m_motor.getEncoder();
  }

  @Override
  public void updateInputs(FlywheelInputs inputs) {
    inputs.voltage = m_motor.getBusVoltage();
    inputs.current = m_motor.getOutputCurrent();
    inputs.velocity = m_encoder.getVelocity();
  }

  @Override
  public void setVoltage(double voltage) {
    m_motor.setVoltage(voltage);
  }
}
