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
import static frc.robot.Constants.turret.*;

public class TurretSubsystem extends SubsystemBase {
  private final MotionMagicVoltage motionMagic = new MotionMagicVoltage(0);
  private TalonFX motor;
  private static TurretSubsystem instance;
    // singleton
    public static TurretSubsystem getInstance(){
      if (instance == null){
        instance = new TurretSubsystem();
      }
      return instance;
    }  
  private TurretSubsystem() {
    this.motor = new TalonFX(KMotorID);

    TalonFXConfiguration configs = new TalonFXConfiguration();
    MotionMagicConfigs mm = new MotionMagicConfigs();

    mm.MotionMagicCruiseVelocity = mmVelocity;
    mm.MotionMagicAcceleration = mmAcceleration;
    mm.MotionMagicJerk = mmJerk;
    configs.MotionMagic = mm;

    configs.Slot0.kP = KP;
    configs.Slot0.kD = KD;
    configs.Slot0.kV = KV;
    configs.Slot0.kS = KS;

    configs.Voltage.PeakForwardVoltage = PeakForwardVoltage;
    configs.Voltage.PeakReverseVoltage = PeakReverseVoltage;
    configs.Feedback.SensorToMechanismRatio = SensorToMechanismRatio;

    configs.SoftwareLimitSwitch.ForwardSoftLimitEnable = ForwardSoftLimitEnable;
    configs.SoftwareLimitSwitch.ForwardSoftLimitThreshold = ForwardSoftLimitThreshold;
    configs.SoftwareLimitSwitch.ReverseSoftLimitEnable = ReverseSoftLimitEnable;
    configs.SoftwareLimitSwitch.ReverseSoftLimitThreshold = RevesrseSoftLimitThreshold;

    // gives code to TalonFX
    StatusCode status = StatusCode.StatusCodeNotInitialized;
    for (int i = 0; i < 5; i++){
        status = motor.getConfigurator().apply(configs);
        if (status.isOK()){
          break;
        }
      }
      if (!status.isOK()){
        System.out.println("Could not apply configs to turret, erro code: " + status.toString());
      }
        
      //make sure we start at 0.
      motor.setPosition(0);
    }

    // moves the turret to one place
    public void putTurretInPose(double degree){
      motor.setControl(motionMagic.withPosition(degree));
    }
    //get turret position
    public double getPostionTurret(){
      return this.motor.getPosition().getValue();
    }
    // checks if the turret is in the right position
    public boolean checkTurretPosition(){
      if (Math.abs(motor.getClosedLoopError().getValue()) < minimumError){
        return true;
      }
      return false;
    }
    //manual turn for the turret
    public void manualTurnForTurret(double speed){
      this.motor.set(speed);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
