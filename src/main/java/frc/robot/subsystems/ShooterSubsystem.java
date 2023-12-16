// Copyright (c) FIRST and other WPILib contributors.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.interpolation.InterpolateUtil;

import static frc.robot.Constants.ShooterConstants.*;

public class ShooterSubsystem extends SubsystemBase {

  // Creating the motors
  private TalonFX m_shooterMotor;
  private TalonSRX m_feederMotor;

  // Motion magic
  private final MotionMagicVelocityVoltage motionMagic = new MotionMagicVelocityVoltage(0);

  private static ShooterSubsystem instance;

  public static ShooterSubsystem getInstance() {
    if (instance == null) {
      instance = new ShooterSubsystem();
    }
    return instance;
  }

  /** Creates a new TurrentShot. */
  private ShooterSubsystem() {

    // giving values to the motors
    this.m_shooterMotor = new TalonFX(KShooterMotorID);
    this.m_feederMotor = new TalonSRX(KFeederMotorID);

    // declaring Configs
    TalonFXConfiguration configs = new TalonFXConfiguration();
    MotionMagicConfigs mm = new MotionMagicConfigs();

    // giving motion magic values
    mm.MotionMagicCruiseVelocity = MotionMagicCruiseVelocity;
    mm.MotionMagicAcceleration = MotionMagicAcceleration;
    mm.MotionMagicJerk = MotionMagicJerk;
    configs.MotionMagic = mm;

    // giving PID values
    configs.Slot0.kP = kP;
    configs.Slot0.kD = kD;
    configs.Slot0.kV = kV;
    configs.Slot0.kS = kS;

    // max voltage for shotMotor
    configs.Voltage.PeakForwardVoltage = PeakForwardVoltage;
    configs.Voltage.PeakReverseVoltage = PeakReverseVoltage;

    StatusCode status = StatusCode.StatusCodeNotInitialized;
    for (int i = 0; i < 5; ++i) {
      status = m_shooterMotor.getConfigurator().apply(configs);
      if (status.isOK())
        break;
    }
    if (!status.isOK()) {
      System.out.println("Could not apply configs, error code: " + status.toString());
    }

    configs.Feedback.SensorToMechanismRatio = SensorToMechanismRatio;

  }

  //activate feeder motor
  public void setFeederSpeed(double speedPrecent) {
    this.m_feederMotor.set(TalonSRXControlMode.PercentOutput, speedPrecent);
    ;
  }
  
  //activate shooter motor
  public void setShooterSpeed(double speed) {
    this.m_shooterMotor.setControl(motionMagic.withVelocity(speed));
  }

  public double getMotorSpeed() {
    return m_shooterMotor.getVelocity().getValue();
  }

  public boolean checksIfAtSpeed() {
    return (Math.abs(m_shooterMotor.getClosedLoopError().getValue()) < MaxError);

  }

  public double InterpolationValue(double distance){
    return InterpolateUtil.interpolate(ShooterInterpolation, distance);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
