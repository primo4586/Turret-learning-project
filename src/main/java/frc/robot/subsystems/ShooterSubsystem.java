// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Constants.ShooterConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

  // Creating the motors
  private TalonFX m_shooterMotor;
  private TalonSRX m_feederMotor;

  // Motion magic
  private final MotionMagicVelocityVoltage motionMagic = new MotionMagicVelocityVoltage(0);

  /** Creates a new TurrentShot. */
  private ShooterSubsystem() {

    // giving values to the motors
    this.m_shooterMotor = new TalonFX(ShooterConstants.KShooterMotorID);
    this.m_feederMotor = new TalonSRX(ShooterConstants.KFeederMotorID);

    // declaring Configs
    TalonFXConfiguration configs = new TalonFXConfiguration();
    MotionMagicConfigs mm = new MotionMagicConfigs();

    // giving motion magic values
    mm.MotionMagicCruiseVelocity = ShooterConstants.MotionMagicCruiseVelocity;
    mm.MotionMagicAcceleration = ShooterConstants.MotionMagicAcceleration;
    mm.MotionMagicJerk = ShooterConstants.MotionMagicJerk;
    configs.MotionMagic = mm;

    // giving PID values
    configs.Slot0.kP = ShooterConstants.kP;
    configs.Slot0.kD = ShooterConstants.kD;
    configs.Slot0.kV = ShooterConstants.kV;
    configs.Slot0.kS = ShooterConstants.kS;

    // max voltage for shotMotor
    configs.Voltage.PeakForwardVoltage = ShooterConstants.PeakForwardVoltage;
    configs.Voltage.PeakReverseVoltage = ShooterConstants.PeakReverseVoltage;

    configs.Feedback.SensorToMechanismRatio = ShooterConstants.SensorToMechanismRatio;

    StatusCode status = StatusCode.StatusCodeNotInitialized;
    for (int i = 0; i < 5; ++i) {
      status = m_shooterMotor.getConfigurator().apply(configs);
      if (status.isOK())
        break;
    }
    if (!status.isOK()) {
      System.out.println("Could not apply configs, error code: " + status.toString());
    }

    configs.Feedback.SensorToMechanismRatio = ShooterConstants.SensorToMechanismRatio;

  }

  private static ShooterSubsystem instance;

  public static ShooterSubsystem getInstance(){
    if (instance==null){
      instance = new ShooterSubsystem();
    }
    return instance;
  }

  public void setShooterSpeed(double shooterSpeed){
    this.m_shooterMotor.setControl(motionMagic.withVelocity(shooterSpeed));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
