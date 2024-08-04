package frc.robot.subsystems.flywheel;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;

public class FlywheelIOKraken implements FlywheelIO {
  private TalonFX m_flywheelMotor;
  private StatusSignal<Double> m_velocitySignal;
  private StatusSignal<Double> m_voltageSignal;
  private StatusSignal<Double> m_currentSignal;

  public FlywheelIOKraken(int motorPort) {
    m_flywheelMotor = new TalonFX(motorPort);
    m_velocitySignal = m_flywheelMotor.getVelocity();
    m_voltageSignal = m_flywheelMotor.getMotorVoltage();
    m_currentSignal = m_flywheelMotor.getSupplyCurrent();
  }

  @Override
  public void updateInputs(FlywheelInputs inputs) {
    BaseStatusSignal.refreshAll(m_velocitySignal, m_voltageSignal, m_currentSignal);

    inputs.curVelocity = m_velocitySignal.getValueAsDouble();
    inputs.curVoltage = m_voltageSignal.getValueAsDouble();
    inputs.curAmps = m_currentSignal.getValueAsDouble();
  }

  @Override
  public void setFlywheelVoltage(double voltage) {
    m_flywheelMotor.setVoltage(voltage);
  }
}
