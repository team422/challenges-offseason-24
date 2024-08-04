import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.arm.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class Challenge2 {

  // utils for testing
  private static void myAssert(boolean condition, String message) {
    if (!condition) {
      System.out.println("Assertion failed: " + message);
      throw new AssertionError(message);
    }
  }

  private static double[] getRange(double min, double max, int length) {
    double[] range = new double[length];
    for (int i = 0; i < length; i++) {
      range[i] = min + i * (max - min) / (length - 1);
    }
    return range;
  }

  ArmIO armIO;
  TalonFX motor;

  @BeforeEach
  public void setup() {
    assert HAL.initialize(500, 0);

    armIO = new ArmIOKraken(5);
    try {
      motor = (TalonFX) armIO.getMotor();
    } catch (ClassCastException e) {
      myAssert(false, "getMotor() should return a TalonFX object. Do not modify this method.");
    }

    if (motor == null) {
      myAssert(
          false,
          "getMotor() should return a TalonFX object. If you have not modified the method and you are seeing this, you did not properly instantiate the TalonFX object.");
    }

    Timer.delay(0.100);
  }

  @AfterEach
  public void shutdown() {
    motor.close();
  }

  @Test
  public void setVoltageTest() {
    double[] voltages = {0.0, 0.2, -0.2, 1.0, -1.0, 6.0, -6.0, 12.0, -12.0};
    for (double voltage : voltages) {
      armIO.setVoltage(voltage);

      var info = motor.getAppliedControl().getControlInfo();
      String str = info.get("Output");
      myAssert(str != null, "Output voltage should not be null");
      double volts = Double.parseDouble(str);

      myAssert(
          Math.abs(volts - voltage) < 1e-4,
          String.format("Incorrect output voltage. Expected: %f, Actual: %f", voltage, volts));
    }
  }

  @Test
  public void getVelocityTest() {
    double[] velocities = getRange(0, 159, 50);
    for (double velocity : velocities) {
      var state = motor.getSimState();
      state.setRotorVelocity(velocity);
      Timer.delay(0.040);

      double output = armIO.getVelocityRadiansPerSecond();
      double expected = Units.rotationsToRadians(velocity);

      myAssert(
          Math.abs(output - expected) < 1e-1,
          String.format("Incorrect velocity. Expected: %f, Actual: %f", expected, output));
    }
  }

  @Test
  public void getPositionTest() {
    double[] positions = getRange(0, 10, 50);
    for (double position : positions) {
      var state = motor.getSimState();
      state.setRawRotorPosition(position);
      Timer.delay(0.040);

      Rotation2d output = armIO.getPosition();
      Rotation2d expected = Rotation2d.fromRotations(position);

      myAssert(
          Math.abs(output.getRadians() - expected.getRadians()) < 1e-1,
          String.format(
              "Incorrect position. Expected: %f, Actual: %f",
              expected.getRadians(), output.getRadians()));
    }
  }
}
