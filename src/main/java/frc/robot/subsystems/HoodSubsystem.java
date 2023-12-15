// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.ControlModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.PdConstants;
import static frc.robot.Constants.HoodConstants.*;

public class HoodSubsystem extends SubsystemBase {
  /** Creates a new Hood. */

  //created the motor,MM, and the hood deg it's needs to be
  private TalonFX m_hood;
  private double hoodDeg;
  private final MotionMagicVoltage m_PositionVoltage = new MotionMagicVoltage(0);

  //the instance
  public static HoodSubsystem instance;

  public static HoodSubsystem getInstance(){
    if(instance == null){
      instance = new HoodSubsystem();
    }
    return instance;
  }

  //Constractor
  public HoodSubsystem() {
     this.m_hood = new TalonFX(HoodPort);
     hoodDeg = 0;

    //creat the full MotionMagic
    TalonFXConfiguration configuration = new TalonFXConfiguration();
    MotionMagicConfigs mm = new MotionMagicConfigs();

    mm.MotionMagicCruiseVelocity = PdConstants.mmc;
    mm.MotionMagicAcceleration = PdConstants.mma;
    mm.MotionMagicJerk=PdConstants.mmj;
    configuration.MotionMagic = mm;

    configuration.Slot0.kP = PdConstants.kp;
    configuration.Slot0.kS = PdConstants.ks;
    configuration.Slot0.kV = PdConstants.kv;
    configuration.Slot0.kS = PdConstants.ks;

    configuration.Voltage.PeakForwardVoltage = Constants.MaxVolConstants.peekFvol;
    configuration.Voltage.PeakReverseVoltage = Constants.MaxVolConstants.peekRvol;
    
    //set Ratio to 50:1
    configuration.Feedback.SensorToMechanismRatio =50;
    
    StatusCode statusCode = StatusCode.StatusCodeNotInitialized;

    for(int i=0;i<5;i++){
      statusCode = m_hood.getConfigurator().apply(configuration);
      if(statusCode.isOK())
        break;
    }
    if(!statusCode.isOK())
      System.out.println("Could not apply config, error code:"+statusCode.toString());

      m_hood.setPosition(0);
  }


  public double getHoodPose(){
    return this.hoodDeg;
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
