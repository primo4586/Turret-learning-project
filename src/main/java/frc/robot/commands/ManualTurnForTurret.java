// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TurretSubsystem;
import static frc.robot.Constants.Turret.*;

public class ManualTurnForTurret extends Command {
  private final TurretSubsystem turret = TurretSubsystem.getInstance();
  private int vector;
  private boolean start;

  public ManualTurnForTurret(int vector, boolean start) {  
    this.vector = vector; //vector is 1 or -1 for forward or backword
    this.start = start; //start is true or false for turn or not to turn
  }

  @Override
  public void initialize() {
    if (start){
      turret.setSpeed(turretManualSpeed * vector);
    }else{
      turret.setSpeed(0);
    }
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
