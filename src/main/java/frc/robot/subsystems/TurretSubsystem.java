// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TurretSubsystem extends SubsystemBase {
  private final MotionMagicVoltage motionMagic = new MotionMagicVoltage(0);
  private TalonFX motor;


  public TurretSubsystem() {
    this.motor = new TalonFX(Constants.turretConstans.KMotorID);

    TalonFXConfiguration configs = new TalonFXConfiguration();
    MotionMagicConfigs mm = new MotionMagicConfigs();

    mm.MotionMagicCruiseVelocity = Constants.turretConstans.mmVelocity;
    mm.MotionMagicAcceleration = Constants.turretConstans.mmAcceleration;
    mm.MotionMagicJerk = Constants.turretConstans.mmJerk;
    configs.MotionMagic = mm;

    configs.Slot0.kP =  Constants.turretConstans.KP;
    configs.Slot0.kD =  Constants.turretConstans.KD;
    configs.Slot0.kV =  Constants.turretConstans.KV;
    configs.Slot0.kS =  Constants.turretConstans.KS;

    configs.Voltage.PeakForwardVoltage = Constants.turretConstans.PeakForwardVoltage;
    configs.Voltage.PeakReverseVoltage = Constants.turretConstans.PeakReverseVoltage;
    configs.Feedback.SensorToMechanismRatio = Constants.turretConstans.SensorToMechanismRatio;

        // gives code to TalonFX
    StatusCode status = StatusCode.StatusCodeNotInitialized;
    for (int i = 0; i < 5; i++){
        status = motor.getConfigurator().apply(configs);
        if (status.isOK()){
          break;
        }
      }
      if (!status.isOK()){
        System.out.println("Could not apply configs, erro code: " + status.toString());
      }
        
      //make sure we start at 0.
      motor.setPosition(0);
    }
    



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
